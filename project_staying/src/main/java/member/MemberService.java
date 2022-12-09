package member;

import java.util.Random;

import phone.Sms_Ck;

public class MemberService {
	MemberDAO memberDAO;
	
	// 생성자
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	// ID 중복 확인 호출
	public boolean overlappedID(String id) {
		boolean overlappedID;
		
		overlappedID = memberDAO.overlappedID(id);
		
		return overlappedID;
	}
	
	// 랜덤 난수 생성 + 문자 발송 서비스
	public String sendRandomMessage(String tel) {
		// 문자 발송을 위한 Sms_Ck 객체 생성
		Sms_Ck message = new Sms_Ck();
		Random random = new Random();
		
		String ran_Num = "";  // 난수를 담을 변수 선언 (String)
		for (int i = 0; i < 5; i++) {  // 5자리 생성
			String ran = Integer.toString(random.nextInt(10)); // 0~9사이의 랜덤난수
			ran_Num += ran;  
		}
		System.out.println("랜덤 난수 " + ran_Num);
		
		message.send_msg(tel, ran_Num);  // 해당 번호와 난수로 메세지 전달
		
		return ran_Num;
	}
	
	// 새 계정 등록 요청
	public void addMember(MemberVO memberVO) {
		memberDAO.addMember(memberVO);
	};
	
	// 계정 삭제 요청
	public void delMember(String id) {
		memberDAO.delMember(id);
	}
	
	// 계정정보 요청
	public MemberVO infoMember(String id) {
		MemberVO memberInfo = new MemberVO();
		
		memberInfo = memberDAO.infoMember(id);
		return memberInfo;
	}
	
	// 로그인 요청
	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		// id, pwd 일치 확인
		result = memberDAO.isExisted(memberVO);
		
		return result;
	}
	
	// 해당 아이디 존재여부 확인
	public boolean idExist(String email) {
		boolean result = false;
		result = memberDAO.idExist(email);
		
		return result;
	}
	
	// id로 pwd를 찾음
	public String pwdFind(String id) {
		String pwd = memberDAO.pwdFind(id);
		return pwd;
	}
	
	public void update_pwd(MemberVO memberVO) {
		memberDAO.update_pwd(memberVO);
	}
	

}
