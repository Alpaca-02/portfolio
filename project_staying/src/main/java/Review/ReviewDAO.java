package Review;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ReviewDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public ReviewDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
	}
	
	// 새 게시글 추가 메서드
		public int insertNewReview(ReviewVO review) {
			// return 하기위해 articleNo를 먼저 받음
			int rv_no = getNewReviewNo();  // 새글을 추가하기 전에 사용할 글 번호를 받아옴
			
			String host_id = review.getHost_id();
			try {
				conn=dataFactory.getConnection();
				//parentNo를 받아옴
				int rv_type = review.getRv_type();
				int book_no = review.getBook_no();
				int rv_parent = 0;
				
				String rv_content = review.getRv_content();
				String rv_img = review.getRv_img();
				int rv_rate = review.getRv_rate();
				
				
				String id = review.getId();
				
				
				// 쿼리문 (데이터 삽입)
				String query = "insert into reviewTBL(rv_no, rv_parent, host_id, book_no, rv_type, id, rv_content, rv_img, rv_rate) "
						+ "values(?,?,?,?,?,?,?,?,?)";
				System.out.println(query); // 쿼리문 확인
				pstmt=conn.prepareStatement(query);
				
				// 쿼리문의 ?에 위에서 담아온 내용을 담음
				pstmt.setInt(1, rv_no);
				pstmt.setInt(2, rv_parent);
				pstmt.setString(3, host_id);
				pstmt.setInt(4, book_no);
				pstmt.setInt(5, rv_type);
				pstmt.setString(6, id);
				pstmt.setString(7, rv_content);
				pstmt.setString(8, rv_img);
				pstmt.setInt(9, rv_rate);
				
				pstmt.executeUpdate(); // 쿼리문 실행
				
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("새 글 추가중 에러");
			}
			
			int count = getReviewCount(host_id);  // 해당 업체의 리뷰 수를 가져온다.
			int sum = getSumRate(host_id);  // 해당 업체의 리뷰별점 총 합을 구한다
			int avgRate = sum/count;
			
			updateRate(host_id, avgRate);   // 해당 업체의 별점 평균을 업체 테이블에 저장
			return rv_no;
		}
		
		public void insertReply(ReviewVO review) {
			int rv_no = getNewReviewNo();  // 새글을 추가하기 전에 사용할 글 번호를 받아옴
			try {
				conn=dataFactory.getConnection();
				//parentNo를 받아옴
				int rv_type = review.getRv_no();
				
				int rv_parent = review.getRv_parent();
				String host_id = review.getHost_id();
				int book_no = review.getBook_no();
				
				String id = review.getId();
				String rv_content = review.getRv_content();
				String rv_img = review.getRv_img();
				
				// 쿼리문 (데이터 삽입)
				String query = "insert into reviewTBL(rv_no, rv_parent, host_id, book_no, rv_type, id, rv_content, rv_img, rv_rate) "
						+ "values(?,?,?,?,?,?,?,?,?)";
				System.out.println(query); // 쿼리문 확인
				
				pstmt=conn.prepareStatement(query);
				
				// 쿼리문의 ?에 위에서 담아온 내용을 담음
				pstmt.setInt(1, rv_no);
				pstmt.setInt(2, rv_parent);
				pstmt.setString(3, host_id);
				pstmt.setInt(4, book_no);
				pstmt.setInt(5, 1);
				pstmt.setString(6, id);
				pstmt.setString(7, rv_content);
				pstmt.setString(8, rv_img);
				pstmt.setInt(9, 0);  // 
				
				pstmt.executeUpdate(); // 쿼리문 실행
				
				pstmt.close();
				conn.close();
				
				
				
			}catch (Exception e) {
				System.out.println("답글 추가중 에러 : "+e.getMessage());
			}
		}
		
		// articleNo(글번호) 생성 메서드
		private int getNewReviewNo() {
			try {
				// db에 연결
				conn=dataFactory.getConnection();
				// articleNo가 가장 큰 값을 가져오는 쿼리문
				String query="select max(rv_no) from reviewtbl";
				System.out.println(query);
				
				pstmt=conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery(query);
				
				if(rs.next()) {
					// 가장 큰 값에 1을 더해서 저장
					return(rs.getInt(1)+1); // 쿼리문에서 가져온 첫번째 값을 가져온다.
				}
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				System.out.println("글번호 생성중 에러 : " + e.getMessage());
			}
			return 0;  // 에러로 못 넘겨줄 경우 0을 반환
		}
		
		// 해당 host의 리뷰 수 별점의 총합을 가져오는 메서드
		private int getSumRate(String host_id) {
			int sum = 0;
			try {
				conn=dataFactory.getConnection();
				// articleNo가 가장 큰 값을 가져오는 쿼리문
				String query="SELECT rv_rate FROM reviewTBL WHERE host_id=? AND rv_rate > 1";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, host_id);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					sum +=rs.getInt("rv_rate"); // 글의 별점 합을 더함
				}
				
				conn.close();
				pstmt.close();
				rs.close();
			}catch (Exception e) {
				System.out.println("리뷰 카운트 를 가져오는 중 에러 : "+e.getMessage());
			}
			return sum;
		}
		
		// 해당 host의 리뷰 수를 가져오는 메서드
		private int getReviewCount(String host_id) {
			int count = 0;
			try {
				conn=dataFactory.getConnection();
				// articleNo가 가장 큰 값을 가져오는 쿼리문
				String query="SELECT count(*) as cnt FROM reviewTBL WHERE host_id=? AND rv_rate > 1";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, host_id);
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				count = rs.getInt("CNT"); // 글 수를 가져옴
				
				conn.close();
				pstmt.close();
				rs.close();
				
			}catch (Exception e) {
				System.out.println("리뷰 수 를 가져오는 중 에러 : " + e.getMessage());
			}
			return count;
		}
		
		// host의 평점을 등록하는 메서드
		private void updateRate(String host_id,int rate) {
			try {
				conn=dataFactory.getConnection();
				// 해당 사업자의 별점 정보를 업데이트
				String query = "update hosttbl set host_ro_rate=? WHERE host_id=?"; 
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1,rate);
				pstmt.setString(2, host_id);
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
				
			}catch (Exception e) {
				System.out.println("별점 업데이트중 에러 : "+e.getMessage());
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 해당 host의 리뷰 목록을 가져오는 메서드
		public List<ReviewVO> review_list_host(String host_id) {
			// 리스트 객체생성
			List<ReviewVO> reviewList = new ArrayList();
			
			try {
				// DB에 연결
				conn=dataFactory.getConnection();
				// 쿼리문 작성  (계층형 SQL문)
				String query="select *"+
						" from reviewtbl WHERE host_id=? START WITH rv_type=0 CONNECT BY PRIOR"+
						" rv_no=rv_parent ORDER SIBLINGS BY rv_no DESC";
					// articleNo와 parentNo가 같은것을 article번호대로 정렬  (리뷰글 뒤에 답글이 들어감)
				System.out.println(query);  // 쿼리문 확인
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, host_id);
				ResultSet rs=pstmt.executeQuery();
				// 데이터 목록의 첫번째 부터 가져옴
				while(rs.next()){
					int rv_no =rs.getInt("rv_no"); 
					int rv_parent =rs.getInt("rv_parent"); 
					
					int book_no =rs.getInt("book_no");
					int rv_type =rs.getInt("rv_type");
					
					String id = rs.getString("id");
					String rv_content = rs.getString("rv_content");
					String rv_img = rs.getString("rv_img");
					Date rv_writeDate = rs.getDate("rv_writeDate");
					int rv_rate = rs.getInt("rv_rate");
					
					ReviewVO review = new ReviewVO(); // 리뷰 정로를 
					
					review.setRv_no(rv_no);
					review.setRv_parent(rv_parent);
					review.setHost_id(host_id);
					review.setBook_no(book_no);
					review.setRv_type(rv_type);
					review.setId(id);
					review.setRv_content(rv_content);
					review.setRv_img(rv_img);
					review.setRv_writeDate(rv_writeDate);

					
					// 위에서 생성한 ArticleVO 객체를 리스트에 담음
					reviewList.add(review);
				}
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				System.out.println("글 목록 조회중 에러 : "+e.getMessage());
			}
			return reviewList;
		}
}
