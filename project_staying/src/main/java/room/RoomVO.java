package room;

public class RoomVO {
	// 필드
	private String host_id;  // hosttbl과 연동된 호스트 이름 (FK)
	private int room_no;    // 방 번호
	
	private String room_name;  // 방 이름
	private String room_info;  // 방 정보 (추가사항)
	
	private int room_price;    // 1박 가격
	private int room_people;   // 가용 인원수
	
	private String room_img;  // 방 이미지
	
	// 생성자
	public RoomVO() {
		
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getRoom_info() {
		return room_info;
	}

	public void setRoom_info(String room_info) {
		this.room_info = room_info;
	}

	public int getRoom_price() {
		return room_price;
	}

	public void setRoom_price(int room_price) {
		this.room_price = room_price;
	}

	public int getRoom_people() {
		return room_people;
	}

	public void setRoom_people(int room_people) {
		this.room_people = room_people;
	}

	public String getRoom_img() {
		return room_img;
	}

	public void setRoom_img(String room_img) {
		this.room_img = room_img;
	}
	
	
	
	
}
