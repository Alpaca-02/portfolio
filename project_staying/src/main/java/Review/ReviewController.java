package Review;

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

import booking.BookingService;


@WebServlet("/review/*")
public class ReviewController extends HttpServlet {
	private static String ART_IMAGE_REPO="C:\\staying\\review_image";
	ReviewService boardService;
	ReviewVO reviewVO;
	public void init(ServletConfig config) throws ServletException {
		boardService=new ReviewService();
		reviewVO=new ReviewVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage="";
		PrintWriter pw;
		HttpSession session;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getPathInfo(); //요청명을 가져온다.
		
		
		System.out.println("요청 이름 : " + action);
		try {
			List<ReviewVO> reviewList=new ArrayList<ReviewVO>();
			if(action == null) {
				
				nextPage="/host/main_page.do";				
			}else if(action.equals("/add_review.do")) { // 새 글 쓰기 작업을 수행
				int rv_no=0;
				//파일 업로드 기능을 사용하기 위해 upload로 요청을 전달
				session = request.getSession();
				String id = (String) session.getAttribute("log_id");
				
				Map<String, String> articleMap=upload(request, response);
				
				String host_id = articleMap.get("host_id");  // host_id 받아와야함
				int book_no = Integer.parseInt(articleMap.get("book_no"));
				
				String rv_content = articleMap.get("review_content");
				String rv_img = articleMap.get("review_image");
				int rv_rate = Integer.parseInt( articleMap.get("review_star"));  // 별점 0~100
				
				reviewVO = new ReviewVO();
				reviewVO.setRv_type(0);  // 리뷰글의 경우는 0으로 지정
				reviewVO.setId(id);
				
				reviewVO.setBook_no(book_no);
				reviewVO.setHost_id(host_id);
				
				reviewVO.setRv_content(rv_content);				
				reviewVO.setRv_img(rv_img);	
				reviewVO.setRv_rate(rv_rate);
				rv_no=boardService.addReview(reviewVO);
				
				BookingService bookingService = new BookingService();
				bookingService.set_after(book_no);  // 해당 예약의 리뷰 작성 여부를 변경
				
				//이미지를 첨부한 경우에만 수행
				if(rv_img != null && rv_img.length() != 0) {					
					//temp 폴더에 임시로 업로드된 파일객체를 생성
					File srcFile=new File(ART_IMAGE_REPO+"\\temp\\" + rv_img);
					//글 번호로 폴더를 생성
					File destDir=new File(ART_IMAGE_REPO+"\\" + rv_no);
					destDir.mkdir();
					//temp폴더의 파일을 글 번호 이름으로 하는 폴더로 이동
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}
				
				request.setAttribute("msg", "리뷰가 등록되었습니다.");
				nextPage="/booking/member_book_list.do";
				
			}
			else if(action.equals("/add_reply.do")) {
				session = request.getSession();
				String id = (String) session.getAttribute("log_id");
				
				int parent_no = Integer.parseInt(request.getParameter("rv_no"));
				
				String rv_content = request.getParameter("reply_content");
				int book_no = Integer.parseInt(request.getParameter("book_no"));
				
				
				reviewVO = new ReviewVO();
				reviewVO.setRv_parent(parent_no);
				reviewVO.setRv_type(1);  // 답글의 경우는 1으로 지정
				reviewVO.setId(id);
				reviewVO.setBook_no(book_no);
				reviewVO.setHost_id(id);
				reviewVO.setRv_content(rv_content);	
				
				ReviewService reviewService = new ReviewService();
				reviewService.addReply(reviewVO);
				
				nextPage="/room//my_room_info.do";
			}
			
			
//			else if(action.equals("/stay_info.do")) {
//				String rv_no=request.getParameter("rv_no");
//				System.out.println(rv_no);
//				reviewVO=boardService.viewReview(Integer.parseInt(rv_no));
//				request.setAttribute("review", reviewVO);
//				nextPage="/boardinfo/stay_info.jsp";
//			}
			
			
//			else if(action.equals("/modReview.do")) {
//				Map<String, String> articleMap=upload(request,response);
//				int rv_no=Integer.parseInt(articleMap.get("rv_no"));
//				reviewVO.setRv_no(rv_no);
//				String id=articleMap.get("id");
//				String rv_content=articleMap.get("rv_content");
//				String rv_imageFileName=articleMap.get("rv_imageFileName");
//				reviewVO.setRv_parentNo(0);
//				reviewVO.setId(id);
//				reviewVO.setRv_content(rv_content);
//				reviewVO.setRv_imageFileName(rv_imageFileName);
//				boardService.modReview(reviewVO);
//				//이미지를 첨부한 경우에만 수행
//				if(rv_imageFileName != null && rv_imageFileName.length() != 0) {
//					String originalFileName=articleMap.get("originalFileName");
//					//temp 폴더에 임시로 업로드된 파일객체를 생성
//					File srcFile=new File(ART_IMAGE_REPO+"\\" + "temp\\" + rv_imageFileName);
//					//글 번호로 폴더를 생성
//					File destDir=new File(ART_IMAGE_REPO+"\\" + rv_no);
//					destDir.mkdir();
//					//temp폴더의 파일을 글 번호 이름으로 하는 폴더로 이동
//					FileUtils.moveFileToDirectory(srcFile, destDir, true);
//					File oldFile=new File(ART_IMAGE_REPO+"\\" + rv_no + "\\" + 
//							originalFileName);
//					oldFile.delete();
//				}
//				pw=response.getWriter();
//				pw.print("<script>" + "alert('글을 수정했습니다');" + 
//						"location.href='" + request.getContextPath() +
//						"/board/reviewPage.do?rv_no=" + rv_no + "';" + "</script>");
//						return;
//			}else if(action.equals("/removeArticle.do")) {
//				int rv_no=Integer.parseInt(request.getParameter("rv_no"));
//				List<Integer> articleNoList=boardService.removeReview(rv_no);
//				for(int no: articleNoList) {
//					File imgDir=new File(ART_IMAGE_REPO + "\\" + no);
//					if(imgDir.exists()) {
//						FileUtils.deleteDirectory(imgDir);
//					}
//				}
//				
//				pw=response.getWriter();
//				pw.print("<script>" + "alert('글을 삭제 했습니다');" +
//				"location.href='" + request.getContextPath() +
//				"/board/listReviews.do';"+"</script>");
//			} else if(action.equals("/replyForm.do")) {
//				int rv_parentNo=Integer.parseInt(request.getParameter("rv_parentNo"));
//				session=request.getSession();
//				session.setAttribute("rv_parentNo", rv_parentNo);
//				nextPage="/boardinfo/replyForm.jsp";
//			} else if(action.equals("/addReply.do")) {
//				session=request.getSession();
//				int rv_parentNo=(Integer)session.getAttribute("rv_parentNo");
//				session.removeAttribute("rv_parentNo");
//				Map<String, String> articleMap=upload(request, response);
//				String id=articleMap.get("id");
//				String rv_content=articleMap.get("rv_content");
//				String rv_imageFileName=articleMap.get("rv_imageFileName");
//				reviewVO.setRv_parentNo(rv_parentNo);
//				reviewVO.setId(id);
//				reviewVO.setRv_content(rv_content);
//				reviewVO.setRv_imageFileName(rv_imageFileName);
//				int rv_no=boardService.addReply(reviewVO);
//				if(rv_imageFileName != null && rv_imageFileName.length() != 0) {
//					File srcFile=new File(ART_IMAGE_REPO + "\\temp\\" + rv_imageFileName);
//					File destDir=new File(ART_IMAGE_REPO + "\\" + rv_no);
//					destDir.mkdir();
//					FileUtils.moveFileToDirectory(srcFile, destDir,true);
//				}
//				pw=response.getWriter();
//				pw.print("<script>" + "alert('답글을 추가했습니다');" + 
//				"location.href='" + request.getContextPath() +
//				"/board/reviewPages.do?rv_no="+rv_no+"';" + "</script>");
//			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}catch (Exception e) {
			System.out.println("요청 처리 중 에러" + e.getMessage());
		}
	}//doHandle 메서드 끝
	
	//이미지 파일 업로드 + 새 글 관련 정보 저장
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap=new HashMap<String, String>();
		String encoding="utf-8";
		File currentDirPath=new File(ART_IMAGE_REPO);
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List items=upload.parseRequest(request);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" 
				+ fileItem.getString(encoding));
					//파일 업로드로 같이 전송된 새 글 관련(제목, 내용) 매개변수를 저장한 후 반환
					articleMap.put(fileItem.getFieldName(),fileItem.getString(encoding));					
				}else {
					System.out.println("파라미터이름 : " + fileItem.getFieldName());
					System.out.println("파일이름 : " + fileItem.getName());
					System.out.println("파일크기 : " + fileItem.getSize() + "bytes");
					if(fileItem.getSize() > 0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx=fileItem.getName().lastIndexOf("/");
						}
						String fileName=fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						//업로드한 이미지를 일단 temp에 저장
						File uploadFile=new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		}catch (Exception e) {
			System.out.println("파일 업로드 중 에러");
		}
		return articleMap;
	}

}
