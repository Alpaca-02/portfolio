function clause_show() {
    let body_clause = document.querySelector(".body_clause");
    console.log(body_clause.style.display)
    if(body_clause.style.display === 'none'){
        $(".body_clause").slideDown();
        document.querySelector(".more_icon").style.transform = 'rotate(180deg)';
    } else {
        $(".body_clause").slideUp();
        document.querySelector(".more_icon").style.transform = "none";
    }
}

function frm_join_submit (){
    
    if(document.frm_join.name.value === "" || document.frm_join.name.value.length === 0){
        alert("이름을 입력해주세요");
        return false;
    }

    let rexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,15}$/;
    let result = rexp.test(document.frm_join.id.value);
    if(!result){
        alert("영문, 숫자를 혼합하여 6자~15자");
        document.frm_join.id.focus();
        return false;
    }

    rexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
    result = rexp.test(document.frm_join.pwd.value);
    if(!result){
        alert("규칙에 맞는 비밀번호를 입력해주세요");
        document.frm_join.pwd.focus();
        document.frm_join.pwd.value = "";
        return false;
    }

    if(document.frm_join.pwd.value != document.frm_join.confirm_pwd.value){
        alert("비밀번호와 확인이 일치하지 않습니다.");
        document.frm_join.pwd.focus();
        return false;
    }

    rexp =  /^[0-9]{11}/;
    result = rexp.test(document.frm_join.phone.value);
    if(!result){
        alert("전화번호는 숫자만 입력해주세요");
        document.frm_join.phone.focus();
        return false;
    }
    
    if(document.frm_join.clause_check.checked == false) {
        alert("약관을 확인 후 동의해주세요");
        return false;
    }

    document.frm_join.submit();
}


$(function(){

    
    //패스워드 확인
    let passCheck = false;
    
    $(document).on("keyup","#confirm_pwd", function(){
        
        
        let pwCk = $('#pwd').val();
        let ck_Num = $(this).val();
        if(pwCk == ck_Num ){
            passCheck = true;
            $(".pwd_check").html("비밀번호가 일치합니다.");
            $(".pwd_check").css("color","black");
        }else{
            passCheck = false;
            $(".pwd_check").html("비밀번호가 일치하지 않습니다.");
            $(".pwd_check").css("color","red");
        }
    })
    
    
})
//ID 중복 확인  함수
function fn_idCheck() {
    let id =$('#id').val();   // 입력한 id값을 가져옴
    if(id == ''){
        $('.id_check_result').html("ID를 입력해주세요");
        $('.id_check_result').css('color','red');
        return;
    }
    $.ajax({
        type:"post",
        async:true,
        dataType:"text",
        url:(ctx +"/host/id_check"),
        data:{id:id},
        success: function(data, textStatus){
            if(data=="usable"){
                $('.id_check_result').html("사용 가능한 ID입니다.");
                $('.id_check_result').css('color','black');
            }else{
                $('.id_check_result').html("이미 사용중인 ID입니다.");
                $('.id_check_result').css('color','red');
            }
        }
    })
}

function ck_ph(){
    let phoneNum = $('#phone').val();
    $.ajax({
        type:'post',
        async:false,
        url:(ctx+"/member/phoneCK"),
        data:{"ph_num":phoneNum},
        success:function(){
            Swal.fire('인증번호가 발송되었습니다.');
        }
    })
    // 임시 새로고침 (나중에 수정) !!!!!!!!!
    //location.reload();
}

// 인증번호 확인 함수
function ck_num(){
    let ck_num = $(".input_check_code").val();
    console.log(ck_num);
    $.ajax({
        type:'post',
        async:false,
        url:(ctx+"/member/ck_num"),
        dataType:'text',
        data:{"ck_num":ck_num},
        success:function(data, textStatus){
            console.log(data);

            if(data === 'correct'){
                Swal.fire('인증번호가 일치합니다.');
                
                document.getElementById('phone').readOnly = true;
                document.getElementById('input_check_code').readOnly = true;
                
                document.getElementById('btn_send_code').disabled = true;
                document.getElementById('input_check_code_btn').disabled = true;

                document.getElementById('btn_join').disabled = false;
            } else{
                Swal.fire('인증번호가 일치하지 않습니다.')
            }
        }
    })
}


