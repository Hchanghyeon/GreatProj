<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.great.domain.ProfileVO" %>
<% ProfileVO profile = (ProfileVO)session.getAttribute("profile"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- initial-scale ë¤ ë¬¸êµ¬ ì°ë©´ í¸ëí°ìì input í´ë¦­ì íë ìëê²í¨ -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scaleable=no,maximum-scale=1,width=device-width">
    <title>유통기한이 별로 남지 않은 리스트</title>
    <!-- fontawesome ìì´ì½Â -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- êµ¬ê¸í°í¸ -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/limitfoodlist.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <script src="https://kit.fontawesome.com/a0175a9a60.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
    <div class="btn"><i class="fas fa-bars"></i></div>
    <header>  <!-- ì ëª© -->
        <div class="title">
            <div class="title_text">식재료 리스트</div>
        </div>
    </header>

    <section>
        <article>
            <ul class="list1">
                <hr>
                <c:set var="path" value="${pageContext.request.contextPath}"/>
                <c:forEach items="${limitfoodlist}" var="limitfoodlist">
                <div>
                    <li>
                    	<div id="foodimg">
                    		<img src="${limitfoodlist.img}">
                    	</div>
                    	<div id="foodcls">
                    		<a href="">${limitfoodlist.cls}</a>
                    	</div>
                    	<div class="foodlimit">
                    		<b class="foodlimitname">${limitfoodlist.date}</b>
                    	</div>
                    	<div id="foodDate">
                    		<b>${limitfoodlist.endDate}</b>
                    	</div>
                    </li><hr>
                </div>
				</c:forEach>
            </ul>
            <ul class="list2">
                <li><a href="/foodlist">식재료 리스트로 가기</a>
                    <i class="fas fa-greater-than"></i></li>
                <li><a href="">알레르기 식재료 리스트 </a>
                    <i class="fas fa-greater-than"></i></li>
            </ul>
        </article>
    </section>
     <%@ include file="slide.jsp" %>
</body>
<script>
var x = document.getElementsByClassName('foodlimitname');
for(var i=0; i < x.length; i++){
	var limitdate = x[i].innerHTML;
	if(limitdate < 0){
		
		x[i].innerHTML = Math.abs(Number(limitdate)) + "일 남음";
		x[i].style.color="orange";
	}else {
		x[i].innerHTML = limitdate + "일 지남";
		x[i].style.color="red";
	}
}
</script>
</html> 