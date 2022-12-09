function frm_mod_member_submit (){
    rexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
    result = rexp.test(document.frm_member_info.pwd.value);
    if(!result){
        alert("규칙에 맞는 비밀번호를 입력해주세요");
        document.frm_member_info.pwd.focus();
        document.frm_member_info.pwd.value = "";
        return false;
    }

    if(document.frm_member_info.pwd.value != document.frm_member_info.confirm_pwd.value){
        alert("비밀번호와 확인이 일치하지 않습니다.");
        document.frm_member_info.pwd.focus();
        return false;
    }

    document.frm_member_info.submit();
}