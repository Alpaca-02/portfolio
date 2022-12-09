package booking;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import host.HostService;
import host.HostVO;
import member.MemberService;
import member.MemberVO;
import room.RoomService;
import room.RoomVO;


@WebServlet("/booking/*")
public class BookingController extends HttpServlet {
	BookingService bookingService;

	public void init(ServletConfig config) throws ServletException {
		bookingService = new BookingService();
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
			nextPage="/host/main_page.jsp";
		}else if(action.equals("/booking_room.do")) {
			// 예약 생성
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			
			String is_logon = (String)session.getAttribute("isLogon");
			if(!is_logon.equals("true")) {  // 로그인 상태가 아닐경우
				nextPage="/login.jsp";  
				return;  // 로그인 페이지로 연결
			}
			
			// 로그인중인 경우 예약페이지 연결
			String id = (String)session.getAttribute("log_id"); // 세션에서 id를 가져옴
			
			int room_no = Integer.parseInt(request.getParameter("room_no"));  // 방번호
			String room_name = request.getParameter("room_name");  // 방 이름
			String host_ro_img = request.getParameter("host_ro_img");  // 업체 이미지파일명
			String host_ro_name = request.getParameter("host_ro_name"); // 업체 이름
			
			// 체크인 아웃 날짜를 yyyyMMdd형태 int로 저장
			int checkInDate = Integer.parseInt(repString(request.getParameter("check_in")));
			int checkOutDate = Integer.parseInt(repString(request.getParameter("check_out")));
			
			bookingService.add_book(checkInDate,checkOutDate,id,room_no,room_name,host_ro_img, host_ro_name);
			request.setAttribute("msg", "예약이 완료되었습니다.");
			nextPage="/booking/member_book_list.do";
			
		}else if(action.equals("/booking_page.do")) {
			// 예약 확인페이지 요청
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			String loged_in = (String) session.getAttribute("isLogon");
			String user_type = (String) session.getAttribute("user_kind");
			
			if(loged_in != null) {  // 로그인 되어있는 경우
				if(user_type.equals("user")) { // 유저로 로그인되어있는 경우
					
					int cki_date = Integer.parseInt(String.valueOf(session.getAttribute("cki_date"))); //체크인 날짜
					int cko_date = Integer.parseInt(String.valueOf(session.getAttribute("cko_date"))); // 체크아웃 날짜
					System.out.println(cki_date);
					System.out.println(cko_date);
					
					String id = (String) session.getAttribute("log_id");
					MemberService memberService = new MemberService();
					MemberVO memberVO = memberService.infoMember(id);
					
					
					int room_no = Integer.parseInt(request.getParameter("room_no"));  // 방 번호를 가져옴
					String host_id = request.getParameter("host_id");
					System.out.println(host_id);
					System.out.println(room_no);
					
					HostService hostService = new HostService();
					HostVO hostVO = hostService.info_ro_host(host_id);
					
					RoomService roomService = new RoomService();
					RoomVO roomVO = roomService.room_no_info(room_no); // 방 번호로 방 정보를 불러옴
					
					// 두 날짜간 간격을 통해 숙박일수를 구함
					int day=0;
					for (int i = cki_date; i < cko_date; i++) {
						day++; 
					}
					// 1박 가격에 숙박일수를 곱해 총 숙박비를 계산
					int full_price = (roomVO.getRoom_price()*day);  
					
					String cki_res = date_to_kr(cki_date);
					String cko_res = date_to_kr(cko_date);
					System.out.println(cki_res);
					
					request.setAttribute("day",day);
					
					request.setAttribute("cki_date", cki_res);
					request.setAttribute("cko_date", cko_res);
					
					request.setAttribute("cki_date_ori", cki_date);
					request.setAttribute("cko_date_ori", cko_date);
					
					request.setAttribute("full_price", full_price);
					
					request.setAttribute("user_info", memberVO);
					request.setAttribute("room_info", roomVO);
					request.setAttribute("host_info", hostVO);
					
					nextPage="/stay_book.jsp";		
				}else {
					// 사업자일 경우 로그아웃 시키고 유저로 로그인 시킴
					session.removeAttribute("log_id");
					session.removeAttribute("isLogon");
					session.removeAttribute("user_kind");
					
					request.setAttribute("msg", "유저 전용 서비스입니다.");
					
					nextPage = "/login.jsp";
				}
				
			} else {  // 로그인이 필요한 경우
				request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
				nextPage = "/login.jsp";
			}
			
			
				
		}else if(action.equals("/member_book_list.do")) {
			// 마이페이지 예약한 내역 불러오기
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			String log_id = (String) session.getAttribute("log_id");  // 세션에서 로그인한 아이디를 가져옴
			
			List<BookingVO> book_list = bookingService.get_member_book(log_id); // 해당 id의 예약정보를 불러옴
			
			request.setAttribute("book_list", book_list);  // 예약 리스트를 포워딩
			nextPage= "/member_book.jsp";
			
		}else if(action.equals("/del_book.do")){
			int book_no =  Integer.parseInt(request.getParameter("book_no")); // 예약번호를 가져옴
			int room_no =  Integer.parseInt(request.getParameter("room_no"));  // 방 번호
			int cki = Integer.parseInt(request.getParameter("cki"));  // 체크인 날짜 yyyyMMdd
			int cko = Integer.parseInt(request.getParameter("cko"));
			System.out.println("cki : "+cki);
			System.out.println("cko : "+cko);
			System.out.println("book_no : "+book_no);
			System.out.println("room_no : "+room_no);
			
			bookingService.del_book(book_no, room_no, cki, cko);
			
			request.setAttribute("msg", "취소되었습니다.");
			nextPage="/booking/member_book_list.do";
			
		}else {
			nextPage="/host/main_page.do";
		}
		// nextPage의 링크에 바인딩
		System.out.println("보내질 URL : " + nextPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	}
	
	// 문자열에서 특수문자를 제거하는 메서드
	public static String repString(String str){
        String match = "[^\uAC00-\uD7A30-9a-zA-Z\\s]";
        str = str.replaceAll(match, "");
        return str;
    }
	
	public static String date_to_kr(int str_date) {
		String date = Integer.toString(str_date);  // 문자열을 문자로 변경
		StringBuffer sb = new StringBuffer(date);
		sb.delete(0, 4);
		sb.insert(2, "월 ");
		sb.insert(6, "일");
		
		String result = sb.toString();
		return result;
	}

}
