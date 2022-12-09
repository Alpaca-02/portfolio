package host;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.MemberVO;

public class HostDAO {
	
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public HostDAO() {
		try {
			Context ctx = new InitialContext();
			
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			
			//오라클 연동
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB연결중 에러발생 : "+e.getMessage());
		}
		
	}
	
	
	// ID중복 확인 메서드
		public boolean overlappedID(String host_id) {
			boolean result = false;
			try {
				conn = dataFactory.getConnection();
				
				// 쿼리문 동일 id가 1개 일시 true, 없을시 false를 반환
				String query = "SELECT DECODE(COUNT(*),1,'true','false') AS RESULT FROM hosttbl WHERE host_id = ?"; 
				
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, host_id);
				
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
				// boolean 값으로 변환해서 리턴할 값에 담음
				result=Boolean.parseBoolean(rs.getString("result"));
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				System.out.println("사업자 ID중복 조회중 에러 : " + e.getMessage());
			}
			return result;
		}
	// 사업자 회원 추가 메서드
	public void addHost(HostVO hostVO) {
		try {
			conn=dataFactory.getConnection();
			
			String host_id = hostVO.getHost_id();
			String host_pwd = hostVO.getHost_pwd();
			String host_phone = hostVO.getHost_phone();
			String host_name = hostVO.getHost_name();
			
			
			String host_ro_name = hostVO.getHost_ro_name();
			String host_ro_addr = hostVO.getHost_ro_addr();
			String host_ro_kind = hostVO.getHost_ro_kind();
			String host_ro_latitude = hostVO.getHost_ro_latitude();
			String host_ro_longitude = hostVO.getHost_ro_longitude();
			String host_ro_content = hostVO.getHost_ro_content();
			String host_ro_img = hostVO.getHost_ro_img();
			
			String query = "INSERT INTO hosttbl(host_id, host_pwd, host_phone, host_name,"
					+ " host_ro_name, host_ro_addr, host_ro_kind, host_ro_latitude,"
					+ " host_ro_longitude,host_ro_content, host_ro_img, host_ro_rate)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,0)";
			System.out.println(query);  // 쿼리문 확인
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, host_id);
			pstmt.setString(2, host_pwd);
			pstmt.setString(3, host_phone);
			pstmt.setString(4, host_name);
			
			pstmt.setString(5, host_ro_name);
			pstmt.setString(6, host_ro_addr);
			pstmt.setString(7, host_ro_kind);
			pstmt.setString(8, host_ro_latitude);
			pstmt.setString(9, host_ro_longitude);
			pstmt.setString(10, host_ro_content);
			pstmt.setString(11, host_ro_img);
			
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("사업차 회원 추가중 에러 : " + e.getMessage());
		}
	}
	
	// 사업자 사업장 정보 추가 메서드
	public void addHostInfo(HostVO hostVO) {
		try {
			conn=dataFactory.getConnection();
			
			String host_ro_name = hostVO.getHost_ro_name();
			String host_ro_addr = hostVO.getHost_ro_addr();
			String host_ro_kind = hostVO.getHost_ro_kind();
			String host_ro_latitude = hostVO.getHost_ro_latitude();
			String host_ro_longitude = hostVO.getHost_ro_longitude();
			String host_ro_content = hostVO.getHost_ro_content();
			String host_ro_img = hostVO.getHost_ro_img();
			
			// 쿼리문 작성 (별점 default값은 0으로 한다.)
			String query = "INSERT INTO hosttbl"
					+ "(host_ro_name, host_ro_addr, host_ro_kind, host_ro_latitude, host_ro_longitude, "
					+ "host_ro_content, host_ro_img, host_ro_rate) "
					+ "VALUES (?,?,?,?,?,?,?,0)";
			System.out.println(query);  // 쿼리문 확인
			
			pstmt.setString(1, host_ro_name);
			pstmt.setString(2, host_ro_addr);
			pstmt.setString(3, host_ro_kind);
			pstmt.setString(4, host_ro_latitude);
			pstmt.setString(5, host_ro_longitude);
			pstmt.setString(6, host_ro_content);
			pstmt.setString(7, host_ro_img);
			
			pstmt.executeUpdate();  // 쿼리문 실행
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.out.println("사업장 정보 추가중 에러 : "+ e.getMessage());
		}
	}
	
	
	
	// 사업자 계정 삭제 메서드
	public void delHost(String id) {
		try {
			conn=dataFactory.getConnection();
			
			String query = "DELETE FROM hosttbl WHERE ID=?";
			System.out.println(query);  // 쿼리문 확인
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.out.println("사업자 회원 삭제중 에러 : " + e.getMessage());
		}
	}
	
	// 사업자 정보를 확인하는 메서드
		public HostVO infoHost(String host_id) {
			HostVO hostInfo = null;
			try {
				conn=dataFactory.getConnection();
				
				String query = "SELECT * FROM hosttbl WHERE host_id=?";
				System.out.println(query); // 쿼리문 확인
				
				pstmt=conn.prepareStatement(query);
				
				pstmt.setString(1, host_id);
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				
				String _id = rs.getString("host_id");
				String _pwd = rs.getString("host_pwd");
				String _phone = rs.getString("host_phone");
				String _name = rs.getString("host_name");
				
				
				hostInfo = new HostVO(_id, _pwd, _phone, _name);
				
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				System.out.println("유저 정보 확인중 에러 : " + e.getMessage());
			}
			return hostInfo;   // memberInfo VO를 반환
		}
		
		
		// 사업자id로 사업장의 공개 정보를 확인하는 메서드
		public HostVO info_ro_host(String host_id) {
			HostVO hostInfo = null;
			try {
				conn=dataFactory.getConnection();
				
				String query = "SELECT * FROM hosttbl WHERE host_id=?";
				System.out.println(query); // 쿼리문 확인
				
				pstmt=conn.prepareStatement(query);
				
				pstmt.setString(1, host_id);  // 해당 id의 호스트 정보를 가져옴
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();

				String host_ro_name = rs.getString("host_ro_name");
				String host_ro_addr = rs.getString("host_ro_addr");
				String host_ro_kind = rs.getString("host_ro_kind");
				String host_ro_content = rs.getString("host_ro_content");
				String host_ro_latitude = rs.getString("host_ro_latitude");
				String host_ro_longitude = rs.getString("host_ro_longitude");
				String host_ro_img = rs.getString("host_ro_img");
				int host_ro_rate = rs.getInt("host_ro_rate");
				
				hostInfo = new HostVO();  // HostVO 생성 후 정보를 담음
				
				hostInfo.setHost_id(host_id);
				hostInfo.setHost_ro_name(host_ro_name);
				hostInfo.setHost_ro_addr(host_ro_addr);
				hostInfo.setHost_ro_kind(host_ro_kind);
				hostInfo.setHost_ro_content(host_ro_content);
				hostInfo.setHost_ro_latitude(host_ro_latitude);
				hostInfo.setHost_ro_longitude(host_ro_longitude);
				hostInfo.setHost_ro_img(host_ro_img);
				hostInfo.setHost_ro_rate(host_ro_rate);
				
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				System.out.println("업체 정보 확인중 에러 : " + e.getMessage());
			}
			return hostInfo;   // hostInfo VO를 반환
		}
		
		// 조건으로 사업장을 검색하는 메서드 !!!!!!!!!!!
		public List<HostVO> searchHost(int ckiDate,int ckoDate, String room_kind, String lowPrice, String highPrice, int room_people, String rate){
			List<HostVO> searcherdHost = new ArrayList<HostVO>() ;  // 리턴할 리스트
			System.out.println(lowPrice);
			System.out.println(ckiDate);
			System.out.println(ckoDate);
			System.out.println(room_kind);
			try {
				conn=dataFactory.getConnection();
				
				String query = "SELECT * FROM hosttbl WHERE "
						+ "host_id IN(SELECT DISTINCT host_id FROM roomtbl "
						+ "WHERE room_no NOT IN"
						+ "(SELECT DISTINCT room_no FROM reservetbl WHERE reserve_date BETWEEN ? and ?)"  // 지정한 체크인 체크아웃 일자 사이 예약이 없는경우
						+ " AND room_people >= ?";
						
				if(lowPrice != null && !(lowPrice.equals(""))) { // 가격대 지정이 있는경우
					query += " AND room_price BETWEEN "+lowPrice+" AND "+highPrice;     // 가격대를 지정 
				}
				query += ")";   // 중간 괄호
				if(rate != null && !(rate.equals(""))){  // 별점 조건이 있는경우
					query += " AND host_ro_rate > "+rate; 
				}
				query += " AND host_ro_kind = ?  ORDER BY host_ro_lowprice ASC"; // 숙박유형 추가 및 가격순 정렬
				
				System.out.println(query);  // 쿼리문 확인
				
				pstmt=conn.prepareStatement(query);
				System.out.println(query);
				
				System.out.println(ckiDate);
				pstmt.setInt(1, ckiDate);
				pstmt.setInt(2, ckoDate);
				pstmt.setInt(3, room_people);
				pstmt.setString(4, room_kind);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String host_id = rs.getString("host_id");
					String host_ro_name = rs.getString("host_ro_name");
					String host_ro_addr = rs.getString("host_ro_addr");
					String host_ro_kind = rs.getString("host_ro_kind");
					
					
					String content_before = rs.getString("host_ro_content");
					String host_ro_content;
					if((content_before.length()) > 44) {
						host_ro_content = content_before.substring(0,45) + "...";
					}else {
						host_ro_content = content_before;
					}
					
					
					
					String host_ro_latitude = rs.getString("host_ro_latitude");
					String host_ro_longitude = rs.getString("host_ro_longitude");
					int host_ro_lowprice = rs.getInt("host_ro_lowprice");
					String host_ro_img = rs.getString("host_ro_img");
					int host_ro_rate = rs.getInt("host_ro_rate");
					
					HostVO hostVO = new HostVO();
					
					hostVO.setHost_id(host_id);
					hostVO.setHost_ro_name(host_ro_name);
					hostVO.setHost_ro_addr(host_ro_addr);
					hostVO.setHost_ro_kind(host_ro_kind);
					hostVO.setHost_ro_content(host_ro_content);
					hostVO.setHost_ro_latitude(host_ro_latitude);
					hostVO.setHost_ro_longitude(host_ro_longitude);
					hostVO.setHost_ro_lowprice(host_ro_lowprice);
					hostVO.setHost_ro_img(host_ro_img);
					hostVO.setHost_ro_rate(host_ro_rate);
					
					searcherdHost.add(hostVO);
				}
				rs.close();
				conn.close();
				pstmt.close();

			}catch (Exception e) {
				System.out.println("방 목록 검색중 에러 : " + e.getMessage());
			}
			return searcherdHost;
		}
		// 별점순으로 상위 숙박업소 5개를 가져오는 메서드
		public List<HostVO> bestRate(String host_ro_kind){
			List<HostVO> bestList = new ArrayList<HostVO>() ;  // 리턴할 리스트
			try {
				conn = dataFactory.getConnection();
				
				String query = "SELECT * FROM"
						+ " (SELECT * FROM hosttbl WHERE host_ro_kind=? ORDER BY host_ro_rate DESC)"
						+ " WHERE ROWNUM <= 5"; // 별점순으로 정렬 후 상위 5개만 추출
				
				pstmt=conn.prepareStatement(query);
				System.out.println(query); // 쿼리문 확인
				
				pstmt.setString(1, host_ro_kind);  // 받아온 숙소 유형을 지정
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String host_id = rs.getString("host_id");
					String host_ro_name = rs.getString("host_ro_name");
					String host_ro_addr = rs.getString("host_ro_addr");
					int host_ro_lowprice = rs.getInt("host_ro_lowprice");
					String host_ro_img = rs.getString("host_ro_img");
					int host_ro_rate = rs.getInt("host_ro_rate");
					
					HostVO hostVO = new HostVO();
					
					hostVO.setHost_id(host_id);
					hostVO.setHost_ro_name(host_ro_name);
					hostVO.setHost_ro_addr(host_ro_addr);
					hostVO.setHost_ro_lowprice(host_ro_lowprice);
					hostVO.setHost_ro_img(host_ro_img);
					hostVO.setHost_ro_rate(host_ro_rate);
					
					bestList.add(hostVO);
				}
				rs.close();
				conn.close();
				pstmt.close();
	
			}catch (Exception e) {
				System.out.println("상위 숙박목록 불러오는중 에러 : "+ e.getMessage());
			}
			return bestList;
		}
		
		
		// 사업자 계정 존재 여부 확인 메서드
		public boolean isExisted(HostVO hostVO) {
			boolean result = false; 
			String host_id = hostVO.getHost_id();
			String host_pwd = hostVO.getHost_pwd();
			try {
				conn = dataFactory.getConnection();
				
				String query = "SELECT COUNT(*) FROM hosttbl WHERE 1=1 AND host_id = ? AND host_pwd = ?";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, host_id);
				pstmt.setString(2, host_pwd);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				int acc_exist = rs.getInt("count(*)");
				if(acc_exist == 1) {
					result = true;
				}
				
				pstmt.close();
				conn.close();
				rs.close();
			}catch (Exception e) {
				System.out.println("사업자 로그인 중 에러 : " + e.getMessage());
			}
			return result;
			

		}
}
