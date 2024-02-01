<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
<script>
document.addEventListener("DOMContentLoaded",function(){
	console.log("DOMContentLoaded");
	
	//javasript 선택자 
	const moveToRegBTN  = document.querySelector("#moveToReg");
	const doRetrieveBTN = document.querySelector("#doRetrieve");//목록 버튼
 	
	//form submit방지
	const bulletinForm = document.querySelector("#bulletinForm");
	const searchWordTxt = document.querySelector("#searchWord");

	//javascript:다건 이벤트 처리
	const rows = document.querySelectorAll("#bulletinTable>tbody>tr");
	//각행에 이벤트 처리
	rows.forEach(function(row) {
		row.addEventListener('click',function(e){
	         //클릭된 행의 모든 셀(td)을 가져 오기		
			 let cells = row.getElementsByTagName("td");
			 const postNo   = cells[5].innerText;
			 console.log('postNo:'+postNo);

       //javascript
       if(confirm('상세 조회 하시겠어요?')==false) return;
    	    let div = document.querySelector("#div").value;
    	    console.log('divs:'+divs);
            window.location.href = "${CP}/bulletin/doSelectOne.do?seq="+seq+"&divs="+divs;   

		});
	});

	
	
	moveToRegBTN.addEventListener("click",function(e){
		console.log("moveToRegBTN click");
		boardForm.pageNo.value = "1";/*null 처리 필수*/
		boardForm.div.value = document.querySelector("#divs").value;
		boardForm.action = "/bdm/bulletin/moveToReg.do";
		boardForm.submit();
		
	});
	
	
	
	searchWordTxt.addEventListener("keyup", function(e) {
		console.log("keyup:"+e.keyCode);
		if(13==e.keyCode){//
			doRetrieve(1);
		}
		//enter event:
	});	
	
	//form submit방지
	bulletinForm.addEventListener("submit", function(e) {
		console.log(e.target)
		e.preventDefault();//submit 실행방지
		
	});
	
	//목록버튼 이벤트 감지
	doRetrieveBTN.addEventListener("click",function(e){
		console.log("doRetrieve click");
		doRetrieve(1);
	});
	
	function doRetrieve(pageNo){
		console.log("doRetrieve pageNO:"+pageNo);
		
		let bulletinForm = document.bulletinForm;
		bulletinForm.divs.value = document.querySelector("#divs").value;
		bulletinForm.pageNo.value = pageNo;
		bulletinForm.action = "/bdm/bulletin/doRetrieve.do";
		console.log("doRetrieve pageNO:"+bulletinForm.pageNo.value);
		bulletinForm.submit();
	}
	
	
	
	//검색조건 변경!:change Event처리 
	searchDivsSelect.addEventListener("change",function(e){
		console.log("change:"+e.target.value);
		
		let chValue = e.target.value;
		if(""==chValue){ 
		    
		    //input text처리
		    let searchWordTxt = document.querySelector("#searchWord");
		    searchWordTxt.value = "";
		    
		    //select값 설정
		    let pageSizeSelect =  document.querySelector("#pageSize");
		    pageSizeSelect.value = "10";    
		}
	});
	
	
});//--DOMContentLoaded

function pageDoRerive(url,pageNo){
    console.log("url:"+url);
    console.log("pageNo:"+pageNo);
    
    let bulletinForm = document.bulletinForm;
    bulletinForm.pageNo.value = pageNo;
    bulletinForm.action = url;
    bulletinForm.submit();
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" href="/bdm/index.jsp">Balance Diet Management</a>
		</li>
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			data-bs-toggle="dropdown" href="#" role="button"
			aria-expanded="false">MEMBER</a>
			<ul class="dropdown-menu">
				<li><a class="dropdown-item" href="/bdm/user/moveToReg.do">회원가입</a></li>			
			</ul>
			</li>
		<a class="nav-link" href="/bdm/beforeMain/moveToBulletin.do">자유게시판</a>
		</li>
		<li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">로그인</a></li>
	</ul>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">자유 게시판</h1>
				<hr />
			</div>
		</div>
		 <!-- 검색 -->
    <form action="#" method="get" id="bulletinFrm" name="bulletinFrm">
      <input type="hidden" name="pageNo" id="pageNo" />
      <input type="hidden" name="div"    id="div"  value="${paramVO.getDivs() }"/>
      <div class="row g-1 justify-content-end ">
          <label for="searchDiv" class="col-auto col-form-label">검색조건</label>
          <div class="col-auto">
              <select class="form-select pcwk_select" id="searchDiv" name="searchDiv">
                     <option value="">전체</option>
                     <option value="title">제목</option>
                     <option value="contents">내용</option>                    
	                 <c:forEach var="vo" items="${bulletinSearch }">
	                    <option value="<c:out value='${vo.divs}'/>"  <c:if test="${vo.divs == paramVO.searchDivs }">selected</c:if>  ><c:out value="${vo.div_name}"/></option>
	                 </c:forEach>
              </select>
          </div>    
          <div class="col-auto">
              <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력 하세요" value="${paramVO.searchWord}">
          </div>   
          <div class="col-auto"> 
               <select class="form-select" id="pageSize" name="pageSize">
                  <c:forEach var="vo" items="${pageSize }">
                    <option value="<c:out value='${vo.divs }' />" <c:if test="${vo.divs == paramVO.pageSize }">selected</c:if>  ><c:out value='${vo.div_name}' /></option>
                  </c:forEach>
               </select>  
          </div>    
          <!-- <div class="col-auto "> 열의 너비를 내용에 따라 자동으로 설정
            <input type="button" value="목록" class="btn btn-primary"  id="doRetrieve">
            <input type="button" value="등록" class="btn btn-primary"  id="moveToReg">
          </div>     -->          
      </div>
                           
    </form>
    <!--// 검색 ----------------------------------------------------------------->
    
<!-- 		<div class="mb-3">
			<div class="input-group">
                <input type="text" class="form-control ppl_input" id="search" name="search" placeholder="검색어를 입력하세요.">
                <button type="button" class="btn btn-primary">검색</button>
                <button type="button" class="btn btn-primary">등록</button>
            </div>
  			<label for="exampleFormControlTextarea1" class="form-label">
  			</label>
		  	<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
		</div> -->
    <!-- table -->
   <table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
            <thead>
                <tr>
                    <th class="text-center col-lg-1 col-sm-1">번호</th>
                    <th class="text-left col-lg-7 col-sm-8">제목</th>
                    <th class="text-center col-lg-2 col-sm-1">날짜</th>
                    <th class="col-lg-1">작성자</th>
                    <th class="text-end col-lg-1">조회수</th>
                    <th style="display: none;">시퀀스</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${ not empty list }">
                        <!-- 반복문 -->
                        
                        <!--// 반복문 -->
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="99" class="text-center">조회된 데이터가 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    <!--// table -------------------------------------------------------------->
    <div class="d-flex justify-content-center">
        <nav>
           ${pageHtml }
        </nav>    
    </div> 
    
	</div>
</body>
</html>