<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<script src="${CP }/resources/js/jquery-3.7.1.js"></script>
<script src="${CP }/resources/js/eUtil.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>kakaoLogin</title>
    
</head>
<body>
    <a href="javascript:kakaoLogin();"><img src="${CP }/resources/images/kakao_login_medium.png" /></a>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
    <!-- 카카오톡 연동 API -->
	window.Kakao.init("7a61c303dff46bfedcb6d13327e48c00");

	function kakaoLogin(){
		window.Kakao.Auth.login({
			scope: 'account_email',
			success: function(authObj){
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success: res => {
						const kakao_account= res.kakao_account;
						console.log(kakao_account.email);
						$.ajax({
				            type: "GET",
				            url:"/bdm/user/doCheckEmail.do",
				            asyn:"true",
				            dataType:"html",
				            data:{
				                email: kakao_account.email
				            },
				            success:function(data){//통신 성공
				            	let parsedJSON = JSON.parse(data);
				            
				            	if("1" === parsedJSON.msgId){
				            		window.location.href = "${CP}/beforeMain/doApiLogin.do?email="+kakao_account.email;
				                }else{
				                	alert("등록되지 않은 회원입니다.\n회원가입 창으로 이동합니다");
				    				window.location.href = "${CP}/user/moveToKakao.do?email="+kakao_account.email;
				                }
				            },
				            error:function(data){//실패시 처리
				                console.log("error:"+data);
				            },
				            complete:function(data){//성공/실패와 관계없이 수행!
				                console.log("complete:"+data);
				            }
				        });
					}
				});
			}
		});
	}
	
	//카카오톡으로 로그인하기
	function doCheckEmail(email){
		/* $.ajax({
            type: "GET",
            url:"/bdm/user/doCheckEmail.do",
            asyn:"true",
            dataType:"html",
            data:{
                email: email
            },
            success:function(data){//통신 성공
            	let parsedJSON = JSON.parse(data);
            
            	if("1" === parsedJSON.msgId){
            		window.location.href = "${CP}/beforeMain/popSearchWord.do";
                }else{
                	alert("등록되지 않은 회원입니다.\n회원가입 창으로 이동합니다");
    				window.location.href = "${CP}/beforeMain/moveToReg.do";
                }
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        }); */
		
		/* EClass.callAjax(url, parameters, method, async, function(data) {
			console.log(data);
			
			if(null!=data && "1"==data.msgId){
				alert(data.msgContents);
				window.location.href = "${CP}/beforeMain/popSearchWord.do";
			} else { 
				alert("등록되지 않은 회원입니다.\n회원가입 창으로 이동합니다");
				window.location.href = "${CP}/beforeMain/moveToReg.do";
			}
		});   */
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