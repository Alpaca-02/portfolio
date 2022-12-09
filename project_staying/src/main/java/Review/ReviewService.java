package Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewService {
	ReviewDAO reviewDAO;
	public ReviewService() {
		reviewDAO=new ReviewDAO(); //생성자에서 BoardDAO객체를 생성
	}
	

	// 리뷰글을 등록하는 메서드
	public int addReview(ReviewVO review) {
		return reviewDAO.insertNewReview(review);
	}
	
	// 사업자 답글을 등록하는 메서드
	public void addReply(ReviewVO review) {
		reviewDAO.insertReply(review);
	}
	
	
	public List<ReviewVO> review_list_host(String host_id){
		return reviewDAO.review_list_host(host_id);
	}

}
