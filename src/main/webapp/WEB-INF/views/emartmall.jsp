<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이마트몰</title>
<style>
body {
margin:0px;
padding:0px;}

 iframe {
 	width:100%;
 	height:100vh;
 	border-style:none;
 }
.logo {
display:flex;
justify-content:space-between;
align-items:center;
}
.logo > img {
	padding-top:10px;
    width: 200px;
    height: 84px;
}

button {
	background-color:#4caf50;
	width:80px;
	height:35px;
	color:white;
	font-size:12px;
	border-style:none;
	border-width:0px;
	margin-right:10px;
}
</style>
</head>
<body>
<div class="logo"><img src="${path}/resources/img/GreatLogo.png"><button onclick="home();">홈으로</button></div>
<iframe src="http://m.emart.ssg.com">
</iframe>
<script>
	function home(){
		window.location.href="/main";
	}
</script>
</body>
</html>