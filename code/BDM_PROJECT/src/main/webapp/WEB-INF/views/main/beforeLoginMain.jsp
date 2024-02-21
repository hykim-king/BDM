<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<jsp:include page="/WEB-INF/cmn/sidebar.jsp"></jsp:include>
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
    
    #loginFieldset {
        width: 300px;
        position: absolute;
        top:  50%; /* 화면 상단에서 세로 중앙 정렬 */
        left: 0%; /* 화면 좌측에서 가로 중앙 정렬 */
        <!--transform: translate(-50%, -50%); /* 중앙 정렬을 위한 transform */
    }
</style>
<title>BDM</title>
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
	const moveToFindBtn = document.querySelector("#moveToFind");
	
	moveToFindBtn.addEventListener("click", function(e){
		location.href = "/bdm/beforeMain/moveToFind.do";
	});
     
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
         location.href = "/bdm/news/doRetrieve.do";
     });
     moveToMyPageBtn.addEventListener("click", function(e){
    	 alert('로그인이 필요한 서비스입니다.');
         location.href = "/bdm/beforeMain/moveToMain.do";
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
    <!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/bdm/index.jsp">Balance Diet Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToMain.do">메인으로</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToMyPage.do" id="moveToMyPage">마이페이지</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToBulletin">자유게시판</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bdm/notice/doRetrieve.do" id="moveToNotice">공지사항</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bdm/news/doRetrieve.do" id="moveToNews">뉴스</a></li>
                </ul>
            </div>
        </div>
    </nav> -->
	
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
	                	<td><input type="button" value="회원가입" id="moveToReg" style="height: 100%;"></td>
	                	<td><input type="button" value="계정 찾기" id="moveToFind" style="height: 100%;"></td>
	                </tr>
	            </table>
	        </form>
	        <div style="display: inline-block; position: absolute; top: 0; right: 0; height: 60px;">
	        </div>
        </div>
    </fieldset>
</body>
</html>