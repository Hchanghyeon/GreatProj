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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/register.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    <header>
        <a href="/login"'><i class="fas fa-arrow-left"></i></a>
        <div>회원가입</div>
    </header>
    <div id="app">
    <section >
        <article>
            <form action="/register" method="post">
	            <div>
    	            <label for="id">아이디</label>
            	</div>
                <div class="idinput">
                    <div>
                        <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" required>
                        <input type="button" value="중복확인" onclick="checkId()">
                    </div>
                </div>
                <div class="id_input_re_1">사용 가능한 아이디 입니다.</div>
                <div class="id_input_re_2">아이디가 이미 존재합니다.</div>
                <div>
                    <label for="pw">비밀번호</label>
                    <div>
                        <input type="password" id="pw" name="pw" placeholder="비밀번호를 입력하세요" v-model="pwd1" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$" required>
                    </div>
                    <div class="pass">
                	{{password}}
                	</div>
                </div>
                <div>
                 	<label for="pw2">비밀번호 확인</label>
                    <div>
                        <input type="password" id="pw2" name="pw2" placeholder="비밀번호를 확인" v-model="pwd2" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$" required>
                    </div>
                </div>
                
                <div v-if='reversedMessage[0]==0' class="passcheck">
                	{{reversedMessage[1]}}
                </div>
                <div v-if='reversedMessage[0]==1' style="font-size:13px; color:blue" class="passcheck">
                	{{reversedMessage[1]}}
                </div>
                <div v-if='reversedMessage[0]==2' style="font-size:13px; color:red" class="passcheck">
                	{{reversedMessage[1]}}
                </div>
                
                <div>
                    <label for="nickname">이름(닉네임)</label>
                    <input type="text" id="nickname" name="nickname" placeholder="이름을 입력하세요" required>
                </div>
                <div>
                    <label for="email">이메일</label>
                    <input type="email" id="email" name="email" placeholder="이메일을 입력하세요" required>
                </div>
                <div>
                	<label for="address">주소</label>
                </div>
                <div class="add">
                    <input type="text" id="address1" name="address1" placeholder="우편번호" required readonly>
                    <button id="btn" class="btn" type="button" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>
                </div>
                <div>
                    <input type="text" id="address2" name="address2" placeholder="도로명 주소" required readonly>
                    <input type="text" id="address3" name="address3" placeholder="상세주소" required>
                </div>
                
                <div class="submit">
                    <input type="submit" value="회원가입">
                </div>
            </form>
        </article>
        <footer>

        </footer>
    </section>
    </div>
    <div id="modal2" class="modal2">
        <button>&times;</button>
    </div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
    
    var data11 = document.getElementById('modal2');
   
    // 회원 아이디 중복체크 기능
    function checkId(){
    	console.log("check")
        let id = $('#id').val();
        let data = {id : id}
        ;
        $.ajax({
        	type : "post",
        	url : "/login/memberIdChk",
        	data : data,
        	success : function(result){
        		if(result == 0){
        			$('.id_input_re_1').css("display","inline-block");
        			$('.id_input_re_2').css("display","none");
        		} else {
        			$('.id_input_re_2').css("display","inline-block");
        			$('.id_input_re_1').css("display","none");
        		}
        	}
        })
    }
    
    
    const app = new Vue({
    	el:'#app',
    	data: {
    		pwd1:'',
    		pwd2:'',
    		pwd3:'',
    	},
        computed: {
        	reversedMessage: function () {
        	if((this.pwd1==''&&this.pwd2=='')){
        		return [0, '']
        	}
        	else if(this.pwd1==this.pwd2){
        		return [1, '비밀번호가 같습니다']
        		
        	} else {
        		return [2, '비밀번호가 다릅니다']
        	}
        	return null;
          },
          password: function(){
          	if((this.pwd1=='')){
        		return ''
        	}
        	else {
        		return '비밀번호는 특수, 영어, 숫자 포함 8글자 ~ 16글자'
        	}
          }
    	}
    })
    
    function execPostCode() {
        new daum.Postcode({
            oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
               var extraRoadAddr = ''; // 도로명 조합형 주소 변수

               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                   extraRoadAddr += data.bname;
               }
               // 건물명이 있고, 공동주택일 경우 추가한다.
               if(data.buildingName !== '' && data.apartment === 'Y'){
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
               }
               // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
               if(extraRoadAddr !== ''){
                   extraRoadAddr = ' (' + extraRoadAddr + ')';
               }
               // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
               if(fullRoadAddr !== ''){
                   fullRoadAddr += extraRoadAddr;
               }

               document.getElementById('address1').value = data.zonecode;
               document.getElementById('address2').value = fullRoadAddr;
               self.close();
               $('.modal2').css("display","none");
           }
        }).embed(data11);
        
      	 $(".btn").click(function(){
             $('.modal2').css("display","flex");
         });
         
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
   
    </script>
</body>
</html>