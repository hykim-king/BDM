<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
<script>
document.addEventListener("DOMContentLoaded", function () {
	console.log("DOMContentLoaded ON");

	const moveRegBTN = document.querySelector("#moveReg");
	const doRetrieveBTN = document.querySelector("#doRetrieve");
	const searchDivSelect = document.querySelector("#searchDiv");
	const qaForm = document.querySelector("#qaFrm");
	const searchWordTxt = document.querySelector("#searchWord");
	const rows = document.querySelectorAll("#qaTable>tbody>tr");

	rows.forEach(function (row) {
		row.addEventListener('click', function(e) {
		let cells = row.getElementsByTagName("td");
		
		const userFilterValue = ${empty user ? 0 : user.userFilter};
		const postNo = cells[4].innerText;
		const disclosure = cells[5].innerText;
		console.log('postNo:'+ postNo);
		
		if(disclosure == 0 || '${user.id}' == cells[3].innerText || userFilterValue == 1){
			if(confirm('상세 조회 하시겠습니까?') == false) return;
			window.location.href = "/bdm/qa/qaView.do?postNo=" + postNo;
		} else{
			alert('공개를 희망하지 않는 질문입니다.');
		}
			
		});
	});

	moveRegBTN.addEventListener("click", function(e) {
		console.log("moveRegBTN click");
		<c:if test="${empty user}">
 	   		alert('로그인이 필요한 서비스입니다.');
 	   		return;
 	 	</c:if>
 	 	window.location.href = "/bdm/qa/moveReg.do";
	});

	searchWordTxt.addEventListener("keyup", function (e) {
		if (13 == e.keyCode) {
			doRetrieve();
		}

	});


	qaForm.addEventListener("submit", function (e) {
		console.log(e.target)
		e.preventDefault();

	});


	doRetrieveBTN.addEventListener("click", function (e) {
		console.log("doRetrieve click");
		doRetrieve(1);
	});

	function doRetrieve(pageNo) {
		console.log("doRetrieve pageNO: " + pageNo);

		let qaForm = document.qaFrm;
		qaForm.pageNo.value = pageNo;
		
		qaForm.action = "/bdm/qa/doRetrieve.do";
		console.log("doRetrieve pageNO:" + qaForm.pageNo.value);
		qaForm.submit();
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

	let qaForm = document.qaFrm;
	qaForm.pageNo.value = pageNo;
	qaForm.action = url;
	qaForm.submit();
}
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Q&A</h1>
				<hr />
			</div>
		</div>
			<form action="#" method="get" id="qaFrm" name="qaFrm">
				<input type="hidden" name="pageNo" id="pageNo" />
				<div class="row g-1 justify-content-end ">
					<label for="searchDiv" class="col-auto col-form-label">검색조건</label>
						<div class="col-auto">
							<select class="form-select test_select" id="searchDiv" name="searchDiv">
								<option value="">전체</option>
								<c:forEach var="vo" items="${qaSearch}">
									<option value="<c:out value='${vo.divs}'/>" <c:if test="${vo.divs == paramVO.searchDiv}">selected</c:if>>
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
								<c:forEach var="vo" items="${pageSize}">
									<option value="<c:out value='${vo.divs}' />" <c:if test="${vo.divs == paramVO.pageSize}">selected</c:if>>
										<c:out value='${vo.divName}' />
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-auto ">
							<!-- 열의 너비를 내용에 따라 자동으로 설정 -->
							<input type="button" value="조회" class="btn btn-primary" id="doRetrieve">
							<input type="button" value="글쓰기" class="btn btn-primary" id="moveReg">
						</div>
					</div>
				</form>
				<table class="table table-bordered border-primary table-hover table-striped" id="qaTable">
					<thead>
						<tr>
							<th class="text-center col-lg-1 col-sm-1">번호</th>
							<th class="text-left col-lg-7 col-sm-8">제목</th>
							<th class="text-center col-lg-2 col-sm-1">날짜</th>
							<th class="col-lg-1">작성자</th>
							<th scope="col" class="text-center" style="display: none;">SEQ</th>
							<th scope="col" class="text-center" style="display: none;">공개여부</th>
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
            								<c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                								<a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                    								<c:out value="${vo.title}" escapeXml="true" />
                								</a>
            								</c:if>
            								<c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                								<c:out value="비공개글입니다." />
            								</c:if>
        								</td>
										
										<td class="text-center col-lg-2  col-sm-1">
											<c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                								<a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
													<c:out value="${vo.regDt}" escapeXml="true" />
												</a>
											</c:if>
											<c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                								<c:out value="" />
            								</c:if>
										</td>
										
										<td class="col-lg-1">
											<c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                								<a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
													<c:out value="${vo.id}" />
												</a>
											</c:if>
											<c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                								<c:out value="비공개"/>
            								</c:if>
										</td>
										
										<td style="display: none;">
											<c:out value="${vo.postNo}" />
										</td>
										
										<td style="display: none;">
											${vo.disclosure}
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
					<nav>${pageHtml}</nav>
				</div>

			</div>
		</body>

		</html>