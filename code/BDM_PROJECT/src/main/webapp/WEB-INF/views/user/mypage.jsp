<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="CP" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<!-- Required meta tags -->
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Mypage Test</title>
<!-- plugins:css -->
<link rel="stylesheet" href="${CP}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="${CP}/resources/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet" href="${CP}/resources/jvectormap/jquery-jvectormap.css">
<link rel="stylesheet" href="${CP}/resourcess/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="${CP}/resources/owl-carousel-2/owl.carousel.min.css">
<link rel="stylesheet" href="${CP}/resources/owl-carousel-2/owl.theme.default.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="${CP}/resources/css/style.css">
<!-- End layout styles -->
<link rel="shortcut icon" href="${CP}/resources/images/favicon.png" />
<!-- plugins:js -->
<script src="${CP }/resources/vendors/js/vendor.bundle.base.js"></script>

<script src="${CP }/resources/vendors/chart.js/Chart.min.js"></script>
<script src="${CP }/resources/vendors/progressbar.js/progressbar.min.js"></script>
<script src="${CP }/resources/vendors/jvectormap/jquery-jvectormap.min.js"></script>
<script src="${CP }/resources/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${CP }/resources/vendors/owl-carousel-2/owl.carousel.min.js"></script>

<script src="${CP}/resources/js/off-canvas.js"></script>
<script src="${CP}/resources/js/hoverable-collapse.js"></script>
<script src="${CP}/resources/js/misc.js"></script>
<script src="${CP}/resources/js/settings.js"></script>
<script src="${CP}/resources/js/todolist.js"></script>
<!-- endinject -->
<!-- Custom js for this page -->
<script src="${CP}/resources/js/dashboard.js"></script>

<!-- End custom js for this page -->
<title>Insert title here</title>

<script>
document.addEventListener("DOMContentLoaded",function(){
	
	const addBtn = document.querySelector("#add");
	
	addBtn.addEventListener("click", function(e){
		window.location.href = "/bdm/nutrient/moveToNut.do";
	});
  
});


</script>
<script>
        // 페이지 로드 후 실행되는 함수
$(document).ready(function () {
            // 사용자의 생년월일 데이터를 가져와서 계산
	var userBirthNumber = "${user.birth}";
	var age = calculateAge(userBirthNumber);

            // 계산된 만 나이를 출력
	var ageInfoElement = $("#ageInfo");
	ageInfoElement.html("<strong>나이</strong> " + age + "세");
});

        // 생년월일을 받아서 만 나이를 계산하는 함수
function calculateAge(birth) {
	var birthString = birth.toString();
	var twoDigitYear = parseInt(birthString.substr(0, 2), 10);

	var fullYear = (twoDigitYear >= 50) ? 1900 + twoDigitYear : 2000 + twoDigitYear;
	var month = parseInt(birthString.substr(2, 2), 10) - 1;
	var day = 1;

	var birthdate = new Date(fullYear, month, day);
	var currentDate = new Date();

	var age = currentDate.getFullYear() - birthdate.getFullYear();

    if (currentDate.getMonth() < birthdate.getMonth() ||
       (currentDate.getMonth() === birthdate.getMonth() && currentDate.getDate() < birthdate.getDate())) {
       		age--;
    }

    return age;
}
</script>
<script>
    let myChartOne = document.getElementById('myChartOne').getContext('2d');

    let barChart = new chart(myChartOne,{
        type : 'bar',
        data:{
            labels : ['학원','연구원','출판사', '미디어사','우니브'],
            datasets : [{
                label : '바울랩 매출액' ,
                data : [
                    10,
                    100,
                    100,
                    200,
                    1000 
                ]
            }]
        }
    })
    
