<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.great.domain.ProfileVO" %>
<% ProfileVO profile=(ProfileVO)session.getAttribute("profile"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scaleable=no,maximum-scale=1,width=device-width">
    <title>식재료 리스트</title>
    <link href="${path}/resources/css/foodlist.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <!-- 구글 폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class="body">
	<div class="bodyh">
    <header>
        <div class="logo"><img src="${path}/resources/img/GreatLogo.png"></div>
        <div class="btn"><i class="fas fa-bars"></i></div>
    </header>
    <section>
        <div class="notuse">
        </div>
        <div class="use">
            <div class="content">
                    <div class="menudetail">
                        <input type="checkbox" id="fooddue" name="date" value="유통기한" onclick="check(this)"/>
                        <label for="fooddue">유통기한</label>
                        <input type="checkbox" id="allergy" name="date" value="알레르기" onclick="check(this)"/>
                        <label for="allergy">알레르기</label>
               		 </div>
                <div><input type="search" name="search" class="search" id="search" placeholder="식재료 검색"></div>
                <div class="foodform" id="foodform">
                <c:set var="path" value="${pageContext.request.contextPath}" />
                <c:forEach items="${foodlist}" var="foodlist">
                <div class="food">
                   <a onclick="foodlink('${foodlist.num}','${foodlist.img}', '${foodlist.cls}', '${foodlist.inDate}', '${foodlist.endDate}')">
                    <div class="foodimg"><img src="${foodlist.img}"></div>
                    <div class="foodname"> ${foodlist.cls}</div>
                    <div class="fooddate">
                        <div class="date">${foodlist.endDate}</div>
                        <span class="due">${foodlist.date }</span>
                    </div>
                    </a>
                </div>

                </c:forEach>
                </div>
            </div>
        </div>
        <div class="notuse">
        </div>
    </section>
    </div>
    <div id="modal2" class="modal2">
        <div>
            <div>
                <button id="xbtn"><i id="xbutton" class="fas fa-times"></i></button>
                <div>
                    <h1>식재료 정보</h1>
                </div>
                <div><img src="" id="clickimg"></div>
                <div><b>식재료 이름</b></div>
                <div><input type="text" id="clickcls" value=""></div>
                <div><b>등록한 날짜</b></div>
                <div><input type="text" id="clickindate"></div>
                <div><b>유통기한 날짜</b></div>
                <div><input type="text" id="clickenddate"></div>
                <div><input type="hidden" id="foodnum" value=""></div>
                <div><button id="edit" onclick="editfood()">수정</button><button id="delete"
                        onclick="deletefood()">삭제</button></div>
            </div>
        </div>
    </div>
    <%@ include file="slide.jsp" %>
</body>
<script>
	css();

	function check(a){
    	var obj = document.getElementsByName("date");
    	for(var i=0; i<obj.length; i++){
	        if(obj[i] != a){
            	obj[i].checked = false;
    	    }
	    }
    	
    	if($("input:checkbox[name=date]:checked").length <= 0){
			search.value="";
			search.readOnly=false;
    		 $.ajax({
 	            type: "POST",
 	            url: '/foodlist/search',
 	            data: { text: ""},
 	            success: function (jarray) {
 	            	$(".foodform").empty();
 	        		for(let i in jarray){
 	        			makeFoodlist(i, jarray);
 	        		}	
 	        		css();	
 	            }
 	        });	
    	}else{
    		if(a.value == "유통기한"){
    			let search = document.getElementById("search");
    			search.value="";
    			search.readOnly=true;
       		 $.ajax({
  	            type: "POST",
  	            url: '/foodlist/search',
  	            data: { text: "due"},
  	            success: function (jarray) {
  	            	$(".foodform").empty();
  	        		for(let i in jarray){
  	        			makeFoodlist(i, jarray);
  	        		}	
  	        		css();	
  	            }
  	        });	
        	}else if(a.value == "알레르기"){
    			search.value="";
    			search.readOnly=true;
       		 $.ajax({
  	            type: "POST",
  	            url: '/foodlist/search',
  	            data: { text: "allergy"},
  	            success: function (jarray) {
  	            	$(".foodform").empty();
  	        		for(let i in jarray){
  	        			makeFoodlistallergy(i, jarray);
  	        		}	
  	        		css();	
  	            }
  	        });	
        	}
    	}
	}

	
   function makeFoodlist(i, jarray) {
	   let foodform = document.getElementById("foodform");

		let food = document.createElement('div');
		food.setAttribute('class','food');

		let foodimg = document.createElement('div');
		foodimg.setAttribute('class','foodimg');
		
		let img = document.createElement("img");
		img.setAttribute("src",jarray[i].img);


		let foodname = document.createElement('div');
		foodname.setAttribute('class','foodname');
		foodname.innerHTML = jarray[i].cls;

		let fooddate = document.createElement('div');
		fooddate.setAttribute('class','fooddate');


		let date = document.createElement('div');
		date.setAttribute('class','date');
		date.innerHTML = jarray[i].endDate;
		

		let due = document.createElement('span');
		due.setAttribute('class','due');
		due.innerHTML = jarray[i].date;

		let a = document.createElement("a");
		a.setAttribute("onclick","foodlink('"+jarray[i].num+"','"+jarray[i].img+"','"+jarray[i].cls+"','"+jarray[i].inDate+"','"+jarray[i].endDate+"')");
		console.log("foodlink('"+jarray[i].num+"','"+jarray[i].img+"','"+jarray[i].inDate+"','"+jarray[i].endDate+"')");
		
		food.appendChild(a);
		a.appendChild(foodimg);
		foodimg.appendChild(img);
		a.appendChild(foodname);
		a.appendChild(fooddate);
		fooddate.appendChild(date);
		fooddate.appendChild(due);
		foodform.appendChild(food);
	   
   }
   
   function makeFoodlistallergy(i, jarray) {
	   let foodform = document.getElementById("foodform");

		let food = document.createElement('div');
		food.setAttribute('class','food');

		let foodimg = document.createElement('div');
		foodimg.setAttribute('class','foodimg');
		
		let img = document.createElement("img");
		img.setAttribute("src",jarray[i].img);


		let foodname = document.createElement('div');
		foodname.setAttribute('class','foodname');
		foodname.innerHTML = jarray[i].cls;

		let fooddate = document.createElement('div');
		fooddate.setAttribute('class','fooddate');


		let date = document.createElement('div');
		date.setAttribute('class','date');
		date.innerHTML = jarray[i].endDate;
		

		let due = document.createElement('span');
		due.setAttribute('class','due');
		due.innerHTML = jarray[i].date;
		
		let name = document.createElement('span');
		name.setAttribute('class','name');
		name.innerHTML = jarray[i].username;

		let a = document.createElement("a");
		a.setAttribute("onclick","foodlink('"+jarray[i].num+"','"+jarray[i].img+"','"+jarray[i].cls+"','"+jarray[i].inDate+"','"+jarray[i].endDate+"')");
		console.log("foodlink('"+jarray[i].num+"','"+jarray[i].img+"','"+jarray[i].inDate+"','"+jarray[i].endDate+"')");
		
		food.appendChild(a);
		a.appendChild(foodimg);
		foodimg.appendChild(img);
		a.appendChild(foodname);
		a.appendChild(fooddate);
		fooddate.appendChild(date);
		fooddate.appendChild(due);
		fooddate.appendChild(name);
		foodform.appendChild(food);
	   
   }
	
		search.oninput = function(){
		 	console.log(search.value);

	        $.ajax({
	            type: "POST",
	            url: '/foodlist/search',
	            data: { text: search.value },
	            success: function (jarray) {
	            	$(".foodform").empty();
	        		for(let i in jarray){
	        			makeFoodlist(i, jarray)
	        		}	
	        	    css();	
	            }
	        });
		}


    function foodlink(num, img, food, indate, enddate) {
        console.log("눌림");
        $('.body').addClass('modal-open');

        $("#clickimg").attr("src", img);
        $('#clickcls').val(food);
        $('#clickindate').val(indate);
        $('#clickenddate').val(enddate);
        $('#foodnum').val(num);
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

    function editfood() {
        var cls = $('#clickcls').val();
        var inDate = $('#clickindate').val();
        var endDate = $('#clickenddate').val();
        var num = $('#foodnum').val();

        loading();


        $.ajax({
            type: "POST",
            url: '/foodlist/edit',
            data: { cls: cls, inDate: inDate, endDate: endDate, num: num },
            success: function (data) {
                if (data == "success") {
                    location.reload();
                }
            }
        });
    }

    function deletefood() {
        var num = $('#foodnum').val();

        loading();

        $.ajax({
            type: "POST",
            url: '/foodlist/delete',
            data: { num: num },
            success: function (data) {
                if (data == "success") {
                    location.reload();
                }
            }
        });
    }
    
    function css() {
        var x = document.getElementsByClassName('due');
        for(var i=0; i < x.length; i++){
        	var limitdate = x[i].innerHTML;
        	if(limitdate < 0){
        		
        		x[i].innerHTML = Math.abs(Number(limitdate)) + "일 남음";
        		x[i].style.backgroundColor="rgb(74, 202, 213)";
        	}else {
        		x[i].innerHTML = limitdate + "일 지남";
        		x[i].style.backgroundColor="yellow";
        		x[i].style.color="black";
        	}
        }
    }

</script>
<script src="${path}/resources/js/changepage.js"></script>
</html>