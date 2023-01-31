const img_size = document.getElementById("image");
const img_url = "/resources/img/img.png";
let button_pos = {};
var c;
// 문서 로딩이 끝나면
$(document).ready(function(){
  // 캔버스 가져오기
  var canvas = document.getElementById("bCanvas");
  var V_LTX = document.getElementById("LTX");
  var V_LTY = document.getElementById("LTY");
  var V_RDX = document.getElementById("RDX");
  var V_RDY = document.getElementById("RDY");
  var V_X = document.getElementById("X");
  var V_Y = document.getElementById("Y");

  // 캔버스의 가로 길이는 사진의 가로 길이
  // 캔버스의 세로 길이는 사진의 세로 길이
  canvas.width=img_size.width;
  canvas.height=img_size.height;

  // 캔버스에 2d 기능을 그릴 수 있게 getContext로 설정 해주기
  var ctx = canvas.getContext('2d');
  
  // 라인 굵기 3
  ctx.lineWidth = 3;

  // 라인 색깔 블랙
  ctx.strokeStyle = "lightgreen";

  var sX,sY,cX,cY;
  var width, height;
  var r_width, r_height, LTX, LTY, RDY, RDY, X, Y;

  // 문서의 전역 왼쪽에서부터 얼마나 떨어져있는지 좌표(간단하게 가로)
  var canvasX = $("#bCanvas").offset().left;
  // 문서의 전역 위에서부터 얼마나 떨어져있는지 좌표(간단하게 세로)
  var canvasY = $("#bCanvas").offset().top;

  var draw = false;

  // 모달 창에서 띄울 이미지 선택
  var backimg = document.getElementById("backimg");
  var imgtrans = document.getElementById("im");

  // 버튼을 눌렀을 때 
  function button_check(sX,sY){
    if(button_pos){
      if((button_pos.startX<=sX&&sX<=button_pos.endX)&&(button_pos.startY<=sY&&sY<=button_pos.endY)){

          $('.modal2').css("display","flex");

          backimg.src = c;
          imgtrans.value = c;
          
          V_LTX.value = (LTX / canvas.width).toFixed(2);
          V_LTY.value = (LTY / canvas.height).toFixed(2);
          V_RDX.value = (RDX / canvas.width).toFixed(2);
          V_RDY.value = (RDY / canvas.height).toFixed(2);
          V_X.value = Math.round((X / canvas.width) * 100);
          V_Y.value = Math.round((Y / canvas.height) * 100);
          

         	
      }
    }
  }

  // 버튼 이미지 가져오기
  const button_url = "/resources/img/plus.png";

  // 새로운 이미지 만들기
  let img_btn = new Image();

  // 보안적인 부분으로 그냥 명시;
  img_btn.crossOrigin="Anonymous"

  // 이미지의 소스를 버튼 이미지로 가져오기
  img_btn.src = button_url;



  // 캔버스에 버튼 그리기
  function drawTest(ctx,canvas,sX,sY,cX,cY){
    // 캔버스 전체에 불투명도 주기
    ctx.fillStyle = "rgba(17, 172, 63, 0.3)";
    // 이미지 크기 만큼 그리기
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // 사각형의 크기 가로 (cX-sX) 세로 (cY-sY) 임
    console.log(cX-sX,cY-sY);

    // 사각형의 크기만큼만 클리어 해주기
    ctx.clearRect(sX,sY,cX-sX,cY-sY);
    
    r_width = cX-sX;
    r_height = cY-sY;
    LTX = sX;
    LTY = sY;
    RDX = sX+r_width;
    RDY = sY+r_height;
    X = sX + r_width/2;
    Y = sY + r_height/2;
    

    // 그리고 그 사각형 옆에다가 버튼 그리기
    ctx.drawImage(img_btn,(sX+(cX-sX))+10,(sY+(cY-sY)/2)-25,50,50);

    // 버튼의 위치 저장해놓기
    button_pos={
      startX : (sX+(cX-sX))+10,
      startY : (sY+(cY-sY)/2)-25,
      endX : (sX+(cX-sX))+10 + 50,
      endY : (sY+(cY-sY)/2)-25 + 50
    }
  }
  
  // 사각형 이미지 만들기
  function drawCanvas (x,y,width,height){
    var canvas = document.createElement("canvas");
    var img = new Image();
    img.crossOrigin="Anonymous";
    img.src = img_url;

    
    var w,h;
  
    if(Math.abs(width)<=Math.abs(height)){
      h = img_size.height;
      w = h*width/height;
    } else {
      w = img_size.width;
      h = w*height/width;
    }
    canvas.width = w;
    canvas.height = h;
    
    img.addEventListener("load", async function () {
      var RATE = img.width/img_size.width;
      canvas.getContext('2d').drawImage(img, x*RATE, y*RATE, width*RATE, height*RATE, (w-w*0.9)/2, (h-h*0.9)/2, w*0.9, h*0.9);
      
      c = canvas.toDataURL("image/png");// 여기에 들어가있어야함 캔버스 이미지 로드가 끝난 후에 저장 됩니당.
      
      
      //let a = document.querySelector('a');
      //a.href = c
    },false);
    
    /*
    document.querySelector('a').addEventListener('click', event => {
      console.log(canvas.toDataURL("image/png"))
      event.target.href = canvas.toDataURL("image/png")
      }
    );
    */
  }
  // click 시 draw 시작
  /*
  $("#bCanvas").mousedown(function(e){
    ctx.clearRect(0,0,canvas.width,canvas.height);
    sX=parseInt(e.clientX-canvasX);
    sY=parseInt(e.clientY-canvasY);

    button_check(sX,sY);

    $("body").css({
      "background-color":"white",
    });
  
    draw = true;
  })
*/

// 손가락이 닿았을 때
  $("#bCanvas").bind('touchstart', function(e){
    // 이미 그려져있던 사각형 지우기
    ctx.clearRect(0,0,canvas.width,canvas.height); 
    // 첫번째 터치 오브젝트 가져오기
    let touches = e.originalEvent.touches[0];


    // clientX : 브라우저 페이지에서의 X좌표 위치를 반환하나 스크롤은 무시하고 해당 페이지의 상단을 0으로 측정합니다. 브라우저 화면을 기준으로 한 X 좌표
    // canvasX : 캔버스가 시작되는 x값 좌측의 여백을 의미한다.
    sX=parseInt(touches.clientX-canvasX); // 뭔진 모르겠지만 클릭한 곳의 X좌표를 구하려면 clientX에서 canvasX를 빼야한다고함
    sY=parseInt(touches.clientY-canvasY); // 뭔진 모르겠지만 클릭한 곳의 Y좌표를 구하려면 clientY에서 canvasY를 빼야한다고함

    // 처음 클릭한 X와 Y좌표를 토대로 버튼 그리기
    button_check(sX,sY);
  
    draw = true;
    //console.log("시작")
  })

/*
  $("#bCanvas").mousemove(function(e){
    if(draw){
      cX=parseInt(e.clientX-canvasX);
      cY=parseInt(e.clientY-canvasY);
      ctx.clearRect(0,0,canvas.width,canvas.height); //clear canvas
      ctx.strokeRect(sX,sY,cX-sX,cY-sY);
    }
  })
*/

// 손가락이 움직이고 있을 때
  $("#bCanvas").bind('touchmove', function(e){
    // 처음 손가락이 닿은 상태이면
    if(draw){
      // 처음 닿은 손가락의 위치 이벤트 가져오기
      let touches = e.originalEvent.touches[0];
      // 닿은 손가락의 X, Y 값 저장
      cX=parseInt(touches.clientX-canvasX); 
      cY=parseInt(touches.clientY-canvasY);
      // 캔버스 지우기
      ctx.clearRect(0,0,canvas.width,canvas.height); //clear canvas
      // strokeRect = 테두리만 있는 사각형을 그림, 처음 클릭했던 X, Y좌표 , width 가로길이, height 세로길이)
      ctx.strokeRect(sX,sY,cX-sX,cY-sY);
      //console.log("미들")
    }
  })

  /*
  // 마우스 놓으면 rectangle 완성 및 popup 생성
  $("#bCanvas").mouseup(function(e){
    draw = false;
    
    // 일정 크기 이상의 사각형일때 crop
    var threshold = 10;
    if(Math.abs(cX-sX)>threshold&&Math.abs(cY-sY)>threshold){
      //drawTest(e,ctx,canvas,sX,sY,cX,cY);
    	drawCanvas(sX,sY,cX-sX,cY-sY);
    }
  })
*/

// 손가락이 떼어졌을 때
  $("#bCanvas").bind('touchend',function(e){
    draw = false;
    let touches = e.originalEvent.changedTouches[0];
 
    // 드래그를 했을 때 마지막의 값과 터치를 뗐을 때 마지막의 값이 다르면 그리기(이렇게 해야 손을 뗐을 때 다른 곳에 또 안그림)
    if(!(sX==parseInt(touches.clientX-canvasX)&&sY==parseInt(touches.clientY-canvasY))){
      // 버튼 그리기
      drawTest(ctx,canvas,sX,sY,cX,cY);

      // 사각형 그리기 
      drawCanvas(sX,sY,cX-sX,cY-sY);
    }
    //console.log("엔드")
  	});
  });
