<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script>
function doSave(){
	console.log('doSave()');
	
	console.log("javascript userId:"+document.querySelector("#userId").value);
	console.log("javascript userId:"+document.querySelector("#password").value);
	console.log("javascript userId:"+document.querySelector("#email").value);
	console.log("javascript userId:"+document.querySelector("#name").value);
	console.log("javascript userId:"+document.querySelector("#birth").value);
	console.log("javascript userId:"+document.querySelector("#sex").value);
	console.log("javascript userId:"+document.querySelector("#height").value);
	console.log("javascript userId:"+document.querySelector("#weight").value);
}
</script>
</head>
<body>
    <div class = "container">
       <div class ="row">
           <div class = "col-lg-12">
               <h1 class = "page-header">회원 등록</h1>
           </div>
       </div>
         <!-- Button영역 -->
       <div class = "row justify-content-end">
           <div class = "col-auto">
               <input type="button" class="btn btn-primary" value="등록" id="doSave"      onclick="window.doSave();">
               <input type="button" class="btn btn-primary" value="목록" id="moveToList" >
           </div>
       </div>
         <!--// Button영역 ------------------------------------------------------>
         
         <!-- 회원 등록영역 -->  
         <div>
           <form action="#" name="userRegFrm">
               <%-- id중복체크 수행 여부 확인:0(미수행),1(수행) --%>
               <input type="hidden" name="idCheck" id="idCheck" value="0">
               <div class = "mb-3">
                   <label for="userId" class="form-label">아이디</label>
                   <input type="text"  class="form-control ppl_input" name="userId" id="userId" placeholder="아이디를 입력 하세요." size="20"  maxlength="30">
                   <input type="button" class="btn btn-primary" value="중복 체크" id="idDuplicateCheck"      onclick="window.idDuplicateCheck();">
               </div>
               <div class = "mb-3">
                   <label for="password" class="form-label">비밀번호</label>
                   <input type="password"  class="form-control"  name="password" id="password" placeholder="비밀번호를 입력 하세요." size="20"  maxlength="30">
               </div>
               <div class="p-div">
                   <label for="email" class="form-label">이메일</label>
                   <input type="text"  class="form-control" name="email" id="email" placeholder="이메일을 입력하세요" size="20"  maxlength="320">
               </div>
               <div class = "mb-3">
                   <label for="name" class="form-label">이름</label>
                   <input type="text"  class="form-control"  name="name" id="name" placeholder="이름을 입력 하세요." size="20"  maxlength="21">
               </div>
               <div class="p-div">
                   <label for="login" class="form-label">생년월일</label>
                   <input type="text"  class="form-control" name="birth" id="birth" placeholder="앞 6자리만 입력하세요." size="20"  maxlength="6">
               </div>
               <div class="p-div">     
                   <label for="recommend" class="form-label">성별</label>
                   <input type="text" class="form-control"  name="sex" id="sex" placeholder="성별을 입력하세요" size="20"  maxlength="8">
               </div>
               <div class="p-div">
                   <label for="email" class="form-label">키</label>
                   <input type="text"  class="form-control" name="height" id="height" placeholder="키를 입력하세요" size="20"  maxlength="320">
               </div>
               <div class="p-div">
                   <label for="regDt" class="form-label">몸무게</label>
                   <input type="text" class="form-control"  name="weight" id="weight" placeholder="몸무게를 입력하세요" size="20"  maxlength="7">
               </div>                                                        
           </form>
         </div>
         <!--// 회원 등록영역 ------------------------------------------------------>
         
     </div>
</body>
</html>