    changeBackground = () => {
        document.getElementById("body").style.backgroundColor = "#4caf50";
        document.getElementById("hambtn").style.color = "white";
        document.getElementById("logo").style.display = "none";
        document.getElementById("logo2").style.display = "block";
    }

    setTimeout(changeBackground, 300);

    more = () => {
        const fade = document.getElementsByClassName("fade");
        for (let i = 0; i < fade.length; i++) {
            fade[i].style.display = "block";
        }
        document.getElementById("more").style.display = "none";
        document.getElementById("close").style.display = "flex";
    }

    closemore = () => {
        console.log("눌림");
        const fade = document.getElementsByClassName("fade");
        for (let i = 0; i < fade.length; i++) {
            fade[i].style.display = "none";
        }
        document.getElementById("more").style.display = "flex";
        document.getElementById("close").style.display = "none";
    }
    
    
   	$('div[id="search"]').click(function() {
		console.log("눌림 ");
		$("#menulist").empty();
		
		let nation_nm = [];
		nation_nm.push($('input[name="nation_nm"]:checked').val());
		
		let level_nm = [];
		level_nm.push($('input[name="level_nm"]:checked').val());
		
		let ty_nm = [];
        
        $('input[name="ty_nm"]:checked').each(function(i){//체크된 리스트 저장
    
        	ty_nm.push($(this).val());
        });
        
        for(let i=0; i<Array.length;i++){
			console.log(Array[i]);
		}
		var objParams = {
						"ty_nm" : ty_nm,	
						"nation_nm" : nation_nm,
						"level_nm" : level_nm
       };
		
		 $.ajax({
                    url         :   "/recipe/getRecipe",
                    dataType    :   "json",
                    contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                    type        :   "post",
                    data        :   objParams,
                    success     :   function(jarray){
                    	    for(let i in jarray){
			let newDIV = document.createElement('div');
			newDIV.setAttribute('id','listmain');
			let a = document.createElement("a");
			a.setAttribute("href","/recipedetail?no="+jarray[i].recipe_id);
			let img = document.createElement("img");
			img.setAttribute("src","/resources/img/basic_img/" + jarray[i].img_path);
			let div1 = document.createElement("div");
			div1.setAttribute('class','side2');
			div1.innerHTML=jarray[i].nation_nm;
			newDIV.appendChild(div1);
			let div2 = document.createElement("div");
			div2.innerHTML=jarray[i].recipe_nm_ko;
			newDIV.appendChild(a);
			a.appendChild(img);
			a.appendChild(div2);
			document.getElementById("menulist").appendChild(newDIV);
		}		
                         
                    },
                    error       :   function(request, status, error){
                        console.log("AJAX_ERROR");
                    }
                });
        
    
	});