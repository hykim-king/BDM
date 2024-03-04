<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<style>
    .card-text {
        /* 텍스트가 3줄까지만 표시되고 그 이상은 숨깁니다. */
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3; /* 줄바꿈을 유지할 최대 줄 수 */
        -webkit-box-orient: vertical;
        
    }
    .card-link {
        text-decoration: none; /* 밑줄 제거 */
        width: fit-content;
    }
    .news_main{
    	position: relative;
    	top:0;
    	left:35%;
    	transform:translate(-50%,0);
    }
    .col-lg-2 {
    flex: 0 0 auto;
    width: 8%;
	}
    .card-img {
        width: 100%; /* 이미지의 너비를 100%로 지정하여 부모 요소에 맞게 크기 조정 */
        height: 200px; /* 이미지의 높이를 고정 크기로 지정 */
        object-fit: cover; /* 이미지를 카드에 맞게 크롭하여 보여줌 */
    }
    body {
    background-color: #f7e9e8;
	}
    .container{
    	background-color:#f7e9e8;
    }
    .card-box{
    	 background-color: #FDF8EE;
    }
</style>
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

<div class="container mt-5 container-box">
    <h1 class="mb-4">꼬르륵 뉴스</h1>
    <!-- 검색 폼 -->
    <form action="#" method="get" id="newsFrm" name="newsFrm">
        <input type="hidden" name="pageNo" id="pageNo" />
        <div class="row justify-content-center mb-3">
            <div class="col-lg-6">
                <div class="input-group">
                    <select class="form-select" id="searchDiv" name="searchDiv">
                        <c:forEach var="vo" items="${newsSearch}">
                            <option value="<c:out value='${vo.getDivs()}'/>"
                                    <c:if test="${vo.getDivs() == paramVO.searchDiv}">selected</c:if>>
                                <c:out value="${vo.divName}" />
                            </option>
                        </c:forEach>
                    </select>
                    <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력하세요" value="${paramVO.searchWord}">
                    <button type="button" class="btn btn-primary" id="doRetrieve">검색</button>
                </div>
            </div>
            <div class="col-lg-2">
            <c:if test="${user.userFilter eq '1'}">
        						<input type="button" value="글쓰기" class="btn btn-primary" id="moveToReg">
    						</c:if>
            </div>
        </div>
    </form>
    <!-- 뉴스 카드 -->
    <div class="row justify-content-center">
        <c:forEach var="vo" items="${list}" varStatus="status">
            <div class="col-md-8 mb-4">
                <a href="${CP}/news/doSelectOne.do?postNo=${vo.postNo}" class="card-link text-decoration-none">
                    <div class="card h-100">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="<c:url value='/resources/upload/${vo.fileList[0].saveFileName}'/>" class="img-fluid rounded-start card-img" alt="이미지">
                            </div>
                            <div class="col-md-8 card-box">
                                <div class="card-body">
                                    <h5 class="card-title">${vo.title}</h5>
                                    <p class="card-text">${vo.contents}</p>
                                    <p class="card-text"><small class="text-muted">${vo.regDt}</small></p>
                                </div>
                            </div>
                        </div>    
                    </div>
                </a>
            </div>
        </c:forEach>
        <!-- 데이터가 없는 경우를 처리하는 부분 -->
        <c:if test="${empty list}">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">조회된 데이터가 없습니다.</p>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <!--// 뉴스 카드 -->
    <div class="d-flex justify-content-center mt-4">
        <nav>${pageHtml }</nav>
    </div>
</div>
`
</body>
</html>