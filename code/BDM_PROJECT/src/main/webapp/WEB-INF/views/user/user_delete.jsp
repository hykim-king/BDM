<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
</style>
<title></title>
<script src="${CP }/resources/js/jquery-3.7.1.js"></script>
<script src="${CP }/resources/js/eUtil.js"></script>  
<script>
document.addEventListener("DOMContentLoaded", function () {
	const cancleBtn = document.querySelector("#cancle");
	const doDeleteBtn = document.querySelector("#doDelete");
	const deleteForm = document.querySelector("#deleteFrm");
	
	cancleBtn.addEventListener("click", function(e){
        window.close();
    });
	
	doDeleteBtn.addEventListener("click", function(e){
		let pw = deleteForm.pw.value;
        if(eUtil.isEmpty(pw)==true){
            alert('비밀번호를 입력 하세요.');
            deleteForm.pw.focus();
            return;
        }
        
        let id = '${user.id}';
          
        $.ajax({
          type: "GET",
          url:"${CP }/user/doDelete.do",
          asyn:"true",
          dataType:"html",
          data:{
              id: id,
              pw: pw
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
               <h1 class = "page-header">회원 탈퇴</h1>
           </div>
       </div>
         
         <!-- 회원 등록영역 -->  
         <div>
           <form action="#" name="deleteFrm" id = "deleteFrm">
               <div class = "p-div">
                   <label for="name" class="form-label">비밀번호</label>
                   <input type="password"  class="form-control"  name="pw" id=""pw"" placeholder="비밀번호를 입력 하세요." size="20"  maxlength="21">
               </div>
           </form>
         </div>
         <!--// 회원 등록영역 ------------------------------------------------------>
         
           <!-- Button영역 -->
       <div class = "row justify-content-end">
           <div class = "col-auto">
               <input type="button" class="btn btn-primary" value="취소" id="cancle" >
               <input type="button" class="btn btn-primary" value="탈퇴" id="doDelete">
           </div>
       </div>
         <!--// Button영역 ------------------------------------------------------>
     </div>
</body>
</html>