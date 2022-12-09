package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/member/*")
public class MemberController extends HttpServlet {
		MemberService memberService;
	
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 포워딩할 페이지를 결정하는 링크 변수
		String nextPage = "index.jsp";
		
		// 페이지 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// getPathInfo() 메서드로 URL요청명을 가져온다
		String action = request.getPathInfo();
		System.out.println("요청 URL : "+ action);

		if(action == null || action.equals("")) {
			nextPage="/member_login.jsp";
		}else if (action.equals("/addMember.do")){
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			
			MemberVO memberVO = new MemberVO(id,pwd,phone, name);
			// memberService에 유저 등록 요청
			memberService= new MemberService();
			memberService.addMember(memberVO);
			
			nextPage="/member_join.jsp";
			
		}else if (action.equals("/id_check")) {
			// 아이디 중복조회 요청시
			PrintWriter out = response.getWriter();
			String id = (String)request.getParameter("id");
			
			MemberService memberService = new MemberService();
			boolean overlappedID = memberService.overlappedID(id);
			if(overlappedID) {
				out.print("not_usable");  // 중복 계정이 있을경우
			}else {
				out.print("usable");  // 중복 계정이 없을경우
			}
			return;
			
		}else if(action.equals("/phoneCK")) {
			// 인증번호 요청 받을시

			String ph_Num = request.getParameter("ph_num");
			memberService = new MemberService();
			String ran_Num = memberService.sendRandomMessage(ph_Num);
					
			System.out.println("전화번호 : "+ph_Num);
			System.out.println("랜덤난수 : "+ran_Num);
			
			HttpSession session = request.getSession();  // 세션 생성
			// 난수를 세션에 저장
			session.setAttribute("ran_Num", ran_Num);
			return;
		}else if(action.equals("/ck_num")) {
			// 인증번호 확인용 (ajax로 인증번호를 보냄)
			String ck_num = request.getParameter("ck_num");
			
			HttpSession session = request.getSession();  // 세션에서 난수를 가져옴
			String ran_Num = (String) session.getAttribute("ran_Num");
			
			PrintWriter out = response.getWriter();
			
			
			if(ran_Num.equals(ck_num)) {
				out.print("correct");
			}else {
				out.print("non_correct");
			}
			return;
		}else if (action.equals("delMember")) {
			
			// 삭제할 id값을 가져옴
			String id = request.getParameter("id");
			memberService = new MemberService();
			
			// MemberService에서 삭제요청
			memberService.delMember(id);
			
			
		}else if(action.equals("/member_info.do")) {
			// 유저 정보 요청 (내 정보 보기)
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴		
			String id = (String)session.getAttribute("log_id");  // 로그인 된 id를 가져옴
			System.out.println(id);
			
			if(id==null || id.equals("")) {  // 로그인 되어있지 않을 경우
				request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
				nextPage = "/login.jsp";   // 로그인 페이지로 이동
			}else {
				
				MemberVO memberVO = new MemberVO();
				
				memberService = new MemberService();
				//해당 id의 유저정보를 가져옴
				memberVO = memberService.infoMember(id);
				System.out.println(memberVO.getId());
				System.out.println(memberVO.getPwd());
				
				request.setAttribute("memberInfo", memberVO);
				nextPage="/member_info.jsp";
			}
			
		}else if (action.equals("/memberLogin.do")) {
			// 로그인 요청받을 경우
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			MemberVO memberVO = new MemberVO();
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			
			boolean loggedIn = memberService.isExisted(memberVO);
			// id, pwd가 일치할 경우 true 아닐경우 false를 반환
			
			if(loggedIn) {
				// id, pwd가 일치할 경우
				
				HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
				
				session.setAttribute("isLogon", "true");  // logon 확인 -> true
				session.setAttribute("user_kind", "user");
				session.setAttribute("log_id", id);     // 세션에 로그인 저장
				
				System.out.println((String)session.getAttribute("log_id") + "로 로그인됨");
				nextPage="/host/main_page.do";
				
			}else {
				// id나 pwd가 일치하지 않을 경우
				request.setAttribute("msg", "ID나 패스워드를 다시 확인해주세요.");
				nextPage = "/login.jsp";

			}
		
		
		}else if(action.equals("/naver_login_req")) {
			// 네이버 로그인 요청시 ( 가입되어있는지 확인 )
			String email = request.getParameter("email");
			String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
			boolean result = memberService.idExist(email);
			
			MemberVO memberVO = new MemberVO();
			
			if(result) { // 이미 존재하는 아이디 인경우
				String pwd = memberService.pwdFind(email);
				memberVO.setId(email);
				memberVO.setPwd(pwd);
				
				boolean loggedIn = memberService.isExisted(memberVO);
				if(loggedIn) {
					HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
					
					session.setAttribute("log_id", email);     // 세션에 로그인 저장
					session.setAttribute("isLogon", "true");  // logon 확인 -> true
					session.setAttribute("user_kind", "user");
					System.out.println((String)session.getAttribute("log_id") + "로 로그인됨");
					nextPage="/host/main_page.do";
				}else {
					nextPage = "/host/main_page.do";
				}
				
				
				
			}else { // 아직 생성 전의 아이디인 경우
				
				memberVO.setId(email);
				memberVO.setName(name);
//				memberVO.setPhone(phone);
				
				request.setAttribute("memberVO", memberVO);
				nextPage="/member_naver_join.jsp";
			}
			
			
		}else if(action.equals("/update_password.do")){
			String pwd = request.getParameter("pwd");
			String id = request.getParameter("id");
			
			MemberVO memberVO = new MemberVO();
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			
			memberService.update_pwd(memberVO);
			request.setAttribute("msg", "비밀번호가 변경되었습니다.");
			nextPage="/member/member_info.do";
			
		}else if(action.equals("/logout.do")){
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			session.removeAttribute("log_id");
			session.removeAttribute("isLogon");
			session.removeAttribute("user_kind");
			nextPage="/host/main_page.do";
		}else {
			nextPage="/host/main_page.do";
		}

		
		// nextPage의 링크에 바인딩
		System.out.println("보내질 URL : " + nextPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
