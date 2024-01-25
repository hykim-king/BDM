<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include> --%>
    <style>
        .login-form {
            width: 340px;
            margin: 50px auto;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {        
            font-size: 15px;
            font-weight: bold;
        }
    </style>
<%-- <title><spring:message  code="login.title"/></title> --%>
<script>
 $(document).ready(function(){
	 
	 console.log( "ready!" );
	 
	 
	 
	 $("#doLogin").on("click",function(e){
		 console.log( "doLogin click!" );
		 
		 let id = document.querySelector("#id").value;
		 if(eUtil.isEmpty(id)==true){
			 alert('아이드를 입력하시오');
			 document.querySelector("#id").focus();
			 return;
		 }
		 console.log("id:"+id);
		 
         let pw = document.querySelector("#pw").value;
         if(eUtil.isEmpty(pw)==true){
             alert('비밀번호를 입력하시오');
             document.querySelector("#pw").focus();
             return;
         }
         console.log("pw:"+pw);
		 
         if(confirm("로그인 하시겠습니까?")===false) return;
         
         $.ajax({
             type: "POST",
             url:"/ehr/login/doLogin.do",
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
                	 window.location.href = "/bdm/main/mainView.do";
                 }
             },
             error:function(data){//실패시 처리
                 console.log("error:"+data);
             },
             complete:function(data){//성공/실패와 관계없이 수행!
                 console.log("complete:"+data);
             }
         });
         
         
	 });//--#doLogin
	 
	 
 });//--document ready
</script>   
</head>
<body>
    <div class="login-form"> 
    
        <form action="/bdm/login/doLogin.do" method="post">
            <h2 class="text-center"><spring:message  code="login.title"/></h2><!-- 로그인 -->
            <div class="form-group">
                <input type="text" class="form-control" id="id" name="id" placeholder="<spring:message  code='login.id'/>" required="required" value="lee">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pw" name="pw" placeholder="<spring:message  code='login.password'/>" required="required" value="1234">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-primary btn-block" id="doLogin"><spring:message  code='login.loginButton'/></button>
                <!-- <button type="button" class="btn btn-primary btn-block" id="doMemberPopup">회원</button> -->
            </div>
        </form>
        <p class="text-center"><a href="#">계정 만들기</a></p>
    </div>
    
</body>
</html>