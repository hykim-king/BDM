<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>
<title>회원등록</title>
<script>
    // A $( document ).ready() block.
    // body에 모든 element가 로딩이 완료되면 동작
    $(document).ready(function() {
        console.log("ready!");
    });

    function doLogin() {
        console.log("----------------------");
        console.log("-doLogin()-");
        
        console.log("----------------------");
        
        let userId = document.querySelector("#userId").value;
        if (eUtil.isEmpty(userId) == true) {
            alert('아이디를 입력 하세요.');
            document.querySelector("#userId").focus();
            return;
        }
        console.log("userId:" + userId);
        
        let password = document.querySelector("#password").value;
        if (eUtil.isEmpty(password) == true) {
            alert('비번을 입력 하세요.');
            document.querySelector("#password").focus();
            return;
        }
        console.log("password:" + password);
        
        if (confirm("로그인 하시겠습니까?") === false) return;
        
        $.ajax({
            type: "POST",
            url: "/bdm/login/doLogin.do",
            async: "true",
            dataType: "json",
            data: {
                "userId": userId,
                "password": password
            },
            success: function(data) {//통신 성공
                console.log("data.msgId:" + data.msgId);
                console.log("data.msgContents:" + data.msgContents);
                
                if ("10" == data.msgId) {
                    alert(data.msgContents);
                    document.querySelector("#userId").focus();
                } else if ("20" == data.msgId) {
                    alert(data.msgContents);
                    document.querySelector("#password").focus();
                } else if ("30" == data.msgId) {
                    alert(data.msgContents);
                    
                }
            },
            error: function(data) {//실패시 처리
                console.log("error:" + data);
            },
            complete: function(data) {//성공/실패와 관계없이 수행!
                console.log("complete:" + data);
            }
        });
    }
</script>
</head>
<body>
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">로그인</h1>
        </div>
    </div>
    <!--// 제목 ----------------------------------------------------------------->
    <!-- 버튼 -->
    <div class="row justify-content-end">
        <div class="col-auto">
            <input type="button" class="btn btn-primary" value="로그인" id="doLogin" onclick="window.doLogin();">
        </div>
    </div>
    <!--// 버튼 ----------------------------------------------------------------->
    <!-- 회원 등록영역 -->
    <div>
        <form action="#" name="userRegFrm">
            <div class="mb-3">
                <label for="userId" class="form-label">아이디</label>
                <input type="text" class="form-control ppl_input" name="userId" id="userId" placeholder="아이디를 입력 하세요." size="20" maxlength="30">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력 하세요." size="20" maxlength="30">
            </div>
        </form>
    </div>
    <!--// 회원 등록영역 ------------------------------------------------------>
</div>
</body>
</html>
