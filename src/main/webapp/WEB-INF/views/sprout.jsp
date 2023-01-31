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
    <title>월간 새싹</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- êµ¬ê¸í°í¸ -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/sprout.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <script src="https://kit.fontawesome.com/a0175a9a60.js" crossorigin="anonymous"></script>
</head>
<body class="body">
	<div>
    <div class="logo"><img src="${path}/resources/img/GreatLogo.png">    <div class="btn"><i class="fas fa-bars"></i></div></div>

    <header>  <!-- ì ëª© -->
    </header>

    <section>
    <div class="sectionmain">
        <div class="calendar">
        	<div class="month">9월</div>
        	<div class="stamp">
				<c:forEach begin="1" end="${MonthlyDay}" varStatus="status">
					<c:choose>
					    <c:when test="${booleanMileage[status.index]}">
					        <div class="itemBox">
								 <div class="day">${status.index}</div>
		               	   		 <div class="item">
		               	   		 	<img src="${path}/resources/img/after.png" style="width:45px; height:45px; padding-top:2.5px;">
		               	   		 </div>
		               	    </div>
					    </c:when>
					    <c:when test="${not booleanMileage[status.index]}">
					        <div class="itemBox">
								 <div class="day">${status.index}</div>
		               	   		 <div class="item">
		               	   		 	<img src="${path}/resources/img/before.png" style="width:45px; height:45px; padding-top:2.5px; opacity: 0.5;">
		               	   		 </div>
		               	    </div>
					    </c:when>
					</c:choose>
				</c:forEach>   	
        	</div>
        	<br>
        </div>
        <div class="holdCount"> 남은 새싹 포인트: <span>${holdCount}</span> 개</div>
        </div>
        <div class="funding">
        	<div class="greenfund">그린 펀드 리스트</div>
        	<c:forEach var="vo" items="${raising}" varStatus="status">
        	<div class="corplist">
        		<a onclick="clickname('${vo.name}','${vo.info}','${vo.count}','${holdCount}','${path}/resources/img/${vo.imgsrc}')">
        			<div class="corp_img"><img src="${path}/resources/img/${vo.imgsrc}"></div>
        			<div class="corp_name">${vo.name}</div>
        			<span class="corp_fund">${vo.count}만원</span>
        		</a>
        	</div>
        	</c:forEach>
        </div>
    </section>
    </div>
    <div id="modal2" class="modal2">
        <div>
        	<button id="xbtn"><i id="xbutton" class="fas fa-times"></i></button>
            <div id="raisingHead">
            	펀딩하기
            </div>
            <div id="corpImg">
            	<img id="corpcorp">
            </div>
            <div id="corpName">
            </div>
            <div id="corpInfo">    
            </div>
            <div><div id="corpRaising"></div> </div>
            <form class="vovo" action="/sprout" method="post">
				<input id="corpid" type="text" name="corp" style="visibility: hidden; display: none;">
				<input type="text" name="count" id="corpcount">
				<input id="corpcheck" type="button" value="펀딩하기">
			</form>
        </div>
    </div>
    <%@ include file="slide.jsp" %>
    <script>
    	function clickname(name, info, count, holdcount, img){
    		$('.body').addClass('modal-open');
    		
    		let corpimg = document.getElementById("corpcorp");
    		let corpname = document.getElementById("corpName");
    		let corpInfo = document.getElementById("corpInfo");
    		let corpRaising = document.getElementById("corpRasing");
    		let corpcount = document.getElementById("corpcount");
    		let corpcheck = document.getElementById("corpcheck");
    		let corpid = document.getElementById("corpid");
    		let vovo = document.getElementsByClassName('vovo')[0];
    		
    		corpimg.setAttribute("src",img);
    		corpname.innerHTML = name;
    		corpInfo.innerHTML = info;
    		corpcount.setAttribute("placeholder",count+"만원 이상");
    		vovo.setAttribute("id",name+"_frm");
    		corpid.setAttribute("value",name);
    		corpcheck.setAttribute("onclick","submitCheck(this," + holdcount + ", count)")
    	}
    	
        //.modal안에 button을 클릭하면 .modal닫기
        $("#xbtn").click(function () {
        	$('.body').removeClass('modal-open');
            
        });

        //.modal밖에 클릭시 닫힘
        $(".modal2").click(function (e) {
            if (e.target.className != "modal2") {
                return false;
            } else {
            	$('.body').removeClass('modal-open');
            }
        });
    
		function submitCheck(a, holdCount, name) {
			//console.log(name);
			//console.log(name.value);
			//console.log(holdCount);
			if(name.value > holdCount) {
				alert("보유한 새싹보다 많습니다!");
			}
			else if(name.value <= holdCount) {
				//name.value = name.name + "_" + name.value
				//console.log(name.value);
				a.parentNode.submit();
				//document.getElementById("frm").submit();				
			}
		}
	</script>
</body>

</html> 