package booking;

import java.util.List;

public class BookingService {
	BookingDAO bookingDAO;
	
	public BookingService() {
		bookingDAO = new BookingDAO();
	}
	
	// 예약 내용을 추가
	public void add_book(int cki_date, int cko_date, String id, int room_no,String room_name,String host_ro_img, String host_ro_name) {
		String host_id = bookingDAO.get_host_id(room_no)[0];
		int room_price = Integer.parseInt(bookingDAO.get_host_id(room_no)[1]);
		bookingDAO.add_book(cki_date,cko_date,room_price,room_no,host_id,id,room_name,host_ro_img, host_ro_name);
	}
	
	public List<BookingVO> get_member_book(String id){
		System.out.println(id);
		List<BookingVO> book_list = bookingDAO.get_member_book(id);
		return book_list;
	}
	
	public void del_book(int book_no, int room_no, int check_in, int check_out) {
		bookingDAO.del_book(book_no);  // booking 테이블에서 제거
		bookingDAO.del_reserve(room_no, check_in, check_out);
	}
	
	// 리뷰가 작성된 예약번호의 리뷰 여부를 저장
	public void set_after(int book_no) {
		bookingDAO.set_after(book_no);
	}
	
}
