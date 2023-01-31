<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.security.SecureRandom" %>

<c:set var="path" value="${pageContext.request.contextPath}" />
<% SecureRandom random=new SecureRandom(); String state=new BigInteger(130, random).toString(); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>로그인</title>
    <link href="${path}/resources/css/index.css" rel="stylesheet" type="text/css">
    <!-- 구글 폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class="body">
    <section>
        <img src="${path}/resources/img/great.gif" class="mainimg">
    </section>
    <div class="modal">
        <div id="modal_content">
            <h1 id='login'>로그인</h1>
            <div class="form">
                <div class="form_title">아이디</div>
                <div class="form_content"><input type="text" placeholder="아이디" id="familyid"><i class="fas fa-users"></i></div>

            </div>
            <div class="form">
                <div class="form_title">패스워드</div>
                <div class="form_content"><input type="password" placeholder="패스워드" id="familypw"><i class="fas fa-key"></i></div>

            </div>
            <div>
                <input type="button" value="로그인" class="loginbtn" onclick="checkid()">
            </div>
            <div class="apilogin">
                <a
                    href="https://kauth.kakao.com/oauth/authorize?client_id=e726d6b4b8543d40dbf1c634fd9b16e1&redirect_uri=http://localhost:8080/login/kakao&response_type=code" id=''>
                    <img src="${path}/resources/img/kakao_login_medium_wide.png">
                </a>
            </div>
            <div class="apilogin">
                <a
                    href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=fZ9Vtpt0HVjxbL3T7lFP&redirect_uri=http://greenrestauranteats.com:8484/login/naver&state=<%=state%>">
                    <img src="${path}/resources/img/naver.png" class="naver">
                </a>
            </div>
            <div class="id_pwd">
                <a href="/register">회원가입</a>
                <a href="/login/idpwdcheck">ID/PWD찾기</a>
            </div>
        </div>
    </div>
    <div class="loading-container">
        <div class="loading"></div>
        <div id="loading-text">loading</div>
    </div>
    <script src="${path}/resources/js/index.js">
    </script>
</body>

</html>