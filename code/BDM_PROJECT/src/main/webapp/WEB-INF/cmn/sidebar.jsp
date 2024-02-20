<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="${CP}/resources/css/sidebar2.css">
<script>
document.addEventListener("DOMContentLoaded", function(){
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
}); // -- DOM end
</script>

</head>
<body>
<%-- cp:${CP} --%>
<aside class="side-bar">
  <section class="side-bar__icon-box">
    <section class="side-bar__icon-1">
      <div></div>
      <div></div>
      <div></div>
    </section>
  </section>
  <ul>
  	<li><a class="side-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="login">로그인</a></li>
  	<li><a class="side-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="logout">로그아웃</a></li>
    <li>
      <a href="/bdm/beforeMain/moveToMyPage.do"><i class="fa-solid fa-cat"></i> mypage</a>
    </li>
    <li>
      <a href="#">게시판</a>
      <ul>
        <li><a href="/bdm/bulletin/doRetrieve.do">자유게시판</a></li>
        <li><a href="/bdm/notice/doRetrieve.do">공지사항</a></li>
        <li><a href="#">Q&A</a></li>
      </ul>
    </li>
    <li>
      <a href="/bdm/beforeMain/moveToNews.do">news</a>
      <ul>
        <li><a href="#">text1</a></li>
        <li><a href="#">text2</a></li>
      </ul>
    </li>
    <li>
      <a href="#">notice</a>
      <ul>
        <li><a href="#">text1</a></li>
        <li><a href="#">text2</a></li>
        <li><a href="#">text3</a></li>
        <li><a href="#">text4</a></li>
      </ul>
    </li>
  </ul>
</aside>

</body>
</html>