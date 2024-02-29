<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>NAVER LOGIN TEST</title>
    <style>
        /* 버튼 스타일 */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #45a049;
        }

        /* 로그아웃 링크 스타일 */
        .logout-link {
            color: #FF5733;
            text-decoration: none;
            font-weight: bold;
            margin-left: 10px;
        }

        .logout-link:hover {
            color: #E74C3C;
        }
    </style>
</head>
<body>
    <center>
        <c:choose>
            <c:when test="${sessionId != null}">
                <h2>네이버 아이디 로그인 성공하셨습니다!</h2>
                <h3>'${sessionId}' 님 환영합니다.</h3>
                <h3>
                    <a href="/bdm/beforeMain/checkSession.do" class="btn">메인화면 이동</a>
                    <a href="logout.do" class="logout-link">로그아웃</a>
                </h3>
            </c:when>
            <c:otherwise>
                <form action="login.userdo" method="post" name="frm" style="width: 470px;"></form>
                <br>
                <!-- 네이버 로그인 창으로 이동 -->
                <div id="naver_id_login" style="text-align: center">
                    <a href="${url}"> <img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" /></a>
                </div>
            </c:otherwise>
        </c:choose>
    </center>
</body>
</html>