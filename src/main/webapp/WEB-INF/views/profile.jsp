
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.great.domain.FamilyVO"  %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<% FamilyVO user = (FamilyVO)session.getAttribute("user"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>메인 화면</title>
    <!-- fontawesome 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/profile.css" rel="stylesheet"> 
        <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
</head>
<body>
    <header>
        <img src="${path}/resources/img/GreatLogo.png">
        <a onclick="loading('/profilemain/logout')">로그아웃</a>
    </header>
    <div class="mainsection">
        <section>
            <header>
                <div> <%= user.getNickname() %>님 가족을 환영합니다</div>
            </header>
            <article>
            <c:forEach items="${profileList}" var="profile">
                <div>
                    <a onclick="loading('/main/profile?username=${profile.username}&path=${profile.path}&id=${profile.id}')"><img src="${path}${profile.path}"><div>${profile.username}</div></a>
                </div>
			</c:forEach>
            </article>
            <footer>
                <div class="button">
                    <a onclick="loading('/register/profile')"><img src="${path}/resources/img/plus.png"></a>
                </div>
            </footer>
        </section>
    	</div>
            <div class="loading-container">
   			 <div class="loading"></div>
    		<div id="loading-text">loading</div>
		</div>
    <script>
    function loading(go){
    	
    	var loader = $(".loading-container"); 
    	var content =  $(".loading"); 
    	
    	console.log(loader);
    	console.log(content);
    	loader.css("display","block");
    	content.css("display","block");

    	window.location.href=go;
    }
    </script>
</body>
</html>