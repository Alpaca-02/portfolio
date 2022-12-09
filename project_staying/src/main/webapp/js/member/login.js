window.onload = function () {
   

}

function stay_contents_work(){
	let index = $(this).index();
	$(".login").css("z-index", 0);
	$(".login").find("h2").removeClass("target");
	$(".login").eq(index).css("z-index", 10);
	$(".login").eq(index).find("h2").addClass("target");
}

function member_loginCheck(){
	if (document.member_login_form.id.value.trim() == ""){
		alert("아이디를 입력해 주세요.");
		document.member_login_form.id.value = "";
		document.member_login_form.id.focus();
		return false;
	} else if (document.member_login_form.pwd.value.trim() == ""){
		alert("비밀번호를 입력해주세요.");
		document.member_login_form.pwd.value = "";
		document.member_login_form.pwd.focus();
		return false;
	} else {
		document.member_login_form.submit();
		return true;
	}
	document.member_login_form.submit();
}

function business_loginCheck(){
	if (document.business_login_form.id.value.trim() == ""){
		alert("아이디를 입력해 주세요.");
		document.business_login_form.id.value = "";
		document.business_login_form.id.focus();
		return false;
	} else if (document.business_login_form.pwd.value.trim() == ""){
		alert("비밀번호를 입력해주세요.");
		document.business_login_form.pwd.value = "";
		document.business_login_form.pwd.focus();
		return false;
	} else {
		document.business_login_form.submit();
		return true;
	}
	document.business_login_form.submit();
}