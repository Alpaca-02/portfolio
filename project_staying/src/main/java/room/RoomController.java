package room;

import java.io.File;
import java.io.IOException;
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

import Review.ReviewService;
import Review.ReviewVO;
import host.HostService;
import host.HostVO;
import member.MemberService;


@WebServlet("/room/*")
public class RoomController extends HttpServlet {
	RoomService roomService;
	RoomVO roomVO;
	
	// 파일 업로드 주소 상수
   	private static String ART_IMAGE_REPO = "C:\\staying\\room_image";


	public void init(ServletConfig config) throws ServletException {
		roomService = new RoomService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 포워딩할 페이지를 결정하는 링크 변수
		String nextPage = null;
		
		// 페이지 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// URL 요청명을 가져옴
		String action = request.getPathInfo();
		System.out.println("요청 URL : "+ action);
		
		if(action == null || action.equals("")) {
			// url요청명이 비어있을경우
			nextPage="/member_login.jsp";
			
		}else if(action.equals("/addRoom.do")) {
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			String host_id = (String)session.getAttribute("log_id");  // 세션에서 로그인 id값 가져옴
			
			Map<String, String> articleMap=upload(request, response);
			
			
			
			String room_name = articleMap.get("room_name");
			String room_info = articleMap.get("room_facility");
			

			int room_price = Integer.parseInt(articleMap.get("room_price"));
			int room_people = Integer.parseInt(articleMap.get("room_per_num"));
			System.out.println(room_price);
			System.out.println(room_people);
			
			String room_img = articleMap.get("room_image");
			
			roomVO = new RoomVO();
			roomVO.setHost_id(host_id);
			System.out.println(host_id);
			
			roomVO.setRoom_name(room_name);
			roomVO.setRoom_info(room_info);
			roomVO.setRoom_price(room_price);
			roomVO.setRoom_people(room_people);
			roomVO.setRoom_img(room_img);
			
			int room_no = roomService.addRoom(roomVO);
			
			if(room_img != null && room_img.length() != 0) {
				// temp폴더에 임시로 업로드된 파일객체를 생성
				File srcFile = new File(ART_IMAGE_REPO + "\\"+"temp\\"+ room_img);
				// 글 번호로 폴더를 생성
				File destDir = new File(ART_IMAGE_REPO+"\\"+room_no);
				destDir.mkdir();  // 방 번호로 해당 경로의 폴더생성
				
				// temp폴더의 파일을 글번호 폴더로 이동
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				srcFile.delete();  // temp의 임시 파일을 삭제함
			}
			
			String user_type = (String) session.getAttribute("user_kind"); // 로그인된 유저 타입을 확인
			request.setAttribute("user_type", user_type); // 로그인된 유저 타입을 전달
			nextPage="/room/my_room_info.do";
			
		}else if(action.equals("/add_room_form.do")) {
			// 방 등록페이지 요청
			
			HttpSession session = request.getSession();  // 세션 생성
			String host_id = (String)session.getAttribute("log_id");

			HostService hostService = new HostService();
			HostVO hostVO = hostService.info_ro_host(host_id);
			
			
			String user_type = (String) session.getAttribute("user_kind"); // 로그인된 유저 타입을 확인
			request.setAttribute("user_type", user_type); // 로그인된 유저 타입을 전달
			
			request.setAttribute("host_info", hostVO);
			nextPage= "/room_add_form.jsp";
			
			
			
		}else if(action.equals("/roomInfo.do")) {
			// 방 정보 요청
			String host_id = request.getParameter("host_id");
			HostService hostService = new HostService();
			HostVO hostVO = hostService.info_ro_host(host_id);
			
			System.out.println(host_id);
			
			List<RoomVO> roomList = roomService.hostRoomList(host_id);
			
			ReviewService reviewService = new ReviewService();
			List<ReviewVO> review_list = reviewService.review_list_host(host_id);
			
			// 해당 사업자의 방 목록을 포워딩
			request.setAttribute("hostInfo", hostVO);
			request.setAttribute("roomList", roomList);
			// 해당 방의 리뷰를 포워딩
			request.setAttribute("review_list", review_list);
			
			HttpSession session = request.getSession();  // 세션 생성 또는 가져옴
			String user_type = (String) session.getAttribute("user_kind"); // 로그인된 유저 타입을 확인
			request.setAttribute("user_type", user_type); // 로그인된 유저 타입을 전달
			
			nextPage ="/stay_info.jsp";
			
		}else if(action.equals("/my_room_info.do")) {
			// 내가 등록한 방 정보 요청
			HttpSession session = request.getSession();  // 세션 생성
			String host_id = (String)session.getAttribute("log_id");  // 로그인한 사업자의 아이디를 가져옴
			
			List<RoomVO> roomList = roomService.hostRoomList(host_id);  // 로그인된 사업자id의 방 리스트를 가져옴
			
			HostService hostService = new HostService();
			HostVO hostVO = hostService.info_ro_host(host_id);
			
			ReviewService reviewService = new ReviewService();
			List<ReviewVO> review_list = reviewService.review_list_host(host_id);
			
//			System.out.println(roomList.get(0).getRoom_info());
			request.setAttribute("host_info", hostVO);
			request.setAttribute("room_list", roomList);
			request.setAttribute("review_list", review_list);
			
			request.setAttribute("user_type", "host"); // 로그인된 유저 타입을 전달
			
			nextPage = "/host_my_list.jsp";
			
		}
//		else if(action.equals("/delRoom.do")){
//			// 방 삭제를 요청받은 경우
//			int room_no = Integer.parseInt(request.getParameter("room_no"));
//			
//			// 글을 삭제하면서 관련 정보 객체를 가져옴
//			roomVO = roomService.delRoom(room_no);
//			
//			File imgDir = new File(ART_IMAGE_REPO+"\\"+roomVO.getRoom_no());
//			if(imgDir.exists()) { // 해당 이미지 폴더가 존재할 경우
//				FileUtils.deleteDirectory(imgDir); // 해당 폴더를 삭제
//			}
//		}
		
		else if(action.equals("/delRoom.do")){
			// 방 삭제를 요청받은 경우
			String[] room_no_st = request.getParameterValues("room_no");
			
			for(String room_no_str : room_no_st) {
				int room_no = Integer.parseInt(room_no_str);
				
				// 글을 삭제하면서 관련 정보 객체를 가져옴
				roomVO = roomService.delRoom(room_no);
				
				File imgDir = new File(ART_IMAGE_REPO+"\\"+roomVO.getRoom_no());
				if(imgDir.exists()) { // 해당 이미지 폴더가 존재할 경우
					FileUtils.deleteDirectory(imgDir); // 해당 폴더를 삭제
				}
	
			}
			nextPage="/host/my_room_info.do";
			
		}
		
		
		
		// nextPage의 링크에 바인딩
				System.out.println("보내질 URL : " + nextPage);
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
				dispatcher.forward(request, response);
	}
		
	
	// 이미지 파일 업로드 메서드
		private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
			 // Map-> key와 value를 가진 object 구조
			Map<String, String> articleMap= new HashMap<String, String>();
			String encoding="utf-8"; // 파일명 인코딩 지정
			File currentDirPath=new File(ART_IMAGE_REPO); //이미지 경로 지정
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
