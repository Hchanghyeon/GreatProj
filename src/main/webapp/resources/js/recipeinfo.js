window.onload = function() {
	let a = document.getElementsByClassName("imgclass");	
	if(a.length == 0){
		let div = document.createElement("div");
		div.setAttribute('class','noneimg');
		div.innerHTML = "이미지가 제공되지 않습니다";
		document.getElementById("process_img").appendChild(div);
	}
	  
}
  changeBackground = () => {
        document.getElementById("body").style.backgroundColor = "#4caf50";
        document.getElementById("hambtn").style.color = "white";
        document.getElementById("logo").style.display = "none";
        document.getElementById("logo2").style.display = "block";
    }

    setTimeout(changeBackground, 300);