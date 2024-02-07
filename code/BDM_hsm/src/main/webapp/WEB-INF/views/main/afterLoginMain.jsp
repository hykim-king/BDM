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
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>BDM</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	const moveToFreeBoard = document.querySelector("#bulletin");
	
	moveToMyPageBtn.addEventListener("click", function(e){
        location.href = "/bdm/beforeMain/moveToMyPage.do";
    });
	
	moveToFreeBoard.addEventListener("click", function(e){
		location.href = "/bdm/bulletin/doRetrieve.do"
	});
	
});
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="/bdm/index.jsp">Balance Diet Management</a>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">MEMBER</a>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item" href="/bdm/user/moveToReg.do">회원 가입</a></li>
      <li><a class="dropdown-item" href="#">새로운 탭</a></li>
      <li><a class="dropdown-item" href="#">새로운 탭</a></li>
      <li><hr class="dropdown-divider"></li>
      <li><a class="dropdown-item" href="/bdm/beforeMain/moveToMyPage.do">마이페이지</a></li>
    </ul>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/bdm/bulletin/doRetrieve.do">자유게시판</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/bdm/notice/doRetrieve.do">공지사항</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/bdm/index.jsp">로그아웃</a>
  </li>
</ul>
</body>
</html>