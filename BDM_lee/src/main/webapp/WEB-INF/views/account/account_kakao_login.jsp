<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>kakaoLogin</title>
    
</head>
<body>
    <a href="javascript:kakaoLogin();"><img src="resources/images/kakao_login_medium.png" /></a>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
    <!-- 카카오톡 연동 API -->
	window.Kakao.init("7a61c303dff46bfedcb6d13327e48c00");

	function kakaoLogin(){
		window.Kakao.Auth.login({
			scope: 'profile, account_email',
			success: function(authObj){
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success: res => {
						const kakao_account= res.kakao_account;
						console.log(kakao_account);
						console.log(kakao_account.profile.nickname);
						console.log(kakao_account.email);
						doLogin(kakao_account.email,kakao_account.profile.nickname);
					}
				});
			}
		});
	}
	
	//카카오톡으로 로그인하기
	function doLogin(email,nickname){
		let url = "${hContext}/member/do_sns_login.do";
		let parameters = {"memberId" :email,
						  "nickname" :nickname
						  };
		let method = "POST";
		let async  = true; 
		
		EClass.callAjax(url, parameters, method, async, function(data) {
			console.log(data);
			
			if(null!=data && "20"==data.msgId){
				alert(data.msgContents);
				window.location.href = "${hContext}/houses/home_view.do";
			} else { 
				alert("등록되지 않은 회원입니다.\n회원가입 창으로 이동합니다");
				window.location.href = "${hContext}/member/reg_view.do";
			}
		});  
	}
        // const SocialKakao = ()=>
		// {
		//     const Rest_api_key='a634abe8d0e118ebad840b88f3ff20e4' //REST API KEY
		//     const redirect_uri = 'http://localhost:8080/bdm' //Redirect URI
		//     // oauth 요청 URL
		//     const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`
		//     const handleLogin = ()=>{
		//         window.location.href = kakaoURL
		//     }
		//     return(
		//     <>
		//     <button onClick={handleLogin}>카카오 로그인</button>
		//     </>
		//     )
		// }
		// export default SocialKakao
    </script>
    //rest key a634abe8d0e118ebad840b88f3ff20e4
    //js key 7a61c303dff46bfedcb6d13327e48c00
</body>
</html>