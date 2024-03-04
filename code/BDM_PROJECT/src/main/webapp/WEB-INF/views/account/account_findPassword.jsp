<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	background-color: #f7e9e8;
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
<title></title>
<script src="${CP }/resources/js/eUtil.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
    const cancleBtn = document.querySelector("#cancle");
    const doFindPasswordBtn = document.querySelector("#doFindPassword");
    const findPasswordForm = document.querySelector("#findPasswordFrm");
    
    cancleBtn.addEventListener("click", function(e){
        window.close();
    });
    
    doFindPasswordBtn.addEventListener("click", function(e){
        let name = findPasswordForm.name.value;
        if(eUtil.isEmpty(name)==true){
            alert('이름을 입력 하세요.');
            findIdForm.name.focus();
            return;
        }
        let birth = findPasswordForm.birth.value;
        if(eUtil.isEmpty(birth)==true){
            alert('생년월일 6자리를 입력 하세요.');
            findIdForm.birth.focus();
            return;
        }
        let id = findPasswordForm.id.value;
        if(eUtil.isEmpty(id)==true){
            alert('아이디를 입력 하세요.');
            findIdForm.id.focus();
            return;
        }
        let email = findPasswordForm.email.value;
        if(eUtil.isEmpty(email)==true){
            alert('이메일을 입력 하세요.');
            findIdForm.email.focus();
            return;
        }
        
        $.ajax({
            type: "GET",
            url:"/bdm/user/doFindPassword.do",
            asyn:"true",
            dataType:"html",
            data:{
                name: name,
                birth: birth,
                id: id,
                email: email
            },
            success:function(data){//통신 성공     
               console.log("data:" + data);
               let parsedJSON = JSON.parse(data);
               if("1" === parsedJSON.msgId){
                   alert(parsedJSON.msgContents);
                   window.close();
               }else{
                   alert(parsedJSON.msgContents);
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
});
</script>
</head>
<body>
    <div class = "container">
       <div class ="row">
           <div class = "col-lg-12">
               <h1 class = "page-header">비밀번호 찾기</h1>
           </div>
       </div>
         
         <!-- 회원 등록영역 -->  
         <div>
           <form action="#" name="findPasswordFrm" id = "findPasswordFrm">
               <div class = "p-div">
                   <label for="name" class="form-label">이름</label>
                   <input type="text"  class="form-control"  name="name" id="name" placeholder="이름을 입력 하세요." size="20"  maxlength="21">
               </div>         
               <div class="p-div">
                   <label for="birth" class="form-label">생년월일</label>
                   <input type="text"  class="form-control" name="birth" id="birth" placeholder="앞 6자리만 입력하세요." size="20"  maxlength="6">
               </div>             
               <div class="p-div">
                   <label for="id" class="form-label">아이디</label>
                   <input type="text"  class="form-control" name="id" id="id" placeholder="아이디를 입력하세요" size="20"  maxlength="320">
               </div>    
               <div class="p-div">
                   <label for="email" class="form-label">이메일</label>
                   <input type="text"  class="form-control" name="email" id="email" placeholder="이메일을 입력하세요" size="20"  maxlength="320">
               </div>
           </form>
         </div>
         <!--// 회원 등록영역 ------------------------------------------------------>
         
           <!-- Button영역 -->
       <div class = "row justify-content-end">
           <div class = "col-auto">
               <input type="button" class="btn btn-primary" value="취소" id="cancle" >
               <input type="button" class="btn btn-primary" value="입력 완료" id="doFindPassword">
           </div>
       </div>
         <!--// Button영역 ------------------------------------------------------>
     </div>
</body>
</html>