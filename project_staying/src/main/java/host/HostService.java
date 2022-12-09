package host;

import java.util.List;
import java.util.Random;

import member.MemberVO;
import phone.Sms_Ck;

public class HostService {
	
	HostDAO hostDAO;
	
	public HostService() {
		hostDAO = new HostDAO();
	}
	
	// ID 중복 확인 호출
		public boolean overlappedID(String id) {
			boolean overlappedID;
			
			overlappedID = hostDAO.overlappedID(id);
			
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
	
	
	// 사업자 계정 추가 서비스
	public void addHost(HostVO hostVO) {
		hostDAO.addHost(hostVO);
	}
	
	// 사업자 계정 삭제 서비스
	public void delHost(String id) {
		hostDAO.delHost(id);
	}
	// 계정정보 요청
	public HostVO infoHost(String host_id) {
		HostVO hostInfo = new HostVO();
		
		hostInfo = hostDAO.infoHost(host_id);
		return hostInfo;
	}
	
	// 업체 정보 요청
	public HostVO info_ro_host(String host_id) {
		HostVO hostInfo = new HostVO();
		hostInfo = hostDAO.info_ro_host(host_id);
		return hostInfo;
	}
		
		// 로그인 요청
	public boolean isExisted(HostVO hostVO) {
		boolean result = false;
		// id, pwd 일치 확인
		result = hostDAO.isExisted(hostVO);
		
		return result;
	}
	
	
	// 사업자 사업장 정보 추가
	public void addHostInfo(HostVO hostVO) {
		hostDAO.addHostInfo(hostVO);
	}
	
	// 조건 검색으로 숙소 목록을 가져오는 메서드
	public List<HostVO> searchHost(int ckiDate,int ckoDate, String room_kind, String lowPrice, String highPrice, int room_people, String rate) {
		List<HostVO> searchedHost = hostDAO.searchHost(ckiDate,ckoDate,room_kind,lowPrice,highPrice,room_people,rate);
		return searchedHost;
	}
	
	// 별점순으로 상위 5개 숙소를 가져오는 메서드
	public List<HostVO> bestRate(String host_ro_kind){
		List<HostVO> bestList = hostDAO.bestRate(host_ro_kind);
		return bestList;
	}
}
