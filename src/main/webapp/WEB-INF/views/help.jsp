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
    <title>헬프센터</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- êµ¬ê¸í°í¸ -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/help.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/slide.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <script src="https://kit.fontawesome.com/a0175a9a60.js" crossorigin="anonymous"></script>
</head>
<body class="body">
	<div style="margin: 0px auto;">
	    <div class="logo"><img src="${path}/resources/img/GreatLogo.png">
	    	<div class="btn"><i class="fas fa-bars"></i></div>
	    </div>
	
	    <header>  <!-- ì ëª© -->
	    </header>
	
	    <section>
	    	 <div class="container">

				<ul class="tabs">
					<li class="tab-link current" data-tab="tab-1">메인화면 이용 가이드</li>
					<li class="tab-link" data-tab="tab-2">식재료 관리화면 가이드</li>
					<li class="tab-link" data-tab="tab-3">식재료 리스트화면 가이드</li>
					<li class="tab-link" data-tab="tab-4">스마트 레시피 가이드</li>
					<li class="tab-link" data-tab="tab-5">탄소 마일리지 가이드</li>
				</ul>
			
				<div id="tab-1" class="tab-content current">
					<div id="title">🟢 메인화면 이용 가이드</div>
					<img src="${path}/resources/img/help_img/help_main1.png"><br>
					<div id="bodytxt">다양한 정보를 확인할 수 있는 "메인화면"에 대한 가이드입니다!</div><br>
					
					<div id="head">1) 레시피 추천</div>
					<div id="bodytxt">이곳에서는 사용자의 프로필 정보를 바탕으로 레시피를 추천해줍니다.</div>
					<img src="${path}/resources/img/help_img/help_main2.png" style="width:40%"><br>
					
					<div id="head">2) 오늘 정보</div>
					<div id="bodytxt">이곳에서는 오늘의 날씨와 적립된 탄소 마일리지를 확인할 수 있습니다.</div>
					<div id="bodytxt"><b>새싹 아이콘</b>을 터치하면 <b>월간 새싹</b> 화면으로 이동합니다.</div>
					<img src="${path}/resources/img/help_img/help_main3.png" style="width:40%"><br>
					
					<div id="head">3) 식재료 리스트</div>
					<div id="bodytxt">이곳에서는 보관기간이 얼마 남지않은 식재료 리스트를 확인할 수 있습니다.</div>
					<div id="bodytxt"><b>[더보기]</b>를 선택하면 <b>식재료 리스트</b> 화면으로 이동합니다.</div>
					<img src="${path}/resources/img/help_img/help_main4.png" style="width:40%"><br>
					
					<div id="head">4) 메모</div>
					<div id="bodytxt">이곳에서는 가족들이 남긴 메모를 확인할 수 있습니다.</div>
					<div id="bodytxt"><b>[생성]</b>을 선택하면 가족들에게 메모를 남길 수 있습니다.</div>
					<img src="${path}/resources/img/help_img/help_main5.png" style="width:40%"><br>
					
					<br><div id="title">🛑 문제가 있나요?</div><br>
					
					<div id="head">Q. 추천 레시피가 보이지 않아요!</div>
					<div id="bodytxt">✅ 프로필 정보가 올바르게 설정되어 있는지 확인해보세요!</div><br>
					
					<div id="head">Q. 식재료 리스트가 보이지 않아요!</div>
					<div id="bodytxt">✅ 저장된 재료가 있는지 확인해보세요!</div><br>
					
				</div>
				
				<div id="tab-2" class="tab-content">
					<div id="title">🟢 식재료 관리화면 가이드</div>
					<img src="${path}/resources/img/help_img/ready.jpg"><br>
					<div id="bodytxt">식재료를 자동으로 인식하고 저장할 수 있는 "식재료 화면"에 대한 가이드입니다!</div><br>
					
					<div id="head">1) 식재료 추가(보관)</div>
					<div id="bodytxt">인공지능이 현재 냉장고 안의 재료를 인식하여 라벨을 붙여줍니다.</div>
					<div id="bodytxt">추가할 재료를 선택하여 <b>[리스트 추가 >>]</b> 버튼을 누르면 선택된 재료가 <b>식재료 리스트</b>에 추가됩니다.</div>
					<img src="${path}/resources/img/help_img/ready.jpg" style="width:40%"><br>
					
					<br><div id="title">🛑 문제가 있나요?</div><br>
					
					<div id="head">Q. 재료 인식이 안돼요!</div>
					<div id="bodytxt">
						✅ 재료가 가려져있는지 확인해보세요!<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;인식되지 않은 식재료는 다음과 같이 수동으로 추가할 수 있어요!
					</div><br>
					<div id="bodytxt">1. 오른쪽 하단의 <b>+버튼</b>을 눌러주세요.</div>
					<div id="bodytxt">2. 추가할 재료를 왼쪽 상단에서 오른쪽 하단으로 드레그 하세요.</div>
					<div id="bodytxt">3. 재료의 이름, 보관 기간 등의 정보를 입력해주세요.</div>
					<div id="bodytxt">4. <b>[입력]</b>버튼을 누르면 저장이 완료됩니다.</div>
					<img src="${path}/resources/img/help_img/ready.jpg" style="width:40%"><br>
				</div>
				
				<div id="tab-3" class="tab-content">
					<div id="title">🟢 식재료 리스트화면 가이드</div>
					<img src="${path}/resources/img/help_img/help_list1.png"><br>
					<div id="bodytxt">등록된 식재료를 확인하고 관리할 수 있는 "식재료 리스트화면"에 대한 가이드입니다!</div><br>
					
					<div id="head">1) 식재료 정보보기</div>
					<div id="bodytxt">등록된 식재료를 선택하면 등록날짜와 보관기간을 확인할 수 있습니다.</div>
					<img src="${path}/resources/img/help_img/help_list2.png" style="width:40%"><br>
					
					<div id="head">2) 식재료 찾기</div>
					<div id="bodytxt">식재료 검색 탭에서 재료 이름을 입력하면 보관된 재료를 확인할 수 있습니다.</div>
					<div id="bodytxt"><b>[유통기한]</b>버튼을 누르면 유통기한이 얼마 남지않은 재료를 보여줍니다.</div>
					<div id="bodytxt"><b>[알레르기]</b>버튼을 누르면 프로필에 입력한 정보를 바탕으로 알레르기 유발 재료를 보여줍니다.</div>
					<img src="${path}/resources/img/help_img/help_list3.png" style="width:40%"><br>
					
					<br><div id="title">🛑 문제가 있나요?</div><br>
					
					<div id="head">Q. 식재료 리스트에 아무것도 보이지 않아요!</div>
					<div id="bodytxt">✅ 식재료가 제대로 저장되었는지 확인해보세요!</div><br>
					
					<div id="head">Q. [유통기한]버튼을 눌렀는데 아무것도 보이지 않아요!</div>
					<div id="bodytxt">✅ 보관된 식재료들의 유통기한이 충분히 남아있는지 확인해보세요!<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;유통기한이 3일 이내로 남은 재료만 표시됩니다!</div><br>

					<div id="head">Q. [알레르기]버튼을 눌렀는데 아무것도 보이지 않아요!</div>
					<div id="bodytxt">✅ 프로필에 알레르기 정보가 제대로 입력되어 있는지 확인해보세요!</div><br>
				</div>
				
				<div id="tab-4" class="tab-content">
					<div id="title">🟢 스마트 레시피 가이드</div>
					<img src="${path}/resources/img/help_img/help_recipe1.png"><br>
					<div id="bodytxt">다양한 레시피를 확인할 수 있는 "스마트 레시피화면"에 대한 가이드입니다!</div><br>
					
					<div id="head">1) 추천 레시피 보기</div>
					<div id="bodytxt">당신을 위한 레시피는 프로필의 선호하는 음식 정보를 바탕으로 레시피를 추천해줍니다.</div>
					<div id="bodytxt"><b>[더보기]</b>버튼을 누르면 추천 레시피를 최대 20개까지 보여줍니다.</div>
					<img src="${path}/resources/img/help_img/help_recipe2.png" style="width:40%"><br>
					
					<div id="head">2) 레시피 검색</div>
					<div id="bodytxt">고르는 레시피에서 검색할 조건을 선택합니다.</div>
					<div id="bodytxt"><b>[조건 검색]</b>버튼을 누르면 선택된 조건으로 검색한 레시피를 보여줍니다.</div>
					<img src="${path}/resources/img/help_img/help_recipe3.png" style="width:40%"><br>
					
					<br><div id="title">🛑 문제가 있나요?</div><br>
					
					<div id="head">Q. 추천 레시피가 보이지 않아요!</div>
					<div id="bodytxt">✅ 프로필 정보가 올바르게 설정되어 있는지 확인해보세요!</div><br>
				</div>
				
				<div id="tab-5" class="tab-content">
					<div id="title">🟢 탄소 마일리지 가이드</div>
					<img src="${path}/resources/img/help_img/help_sprout1.png"><br>
					<div id="bodytxt">새싹 포인트를 확인하고 사용할 수 있는 "탄소 마일리지화면"에 대한 가이드입니다!</div><br>
					
					<div id="head">1) 새싹 포인트</div>
					<div id="bodytxt">한 달 동안 우리 가족이 새싹 포인트를 획득한 날을 확인할 수 있습니다.</div>
					<div id="bodytxt">하단에서는 현재 보유중인 새싹 포인트를 확인할 수 있습니다.</div>
					<img src="${path}/resources/img/help_img/help_sprout2.png" style="width:40%"><br>
					
					<div id="head">2) 그린 펀드</div>
					<div id="bodytxt">이곳에서는 탄소절감을 위해 기술을 개발하는 기업에 간접적으로 투자할 수 있습니다.</div>
					<img src="${path}/resources/img/help_img/help_sprout3.png" style="width:40%"><br>
					<div id="bodytxt">기업명을 선택하면 팝업창이 열리고 사용할 새싹 포인트를 입력하고 <b>[펀딩하기]</b>버튼을 누르면 새싹포인트를 사용할 수 있습니다.</div>
					<img src="${path}/resources/img/help_img/help_sprout4.png" style="width:40%"><br>
					
					<br><div id="title">🛑 문제가 있나요?</div><br>
					
					<div id="head">Q. 펀딩을 할 수 없어요!</div>
					<div id="bodytxt">✅ 남은 새싹 포인트를 확인해보세요!</div><br>
				</div>
			
			</div>
	    </section>
    </div>
    
    <%@ include file="slide.jsp" %>
    
    <script>
	    $(document).ready(function(){
	    	$('ul.tabs li').click(function(){
	    		var tab_id = $(this).attr('data-tab');
	
	    		$('ul.tabs li').removeClass('current');
	    		$('.tab-content').removeClass('current');
	
	    		$(this).addClass('current');
	    		$("#"+tab_id).addClass('current');
	    	})
	    })
    </script>
</body>
</html> 