<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Insert title here</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
     
    $("#doLogin").on("click",function(e){
        console.log( "doLogin click!" );
        
        let id = document.querySelector("#id").value;
        if(eUtil.isEmpty(id)==true){
            alert('아이디를 입력 하세요.');
            document.querySelector("#id").focus();
            return;
        }
        
        let pw = document.querySelector("#pw").value;
        if(eUtil.isEmpty(pw)==true){
            alert('비번을 입력 하세요.');
            document.querySelector("#pw").focus();
            return;
        }
        
        if(confirm("로그인 하시겠습니까?")===false) return;
        
        $.ajax({
            type: "POST",
            url:"/bdm/beforeMain/doLogin.do",
            asyn:"true",
            dataType:"json",
            data:{
                "id": id,
                "pw": pw
            },
            success:function(data){//통신 성공
                console.log("data.msgId:"+data.msgId);
                console.log("data.msgContents:"+data.msgContents);
                
                if("10" == data.msgId){
                    alert(data.msgContents);
                    document.querySelector("#id").focus();
                }else if("20" == data.msgId){
                    alert(data.msgContents);
                    document.querySelector("#pw").focus();                 
                }else if("30" == data.msgId){
                    alert(data.msgContents);
                    location.href = "/bdm/beforeMain/popSearchWord.do";
                }
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
    }); //--#doLogin
    
    $("#moveToMain").on("click",function(e){
    	location.href = "/bdm/beforeMain/moveToMain.do";
    }); // --#moveToMain
    
    $("#findId").on("click",function(e){
    	var width = 750;
        var height = 1200;
        var left = (window.innerWidth - width) / 2;
        var top = (window.innerHeight - height) / 2;
        myWindow = window.open('../beforeMain/moveToFindId.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
    }); // --#moveToMain
    
    $("#findPassword").on("click",function(e){
    	var width = 750;
        var height = 1200;
        var left = (window.innerWidth - width) / 2;
        var top = (window.innerHeight - height) / 2;
        myWindow = window.open('../beforeMain/moveToFindPassword.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
    }); // --#moveToMain
    
    $("#moveToReg").on("click",function(e){
    	location.href = "/bdm/user/moveToReg.do";
    }); // --#moveToMain
    
    $("#naverLogin").on("click",function(e){
    	location.href = "/bdm/login/login.do";
    }); // --#moveToMain
    
    
 }); //--document ready
</script>
</head>
<body>
<div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
    <div style="display: inline-block; position: absolute; top: -50px; right: 130px;">
        <button id="moveToMain" style="height: 30px; width: 100px;">BDM</button>
    </div>
    <fieldset style="width: calc(100% - 260px); max-width: 300px; min-width: 240px;">
        <legend>로그인</legend>
        <div>
            <form action="#" method="post">
                <table>
                    <tr>
                        <td>
                            <label for="id">아이디</label>
                        </td>
                        <td>
                            <input type="text" id="id" name="id" size="20" maxlength="30">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="pw">비밀번호</label>
                        </td>
                        <td>
                            <input type="password" id="pw" name="pw" size="20" maxlength="30">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="button" value="로그인" id="doLogin" style="height: 30px; width: 100%; box-sizing: border-box;">
                            <input type="button" value="네이버로그인" id="naverLogin" style="height: 30px; width: 100%; href="javascript:void(0)">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </fieldset>
    <div style="margin-top: 10px;">
        <input type="button" value="아이디 찾기" id="findId" style="height: 30px;">
        <input type="button" value="비밀번호 찾기" id="findPassword" style="height: 30px;">
        <input type="button" value="회원 가입" id="moveToReg" style="height: 30px;">
    </div>
</div>

<!-- 네이버 스크립트
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script>
    var clientId = "xH8CIFYIccFEu1_htDkb"; // 클라이언트 아이디 설정

    var naverLogin = new naver.LoginWithNaverId({
        clientId: clientId, // 클라이언트 아이디 설정
        callbackUrl: "http://localhost:8080/bdm/login/callback.do", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
        isPopup: false,
        callbackHandle: true
    });

    // 네이버 로그인 초기화
    naverLogin.init();

    // 로그인 상태 확인
    window.addEventListener('load', function () {
        naverLogin.getLoginStatus(function (status) {
            if (status) {
                var email = naverLogin.user.getEmail(); // 필수로 설정할것을 받아와 아래처럼 조건문을 줍니다.
                
                console.log(naverLogin.user); 
                
                if (email == undefined || email == null) {
                    alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
                    naverLogin.reprompt();
                    return;
                }
            } else {
                console.log("callback 처리에 실패하였습니다.");
            }
        });
    });

    // 네이버 로그아웃 함수
    function naverLogout() {
        var testPopUp = window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
        setTimeout(function() {
            testPopUp.close();
        }, 1000);
    }
</script>
-->

</body>
</html>