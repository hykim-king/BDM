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
<title>BDM</title>
<script src="${CP }/resources/js/jquery-3.7.1.js"></script>
<script src="${CP }/resources/js/eUtil.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	
	moveToMyPageBtn.addEventListener("click", function(e){
        location.href = "/bdm/beforeMain/moveToMyPage.do";
    });
});

document.addEventListener("DOMContentLoaded", function(){
	const moveToBulletinBtn = document.querySelector("#moveToBulletin");
	
	moveToBulletinBtn.addEventListener("click", function(e){
        location.href = "/bdm/bulletin/doRetrieve.do";
    });
});

</script>
</head>
<body>
THIS IS AFTERLOGINMAIN <br/>
${user} <br/>
<input type="button" value="마이페이지"  id="moveToMyPage">
<input type="button" value="자유게시판"  id="moveToBulletin">
</body>
</html>