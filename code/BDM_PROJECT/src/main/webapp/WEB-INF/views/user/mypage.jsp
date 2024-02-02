<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Insert title here</title>
<script>
document.addEventListener("DOMContentLoaded",function(){
	
	const addBtn = document.querySelector("#add");
	const count = 0;
	
	addBtn.addEventListener("click", function(e){
		window.location.href = "/bdm/nutrient/moveToNut.do";
	});
	
	/* if(count == 0){
		window.location.href = "${CP }/nutrient/doRetrieveOneDay.do";
		count = 1;
		return;
	} */
	
});
</script>
</head>
<body>
${user} <br/>
oneDay: ${oneDay} <br/>
thisWeek: ${thisWeek }
    <div class = "container">
        <div class = 'row'>
            <div class = "col-lg-12">
                <h1 class = "page-header">마이페이지</h1>
            </div>
        </div>
        <!-- // 제목 ---------------------------------------------- -->
        <input type = "button" value = "추가하기" id = "add" name ="add">
    </div>
    
    <h2>오늘</h2>
    <!-- table -->
    <table class="table table-bordered border-primary table-hover table-striped" id="boardTable">
      <thead>
        <tr >
          <th scope="col" class="text-center col-lg-1  col-sm-1">KCAL</th>
          <th scope="col" class="text-center col-lg-7  col-sm-8">탄수화물</th>
          <th scope="col" class="text-center col-lg-2  col-sm-1">단백질</th>
          <th scope="col" class="text-center col-lg-1  ">지방</th>
          <th scope="col" class="text-center col-lg-1  ">당류</th>
        </tr>
      </thead>         
      <tbody>
        <c:choose>
            <c:when test="${ not empty oneDay }">
                <tr>
                  <td class="text-center col-lg-1  col-sm-1"><c:out value="${oneDay.kcal}" escapeXml="true"/> </td>
                  <td class="text-left   col-lg-7  col-sm-8" ><c:out value="${oneDay.carbohydrate}" escapeXml="true"/></td>
                  <td class="text-center col-lg-2  col-sm-1"><c:out value="${oneDay.protein}" escapeXml="true"/></td>
                  <td class="            col-lg-1 "><c:out value="${oneDay.fat}" /></td>
                  <td class="text-end    col-lg-1 "><c:out value="${oneDay.sugars}" /></td>
                </tr>
              <!--// 반복문 -->      
            </c:when>
            <c:otherwise>
               <tr>
                <td colspan="99" class="text-center">조회된 데이터가 없습니다..</td>
               </tr>              
            </c:otherwise>
        </c:choose>
      </tbody>
    </table>
    <!--// table --------------------------------------------------------------> 
    
    <h2>이번 주</h2>
    <!-- table -->
    <table class="table table-bordered border-primary table-hover table-striped" id="boardTable">
      <thead>
        <tr >
          <th scope="col" class="text-center col-lg-1  col-sm-1">KCAL</th>
          <th scope="col" class="text-center col-lg-7  col-sm-8">탄수화물</th>
          <th scope="col" class="text-center col-lg-2  col-sm-1">단백질</th>
          <th scope="col" class="text-center col-lg-1  ">지방</th>
          <th scope="col" class="text-center col-lg-1  ">당류</th>
        </tr>
      </thead>         
      <tbody>
        <c:choose>
            <c:when test="${ not empty thisWeek }">
                <tr>
                  <td class="text-center col-lg-1  col-sm-1"><c:out value="${thisWeek.kcal}" escapeXml="true"/> </td>
                  <td class="text-left   col-lg-7  col-sm-8" ><c:out value="${thisWeek.carbohydrate}" escapeXml="true"/></td>
                  <td class="text-center col-lg-2  col-sm-1"><c:out value="${thisWeek.protein}" escapeXml="true"/></td>
                  <td class="            col-lg-1 "><c:out value="${thisWeek.fat}" /></td>
                  <td class="text-end    col-lg-1 "><c:out value="${thisWeek.sugars}" /></td>
                </tr>
              <!--// 반복문 -->      
            </c:when>
            <c:otherwise>
               <tr>
                <td colspan="99" class="text-center">조회된 데이터가 없습니다..</td>
               </tr>              
            </c:otherwise>
        </c:choose>
      </tbody>
    </table>
    <!--// table --------------------------------------------------------------> 
</body>
</html>