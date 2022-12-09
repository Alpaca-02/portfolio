package host;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;


import member.MemberService;
import member.MemberVO;


@WebServlet("/host/*")
public class HostController extends HttpServlet {
   HostService hostService;
   HostVO hostVO;
       
    // 파일 업로드 주소 상수
   	private static String ART_IMAGE_REPO = "C:\\staying\\host_image";

	public void init(ServletConfig config)throws ServletException{
		hostService = new HostService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage=""; // 포워딩할 페이지를 담을 변수
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getPathInfo();  // URL 요청명을 받아옴
		System.out.println("요청 이름 : " + action);   // 요청명 콘솔에서 확인
		

		if(action == null|| action.equals("")) {
			nextPage="host/main_page.do";
		}else if(action.equals("/hostInfoTo.do")){
			
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("pwd", request.getParameter("pwd"));
			request.setAttribute("phone", request.getParameter("phone"));
			nextPage="/host_join2.jsp";

			
		}else if(action.equals("/addHost.do")){
			// 숙박시설 등록 요청
			Map<String, String> articleMap=upload(request, response);
			// 새 페이지에서 입력받은 부분
			String host_id = articleMap.get("id");
			String host_pwd = articleMap.get("pwd");
			String host_phone = articleMap.get("phone");
			String host_name = articleMap.get("name");
			
			// 두번째 페이지에서 입력한 부분
			String ro_name = articleMap.get("stay_name");
			String ro_addr = articleMap.get("roadAddress");  // 도로명 주소
			System.out.println(ro_addr);
			// 숙박업소면 상세주소 까지는 필요 없을 것 같아서...
			
			String latitude = articleMap.get("latitude"); // 위도값
			String longitude = articleMap.get("longitude");  // 경도값
			
			// 시설 종류 1~4의 값으로 받아서 구분한다.
			String ro_kind = articleMap.get("stay_kind");
			
			String ro_content = articleMap.get("stay_content");
			String ro_img = articleMap.get("stay_image");
			
			hostVO = new HostVO();
			
			hostVO.setHost_name(host_name);
			hostVO.setHost_id(host_id);
			hostVO.setHost_pwd(host_pwd);
			hostVO.setHost_phone(host_phone);
			hostVO.setHost_ro_name(ro_name);
			hostVO.setHost_ro_addr(ro_addr);
			hostVO.setHost_ro_kind(ro_kind);
			hostVO.setHost_ro_latitude(latitude);
			hostVO.setHost_ro_longitude(longitude);
			
			hostVO.setHost_ro_content(ro_content);
			hostVO.setHost_ro_img(ro_img);
			
			hostService.addHost(hostVO);
			
			
			if(ro_img != null && ro_img.length() != 0) {
				// temp폴더에 임시로 업로드된 파일객체를 생성
				File srcFile = new File(ART_IMAGE_REPO + "\\"+"temp\\"+ ro_img);
				// 글 번호로 폴더를 생성
				File destDir = new File(ART_IMAGE_REPO+"\\"+host_id);
				destDir.mkdir();  // 해당 경로의 폴더생성
				
				// temp폴더의 파일을 글번호 폴더로 이동
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				srcFile.delete();  // temp의 임시 파일을 삭제함
			}
			
			request.setAttribute("msg", "사업자 회원가입이 완료되었습니다.");
			nextPage="/login.jsp";
			
		}else if (action.equals("delMember")) {
			// 호스트 계정을 삭제 요청
 			
			// 삭제할 id값을 가져옴
			String id = request.getParameter("id");
			hostService = new HostService();
			
			// MemberService에서 삭제요청
			hostService.delHost(id);
			
			
		}else if (action.equals("/id_check")) {
			// 아이디 중복조회 요청시
			PrintWriter out = response.getWriter();
			String id = (String)request.getParameter("id");
			
			hostService = new HostService();
			boolean overlappedID = hostService.overlappedID(id);
			if(overlappedID) {
				out.print("not_usable");  // 중복 계정이 있을경우
			}else {
				out.print("usable");  // 중복 계정이 없을경우
			}
			return;
			
		}else if (action.equals("/hostLogin.do")) {
			// 로그인 요청받을 경우
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			HostVO hostVO = new HostVO();
			hostVO.setHost_id(id);
			hostVO.setHost_pwd(pwd);
			
			boolean loggedIn = hostService.isExisted(hostVO);
			// id, pwd가 일치할 경우 true 아닐경우 false를 반환
			
			if(loggedIn) {
				// id, pwd가 일치할 경우
				
				HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
				
				session.setAttribute("isLogon", "true");  // logon 확인 -> true
				session.setAttribute("user_kind", "host");
				session.setAttribute("log_id", id);     // 세션에 로그인 저장
				
				System.out.println((String)session.getAttribute("log_id") + "로 로그인됨");
				nextPage="/host/main_page.do";
				
			}else {
				// id나 pwd가 일치하지 않을 경우
				request.setAttribute("msg", "ID나 패스워드를 다시 확인해주세요.");
				nextPage = "/login.jsp";
				
			}
		}else if(action.equals("/search.do")) {
			// 검색 요청시 넘겨줄 방목록
			
			// 체크인 아웃 날짜를 가져와서 '-'를 제거후 int로 저장
			int checkInDate = Integer.parseInt(repString(request.getParameter("check_in")));
			// 체크인 아웃 날짜를 가져와서 '-'를 제거후 int로 저장
			int checkOutDate = Integer.parseInt(repString(request.getParameter("check_out")));
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			session.setAttribute("cki_date", checkInDate);  // 체크인 날짜 (yyyyMMdd)
			session.setAttribute("cko_date", checkOutDate); // 체크아웃 날짜 (yyyyMMdd)
			
			String room_kind = request.getParameter("room_kind");  // 숙소 유형
			String room_lowprice = request.getParameter("min_price");  // 최저가격
			String room_highprice = request.getParameter("max_price");  // 최고가격
			int room_people = Integer.parseInt(request.getParameter("stay_person")); // 숙박인원
			String rate = request.getParameter("grade_value");  // 별점 조건
			
			List<HostVO> searchedHost = hostService.searchHost(checkInDate, checkOutDate, room_kind, room_lowprice, room_highprice, room_people, rate);
			
			String user_type = (String) session.getAttribute("user_kind"); // 로그인된 유저 타입을 확인
			request.setAttribute("user_type", user_type); // 로그인된 유저 타입을 전달
			
			// 검색했던 조건을 다음창에도 유지하기 위해 검색 조건을 포워딩
			request.setAttribute("check_in", request.getParameter("check_in"));    
			request.setAttribute("check_out", request.getParameter("check_out"));
			request.setAttribute("person", room_people);
			request.setAttribute("room_kind", room_kind);
			
			
			request.setAttribute("hostList", searchedHost);  // 생성한 리스트를 포워딩
			nextPage = "/stay_list.jsp";		
		}
		
		else if(action.equals("/main_page.do") ){
			// 메인페이지를 요청했을 경우
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			String user_type = (String) session.getAttribute("user_kind"); // 로그인된 유저 타입을 확인
			
			
			// 각 숙박업소간 상위 5위 리스트를 생성
			List<HostVO> bestPension = hostService.bestRate("pension");
			List<HostVO> bestHotel = hostService.bestRate("hotel");
			List<HostVO> bestCamping = hostService.bestRate("camping");
			List<HostVO> bestHanok = hostService.bestRate("hanok");
			
			request.setAttribute("best_pension", bestPension);
			request.setAttribute("best_hotel", bestHotel);
			request.setAttribute("best_camping", bestCamping);
			request.setAttribute("best_hanok", bestHanok);
			
			
			request.setAttribute("user_type", user_type); // 로그인된 유저 타입을 전달
			
			nextPage = "/index.jsp";
			
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
	
	// 이미지 파일 업로드 메서드
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		 
		Map<String, String> articleMap= new HashMap<String, String>();
		String encoding="utf-8"; // 파일명 인코딩 지정
		File currentDirPath=new File(ART_IMAGE_REPO); //이미지 경로 지정 (미리 지정한 경로 변수)
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath); // 업로드할 repo를 설정
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);  // factory에 있는 파일을 업로드
		try {
			List items=upload.parseRequest(request);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if(fileItem.isFormField()) {   // Form에서 온 데이터인지 확인
					// 파일 이름을 지정했던 인코딩 방식으로 출력해봄
					// 파일 업로드로 같이 전송된 새 글 관련 (제목, 내용) 매개변수를 저장한 후 반환
					System.out.println((fileItem.getString()+ " = "+fileItem.getString(encoding)));  
					
					// 이미지 이름을 DB 필드에 저장하기 위해 컬럼에 저장 (articleMap에 담음)
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("파라미터 이름 : "+ fileItem.getFieldName());
					System.out.println("파일 이름 : "+ fileItem.getName());
					System.out.println("파일 크기 : "+ fileItem.getSize()+"bytes");
					if(fileItem.getSize()>0) { // 파일 사이즈가 0이 아닐경우
						// 마지막 \\ 뒤의 내용 (이미지의 이름)의 위치 [없을경우 -1을 반환]
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {  // 해당 내용을 찾지 못했을 경우
							// 혹시 \\이 아닌 /로 경로 구분을 했는지 확인
							idx=fileItem.getName().lastIndexOf("/");
						}
						//  \\가 끊긴 다음부터 String이 파일이름
						String fileName = fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						
						// 업로드한 이미지를 일단 temp에 저장
						File uploadFile= new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		}catch(Exception e) {
			System.out.println("파일 업로드중 에러 : " + e.getMessage());
		}
		return articleMap;

	}

}
