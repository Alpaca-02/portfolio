<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
        isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 페이지 주소를 가져와 변수에 담음 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>STAYING 로그인</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    
    <script src="${contextPath}/js/member/login.js"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    
    
    <%-- 알림메세지가 존재할 경우 --%>
    <script>
        window.onload = function(){
            <c:if test="${not empty msg}">
                alert('${msg}');
            </c:if>
            $(".login").click(stay_contents_work);
        }
    </script>

    <%-- 네이버 연동 스크립트 --%>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/member/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/common.css">
    <style>
        h2 {
            margin-top: 13px;
        }
    </style>
</head>
<body>
    <%-- header 영역 --%>
        <header>
            <div id="inner_header">
                <h1 class="logo">
                <a href="${contextPath}/host/main_page.do">
                    <img src="${contextPath}/images/Home/logo/logo_img.png" alt="page_logo">
                </a>
                </h1>
                <nav role="navigation">
                    <c:choose>
                        <c:when test="${user_type eq 'user'}">
                            <a href="${contextPath}/member/member_info.do">마이페이지</a>
                            <a class="btn_login" href="${contextPath}/member/logout.do">로그아웃</a>
                        </c:when>
                        <c:when test="${user_type eq 'host'}">
                            <a href="${contextPath}/room//my_room_info.do">내 사업장</a>
                            <a class="btn_login" href="${contextPath}/member/logout.do">로그아웃</a>
                        </c:when>
                        <c:otherwise>

                            <a class="btn_login" href="${contextPath}/login.jsp">로그인</a>
                        </c:otherwise>
                    </c:choose>
                </nav>
            </div>
        </header>
        <%-- // header 영역 --%>
    <div id="wrap">
        <div id="login_wrap">
            <div class="member_login login">
                <h2 class="target">일반 회원</h2>
                <form action="${contextPath}/member/memberLogin.do" method="post" name="member_login_form" class="login_form">
                    <fieldset>
                        <div class="title">
                            <label for="id">아이디</label>
                            <input type="id" name="id" placeholder="ID" maxlength="20" autofocus>
                        </div>
                        <div class="title">
                            <label for="pwd">비밀번호</label>
                            <input type="password" name="pwd" placeholder="Password" maxlength="20">
                        </div>
                        <input type="button" id="btn_mamber_login" onclick="member_loginCheck()" value="로그인">
                    </fieldset>
                </form>
                <hr>
                <button type="button" id="join" onclick="location.href='${contextPath}/member_join.jsp'">회원가입</button>
                <button type="button" id="naverIdLogin" onclick="naver_login()">네이버로 로그인</button>
                <div id="naver_id_login">네이버로 로그인</div>
            </div>
            <div class="business_login login">
                <h2>사업자</h2>
                <form action="${contextPath}/host/hostLogin.do" method="post" name="business_login_form" class="login_form">
                    <fieldset>
                        <div class="title">
                            <label for="id">아이디</label>
                            <input type="id" name="id" placeholder="ID" maxlength="20" autofocus>
                        </div>
                        <div class="title">
                            <label for="pwd">비밀번호</label>
                            <input type="password"  name="pwd" placeholder="Password" maxlength="20">
                        </div>
                        <input type="button" id="btn_business_login"  onclick="business_loginCheck()" value="로그인">
                    </fieldset>
                </form>
                <hr>
                <button type="button" id="join" onclick="location.href='${contextPath}/host_join.jsp'">사업자 회원가입</button>
            </div>
        </div>
    </div>
        <%-- footer --%>
        <footer>
            <div id="footer_inner">
                <div class="page_info_area">
                    <img src="${contextPath}/images/Home/logo/rm_back_logo.png" alt="page logo">
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
        <%-- // footer --%>
</body>
<script type="text/javascript">
    var naver_id_login = new naver_id_login("", "http://192.168.4.150:8090/staying/naver_callback.jsp");
        var state = naver_id_login.getUniqState();
        naver_id_login.setButton("green", 3,40);
        naver_id_login.setDomain("http://192.168.4.150:8090/staying/login.jsp");
        naver_id_login.setState(state);
        naver_id_login.init_naver_id_login();

    function naver_login(){
        var btnNaverLogin = document.getElementById("naver_id_login").firstChild;
		btnNaverLogin.click();
    }
    </script>
</html>