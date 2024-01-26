<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <link rel="stylesheet" href="${CP}/resources/css/user.css"> --%>
<style></style>
<title>Insert title here</title>
<script src="${CP}/resources/js/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>
<script>

    $(document).ready(function(){
        console.log("ready");

        $("#doLogin").on("click", function(e){
            console.log("doLogin click");

            let id = document.querySelector("#id").value;
            let pw = document.querySelector("#pw").value;
            if(eUtil.isEmpty(id) == true){
                alert('아이디 입력하시오');
                document.querySelector("#id").focus();
                return;
            }
            console.log("id:"+id);
            console.log("pw:"+pw);

            if(confirm("로그인 하시겠습니다?") === false) return;

            $.ajax({
                type: "POST",
                url:"/bdm/login/doLogin.do",
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
    	    });//--ajax

        });//--#doLogin

    });//--ready

</script>
</head>
<body>
    <div class="login-form">
        <form action="/bdm/login/doLogin.do" method="post">
            <h2 class="text-center">login</h2><!-- 로그인 <spring:message  code="login.title"/> -->
            <div class="form-group">
                <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요." required="required" value="lee">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요." required="required" value="1234">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-primary btn-block" id="doLogin">login</button>
            </div>
        </form>
    </div>

</body>
</html>