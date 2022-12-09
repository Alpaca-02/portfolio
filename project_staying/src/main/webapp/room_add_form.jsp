<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 페이지 주소를 가져와 변수에 담음 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>객실정보</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <link rel="stylesheet" href="${contextPath}/css/business/business_form.css">

    <script src="${contextPath}/js/business/business_form.js"></script>
</head>
<body>
    <%--수정, 추가 같은 폼 사용--%>
    <div id="wrapper">

        <%-- header 영역 --%>
        <header>
            <div id="inner_header">
                <h1 class="logo">
                <a href="${contextPath}/host/main_page.do">
                    <img src="${contextPath}/images/Home/logo/logo_img.png" alt="page logo">
                </a>
                </h1>
                <nav role="navigation">
                    <a href="#">마이페이지</a>
                    <a class="btn_login" href="${contextPath}/login.jsp">로그인</a>
                </nav>
            </div>
        </header>
        <%-- // header 영역 --%>

        
        <%--컨텐츠 영역--%>
        <div id="contents_area">
            <div class="visual_image">
                
                <img src="${contextPath}/hostDownload.do?host_id=${host_info.host_id}&imageFileName=${host_info.host_ro_img}" alt="숙소 대표 사진">
                
                <h2>${host_info.host_ro_name}</h2>
            </div>
            <section class="room_form_area">
                <form action="${contextPath}/room/addRoom.do" name="frm_room_info" method="post" enctype="multipart/form-data">
                    <h2 class="hidden">숙소 방 수정 폼</h2>
                    <div class="room_info">
                        <div class="flex_area">
                            <span class="label_name">
                                <label for="room_image">객실사진</label>
                            </span>
                            <span class="label_content">
                                <input type="file" name="room_image" id="room_image" onchange="readImage(this)">
                                <img src="" alt="#" id="preview" class="hidden">
                            </span>
                        </div>
                        <div>
                            <span class="label_name">
                                <label for="room_name">객실명</label>
                            </span>
                            <span class="label_content">
                                <input type="text" name="room_name" id="room_name">
                            </span>
                        </div>
                        <div>
                            <span class="label_name">
                                <label for="room_price">가격</label>
                            </span>
                            <span class="label_content">
                                <input type="number" name="room_price" id="room_price">
                            </span>
                        </div>
                        <div>
                            <span class="label_name">
                                <label for="room_per_num">인원</label>
                            </span>
                            <span class="label_content">
                                <input type="number" name="room_per_num" id="room_per_num">
                            </span>
                        </div>
                        <div class="flex_area">
                            <span class="label_name">
                                <label for="room_facility">편의시설</label>
                            </span>
                            <span class="label_content">
                                <textarea name="room_facility" id="room_facility" maxlength="50"></textarea>
                            </span>
                        </div>
                    </div>
                    <input type="button" class="btn_info_save" value="저장하기" onclick="modify_info()">
                </form>
            </section>
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