</script>
</head>
<body>
	
    <div class ="container-scroller">
        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
                <a class="sidebar-brand brand-logo" href="index.jsp" src="${CP}/resources/images/logo.png" alt="logo" ></a>
                <a class="sidebar-brand brand-logo-mini" href="index.html"><img src="${CP}/resources/images/logo-mini.png" alt="logo" /></a>
            </div>
            <ul class="nav">
                <li class="nav-item menu-item">
                    <span class="nav-link">Navigation</span>
                </li>
                <li class="nav-item menu-item">
                    <a class="nav-link" href="">
                        <span class="menu-icon">
                            <i class="mdi mdi-speedometer"></i>
                        </span>
                        <span class="menu-title">메인화면</span>
                    </a>    
                </li><!--메인화면 이동-->
                <li class="nav-item menu-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
                        <span class="menu-icon">
                            <i class="mdi mdi-laptop"></i>
                        </span>
                        <span class="menu-title">게시판</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="ui-basic">
                        <ul class="nav flex-column sub-menu">
                          <li class="nav-item"> <a class="nav-link" href="">자유게시판</a></li>
                          <li class="nav-item"> <a class="nav-link" href="">공지사항</a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item menu-items">
                    <a class="nav-link" href="">
                      <span class="menu-icon">
                        <i class="mdi mdi-playlist-play"></i>
                      </span>
                      <span class="menu-title">뉴스</span>
                    </a>
                </li>
            </ul><!--nav-->
        </nav><!-- sidebar-->
        <div class="container-fluid page-body-wrapper">
            <nav class="navbar p-0 fixed-top d-flex flex-row">
                <div class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
                  <a class="navbar-brand brand-logo-mini" href=""><img src="${CP}/resources/images/logo-mini.png" alt="logo" /></a>
                </div>
                <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
                    <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                        <span class="mdi mdi-menu"></span>
                    </button>
                    <ul class="navbar-nav navbar-nav-right">
                        <li class="nav-item dropdown">
                            <a class="nav-link" id="profileDropdown" href="#" data-toggle="dropdown">
                            <div class="navbar-profile">
                                <img class="img-xs rounded-circle" src="${CP}/resources/images/faces/face15.jpg" alt="">
                                <p class="mb-0 d-none d-sm-block navbar-profile-name">${user.name}</p>
                                <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                            </div>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
                                <h6 class="p-3 mb-0">Profile</h6>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item preview-item">
                                    <div class="preview-thumbnail">
                                        <div class="preview-icon bg-dark rounded-circle">
                                            <i class="mdi mdi-settings text-success"></i>
                                        </div>
                                    </div>
                                    <div class="preview-item-content">
                                    <p class="preview-subject mb-1">Settings</p>
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
                                        <p class="preview-subject mb-1">Log out</p>
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
            <div class="main-panel">
                <div class="container mt-4">
                    <div class="row ">
                        <!-- 왼쪽: 프로필 사진 -->
                        <div class="col-md-3">
                            <div class="card h-100">
                                <div class="card-body d-flex justify-content-center align-items-center">
                                    <img src="${CP}/resources/images/faces/face14.jpg" alt="프로필 사진" class="profile-img img-fluid img-thumbnail">
                                </div>
                                <div class="card-footer text-center">
                                    <h5 class="card-title">프로필</h5>
                                </div>
                            </div>    
                        </div>
                        <!-- 오른쪽: 개인 정보 -->
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h2>개인 정보</h2>
                                    <p><strong>이름    </strong>${user.name}</p>
                                    <p id="ageInfo"></p>
                                    <p><strong>키      </strong> ${user.height} cm</p> 
                                    <p><strong>몸무게  </strong>${user.weight} kg</p>
                                    <!-- 추가적인 개인 정보를 나열할 수 있습니다. -->
                                </div>

                            </div>
                        </div> 
                        <div class="card_no_bg col-md-3">
                            <button class="btn btn-primary full_buton"><strong>먹은음식 추가하기</strong></button>
                        </div>
                    </div>
                   
                    <div class="row mdi-arrow-top-right">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                              <div class="card-body">
                                <h4 class="card-title">2024.01.27 토요일</h4>
                                <canvas id="myChartOne"></canvas>
                                ${user }
                                <canvas id="lineChart" style="height:250px"></canvas>
                              </div>
                            </div>
                          </div>
                    </div>  
                </div>
            </div>
            
        </div><!--page-body-wrapper -->
    </div><!--container-->
</body>
</html>