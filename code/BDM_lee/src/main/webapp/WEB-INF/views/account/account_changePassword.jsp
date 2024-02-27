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
document.addEventListener("DOMContentLoaded", function(){
    const cancleBtn = document.querySelector("#cancle");
    const changePasswordBtn = document.querySelector("#changePassword");
    const form = document.querySelector("#changePasswordFrm");
    
    cancleBtn.addEventListener("click", function(e){
        window.close();
    });
    
    changePasswordBtn.addEventListener("click", function(e){
        let password = form.password.value;
        if(eUtil.isEmpty(password)==true){
            alert('비밀번호를 입력하세요.');
            form.password.focus();
            return;
        }
        let confirm = form.confirm.value;
        if(eUtil.isEmpty(confirm)==true){
            alert('비밀번호를 다시 입력하세요.');
            form.confirm.focus();
            return;
        }
        let newPassword = form.newPassword.value;
        if(eUtil.isEmpty(newPassword)==true){
            alert('새 비밀번호를 입력하세요.');
            form.newPassword.focus();
            return;
        }
        
        if(password != confirm){
            alert('현재 비밀번호를 확인하세요.');
            return;
        }
        
        let id = '${user.id}';
        
        $.ajax({
            type: "POST",
            url:"/bdm/user/changePassword.do",
            asyn:"true",
            dataType:"html",
            data:{
                id: id,
                pw: newPassword
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
               <h1 class = "page-header">비밀번호 변경</h1>
           </div>
       </div>
         
         <!-- 회원 등록영역 -->  
         <div>
           <form action="#" name="changePasswordFrm" id = "changePasswordFrm">
               <div class = "p-div">
                   <label for="password" class="form-label">현재 비밀번호</label>
                   <input type="text"  class="form-control"  name="password" id="password" placeholder="현재 비밀번호를 입력하세요." size="20"  maxlength="30">
               </div>         
               <div class="p-div">
                   <label for="confirm" class="form-label">현재 비밀번호 확인</label>
                   <input type="text"  class="form-control" name="confirm" id="confirm" placeholder="현재 비밀번호를 다시 입력하세요." size="20"  maxlength="30">
               </div>                 
               <div class="p-div">
                   <label for="newPassword" class="form-label">새 비밀번호</label>
                   <input type="text"  class="form-control" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력하세요." size="20"  maxlength="30">
               </div>
           </form>
         </div>
         <!--// 회원 등록영역 ------------------------------------------------------>
         
           <!-- Button영역 -->
       <div class = "row justify-content-end">
           <div class = "col-auto">
               <input type="button" class="btn btn-primary" value="취소" id="cancle" >
               <input type="button" class="btn btn-primary" value="변경하기" id="changePassword">
           </div>
       </div>
         <!--// Button영역 ------------------------------------------------------>
     </div>
</body>
</html>