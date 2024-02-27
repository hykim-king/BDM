<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.test.bdm.nutrient.domain.NutrientVO" %>
   
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport"  content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${CP}/resources/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${CP}/resources/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${CP}/resources/vendors/jvectormap/jquery-jvectormap.css">
    <link rel="stylesheet" href="${CP}/resources/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="${CP}/resources/vendors/owl-carousel-2/owl.carousel.min.css">
    <link rel="stylesheet" href="${CP}/resources/vendors/owl-carousel-2/owl.theme.default.min.css">
    <link rel="stylesheet" href="${CP}/resources/css/style.css" >
    <link rel="shortcut icon" href="${CP}/resources/images/favicon.png" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="${CP}/resources/vendors/js/vendor.bundle.base.js"></script>
	<script src="${CP}/resources/vendors/progressbar.js/progressbar.min.js"></script>
	<script src="${CP}/resources/vendors/jvectormap/jquery-jvectormap.min.js"></script>
	<script src="${CP}/resources/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="${CP}/resources/vendors/owl-carousel-2/owl.carousel.min.js"></script>
	<script src="${CP}/resources/js/off-canvas.js"></script>
	<script src="${CP}/resources/js/hoverable-collapse.js"></script>
	<script src="${CP}/resources/js/misc.js"></script>
	<script src="${CP}/resources/js/settings.js"></script>
	<script src="${CP}/resources/js/todolist.js"></script>
	<script src="${CP}/resources/js/dashboard.js"></script>
<style>
    .card-body{
        color:#f7e9e8;
    }
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
      border: 1px solid #fdce64; 
      border-radius: 20px 0 0 20px;
      font-size: 16px;
      outline: none;
    }
    .search-button {
      width: 50px;
      padding: 10px;
      background-color: #fdce64;
      border: none;
      border-radius: 0 20px 20px 0;
      cursor: pointer;
      font-size: 16px;
    }
    .search-input:focus {
      border-color: #007bff;
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
<title>Insert title here</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	const gumsaekBtn = document.querySelector("#gumsaek");
	const searchWordTxt = document.querySelector("#searchWord");
	const moveToFindBtn = document.querySelector("#moveToFind");
     
	gumsaekBtn.addEventListener("click", function(e){
		let searchWordTxtV = document.querySelector("#searchWord").value;
        $.ajax({
            type: "POST",
            url:"/bdm/beforeMain/doSaveSearch.do",
            asyn:"true",
            dataType:"json",
            data:{
                words: searchWordTxtV
            },
            success:function(data){//통신 성공
            	// doRetrieve(1, searchWordTxtV);
                location.href = "/bdm/beforeMain/doGumsaek.do?pageNo=1&searchWord="+searchWordTxtV;
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
	});
	
	searchWordTxt.addEventListener("keyup", function(e) {
        console.log("keyup:" + e.keyCode);
        let searchWordTxtV = document.querySelector("#searchWord").value;
        if (13 == e.keyCode) {
        	$.ajax({
                type: "POST",
                url:"/bdm/beforeMain/doSaveSearch.do",
                asyn:"true",
                dataType:"json",
                data:{
                    words: searchWordTxtV
                },
                success:function(data){//통신 성공
                    // doRetrieve(1, searchWordTxtV);
                	location.href = "/bdm/beforeMain/doGumsaek.do?pageNo=1&searchWord="+searchWordTxtV;
                },
                error:function(data){//실패시 처리
                    console.log("error:"+data);
                },
                complete:function(data){//성공/실패와 관계없이 수행!
                    console.log("complete:"+data);
                }
            });
        }
    });
	
	function doRetrieve(pageNo, searchWord) {
        console.log("doRetrieve pageNO:" + pageNo);
        console.log("doRetrieve searchWord:" + searchWord);

        let gumsaekForm = document.gumsaekFrm;
        gumsaekForm.pageNo.value = pageNo;
        gumsaekForm.action = "/bdm/beforeMain/doGumsaek.do";
        console.log("doRetrieve pageNO:" + gumsaekForm.pageNo.value);
        gumsaekForm.submit();
    }
}); // -- DOM end
</script>
</head>
<body>
	<nav class="navbar p-0 fixed-top d-flex flex-row">
                <div class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
                  <a class="navbar-brand brand-logo-mini" href=""><img src="${CP}/resources/images/mini_logo.png" alt="logo" /></a>
                </div>
                <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
                    <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                        <span class="mdi mdi-menu"></span>
                    </button>
                    <ul class="navbar-nav navbar-nav-right">
                        <li class="nav-item dropdown">
                            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
                                <h6 class="p-3 mb-0">로그인</h6>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item preview-item">
                                    <div class="preview-thumbnail">
                                        <div class="preview-icon bg-dark rounded-circle">
                                            <i class="mdi mdi-settings text-success"></i>
                                        </div>
                                    </div>
                                    <div class="preview-item-content">
                                    <p class="preview-subject mb-1" id = "moveToMod">회원 정보 수정</p>
                                    </div>
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item preview-item">
                                    <div class="preview-thumbnail">
                                        <div class="preview-icon bg-dark rounded-circle">
                                            <i class="mdi mdi-logout text-danger"></i>
                                        </div>
                                    </div>
                                    <div class="preview-item-content">
                                        <p class="preview-subject mb-1" id = "logout">Log out</p>
                                    </div>
                                </a>
                                <div class="dropdown-divider"></div>
                                <p class="p-3 mb-0 text-center">Advanced settings</p>
                            </div>
                        </li>
                    </ul>
                    <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                        <span class="mdi mdi-format-line-spacing"></span>
                    </button>
                </div> 
    </nav><!--header-->
<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
		<a class="navbar-brand" href="${CP }/beforeMain/popSearchWord.do"><img alt="로고" src="${CP}/resources/images/mini_logo.png"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<div class="search-container" id="search_area">
				<div class="search">
	                <form action="#" method="get" id="gumsaekFrm" name="gumsaekFrm">
	                    <input type="hidden" name="pageNo" id="pageNo" value = "1"/>
	                    <a href="#" class="link_main"> 
	                        <img src="${CP}/resources/images/mini_logo.png" alt="로고">
	                    </a>
	                    <input type="text" placeholder="검색어를 입력하세요" id = "searchWord" name="searchWord" class="search-input">
	                    <button type = "submit" class="search-button" id = "gumsaek"><img src="${CP}/resources/images/search_icon.png" alt=""></button>
	                </form>
	            </div>
			</div>
		</div>
	</div>
</nav>

</body>
</html>