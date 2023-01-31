 setTimeout(function () {
            $('.body').addClass('modal-open');
            $('section img').addClass('on');
        }, 3000);

        function checkid() {
            var userId = $('#familyid').val();
            var userPwd = $('#familypw').val();

            var loader = $("div.loading-container");
            var content = $("div.loading");
            //var container = $("div.loading-container"); 
            loader.css("display", "block");
            content.css("display", "block");
            //container.css("display","none"); 


            $.ajax({
                type: "POST",
                url: '/login',
                data: { userId: userId, userPwd: userPwd },
                success: function (data) {
                    if (data == "로그인")
                        window.location.href = "/profile";
                    else {
                        loader.css("display", "none");
                        
                        let login = document.getElementById('login');
               			login.innerHTML = "로그인에 실패하셨습니다. <b style='color:red'>아이디</b>와 <b style='color:red'>패스워드</b>를 다시 확인해주세요"
               			login.style.fontSize = '14px';
               			login.style.padding = '3px 20px';
                 		
                    }
                }
            });
        }