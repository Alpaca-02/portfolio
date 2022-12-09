<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 페이지 주소를 가져와 변수에 담음 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    <%--아이콘--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
        let ctx = "${contextPath}/";
    </script>
    
    <script src="${contextPath}/js/member/member_join.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <link rel="stylesheet" href="${contextPath}/css/member/member_join.css">
</head>
<body>
    <%--건너뛰기 링크--%>
    <a id="skipNav" href="#contentsArea">본문 바로가기</a>
    <%--건너뛰기 링크 종료--%>

    <%--wraper 시작--%>
    <div id="wrapper">
        <%-- header 영역 --%>
        <header>
            <div id="inner_header">
                <h1 class="logo">
                <a href="${contextPath}/host/main_page.do">
                    <img src="${contextPath}/images/Home/logo/logo_img.png" alt="page_logo">
                </a>
                </h1>
                <nav role="navigation">
                            <a class="btn_login" href="${contextPath}/login.jsp">로그인</a>
                </nav>
            </div>
        </header>
        <%-- // header 영역 --%>
        
        <%--컨텐츠 영역 시작--%>
        <div id="contents_area">
            <section id="member_join_area">
                <h2>회원가입</h2>
                <form action="${contextPath}/member/addMember.do" method="post" id="frm_join" name="frm_join">
                    <div class="frm_input_area">
                        <p>
                            <input type="text" id="name" name="name" placeholder="이름">
                        </p>
                        <p>
                            <input type="text" id="id" name="id" placeholder="아이디">
                            <a href="javascript:return false;" class="id_check" onclick="fn_idCheck()">중복확인</a>
                        </p>
                        <p class="id_check_result"></p>
                        <p>
                            <input type="password" name="pwd" id="pwd" placeholder="영문/숫자 8~20 비밀번호">
                        </p>
                        <p>
                            <input type="password" name="confirm_pwd" id="confirm_pwd" placeholder="비밀번호 확인">
                        </p>
                        <p>
                            <input type="tel" class="input_phone" name="phone" id="phone" maxlength="11" title="하이픈(-)은 입력할 수 없습니다" placeholder="전화번호">
                            <input type="button" class="btn_send_code" id="btn_send_code" value="인증번호 발송" onclick="ck_ph()">
                        </p>
                        <p>
                            <input type="number" name="input_check_code" class="input_check_code" id="input_check_code" maxlength="6" placeholder="인증번호">
                            <input type="button" class="btn_check_code" value="인증번호 확인" id="input_check_code_btn" onclick="ck_num()">
                        </p>
                        <div class="clause_area">

                            <div class="btn_clause">
                                <p>
                                    <input type="checkbox" name="clause_check" id="clause_check" class="clause_check">
                                    <label for="clause_check" class="clause_check_label">약관동의 <span style="color: rgba(255, 0, 0, 0.612);">(필수)</span></label>
                                </p>
                                <button type="button" id="more" class="more" onclick="clause_show()"><i class="more_icon fas fa-angle-down"></i></button>
                            </div>
                            <div class="body_clause" style="display: none;">
                                <p class="clause_content">
                                    서비스 이용약관<br>
                                    제 1조 (목적)<br>
                                    본 약관은 서비스(이하 "회사"라 한다)는 홈페이지에서 제공하는 서비스(이하 "서비스"라 한다)를 제공함에 있어 회사와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.<br>
                                    제 2조 (용어의 정의)<br>
                                    1. 본 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>
                                    '서비스'란 회사가 이용자에게 서비스를 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 구성한 가상의 공간을 의미하며, 서비스 자체를 의미하기도 합니다.<br>
                                    '회원(이하 "회원"이라 한다)'이란 개인정보를 제공하여 회원등록을 한 자로서 홈페이지의 정보를 지속적으로 제공받으며 홈페이지가 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.<br>
                                    '아이디(이하 "ID"라 한다)'란 회원의 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 회사가 승인하는 회원 고유의 계정 정보를 의미합니다.<br>
                                    '비밀번호'란 회원이 부여 받은 ID와 일치된 회원임을 확인하고, 회원의 개인정보를 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 의미합니다.<br>
                                    '회원탈퇴(이하 "탈퇴"라 한다)'란 회원이 이용계약을 해지하는 것을 의미합니다.<br>
                                    2. 본 약관에서 사용하는 용어의 정의는 제1항에서 정하는 것을 제외하고는 관계법령 및 서비스 별 안내에서 정하는 바에 의합니다.<br>
                                    제 3조 (이용약관의 효력 및 변경)<br>
                                    1. 회사는 본 약관의 내용을 회원이 쉽게 알 수 있도록 각 서비스 사이트의 초기 서비스화면에 게시합니다.<br>
                                    2. 회사는 약관의 규제에 관한 법률, 전자거래기본법, 전자 서명법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.<br>
                                    3. 회사는 본 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행 약관과 함께 회사가 제공하는 서비스 사이트의 초기 화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다.<br>
                                    다만, 회원에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 회사는 개정 전 내용과 개정 후 내용을 명확하게 비교하여 회원이 알기 쉽도록 표시합니다.<br>
                                    4. 회원은 개정된 약관에 대해 거부할 권리가 있습니다. 회원은 개정된 약관에 동의하지 않을 경우 서비스 이용을 중단하고 회원등록을 해지할 수 있습니다.<br>
                                    단, 개정된 약관의 효력 발생일 이후에도 서비스를 계속 이용할 경우에는 약관의 변경사항에 동의한 것으로 간주합니다.<br>
                                    5. 변경된 약관에 대한 정보를 알지 못해 발생하는 회원 피해는 회사가 책임지지 않습니다.<br>
                                    제 4조 (약관 외 준칙)<br>
                                    1. 본 약관은 회사가 제공하는 서비스에 관해 별도의 정책 및 운영규칙과 함께 적용됩니다.<br>
                                    2. 본 약관에 명시되지 아니한 사항과 본 약관의 해석에 관하여는 관계법령에 따릅니다.<br>
                                </p>
                            </div>  

                        </div>
                    </div>
                    <div class="btn_join_area">
                        <input type="button" id="btn_join" name="btn_join" class="btn_join" onclick="frm_join_submit()" value="회원가입" disabled>
                    </div>
                </form>
            </section>
        </div>
        <footer>
            <div id="footer_inner">
                <div class="page_info_area">
                    <img src="images/rm_back_logo.png" alt="페이지 로고">
                    <p class="page_info"> 
                        &copy;2022 사이트이름. All Rights Reserved. <br>
                        주소: 서울특별시 종로구 어딘가. 사업자등록번호: 777-88-99999. <br>
                        대표이사: 누군가
                    </p>
                </div>
                <div class="cs_info_area">
                    <table class="cs_info">
                        <tr><td colspan = "2">고객센터</td></tr>
                        <tr><td>Tel.</td><td>000-111-2222</td></tr>
                        <tr><td>E-mail</td><td>staying@staying.co.kr</td></tr>
                    </table>
                </div>
            </div>
        </footer>
        <%--푸터 영역 종료--%>
    </div>
    <%--wrapper 영역 종료--%>
</body>
</html>