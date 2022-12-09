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
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>검색 목록</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">

    <%--양 방향 슬라이드바--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" type="text/css" media="all" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/stay/price_condition.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/price_condition.css"/>

    <%--아이콘--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    
    <%--달력--%>
    <link rel="stylesheet" href="${contextPath}/css/stay/datepicker.min.css">
    <script src="${contextPath}/js/stay/datepicker.min.js"></script>
    <script src="${contextPath}/js/stay/datepicker.ko.js"></script>
    <script defer src="${contextPath}/js/stay/datepicker.js"></script>

    <%--지도--%>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0eb77c4d3ba3735dfe84007620b700bd&libraries=services"></script>
    <script>
        let map_name = new Array();
        let map_lat = new Array();
        let map_long = new Array();

        <%-- 업체 이름과 업체 위도, 경도를 배열에 담음--%>
	    <c:forEach var="host" items="${hostList}">
            map_name.push("${host.host_ro_name}");
            map_lat.push(${host.host_ro_latitude});
            map_long.push(${host.host_ro_longitude});
	    </c:forEach>
    </script>
    
    <script src="${contextPath}/js/stay/stay_list_map.js"></script>

    <%--숙소 유형--%>
    <script src="${contextPath}/js/stay/search_common.js"></script>
    <script src="${contextPath}/js/stay/stay_list.js"></script>
    <script src="${contextPath}/js/stay/view_star.js"></script>



    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <link rel="stylesheet" href="${contextPath}/css/stay/search_common.css">
    <link rel="stylesheet" href="${contextPath}/css/stay/stay_list.css">
    
    <script>
    	$(document).ready(function () {
			$("#${room_kind}").trigger("click");
			
			$("input:radio[name ='stay_kind']:input[value='${room_kind}']").prop("checked",true).change();
			$("input:radio[name ='stay_kind']:input[value='${room_kind}']").prop("checked",true).trigger('change');
		})
    </script>

