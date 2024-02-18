<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
<script>
document.addEventListener("DOMContentLoaded",function() {
	console.log("DOMContentLoaded");

	const moveToRegBTN = document.querySelector("#moveToReg");
	
	
     
		
	const doRetrieveBTN = document.querySelector("#doRetrieve");
	const searchDivSelect = document.querySelector("#searchDiv");
	const bulletinForm = document.querySelector("#bulletinFrm");
	const searchWordTxt = document.querySelector("#searchWord");
	const Select = document.querySelector("#searchDiv");
	const searchButton = document.querySelector("#searchButton");
	const rows = document.querySelectorAll("#bulletinTable>tbody>tr");


	rows.forEach(function(row) {
		row.addEventListener('click', function(e) {			
			let cells = row.getElementsByTagName("td");
				const postNo = cells[5].innerText;
				console.log('postNo:'+ postNo);
			

			//javascript
			if (confirm('상세 조회 하시겠어요?') == false) return;
			window.location.href = "${CP}/bulletin/doSelectOne.do?postNo="+postNo;
		});
	});

	moveToRegBTN.addEventListener("click", function(e) {
		console.log("moveToRegBTN click");
		bulletinForm.pageNo.value = "1";/*null 처리 필수*/
		//bulletinForm.postNo.value = document.querySelector("#postNo").value;
		bulletinForm.action = "/bdm/bulletin/moveToReg.do";
		bulletinForm.submit();
		
		if(confirm('작성하시겠습니까?')==false){
	         return;
	     }
	  
	     var id = '${sessionScope.user.id}';
	     
	     if(id != id){
	     	alert('로그인해주세요.');
	     	return;
	     }

	});


	searchWordTxt.addEventListener("keyup", function(e) {
		console.log("keyup:" + e.keyCode);
		if (13 == e.keyCode) {//
			doRetrieve(1);
		}
	});

	//form submit방지
	bulletinForm.addEventListener("submit", function(e) {
		console.log(e.target)
		e.preventDefault();

	});

	//목록버튼 이벤트 감지
	doRetrieveBTN.addEventListener("click", function(e) {
		console.log("doRetrieve click");
		doRetrieve(1);
	});

	function doRetrieve(pageNo) {
		console.log("doRetrieve pageNO:" + pageNo);

		let bulletinForm = document.bulletinFrm;
		//bulletinForm.postNo.value = document.querySelector("#postNo").value;
		bulletinForm.pageNo.value = pageNo;
		bulletinForm.action = "/bdm/bulletin/doRetrieve.do";
		console.log("doRetrieve pageNO:" + bulletinForm.pageNo.value);
		bulletinForm.submit();
	}

	//검색조건 변경!:change Event처리 
	searchDivSelect.addEventListener("change", function(e) {
		console.log("change:" + e.target.value);

		let chValue = e.target.value;
		if ("" == chValue) {

			//input text처리
			let searchWordTxt = document.querySelector("#searchWord");
			searchWordTxt.value = "";

			//select값 설정
			let pageSizeSelect = document.querySelector("#pageSize");
			pageSizeSelect.value = "10";
		}
	});

});//--DOMContentLoaded

	function pageDoRerive(url, pageNo) {
		console.log("url:" + url);
		console.log("pageNo:" + pageNo);

		let bulletinForm = document.bulletinFrm;
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
			</ul></li>
		<a class="nav-link" href="/bdm/beforeMain/moveToBulletin.do">자유게시판</a>
		</li>
		<li class="nav-item"><a class="nav-link"
			href="/bdm/beforeMain/moveToMain.do" tabindex="-1"
			aria-disabled="true">로그인</a></li>
	</ul>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">자유 게시판</h1>
				<hr />
			</div>
		</div>
		<!-- 검색 -->
		<%--  
			    <div class="input-group">
			        <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력하세요" value="${paramVO.searchWord}">
			        <button type="button" class="btn btn-primary" id="searchButton">검색</button>
			    </div>
         
          <!-- <div class="col-auto "> 열의 너비를 내용에 따라 자동으로 설정
            <input type="button" value="목록" class="btn btn-primary"  id="doRetrieve">
            <input type="button" value="등록" class="btn btn-primary"  id="moveToReg">
          </div>     -->          
      </div>
                           
    </form> --%>
		<form action="#" method="get" id="bulletinFrm" name="bulletinFrm">
			<input type="hidden" name="pageNo" id="pageNo" />	
								
			<div class="row g-1 justify-content-end ">
				<label for="searchDiv" class="col-auto col-form-label">검색조건</label>
				<div class="col-auto">
					<select class="form-select test_select" id="searchDiv" name="searchDiv">						
						<c:forEach var="vo" items="${bulletinSearch }">
							<option value="<c:out value='${vo.getDivs()}'/>"
								<c:if test="${vo.getDivs() == paramVO.searchDiv }">selected</c:if>>
								<c:out value="${vo.divName}" />
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-auto">
					<input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력 하세요"
						value="${paramVO.searchWord}">						
					<button type="button" class="btn btn-primary" id="doRetrieve">검색</button>
					<input type="button" class="btn btn-primary" value="글쓰기"  id="moveToReg">

				</div>
				<%-- <div class="col-auto">
							<select class="form-select" id="pageSize" name="pageSize">
 								<c:forEach var="vo" items="${pageSize }">
 									<option value="<c:out value='${vo.getDivs()}'/>" <c:if test="${vo.divName == paramVO.searchDiv }">selected</c:if>>
										<c:out value="${vo.divName}" />
									</option>
								</c:forEach> 
							</select>
						</div> --%>
				<!-- <div class="col-auto ">
  							<input type="button" value="검색" class="btn btn-primary" id="doRetrieve"> 
							<input type="button" value="글쓰기" class="btn btn-primary" id="moveToReg">
						</div> -->
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
		<table
			class="table table-bordered border-primary table-hover table-striped"
			id="bulletinTable">
			<thead>
				<tr>
					<th class="text-center col-lg-1 col-sm-1">번호</th>
					<th class="text-left col-lg-7 col-sm-8">제목</th>
					<th class="text-center col-lg-2 col-sm-1">날짜</th>
					<th class="col-lg-1">작성자</th>
					<th class="text-end col-lg-1">조회수</th>
					<th scope="col" class="text-center   "style="display: none;">SEQ</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ not empty list }">
						<!-- 반복문 -->
						<c:forEach var="vo" items="${list}" varStatus="status">
							<tr>
								<td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
								<td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.title}" escapeXml="true" /></td>
								<td class="text-center col-lg-2  col-sm-1"><c:out value="${vo.regDt}" escapeXml="true" /></td>
								<td class="            col-lg-1 "><c:out value="${vo.id}" /></td>
								<td class="text-end    col-lg-1 "><c:out value="${vo.readCnt}" /></td>
								<td style="display: none;"><c:out value="${vo.postNo}" /></td>
							</tr>
						</c:forEach>
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
			<nav>${pageHtml }</nav>
		</div>

	</div>
</body>
</html>