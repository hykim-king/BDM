<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 페이지 경로를 변수로 설정 -->
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${CP}/resources/css/main_style.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<title>Balance Diet Management</title>
<style>
body {
	background-color: #f7e9e8;
}

.searchButton{
	margin-bottom:15px;
}
</style>
<script>
        document.addEventListener("DOMContentLoaded", function () {
            // DOM이 로드된 후 실행되는 코드

            console.log("DOMContentLoaded ON");

            // 필요한 요소들을 변수로 저장
            const moveRegBTN = document.querySelector("#moveReg");
            const doRetrieveBTN = document.querySelector("#doRetrieve");
            const searchDivSelect = document.querySelector("#searchDiv");
            const qaForm = document.querySelector("#qaFrm");
            const searchWordTxt = document.querySelector("#searchWord");
            const rows = document.querySelectorAll("#qaTable>tbody>tr");

            // 각 행에 이벤트 리스너 등록
            rows.forEach(function (row) {
                row.addEventListener('click', function(e) {
                    // 행 클릭 시 동작
                    
	                <c:if test="${empty user}">
	                    alert('로그인이 필요한 서비스입니다.');
	                    return;
	                </c:if>

                    let cells = row.getElementsByTagName("td");

                    // 사용자 필터 값 설정
                    const userFilterValue = ${empty user ? 0 : user.userFilter};
                    const postNo = cells[4].innerText;
                    const disclosure = cells[5].innerText;
                    console.log('postNo:'+ postNo);

                    // 상세 조회 조건 확인 후 이동
                    if(disclosure == 0 || '${user.id}' == cells[3].innerText || userFilterValue == 1){
                        if(confirm('상세 조회 하시겠습니까?') == false) return;
                        window.location.href = "/bdm/qa/qaView.do?postNo=" + postNo;
                    } else{
                        alert('공개를 희망하지 않는 질문입니다.');
                    }
                });
            });

            // 글쓰기 버튼 클릭 시 동작
            moveRegBTN.addEventListener("click", function(e) {
                console.log("moveRegBTN click");
                <c:if test="${empty user}">
                    alert('로그인이 필요한 서비스입니다.');
                    return;
                </c:if>
                window.location.href = "/bdm/qa/moveReg.do";
            });

            // 검색어 입력란에서 Enter 키 입력 시 조회 수행
            searchWordTxt.addEventListener("keyup", function (e) {
                if (13 == e.keyCode) {
                    doRetrieve();
                }
            });

            // 조회 버튼 클릭 시 동작
            doRetrieveBTN.addEventListener("click", function (e) {
                console.log("doRetrieve click");
                doRetrieve(1);
            });

            // 조회 함수 정의
            function doRetrieve(pageNo) {
                console.log("doRetrieve pageNO: " + pageNo);

                let qaForm = document.qaFrm;
                qaForm.pageNo.value = pageNo;

                // 폼 액션 설정 후 제출
                qaForm.action = "/bdm/qa/doRetrieve.do";
                console.log("doRetrieve pageNO:" + qaForm.pageNo.value);
                qaForm.submit();
            }

            // 검색 조건 변경 시 동작
            searchDivSelect.addEventListener("change", function (e) {
                console.log("change:" + e.target.value);

                let chValue = e.target.value;
                if ("" == chValue) {
                    // 검색 조건이 선택되지 않았을 때 초기화
                    let searchWordTxt = document.querySelector("#searchWord");
                    searchWordTxt.value = "";

                    let pageSizeSelect = document.querySelector("#pageSize");
                    pageSizeSelect.value = "10";
                }
            });

        });//--DOMContentLoaded

        // 페이지 번호에 따라 조회 수행
        function pageDoRerive(url, pageNo) {
            console.log("url:" + url);
            console.log("pageNo:" + pageNo);

            let qaForm = document.qaFrm;
            qaForm.pageNo.value = pageNo;
            qaForm.action = url;
            qaForm.submit();
        }
