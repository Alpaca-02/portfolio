function modify_info() {

    if (document.frm_room_info.room_image.value.trim() == "") {
		alert("객실 사진을 추가해 주세요.");
		document.frm_room_info.room_image.value = "";
		document.frm_room_info.room_image.focus();
		return false;
	} else if (document.frm_room_info.room_name.value.trim() == "") {
		alert("객실명을 입력해 주세요.");
		document.frm_room_info.room_name.value = "";
		document.frm_room_info.room_name.focus();
		return false;
	}else if (document.frm_room_info.room_price.value == "") {
		alert("가격을 입력해 주세요.");
        document.frm_room_info.room_price.value = "";
		document.frm_room_info.room_price.focus();
        return false;
	} else if (document.frm_room_info.room_per_num.value.trim() == "") {
		alert("인원을 입력해 주세요.");
		document.frm_room_info.room_per_num.value = "";
		document.frm_room_info.room_per_num.focus();
		return false;
	} else if (document.frm_room_info.room_facility.value.trim() == "") {
		alert("편의시설을 입력해 주세요.");
		document.frm_room_info.room_facility.value = "";
		document.frm_room_info.room_facility.focus();
		return false;
	} else {
		alert("저장되었습니다.");
		document.frm_room_info.submit();
		return true;
	}
}
// 객실 숙소 수정 페이지 js

function readImage(input){
	if(input.files && input.files[0]){
		let reader = new FileReader();
		reader.onload=function (event){
			document.querySelector("#preview").src = event.target.result;
			document.querySelector("#preview").classList.remove("hidden");
		}
		reader.readAsDataURL(input.files[0]);
	}
}

