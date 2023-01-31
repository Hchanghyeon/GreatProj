<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="${path}/resources/css/userinfo.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    <header>
        <a href="/main/profile?username=${profileinfo.username}&path=${profileinfo.path}&id=${profileinfo.id}"><i class="fas fa-arrow-left"></i></a>
        <div>계정 정보</div>
    </header>
    <div id="app">
    <section >
        <article>
            <form>
            	<div class="edit"><a href="/profile/useredit">계정 정보 수정 및 비밀번호 수정</a></div>
            	<div class="edit"><a href="/outoffamily">계정 탈퇴</a></div>
                <div>
                    <label for="id">아이디</label>
                    <div>
                        <input type="text" id="id" name="id"  value="${userinfo.id}" disabled>
                    </div>
                </div>
                <div>
                    <label for="nickname">닉네임</label>
                    <div>
                        <input type="text" id="nickname" name="nickname" value="${userinfo.nickname}" disabled>
                    </div>
                </div>
                <div>
                    <label for="email">이메일</label>
                    <div>
                        <input type="text" id="email" name="email"  value="${userinfo.email}" disabled>
                    </div>
                </div>
                <div>
                	<label for="address">주소</label>
                </div>
                <div class="add">
                    <input type="text" id="address1" name="address1" placeholder="우편번호" value="${userinfo.address1}" disabled>
                </div>
                <div>
                    <input type="text" id="address2" name="address2" placeholder="도로명 주소" value="${userinfo.address2}" disabled>
                    <input type="text" id="address3" name="address3" placeholder="상세주소" value="${userinfo.address3}" disabled>
                </div>
            </form>
        </article>
        <footer>
        </footer>
    </section>
    </div>
</body>
</html>