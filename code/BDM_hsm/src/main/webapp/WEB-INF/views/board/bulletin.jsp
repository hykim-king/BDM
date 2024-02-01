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
					console.log("DOMContentLoaded");


					const moveToRegBTN = document.querySelector("#moveToReg");
					const doRetrieveBTN = document.querySelector("#doRetrieve");
					const searchDivSelect = document.querySelector("#searchDiv");
					const bulletinForm = document.querySelector("#bulletinFrm");
					const searchWordTxt = document.querySelector("#searchWord");
					const rows = document.querySelectorAll("#bulletinTable>tbody>tr");

					rows.forEach(function (row) {
						row.addEventListener('click', function (e) {

							const cells = row.cells;
							if (cells.length > 5) {
							    const postNo = cells[5].innerText;
							    console.log('postNo:' + postNo);
							} else {
								console.error("테이블 열이 충분하지 않습니다.");
							}


							if (confirm('상세 조회 하시겠습니까?') == false)
								return;

							window.location.href = "${CP}/bulletin/doSelectOne.do?postNo=" + postNo;

						});
					});

					moveToRegBTN.addEventListener("click", function (e) {
						console.log("moveToRegBTN click");
						alert("로그인 후 이용 가능합니다.");

						bulletinForm.action = "/bdm/beforeMain/moveToMain.do";
						bulletinForm.submit();

					});

					searchWordTxt.addEventListener("keyup", function (e) {
						console.log("keyup:" + e.keyCode);
						if (13 == e.keyCode) {//
							doRetrieve();
						}

					});


					bulletinForm.addEventListener("submit", function (e) {
						console.log(e.target)
						e.preventDefault();

					});


					doRetrieveBTN.addEventListener("click", function (e) {
						console.log("doRetrieve click");
						doRetrieve();
					});

					function doRetrieve(pageNo) {
						console.log("doRetrieve pageNO: " + pageNo);

						let bulletinForm = document.bulletinFrm;
						bulletinForm.pageNo.value = pageNo;
						bulletinForm.action = "/bdm/beforeMain/moveToBulletin.do";
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
		list: ${list }
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="/bdm/index.jsp">Balance Diet
						Management</a>
				</li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
						role="button" aria-expanded="false">MEMBER</a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="/bdm/user/moveToReg.do">회원 가입</a></li>
						<li><a class="dropdown-item" href="#"></a></li>
						<li><a class="dropdown-item" href="#"></a></li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item" href="#">마이페이지</a></li>
					</ul>
				</li>
				<a class="nav-link" href="/bdm/bulletin/doRetrieve.do">자유게시판</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#" tabindex="-1" aria-disabled="true">로그인</a></li>
			</ul>
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">자유 게시판</h1>
						<hr />
					</div>
				</div>
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
							<input type="button" value="목록" class="btn btn-primary" id="doRetrieve"> <input
								type="button" value="글쓰기" class="btn btn-primary" id="moveToReg">
						</div>
					</div>
				</form>
				<table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
					<thead>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${ not empty list }">
								<!-- 반복문 -->
								<c:forEach var="vo" items="${list}" varStatus="status">
									<tr>
										<td class="text-center col-lg-1  col-sm-1">
											<c:out value="${vo.postNo}" escapeXml="true" />
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
										<td  style="display: none;"><c:out value="${vo.postNo}" /></td>
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