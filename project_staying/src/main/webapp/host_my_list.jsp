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
    <title>운영 숙소 정보</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    
    <%-- 부트스트랩 input태그 --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <%-- 공통 css --%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/common.css">
    <%-- 운영 숙소 정보 css --%>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/business/business_list.css">
    <%-- 객실관리, 리뷰 --%>
    <script src="${contextPath}/js/stay/jquery-3.6.0.min.js"></script>
    <script src="${contextPath}/js/business/business_list.js"></script>

    <script src="${contextPath}/js/stay/view_star.js"></script>
    <%-- 아이콘 --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<body>
    <%--건너뛰기 링크--%>
    <a id="skipNav" href="#contentsArea">본문 바로가기</a>
    <%--건너뛰기 링크 종료--%>

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
                    <a href="${contextPath}/room//my_room_info.do">내 사업장</a>
                    <a class="btn_login" href="${contextPath}/member/logout.do">로그아웃</a>
                </nav>
            </div>
        </header>
        <%-- // header 영역 --%>

        <%-- contents --%>
        <div class="container">
            <main>
                <div class="row">
                    <%-- background_image--%>
                    <div class="col-12">
                            <h2>${host_info.host_ro_name}</h2>
                        <div id="main_img">
                            <img src="${contextPath}/hostDownload.do?host_id=${host_info.host_id}&imageFileName=${host_info.host_ro_img}" alt="business_info_bg_pic">
                        </div>
                    </div>
                    <%-- // background_image --%>

                    <%-- room management, review --%>
                    <div class="col-12">
                        <div id="manage_review_tab">
                            <div class="container text-center">
                                <div class="row row-cols-auto">
                                    <div class="col select select1" id="room_mg">객실관리</div>
                                    <div class="col select select2" id="review">리뷰</div>
                                </div>
                                <hr>
                            </div>
                        </div>
                    </div>
                    <%-- // room management, review --%>

                    <%-- romm add button --%>
                    <div class="col-12">
                        <div id="select_wrap">
                            <div class="select_btn" id="tete">
                                <button type="button" class="btn btn-outline-danger btn-sm" id="del_click">삭제하기</button>
                                <button type="button" class="btn btn-outline-secondary btn-sm" id="canccle_click">취소하기</button>
                                <button type="button" class="btn btn-outline-primary btn-sm" id="add_click" onclick="location.href='${contextPath}/room/add_room_form.do' ">객실 추가</button>
                                <button type="button" class="btn btn-outline-danger btn-sm" id="room_del_click">객실 삭제</button>
                            </div>
                        </div>
                    </div>
                    <%-- // romm add button --%>

                    <%-- room info --%>
                    <div class="col-12" id="management">
                        <form action="${contextPath}/room/delRoom.do" name="frm_remove_room">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">객실 사진</th>
                                    <th scope="col">객실 타입</th>
                                    <th scope="col">최대 투숙 가능 인원</th>
                                    <th scope="col">가격</th>
                                    <th scope="col"><span class="del_click">객실 삭제</span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="room" items="${room_list}">
                                <tr class="hei">
                                    <th scope="row"><img class="room_pic" src="${contextPath}/roomDownload.do?room_no=${room.room_no}&imageFileName=${room.room_img}"> </th>
                                    <td>${room.room_name}</td>
                                    <td>${room.room_people} 명</td>
                                    <td>${room.room_price} 원</td>
                                    <td><input class="form-check-input del_click" type="checkbox" value="${room.room_no}" name="room_no" id="flexCheckDefault"></td>
                                </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            <input type="submit" id="btn_remove_room" value="삭제하기">
                        </form>
                    
                    <%-- // room info --%>
                    <%-- <div class="col-12"> --%>
                                <div id="review_wrap">
                        <c:forEach var="review" items="${review_list}" varStatus="status">
                            <c:choose>
                                <c:when test="${review.rv_type eq 0}"> 
                                    <div class="review_content_btn">
                                        <div class="id_date">
                                            <p class="id_date_1line"><span class="id">${review.id}</span>
                                                <span class="rating">
                                                    <span class="view_star">${review.rv_rate/10}</span>
                                                </span>
                                            </p>
                                            <div class="id_date_2line">
                                                <span><fmt:formatDate value="${review.rv_writeDate}"/></span>
                                                <div class="review_content">
                                                    <p>${review.rv_content}</p>
                                                    <c:if test="${!empty review.rv_img}">
                                                        <img src="${contextPath}/reviewDownload.do?rv_no=${review.rv_no}&rv_img=${review.rv_img}" alt="${review.rv_no}번 글 이미지">
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="id_date_btn">
                                            <button type="button" value="${status.index}" class="btn btn-outline-info btn-sm btn_owner_review">답글 작성</button>
                                        </div>
                                    </div>
                                    <div class="owner">
                                        <p>사장님 답글</p>
                                        <form action="${contextPath}/review/add_reply.do" name="frm_business_reply">
                                            <input type="hidden" name="rv_no" value="${review.rv_no}">
                                            <input type="hidden" name="book_no" value="${review.book_no}">

                                            <textarea name="reply_content" name="reply_content" id="owner_reply" cols="30" rows="10" maxlength="300"></textarea>
                                            <p class="owner_btn"><button type="submit" class="btn btn-outline-info btn-sm">답글 저장</button></p>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${review.rv_type eq 1}"> 
                                    <div class="review_content_btn reply">
                                            <p class="id_date_1line"><span class="id">내 답글</span></p>
                                            <div class="id_date_2line">
                                                <div class="review_content">
                                                    <p>${review.rv_content}</p>
                                            </div>
                                        </div>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        </div>
                    </div>
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
</body>
</html>