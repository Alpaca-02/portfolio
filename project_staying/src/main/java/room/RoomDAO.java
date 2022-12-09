package room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public RoomDAO() {
		
		// DB연동
		try {
			Context ctx = new InitialContext();
			
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			
			//오라클 연동
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결중 에러발생 : " + e.getMessage());
		}
		
	}
	
	// 방 등록번호를 생성하는 메서드
	private int getRoomNo() {
		try {
			conn = dataFactory.getConnection();
			
			String query = "select max(room_no) from roomtbl";
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
			System.out.println("방 등록번호 생성중 에러 : " + e.getMessage());
		}
		return 0;
	}
	
	// 해당 숙소의 최저 가격을 가져오는 메서드
	private int getRoomMinPrice(String host_id) {
		int minPrice = Integer.MAX_VALUE;
		try {
			conn = dataFactory.getConnection();
			
			String query = "select host_ro_lowprice from hosttbl where host_id=?";
			System.out.println(query);
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, host_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				minPrice = rs.getInt("host_ro_lowprice");
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("최저가격 가져오는 중 에러 : " + e.getMessage());
		}
		return minPrice;
	}
	
	// 방 등록 메서드
	public int addRoom(RoomVO roomVO) {
			String host_id = roomVO.getHost_id();
			int room_no = getRoomNo();  // 방번호를 생성하는 메서드
			int min_price = getRoomMinPrice(host_id);
			System.out.println("최저가격 : " + min_price);
			int room_price = roomVO.getRoom_price();
		
		try {
			conn = dataFactory.getConnection();
			

			String room_name = roomVO.getRoom_name(); // 방 이름
			String room_info = roomVO.getRoom_info(); // 방 편의시설
			
			int room_people = roomVO.getRoom_people();  // 가용인원
			String room_img = roomVO.getRoom_img();    // 방 이미지 파일명
			
			String query = "INSERT INTO roomtbl(host_id, room_no, room_name, room_info, room_price, "
					+ "room_people, room_img) VALUES(?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, host_id);
			pstmt.setInt(2, room_no);   // 등록할 방 번호 (개별 메서드에서 생성)
			pstmt.setString(3, room_name);
			pstmt.setString(4, room_info);
			pstmt.setInt(5, room_price);
			pstmt.setInt(6, room_people);
			pstmt.setString(7, room_img);
			
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.out.println("방 등록중 에러 : " + e.getMessage());
		}
		
		// 등록하려는 방의 가격이 현재 최저가격보다 저렴할때
		// 해당 업소의 최저가격을 갱신
		if(min_price>room_price) {
			try {
				conn = dataFactory.getConnection();
				
				String query = "update hosttbl set host_ro_lowprice=? where host_id=?";
				
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1, room_price);
				pstmt.setString(2, host_id);
				
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				System.out.println("최저가격 등록중 에러 : " + e.getMessage());
			}

		}
		return room_no;  // 생성한 방 번호를 리턴함
		
	}
	
	// 방 삭제 메서드
	public RoomVO delRoom(int room_no) {
		RoomVO roomVO = new RoomVO();  // 리턴할 RoomVO
		// 방에서 지울 이미지 이름을 가져옴
		try {
			conn =dataFactory.getConnection();
			
			String query="SELECT * FROM roomtbl WHERE room_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,room_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			roomVO.setRoom_img(rs.getString("room_img"));  // 방 이미지 이름을 vo에 담음
			roomVO.setRoom_name(rs.getString("room_name"));
			roomVO.setRoom_no(room_no);
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("방 삭제전 정보 조회중 에러");
		}
		
		// 방을 지움
		try {
			conn=dataFactory.getConnection();
			
			String query="DELETE FROM roomtbl WHERE room_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.out.println("방 삭제중 에러 : " + e.getMessage());
		}
		return roomVO;
		
	}
	
	public RoomVO room_no_info(int room_no) {
		RoomVO roomVO = new RoomVO();
		try {
			conn = dataFactory.getConnection();
			
			String query="SELECT * FROM roomtbl WHERE room_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			roomVO.setRoom_no(room_no);
			roomVO.setRoom_name(rs.getString("room_name"));
			roomVO.setRoom_img(rs.getString("room_img"));
			roomVO.setRoom_price(rs.getInt("room_price"));
			
			pstmt.close();
			conn.close();
			rs.close();
			
			
		}catch(Exception e){
			System.out.println("해당 방번호 조회중 에러 : " + e.getMessage());
		}
		return roomVO;
	}
	
	
	// 해당 host_id의 방 목록 조회
	public List<RoomVO> hostRoomList(String host_id){
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT * FROM roomtbl"
					+ " WHERE host_id=?"
					+ " ORDER BY room_price ASC";  // 낮은 가격부터 정렬
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, host_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String room_name = rs.getString("room_name"); // 방 이름
				String room_info = rs.getString("room_info"); // 방 설명
				int room_price = rs.getInt("room_price");     // 1박 가격
				int room_people = rs.getInt("room_people");   // 수용 인원	
				String room_img = rs.getString("room_img");   // 이미지파일명
				int room_no = rs.getInt("room_no");           // 방 번호
				
				RoomVO roomVO = new RoomVO();
				
				roomVO.setHost_id(host_id);
				
				roomVO.setRoom_name(room_name);  
				roomVO.setRoom_info(room_info);  
				roomVO.setRoom_price(room_price); 
				roomVO.setRoom_people(room_people); 
				roomVO.setRoom_img(room_img);      
				roomVO.setRoom_no(room_no);       
				
				roomList.add(roomVO);   // 리스트에 해당 roomVO를 담음
			}
			rs.close();
			conn.close();
			pstmt.close();
	
		}catch (Exception e) {
			System.out.println("사업자 방목록 조회중 에러 : "+ e.getMessage());
		}
		return roomList;
	}
	
}
