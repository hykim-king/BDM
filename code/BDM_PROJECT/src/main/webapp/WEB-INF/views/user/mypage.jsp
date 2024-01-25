<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Insert title here</title>
<script>
document.addEventListener("DOMContentLoaded",function(){
	
	const addBtn = document.querySelector("#add");
	
	addBtn.addEventListener("click", function(e){
		window.location.href = "/bdm/nutrient/moveToNut.do";
	});
});
</script>
</head>
<body>
${user }
    <div class = "container">
        <div class = 'row'>
            <div class = "col-lg-12">
                <h1 class = "page-header">마이페이지</h1>
            </div>
        </div>
        <!-- // 제목 ---------------------------------------------- -->
        <input type = "button" value = "추가하기" id = "add" name ="add">
    </div>
</body>
</html>