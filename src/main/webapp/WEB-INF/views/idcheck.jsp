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
    <link href="${path}/resources/css/idcheck.css" rel="stylesheet" type="text/css">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <!-- Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
    <header>
        <a href="/login"'><i class="fas fa-arrow-left"></i></a>
        <div>ID찾기</div>
    </header>
    <div id="app">
    <section >
        <article>
            <div class="userid">고객님의 아이디는 ${userid}입니다</div>
             <div class="submit">
                <a href="/login/idpwdcheck"><input type="submit" value="임시 비밀번호 발송하러 가기"></a>
             </div>
        </article>
        <footer>

        </footer>
    </section>
    </div>
    <script>
    </script>
</body>
</html>