</script>
<style>
/* 공통 배경 색상 */
.accordion-item {
	background-color: #f0e68c; /*연한 금색 */
}
</style>
</head>
<body>
        <!-- 페이지 내용 시작 -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Q&A</h1>
                <hr />

                <!-- 자주 묻는 질문 아코디언 -->
                <div class="accordion" id="accordionExample">
    				<!-- 질문 1 -->
    				<div class="accordion-item">
        				<h2 class="accordion-header" id="headingOne">
            				<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                				식단 기록을 어떻게 해야 하나요?
            				</button>
        				</h2>
        				<div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
            				<div class="accordion-body" style="background-color: #FDF8EE;">
                				<strong>답변</strong> BDM <a href="${CP}/beforeMain/moveToLogin.do">회원 가입</a> 후 마이페이지 에서 각 날짜에 섭취한 음식을 검색하고 추가한 후 저장하면 됩니다.
            				</div>
        				</div>
    				</div>
    				<!-- 질문 2 -->
    				<div class="accordion-item">
        				<h2 class="accordion-header" id="headingTwo">
            				<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                				음식 추가 버튼에 원하는 음식이 없어요.
            				</button>
        				</h2>
        				<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            				<div class="accordion-body" style="background-color: #FDF8EE;">
                				<strong>답변</strong> 곧 섭취한 음식도 직접 추가 가능한 기능을 업데이트할 예정이니 좀만 기다려 주세요 :)
            				</div>
        				</div>
    				</div>
    				<!-- 질문 3 -->
    				<div class="accordion-item">
        				<h2 class="accordion-header" id="headingThree">
            				<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                				BDM은 무슨 의미인가요?
            				</button>
        				</h2>
        				<div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            				<div class="accordion-body" style="background-color: #FDF8EE;">
                				<strong>답변</strong> BDM은 Balance Diet Management의 약자로서 현대인의 건강한 식습관을 위해 균형 잡힌 식단을 설계하고 관리하는 사이트입니다.
            				</div>
        				</div>
    				</div>
				</div>
                <!--// 아코디언 -->

                <hr />
            </div>
        </div>

        <!-- Q&A 검색 폼 -->
        <form action="#" method="get" id="qaFrm" name="qaFrm" class="searchButton">
            <input type="hidden" name="pageNo" id="pageNo" />
            <div class="row g-1 justify-content-end ">
                <label for="searchDiv" class="col-auto col-form-label">검색조건</label>
                <div class="col-auto">
                    <!-- 검색 조건 선택 -->
                    <select class="form-select test_select" id="searchDiv" name="searchDiv">
                        <option value="">전체</option>
                        <!-- 검색 조건 목록 반복 출력 -->
                        <c:forEach var="vo" items="${qaSearch}">
                            <option value="<c:out value='${vo.divs}'/>"
                                <c:if test="${vo.divs == paramVO.searchDiv}">selected</c:if>>
                                <c:out value="${vo.divName}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto">
                    <!-- 검색어 입력 -->
                    <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100"
                        placeholder="검색어를 입력 하세요" value="${paramVO.searchWord}">
                </div>
                <div class="col-auto">
                    <!-- 페이지 크기 선택 -->
                    <select class="form-select" id="pageSize" name="pageSize">
                        <!-- 페이지 크기 목록 반복 출력 -->
                        <c:forEach var="vo" items="${pageSize}">
                            <option value="<c:out value='${vo.divs}' />"
                                <c:if test="${vo.divs == paramVO.pageSize}">selected</c:if>>
                                <c:out value='${vo.divName}' />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto ">
                    <!-- 버튼 클릭 시 조회 및 글쓰기 이동 -->
                    <input type="button" value="조회" class="btn btn-primary" id="doRetrieve">
                    <input type="button" value="글쓰기" class="btn btn-primary" id="moveReg">
                </div>
            </div>
        </form>

        <!-- Q&A 목록 테이블 -->
        <table class="table table-bordered border-primary table-hover" id="qaTable">
            <thead>
                <tr>
                    <th class="text-center col-lg-1 col-sm-1" style="background-color: #514752; color: #ffffff;">번호</th>
                    <th class="text-left col-lg-7 col-sm-8" style="background-color: #514752; color: #ffffff;">제목</th>
                    <th class="text-center col-lg-2 col-sm-1" style="background-color: #514752; color: #ffffff;">날짜</th>
                    <th class="col-lg-1" style="background-color: #514752; color: #ffffff;">작성자</th>
                    <th scope="col" class="text-center" style="display: none;">SEQ</th>
                    <th scope="col" class="text-center" style="display: none;">공개여부</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${ not empty list }">
                        <!-- 목록이 있을 경우 -->
                        <!-- 반복문 -->
                        <c:forEach var="vo" items="${list}" varStatus="status">
                            <tr>
                                <!-- 번호 -->
                                <td class="text-center col-lg-1  col-sm-1" style="background-color: #FDF8EE;">
                                    <c:out value="${status.index+1}" escapeXml="true" />
                                </td>

                                <!-- 제목 -->
                                <td class="text-left col-lg-7 col-sm-8" style="background-color: #FDF8EE;">
                                    <c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                                        <!-- 공개 여부에 따라 링크 설정 -->
                                        <a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                                            <c:out value="${vo.title}" escapeXml="true" />
                                        </a>
                                    </c:if>
                                    <c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                                        <c:out value="비공개글입니다." />
                                    </c:if>
                                </td>

                                <!-- 날짜 -->
                                <td class="text-center col-lg-2  col-sm-1" style="background-color: #FDF8EE;">
                                    <c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                                        <a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                                            <c:out value="${vo.regDt}" escapeXml="true" />
                                        </a>
                                    </c:if>
                                    <c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                                        <c:out value="" />
                                    </c:if>
                                </td>

                                <!-- 작성자 -->
                                <td class="col-lg-1" style="background-color: #FDF8EE;">
                                    <c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                                        <a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                                            <c:out value="${vo.id}" />
                                        </a>
                                    </c:if>
                                    <c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                                        <c:out value="비공개"/>
                                    </c:if>
                                </td>

                                <!-- 숨겨진 SEQ -->
                                <td style="display: none;">
                                    <c:out value="${vo.postNo}" />
                                </td>

                                <!-- 숨겨진 공개여부 -->
                                <td style="display: none;">
                                    ${vo.disclosure}
                                </td>
                            </tr>
                        </c:forEach>
                        <!--// 반복문 -->
                    </c:when>
                    <c:otherwise>
                        <!-- 목록이 없을 경우 -->
                        <tr>
                            <td colspan="99" class="text-center">조회된 데이터가 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <!--// table -------------------------------------------------------------->

        <!-- 페이지 네비게이션 -->
        <div class="d-flex justify-content-center">
            <nav>${pageHtml}</nav>
        </div>

    </div>
    <!-- 페이지 내용 끝 -->
</body>

</html>
