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
    <title>메인페이지</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">

    <script>
        let ctx = "${contextPath}/";
    </script>
    <%-- 공통 css --%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/common.css">
    <%-- 검색 css --%>
    <%-- <link rel="stylesheet" href="${contextPath}/css/stay/search_common.css"> --%>
    <%-- 메인페이지 css --%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/index.css">
    <%-- 인기 숙소 --%>
    <script src="${contextPath}/js/stay/jquery-3.6.0.min.js"></script>
    <script src="${contextPath}/js/stay/best_room_select.js"></script>
    <%-- 달력 --%>
    <link rel="stylesheet" href="${contextPath}/css/stay/datepicker.min.css">
    <script src="${contextPath}/js/stay/datepicker.min.js"></script>
    <script src="${contextPath}/js/stay/datepicker.ko.js"></script>
    <%-- 아이콘 --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <%-- 스크롤시 상단바 --%>
    <%-- <script src="${contextPath}/js/stay/scroll.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/scroll.css"> --%>
    <%-- 상단 숙소유형, 체크인, 체크아웃, 인원수 --%>
    <%-- 부트스트랩 --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

    <style>
        a{
            text-decoration: none;
            color: #000;
        }
        p{
            color: #000;
            text-decoration:none;
        }
    </style>
    <%-- 아이콘 클릭시 input(숙소유형)에 이름 --%>
    <script>
        function fn_search_submit () {
            const frm = document.frm_stay_search;
            if(frm.selected.value === "none") {
                alert("숙소 유형을 선택해주세요")
                return false;
            };
            if(frm.check_in.value === "" || frm.check_in.value.length === 0) {
                alert("체크인 날짜를 입력해주세요");
                return false;
            };
            if(frm.check_out.value === "" || frm.check_out.value.length === 0) {
                alert("체크아웃 날짜를 입력해주세요");
                return false;
            };
            if(frm.stay_person.value === "" || frm.stay_person.value.length === 0) {
                alert("인원수를 입력해주세요");
                frm.stay_person.focus();
                return false;
            };
            frm.submit();
        };
    </script>
