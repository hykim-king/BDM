<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded",function() {
	console.log("DOMContentLoaded");

	const moveToRegBTN = document.querySelector("#moveToReg");
	const doRetrieveBTN = document.querySelector("#doRetrieve");
	const searchDivSelect = document.querySelector("#searchDiv");
	const newsForm = document.querySelector("#newsFrm");
	const searchWordTxt = document.querySelector("#searchWord");
	const Select = document.querySelector("#searchDiv");
	const searchButton = document.querySelector("#searchButton");
	const rows = document.querySelectorAll("#newsTable>tbody>tr");


	rows.forEach(function(row) {
		row.addEventListener('click', function(e) {			
			let cells = row.getElementsByTagName("td");
				const postNo = cells[5].innerText;
				console.log('postNo:'+ postNo);
			

			//javascript
			if (confirm('상세 조회 하시겠어요?') == false) return;
			window.location.href = "${CP}/news/doSelectOne.do?postNo="+postNo;
		});
	});

	moveToRegBTN.addEventListener("click", function(e) {
		console.log("moveToRegBTN click");
		newsForm.pageNo.value = "1";/*null 처리 필수*/
		//newsForm.postNo.value = document.querySelector("#postNo").value;
		newsForm.action = "/bdm/news/moveToReg.do";
		newsForm.submit();

	});


	searchWordTxt.addEventListener("keyup", function(e) {
		console.log("keyup:" + e.keyCode);
		if (13 == e.keyCode) {//
			doRetrieve(1);
		}
	});

	//form submit방지
	newsForm.addEventListener("submit", function(e) {
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

		let newsForm = document.newsFrm;
		//newsForm.postNo.value = document.querySelector("#postNo").value;
		newsForm.pageNo.value = pageNo;
		newsForm.action = "/bdm/news/doRetrieve.do";
		console.log("doRetrieve pageNO:" + newsForm.pageNo.value);
		newsForm.submit();
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

		let newsForm = document.newsFrm;
		newsForm.pageNo.value = pageNo;
		newsForm.action = url;
		newsForm.submit();
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
                    
                    <li class="nav-item">
                        <a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do">메인으로</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/bdm/beforeMain/moveToMain.do" tabindex="-1" aria-disabled="true">로그인</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
		
      <div class="container mt-4">
        <h1 class="mb-4">꼬르륵 뉴스</h1>

        <!-- 검색 폼 -->
        <form action="#" method="get" id="newsFrm" name="newsFrm">
            <input type="hidden" name="pageNo" id="pageNo" />
            <div class="row">
                <div class="col-lg-4">
                    <select class="form-select" id="searchDiv" name="searchDiv">
                        <c:forEach var="vo" items="${newsSearch}">
                            <option value="<c:out value='${vo.getDivs()}'/>"
                                <c:if test="${vo.getDivs() == paramVO.searchDiv}">selected</c:if>>
                                <c:out value="${vo.divName}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력하세요" value="${paramVO.searchWord}">
                </div>
                <div class="col-lg-2">
                    <button type="button" class="btn btn-primary" id="doRetrieve">검색</button>
                </div>
                <div class="col-lg-2">
                    <button type="button" class="btn btn-primary" id="moveToReg">글쓰기</button>
                </div>
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
			id="newsTable">
			<thead>
				<tr>
					<th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">날짜</th>
                    <th scope="col">작성자</th>
                    <th scope="col">조회수</th>
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
								<td class="text-end    col-lg-1 "><c:out value="${vo.uuid}" /></td>
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