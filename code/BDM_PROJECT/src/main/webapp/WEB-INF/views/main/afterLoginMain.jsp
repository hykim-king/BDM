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
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
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
        location.href = "/bdm/notice/doRetrieve.do";
    });
    moveToBulletinBtn.addEventListener("click", function(e){
        location.href = "/bdm/bulletin/doRetrieve.do";
    });
    moveToMyPageBtn.addEventListener("click", function(e){
		window.location.href = "${CP }/nutrient/doRetrieveOneDay.do";
    });
    moveToNewsBtn.addEventListener("click", function(e){
		window.location.href = "${CP }/news/doRetrieve.do"
	});
	
});
</script>
</head>
<body>
	<div class ="container-scroller">
	<div class="search-container" id="search_area">
		<div class="search">
			<form action="#">
				<a href="#" class="link_main">
					<img src="${CP}/resources/images/logo-mini.png" alt="로고">
				</a>
				<input type="text" placeholder="검색어를 입력하세요" name="search" class="search-input">
				<button type="submit" class="search-button"><img src="${CP}/resources/images/search_icon.png" alt=""></button>
			</form>
		</div>
	</div>
</div>
<style>
    .search-container {
      max-width: 1920px;
      width:80%;
      margin: 0 auto;
      padding: 20px;
      text-align: center;
    }
    .search-input {
      width: 70%;
      padding: 10px;
      border: 1px solid #FDCE64;
      border-radius: 20px 0 0 20px;
      font-size: 16px;
      outline: none;
    }
    .search-button {
      width: 50px;
      padding: 10px;
      background-color: #FDCE64;
      border: none;
      border-radius: 0 20px 20px 0;
      cursor: pointer;
      font-size: 16px;
    }
    .search-input:focus {
      border-color: #007BFF;
    }
    .search-button img{
        width: 20px;
          height: 20px;
    }
    .link_main img{
        width:50px;
        height: auto;
    }
</style>
<a>${user.getName()}님 반갑습니다.</a>
</body>
</html>