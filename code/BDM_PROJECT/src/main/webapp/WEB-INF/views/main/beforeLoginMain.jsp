<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<jsp:include page="/WEB-INF/cmn/sidebar.jsp"></jsp:include>
<title>BDM</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	
	console.log( "main!" );
	const moveToNoticeBtn = document.querySelector("#moveToNotice");
	const moveToBulletinBtn = document.querySelector("#moveToBulletin");
	const moveToNewsBtn = document.querySelector("#moveToNews");
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	
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
<body>
    <fieldset style="width: 300px; display: inline-block; vertical-align: top; position: relative; float:right;">

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
</body>
</html>