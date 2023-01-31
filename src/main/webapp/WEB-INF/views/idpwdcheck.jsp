<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
    <link href="${path}/resources/css/idpwdcheck.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    <header>
        <a href="/login"'><i class="fas fa-arrow-left"></i></a>
        <div>ID/PWD 찾기</div>
    </header>
    <div id="app">
    <section >
        <article>
            <form action="/login/idcheck" method="post">
            	<h2>ID 찾기</h2>
                <div>
                    <label for="email">이메일</label>
                    <input type="text" id="email" name="email" placeholder="이메일을 입력하세요" required>
                </div>
                <div class="submit">
                    <input type="button" value="아이디 확인" onclick="checkid()">
                </div>
            </form>
             <form action="/login/pwdcheck" method="post">
            	<h2>임시 비밀번호 발급</h2>
                <div class="idinput">
                    <label for="id">아이디</label>
                    <div>
                        <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" required>
                    </div>
                </div>
                <div>
                    <label for="email">이메일</label>
                    <input type="text" id="email2" name="email2" placeholder="이메일을 입력하세요" required>
                </div>
                <div class="submit">
                    <input type="button" value="이메일 발송" onclick="checkpwd()">
                </div>
            </form>
        </article>
        <footer>

        </footer>
    </section>
    </div>
    <div id="modal2" class="modal2">
    	<div>
    		<div>
    		    <div><img src="${path}/resources/img/GreatLogo.png"></div>
    			<div class="modaldata"></div>
    			<div><button>확인</button></div>
    		</div>
    	</div>
    </div>
    <script>
	function checkid() {
        var userEmail = $('#email').val();
        $.ajax({
            type : "POST",
            url: '/login/idcheck',
            data: {userEmail:userEmail},
            success: function(data) {
                		 $('.modal2').css("display","flex");
                		 $('.modaldata').html(data);
                     
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
            		}
        });
	}
    
    	function checkpwd() {
            var userId  = $('#id').val();
            var userEmail = $('#email2').val();
            $.ajax({
                type : "POST",
                url: '/login/pwdcheck',
                data: {userId:userId,userEmail:userEmail},
                success: function(data) {
                    		 $('.modal2').css("display","flex");
                    		 $('.modaldata').html(data);
                         
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
                		}
            });
    	}
    </script>
</body>
</html>