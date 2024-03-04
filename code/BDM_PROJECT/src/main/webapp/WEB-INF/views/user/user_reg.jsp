<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${CP }/resources/js/eUtil.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<title>회원 가입</title>
<style>
	.black-han,
	.noto-sans {
	    font-weight: 400;
	    font-style: normal;
	}
	
	.black-han {
	    font-family: "Black Han Sans", sans-serif;
	}
	
	.noto-sans {
	    font-family: "Noto Sans KR", sans-serif;
	    font-optical-sizing: auto;
	}
	
	body {
		font-family: sans-serif;
	    background-color: #f7e9e8;
	    margin: 0;
	    padding: 0;
	}
	.wrap{
	padding: 20px 0;
	}
	label {
    	font-weight: bold;
	}
	h1{
		text-align:center;
	}
	.btn{
		margin:7px 0;
		margin-bottom:15px;
	}
</style>
<script>
document.addEventListener("DOMContentLoaded", function(){
    console.log("DOMContentLoaded");
    
    const moveToMainBtn = document.querySelector("#moveToMain");
    // const moveToListBtn = document.getElementById("moveToList");
    const doSaveBtn = window.document.querySelector("#doSave");
    const regForm = document.querySelector("#userRegFrm");
    const doCheckIdBtn = document.querySelector("#doCheckId");
    const doCheckEmailBtn = document.querySelector("#doCheckEmail");
    const matchText = document.getElementById('passwordMatchText');
    const emailInput = document.querySelector("#email");
	
    // Add event listener to email input to detect changes
    emailInput.addEventListener("input", function(){
    	// Reset email duplication check status
        document.querySelector("#emailCheck").value = 0;
        // Disable email authentication button
        document.querySelector("#doCheckEmail").disabled = false;
        // Clear the authentication code input field and message
        document.querySelector(".mail-check-input").value = "";
        document.querySelector("#mail-check-warn").innerText = "";
    });
    
    // Add an event listener to the confirmPw input to check for password match on each input
    document.getElementById('confirmPw').addEventListener('input', function () {
        // Get the values of both password and confirm password fields
        var password = document.getElementById('pw').value;
        var confirmPassword = this.value;

        // Check if the passwords match
        if (password === confirmPassword) {
            matchText.innerText = '비밀번호가 일치합니다.';
        } else {
            matchText.innerText = '비밀번호가 일치하지 않습니다.';
        }
    });
    
    moveToMainBtn.addEventListener("click", function(e){
        console.log("moveToMainBtn");
        
        window.location.href = "/bdm/beforeMain/moveToMain.do";
    });
    
    doCheckEmailBtn.addEventListener("click", function(e){
        console.log('doCheckEmailBtn');
        let email = regForm.email.value;
        if(eUtil.isEmpty(email)==true){
            alert('이메일을 입력 하세요.');
            regForm.email.focus();
            return;
        }
        
        $.ajax({
            type: "GET",
            url:"/bdm/user/doCheckEmail.do",
            asyn:"true",
            dataType:"html",
            data:{
                email: email
            },
            success:function(data){//통신 성공     
                console.log("data:" + data);
               let parsedJSON = JSON.parse(data);
               if("1" === parsedJSON.msgId){
                   alert(parsedJSON.msgContents);
                   document.querySelector("#emailCheck").value = 0;
               }else{
                   alert(parsedJSON.msgContents);
                   document.querySelector("#emailCheck").value = 1;
               }
            
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
    });
    $(document).ready(function () {
        $('#mail-Check-Btn').click(function() {
        	if(document.querySelector("#emailCheck").value == 0){
                alert('이메일 중복체크를 수행하세요.');
                document.querySelector("#emailCheck").focus();
                return;
            }
        	
        	
            const email = $('#email').val()// 이메일 주소값 얻어오기!
            console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
            const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 

            
            /* console.log("javascript ppl_input:"+document.querySelector(".ppl_input").value);    */
             
             if(eUtil.isEmpty(email) == true){
                alert('이메일을 입력 하세요.');
                //$("#email").focus();//사용자 id에 포커스
                document.querySelector("#email").focus();
                return;
             }
            
            $.ajax({
                type : 'get',
                url : '/bdm/user/mailCheck.do?email='+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
                success : function (data) {
                    console.log("data : " +  data);
                    checkInput.attr('disabled',false);
                    code =data;
                    alert('인증번호가 전송되었습니다.')
                }           
            }); // end ajax
        }); // end send eamil

        // 인증번호 비교 
        // blur -> focus가 벗어나는 경우 발생
        $('.mail-check-input').blur(function () {
            const inputCode = $(this).val();
            const $resultMsg = $('#mail-check-warn'); // 이 위치로 이동되었습니다.

            if(inputCode == code){
                $resultMsg.html('인증번호가 일치합니다.');
            }else{
                $resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
            }
        });


        });
    
    
    doCheckIdBtn.addEventListener("click", function(e){
        console.log('doCheckIdBtn');
        let id = regForm.id.value;
        if(eUtil.isEmpty(id)==true){
            alert('아이디를 입력 하세요.');
            regForm.id.focus();
            return;
        }
        
        $.ajax({
            type: "GET",
            url:"/bdm/user/doCheckId.do",
            asyn:"true",
            dataType:"html",
            data:{
                id: id
            },
            success:function(data){//통신 성공     
                console.log("data:" + data);
               let parsedJSON = JSON.parse(data);
               if("1" === parsedJSON.msgId){
                   alert(parsedJSON.msgContents);
                   document.querySelector("#idCheck").value = 0;
               }else{
                   alert(parsedJSON.msgContents);
                   document.querySelector("#idCheck").value = 1;
                   
                   
               }
            
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
    });
   
    
    doSaveBtn.addEventListener("click", function(e){
        var radioBtn = document.getElementsByName('flexRadioDefault');
        var radioValue = '';
        const $resultMsg = $('#mail-check-warn');
        
        
        for(var i=0; i<radioBtn.length; i++){
            if(radioBtn[i].checked){
                radioValue = radioBtn[i].value;
                break;
            }
        }
        
        console.log("doSaveBtn");
        console.log("javascript id:"+document.querySelector("#id").value);
        console.log("javascript pw:"+document.querySelector("#pw").value);
        console.log("javascript email:"+document.querySelector("#email").value);
        console.log("javascript name:"+document.querySelector("#name").value);
        console.log("javascript birth:"+document.querySelector("#birth").value);
        console.log("javascript gender:"+radioValue);
        console.log("javascript height:"+document.querySelector("#height").value);
        console.log("javascript weight:"+document.querySelector("#weight").value);
        console.log("javascript activity:"+document.querySelector("#activity").value);
        
        let id = regForm.id.value;
        if(eUtil.isEmpty(id)==true){
            alert('아이디를 입력 하세요.');
            regForm.id.focus();
            return;
        }
        let pw = regForm.pw.value;
        if(eUtil.isEmpty(pw)==true){
            alert('비밀번호를 입력 하세요.');
            regForm.pw.focus();
            return;
        }
        let email = regForm.email.value;
        if(eUtil.isEmpty(email)==true){
            alert('이메일을 입력 하세요.');
            regForm.email.focus();
            return;
        }
        let name = regForm.name.value;
        if(eUtil.isEmpty(name)==true){
            alert('이름을 입력 하세요.');
            regForm.name.focus();
            return;
        }
        let birth = regForm.birth.value;
        if(eUtil.isEmpty(birth)==true){
            alert('생년월일 6자리를 입력 하세요.');
            regForm.birth.focus();
            return;
        }
        let height = regForm.height.value;
        if(eUtil.isEmpty(height)==true){
            alert('키를 입력 하세요.');
            regForm.height.focus();
            return;
        }
        let weight = regForm.weight.value;
        if(eUtil.isEmpty(weight)==true){
            alert('몸무게를 입력 하세요.');
            regForm.weight.focus();
            return;
        }
        let activity = regForm.activity.value;
        if(eUtil.isEmpty(activity)==true){
            alert('활동지수를 입력 하세요.');
            regForm.activity.focus();
            return;
        }
        if(document.querySelector("#idCheck").value == 0){
            alert('아이디 중복체크를 수행하세요.');
            document.querySelector("#idCheck").focus();
            return;
        }
        if(document.querySelector("#emailCheck").value == 0){
            alert('이메일 중복체크를 수행하세요.');
            document.querySelector("#emailCheck").focus();
            return;
        }
        if(matchText.innerText == "비밀번호가 일치하지 않습니다."){
            alert('비밀번호를 확인하세요.');
            document.querySelector("#passwordMatchText").focus();
            return;
        }
        if($resultMsg.html() === '인증번호가 불일치 합니다. 다시 확인해주세요!.'){
            alert('인증번호를 확인하세요.');
            document.querySelector("#mail-check-warn").focus();
            return;
        }
        if(eUtil.isEmpty(code)==true){
            alert('인증번호를 입력하세요.'); // 인증번호가 비어있을 경우
            return;
        }
        
        
        $.ajax({
            type: "POST",
            url:"/bdm/user/doSave.do",
            asyn:"true",
            dataType:"html",
            data:{
                id: document.querySelector("#id").value,
                pw: document.querySelector("#pw").value,
                email: document.querySelector("#email").value,
                name: document.querySelector("#name").value,
                birth: document.querySelector("#birth").value,
                gender: radioValue,
                height: document.querySelector("#height").value,
                weight: document.querySelector("#weight").value,
                activity: document.querySelector("#activity").value
            },
            success:function(data){//통신 성공     
               console.log("success data:"+data);
               let parsedJSON = JSON.parse(data);
               if("1" === parsedJSON.msgId){
                   alert(parsedJSON.msgContents);
               }else{
                   alert(parsedJSON.msgContents);
               }
               window.location.href = "/bdm/beforeMain/moveToMain.do";
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
    });
});
</script>
</head>
<body>
	<div class="wrap">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">회원 등록</h1>
                </div>
            </div>
            <form action="#" name="userRegFrm" id="userRegFrm">
                <!-- id중복체크 수행 여부 확인:0(미수행),1(수행) -->
                <input type="hidden" name="idCheck" id="idCheck" value="0">
                <input type="hidden" name="emailCheck" id="emailCheck" value="0">
                <div class="mb-3">
                    <label for="id" class="form-label">아이디</label>
                    <input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요" size="20" maxlength="30">
                    <button type="button" class="btn btn-primary mt-2" id="doCheckId">중복 체크</button>
                </div>
	            <div class = "mb-3">
	                 <label for="pw" class="form-label">비밀번호</label>
	                 <input type="password"  class="form-control"  name="pw" id="pw" placeholder="비밀번호를 입력 하세요." size="20"  maxlength="30">
	            </div>
	            <div class = "mb-3">
	                 <label for="confirmPw" class="form-label">비밀번호 확인</label>
	                 <input type="password"  class="form-control"  name="confirmPw" id="confirmPw" placeholder="비밀번호를 다시 입력 하세요." size="20"  maxlength="30">
	                 <p id="passwordMatchText"></p>
	             </div>
	             <div class="p-div">
	                  <label for="email" class="form-label">이메일</label>
	                  <input type="text"  class="form-control" name="email" id="email" placeholder="이메일을 입력하세요" size="20"  maxlength="320">
	                  <input type="button" class="btn btn-primary" value="이메일 인증" id="doCheckEmail">
	            	  <input type="button" class="btn btn-primary" value="이메일 인증 요청" id="mail-Check-Btn">
	           	      <input class="form-control mail-check-input mt-1" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
	           	      <p id="mail-check-warn"></p>
	             </div>
	             
	             <div class = "mb-3">
	                  <label for="name" class="form-label">이름</label>
	                  <input type="text"  class="form-control"  name="name" id="name" placeholder="이름을 입력 하세요." size="20"  maxlength="21">
	             </div>
	             <div class="p-div">
	                  <label for="login" class="form-label">생년월일</label>
	                  <input type="text"  class="form-control" name="birth" id="birth" placeholder="앞 6자리만 입력하세요." size="20"  maxlength="6">
	             </div>
	             <div class="p-div" id = "gender">     
	                  <label for="recommend" class="form-label">성별</label>
	                  <input class="form-check-input" type="radio" name="flexRadioDefault" id="man" value = 1 checked>
	                  <label class="form-check-label" for="man">남자</label>
	                  <input class="form-check-input" type="radio" name="flexRadioDefault" id="woman" value = 2>
	                  <label class="form-check-label" for="woman">여자</label>         
	              </div>
	              <div class="p-div">
	                  <label for="email" class="form-label">키</label>
	                  <input type="text"  class="form-control" name="height" id="height" placeholder="키를 입력하세요" size="20"  maxlength="320">
	              </div>
	              <div class="p-div">
	                  <label for="regDt" class="form-label">몸무게</label>
	                  <input type="text" class="form-control"  name="weight" id="weight" placeholder="몸무게를 입력하세요" size="20"  maxlength="7">
	              </div>
	              <div class="p-div">
	                  <label for="activity" class="form-label">활동지수</label>
	                  <input type="text" class="form-control"  name="activity" id="activity" placeholder="활동지수를 입력하세요" size="20"  maxlength="7">
	               </div>
	              <div class="row justify-content-end">
	               	<div class="col-auto">
	                   <input type="button" class="btn btn-primary" value="돌아가기" id="moveToMain">
	                   <input type="button" class="btn btn-primary" value="가입하기" id="doSave">
	               </div>
	             </div>
	           </form>
	      </div>                 
	</div>
</body>
</html>