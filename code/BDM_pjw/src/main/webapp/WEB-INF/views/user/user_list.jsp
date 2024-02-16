<%@page import="com.bdm.ehr.user.domain.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
    UserVO dto = (UserVO)request.getAttribute("searchVO");

%>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/ehr/resources/css/user.css">
<style> 
       
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>

</head>
<body>
  <h2>회원관리</h2>
  <hr/>
    
   <div>
    
    <form action="#" method="get" name="userFrm" style="display: inline;">
           <input type="hidden" name="pageNo" >
        <div>
            <!-- 검색구분 -->
            <select name="searchDiv" id="searchDiv" class="select">
              <option value="">전체</option>
              <option value="10" <% if("10".equals(dto.getSearchDiv())){out.print("selected");} %>  >사용자ID</option>
              <option value="20" <% if("20".equals(dto.getSearchDiv())){out.print("selected");} %>  >이름</option>
              <option value="30" <% if("30".equals(dto.getSearchDiv())){out.print("selected");} %>  >이메일</option>
            </select>
            <!-- 검색어 -->
            <input type="text"  class="input" value="${searchVO.searchWord }" name="searchWord" id="searchWord" placeholder="검색어를 입력하세요">
            <!-- pageSize: 10,20,30,50,10,200 -->
            <select name="pageSize" id="pageSize"  class="select">
               <option value="10"   <% if(10L==dto.getPageSize()){out.print("selected");} %>  >10</option>                 
               <option value="20"   <% if(20L==dto.getPageSize()){out.print("selected");} %>  >20</option>
               <option value="30"   <% if(30L==dto.getPageSize()){out.print("selected");} %>  >30</option>
               <option value="50"   <% if(50L==dto.getPageSize()){out.print("selected");} %>  >50</option>
               <option value="100"  <% if(100L==dto.getPageSize()){out.print("selected");} %> >100</option>
               <option value="200"  <% if(200L==dto.getPageSize()){out.print("selected");} %> >200</option>
            </select>    
    <!-- button -->
    <input type="button" class="btn" value="조회" id="doRetrieve" onclick="window.doRetrieve(1);">
    <input type="button" class="btn" value="등록" id="moveToReg"  onclick="window.moveToReg();">
        </div>
    </form>
    
    <!-- table -->
    <table id="userTable">    
        <thead>
        <tr>
            <th>번호</th>
            <th>사용자ID</th>
            <th>이름</th>
            <th>이메일</th>
            <th>등급</th>
            <th>로그인</th>
            <th>추천</th>
            <th>수정일</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <%-- 조회데이터가 있는 경우:jsp comment(html에 노출 않됨) --%>
            <c:when test="${not empty list }">
		        <c:forEach var="vo" items="${list}">
			        <tr>
			            <td class="txt-center">${vo.no}</td>
			            <td class="txt-left">${vo.userId}</td>
			            <td class="txt-left">${vo.name }</td>
			            <td class="txt-left">${vo.email }</td>
			            <td class="txt-left">${vo.level }</td>
			            <td class="txt-right">${vo.login}</td>
			            <td class="txt-right">${vo.recommend }</td>   
			            <td class="txt-center">${vo.regDt }</td>         
			        </tr>
		        </c:forEach>
	        </c:when>
	        <%-- 조회데이터가 없는 경우:jsp comment(html에 노출 않됨) --%>
	        <c:otherwise>
	           <tr>
	               <td colspan="99">No data found.</td>
	           </tr>
	        </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <!--// table -------------------------------------------------------------->
   </div>
 <script type="text/javascript">
  //jquery event감지
  $("#searchWord").on("keypress",function(e){
	  console.log('searchWord:keypress');
	  //e.which : 13
	  console.log(e.type+':'+e.which);
	  if(13==e.which){
		  e.preventDefault();//버블링 중단
		  doRetrieve(1);
	  }
  });
 
 
   //jquery:table 데이터 선택     
   $("#userTable>tbody").on("click","tr" , function(e){
       console.log('----------------------------');
       console.log('userTable>tbody');
       console.log('----------------------------');    
       
       let tdArray = $(this).children();//td
       
       let userId = tdArray.eq(1).text();
       console.log('userId:'+userId);
       
       window.location.href ="/ehr/user/doSelectOne.do?userId="+userId;
       
   });
    
    function moveToReg(){
        console.log('----------------------------');
        console.log('moveToReg');
        console.log('----------------------------');  
        
        let frm = document.userFrm;
        frm.action = "/ehr/user/moveToReg.do";
        frm.submit();
        
       //window.location.href= '/ehr/user/moveToReg.do';
      
    }
    
    function  doRetrieve(pageNo){
        console.log('----------------------------');
        console.log('doRetrieve');
        console.log('----------------------------');
        
        let frm = document.forms['userFrm'];//form
        let pageSize = frm.pageSize.value;
        console.log('pageSize:'+pageSize);
        
        let searchDiv = frm.searchDiv.value;
        console.log('searchDiv:'+searchDiv);
        
        let searchWord = frm.searchWord.value;
        console.log('searchWord:'+searchWord);
        
        console.log('pageNo:'+pageNo);
        frm.pageNo.value = pageNo;
        
        console.log('pageNo:'+frm.pageNo.value);
        //pageNo
        frm.action = "/ehr/user/doRetrieve.do";
        //서버 전송
        frm.submit();
    }
</script>  
</body>
</html>