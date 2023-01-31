<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.great.domain.ProfileVO" %>
<% ProfileVO profile=(ProfileVO)session.getAttribute("profile"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <link href="${path}/resources/css/outof.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <!-- 구글 폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>프로필 삭제</title>
</head>
<body>
    <header>
        <div class="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
        <div class="btn"><i class="fas fa-bars"></i></div>
    </header>
    <section>
        <div class="main_header">프로필 삭제</div>
        <div class="main_header_1">* 주의 *</div>
        <div class="main_content">프로필 삭제시 해당하는 프로필의 정보가 모두 삭제 됩니다</div>
        <button onclick="deleteProfile()">프로필 삭제</button>
    </section>
    <%@ include file="slide.jsp"%>
        <script>
    deleteProfile = () => {
    	location.href="/outofprofile/delete";
    }
    </script>
</body>
</html>