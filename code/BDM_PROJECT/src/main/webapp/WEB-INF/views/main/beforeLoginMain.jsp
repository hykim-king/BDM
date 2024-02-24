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
<script>
document.addEventListener("DOMContentLoaded", function(){
	
	console.log( "main!" );
     
     $("#doLogin").on("click",function(e){
    	 window.location.href = "${CP }/beforeMain/moveToLogin.do";
     });//--#doLogin
     
     $("#findId").on("click",function(e){
     	var width = 750;
         var height = 1200;
         var left = (window.innerWidth - width) / 2;
         var top = (window.innerHeight - height) / 2;
         myWindow = window.open('../beforeMain/moveToFindId.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
     }); // --#moveToMain
     
     $("#findPassword").on("click",function(e){
     	var width = 750;
         var height = 1200;
         var left = (window.innerWidth - width) / 2;
         var top = (window.innerHeight - height) / 2;
         myWindow = window.open('../beforeMain/moveToFindPassword.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
     }); // --#moveToMain
     
     $("#moveToReg").on("click",function(e){
     	location.href = "/bdm/user/moveToReg.do";
     }); // --#moveToMain     
 });//--document ready
</script>
</head>
<body>
    <fieldset style="width: 300px; display: inline-block; vertical-align: top; position: relative; float:right;">

        <legend>로그인</legend>
        <div>
            <form action="#" method="post">
            
                <table>
                    <tr>
                        <td class="col"><input type="button" value="BDM 로그인" id="doLogin" style="height: 100%;"></td>
                    </tr>
                    <tr>
                        <td class="col"><input type="button" value="아이디 찾기" id="findId" style="height: 100%;"></td>
                        <td class="col"><input type="button" value="비밀번호 찾기" id="findPassword" style="height: 100%;"></td>
                        <td class="col"><input type="button" value="회원 가입" id="moveToReg" style="height: 100%;"></td>
                    </tr>
                </table>
            </form>
            <div style="display: inline-block; position: absolute; top: 0; right: 0; height: 60px;">
            </div>
        </div>
    </fieldset>
    <fieldset style="width: 300px; display: inline-block; vertical-align: top; position: relative; float:right;">
        <legend>인기 검색어</legend>
        <c:choose>
             <c:when test="${ not empty wordList }">  
                 <!-- 반복문 -->
                 <c:forEach var="vo" items="${wordList.subList(0, (wordList.size() < 5 ? wordList.size() : 5))}" varStatus="status">
                     <table>
                     <tr>
                         <td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
                         <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.searchWord}" escapeXml="true" /></td>
                     </tr>
                     </table>
                 </c:forEach>
                 <!--// 반복문 -->
             </c:when>
             <c:otherwise>
                 <tr>
                     <td colspan="99" class="text-center">인기검색어가 없습니다.</td>
                 </tr>
             </c:otherwise>
         </c:choose>
    </fieldset>
</body>

</html>