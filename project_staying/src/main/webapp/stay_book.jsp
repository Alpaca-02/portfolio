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
    <title>예약자,결제 정보</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    <script>
        let host_ro_name = '${host_info.host_ro_name}';
        let full_price = ${full_price};
        let user_name = '${user_info.name}';
        let user_phone = '${user_info.phone}'
    </script>


    <!--공통 css-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/common.css">
    <!--결제페이지 css-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/stay/stay_book.css">
    <!-- 결제 페이지 js  빈칸시 alert -->
    <script src="${contextPath}/js/stay/stay_book.js"></script>
    <!-- 아이콘 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <!-- 부트스트랩 input태그, 약관 동의창 css, js -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

    <%-- 아임포트 스크립트--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

</head>
<body>
    <!--건너뛰기 링크-->
    <a id="skipNav" href="#contents_area">본문 바로가기</a>
    <!--건너뛰기 링크 종료-->

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
                    <a href="${contextPath}/member/memberInfo.do">마이페이지</a>
                    <a class="btn_login" href="${contextPath}/login.jsp">로그인</a>
                </nav>
            </div>
        </header>
        <%-- // header 영역 --%>

        <div id="contents_area" name="test">
            <div id="info">
                <!-- 좌측 예약자 정보 란 -->
                <div class="reserve_info">
                    <h2>예약자 정보</h2>
                    <form action="login.jsp" method="post" id="login_form" value="${user_info.name}" name="login_form">
                        <div class="name">
                            <label for="name">이름</label>
                            <i class="far fa-user"></i>
                            <input type="name" id="name" name="name" value="${user_info.name}" required autofocus>
                        </div>
                        <div class="tel">
                            <label for="tel">전화번호<span>"-"를 제외한 연락처를 입력해주세요.</span></label>
                            <i class="fa fa-phone"></i>
                            <input type="tel" id="tel" name="phone" value="${user_info.phone}" required readonly>
                        </div>
                    </form>
                </div>
                <!-- 좌측 결제 정보 란 -->
                <div class="payment_info">
                    <h2>결제 정보</h2>
                    <div class="form_check_wrap">
                        <div class="form-check">
                            <input type="radio" id="flexRadioDefault1" class="form-check-input" name="flexRadioDefault" value="1" checked>
                            <label class="form-check-label" for="flexRadioDefault1">카카오 페이</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" id="flexRadioDefault2" class="form-check-input" name="flexRadioDefault" value="2">
                            <label class="form-check-label" for="flexRadioDefault2">신용카드</label>
                        </div>
                    </div>
                <!--    <div class="payment">
                        <div class="pay1">
                            <p>
                                <label>입금 은행</label>
                                <select class="form-select form-select-sm" aria-label=".form-select-sm example">
                                    <option selected>은행 선택</option>
                                    <option value="국민은행">국민은행</option>
                                    <option value="우리은행">우리은행</option>
                                    <option value="신한은행">신한은행</option>
                                  </select>
                            </p>
                            <p>
                                <label>입금자명</label>
                                <input type="text" required>
                            </p>
                            <p>입금계좌 : 000000-00-000000</p>
                        </div>
                        <div class="pay2">
                            <p>
                                <label>카드 선택</label>
                                <select class="form-select form-select-sm" aria-label=".form-select-sm example">
                                    <option selected>카드 선택</option>
                                    <option value="국민은행">국민은행</option>
                                    <option value="우리은행">우리은행</option>
                                    <option value="신한은행">신한은행</option>
                                  </select>
                            </p>
                        </div>
                    </div>  -->
                </div>
           <!-- <div class="agree">
                    <div class="form-check">
                        <input type="radio" id="agree" class="form-check-input" name="flexRadioDefault1">
                        <label class="form-check-label" for="agree">약관 동의합니다. </label><span onclick="show_content();"> 눌러서 내용보기</sapn>
                    </div>
                    <div id="agr_content" class="agree_content">
                        제1조(목적)
                        본 회원약관은 OOOO-업체명(이하 '갑'라 한다)이 운영하는 인터넷관련 서비스(이하 '서비스'라 한다)를 이용함에 있어 관리자와 이용자(이하 '회원'라 한다)의 권리, 의무 및 책임사항을 규정함을 목적으로 한다.

                        제2조 (약관의 효력)
                        1.본 약관은 '갑'에 회원 가입 시 회원들에게 통지함으로써 효력을 발생합니다.
                        2.'갑'은 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.
                        3.약관의 변경사항 및 내용은 '갑'의 홈페이지에 게시하는 방법으로 공시합니다.

                        제3조 (약관 이외의 준칙)
                        이 약관에 명시되지 않은 사항이 전기 통신 기본법, 전기통신 사업법, 기타 관련 법령에 규정되어 있는 경우 그 규정에 따릅니다.

                        제4조 (이용계약의 체결) 회원 가입 시 회원 약관 밑에 있는 동의버튼을 누르면 약관에 동의하여 이 계약이 체결된 것으로 간주한다.

                        제5조 (용어의 정의)
                        이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
                        1.회원: '갑'과 서비스 이용에 관한 계약을 체결한 자
                        2.아이디(ID): 회원 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 '갑'이 승인하는 문자와 숫자의 조합
                        3.비밀번호: 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자와 숫자의 조합

                        제6조 (이용신청)
                        1.회원 가입은 온라인으로 가입신청 양식에 기록하여 '갑'에 제출함으로써 이용신청을 할 수 있습니다.
                        2.가입희망 회원은 반드시 자신의 본명 및 주민등록번호로 이용신청을 하여야 하며, 1개의 ID만 신청을 할 수 있습니다.

                        제7조 (회원가입의 승낙)
                        '갑'의 회원 가입 신청 양식에 가입 희망 회원이 인터넷으로 제6조와 같이 신청하면 '갑'은 바로 가입을 승인하여 서비스를 이용할 수 있다.

                        제8조(회원가입 신청거절 및 강제 탈퇴)
                        1. '갑'은 타인의 명의나 주민등록번호를 도용하여 회원가입신청을 할 경우 회원가입신청을 거절할 수 있다.
                        2. 회원가입신청이 승인이 된 후에도 허위사실의 기재가 발각되거나 '갑'의 명예를 회손시키거나 음란물이나 불건전한 내용을 게재할 경우 회원의 자격을 강제 탈퇴시킬 수 있다.
                    </div>
                </div> -->

                <!-- 약관 동의창 -->
                <div class="clause_area">
                    <div class="accordion close" id="accordionExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                    <input type="checkbox" name="clause_check" id="clause_check" class="clause_check">
                                    <label for="clause_check" class="clause_check_label">약관동의</label>
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
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
                    </div>
                </div>
                <div class="room_pay">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="payCheck()">결제하기</button>
                </div>
            </div>
            
            <!-- 우측 숙소 정보 란 -->
            <div id="room_info">
                <div class="room_info_wrap">
                    <div class="room_info_pic">
                        <img src="${contextPath}/hostDownload.do?host_id=${host_info.host_id}&imageFileName=${host_info.host_ro_img}" alt="객실사진">
                    </div>
                    <div class="room_info_data">
                        <p class="room_name">${host_info.host_ro_name}</p>
                        <p class="room_type">${room_info.room_name}</p>
                        <p class="room_in">체크인</p>
                        <p class="date">${cki_date}   15시</p>
                        <p class="room_out">체크아웃</p>
                        <p class="date">${cko_date}   12시</p>
                        <p class="total">${day}박  ${full_price}원</p>
                    </div>
                </div>
            </div>
        </div>
        	<%-- 예약 정보를 보내기 위한 hidden form --%>
            <form name="book_hidden" method="post" action="${contextPath}/booking/booking_room.do">
                <input type="hidden" value="${user_info.id}" name="log_id">
                <input type="hidden" value="${host_info.host_ro_img}" name="host_ro_img">
                <input type="hidden" value="${host_info.host_ro_name}" name="host_ro_name">

                <input type="hidden" value="${room_info.room_name}" name="room_name">
                <input type="hidden" value="${room_info.room_no}" name="room_no">
                <input type="hidden" value="${cki_date_ori}" name="check_in">
                <input type="hidden" value="${cko_date_ori}" name="check_out">
                <input type="submit" value="테스트 버튼">
            </form>
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