</head>
<body>
    <%--건너뛰기 링크--%>
    <a id="skipNav" href="#contentsArea">본문 바로가기</a>
    <%--건너뛰기 링크 종료--%>
    
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
    
    
    <%--wraper 시작--%>
    <div id="wrapper">
		
        <%--지도 모달--%>
        <div class="map_modal_wrap hidden">
            <div class="map_bg"></div>
            <div class="map_content">
                <button class="btn_map_close"><i class="fas fa-times"></i></button>
                <div id="map_main"></div>
            </div>
        </div>

        <%--메인 검색 영역--%>
        <form action="${contextPath}/host/search.do" name="frm_stay_search" class="stay_search_area">
            <div class="stay_search_area">
                <input type="hidden" name="min_price">
                <input type="hidden" name="max_price">
                <input type="hidden" name="grade_value">

                <label>
                    <span class="bed fa fa-bed" aria-hidden="true"></span>
                    <section  id="stay_select">
                        <h2 class="hidden">selectbox</h2>
                        <input type="hidden" name="room_kind" id="selected" value="none">
                        <button type="button" class="toggle_btn" name="toggle_btn" onclick="fn_show_ul()">
                            <span class="selected_option"></span>
                            <i class="drop fas fa-caret-down"></i>
                        </button>
                        <ul id="select_option" class="hide">
                            <li><button type="button" class="option_btn" value="pension" onclick="fn_option_select(this)">펜션</button></li>
                            <li><button type="button" class="option_btn" value="hotel" onclick="fn_option_select(this)">호텔</button></li>
                            <li><button type="button" class="option_btn" value="hanok" onclick="fn_option_select(this)">한옥</button></li>
                            <li><button type="button" class="option_btn" value="camping" onclick="fn_option_select(this)">캠핑장</button></li>
                        </ul>
                    </section>
                </label>
                <label>
                    <span class="cal far fa-calendar-alt"></span>
                    <input type="text" name="check_in" class="check_in" id="datepicker1" value="${check_in}" placeholder="체크인">
                </label>
                <label>
                    <span class="cal far fa-calendar-alt"></span>
                    <input type="text" name="check_out" class="check_out" id="datepicker2" value="${check_out}" placeholder="체크아웃">
                </label>
                <label>
                    <span class="users fa fa-users" aria-hidden="true"></span>
                    <input type="text" class="stay_person" name="stay_person" value="${person}" placeholder="인원수">
                </label>
                <label>
                    <input type="button" class="btn_stay_search" onclick="fn_search_submit()"><span class="saerch fa fa-search" aria-hidden="true"></span>
                </label>
            </div>
        </form>
        <%--컨텐츠 영역--%>
        <div id="contents_area">
            <%--조건, 지도 영역--%>
            <section class="conditions_map_area">
                <div class="map_area">
                	<%-- 지도버튼 클릭시 지도를 여는 함수에 업체명,위도,경도 리스트를 담아 실행 --%>
                    <button type="button" class="map" id="map" onclick='openModal(map_name, map_lat, map_long)'>
                        <img src="${contextPath}/images/stay/map.svg" alt="지도 버튼 배경 이미지">
                        <div>
                            <img src="${contextPath}/images/stay/map-pin-red.svg" alt="지도 마커 이미지">
                            <p>지도에 표시</p>
                        </div>
                    </button>
                </div>
                <div class="conditions_form_area">
                    <form action="${contextPath}/host/search.do" name="frm_stay_condition">
                        <div class="price_condition">
                            <h3>가격</h3>
                            <div class="price_range_block">
                                <div class="sliders_control">
                                    <input id="fromSlider" type="range" value="11000" min="10000" max="400000"/>
                                    <input id="toSlider" type="range" value="150000" min="10000" max="400000"/>
                                </div>
                                <div class="form_control">
                                    <div class="form_control_container">
                                        <input class="form_control_container__time__input" type="number" id="fromInput" value="11000" min="10000" max="400000"/>
                                    </div>
                                    <div class="form_control_container">
                                        <input class="form_control_container__time__input" type="number" id="toInput" value="150000" min="10000" max="400000"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="grade_condition">
                            <h3>평점</h3>
                            <p>
                                <input type="radio" name="grade_value" id="grade_4_up" value="80">
                                <label for="grade_4_up">
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-plus"></span>
                                </label>
                            </p>
                            <p>
                                <input type="radio" name="grade_value" id="grade_3_up" value="60">
                                <label for="grade_3_up">
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-plus"></span>
                                </label>
                            </p>
                            <p>
                                <input type="radio" name="grade_value" id="grade_2_up" value="40">
                                <label for="grade_2_up">
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-star"></span>
                                    <span class="fas fa-plus"></span>
                                </label>
                            </p>
                        </div>
                        <div class="stay_type_condition">
                            <h3>숙소 유형</h3>
                            <p>
                                <input type="radio" class="stay_kind" name="stay_kind" value="house" id="pension" onclick="stay_kind_search(this)">
                                <label for="house">펜션</label>
                            </p>
                            <p>
                                <input type="radio" class="stay_kind" name="stay_kind" value="hotel" id="hotel" onclick="stay_kind_search(this)">
                                <label for="hotel">호텔</label>
                            </p>
                            <p>
                                <input type="radio" class="stay_kind" name="stay_kind" value="hanok" id="hanok" onclick="stay_kind_search(this)">
                                <label for="hanok">한옥</label>
                            </p>
                            <p>
                                <input type="radio" class="stay_kind" name="stay_kind" value="camping" id="camping" onclick="stay_kind_search(this)">
                                <label for="camping">캠핑장</label>
                            </p>
                        </div>
                        <%--
                        <div class="stay_name_condition">
                            <h3>숙소명</h3>
                            <label for="input_stay_name">
                                <span class="fa fa-search" aria-hidden="true"></span>
                                <input type="text" name="input_stay_name" id="input_stay_name" placeholder="숙소명">
                            </label>
                        </div>
                        --%>
                        <input type="button" name="btn_conditions_search" value="검색하기"  id="btn_conditions_search" onclick="fn_add_form()">
                    </form>
                </div>
            </section>
            <%--숙소 목록 영역--%>
            <section class="stay_list_area">
                <c:forEach var="host" items="${hostList}">
                    <a class="stay_content" href="${contextPath}/room/roomInfo.do?host_id=${host.host_id}&host=${host}">
                        <div class="stay_image">
                            <img src="${contextPath}/hostDownload.do?host_id=${host.host_id}&imageFileName=${host.host_ro_img}" alt="">
                        </div>
                        <div class="stay_info">
                            <div class="stay_info_l">
                                <h2>${host.host_ro_name}</h2>
                                <p>${host.host_ro_content}</p>
                                <p class="view_star">${host.host_ro_rate/10}</p>
                                <p>1박 / ${host.host_ro_lowprice }원</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
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