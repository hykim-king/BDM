<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 링크를 추가하세요 -->
</head>
<body>

    <h1>회원가입</h1>

    <form action="/user/doRegister.do" method="get">
        <!-- 회원가입에 필요한 필드들을 추가하세요 -->
        <label for="userId">아이디:</label>
        <input type="text" id="userId" name="userId" required>
        
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>

        <label for="email">이메일:</label>
        <input type="text" id="email" name="email" required>

        <!-- 추가 필드들을 여기에 추가 -->

        <input type="submit" value="가입하기">
    </form>

    <!-- 기타 내용을 추가하세요 -->

</body>
</html>
