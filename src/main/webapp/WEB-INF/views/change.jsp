<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scaleable=no,maximum-scale=1,width=device-width">
    <title>Great 디스플레이 움직이기</title>
    <!-- fontawesome 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <!-- 구글폰트 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link href="${path}/resources/css/change.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <img src="${path}/resources/img/GreatLogo.png">
    </header>
    <div class="back"><a href="javascript:window.history.back();">뒤로가기</a></div>
    <div>
        <section>
            <h1>디스플레이 위치 변경</h1>
            <div class="key">본인의 키를 입력하세요</div>
            <div class="key2"><input type="text"></div>
            <div class="btn"><button>변경하기</button></div>
            <p>
                그레잇은 사용자의 키에 맞게 인체공학적으로 편리한 눈높이를 제공합니다.
                수동으로 맞추시려면 아래 버튼을 눌러주세요.
            </p>
            <div>
            <div class="change">
               <button>올림</button>
            </div>
            <div class="change">
                <button>내림</button>
             </div>
            </div>
            </div>
        </section>
    </div>
</body>
</html>