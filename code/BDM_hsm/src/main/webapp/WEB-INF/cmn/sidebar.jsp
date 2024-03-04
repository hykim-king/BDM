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
	const monitorBtn = document.querySelector("#monitor");
	
	// 서버로부터 전달된 로그인 여부 정보
    const isLoggedIn = <%= session.getAttribute("user") != null %>;
    
    <c:if test = "${not empty user}">
    const userFilter = ${user.userFilter}
    </c:if>
    
    updateButtonVisibility();

    loginBTN.addEventListener("click", function(e){
        alert('로그인 페이지로 이동합니다.');
        window.location.href = "/bdm/beforeMain/moveToLogin.do";
    });

    logoutBTN.addEventListener("click", function(e){
        alert('로그아웃 되었습니다.');
        window.location.href = "/bdm/beforeMain/popSearchWord.do";
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
               /* alert('로그아웃 되었습니다.'); */
               window.location.href = "/bdm/beforeMain/popSearchWord.do";
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
            if(userFilter == 1){
            	monitorBtn.style.display = "block";
            }
            else monitorBtn.style.display = "none";
        } else {
            logoutBTN.style.display = "none";
            loginBTN.style.display = "block";
            monitorBtn.style.display = "none";
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
  	<li><a class="side-link" href="/bdm/beforeMain/moveToLogin.do" id="login">로그인</a></li>
  	<li><a class="side-link" href="/bdm/beforeMain/doLogout.do" id="logout">로그아웃</a></li>
    <li>
      <a href="#">게시판</a>
      <ul>
        <li><a href="/bdm/bulletin/doRetrieve.do">자유게시판</a></li>
        <li><a href="/bdm/notice/doRetrieve.do">공지사항</a></li>
        <li><a href="/bdm/qa/doRetrieve.do">Q&A</a></li>
      </ul>
    </li>
    <li>
      <a href="/bdm/news/doRetrieve.do">뉴스</a>
    </li>
    <li>
      <a href="/bdm/beforeMain/moveToUserMonitor.do" id = "monitor">회원 관리</a>
    </li>
  </ul>
</aside>

</body>
</html>