</head>
<body>
    <%--건너뛰기 링크--%>
    <a id="skipNav" href="#contents-area">본문 바로가기</a>
    <%--건너뛰기 링크 종료--%>

    <%-- wraper --%>
    <div id="wrapper">
        <%--<div id="scroll_wrap">
            <div class="scroll">
                <div class="sc_search">
                    <input type="text" class="sc_room_name" placeholder="숙 소 명">
                </div>
                <div class="sc_check_in">
                    <input type="text" class="sc_date" id="datepicker1" placeholder="체크인 날짜">
                </div>
                <div class="sc_check_out">
                    <input type="text" class="sc_date" id="datepicker2" placeholder="체크아웃 날짜">
                </div>
                <div class="sc_people">
                    <input type="text" class="sc_people_num" placeholder="인원수">
                </div>
                <div class="sc_btn">
                    <button class="sc_button">검색</button>
                </div>
            </div>
        </div> --%>
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

        <div id="main_img">
            <img src="${contextPath}/images/Home/mainBg/pano.png" alt="main_img">
        </div>

        <%-- contents --%>
        <div class="container" id="container">
            <main>
                <div class="row">
                    <%-- background_image--%>
                    <%-- <div class="col-12">
                        <div id="main_img">
                            <img src="${contextPath}/images/Home/mainBg/pexels-jimmy-teoh-1010657.jpg" alt="main_img">
                        </div>
                    </div> --%>
                    <%-- // background_image--%>

                    <%-- icon_controller , date --%>
                    <div class="col-12">
                        <div id="controller">
                            <div class="controller_wrap card shadow-sm">
                                <div class="row lodging_wrap">
                                    <div class="col icon1 icon">
                                        <img id="pensionIcon" src="${contextPath}/images/Home/themeIcons/house_select.png" alt="pension_icon">
                                    </div>
                                    <div class="col icon2 icon">
                                        <img id="hotelIcon" src="${contextPath}/images/Home/themeIcons/hotel.png" alt="hotel_icon">
                                    </div>
                                    <div class="col icon3 icon">
                                        <img id="campingIcon" src="${contextPath}/images/Home/themeIcons/camping.png" alt="camping_icon">
                                    </div>
                                    <div class="col icon4 icon">
                                        <img id="hanokIcon" src="${contextPath}/images/Home/themeIcons/hanok.png" alt="hakok_icon">
                                    </div>
                                </div>
                            <%--<div class="date_wrap">
                                    <div class="date">
                                        <input type="text" class="check_in" id="datepicker1" placeholder="eeeee">
                                        <input type="text" class="check_in" id="datepicker1" placeholder="체크인 날짜">
                                        <input type="text" class="check_out" id="datepicker2" placeholder="체크아웃 날짜">
                                        <input type="text" name="stay_person" class="search" placeholder="인원수">
                                        <button type="button" class="btn btn-outline-primary btn-sm">검색</button>
                                    </div>
                                </div> --%>
                                <form action="${contextPath}/host/search.do" name="frm_stay_search" class="stay_search_area">
                                    <label>
                                        <span class="bed fa fa-bed" aria-hidden="true"></span>
                                        <input type="text" name="room_kind_show" id="selected" readonly value="펜션">
                                        <input type="hidden" name="room_kind" id="room_kind_select" value="pension">
                                    </label>
                                    <label>
                                        <span class="cal far fa-calendar-alt"></span>
                                        <input type="text" name="check_in" class="check_in" id="datepicker1" placeholder="체크인">
                                    </label>
                                    <label>
                                        <span class="cal far fa-calendar-alt"></span>
                                        <input type="text" name="check_out" class="check_out" id="datepicker2" placeholder="체크아웃">
                                    </label>
                                    <label>
                                        <span class="users fa fa-users" aria-hidden="true"></span>
                                        <input type="text" class="stay_person" name="stay_person" placeholder="인원수"oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" >
                                    </label>
                                    <button type="button" class="btn_stay_search" onclick="fn_search_submit()"><span id="search_btn" class=" fa fa-search" aria-hidden="true"></span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%-- // icon_controller , date --%>

                    <%-- best_hotel --%>
                    <div class="col-12">
                        <div id="best_hotel_wrap">
                            <%--인기 펜션--%>
                            <div class="best_pension row">
                                <h2 class="mb-4">인기 펜션</h2>
                                <c:forEach var="pension" items="${best_pension}">
                                <div class="room col">
                                    <div class="room_pic">
                                        <img src="${contextPath}/hostDownload.do?host_id=${pension.host_id}&imageFileName=${pension.host_ro_img}" alt="pension_pic">
                                    </div>
                                    <a href="${contextPath}/room/roomInfo.do?host_id=${pension.host_id}">
	                                    <p class="pension_name">${pension.host_ro_name}</p>
	                                    <p>${pension.host_ro_addr}</p>
	                                    <p>${pension.host_ro_lowprice}</p>
                                    </a>
                                </div>
                                </c:forEach>
                            </div>
                
                            <%--인기 호텔--%>
                            <div class="best_hotel row">
                                <h2 class="mb-4">인기 호텔</h2>
                                <c:forEach var="hotel" items="${best_hotel}">
	                                <div class="room col">
	                                    <div class="room_pic">
	                                        <img src="${contextPath}/hostDownload.do?host_id=${hotel.host_id}&imageFileName=${hotel.host_ro_img}" alt="hotel_pic">
	                                    </div>
                                		<a href="${contextPath}/room/roomInfo.do?host_id=${hotel.host_id}">
	                                    	<p class="hotel_name">${hotel.host_ro_name}</p>
		                                    <p>${hotel.host_ro_addr}</p>
		                                    <p>${hotel.host_ro_lowprice}</p>
                                		</a>
	                                </div>
                                </c:forEach>
                            </div>
                
                            <%--인기 캠핑장--%>
                            <div class="best_camping row">
                                <h2 class="mb-4">인기 캠핑장</h2>
                                <c:forEach var="camping" items="${best_camping}">
                                <div class="room col">
                                    <div class="room_pic">
                                        <img src="${contextPath}/hostDownload.do?host_id=${camping.host_id}&imageFileName=${camping.host_ro_img}" alt="camping_pic">
                                    </div>
                                    <a href="${contextPath}/room/roomInfo.do?host_id=${camping.host_id}">
	                                    <p class="hotel_name">${camping.host_ro_name}</p>	
	                                    <p>${camping.host_ro_addr}</p>
	                                    <p>${camping.host_ro_lowprice}</p>
                                    </a>
                                </div>
                                </c:forEach>
                            </div>
                
                            <%--인기 한옥--%>
                            <div class="best_hanok row">
                                <h2 class="mb-4">인기 한옥</h2>
                                <c:forEach var="hanok" items="${best_hanok}">
                                <div class="room col">
                                    <div class="room_pic">
                                        <img src="${contextPath}/hostDownload.do?host_id=${hanok.host_id}&imageFileName=${hanok.host_ro_img}" alt="hanok_pic">
                                    </div>
                                    <a href="${contextPath}/room/roomInfo.do?host_id=${hanok.host_id}">
	                                    <p class="pension_name">${hanok.host_ro_name}</p>
	                                    <p>${hanok.host_ro_addr}</p>
	                                    <p>${hanok.host_ro_lowprice}</p>
                                    </a>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <%-- // best_hotel --%>

                    <%-- 좌측 공연,축제   우측 인기차트?(수정)--%>
                    <div class="row g-5" id="un_left">
                        <div class="col-md-8">
                            <%-- 공연,전시 영역 --%>
                            <h5 class="pb-4 mb-4 border-bottom">공연 / 전시</h5>
                            <article class="blog-post">
                                <div class="col-12" >
                                    <div id="exhibition" >
                                        <div class="exhibition_wrap row">
                                            <a class="exhibition col-md-4" href="${contextPath}/festival.html">
                                                <div class="exhibition_pic">
                                                    <img src="${contextPath}/images/Home/exhibition/Jeonju Crazy Festival.jpg" alt="exhibition_pic">
                                                </div>
                                                <p class="exhibition_name">[공연]미리 만나는<br>전주 미:친 축제』</p>
                                                <p>2022-10-25 ~ 2022-10-29</p>
                                            </a>
                                            <a class="exhibition col-md-4" href="${contextPath}/festival.html">
                                                <div class="exhibition_pic">
                                                    <img src="${contextPath}/images/Home/exhibition/traditional playwright.jpg" alt="exhibition_pic">
                                                </div>
                                                <p class="exhibition_name">[공연]전라감영<br>전통놀이술사</p>
                                                <p>2022-10-01 ~ 2022-10-30</p>
                                            </a>
                                            <a class="exhibition col-md-4" href="${contextPath}/festival.html">
                                                <div class="exhibition_pic">
                                                    <img src="${contextPath}/images/Home/exhibition/Here we are, here we are.jpg" alt="exhibition_pic">
                                                </div>
                                                <p class="exhibition_name">전라감영&lt;연극공연&gt;<br>우리,여기,잇다</p>
                                                <p>2022-10-09 ~ 2022-10-10</p>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <%-- // 공연,전시 영역 --%>

                            <%-- 축제 영역 --%>
                            <h5 class="pb-4 mb-4 border-bottom">축제</h5>
                            <article class="blog-post">
                                <div class="col-12" >
                                    <div id="festival" >
                                        <div class="festival_wrap row">
                                            <a class="festival col-md-4" href="${contextPath}/festival.html">
                                                <div class="festival1_pic">
                                                    <img src="${contextPath}/images/Home/festival/Jeonju International Film Festival.jpg" alt="festival_pic">
                                                </div>
                                                <p class="festival_name">전주국제영화제</p>
                                                <p>매년 4월 ~ 5월경</p>
                                            </a>
                                            <a class="festival col-md-4" href="${contextPath}/festival.html">
                                                <div class="festival1_pic">
                                                    <img src="${contextPath}/images/Home/festival/Jeonju Bibimbap Festival.jpg" alt="festival_pic">
                                                </div>
                                                <p class="festival_name">전주비빔밥축제</p>
                                                <p>매년 10월경</p>
                                            </a>
                                            <a class="festival col-md-4" href="${contextPath}/festival.html">
                                                <div class="festival1_pic">
                                                    <img src="${contextPath}/images/Home/festival/Jeonju Hanji Culture Festival.jpg" alt="festival_pic">
                                                </div>
                                                <p class="festival_name">전주한지문화축제</p>
                                                <p>매년 5월경</p>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <%-- // 축제 영역 --%>
                        </div>
                        <%-- 우측 인기차트? 영역 --%>
                        <div class="col-md-4">
                            <div id="event_wrap">
                                <div class="mb-3 rounded" id="bt_event">
                                    <h5 class="pb-4 mb-4 border-bottom">이벤트</h5>
                                    <img src="${contextPath}/images/Home/event/event1.png" alt="event">
                                    <%-- <h5><span class="fa fa-thumbs-up"></span> 인기! 관광명소</h5>
                                    <p>전주 한옥마을</p>
                                    <p>경기전</p>
                                    <p>전주 전동성당</p>
                                    <p>오목대 이목대</p>
                                    <p>남부시장</p> --%>
                                </div>
                                <div class="mb-3">
                                    <img src="${contextPath}/images/Home/event/event2.png" alt="event">
                                   <%-- <h5><span class="fa fa-utensils"></span> 추천! 인기맛집</h5>
                                    <p>연식당</p>
                                    <p>큰가마하누소</p>
                                    <p>한국집</p>
                                    <p>교동석갈비</p>
                                    <p>고궁</p> --%>
                                </div>
                                <div class="mb-3">
                                    <img src="${contextPath}/images/Home/event/event3.png" alt="event">
                                   <%-- <h5>즐길거리 & 랜드마크</h5>
                                    <p>전주 한옥마을</p>
                                    <p>학인당</p>
                                    <p>자만 벽화마을</p>
                                    <p>전주소리문화관</p>
                                    <p>전주한옥생활체험관</p> --%>
                                </div>
                                <div class="mb-3">
                                    <img src="${contextPath}/images/Home/event/event4.png" alt="event">
                                </div>
                            </div>
                        </div>
                        <%-- // 우측 인기차트? 영역 --%>
                    </div>
                    <%-- // 좌측 공연,축제   우측 인기차트?(수정)--%>
                </div>
            </main>
        </div>
        <%-- // contents --%>
        
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
    <%-- // wraper --%>
    <script src="${contextPath}/js/stay/datepicker.js"></script>
</body>
</html>