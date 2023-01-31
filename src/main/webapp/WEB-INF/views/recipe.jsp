<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.great.domain.ProfileVO"  %>
<% ProfileVO profile = (ProfileVO)session.getAttribute("profile"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- initial-scale 뒤 문구 쓰면 핸드폰에서 input 클릭시 확대 안되게함 -->
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0, user-scaleable=no,maximum-scale=1,width=device-width">
    <title>스마트레시피</title>
    <!-- fontawesome 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/recipe.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide2.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body id="body">
	<div>
    <header id="header">
        <div class="logo" id="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
        <div class="logo2" id="logo2"><img src="${path}/resources/img/great.gif"></div>
        <div class="btn"><i id="hambtn" class="fas fa-bars"></i></div>
    </header>
    <section id="section">
        <div class="notuse"></div>
        <div id="use">
            <div id="recommendbackground">
                <h1>당신을 위한 레시피</h1>
                <div id="recommendrecipe">
                <c:set var="path" value="${pageContext.request.contextPath}"/>
                <c:forEach var="item" items="${recipeList}" begin="0" end="8" step="1" varStatus="status">
                    <div>
                        <a href="/recipedetail?no=${item.recipe_id}">
                            <div class="side">추천 레시피</div>
                            <img src="${path}/resources/img/basic_img/${item.img_path}">
                            <div class="recipename">${item.recipe_nm_ko}</div>
                        </a>
                    </div>
				</c:forEach>
				<!-- 더보기 -->
				<c:forEach var="item" items="${recipeList}" begin="9" end="20" step="1" varStatus="status">
                   <div class="fade">
                        <a href="/recipedetail?no=${item.recipe_id}">
                            <div class="side">추천 레시피</div>
                            <img src="${path}/resources/img/basic_img/${item.img_path}">
                            <div class="recipename">${item.recipe_nm_ko}</div>
                        </a>
                    </div>
				</c:forEach>
                </div>
                <div id="more">
                    <div><a onclick="more()">더보기</a></div>
                </div>
                <div id="close">
                    <div><a onclick="closemore()">닫기</a></div>
                </div>
            </div>
            <div id="favoriterecommend">
                <h1>고르는 레시피</h1>
                <div id="nation">
                    <div class="menu">국가별</div>
                    <div class="menudetail">
                        <input type="checkbox" id="korea" name="nation_nm" value="한식" onclick="oneCheckboxNation(this)" checked/>
                        <label for="korea">한식</label>
                        <input type="checkbox" id="china" name="nation_nm" value="중국" onclick="oneCheckboxNation(this)"/>
                        <label for="china">중식</label>
                        <input type="checkbox" id="japan" name="nation_nm" value="일본" onclick="oneCheckboxNation(this)"/>
                        <label for="japan">일식</label>
                        <input type="checkbox" id="west" name="nation_nm" value="서양" onclick="oneCheckboxNation(this)"/>
                        <label for="west">서양</label>
                        <input type="checkbox" id="italy" name="nation_nm" value="이탈리아" onclick="oneCheckboxNation(this)"/>
                        <label for="italy">이탈리아</label>
                        <input type="checkbox" id="eastasia" name="nation_nm" value="동남아시아" onclick="oneCheckboxNation(this)"/>
                        <label for="eastasia">동남아시아</label>
                        <input type="checkbox" id="fusion" name="nation_nm" value="퓨전" onclick="oneCheckboxNation(this)"/>
                        <label for="fusion">퓨전</label>
                    </div>
                </div>
                <div id="ingredient">
                    <div class="menu">재료별</div>
                    <div class="menudetail">
                        <input type="checkbox" id="ingre_1" name="ty_nm" value="밥" checked/>
                        <label for="ingre_1">밥</label>
                        <input type="checkbox" id="ingre_2" name="ty_nm" value="떡/한과"/>
                        <label for="ingre_2">떡/한과</label>
                        <input type="checkbox" id="ingre_3" name="ty_nm" value="만두/면류"/>
                        <label for="ingre_3">만두/면류</label>
                        <input type="checkbox" id="ingre_4" name="ty_nm" value="국"/>
                        <label for="ingre_4">국</label>
                        <input type="checkbox" id="ingre_5" name="ty_nm" value="나물/생채/샐러드"/>
                        <label for="ingre_5">나물/생채/샐러드</label>
                        <input type="checkbox" id="ingre_6" name="ty_nm" value="구이"/>
                        <label for="ingre_6">구이</label>
                        <input type="checkbox" id="ingre_7" name="ty_nm" value="볶음"/>
                        <label for="ingre_7">볶음</label>
                        <input type="checkbox" id="ingre_8" name="ty_nm" value="밑반찬/김치"/>
                        <label for="ingre_8">밑반찬/김치</label>
                        <input type="checkbox" id="ingre_9" name="ty_nm" value="조림"/>
                        <label for="ingre_9">조림</label>
                        <input type="checkbox" id="ingre_10" name="ty_nm" value="찜"/>
                        <label for="ingre_10">찜</label>
                        <input type="checkbox" id="ingre_11" name="ty_nm" value="튀김/커틀릿"/>
                        <label for="ingre_11">튀김/커틀릿</label>
                        <input type="checkbox" id="ingre_12" name="ty_nm" value="찌개/전골/스튜"/>
                        <label for="ingre_12">찌개/전골/스튜</label>
                        <input type="checkbox" id="ingre_13" name="ty_nm" value="도시락/간식"/>
                        <label for="ingre_13">도시락/간식</label>
                        <input type="checkbox" id="ingre_14" name="ty_nm" value="부침"/>
                        <label for="ingre_14">부침</label>
                        <input type="checkbox" id="ingre_15" name="ty_nm" value="양식"/>
                        <label for="ingre_15">양식</label>
                        <input type="checkbox" id="ingre_16" name="ty_nm" value="샌드위치/햄버거"/>
                        <label for="ingre_16">샌드위치/햄버거</label>
                        <input type="checkbox" id="ingre_17" name="ty_nm" value="빵/과자"/>
                        <label for="ingre_17">빵/과자</label>
                        <input type="checkbox" id="ingre_18" name="ty_nm" value="양념장"/>
                        <label for="ingre_18">양념장</label>
                        <input type="checkbox" id="ingre_19" name="ty_nm" value="음료"/>
                        <label for="ingre_19">음료</label>
                        <input type="checkbox" id="ingre_20" name="ty_nm" value="피자"/>
                        <label for="ingre_20">피자</label>
                        <input type="checkbox" id="ingre_21" name="ty_nm" value="그라탕/리조또"/>
                        <label for="ingre_21">그라탕/리조또</label>
                    </div>
                </div>
                <div id="level">
                    <div class="menu">난이도</div>
                    <div class="menudetail">
                        <input type="checkbox" id="level_1" name="level_nm" value="초보환영" onclick="oneCheckboxLevel(this)" checked/>
                        <label for="level_1">초보</label>
                        <input type="checkbox" id="level_2" name="level_nm" value="보통" onclick="oneCheckboxLevel(this)"/>
                        <label for="level_2">보통</label>
                        <input type="checkbox" id="level_3" name="level_nm" value="어려움" onclick="oneCheckboxLevel(this)"/>
                        <label for="level_3">어려움</label>
                    </div>
                </div>
                <div id="search"> <h3>조건 검색</h3> </div>
                <div id="menulist">
                </div>
            </div>
        </div>
        <div class="notuse"></div>
    </section>
    </div>
    <%@ include file="slide.jsp" %>
    
</body>
<script src="${path}/resources/js/recipe.js">
</script>
<script type="text/javascript">
    function oneCheckboxNation(a){
        var obj = document.getElementsByName("nation_nm");
        for(var i=0; i<obj.length; i++){
            if(obj[i] != a){
                obj[i].checked = false;
            }
        }
    }
    function oneCheckboxLevel(a){
        var obj = document.getElementsByName("level_nm");
        for(var i=0; i<obj.length; i++){
            if(obj[i] != a){
                obj[i].checked = false;
            }
        }
    }
</script>
</html>