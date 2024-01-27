<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="http://localhost:8080/ehr/resources/css/user.css"> -->
<link rel="stylesheet" href="/ehr/resources/css/user.css">
<style>
    .p-label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .p-input {
        height: 26.5px;
        width: 380px;
        vertical-align: middle;
        font-size: 12px;        
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    
    .p-div {
        margin-bottom: 5px;
    }

</style>
<title>회원등록</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>
<script >

   
   function doDelete(){
       console.log("----------------------");
       console.log("-doDelete()-");
       console.log("----------------------");	
       
       let userId = document.querySelector("#userId").value;
       console.log("-userId:"+userId);
       
       if(eUtil.isEmpty(userId) == true){
           alert('아이드를 입력 하세요.');
           document.querySelector("#userId").focus();
           return;
       }
       
       if(window.confirm('삭제 하시겠습니까?')==false){
    	   return;
       }
       console.log("-confirm:");
       $.ajax({
           type: "GET",
           url:"/ehr/user/doDelete.do",
           asyn:"true",
           dataType:"json", /*return dataType: json으로 return */
           data:{
               userId: userId
           },
           success:function(data){//통신 성공
               console.log("success data:"+data);
               //let parsedJSON = JSON.parse(data);
               if("1" === data.msgId){
                   alert(data.msgContents);
                   moveToList();
               }else{
                   alert(data.msgContents);
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
   
   
   function moveToList(){
	   console.log("----------------------");
	   console.log("-moveToList()-");
	   console.log("----------------------");
	   
	   window.location.href = "/ehr/user/doRetrieve.do";
   }
   
   function doUpdate(){
       console.log("----------------------");
       console.log("-doUpdate()-");
       console.log("----------------------");
       
       //javascript
       console.log("javascript userId:"+document.querySelector("#userId").value);
       console.log("javascript ppl_input:"+document.querySelector(".ppl_input").value);
       
       //$("#userId").val() : jquery id선택자
       //$(".userId")
       
       console.log("jquery userId:"+$("#userId").val());
       console.log("jquery ppl_input:"+$(".ppl_input").val());      
       
       if(eUtil.isEmpty(document.querySelector("#userId").value) == true){
    	   alert('아이드를 입력 하세요.');
    	   //$("#userId").focus();//사용자 id에 포커스
    	   document.querySelector("#userId").focus();
    	   return;
       }
       
       if(eUtil.isEmpty(document.querySelector("#name").value) == true){
           alert('이름을 입력 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#name").focus();
           return;
       }       
       
       
       if(eUtil.isEmpty(document.querySelector("#password").value) == true){
           alert('비밀번호를 입력 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#password").focus();
           return;
       } 
       
       if(eUtil.isEmpty(document.querySelector("#login").value) == true){
           alert('로그인을 입력 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#login").focus();
           return;
       }      
       
       if(eUtil.isEmpty(document.querySelector("#recommend").value) == true){
           alert('추천을 입력 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#recommend").focus();
           return;
       }      
       
       
       if(eUtil.isEmpty(document.querySelector("#email").value) == true){
           alert('이메일을 입력 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#email").focus();
           return;
       }    
       
       //confirm
       if(confirm("수정 하시겠습니까?")==false)return;
       
       $.ajax({
           type: "POST",
           url:"/ehr/user/doUpdate.do",
           asyn:"true",
           dataType:"html",
           data:{
        	   userId:document.querySelector("#userId").value,
        	   name: document.querySelector("#name").value,
        	   password: document.querySelector("#password").value,
               levelIntValue:document.querySelector("#levelIntValue").value,
        	   login: document.querySelector("#login").value,
        	   recommend: document.querySelector("#recommend").value,
        	   email: document.querySelector("#email").value
           },
           success:function(data){//통신 성공     
               console.log("success data:"+data);
              //data:{"msgId":"1","msgContents":"dd가 등록 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}
              let parsedJSON = JSON.parse(data);
              if("1" === parsedJSON.msgId){
            	  alert(parsedJSON.msgContents);
            	  moveToList();
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
       
   }
</script>

</head>
<body>
     <div>
	     <h2>회원수정</h2>
	     <hr/>
	     <!-- Button영역 -->
	     <div>
	       <input type="button" class="btn" value="수정" id="doUpdate"      onclick="window.doUpdate();">
	       <input type="button" class="btn" value="삭제" id="doDelete"      onclick="window.doDelete();">
	       <input type="button" class="btn" value="목록" id="moveToList"    onclick="window.moveToList();">
	     </div>
	     <!--// Button영역 ------------------------------------------------------>
	     
	     <!-- 회원 등록영역 -->  
	     <div>
	       <form action="#" name="userRegFrm">
	           <div class="p-div">
	               <label for="userId" class="p-label">아이디</label>
	               <input type="text"  class="p-input ppl_input" readonly="readonly" name="userId" id="userId" placeholder="아이디를 입력 하세요."
	                value="${outVO.userId }"
	                size="20"  maxlength="30">
	           </div>
               <div class="p-div">
                   <label for="name" class="p-label">이름</label>
                   <input type="text"  class="p-input"  name="name" id="name" placeholder="이름을 입력 하세요." size="20"  
                   value="${outVO.name }"
                   maxlength="21">
               </div>	
                <div class="p-div">
                   <label for="password" class="p-label">비밀번호</label>
                   <input type="password"  class="p-input"  name="password" id="password" placeholder="비밀번호를 입력 하세요." 
                   value="${outVO.password }"
                   size="20"  maxlength="30">
               </div>                 
               <div class="p-div">
                   <label for="levelIntValue" class="p-label">등급</label>
                   <select name="levelIntValue" id="levelIntValue" class="select" >
                       <option value="1" <c:if test="${outVO.levelIntValue =='1' }">selected</c:if> >BASIC</option>
                       <option value="2" <c:if test="${outVO.levelIntValue =='2' }">selected</c:if> >SILVER</option>
                       <option value="3" <c:if test="${outVO.levelIntValue =='3' }">selected</c:if> >GOLD</option>
                   </select>
               </div>   
               <div class="p-div">
                   <label for="login" class="p-label">로그인</label>
                   <input type="text"  class="p-input" name="login" id="login" placeholder="로그인 회수를 입력하세요" 
                   value="${outVO.login }"
                   size="20"  maxlength="8">
               </div>    
               <div class="p-div">
                   <label for="recommend" class="p-label">추천</label>
                   <input type="text" class="p-input"  name="recommend" id="recommend" placeholder="추천 수를 입력하세요" 
                   value="${outVO.recommend }"
                   size="20"  maxlength="8">
               </div>
               <div class="p-div">
                   <label for="email" class="p-label">이메일</label>
                   <input type="text"  class="p-input" name="email" id="email" placeholder="이메일을 입력하세요" size="20" 
                   value="${outVO.email }"
                    maxlength="320">
               </div>
               <div class="p-div">
                   <label for="regDt" class="p-label">등록일</label>
                   <input type="text" class="p-input"  name="regDt" id="regDt" placeholder="" 
                   value="${outVO.regDt }"
                   size="20"  maxlength="7">
               </div>                                                        
	       </form>
	     </div>
	     <!--// 회원 등록영역 ------------------------------------------------------>
	     
     </div>
</body>
</html>