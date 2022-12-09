package Review;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ReviewVO {
	private int rv_no;
	private String host_id;
	private int rv_parent;


	private int book_no;
	private int rv_type;  // 게시글인지 답글인지를 지정 게시글 => 0 답글=>1
	private String id;
	private String rv_content;
	private String rv_img;
	private Date rv_writeDate;
	private int rv_rate;
	
	public ReviewVO() {
		
	}

	public int getRv_parent() {
		return rv_parent;
	}
	
	public void setRv_parent(int rv_parent) {
		this.rv_parent = rv_parent;
	}
	public String getHost_id() {
		return host_id;
	}
	
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	
	public int getRv_no() {
		return rv_no;
	}

	public void setRv_no(int rv_no) {
		this.rv_no = rv_no;
	}

	public int getBook_no() {
		return book_no;
	}

	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	public int getRv_type() {
		return rv_type;
	}

	public void setRv_type(int rv_type) {
		this.rv_type = rv_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRv_content() {
		return rv_content;
	}

	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}

	public String getRv_img() {
		return rv_img;
	}

	public void setRv_img(String rv_img) {
		this.rv_img = rv_img;
	}

	public Date getRv_writeDate() {
		return rv_writeDate;
	}

	public void setRv_writeDate(Date rv_writeDate) {
		this.rv_writeDate = rv_writeDate;
	}

	public int getRv_rate() {
		return rv_rate;
	}

	public void setRv_rate(int rv_rate) {
		this.rv_rate = rv_rate;
	}
	
	
	
}