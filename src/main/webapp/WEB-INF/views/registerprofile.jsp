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
    <link href="${path}/resources/css/registerprofile.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    <header>
        <a href="/profile"><i class="fas fa-arrow-left"></i></a>
        <div>프로필 추가</div>
    </header>
    <div id="app">
    <section >
        <article>
            <form action="/registerprofile" method="post" onsubmit="CheckSubmit();">
                <div>
                    <label for="username">이름</label>
                    <div>
                        <input type="text" id="username" name="username" placeholder="이름을 입력하세요" required>
                    </div>
                </div>
                <div>
                    <label for="age">나이</label>
                    <div>
                        <input type="number" id="age" name="age" placeholder="나이를 입력하세요" required>
                    </div>
                </div>
                <div>
                    <label for="height">키</label>
                    <div>
                        <input type="number" id="height" name="height" placeholder="카를 입력하세요" required>
                    </div>
                </div>
                <div>
                    <label for="gender">성별</label>
                    <div>
                        <input type="radio" name="gender" value="남자" required="required"><label>남자</label>
     					<input type="radio" name="gender" value="여자" required="required"><label>여자</label>
                    </div>
                </div>
                <div>
                    <label for="phone">전화번호</label>
                    <div>
                        <input type="text" id="phone" name="phone" placeholder="핸드폰 번호를 입력하세요" required>
                    </div>
                </div>
                <div>
                    <label for="allergyinfo">알레르기 정보</label>
                       <div class="search_header">
       						 <input type="text" id='search_area'><button onclick="cleartext()">초기화</button>
   						 </div>
   						<div>
   						   	 
       						 <div id='autoMaker'></div>
       						 <div class="sel">선택한 식재료</div>
       						 <div id="Maker_main"></div>
       						 <input type="hidden" id="allergyinfo" name="allergyinfo" value="">
    					</div>
    				<hr>

                </div>
                 <div>
                 	<label>선호하는 음식</label>
                    <div id="nation">
                    <div class="menu">국가별</div>
                    <div class="menudetail">
                        <input type="checkbox" id="korea" name="nation_nm" value="한식" onclick="oneCheckboxNation(this)" checked/>
                        <label for="korea">한식</label>
                        <input type="checkbox" id="china" name="nation_nm" value="중국" onclick="oneCheckboxNation(this)"/>
                        <label for="china">중식</label>
                        <input type="checkbox" id="japan" name="nation_nm" value="일본" onclick="oneCheckboxNation(this)"/>
                        <label for="japan">일식</label>
                        <input type="checkbox" id="west" name="nation_nm" value="서양" onclick="oneCheckboxNation(this)"/>
                        <label for="west">서양</label>
                        <input type="checkbox" id="italy" name="nation_nm" value="이탈리아" onclick="oneCheckboxNation(this)"/>
                        <label for="italy">이탈리아</label>
                        <input type="checkbox" id="eastasia" name="nation_nm" value="동남아시아" onclick="oneCheckboxNation(this)"/>
                        <label for="eastasia">동남아시아</label>
                        <input type="checkbox" id="fusion" name="nation_nm" value="퓨전" onclick="oneCheckboxNation(this)"/>
                        <label for="fusion">퓨전</label>
                        <input type="hidden" id="nation_nm_value" name="nation_nm_value" value="" onclick="oneCheckboxNation(this)"/>
                    </div>
                </div>
                <div id="ingredient">
                    <div class="menu">재료별</div>
                    <div class="menudetail">
                        <input type="checkbox" id="ingre_1" name="ty_nm" value="밥" onclick="oneCheckboxingredient(this)" checked/>
                        <label for="ingre_1">밥</label>
                        <input type="checkbox" id="ingre_2" name="ty_nm" value="떡/한과" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_2">떡/한과</label>
                        <input type="checkbox" id="ingre_3" name="ty_nm" value="만두/면류" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_3">만두/면류</label>
                        <input type="checkbox" id="ingre_4" name="ty_nm" value="국" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_4">국</label>
                        <input type="checkbox" id="ingre_5" name="ty_nm" value="나물/생채/샐러드" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_5">나물/생채/샐러드</label>
                        <input type="checkbox" id="ingre_6" name="ty_nm" value="구이" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_6">구이</label>
                        <input type="checkbox" id="ingre_7" name="ty_nm" value="볶음" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_7">볶음</label>
                        <input type="checkbox" id="ingre_8" name="ty_nm" value="밑반찬/김치" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_8">밑반찬/김치</label>
                        <input type="checkbox" id="ingre_9" name="ty_nm" value="조림" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_9">조림</label>
                        <input type="checkbox" id="ingre_10" name="ty_nm" value="찜" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_10">찜</label>
                        <input type="checkbox" id="ingre_11" name="ty_nm" value="튀김/커틀릿" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_11">튀김/커틀릿</label>
                        <input type="checkbox" id="ingre_12" name="ty_nm" value="찌개/전골/스튜" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_12">찌개/전골/스튜</label>
                        <input type="checkbox" id="ingre_13" name="ty_nm" value="도시락/간식" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_13">도시락/간식</label>
                        <input type="checkbox" id="ingre_14" name="ty_nm" value="부침" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_14">부침</label>
                        <input type="checkbox" id="ingre_15" name="ty_nm" value="양식" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_15">양식</label>
                        <input type="checkbox" id="ingre_16" name="ty_nm" value="샌드위치/햄버거" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_16">샌드위치/햄버거</label>
                        <input type="checkbox" id="ingre_17" name="ty_nm" value="빵/과자" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_17">빵/과자</label>
                        <input type="checkbox" id="ingre_18" name="ty_nm" value="양념장" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_18">양념장</label>
                        <input type="checkbox" id="ingre_19" name="ty_nm" value="음료" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_19">음료</label>
                        <input type="checkbox" id="ingre_20" name="ty_nm" value="피자" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_20">피자</label>
                        <input type="checkbox" id="ingre_21" name="ty_nm" value="그라탕/리조또" onclick="oneCheckboxingredient(this)"/>
                        <label for="ingre_21">그라탕/리조또</label>
                        <input type="hidden" id="ty_nm_value" name="ty_nm_value" value="" onclick="oneCheckboxingredient(this)"/>
                    </div>
                </div>
                <div id="level">
                    <div class="menu">난이도</div>
                    <div class="menudetail">
                        <input type="checkbox" id="level_1" name="level_nm" value="초보환영" onclick="oneCheckboxlevel(this)" checked/>
                        <label for="level_1">초보</label>
                        <input type="checkbox" id="level_2" name="level_nm" value="보통" onclick="oneCheckboxlevel(this)"/>
                        <label for="level_2">보통</label>
                        <input type="checkbox" id="level_3" name="level_nm" value="어려움" onclick="oneCheckboxlevel(this)"/>
                        <label for="level_3">어려움</label>
                        <input type="hidden" id="level_value" name="level_value" value=""/>
                    </div>
                </div>
                <hr>
            </div>
                <div class="path">
                    <label for="path">프로필 사진 선택</label>
                    <div>
                        <div><label><img src="${path}/resources/img/grandmother.png"></label><input type="radio" name="path" value="/resources/img/grandmother.png" required="required"></div>
                      	<div><label><img src="${path}/resources/img/grandfather.png"></label><input type="radio" name="path" value="/resources/img/grandfather.png" required="required"></div>
                      	<div><label><img src="${path}/resources/img/mom.png"></label><input type="radio" name="path" value="/resources/img/mom.png" required="required"></div>
                      	<div><label><img src="${path}/resources/img/dad.png"></label><input type="radio" name="path" value="resources/img/dad.png" required="required"></div>
                      	<div><label><img src="${path}/resources/img/girl.png"></label><input type="radio" name="path" value="/resources/img/girl.png" required="required"></div>
                      	<div><label><img src="${path}/resources/img/boy.png"></label><input type="radio" name="path" value="/resources/img/boy.png" required="required"></div>
                    </div>
                </div>
                <div class="submit">
                    <input type="submit" value="프로필 등록">
                </div>
            </form>
        </article>
        <footer>
        </footer>
    </section>
    </div>
        <script>
        const nation_nm = document.getElementById("nation_nm_value");
        const level_nm = document.getElementById("level_value");
        const ty_nm = document.getElementById("ty_nm_value");
        const allergyinfo = document.getElementById("allergyinfo");
        
        let n = "";
        let l = "";
        let ty = "";
       	let allergytext = "";
       	let all = [];
        
        function CheckSubmit(){
            
            $('input[name="nation_nm"]:checked').each(function(i){//체크된 리스트 저장
                n += $(this).val() + ":";
            });
            
            $('input[name="level_nm"]:checked').each(function(i){//체크된 리스트 저장
                l += $(this).val() + ":";
            });
            
            $('input[name="ty_nm"]:checked').each(function(i){//체크된 리스트 저장
                ty += $(this).val() + ":";
            });
            
            $('.list').each(function(i){//체크된 리스트 저장
            	all.push($(this).text());
            	
            });
            
            const set = new Set(all);
            const uniqueArr = [...set];
            for(let i=0; i<uniqueArr.length; i++){
            	allergytext += uniqueArr[i]+ ":";
            }
           
            l = l.substring(0, l.length-1);
            n = n.substring(0, n.length-1);
            ty = ty.substring(0, ty.length-1);
            allergytext = allergytext.substring(0, allergytext.length-1);
            nation_nm.value = n;
            level_nm.value = l;
            ty_nm.value = ty;
            allergyinfo.value = allergytext;
        }   
        
        function oneCheckboxNation(a){
        	var count = 0;
            var obj = document.getElementsByName("nation_nm");
           
            for(var i=0; i<obj.length; i++){	
                if(obj[i].checked == true){
                	   count++;
                }
            }
            
            if(a.checked == true){
            	if(count >= 1){
            		a.checked = true;
            	}
            	else{
            		a.checked = false;
            	}
            }
            else if(a.checked == false){
            	if(count >= 1){
            		a.checked = false;
            	}
            	else{
            		a.checked = true;
            	}
            }
        }

        function oneCheckboxingredient(a){
        	var count = 0;
            var obj = document.getElementsByName("ty_nm");
            for(var i=0; i<obj.length; i++){	
                if(obj[i].checked == true){
                	   count++;
                }
            }
            
            
            if(a.checked == true){
            	if(count >= 1){
            		a.checked = true;
            	}
            	else{
            		a.checked = false;
            	}
            }
            else if(a.checked == false){
            	if(count >= 1){
            		a.checked = false;
            	}
            	else{
            		a.checked = true;
            	}
            }
        }
        function oneCheckboxlevel(a){
        	var count = 0;
            var obj = document.getElementsByName("level_nm");
            for(var i=0; i<obj.length; i++){	
                if(obj[i].checked == true){
                	   count++;
                }
            }
            
            if(a.checked == true){
            	if(count >= 1){
            		a.checked = true;
            	}
            	else{
            		a.checked = false;
            	}
            }
            else if(a.checked == false){
            	if(count >= 1){
            		a.checked = false;
            	}
            	else{
            		a.checked = true;
            	}
            }
        }
    </script>
    <script src="${path}/resources/js/registerprofile.js"></script>
</body>
</html>