<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:set var="CP" value="${pageContext.request.contextPath}" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<title>Insert title here</title>
</head>
<style>
.black-han,
.noto-sans {
    font-weight: 400;
    font-style: normal;
}

.black-han {
    font-family: "Black Han Sans", sans-serif;
}

.noto-sans {
    font-family: "Noto Sans KR", sans-serif;
    font-optical-sizing: auto;
}

body {
    background-color: #fdce64;
    margin: 0;
    padding: 0;
}

.wrap {
    background-color: #fdce64;
    padding: 20px 0;
}

.container {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.member {
    text-align: center;
}

.title {
    height: 50px;
    display: flex;
    align-items: center;
}

.logo_img {
    width: 120px;
    height: auto;
    margin-right: 10px;
}

span {
    font-weight: bold;
    font-size: 25px;
    font-family: sans-serif;
    text-align: center;
    line-height: 50px;
}

p {
    font-family: sans-serif;
    font-size: 15px;
    margin-bottom: 0.4rem important;
}

.cum_icon {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.icon {
    margin: 0 10px;
}

.icon img {
    width: 30px;
    height: auto;
}

</style>
<body>
	<div class="wrap">
        <div class="container">
            <div class="member col-md">
                <div class="title"><img src="footer_logo.png" class="logo_img"><span>Balance Diet PROJECT</span></div>
                <p>Kang Sohee</p>
                <p>Kim Jiwoo</p>
                <p>Lee Inkyung</p>
                <p>Park Seonguk</p>
                <p>Park Jinwoo</p>
                <p>Chun Doheon</p>
                <p>Heo Sungmu</p>
            </div>
            <div class="cum_icon">
                <a href=""><div class="icon"><img src="${CP }/resources/images/facebook.png"></div></a>
                <a href=""><div class="icon"><img src="${CP }/resources/images/instagram.png"></div></a>
                <a href=""><div class="icon"><img src="${CP }/resources/images/twitter.png"></div></a>
                <a href=""><div class="icon"><img src="${CP }/resources/images/youtube.png"></div></a>
            </div>
        </div>
    </div>
</body>
</html>