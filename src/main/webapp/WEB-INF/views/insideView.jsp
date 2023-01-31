<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.great.domain.ProfileVO" %>
<%@ page import="com.great.domain.FamilyVO" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% ProfileVO profile = (ProfileVO)session.getAttribute("profile"); %>
<% FamilyVO user = (FamilyVO)session.getAttribute("user"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- initial-scale 뒤 문구 쓰면 핸드폰에서 input 클릭시 확대 안되게함 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>회원가입</title>
    <!-- fontawesome 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/insideView.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
	<div class="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
    <div class="btn"><i class="fas fa-bars"></i></div>
	<a href="/insideAdd"><div class="foodAdd">수동 추가</div></a>

    <script type="text/javascript"> 
    $(document).ready(function() {
        $("#touch").dblclick(function() {
   		 $('.modal2').css("display","flex");
     
     	//.modal안에 button을 클릭하면 .modal닫기
     	$(".modal2 button").click(function(){
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
        });
      });
        
        function toggle(element) {
        	var str = element.id + "_" + element.name;
        	//console.log(document.getElementById(str).style);
        	//console.log(str);
        	//console.log(element.name);
        	if(element.checked){
        		document.getElementById(str).style.boxShadow = "0px 0px 8px 5px rgba(100, 180, 255, 0.9)";	
        	} else {
        		document.getElementById(str).style.boxShadow = "";
        	}
        	getCheckboxValue()
        }
        
        function getCheckboxValue()  {
        	  // 선택된 목록 가져오기
        	  const query = 'input[type="checkbox"]:checked';
        	  const selectedEls = document.querySelectorAll(query);
        	  
        	  // 선택된 목록에서 value 찾기
        	  var result = '';
        	  selectedEls.forEach((el)=>{
        	    result += el.value + ':';
        	  });
        	  
        	  // 출력
        	  document.getElementById("checklist").value = result;
        	  //console.log(document.getElementById("checklist").value);
        	  
        	  if(result){
        		  document.getElementById("add-food").style.visibility = "visible";
        	  } else {
        		  document.getElementById("add-food").style.visibility = "hidden";
        	  }
        	}
        
        
    </script>
    <div class="img-container">
    	<img src="${path}/resources/img/img.png">

    	<form action="addfoodlist" method="post" onsubmit="sbm();">
	    	<c:forEach items="${detectList}" var="item" varStatus="status">
	    		<c:forEach items="${allergyList}" var="allergy">
	    			<c:if test="${allergy eq item.cls}">
	    				<c:set var="flag" value="on" />
	    			</c:if>
				</c:forEach>
				<c:choose>
	    				<c:when test="${flag eq 'on'}">
	    					<div id="${item.cls}_${status.index}" class="detect-warning" style="left:${item.x}%; top:${item.y}%;">
	    						<input type="checkbox" id="${item.cls}" name="${status.index}" value="${item.LTX} ${item.LTY} ${item.RDX} ${item.RDY} ${item.x} ${item.y} ${item.cls}_${status.index}" style="margin:0px;" onclick="toggle(this)"/>
	    						<label for="${item.cls}">${item.cls}</label>
	    					</div>
	    				</c:when>
	    				<c:when test="${flag ne 'on'}">
	    					<div id="${item.cls}_${status.index}" class="detect-safe" style="left:${item.x}%; top:${item.y}%;">
	    						<input type="checkbox" id="${item.cls}" name="${status.index}" value="${item.LTX} ${item.LTY} ${item.RDX} ${item.RDY} ${item.x} ${item.y} ${item.cls}_${status.index}" style="margin:0px;" onclick="toggle(this)"/>
	    						<label for="${item.cls}">${item.cls}</label>
	    					</div>
	    				</c:when>
	    		</c:choose>
	    		<c:set var="flag" value="off" />
			</c:forEach>

			<input type="text" id="checklist" name="selectlist" value=""/>
			<input id="add-food" class="add-food" type="submit" value="리스트 추가  >>" style="visibility:hidden"></input>
		</form>
		<div id="modal2" class="modal2">
    	<div>
    		<div>
    		    <div><img src="${path}/resources/img/GreatLogo.png"></div>
    			<div class="modaldata"></div>
    			<div><button>확인</button></div>
    		</div>
    	</div>
    </div>
    </div>
    <%@ include file="slide.jsp" %>
    <script>
    	function sbm(){
    		loading();
    	}
    </script>
</body>
</html>