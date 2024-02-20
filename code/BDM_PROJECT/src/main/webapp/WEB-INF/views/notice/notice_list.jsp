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

	const moveToRegBTN = document.querySelector("#moveToReg");
	const doRetrieveBTN = document.querySelector("#doRetrieve");
	const searchDivSelect = document.querySelector("#searchDiv");
	const noticeForm = document.querySelector("#noticeFrm");
	const searchWordTxt = document.querySelector("#searchWord");
	const rows = document.querySelectorAll("#noticeTable>tbody>tr");

	rows.forEach(function (row) {
		row.addEventListener('dblclick', function(e) {
		let cells = row.getElementsByTagName("td");
		const postNo = cells[5].innerText;
		console.log('postNo:'+ postNo);
					    
		if(confirm('상세 조회 하시겠습니까?') == false) return;

		window.location.href = "/bdm/notice/noticeView.do?postNo=" + postNo;
		});
	});

	moveToRegBTN.addEventListener("click", function (e) {
		console.log("moveToRegBTN click");
		

	});

	searchWordTxt.addEventListener("keyup", function (e) {
		if (13 == e.keyCode) {
			doRetrieve();
		}

	});


	noticeForm.addEventListener("submit", function (e) {
		console.log(e.target)
		e.preventDefault();

	});


	doRetrieveBTN.addEventListener("click", function (e) {
		console.log("doRetrieve click");
		doRetrieve(1);
	});

	function doRetrieve(pageNo) {
		console.log("doRetrieve pageNO: " + pageNo);

		let noticeForm = document.noticeFrm;
		noticeForm.pageNo.value = pageNo;
		noticeForm.action = "/bdm/notice/doRetrieve.do";
		console.log("doRetrieve pageNO:" + noticeForm.pageNo.value);
		noticeForm.submit();
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

	let noticeForm = document.noticeFrm;
	noticeForm.pageNo.value = pageNo;
	noticeForm.action = url;
	noticeForm.submit();
}
</script>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/bdm/index.jsp">Balance Diet Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do">메인으로</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToMain.do" tabindex="-1" aria-disabled="true">로그인</a></li>
      				<li class="nav-item"><a class="nav-link" href="#" id="doFindAccount">ID/PW 찾기</a></li>
      				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToMyPage.do" id="moveToMyPage">마이페이지</a></li>
      				<li class="nav-item"><a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToBulletin">자유게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="/bdm/notice/doRetrieve.do" id="moveToNotice">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToNews.do" id="moveToNews">뉴스</a></li>
                </ul>
            </div>
        </div>
    </nav>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">공지사항</h1>
				<hr />
			</div>
		</div>
			<form action="#" method="get" id="noticeFrm" name="noticeFrm">
				<input type="hidden" name="pageNo" id="pageNo" />
				<div class="row g-1 justify-content-end ">
					<label for="searchDiv" class="col-auto col-form-label">검색조건</label>
						<div class="col-auto">
							<select class="form-select test_select" id="searchDiv" name="searchDiv">
								<option value="">전체</option>
								<c:forEach var="vo" items="${noticeSearch }">
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
							<input type="button" value="목록" class="btn btn-primary" id="doRetrieve">
							<input type="button" value="글쓰기" class="btn btn-primary" id="moveToReg">
						</div>
					</div>
				</form>
				<table class="table table-bordered border-primary table-hover table-striped" id="noticeTable">
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
										<td class="text-center col-lg-1  col-sm-1">
											<c:out value="${status.index+1}" escapeXml="true" />
										</td>
										<td class="text-left   col-lg-7  col-sm-8">
											<c:out value="${vo.title}" escapeXml="true" />
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