function fn_review_form_submit() {
    let form = document.frm_review;

    if(form.review_star.value == "") {
        alert("평점을 입력해주세요");
        return false;
    } else if (form.review_content.value.length < 10 ) {
        alert("10자 이상 작성해주세요");
        return false;
    } 

    form.submit();
}



function fn_review_active(host_id,book_no,stat) {

    document.getElementById("host_id_hidden").value = host_id;
    document.getElementById("book_no_hidden").value = book_no;

let set_id = "check_out_hidden"+stat

    let check_out = document.getElementById(set_id).value;  // 체크아웃 일자를 가져옴 (yyyyMMdd 형태 number값)
    let year = check_out.substring(0, 4);
    let month = check_out.substring(4, 6);
    let day = check_out.substring(6, 8);  // 년, 월, 일로 구분

    let check_out_date = new Date(year, month-1, day); // 위에서 구분한 값을 Date값으로 변환
    let now = new Date();                              // 현재 일자를 저장
    if(check_out_date > now) {               // 체크아웃 전 일 경우
        $('#exampleModal').modal("hide");
        alert("아직 체크아웃 전 입니다");
    }else {                                 //  체크아웃 후 일 경우
        $('#exampleModal').modal("show");
    }

}

function fn_alert_review(){
    alert("이미 리뷰를 작성한 예약입니다.")
}

