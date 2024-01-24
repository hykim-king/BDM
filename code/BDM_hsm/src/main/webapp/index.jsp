<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
</head>
<body>
user:${user}
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
      <li><a class="dropdown-item" href="#">새로운 탭</a></li>
    </ul>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">새로운 네비게이션 탭</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/bdm/login/loginView.do">로그인</a>
  </li>
</ul>
</body>
</html>