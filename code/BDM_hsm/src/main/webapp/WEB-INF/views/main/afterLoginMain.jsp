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
	const moveToRegBtn = document.querySelector("#moveToReg");
	const moveToNoticeBtn = document.querySelector("#moveToNotice");
	const moveToBulletinBtn = document.querySelector("#moveToBulletin");
	const moveToNewsBtn = document.querySelector("#moveToNews");
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	
	moveToRegBtn.addEventListener("click", function(e){
   	 location.href = "/bdm/user/moveToReg.do";
    });
    moveToNoticeBtn.addEventListener("click", function(e){
        location.href = "/bdm/beforeMain/moveToNotice.do";
    });
    moveToBulletinBtn.addEventListener("click", function(e){
        location.href = "/bdm/bulletin/doRetrieve.do";
    });
    moveToNewsBtn.addEventListener("click", function(e){
        location.href = "/bdm/beforeMain/moveToNews.do";
    });
    moveToMyPageBtn.addEventListener("click", function(e){
        location.href = "/bdm/beforeMain/moveToMyPage.do";
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
				<li><a class="dropdown-item" href="/bdm/user/moveToReg.do" id="moveToReg">회원 가입</a></li>
				<li><a class="dropdown-item" href="#" id="doFindAccount">ID/PW 찾기</a></li>
				<li><hr class="dropdown-divider"></li>
				<li><a class="dropdown-item" href="/bdm/beforeMain/moveToMyPage.do" id="moveToMyPage">마이페이지</a></li>
			</ul>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToBulletin">자유게시판</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToNotice">공지사항</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/bdm/beforeMain/moveToNews.do" id="moveToNews">뉴스</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/bdm/beforeMain/moveToMain.do">로그아웃</a>
		</li>
	</ul>
<a>${user.getName()}님 반갑습니다.</a>
</body>
</html>