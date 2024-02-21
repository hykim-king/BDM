<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
<%-- document.addEventListener("DOMContentLoaded", function(){
	const logoutBTN = document.querySelector("#logout");
	const loginBTN = document.querySelector("#login");
	
	// 서버로부터 전달된 로그인 여부 정보
    const isLoggedIn = <%= session.getAttribute("user") != null %>;
    // 초기화 시 로그인 상태에 따라 버튼 표시 설정
    updateButtonVisibility();

    loginBTN.addEventListener("click", function(e){
        alert('로그인 페이지로 이동합니다.');
        window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
    });

    logoutBTN.addEventListener("click", function(e){
        alert('로그아웃 되었습니다.');
        window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
    });
	
	loginBTN.addEventListener("click", function(e){
   	   alert('로그인 페이지로 이동합니다.');
   	   return;
   	 window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
    });
	
	logoutBTN.addEventListener("click", function(e){
    	$.ajax({
            type: "GET",
            url:"/bdm/beforeMain/doLogout.do",
            asyn:"true",
            dataType:"html",
            data:{
            },
            success:function(data){//통신 성공     
               alert('로그아웃 되었습니다.');
               window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
    });
	
	function updateButtonVisibility() {
        if (isLoggedIn) {
            logoutBTN.style.display = "block";
            loginBTN.style.display = "none";
        } else {
            logoutBTN.style.display = "none";
            loginBTN.style.display = "block";
        }
    }
}); // -- DOM end --%>
</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/bdm/index.jsp">Balance Diet Management</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<%-- <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do">메인으로</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/user/moveToReg.do" tabindex="-1" aria-disabled="true">회원가입</a></li>
				<li class="nav-item"><a class="nav-link" href="#" id="doFindAccount">ID/PW 찾기</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToMyPage.do" id="moveToMyPage">마이페이지</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToBulletin">자유게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/notice/doRetrieve.do" id="moveToNotice">공지사항</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/news/doRetrieve.do" id="moveToNews">뉴스</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="logout">${vo.id}</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="logout">로그아웃</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="login">로그인</a></li>
			</ul>
		</div> --%>
	</div>
</nav>

</body>
</html>