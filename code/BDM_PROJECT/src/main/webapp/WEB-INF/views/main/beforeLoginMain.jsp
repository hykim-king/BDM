<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<script>
document.addEventListener("DOMContentLoaded", function(){
	
	console.log( "main!" );
	const moveToRegBtn = document.querySelector("#moveToReg");
	const moveToNoticeBtn = document.querySelector("#moveToNotice");
	const moveToBulletinBtn = document.querySelector("#moveToBulletin");
	const moveToNewsBtn = document.querySelector("#moveToNews");
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	const gumsaekBtn = document.querySelector("#gumsaek"); 
	const searchWordTxt = document.querySelector("#searchWord");
     
	gumsaekBtn.addEventListener("click", function(e){
		doRetrieve(1, searchWordTxt);
	});
	searchWordTxt.addEventListener("keyup", function(e) {
        console.log("keyup:" + e.keyCode);
        if (13 == e.keyCode) {
            doRetrieve(1, searchWordTxt);
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
	
     moveToRegBtn.addEventListener("click", function(e){
    	 location.href = "/bdm/user/moveToReg.do";
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
     
     $("#doLogin").on("click",function(e){
         console.log( "doLogin click!" );
         
         let id = document.querySelector("#id").value;
         if(eUtil.isEmpty(id)==true){
             alert('아이디를 입력 하세요.');
             document.querySelector("#id").focus();
             return;
         }
         
         let pw = document.querySelector("#pw").value;
         if(eUtil.isEmpty(pw)==true){
             alert('비번을 입력 하세요.');
             document.querySelector("#pw").focus();
             return;
         }
         
         if(confirm("로그인 하시겠습니까?")===false) return;
         
         $.ajax({
             type: "POST",
             url:"/bdm/beforeMain/doLogin.do",
             asyn:"true",
             dataType:"json",
             data:{
                 "id": id,
                 "pw": pw
             },
             success:function(data){//통신 성공
                 console.log("data.msgId:"+data.msgId);
                 console.log("data.msgContents:"+data.msgContents);
                 
                 if("10" == data.msgId){
                     alert(data.msgContents);
                     document.querySelector("#id").focus();
                 }else if("20" == data.msgId){
                     alert(data.msgContents);
                     document.querySelector("#pw").focus();                 
                 }else if("30" == data.msgId){
                     alert(data.msgContents);
                     location.href = "/bdm/beforeMain/moveToAfterMain.do";
                 }
             },
             error:function(data){//실패시 처리
                 console.log("error:"+data);
             },
             complete:function(data){//성공/실패와 관계없이 수행!
                 console.log("complete:"+data);
             }
         });         
         
         
     });//--#doLogin
     
     
 });//--document ready
</script>
</head>
<body>
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link active" aria-current="page" href="/bdm/index.jsp">Balance Diet Management</a>
	  </li>
	  <li class="nav-item dropdown">
	    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">MEMBER</a>
	    <ul class="dropdown-menu">
	      <li><a class="dropdown-item" href="/bdm/user/moveToReg.do" id="moveToReg">회원 가입</a></li>
	      <li><a class="dropdown-item" href="#" id="doFindAccount">ID/PW 찾기</a></li>
	      <li><hr class="dropdown-divider"></li>
	      <li><a class="dropdown-item" href="/bdm/beforeMain/moveToMyPage.do" id="moveToMyPage">마이페이지</a></li>
	    </ul>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToBulletin">자유게시판</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/bdm/notice/doRetrieve.do" id="moveToNotice">공지사항</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/bdm/beforeMain/moveToNews.do" id="moveToNews">뉴스</a>
	  </li>
	</ul>
    <div class ="container-scroller">
        <div class="search-container" id="search_area">
            <div class="search">
                <form action="#" method="get" id="gumsaekFrm" name="gumsaekFrm">
                    <input type="hidden" name="pageNo" id="pageNo" />
                    <a href="#" class="link_main"> 
                        <img src="${CP}/resources/images/logo-mini.png" alt="로고">
                    </a>
                    <input type="text" placeholder="검색어를 입력하세요" id = "searchWord" name="searchWord" class="search-input">
                    <button type = "submit" class="search-button" id = "gumsaek"><img src="${CP}/resources/images/search_icon.png" alt=""></button>
                </form>
            </div>
            
        </div> 
    </div>

    <fieldset style="width: 300px; display: inline-block; vertical-align: top; position: relative;">
        <legend>로그인</legend>
        <div>
	        <form action="#" method="post">
	        
	            <table>
	                <tr>
	                    <td>
	                        <label for="id">아이디</label>
	                    </td>
	                    <td>
	                        <input type="text" id="id" name="id" size="20" maxlength="30">
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                        <label for="pw">비밀번호</label>
	                    </td>
	                    <td>
	                        <input type="password" id="pw" name="pw" size="20" maxlength="30">
	                    </td>
	                </tr>
	                <tr>
	                	<td><input type="button" value="로그인" id="doLogin" style="height: 100%;"></td>
	                </tr>
	            </table>
	        </form>
	        <div style="display: inline-block; position: absolute; top: 0; right: 0; height: 60px;">
	        </div>
        </div>
    </fieldset>
    <input type="button" value="공지사항"  id="moveToNotice">
    <input type="button" value="자유게시판"  id="moveToBulletin">
    <input type="button" value="뉴스"  id="moveToNews">
    <input type="button" value="마이페이지"  id="moveToMyPage">    
</body>
</html>