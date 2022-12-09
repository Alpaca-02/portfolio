package member;

public class MemberVO {
	private String id;
	private String pwd;
	private String phone;
	private String name;
	
	// 생성자
	public MemberVO() {
		
	}
	public MemberVO(String id, String pwd, String phone, String name) {
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.name = name;
	}

	
	//getter setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
