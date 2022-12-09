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
    <title>회원정보</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">

    <script src="${contextPath}/js/member/member_info.js"></script>

    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <link rel="stylesheet" href="${contextPath}/css/member/member_info.css">
    <link rel="stylesheet" href="${contextPath}/css/member/member_common.css">

    <%-- 알림메세지가 존재할 경우 --%>
    <script>
        window.onload = function(){
            <c:if test="${not empty msg}">
                alert('${msg}');
            </c:if>
            $(".login").click(stay_contents_work);
        }
    </script>
</head>
<body>
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
                    <a href="${contextPath}/member/member_info.do">마이페이지</a>
                    <a class="btn_login" href="${contextPath}/member/logout.do">로그아웃</a>
                </nav>
            </div>
        </header>
        <%-- // header 영역 --%>
        <!--컨텐츠 영역-->
        <div id="contents_area">
            <!--마이 페이지 메뉴 영역-->
            <div class="mypage_menu_area">
                <nav role="navigation" class="mypage_menu">
                    <ul class="main_menu">
                        <li>마이 페이지</li>
                        <li>
                            <ul class="under_menu">
                                <li><a href="${contextPath}/member/member_info.do">내 정보 관리</a></li>
                                <li><a href="${contextPath}/booking/member_book_list.do">예약 관리</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="contents_info_area">
                <section class="contents_info">
                    <h2>내 정보 관리</h2>
                    <form action="${contextPath}/member/update_password.do" method="post" id="frm_member_info" name="frm_member_info">
                        <div class="frm_input_area">
                            <p>
                                <label for="name">이름</label>
                                <input type="text" id="name" name="name" value="${memberInfo.getName() }" disabled>
                            </p>
                            <p>
                                <label for="id">아이디</label>
                                <input type="text" id="id" name="id_show" value="${memberInfo.getId() }" disabled>
                                <input type="hidden" name="id" value="${memberInfo.getId()}">
                            </p>
                            <p>
                                <label for="pwd">비밀번호</label>
                                <input type="password" name="pwd" id="pwd" placeholder="영문/숫자 8~20 비밀번호">
                            </p>
                            <p>
                                <label for="confirm_pwd">비밀번호 확인</label>
                                <input type="password" name="confirm_pwd" id="confirm_pwd" placeholder="비밀번호 재입력">
                            </p>
                            <p>
                                <label for="phone">전화번호</label>
                                <input type="tel" class="input_phone" name="phone" id="phone" maxlength="11" value="${memberInfo.getPhone() }" disabled>
                            </p>
                        </div>
                        <div class="btn_area">
                            <input type="button" class="btn_mod" onclick="frm_mod_member_submit()" value="비밀번호 변경">
                        </div>
                    </form>
                </section>
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
    </div>
</body>
</html>