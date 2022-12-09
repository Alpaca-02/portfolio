function registration(){

	if (document.frm_stay_join.stay_name.value.trim() == "") {
		alert("시설 명을 입력해 주세요.");
		document.frm_stay_join.stay_name.value = "";
		document.frm_stay_join.stay_name.focus();
		return false;
	} else if (document.frm_stay_join.detailAddress.value.trim() == "") {
		alert("상세주소를 입력해 주세요.");
		document.frm_stay_join.detailAddress.value = "";
		document.frm_stay_join.detailAddress.focus();
		return false;
	}else if (document.frm_stay_join.stay_kind.value == "") {
		alert("시설 유형을 선택해 주세요.");
        return false;
	} else if (document.frm_stay_join.stay_content.value.trim() == "") {
		alert("시설 설명을 입력해 주세요.");
		document.frm_stay_join.stay_content.value = "";
		document.frm_stay_join.stay_content.focus();
		return false;
	} else if (document.frm_stay_join.stay_image.value.trim() == "") {
		alert("대표 사진을 등록해 주세요.");
		document.frm_stay_join.stay_image.value = "";
		document.frm_stay_join.stay_image.focus();
		return false;
	} else {
		alert("등록되었습니다.");
		document.frm_stay_join.submit();
		return true;
	}
}
// 숙소 등록페이지 js