/*$('input[type=radio][name=flexRadioDefault]').on('click', function() {

    var chkValue = $('input[type=radio][name=flexRadioDefault]:checked').val();

    if (chkValue == '1') {
        $('.pay1').css('display', 'block');
        $('.pay2').css('display', 'none');
    } else if (chkValue == '2') {
        $('.pay1').css('display', 'none');
        $('.pay2').css('display', 'block');
    }
});*/


function payCheck(){
	if (document.login_form.name.value.trim() == ""){
		alert("이름을 입력해 주세요.");
        document.login_form.name.value = "";
		document.login_form.name.focus();
		return false;
	} else if (document.login_form.tel.value.trim() == ""){
		alert("전화번호를 입력해 주세요.");
        document.login_form.tel.value = "";
		document.login_form.tel.focus();
		return false;
	} else if(!clause_check.checked) {
        alert("약관 동의를 체크해 주세요.");
        clause_check.focus();
        return false;
    }else {
		payment();
	}
};

// 결제 uid 생성 (난수)


// 결제 수행 함수
function payment(data) {
    let uid = new Date().getTime() + Math.random() +'a';
    IMP.init('');//아임포트 가맹점 식별코드
    IMP.request_pay({  // param
        pg: "kakaopay.TC0ONETIME", //pg사명 or pg사명.CID 
        pay_method: "card", //지불 방법
        merchant_uid: uid, 
        name: host_ro_name, // 상품명, 업체이름을 가져옴
        amount: full_price , // 결제 금액, 총 숙박금액을 가져옴
        buyer_email : "testiamport@naver.com", 
        buyer_name : user_name,
        buyer_tel : user_phone
    }, function (rsp) { // callback
        if (rsp.success) {
            alert("결제 성공");
            document.book_hidden.submit();

        } else {
            alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg + ")");
        }
    });
}