<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.great.domain.ProfileVO"  %>
<% ProfileVO profile = (ProfileVO)session.getAttribute("profile"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>상세 레시피</title>
    <!-- fontawesome 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/recipeinfo.css" rel="stylesheet">
    <link href="${path}/resources/css/slide2.css" rel="stylesheet">
        <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body id="body">
	<div>
	<div class="logo" id="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
    <div class="logo2" id="logo2"><img src="${path}/resources/img/great.gif"></div>
    <div class="btn"><i class="fas fa-bars" id="hambtn"></i></div>
    <div class="main">
        <div class="notuse"></div>
        <div class="use">
            <header>
                ${recipe_basic.sumry}
            </header>
            <section>
                <div class="recipemain">
                    <h2>${recipe_basic.recipe_nm_ko}</h2>
                    <div>
                        <div class="ingredient_explain">
                            <div><img src="${path}/resources/img/newbie.png"><span>${recipe_basic.level_nm}</span></div>
                            <div><img src="${path}/resources/img/clock.png"><span>${recipe_basic.cooking_time}</span></div>
                            <div><img src="${path}/resources/img/group.png"><span>${recipe_basic.qnt}</span></div>
                        </div>
                    </div>
                    <div>
                        <div class="ingredient_header">
                            <div>재료</div>
                        </div>
                        <div class="ingredient_main">
                        <c:set var="path" value="${pageContext.request.contextPath}"/>
               			 <c:forEach items="${recipe_ingre}" var="ingre">
       						<div>${ingre.irdnt_nm} ${ingre.irdnt_cpcty}</div>
						</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="recipeimg">
                    <div>${recipe_basic.nation_nm}</div>
                    <img src="${path}/resources/img/basic_img/${recipe_basic.img_path}">
                </div>
            </section>
            <article>
                <div class="process">
                    <div class="process_header_before">
                        <div class="process_header">
                            <div>조리사진</div>
                        </div>
                    </div>
                    <div id="process_img" class="process_img">
                    	<c:forEach items="${recipe_pro}" var="pro" varStatus="status">
                    		<c:choose>
                    			<c:when test="${pro.stre_step_image_path eq 'None'}">
                    			</c:when>
                    			<c:otherwise>
                    			<div class="step">
                    			   	<img class="imgclass" src="${path}/resources/img/process_img/${pro.stre_step_image_path}">
                        		</div>
                    			</c:otherwise>
                    		</c:choose>
						</c:forEach>
                        
                    </div>
                    <div class="process_header_before">
                        <div class="process_header">
                            <div>조리방법</div>
                        </div>
                    </div>
                    <div class="process_main">
                        <c:forEach items="${recipe_pro}" var="pro" varStatus="status">
       						<div class="step">
                            	<div class="step_header">step ${status.count}</div>
                            	<div class="step_content">${pro.cooking_dc}</div>
                            	<c:choose>
                    			<c:when test="${pro.step_tip eq 'None'}">
                    			</c:when>
                    			<c:otherwise>
                    				<div class="step_tipheader"><div>TIP!</div></div>
                            		<div class="step_tipcontent">${pro.step_tip}</div>
                    			</c:otherwise>
                    		</c:choose>
                            	
                        	</div>
						</c:forEach>
                    </div>
                </div>
            </article>
        </div>
        <div class="notuse"></div>
    </div>
    </div>
    <%@ include file="slide.jsp" %>
    <script src="${path}/resources/js/recipeinfo.js"></script>
</body>
</html>