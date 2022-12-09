<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 페이지 주소를 가져와 변수에 담음 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <title>네이버로그인</title>
    <link rel="shortcut icon" href="${contextPath}/images/Home/logo/staying.ico">
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

    <style>
    form{
        display:none;
    }
    </style>
  </head>
  <body>
  <form action="${contextPath}/member/naver_login_req" id="naver_login">
  <input type="text" id="email" name="email">
  <input type="text" id="name" name="name">
  <input type="text" id="phone" name="phone">
  </form>


  <script type="text/javascript">
    var naver_id_login = new naver_id_login("", "http://192.168.4.150:8090/staying/naver_callback.jsp");
    // 접근 토큰 값 출력
    // alert(naver_id_login.oauthParams.access_token);

    // 네이버 사용자 프로필 조회
    naver_id_login.get_naver_userprofile("naverSignInCallback()");
    // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
    function naverSignInCallback() {  // 콜백된 이후 사용될 내용
      let email = naver_id_login.getProfileData('email');
      let name = naver_id_login.getProfileData('name');
      let phone = naver_id_login.getProfileData('mobile');
      

      $('#email').val(email);
      $('#name').val(name);
      $('#phone').val(phone);
      $('#naver_login').submit();



    }
  </script>

    </body>


</html>