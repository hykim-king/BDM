<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="com.test.bdm.nutrient.domain.NutrientVO"%>

<c:set var="CP" value="${pageContext.request.contextPath}" scope="page" />
>>>>>>> 899eb84f5bd42cee9ee4caf060e0ae577f140b54

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${CP}/resources/css/nav_style.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
// 버튼 클릭 이벤트 핸들러
document.getElementById('navbar-toggler').addEventListener('click', function() {
    var layerBox = document.querySelector('.layer_box');
    // 팝업이 열려있는지 확인
    var isOpen = layerBox.getAttribute('aria-hidden') === 'false';
    // 팝업 상태를 토글
    isOpen ? closePopup() : openPopup();
});

// 팝업 열기 함수
function openPopup() {
    var layerBox = document.querySelector('.layer_box');
    layerBox.style.display = 'block';
    layerBox.setAttribute('aria-hidden', 'false');
}

// 팝업 닫기 함수
function closePopup() {
    var layerBox = document.querySelector('.layer_box');
    layerBox.style.display = 'none';
    layerBox.setAttribute('aria-hidden', 'true');
}
</script>
<script>

</script>
</head>
<body>
	<div id="wrap">
        <nav class="navbar navbar-expand-md bg-primary navbar-dark">
            <div class="container">
                <div class="d-flex align-items-center"> <!-- 로고와 버튼을 감싸는 부모 요소 -->
                    <div id="topAsideArea" class="menu_area">
                        <button id="navbar-toggler" type="button" class="btn_menu" data-bs-toggle="#navbarNav-menu" 
                        aria-haspopup="navbarNav-menu" aria-expanded="false" aria-label="Toggle navigation"
                        expanded="false" style="background-image:url(${CP}/resources/images/sp_main.ae81c9d5.png);">
                        </button>
                        <div class="layer_box" aria-hidden="true">
                            <div class="box_content">
                                <iframe src="menuBTN.jsp" title="확장영역" width="100%" height="100%"></iframe>
                            </div>
                        </div>
                    </div>
                    <a href="#" class="navbar-brand">
                        <img src="${CP}/resources/images/logo.png" alt=""/>
                    </a>
                </div>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav-menu"
                    aria-controls="navbarNav-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>
        <div class="search-container">
            <div class="search">
                <form action="#" method="get" id="gumsaekFrm" name="gumsaekFrm">
                    <input type="hidden" name="pageNo" id="pageNo" value="1" /> 
                    <a href="#" class="link_main"> 
                        <img src="${CP}/resources/images/logo_kor.jpg" alt="로고">
                    </a> 
                    <input type="text" placeholder="검색어를 입력하세요" id="searchWord"
                        name="searchWord" class="search-input">
                    <button type="submit" class="search-button" id="gumsaek">
                        <img src="${CP}/resources/images/search_icon.png" alt="">
                    </button>
                </form>
            </div>
        </div>
	</div>

</body>
<script>
    // 버튼 클릭 이벤트 핸들러
    document.getElementById('navbar-toggler').addEventListener('click', function() {
        var layerBox = document.querySelector('.layer_box');
        // 팝업이 열려있는지 확인
        var isOpen = layerBox.getAttribute('aria-hidden') === 'false';
        // 팝업 상태를 토글
        isOpen ? closePopup() : openPopup();
    });

    // 팝업 열기 함수
    function openPopup() {
        var layerBox = document.querySelector('.layer_box');
        layerBox.style.display = 'block';
        layerBox.setAttribute('aria-hidden', 'false');
    }

    // 팝업 닫기 함수
    function closePopup() {
        var layerBox = document.querySelector('.layer_box');
        layerBox.style.display = 'none';
        layerBox.setAttribute('aria-hidden', 'true');
    }
</script>
</head>
<style>
    .bg-primary{
        background-color: #f7e9e8;
    }
</style>
</html>