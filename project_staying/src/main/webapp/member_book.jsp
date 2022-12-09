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
    <title>예약 정보</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    <!-- CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <!--아이콘-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />    
    
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${contextPath}/js/member/member_book.js"></script>

    <%-- 이미지 선택시 미리보기 --%>
    <script src="${contextPath}/js/stay/readimage.js"></script>

    <link rel="stylesheet" href="${contextPath}/css/stay/common.css">
    <link rel="stylesheet" href="${contextPath}/css/member/member_common.css">
    <link rel="stylesheet" href="${contextPath}/css/member/member_book.css">
    <link rel="stylesheet" href="${contextPath}/css/member/star.css">

        <%-- 알림메세지가 존재할 경우 --%>
    <c:if test="${not empty msg}">
    <script>
        window.onload = function(){
            alert('${msg}');
        }
		
    </script>
    </c:if>
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
                    <h2>예약 내역 조회</h2>
                            <c:choose>
                                <c:when test="${not empty book_list}">
                                    <c:forEach var="book" items="${book_list}" varStatus="status">
                                    <section class="book_room_area">
                                    <div class="book_room_image">
                                        <img src="${contextPath}/hostDownload.do?host_id=${book.host_id}&imageFileName=${book.host_ro_img}" alt="예약한 방 사진">
                                    </div>
                                    <div class="book_room_content">
                                            <div class="book_room_l">
                                                <input type="hidden" name="room_book_num" value="${room.book_no}">
                                                <p class="book_room_title">${book.host_ro_name}</p>
                                                <p class="book_room_name">${book.room_name}</p>
                                                <table class="book_room_checktime">
                                                    <tr>
                                                        <th>체크인</th><th>체크아웃</th>
                                                    </tr>
                                                    <input type="hidden" value="${book.check_out}" id="check_out_hidden${status.index}">
                                                    <tr>
                                                        <td>
                                                            <time datetime="${book.check_in}">${book.check_in_str}</time>
                                                        </td>
                                                        <td>
                                                            <time datetime="${book.check_out }">${book.check_out_str}</time>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <c:if test="${book.book_state eq 'before'}"> 
                                            <div class="review_cancle_area">
                                                <a type="button" onclick="fn_review_active(${book.host_id}, ${book.book_no},${status.index})">리뷰작성</a>
                                                <a href="${contextPath}/booking/del_book.do?book_no=${book.book_no}&cki=${book.check_in}&cko=${book.check_out}&room_no=${book.room_no}">취소하기</a>
                                            </div>
                                            </c:if>
                                            <c:if test="${book.book_state eq 'after'}">
                                            <div class="review_cancle_area">
                                                <a type="button" onclick="fn_alert_review()">리뷰작성</a>
                                                <a href="${contextPath}/booking/del_book.do?book_no=${book.book_no}&cki=${book.check_in}&cko=${book.check_out}&room_no=${book.room_no}">취소하기</a>
                                            </div>
                                            </c:if>
                                    </div>
                                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <h1 class="modal-title fs-5" id="exampleModalLabel">리뷰 작성</h1>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <form action="${contextPath}/review/add_review.do" name="frm_review" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="host_id" id="host_id_hidden">
                                    <input type="hidden" name="book_no" id="book_no_hidden">
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <input name="book_num" type="hidden" value="">
                                            <p class="star">평점 </p>
                                            <div class="rate-area">
                                                <input type="radio" id="5-star" name="review_star" value="100"> 
                                                <label for="5-star"><span class="fa fa-star"></span></label>
                                                <input type="radio" id="4-half-star" name="review_star" value="90"> 
                                                <label for="4-half-star"><span class="half fa fa-star-half"></span></label>
                                                
                                                <input type="radio" id="4-star" name="review_star" value="80">
                                                <label for="4-star"><span class="fa fa-star"></span></label>
                                                <input type="radio" id="3-half-star" name="review_star" value="70">
                                                <label for="3-half-star"><span class="half fa fa-star-half"></span></label>
                                                
                                                <input type="radio" id="3-star" name="review_star" value="60">
                                                <label for="3-star"><span class="fa fa-star"></span></label>
                                                <input type="radio" id="2-half-star" name="review_star" value="50">
                                                <label for="2-half-star"><span class="half fa fa-star-half"></span></label>
                                                
                                                <input type="radio" id="2-star" name="review_star" value="40">
                                                <label for="2-star"><span class="fa fa-star"></span></label>
                                                <input type="radio" id="1-half-star" name="review_star" value="30">
                                                <label for="1-half-star"><span class="half fa fa-star-half"></span></label>
                                                
                                                <input type="radio" id="1-star" name="review_star" value="20" aria-required="true">
                                                <label for="1-star"><span class="fa fa-star"></span></label>
                                                <input type="radio" id="0-half-star" name="review_star" value="10" aria-required="true">
                                                <label for="0-half-star"><span class="half fa fa-star-half"></span></label>
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <textarea class="form-control" name="review_content" id="message-text" maxlength="300" placeholder="10자 이상 작성해주세요"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <img src="" id="preview" alt="#">
                                            <input type="file" name="review_image" class="form-control" onchange="readImage(this)">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                        <input type="button" class="btn btn-primary" value="리뷰등록" onclick="fn_review_form_submit()">
                                    </div>
                                </form>
                              </div>
                            </div> 
                          </div>

                    </section>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                <section class="book_room_area">
                                    <div class="no_book_room">
                                        <div class="no_room_content">
                                            <p>예약한 숙소가 없습니다</p>
                                            <a href="${contextPath}/host/main_page.do">예약하러가기</a>
                                        </div>
                                    </div>
                                </section>
                                </c:otherwise>
                            </c:choose>

                        
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