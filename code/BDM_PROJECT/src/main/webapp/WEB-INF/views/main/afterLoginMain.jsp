<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
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
      border: 1px solid #FDCE64;
      border-radius: 20px 0 0 20px;
      font-size: 16px;
      outline: none;
    }
    .search-button {
      width: 50px;
      padding: 10px;
      background-color: #FDCE64;
      border: none;
      border-radius: 0 20px 20px 0;
      cursor: pointer;
      font-size: 16px;
    }
    .search-input:focus {
      border-color: #007BFF;
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
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<jsp:include page="/WEB-INF/cmn/sidebar.jsp"></jsp:include>
<title>BDM</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	
    moveToMyPageBtn.addEventListener("click", function(e){
		window.location.href = "${CP }/nutrient/doRetrieveOneDay.do";
    });
});
</script>
</head>
<body>
    <fieldset style="width: 300px; display: inline-block; vertical-align: top; position: relative; float:right;">

        <legend>환영합니다.</legend>
        <div>
	        <form action="#" method="post">
	        
	            <table>
	                <tr>
	                    <td>
	                        <label for="id">${user.getName()}님</label>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                        <input type="button" id = "moveToMyPage" value = "마이페이지">
	                    </td>
	                </tr>
	            </table>
	        </form>
	        <div style="display: inline-block; position: absolute; top: 0; right: 0; height: 60px;">
	        </div>
        </div>
    </fieldset>
</body>
</html>