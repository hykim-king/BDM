<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<title>BDM</title>
<link rel="stylesheet" href="${CP}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="${CP}/resources/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="${CP}/resources/vendors/jvectormap/jquery-jvectormap.css">
<link rel="stylesheet" href="${CP}/resources/vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="${CP}/resources/vendors/owl-carousel-2/owl.carousel.min.css">
<link rel="stylesheet" href="${CP}/resources/vendors/owl-carousel-2/owl.theme.default.min.css">
<link rel="stylesheet" href="${CP}/resources/css/style.css?after"> 
<link rel="shortcut icon" href="${CP}/resources/images/favicon.png" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
<script src="${CP }/resources/js/jquery-3.7.1.js"></script>
<script src="${CP }/resources/js/eUtil.js"></script>  
<script>
document.addEventListener("DOMContentLoaded", function(){
    
    console.log( "main!" );
    const doLogoutBtn = document.querySelector("#doLogout");
    const moveToNoticeBtn = document.querySelector("#moveToNotice");
    const moveToBulletinBtn = document.querySelector("#moveToBulletin");
    const moveToNewsBtn = document.querySelector("#moveToNews");
    const moveToMyPageBtn = document.querySelector("#moveToMyPage");
    
     
    doLogoutBtn.addEventListener("click", function(e){
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
     moveToNoticeBtn.addEventListener("click", function(e){
    	 location.href = "/bdm/notice/doRetrieve.do";
     });
     moveToBulletinBtn.addEventListener("click", function(e){
         location.href = "/bdm/bulletin/doRetrieve.do";
     });
     moveToNewsBtn.addEventListener("click", function(e){
         location.href = "/bdm/beforeMain/moveToNews.do";
     });
     moveToMyPageBtn.addEventListener("click", function(e){
         <c:if test="${empty user}">
           alert('로그인이 필요한 서비스입니다.');
           return;
         </c:if>
         window.location.href = "${CP }/nutrient/doRetrieveOneDay.do";
     });
 });//--document ready
</script>
</head>
<body>
    <div class ="container-scroller">
        <div class="search-container" id="search_area">
            <div class="search">
                <form action="#">
                    <a href="#" class="link_main"> 
                        <img src="${CP}/resources/images/logo-mini.png" alt="로고">
                    </a>
                    <input type="text" placeholder="검색어를 입력하세요" name="search" class="search-input">
                    <button type="submit" class="search-button"><img src="${CP}/resources/images/search_icon.png" alt=""></button>
                </form>
            </div>
            
        </div> 
    </div>
    <fieldset style="width: 300px; display: inline-block; vertical-align: top; position: relative;">
        <legend>환영합니다</legend>
        <div>
            <form action="#" method="post">
            
                <table>
                    <tr>
                        <td>
                            <label for="id">${user.name }님</label>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div>
            <input type="button" value="로그아웃"  id="doLogout">
        </div>
    </fieldset>
    <input type="button" value="공지사항"  id="moveToNotice">
    <input type="button" value="자유게시판"  id="moveToBulletin">
    <input type="button" value="뉴스"  id="moveToNews">
    <input type="button" value="마이페이지"  id="moveToMyPage">
</body>
</html>