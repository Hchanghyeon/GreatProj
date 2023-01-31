<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="${path}/resources/css/profileinfo.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    <header>
        <a href="/main/profile?username=${profileinfo.username}&path=${profileinfo.path}&id=${profileinfo.id}"><i class="fas fa-arrow-left"></i></a>
        <div>프로필 정보</div>
    </header>
    <div id="app">
    <section >
        <article>
            <form action="/registerprofile" method="post">
            	<div class="edit"><a href="/profile/edit">프로필 수정</a></div>
            	<div class="edit"><a href="/outofprofile">프로필 삭제</a></div>
            	<div class="edit"><a onclick="clickmsg()">알림 설정</a></div>
                <div>
                    <label for="username">이름</label>
                    <div>
                        <input type="text" id="username" name="username"  value="${profileinfo.username}" disabled>
                    </div>
                </div>
                <div>
                    <label for="age">나이</label>
                    <div>
                        <input type="number" id="age" name="age" placeholder="나이를 입력하세요" value="${profileinfo.age}" disabled>
                    </div>
                </div>
                <div>
                    <label for="height">키</label>
                    <div>
                        <input type="number" id="height" name="height" placeholder="카를 입력하세요" value="${profileinfo.height}" disabled>
                    </div>
                </div>
                <div>
                    <label for="gender">성별</label>
                    <div>
                        <input type="text" id="height" name="height" placeholder="카를 입력하세요" value="${profileinfo.gender}" disabled>
                    </div>
                </div>
                <div>
                    <label for="phone">전화번호</label>
                    <div>
                        <input type="text" id="phone" name="phone" placeholder="핸드폰 번호를 입력하세요" value="${profileinfo.phone}" disabled>
                    </div>
                </div>
                <div>
                    <label for="allergyinfo">알레르기 정보</label>
                    <div>
                        <input type="text" id="allergyinfo" name="allergyinfo" placeholder="알레르기가 있는 식재료를 입력하세요" value="${profileinfo.allergyinfo}" disabled>
                    </div>
                </div>
                 <div>
                     <label>선호하는 음식</label>
                    <div id="nation">
                    <div class="menu">국가별</div>
                    <div class="menudetail">
                        <input type="checkbox" id="korea" name="nation_nm" value="한식"/>
                        <label for="korea">한식</label>
                        <input type="checkbox" id="china" name="nation_nm" value="중국"/>
                        <label for="china">중식</label>
                        <input type="checkbox" id="japan" name="nation_nm" value="일본"/>
                        <label for="japan">일식</label>
                        <input type="checkbox" id="west" name="nation_nm" value="서양"/>
                        <label for="west">서양</label>
                        <input type="checkbox" id="italy" name="nation_nm" value="이탈리아"/>
                        <label for="italy">이탈리아</label>
                        <input type="checkbox" id="eastasia" name="nation_nm" value="동남아시아"/>
                        <label for="eastasia">동남아시아</label>
                        <input type="checkbox" id="fusion" name="nation_nm" value="퓨전"/>
                        <label for="fusion">퓨전</label>
                        <input type="hidden" id="nation_show" value="${profileinfo.nation_nm}">
             
                    </div>
                </div>
                <div id="ingredient">
                    <div class="menu">재료별</div>
                    <div class="menudetail">
                        <input type="checkbox" id="ingre_1" name="ty_nm" value="밥" disabled/>
                        <label for="ingre_1">밥</label>
                        <input type="checkbox" id="ingre_2" name="ty_nm" value="떡/한과" disabled/>
                        <label for="ingre_2">떡/한과</label>
                        <input type="checkbox" id="ingre_3" name="ty_nm" value="만두/면류" disabled/>
                        <label for="ingre_3">만두/면류</label>
                        <input type="checkbox" id="ingre_4" name="ty_nm" value="국" disabled/>
                        <label for="ingre_4">국</label>
                        <input type="checkbox" id="ingre_5" name="ty_nm" value="나물/생채/샐러드" disabled/>
                        <label for="ingre_5">나물/생채/샐러드</label>
                        <input type="checkbox" id="ingre_6" name="ty_nm" value="구이" disabled/>
                        <label for="ingre_6">구이</label>
                        <input type="checkbox" id="ingre_7" name="ty_nm" value="볶음" disabled/>
                        <label for="ingre_7">볶음</label>
                        <input type="checkbox" id="ingre_8" name="ty_nm" value="밑반찬/김치" disabled/>
                        <label for="ingre_8">밑반찬/김치</label>
                        <input type="checkbox" id="ingre_9" name="ty_nm" value="조림" disabled/>
                        <label for="ingre_9">조림</label>
                        <input type="checkbox" id="ingre_10" name="ty_nm" value="찜" disabled/>
                        <label for="ingre_10">찜</label>
                        <input type="checkbox" id="ingre_11" name="ty_nm" value="튀김/커틀릿" disabled/>
                        <label for="ingre_11">튀김/커틀릿</label>
                        <input type="checkbox" id="ingre_12" name="ty_nm" value="찌개/전골/스튜" disabled/>
                        <label for="ingre_12">찌개/전골/스튜</label>
                        <input type="checkbox" id="ingre_13" name="ty_nm" value="도시락/간식" disabled/>
                        <label for="ingre_13">도시락/간식</label>
                        <input type="checkbox" id="ingre_14" name="ty_nm" value="부침" disabled/>
                        <label for="ingre_14">부침</label>
                        <input type="checkbox" id="ingre_15" name="ty_nm" value="양식" disabled/>
                        <label for="ingre_15">양식</label>
                        <input type="checkbox" id="ingre_16" name="ty_nm" value="샌드위치/햄버거" disabled/>
                        <label for="ingre_16">샌드위치/햄버거</label>
                        <input type="checkbox" id="ingre_17" name="ty_nm" value="빵/과자" disabled/>
                        <label for="ingre_17">빵/과자</label>
                        <input type="checkbox" id="ingre_18" name="ty_nm" value="양념장" disabled/>
                        <label for="ingre_18">양념장</label>
                        <input type="checkbox" id="ingre_19" name="ty_nm" value="음료" disabled/>
                        <label for="ingre_19">음료</label>
                        <input type="checkbox" id="ingre_20" name="ty_nm" value="피자" disabled/>
                        <label for="ingre_20">피자</label>
                        <input type="checkbox" id="ingre_21" name="ty_nm" value="그라탕/리조또" disabled/>
                        <label for="ingre_21">그라탕/리조또</label>
                        <input type="hidden" id="ty_show" value="${profileinfo.ty_nm}">     
                    </div>
                </div>
                <div id="level">
                    <div class="menu">난이도</div>
                    <div class="menudetail">
                        <input type="checkbox" id="level_1" name="level_nm" value="초보환영" disabled/>
                        <label for="level_1">초보</label>
                        <input type="checkbox" id="level_2" name="level_nm" value="보통" disabled/>
                        <label for="level_2">보통</label>
                        <input type="checkbox" id="level_3" name="level_nm" value="어려움" disabled/>
                        <label for="level_3">어려움</label>
                        <input type="hidden" id="level_show" value="${profileinfo.level_nm}">    
                    </div>
                </div>
                <hr>
                </div>
                <div class="path">
                    <label for="path">프로필 사진</label>
                    <div>
                        <img src="../${path}${profileinfo.path}">
                    </div>
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
    			<button id="xbtn"><i id="xbutton" class="fas fa-times"></i></button>
    		    <div><img src="${path}/resources/img/GreatLogo.png"></div>
    			<h2>알림 설정</h2>
    			<div class="exheader">예시</div>
    			<div class="ex"><span>*</span><span>*</span><span>*</span><span>*</span><span>*</span><span>*</span></div>
                <div class="ex"><span>초</span><span>분</span><span>시</span><span>일</span><span>월</span><span>요일</span></div>
                <div class="excontent"> 매일 오후 18시마다 실행</div>
                <div class="ex_c"> 0 0 18 * * *</div>
                <div class="excontent"> 매달 1일 00시에 실행</div>
                <div class="ex_c"> 0 0 0 1 * * </div>
                <div><input type="text" id="time"></div>
    			<div><button id="edit" onclick="editmsg()">알림설정</button><button id="delete" onclick="deletetime()">알림삭제</button></div>
    		</div>
    	</div>
    </div>
    <script>
    
    function clickmsg(){
    	$('.modal2').css("display","flex");
    }
    
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
    
    function editmsg(){
        var cron  = $('#time').val() ;
        
        $.ajax({
            type : "POST",
            url: '/profile/edittime',
            data: {cron:cron},
	        success: function (data) {
	    		if(data.res == "success"){
	    		    alert("스케줄러 동작 시간이 변경되었습니다.");
	    		    location.reload();
	    		}
	        },
	        error: function (error) {
	    		console.log(error)
	        }
        });
    }
    function deletetime(){
    	var cron = $('deltime').val();
    	
        
        $.ajax({
	        url: '/profile/deletetime',
	        type: 'POST',
	        data: '',
	        success: function (data) {
	    		if(data.res == "success"){
	    		    alert("스케줄러가 멈췄습니다.");
	    		    location.reload();
	    		}
	        },
	        error: function (error) {
	    		console.log(error)
	        }
	    });
    }
    
    window.onload = function() {
    	
    	 let nation = document.getElementById('nation_show').value.split(',');
    	 let level = document.getElementById('level_show').value.split(',');
    	 let ty = document.getElementById('ty_show').value.split(',');
    	
    	 console.log("nation : " + nation);
    	 console.log("level : " + level);
    	 console.log("ty : " + ty);
    	 
    	 $('input[name="nation_nm"]').each(function(i){//체크된 리스트 저장
    		 let value = $(this);
    		 
             nation.forEach(function(item){
            	 console.log("value.val : " + value.val());
            	 console.log("item : " + item);
            	 if(value.val() == item){
            		 value.attr('checked',true);
            	 }
             }
             );
         });
         
         $('input[name="level_nm"]').each(function(i){//체크된 리스트 저장
        	 let value = $(this);
         
             level.forEach(function(item){
            	 console.log("value.val : " + value.val());
            	 console.log("item : " + item);
            	 if(value.val() == item){
            		 value.attr('checked',true);
            	 }
             }
             );
         });
         
         $('input[name="ty_nm"]').each(function(i){//체크된 리스트 저장
        	 let value = $(this);
             ty.forEach(function(item){
            	 console.log("value.val : " + value.val());
            	 console.log("item : " + item);
            	 if(value.val() == item){
            		 value.attr('checked',true);
            	 }
             }
             );
         });
    	
    }
    </script>
</body>
</html>