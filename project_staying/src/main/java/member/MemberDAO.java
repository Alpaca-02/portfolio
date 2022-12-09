package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	
	public MemberDAO() {
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
	public boolean overlappedID(String id) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			
			// 쿼리문 동일 id가 1개 일시 true, 없을시 false를 반환
			String query = "SELECT DECODE(COUNT(*),1,'true','false') AS RESULT FROM member2tbl WHERE ID = ?"; 
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			// boolean 값으로 변환해서 리턴할 값에 담음
			result=Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("ID중복 조회중 에러 : " + e.getMessage());
		}
		return result;
	}
	
	// 유저 등록 메서드
	public void addMember(MemberVO memberVO) {
		try {
			conn=dataFactory.getConnection();
			
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String phone = memberVO.getPhone();
			String name = memberVO.getName();
			
			// 쿼리문 작성
			// 임시 내용쿼리 ( 나중에 테이블 명 수정 !!!!!!!!)
			String query = "INSERT INTO Member2tbl (id, pwd, phone, name) VALUES(?,?,?,?)";
			
			System.out.println(query); // 쿼리문 확인
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, phone);
			pstmt.setString(4, name);
			
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("유저 등록중 에러 : "+e.getMessage());
		}
	}
	
	// 유저 삭제 메서드
	public void delMember(String id) {
		try {
			conn=dataFactory.getConnection();
			
			// 쿼리문 (해당 id 삭제)
			String query = "DELETE FROM Member2tbl WHERE ID=?";
			System.out.println(query);  // 쿼리문 확인
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, id);   //  ?에 받아온 id를 담음
			pstmt.executeUpdate();  // 쿼리문 실행
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("유저 삭제중 에러 : " + e.getMessage());
		}
	}
	
	// 유저 정보를 확인하는 메서드
	public MemberVO infoMember(String id) {
		MemberVO memberInfo = null;
		try {
			conn=dataFactory.getConnection();
			// 해당 id의 유저정보를 확인
			String query = "SELECT * FROM member2tbl WHERE ID=?";
			System.out.println(query); // 쿼리문 확인
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			String _id = rs.getString("id");
			String _pwd = rs.getString("pwd");
			String _phone = rs.getString("phone");
			String _name = rs.getString("name");
			
			memberInfo = new MemberVO(_id, _pwd, _phone, _name);
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("유저 정보 확인중 에러 : " + e.getMessage());
		}
		return memberInfo;   // memberInfo VO를 반환
	}
	
	// 계정 존재 여부 확인 메서드
	public boolean isExisted(MemberVO memberVO) {
		boolean result = false; 
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT COUNT(*) FROM member2tbl WHERE 1=1 AND id = ? AND pwd = ?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int acc_exist = rs.getInt("count(*)");  // 해당 계정이 존재할 경우 1을 반환
			if(acc_exist == 1) {
				result = true;
			}	
			
			pstmt.close();
			conn.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("로그인 중 에러 : " + e.getMessage());
		}
		return result;
	}
	// 해당 계정의 존재여부를 확인하는 메서드
	public boolean idExist(String id) {
		boolean result = false; 
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT COUNT(*) FROM member2tbl WHERE 1=1 AND id = ?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			
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
			System.out.println("로그인 요청 중 에러 : " + e.getMessage());
		}
		return result;
	}
	// 비밀번호 찾는 메서드
	public String pwdFind(String id) {
		String pwd = null;
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT pwd FROM member2tbl WHERE id = ?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			pwd = rs.getString("pwd");
			
			
			pstmt.close();
			conn.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("PWD 확인 중 에러 : " + e.getMessage());
		}
		return pwd;
	}
	
	public void update_pwd(MemberVO memberVO) {
		try {
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			
			conn=dataFactory.getConnection();
			String query = "update member2tbl set pwd=? where id = ?";
			System.out.println(query); // 쿼리문 확인
			pstmt=conn.prepareStatement(query);
			
			// 쿼리문의 ?에 위에서 받은 값을 담음
			
			// where 조건에 담을 id값
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();  // 업데이트문을 수행해줌
			pstmt.close();
			conn.close();
			// 수행만 해주는 경우에는 rs 필요 X
		}catch(Exception e) {
			System.out.println("비밀번호 변경중 에러"+e.getMessage());
		}
	}
	

	
	

	
}
