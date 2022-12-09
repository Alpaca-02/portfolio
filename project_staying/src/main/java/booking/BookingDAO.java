package booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import room.RoomVO;

public class BookingDAO {
	
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public BookingDAO() {
		try {
			Context ctx = new InitialContext();
			
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			
			//오라클 연동
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB연결중 에러발생 : "+e.getMessage());
		}
		
	}
	
	// 호스트 아이디를 가져오는 메서드
	public String[] get_host_id(int room_no) {
		String host_id = null;
		String room_price = null;
		String[] result = new String[3];
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT host_id, room_price FROM roomtbl WHERE room_no=?";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, room_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			host_id = rs.getString("host_id");
			room_price =  Integer.toString(rs.getInt("room_price")) ;
			result[0] = host_id;
			result[1] = room_price;
			
		}catch(Exception e) {
			System.out.println("호스트 아이디 조회중 에러 : " + e.getMessage());
		}
		return result;
	}
	
	//  예약번호 생성 메서드 메서드
	private int getBookNo() {
		try {
			conn = dataFactory.getConnection();
			
			String query = "select max(book_no) from bookingtbl";
			System.out.println(query);
			
			pstmt=conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return(rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("예약번호 생성 중 에러 : " + e.getMessage());
		}
		return 0;
	}
	
	// 예약을 등록하는 메서드
	public void add_book(int cki_date, int cko_date, int room_price,int room_no, String host_id, String id, String room_name, String host_ro_img, String host_ro_name) {
		int days = 0; // 숙박일자
		int book_no = getBookNo();  // 예약번호 생성
		try {
			conn= dataFactory.getConnection();
			
			String query = "INSERT INTO reservetbl(room_no, reserve_date) VALUES(?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			
			// 숙박 날짜 만큼 반복 (i 값에 해당 일자를 올려서 DB에 올림);
			for (int i = cki_date; i <= cko_date; i++) {
				days++;
				pstmt.setInt(2, i);
				pstmt.executeUpdate();
			}
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("예약날짜 테이블 등록중 에러 : " + e.getMessage());
		}
		
		try {
			conn= dataFactory.getConnection();
			
			String query = "INSERT INTO bookingtbl"
					+ "(book_no, id, host_id, room_no, check_in, check_out, book_price, room_name, host_ro_img, host_ro_name, book_state)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?, 'before')";
			
			pstmt=conn.prepareStatement(query);
			
			System.out.println(query);
			
			pstmt.setInt(1, book_no);
			pstmt.setString(2, id);
			pstmt.setString(3, host_id);
			pstmt.setInt(4, room_no);
			pstmt.setInt(5, cki_date);
			pstmt.setInt(6, cko_date);
			pstmt.setInt(7, (room_price*(days-1)));  // 1박 가격 * 숙박일을 총 결제금액에 담음
			
			pstmt.setString(8, room_name);
			pstmt.setString(9, host_ro_img);
			pstmt.setString(10, host_ro_name);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("예약등록중 에러 : " + e.getMessage());
		}
		
	}
	
	// 해당 유저의 예약 목록을 가져오는 메서드
	public List<BookingVO> get_member_book(String id){
		List<BookingVO> book_list = new ArrayList<BookingVO>();
		try {
			conn = dataFactory.getConnection();
			
			System.out.println("dao id : " + id);
			String query = "SELECT * FROM bookingtbl WHERE id = ? ORDER BY check_in ";
			
			pstmt=conn.prepareStatement(query);
			System.out.println(query);
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int book_no = rs.getInt("book_no");
				String host_id = rs.getString("host_id");
				int room_no = rs.getInt("room_no");
				String room_name = rs.getString("room_name");
				int check_in = rs.getInt("check_in");
				int check_out = rs.getInt("check_out");
				int book_price = rs.getInt("book_price");
				Date book_date = rs.getDate("book_date");
				String host_ro_img = rs.getString("host_ro_img");
				String host_ro_name = rs.getString("host_ro_name");
				String book_state = rs.getString("book_state");
				
				BookingVO bookingVO = new BookingVO();
				
				bookingVO.setBook_no(book_no);
				bookingVO.setHost_id(host_id);
				bookingVO.setRoom_name(room_name);
				bookingVO.setRoom_no(room_no);
				bookingVO.setCheck_in(check_in);
				bookingVO.setCheck_out(check_out);
				bookingVO.setBook_state(book_state);
				
				bookingVO.setCheck_in_str(date_to_kr(check_in));
				bookingVO.setCheck_out_str(date_to_kr(check_out));
				
				bookingVO.setBook_price(book_price);
				bookingVO.setBook_date(book_date);
				bookingVO.setHost_ro_img(host_ro_img);
				bookingVO.setHost_ro_name(host_ro_name);
				
				book_list.add(bookingVO);
			}
			rs.close();
			conn.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("유저 예약목록 가져오는 중 에러 : " + e.getMessage());
		}
		return book_list;
		
	}
	
	// 예약 삭제 메서드
	public void del_book(int book_no) {
		try {
			conn=dataFactory.getConnection();
			
			// 쿼리문 (해당 예약번호의 예약을 삭제)
			String query = "DELETE FROM bookingtbl WHERE book_no=?";
			System.out.println(query);  // 쿼리문 확인
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, book_no);   //  ?에 받아온 id를 담음
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("유저 삭제중 에러 : " + e.getMessage());
		}
	}
	
	// reserve 테이블 삭제 메서드
	public void del_reserve(int room_no, int check_in, int check_out) {
		try {
			conn=dataFactory.getConnection();
			
			// 쿼리문 (해당 기간내의 해당 방 예약을 삭제)
			String query = "DELETE FROM reservetbl WHERE room_no=? AND reserve_date BETWEEN ? and ?";
			System.out.println(query);  // 쿼리문 확인
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, room_no);   //  ?에 받아온 id를 담음
			pstmt.setInt(2, check_in);
			pstmt.setInt(3, check_out);
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("유저 삭제중 에러 : " + e.getMessage());
		}
	}
	
	// 리뷰완료 여부를 저장하는 메서드
	public void set_after(int book_no) {
		try {
			
			conn=dataFactory.getConnection();
			String query = "update bookingtbl set book_state='after' where book_no = ?";
			System.out.println(query); // 쿼리문 확인
			pstmt=conn.prepareStatement(query);
			
			// 쿼리문의 ?에 위에서 받은 값을 담음
			
			// where 조건에 담을 book_no값
			pstmt.setInt(1, book_no);
			
			pstmt.executeUpdate();  // 업데이트문을 수행해줌
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("리뷰 여부 저장중 에러 : "+e.getMessage());
		}
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
