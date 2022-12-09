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
    <title>숙소 등록</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">

    <%--주소 api--%>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=027377f54149e77d2c5a15096947dd45&libraries=services"></script>
    <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="${contextPath}/js/business/address.js"></script>

    <%-- 이미지 선택시 미리보기 --%>
    <script src="${contextPath}/js/stay/readimage.js"></script>

    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <link rel="stylesheet" href="${contextPath}/css/business/business_stay_join.css">
    <script src="${contextPath}/js/business/business_stay_join.js"></script>
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

        <%--컨텐츠 영역--%>
        <div id="contents_area">
            <form action="${contextPath}/host/addHost.do" name="frm_stay_join" id="frm_stay_join" method="post" enctype="multipart/form-data">
                <%-- 이전 페이지에서 넘겨온 데이터--%>
                <input type="hidden" name="name" value="${name}">
            	<input type="hidden" name="id" value="${id}">
            	<input type="hidden" name="pwd" value="${pwd}">
            	<input type="hidden" name="phone" value="${phone}">

                <h2 class="title">사업장 등록</h2>
                <div class="stay_join_info_area">
                    <div class="stay_name_area">
                        <label for="stay_name">시설 명</label>
                        <input type="text" name="stay_name" id="stay_name">
                    </div>
                    <div class="stay_address_area">
                        <label for="postcode">시설 주소</label>
                        <div class="address_input">
                            <input type="text" name="postcode" id="postcode" placeholder="우편번호" readonly>
                            <input type="button" onclick="fn_postcode()" value="우편번호 찾기">
                            <input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소" readonly>
                            <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
                            
                            <input type="hidden" name="latitude" id="latitude" value=""> <%--위도값--%>
                            <input type="hidden" name="longitude" id="longitude" value=""> <%--경도값--%>
                        </div>
                    </div>
                    <div class="stay_kind_area">
                        <label>시설 유형</label>
                        <div class="radio_input">
                            <input type="radio" name="stay_kind" id="pension" value="pension"> <label for="pension">펜션</label>
                            <input type="radio" name="stay_kind" id="hotel" value="hotel"> <label for="hotel">호텔</label>
                            <input type="radio" name="stay_kind" id="camping" value="camping"> <label for="camping">캠핑장</label>
                            <input type="radio" name="stay_kind" id="hanok" value="hanok"> <label for="hanok">한옥</label>
                        </div>
                    </div>
                    <div class="stay_content_area">
                        <label for="stay_content">시설 설명</label>
                        <textarea type="text" name="stay_content" id="stay_content" maxlength="50"></textarea>
                    </div>
                    <div class="stay_image_area">
                        <label for="stay_image">대표 이미지</label>
                        <div class="image_area">
                            <input type="file" name="stay_image" id="stay_image" onchange="readImage(this)">
                            <img src="${contextPath}/images/logocap.png" id="preview" alt="">
                        </div>
                    </div>
                </div>
                <div class="btn_area">
                    <input type="button" value="등록하기" onclick="registration()">
                </div>
            </form>
        </div>
        <%-- footer --%>
        <footer>
            <div id="footer_inner">
                <div class="page_info_area">
                    <img src="images/Home/logo/rm_back_logo_img.png" alt="page logo">
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