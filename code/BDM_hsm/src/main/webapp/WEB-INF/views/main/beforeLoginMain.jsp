<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>BDM</title> 
<script>
document.addEventListener("DOMContentLoaded", function(){
	
	console.log( "main!" );
	const moveToRegBtn = document.querySelector("#moveToReg");
	const moveToNoticeBtn = document.querySelector("#moveToNotice");
	const moveToBulletinBtn = document.querySelector("#moveToBulletin");
	const moveToNewsBtn = document.querySelector("#moveToNews");
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	
     
     moveToRegBtn.addEventListener("click", function(e){
    	 location.href = "/bdm/user/moveToReg.do";
     });
     moveToNoticeBtn.addEventListener("click", function(e){
         location.href = "/bdm/beforeMain/moveToNotice.do";
     });
     moveToBulletinBtn.addEventListener("click", function(e){
         location.href = "/bdm/bulletin/doRetrieve.do";
     });
     moveToNewsBtn.addEventListener("click", function(e){
         location.href = "/bdm/beforeMain/moveToNews.do";
     });
     moveToMyPageBtn.addEventListener("click", function(e){
         location.href = "/bdm/beforeMain/moveToMyPage.do";
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
    <a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToNotice">공지사항</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/bdm/beforeMain/moveToNews.do" id="moveToNews">뉴스</a>
  </li>
</ul>
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
</body>
</html>