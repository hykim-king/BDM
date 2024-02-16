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

	//A $( document ).ready() block.
	//body에 모든 element가 로딩이 완료되면 동작
	$( document ).ready(function() {
	    console.log( "ready!" );
	    //숫자만 입력
	    //keyup: keyboard event로 키보드를 keyup 했을때 발생
	    $(".numOnly").on("keyup",function(e){
	        console.log("numOnly:"+$(this).val());
	        
	        let replaceNum = $(this).val().replace(/[^0-9]/g, "");
	        
	        $(this).val(replaceNum);
	    });
	    	    
	    
	});//document ready

   function idDuplicateCheck(){
	   console.log("-idDuplicateCheck()-");	
	   let userId = document.querySelector("#userId").value;
       if(eUtil.isEmpty(userId) == true){
           alert('아이드를 입력 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#userId").focus();
           return;
       }
       
       $.ajax({
           type: "GET",
           url:"/ehr/user/idDuplicateCheck.do",
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
                   document.querySelector("#idCheck").value = 0;
               }else{
                   alert(data.msgContents);
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
       
       
   }	
	
	
   function moveToList(){
	   console.log("----------------------");
	   console.log("-moveToList()-");
	   console.log("----------------------");
	   
	   window.location.href = "/ehr/user/doRetrieve.do";
   }
   
   function doSave(){
       console.log("----------------------");
       console.log("-doSave()-");
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
       
       if(document.querySelector("#idCheck").value == '0'){
           alert('아이드 중복체크를 수행 하세요.');
           //$("#userId").focus();//사용자 id에 포커스
           document.querySelector("#idCheck").focus();
           return;
       }
       
       //confirm
       if(confirm("등록 하시겠습니까?")==false)return;
       
       
       $.ajax({
           type: "POST",
           url:"/ehr/user/doSave.do",
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
	     <h2>회원등록</h2>
	     <hr/>
	     <!-- Button영역 -->
	     <div>
	       <input type="button" class="btn" value="등록" id="doSave"      onclick="window.doSave();">
	       <input type="button" class="btn" value="목록" id="moveToList"  onclick="window.moveToList();">
	     </div>
	     <!--// Button영역 ------------------------------------------------------>
	     
	     <!-- 회원 등록영역 -->  
	     <div>
	       <form action="#" name="userRegFrm">
	           <%-- id중복체크 수행 여부 확인:0(미수행),1(수행) --%>
	           <input type="hidden" name="idCheck" id="idCheck" value="0">
	           <div class="p-div">
	               <label for="userId" class="p-label">아이디</label>
	               <input type="text"  class="p-input ppl_input" name="userId" id="userId" placeholder="아이디를 입력 하세요." size="20"  maxlength="30">
	               <input type="button" class="btn" value="등록" id="idDuplicateCheck"      onclick="window.idDuplicateCheck();">
	           </div>
               <div class="p-div">
                   <label for="name" class="p-label">이름</label>
                   <input type="text"  class="p-input"  name="name" id="name" placeholder="이름을 입력 하세요." size="20"  maxlength="21">
               </div>	
                <div class="p-div">
                   <label for="password" class="p-label">비밀번호</label>
                   <input type="password"  class="p-input"  name="password" id="password" placeholder="비밀번호를 입력 하세요." size="20"  maxlength="30">
               </div>                 
               <div class="p-div">
                   <label for="levelIntValue" class="p-label">등급</label>
                   <select name="levelIntValue" id="levelIntValue" class="select">
                       <option value="1">BASIC</option>
                       <option value="2">SILVER</option>
                       <option value="3">GOLD</option>
                   </select>
               </div>   
               <div class="p-div">
                   <label for="login" class="p-label">로그인</label>
                   <input type="text"  class="p-input numOnly" name="login" id="login" placeholder="로그인 회수를 입력하세요" size="20"  maxlength="8">
               </div>    
               <div class="p-div">     
                   <label for="recommend" class="p-label">추천</label>
                   <input type="text" class="p-input numOnly"  name="recommend" id="recommend" placeholder="추천 수를 입력하세요" size="20"  maxlength="8">
               </div>
               <div class="p-div">
                   <label for="email" class="p-label">이메일</label>
                   <input type="text"  class="p-input" name="email" id="email" placeholder="이메일을 입력하세요" size="20"  maxlength="320">
               </div>
               <div class="p-div">
                   <label for="regDt" class="p-label">등록일</label>
                   <input type="text" class="p-input"  name="regDt" id="regDt" placeholder="" size="20"  maxlength="7">
               </div>                                                        
	       </form>
	     </div>
	     <!--// 회원 등록영역 ------------------------------------------------------>
	     
     </div>
</body>
</html>