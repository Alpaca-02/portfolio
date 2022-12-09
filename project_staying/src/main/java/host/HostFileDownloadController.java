package host;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hostDownload.do")
public class HostFileDownloadController extends HttpServlet {
	private static String ART_IMAGE_REPO = "C:\\staying\\host_image";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String host_id = request.getParameter("host_id");
		String imageFileName = request.getParameter("imageFileName");
		
		// 클라이언트 파일에 output
		OutputStream out = response.getOutputStream();
		// 폴더경로\게시글번호 폴더이름\이미지파일 으로 경로 저장
		String path = ART_IMAGE_REPO + "\\" +  host_id + "\\" + imageFileName;
		System.out.println(path);
		File imageFile = new File(path);
		response.setHeader("cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
		
		FileInputStream fis = new FileInputStream(imageFile);
		// 이미지를 임시 버퍼에 담음
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = fis.read(buffer);
			if(count == -1)break;
			
			out.write(buffer,0,count);
		}
		fis.close();
		out.close();
	}
}
