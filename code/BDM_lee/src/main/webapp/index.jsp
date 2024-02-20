<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />    

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"  content="width=device-width, initial-scale=1">
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script> -->

<link rel="shortcut icon" type="image/x-icon" href="/ehr/favicon.ico">
<link href="${CP}/resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${CP}/resources/css/user.css">

<title>index</title>

<script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${CP}/resources/js/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>
<script></script>

</head>
<body>
<c:redirect url="/beforeMain/moveToMain.do" />

<%--     여기는 index.jsp
    <ul class="nav">
	  <li class="nav-item">
	    <a class="nav-link active" aria-current="page" href="<c:url value='/login/loginView.do' />">로그인</a>
	  </li>
	  <li class="nav-item">
        <a class="nav-link" href="<c:url value='/user/moveToReg.do' />">회원가입</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/bulletin/doRetrieve.do' />">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/notice/doRetrieve.do' />">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/template/viewBlank.do' />">blank</a>
      </li>
      
	  <li class="nav-item">
	    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
	  </li>
	</ul> --%>
	
    <!-- 호출 http://localhost:8080/ehr/index.jsp -->
    <!-- 호출 http://localhost:8080/ehr/user/doRetrieve.do -->
    <!-- web.xml cf) tomcat cf)was -->
    <%-- <c:redirect url="/user/doRetrieve.do"/> --%>
    <%-- <c:redirect url="/login/loginView.do"/> --%>
    <%-- <c:redirect url="/file/uploadView.do"/> --%>
    <%-- <c:redirect url="/file/uploadView.do"/> --%>
    <%-- <c:redirect url="/file//dragNDropView.do"/> --%>
    <!-- http://localhost:8080/ehr/board/doRetrieve.do -->
    <%-- <c:redirect url="/board/doRetrieve.do"/> --%>
</body>
</html>
