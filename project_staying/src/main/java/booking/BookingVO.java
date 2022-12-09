package booking;

import java.util.Date;

public class BookingVO {
	// 필드
	private int book_no;
	
	private String id;
	private String host_id;
	private String host_ro_name;
	


	private int room_no;
	private String room_name;
	
	private int check_in;
	private int check_out;
	private int book_price;
	
	private String check_in_str;
	private String check_out_str;
	
	private String book_state;  // 예약 상태 (booked, checkin, checkout) 
	private Date book_date;  // 예약한 일자
	private String host_ro_img;
	
	public String getHost_ro_name() {
		return host_ro_name;
	}
	
	
	public void setHost_ro_name(String host_ro_name) {
		this.host_ro_name = host_ro_name;
	}
	
	public String getCheck_in_str() {
		return check_in_str;
	}


	public void setCheck_in_str(String check_in_str) {
		this.check_in_str = check_in_str;
	}


	public String getCheck_out_str() {
		return check_out_str;
	}


	public void setCheck_out_str(String check_out_str) {
		this.check_out_str = check_out_str;
	}


	
	
	public int getBook_price() {
		return book_price;
	}


	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}


	public String getRoom_name() {
		return room_name;
	}


	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}


	public String getHost_ro_img() {
		return host_ro_img;
	}


	public void setHost_ro_img(String host_ro_img) {
		this.host_ro_img = host_ro_img;
	}


	// 생성자
	public BookingVO() {
		
	}


	public int getBook_no() {
		return book_no;
	}


	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getHost_id() {
		return host_id;
	}


	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}


	public int getRoom_no() {
		return room_no;
	}


	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}


	public int getCheck_in() {
		return check_in;
	}


	public void setCheck_in(int check_in) {
		this.check_in = check_in;
	}


	public int getCheck_out() {
		return check_out;
	}


	public void setCheck_out(int check_out) {
		this.check_out = check_out;
	}


	public String getBook_state() {
		return book_state;
	}


	public void setBook_state(String book_state) {
		this.book_state = book_state;
	}


	public Date getBook_date() {
		return book_date;
	}


	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}
	
	
}
