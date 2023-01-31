<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.great.domain.ProfileVO" %>
 <% ProfileVO profile=(ProfileVO)session.getAttribute("profile"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>메인페이지</title>
    <!-- 구글 폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="${path}/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
</head>

<body>
    <div class="align">
        <header>
            <div class="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
            <div class="btn"><i class="fas fa-bars"></i></div>
        </header>
        <section>
            <div class="main_1">
                <div class="main_1_1">
                    <div class="main_header_div">
                        <div class="main_header">
                            <div>오늘의 레시피 추천</div>
                        </div>
                    </div>
                    <div class="swiper mySwiper">
                        <div class="swiper-wrapper">
                        	<c:forEach var="item" items="${recipeList}" begin="0" end="10" step="1" varStatus="status">
                        	   		 <div class="swiper-slide"><span class="side2">${item.nation_nm}</span><span class="side">${item.recipe_nm_ko}</span><a onclick="loading('/recipedetail?no=${item.recipe_id}')"><img src="${path}/resources/img/basic_img/${item.img_path}"></a></div>
							</c:forEach>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
                <div class="main_1_2">
                    <div class="main_header_div">
                        <div class="main_header">
                            <div> 오늘 정보 </div>
                        </div>
                    </div>
                    <div class="today_info">
                        <div class="weather">
                            <div class="weatherhead">오늘 날씨</div>
                            <div class="CurrIcon"></div>
                            <div class="CurrTemp"></div>
                            <div class="City"></div>
                        </div>
                        <div class="carbon">
							<div class="carbonhead">오늘의<br>탄소 마일리지</div>
							<a onclick="loading('/sprout')">
								<c:choose>
								    <c:when test="${mileage lt '100'}">
								        <div class="sprout">
											<img src="${path}/resources/img/before.png">
										</div>
										<div class="mileage">${mileage}/100</div>
								    </c:when>
								    <c:when test="${mileage ge '100'}">
								        <div class="sprout">
											<img src="${path}/resources/img/after.png">
										</div>
										<div class="mileage">${mileage}/100</div>
										<div class="sproutText">오늘의 새싹 획득!</div>
								    </c:when>
								    <c:when test="${empty mileage}">
								        <div class="sprout">
											<img src="${path}/resources/img/before.png">
										</div>
										<div class="mileage">0/100</div>
								    </c:when>
								</c:choose>
							</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main_2">
                <div>
                    <div class="main_header_div">
                        <div class="main_header memo_header">
                            <div> 유통기한이 별로 남지 않은 식재료 리스트 </div>
                            <span class="create"><a onclick="loading('/foodlist')">더보기</a></span>
                        </div>
                    </div>
                    <div class="ingredient">
                        <div>
                            <c:set var="path" value="${pageContext.request.contextPath}" />
                            <c:forEach items="${limitfoodlist}" var="limitfoodlist" begin="0" end="1" step="1" varStatus="status">
                                <div><span class="side5">${limitfoodlist.cls}</span><img src="${limitfoodlist.img}">
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            <c:forEach items="${limitfoodlist}" var="limitfoodlist" begin="2" end="3" step="1" varStatus="status">
                                <div><span class="side5">${limitfoodlist.cls}</span><img src="${limitfoodlist.img}">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="main_header_div">
                        <div class="main_header memo_header">
                            <div> 메모 </div>
                            <span class="create" id="caa">생성</span>
                        </div>
                    </div>
                    <div class="memo">
                        <div>
                            <div>
                                <div class="swiper mySwiper mySwiper2">
                                    <div class="swiper-wrapper">
                                      <c:forEach items="${memolist}" var="memolist">
                                		  <div class="swiper-slide"><span class="side4">${memolist.date}</span><span
                                                class="side3">${memolist.username}</span><b>${memolist.memo}</b></div>
                            			</c:forEach>
                                    </div>
                                    <div class="swiper-pagination name"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div id="modal2" class="modal2">
    		<div>
    			<div>
    			    <button id="xbtn"><i id="xbutton" class="fas fa-times"></i></button>
    				<div>메모 작성</div>
    				<div class="tt"><textarea id="textt"></textarea></div>
    			    <button id="modal2_btn">작성완료</button>
    			</div>
    		</div>
    	</div>
    </div>
    <%@ include file="slide.jsp" %>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script>
        var swiper = new Swiper(".mySwiper", {
            spaceBetween: 30,
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
        });
        
        
        $("#caa").click(function (e) {
        	$('.modal2').css("display","flex");
        });
        
      //.modal안에 button을 클릭하면 .modal닫기
        $("#xbtn").click(function(){
        	 $('.modal2').css("display","none");
        });

        //.modal밖에 클릭시 닫힘
        $(".modal2").click(function (e) {
        if (e.target.className != "modal2") {
          return false;
        } else {
        	 $('.modal2').css("display","none");
        }
        });
        
        
        $("#modal2_btn").click(function(){
        	let memo = document.getElementById("textt").value;
        	 $.ajax({
                 type : "GET",
                 url: '/addmemo',
                 data: {memo:memo},
                 success: function(data) {
                     if(data == "success")
                     	window.location.href="/main";
                     else {
                 		
                 		}
                 }
             });
        });
        
        

    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            let weatherIcon =
            {
                '01': 'fas fa-sun',
                '02': 'fas fa-cloud-sun',
                '03': 'fas fa-cloud',
                '04': 'fas fa-cloud-meatball',
                '09': 'fas fa-cloud-sun-rain',
                '10': 'fas fa-cloud-showers-heavy',
                '11': 'fas fa-poo-storm',
                '13': 'far fa-snowflake',
                '50': 'fas fa-smog'
            };
            $.ajax({
                url: 'http://api.openweathermap.org/data/2.5/weather?q=Seoul&APPID=688465beb7ba10f0623d7bc2f5617372&units=metric',
                dataType: 'json',
                type: 'GET',
                success: function (data) {
                    var $Icon = (data.weather[0].icon).substr(0, 2);
                    var $Temp = Math.floor(data.main.temp) + 'º';
                    var $city = data.name;
                    $('.CurrIcon').append('<i class="' + weatherIcon[$Icon] + '"></i>');
                    $('.CurrTemp').prepend($Temp); $('.City').append($city);
                }
            })
        });
    </script>
</body>
</html>