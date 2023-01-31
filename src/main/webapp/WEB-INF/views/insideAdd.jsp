<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>재료 수동 추가</title>
    <!-- fontawesome 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/insideadd.css" rel="stylesheet" type="text/css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <style>
    
    </style>
</head>
<body>
	<div class="logo"><img src="${path}/resources/img/GreatLogo.png"><button onclick="loading();">뒤로가기</button></div>
    <div class="content">
        <div class="before">
            <div>
            <canvas id="bCanvas" style="position:absolute;"></canvas>
            <img id="image" src="${path}/resources/img/img.png">   
            </div>
        </div>
    </div>
    <div id="modal2" class="modal2">
    	<div>
    		<div>
    		<button id="xbtn"><i id="xbutton" class="fas fa-times"></i></button>
    		<form action="insideAdd" method="post" onsubmit="loading();">
    		    <div><img src="${path}/resources/img/GreatLogo.png"></div>
    			<h2>재료 추가</h2>
                <div><img id="backimg"></img></div>
                <!-- <div class="name">LTX</div> -->
                <div><input type="text" name="LTX" id="LTX"></div>
                <!-- <div class="name">LTY</div> -->
                <div><input type="text" name="LTY" id="LTY"></div>
                <!-- <div class="name">RDX</div> -->
                <div><input type="text" name="RDX" id="RDX"></div>
                <!-- <div class="name">RDY</div> -->
                <div><input type="text" name="RDY" id="RDY"></div>
                <!-- <div class="name">X</div> -->
                <div><input type="text" name="X" id="X"></div>
                <!-- <div class="name">Y</div> -->
                <div><input type="text" name="Y" id="Y"></div>
                <div class="name">재료 이름</div>
                <div><input type="text" name="cls"></div>
                <div class="name">보관 날짜</div>
                <div><input type="date" name="inDate" id="inDate"></div>
                <div class="name">유통 기한(~까지)</div>
                <div><input type="date" name="endDate" id="endDate"></div>
                	 <input type="hidden" name="img" id="im">
    			<div><input id="button" type="submit" value="입력"></div>
    		</form>
    		</div>
    	</div>
    </div>
    <div class="loader"></div> <div class="container" style="display:none;"> <h1>로딩 후 화면</h1> <div>
    <script src="${path}/resources/js/insideAdd.js"></script>
    <script>
  //.modal안에 button을 클릭하면 .modal닫기
    $("#xbtn").click(function(){
    	 $('.modal2').css("display","none");
    });
    function loading(){
    	var loader = $(".loader"); 
    	var container = $(".container"); 
    	loader.css("display","block"); 
    	container.css("display","none"); 
    	
    	window.location.href="/insideView";
    }
    </script>
</body>
</html>