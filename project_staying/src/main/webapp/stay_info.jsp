<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 페이지 주소를 가져와 변수에 담음 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>숙소 정보</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    <%--공통 css--%>
    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <%-- 숙소정보 페이지 css --%>
    <link rel="stylesheet" href="${contextPath}/css/stay/stay_info.css">
    <%-- 스와이퍼 css, js --%>
    <link rel="stylesheet"  href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="${contextPath}/css/stay/stay_info_swiper.css">
    <script defer src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script defer src="${contextPath}/js/stay/stay_info_swiper.js"></script>
    <%-- 아이콘 --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <%-- 부트스트랩 css --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <%-- 카카오 지도 API --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0eb77c4d3ba3735dfe84007620b700bd&libraries=services"></script>
	<script src="${contextPath}/js/stay/stay_info.js"></script>

    <script src="${contextPath}/js/stay/view_star.js"></script>
	
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
        
    
        <%--컨텐츠 영역--%>
        <div id="contents_area" class="row">
            <%-- 슬라이드 , 숙소정보 --%>
            <div class="stay_info_area col">
                <div class="stay_image_swiper_area" id="slide">
                    <div
                    style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff"
                    class="stay_image_swiper stay_image_swiper_top">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <img src="${contextPath}/hostDownload.do?host_id=${hostInfo.host_id}&imageFileName=${hostInfo.host_ro_img}" />
                            </div>
                            <c:forEach var="room" items="${roomList}" end="3">
                                <div class="swiper-slide">
                                    <img src="${contextPath}/roomDownload.do?room_no=${room.room_no}&imageFileName=${room.room_img}" />
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                    </div>
                    <div thumbsSlider="" class="stay_image_swiper stay_image_swiper_bottom">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <img src="${contextPath}/hostDownload.do?host_id=${hostInfo.host_id}&imageFileName=${hostInfo.host_ro_img}" />
                            </div>
                            <c:forEach var="room" items="${roomList}" end="3">
                            <div class="swiper-slide">
                                <img src="${contextPath}/roomDownload.do?room_no=${room.room_no}&imageFileName=${room.room_img}" />
                            </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
                <div class="stay_info">
                    <h2>${hostInfo.host_ro_name}</h2>
                    <p>${hostInfo.host_ro_content}</p>
                </div>
            </div>
            <%-- // 슬라이드 , 숙소정보 --%>

            <%-- 숙소상세, 위치, 후기 메뉴--%>
            <div class="col12" id="select_menu">
                <div class="container text-center">
                    <div class="row row-cols-auto" id="menu">
                        <div class="col hover" id="detail">객실 상세</div>
                        <div class="col hover" id="place" onclick="place_click('${hostInfo.host_ro_name}', '${hostInfo.host_ro_latitude}', '${hostInfo.host_ro_longitude}')">위치</div>
                        <div class="col hover" id="review">후기</div>
                    </div>
                </div>
            </div>
            <%-- // 숙소상세, 위치, 후기 메뉴--%>

            <%-- 하단 내용 --%>
            <div class="col12" id="stay_contents_area" >
                <%-- 객실 상세 --%>
                <div id="click_detail">
                    <c:forEach var="room" items="${roomList}">
	                    <div class="card mb-3 hotel_room_wrap">
	                        <div class="row g-0 room_info">
	                            <div class="col-md-5 room_info_img">
	                                <img src="${contextPath}/roomDownload.do?room_no=${room.room_no}&imageFileName=${room.room_img}" class="img-fluid" alt="Deluxe room">
	                            </div>
	                            <div class="col-md-7">
	                                <div id="room_info_content2" class="card-body room_info_content">
	                                    <h4 class="card-title">${room.room_name}</h4>
	                                    <p class="card-text">투숙 가능인원 &nbsp&nbsp${room.room_people}명</p>
	                                    <p class="card-text amenitie">편의시설 > <span>${room.room_info}</span></p>
	                                    <hr>
	                                    <p class="card-text price">가격<span>${room.room_price}원 / 1박</span></p>
	                                    <p id="pay_btn2" class="card-text">
	                                    	<button type="button" class="btn btn-outline-primary btn-sm" onclick="location.href='${contextPath}/booking/booking_page.do?host_id=${hostInfo.host_id}&room_no=${room.room_no}' ">예약하기</button>
	                                    	 <%-- 해당 업체의 업체id와 방 번호를 담아 컨트롤러로 전다 --%>
	                                    </p>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
                <%-- // 객실 상세 --%>

                <%-- 위치 --%>
                <div id="click_place">
                    <div id="map"></div>
                </div>
                <%-- // 위치 --%>

                <%-- 리뷰 --%>
                <div id="click_review">
                    <c:if test="${empty review_list}">
                        <div class="reviews">
                            <p class="review_info"><span></span>

                            </p>
                            <div class="review_date">
                                <span></span>
                                <div class="review_content">
                                    작성된 리뷰가 없습니다.
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach var="review" items="${review_list}">
                        <c:choose>
                            <c:when test="${review.rv_type eq 0}">
                                <div class="reviews">
                                    <p class="review_info"><span>${review.id}</span>
                                    </p>
                                        <p class="view_star">${review.rv_rate/10}</p>
                                    <div class="review_date">
                                        <span><fmt:formatDate value="${review.rv_writeDate}"/></span>
                                        <div class="review_content">
                                            ${review.rv_content}
                                            <c:if test="${!empty review.rv_img}">
                                                <img src="${contextPath}/reviewDownload.do?rv_no=${review.rv_no}&rv_img=${review.rv_img}" alt="${review.rv_no}번 글 이미지">
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${review.rv_type eq 1}"> 
                                <div class="reviews">
                                    <div class="host_comments">↳사장님 답글 : </span><span>${review.rv_content}</span></div>
                                    <hr>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
                <%-- // 리뷰 --%>
            </div>
            <%-- // 하단 내용 --%>
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