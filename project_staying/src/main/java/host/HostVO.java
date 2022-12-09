package host;

public class HostVO {
	//필드
	private String host_id;
	private String host_pwd;
	private String host_phone;
	private String host_name;
	
	private String host_ro_name;
	private String host_ro_addr;
	private String host_ro_kind;
	private String host_ro_content;
	private int host_ro_lowprice;   // 방 목록 가격중 최저 가격

	private String host_ro_latitude;  // 숙박 GPS 좌표 위도
	private String host_ro_longitude;  // 숙박 GPS 좌표 경도
	private String host_ro_img;  // 이미지 파일명
	private int host_ro_rate;
	
	


	// 계정 생성용 
	public HostVO(String host_id, String host_pwd, String host_phone, String host_name) {
		this.host_id = host_id;
		this.host_pwd = host_pwd;
		this.host_phone = host_phone;
		this.host_name = host_name;
	}
	
	public HostVO() {
		
	}
	
	
	
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	public String getHost_pwd() {
		return host_pwd;
	}
	public void setHost_pwd(String host_pwd) {
		this.host_pwd = host_pwd;
	}
	public String getHost_phone() {
		return host_phone;
	}
	public void setHost_phone(String host_phone) {
		this.host_phone = host_phone;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getHost_ro_name() {
		return host_ro_name;
	}
	public void setHost_ro_name(String host_ro_name) {
		this.host_ro_name = host_ro_name;
	}
	public String getHost_ro_addr() {
		return host_ro_addr;
	}
	public void setHost_ro_addr(String host_ro_addr) {
		this.host_ro_addr = host_ro_addr;
	}
	public String getHost_ro_kind() {
		return host_ro_kind;
	}
	public void setHost_ro_kind(String host_ro_kind) {
		this.host_ro_kind = host_ro_kind;
	}
	public String getHost_ro_content() {
		return host_ro_content;
	}
	public void setHost_ro_content(String host_ro_content) {
		this.host_ro_content = host_ro_content;
	}
	public String getHost_ro_latitude() {
		return host_ro_latitude;
	}
	public void setHost_ro_latitude(String host_ro_latitude) {
		this.host_ro_latitude = host_ro_latitude;
	}
	public int getHost_ro_lowprice() {
		return host_ro_lowprice;
	}

	public void setHost_ro_lowprice(int host_ro_lowprice) {
		this.host_ro_lowprice = host_ro_lowprice;
	}
	
	public String getHost_ro_longitude() {
		return host_ro_longitude;
	}
	public void setHost_ro_longitude(String host_ro_longitude) {
		this.host_ro_longitude = host_ro_longitude;
	}
	public String getHost_ro_img() {
		return host_ro_img;
	}
	public void setHost_ro_img(String host_ro_img) {
		this.host_ro_img = host_ro_img;
	}
	
	public int getHost_ro_rate() {
		return host_ro_rate;
	}

	public void setHost_ro_rate(int host_ro_rate) {
		this.host_ro_rate = host_ro_rate;
	}
	
	
}
