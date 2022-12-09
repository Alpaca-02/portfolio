package room;

import java.util.List;

public class RoomService {
	RoomDAO roomDAO;
	
	public RoomService() {
		roomDAO=new RoomDAO();
	}
	
	// 방 등록 메서드
	public int addRoom(RoomVO roomVO) {
		int room_no = roomDAO.addRoom(roomVO);
		return room_no;
	}
	
	// 해당 유저의 방 목록을 가져온느 메서드
	public List<RoomVO> hostRoomList(String host_id){
		List<RoomVO> roomList = roomDAO.hostRoomList(host_id);
		return roomList;
	}
	
	// 방 번호로 방 정보를 조회하는 메서드
	public RoomVO room_no_info(int room_no) {
		RoomVO roomVO = roomDAO.room_no_info(room_no);
		return roomVO;
	}
	
	// 방을 삭제하는 메서드
	public RoomVO delRoom(int room_no) {
		RoomVO roomVO = roomDAO.delRoom(room_no);
		return roomVO;
	}
}
