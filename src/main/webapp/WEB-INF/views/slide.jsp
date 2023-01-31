 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
 <div class="slide" id="slide">
        <div class="slide-title">
          <div><img src="${path}<%=profile.getPath()%>"> </div>
          <div><%= profile.getUsername() %>님,<br>환영합니다</div>
        </div>
        <ul>
          <li><a onclick="loading('/main')">홈</a></li>
          <li><a onclick="loading('/profile/userinfoproc')">계정 정보</a></li>
          <li><a onclick="loading('/profile/infoproc')">프로필 정보</a></li>
          <li><a onclick="loading('/change')">디스플레이 위치 변경</a></li>
          <li><a href="http://192.168.0.250:8282/">냉장고 내부 보기</a></li>
          <li><a onclick="loading('/insideView')">식재료 관리</a></li>
		  <li><a onclick="loading('/foodlist')">식재료 리스트</a></li>
          <li><a onclick="loading('/recipemain')">스마트 레시피</a></li>
          <li><a onclick="loading('/emartmall')">이마트 몰</a></li>
          <li><a onclick="loading('/sprout')">탄소 마일리지</a></li>
          <li><a onclick="loading('/help')">헬프센터</a></li>
        </ul>
        <div class="profileuserlogout"><a href="/profile/userlogout">계정 로그아웃</a></div>
        <div class="profilelogout"><a href="/profile/logout">프로필 선택화면으로 가기</a></div>  
</div>
         <div class="loading-container">
   			 <div class="loading"></div>
    		<div id="loading-text">loading</div>
		</div>
<script>
$(".btn").click(function(){  //버튼 클릭 시
    if($("#slide").hasClass('on'))
    {
     $("#slide").removeClass('on');;
    }else{
     $("#slide").addClass('on');  //슬라이드 메뉴 원복
    }
});

function loading(go){
	var loader = $(".loading-container"); 
	var content =  $(".loading"); 
	
	loader.css("display","block");
	content.css("display","block");

	window.location.href=go;
}

</script>