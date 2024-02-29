<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<%-- <jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
 --%><title>Balance Diet Management</title>

<style>
.bi-heart-fill {
    font-size: 15px;
    line-height: 15px;
    color: crimson;
    border: none; /* 테두리 제거 */
}
#heartButton {
    border: none; /* 테두리 제거 */
    background-color: transparent; /* 배경색을 투명으로 설정 */
    cursor: pointer; /* 마우스 커서를 포인터로 변경 */
}
</style>
<script>
document.addEventListener("DOMContentLoaded", function () {
	console.log("DOMContentLoaded ON");
	
	//getTotalCount();

	const moveToRegBTN = document.querySelector("#moveToReg");
	const doRetrieveBTN = document.querySelector("#doRetrieve");
	const searchDivSelect = document.querySelector("#searchDiv");
	const bulletinForm = document.querySelector("#bulletinFrm");
	const searchWordTxt = document.querySelector("#searchWord");
	const rows = document.querySelectorAll("#bulletinTable>tbody>tr");
	

	rows.forEach(function (row) {
		row.addEventListener('dblclick', function(e) {
		let cells = row.getElementsByTagName("td");
		const postNo = cells[5].innerText;
		console.log('postNo:'+ postNo);
					    
		if(confirm('상세 조회 하시겠습니까?') == false) return;

		window.location.href = "/bdm/bulletin/bulletinView.do?postNo=" + postNo;
		});
	});

	moveToRegBTN.addEventListener("click", function(e) {
		console.log("moveToRegBTN click");
		<c:if test="${empty user}">
 	   		alert('로그인이 필요한 서비스입니다.');
 	   		return;
 	 	</c:if>
 	 	window.location.href = "/bdm/bulletin/moveToReg.do";
	});

	searchWordTxt.addEventListener("keyup", function (e) {
		if (13 == e.keyCode) {
			doRetrieve();
		}

	});


	bulletinForm.addEventListener("submit", function (e) {
		console.log(e.target)
		e.preventDefault();

	});


	doRetrieveBTN.addEventListener("click", function (e) {
		console.log("doRetrieve click");
		doRetrieve(1);
	});

	function doRetrieve(pageNo) {
		console.log("doRetrieve pageNO: " + pageNo);

		let bulletinForm = document.bulletinFrm;
		bulletinForm.pageNo.value = pageNo;
		bulletinForm.action = "/bdm/bulletin/doRetrieve.do";
		console.log("doRetrieve pageNO:" + bulletinForm.pageNo.value);
		bulletinForm.submit();
	}


	searchDivSelect.addEventListener("change", function (e) {
		console.log("change:" + e.target.value);

		let chValue = e.target.value;
		if ("" == chValue) {

			let searchWordTxt = document.querySelector("#searchWord");
			searchWordTxt.value = "";


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
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">자유 게시판</h1>
				<hr />
			</div>
		</div>
			<form action="#" method="get" id="bulletinFrm" name="bulletinFrm">
				<input type="hidden" name="pageNo" id="pageNo" />
				<div class="row g-1 justify-content-end ">
					<label for="searchDiv" class="col-auto col-form-label">검색조건</label>
						<div class="col-auto">
							<select class="form-select test_select" id="searchDiv" name="searchDiv">
								<option value="">전체</option>
								<c:forEach var="vo" items="${bulletinSearch }">
									<option value="<c:out value='${vo.divs}'/>" <c:if test="${vo.divs == paramVO.searchDiv }">selected</c:if>>
										<c:out value="${vo.divName}" />
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-auto">
							<input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100"
								placeholder="검색어를 입력 하세요" value="${paramVO.searchWord}">
						</div>
						<div class="col-auto">
							<select class="form-select" id="pageSize" name="pageSize">
								<c:forEach var="vo" items="${pageSize }">
									<option value="<c:out value='${vo.divs }' />" <c:if test="${vo.divs == paramVO.pageSize }">selected</c:if>>
										<c:out value='${vo.divName}' />
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-auto ">
							<!-- 열의 너비를 내용에 따라 자동으로 설정 -->
							<input type="button" value="조회" class="btn btn-primary" id="doRetrieve">
							<input type="button" value="글쓰기" class="btn btn-primary" id="moveToReg">
						</div>
					</div>
				</form>
				<table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
					<thead>
						<tr>
							<th class="text-center col-lg-1 col-sm-1">번호</th>
							<th class="text-left col-lg-7 col-sm-8">제목 </th>
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
										<td class="text-center col-lg-1  col-sm-1">
											<c:out value="${status.index+1}" escapeXml="true" />
										</td>
											<td class="text-left col-lg-7 col-sm-8">
											    <c:out value="${vo.title}" escapeXml="true" />
											    <button id="heartButton">
											        <i id="heartIcon" class="bi bi-heart-fill"></i>
											        <span id="totalCount">${count}</span>
											    </button>
											</td>
										<td class="text-center col-lg-2  col-sm-1">
											<c:out value="${vo.regDt}" escapeXml="true" />
										</td>
										<td class="col-lg-1 ">
											<c:out value="${vo.id}" />
										</td>
										<td class="text-end col-lg-1 ">
											<c:out value="${vo.readCnt}" />
										</td>
										<td style="display: none;">
											<c:out value="${vo.postNo}" />
										</td>
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