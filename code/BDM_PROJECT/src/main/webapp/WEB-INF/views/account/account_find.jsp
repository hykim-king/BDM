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
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<script>
document.addEventListener("DOMContentLoaded",function() {
	const findIdBtn = document.querySelector("#findId");
	const findPasswordBtn = document.querySelector("#findPassword");
	const moveToMainBtn = document.querySelector("#moveToMain");
	
	findIdBtn.addEventListener("click", function(e){
		openFindId();
	});
	findPasswordBtn.addEventListener("click", function(e){
		openFindPassword();
    });
	moveToMainBtn.addEventListener("click", function(e){
		window.location.href = "/bdm/beforeMain/moveToMain.do";
    });
});
function openFindId() {
    var width = 750;
    var height = 1200;
    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
    myWindow = window.open('../beforeMain/moveToFindId.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
}
function openFindPassword() {
    var width = 750;
    var height = 1200;
    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
    myWindow = window.open('../beforeMain/moveToFindPassword.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
}
</script>
</head>
<body>
<h1 class="page-header">계정 찾기</h1>
<input type = "button" id = "findId" value ="아이디 찾기">
<input type = "button" id = "findPassword" value ="비밀번호 찾기">
<input type = "button" id = "moveToMain" value ="메인화면으로">
</body>
</html>