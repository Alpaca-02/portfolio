package Review;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewDownload.do")
public class ReviewFileDownloadController extends HttpServlet {
	private static String ART_IMAGE_REPO="C:\\staying\\review_image";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String rv_no=request.getParameter("rv_no");
		String rv_img=request.getParameter("rv_img");
		OutputStream out=response.getOutputStream();
		String path=ART_IMAGE_REPO+"\\" + rv_no + "\\" + rv_img;
		File imageFile=new File(path);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition","attachment;fileName=" + rv_img);
		FileInputStream fis=new FileInputStream(imageFile);
		byte[] buffer=new byte[1024*8];  //버퍼를 이용해서 한번에 8k씩 전송
		while(true) {
			int count=fis.read(buffer);
			if(count==-1) break;
			out.write(buffer,0, count);
		}
		fis.close();
		out.close();
	}

}
