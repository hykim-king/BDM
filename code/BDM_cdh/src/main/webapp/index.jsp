<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/ehr/favicon.ico">
<meta name="viewport"  content="width=device-width, initial-scale=1">
<link href="${CP}/resources/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="${CP}/resources/css/user.css">
<title>게시등록</title>
<script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${CP}/resources/js/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>

<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<ul class="nav">
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="<c:url value='/user/doSave.do'/>">로그인</a>
  </li>
 
</ul>        
</body>
</html>