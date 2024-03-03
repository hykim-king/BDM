<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<meta charset="UTF-8">
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
<title>유저 관리</title>
<script>
document.addEventListener("DOMContentLoaded", function () {
    console.log("DOMContentLoaded ON");
    const doRetrieveBTN = document.querySelector("#doRetrieve");
    const searchDivSelect = document.querySelector("#searchDiv");
    const userForm = document.querySelector("#userFrm");
    const searchWordTxt = document.querySelector("#searchWord");
    
    searchWordTxt.addEventListener("keyup", function (e) {
        if (13 == e.keyCode) {
            doRetrieve();
        }

    });


    userForm.addEventListener("submit", function (e) {
        console.log(e.target)
        e.preventDefault();

    });


    doRetrieveBTN.addEventListener("click", function (e) {
        console.log("doRetrieve click");
        doRetrieve(1);
    });

    function doRetrieve(pageNo) {
        console.log("doRetrieve pageNO: " + pageNo);

        let userForm = document.userFrm;
        userForm.pageNo.value = pageNo;
        userForm.action = "/bdm/beforeMain/moveToUserMonitor.do";
        console.log("doRetrieve pageNO:" + userForm.pageNo.value);
        userForm.submit();
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
});
function pageDoRerive(url, pageNo) {
    console.log("url:" + url);
    console.log("pageNo:" + pageNo);

    let userForm = document.userFrm;
    userForm.pageNo.value = pageNo;
    userForm.action = url;
    userForm.submit();
}
</script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">유저 관리</h1>
                <hr />
            </div>
        </div>
        <form action="#" method="get" id="userFrm" name="userFrm">
            <input type="hidden" name="pageNo" id="pageNo" />
            <div class="row g-1 justify-content-end ">
                <label for="searchDiv" class="col-auto col-form-label">검색조건</label>
                <div class="col-auto">
                    <select class="form-select test_select" id="searchDiv" name="searchDiv">
                        <option value="">전체</option>
                        <c:forEach var="vo" items="${userSearch }">
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
                </div>
            </div>
        </form>
        <table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
            <thead>
                <tr>
                    <th class="text-center col-lg-1 col-sm-1">번호</th>
                    <th class="text-center col-lg-2 col-sm-2">아이디</th>
                    <th class="text-center col-lg-1 col-sm-1">이름</th>
                    <th class="text-center col-lg-1">이메일</th>
                    <th class="text-center col-lg-1">생년월일</th>
                    <th class="text-center col-lg-1">성별</th>
                    <th class="text-center col-lg-1">가입일</th>
                    <th class="text-center col-lg-1">회원구분</th>
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
                                <td class="text-left   col-lg-2 col-sm-2">
                                    <c:out value="${vo.id}" escapeXml="true" />
                                </td>
                                <td class="text-center col-lg-1  col-sm-1">
                                    <c:out value="${vo.name}" escapeXml="true" />
                                </td>
                                <td class="col-lg-1 ">
                                    <c:out value="${vo.email}" />
                                </td>
                                <td class="text-end col-lg-1 ">
                                    <c:out value="${vo.birth}" />
                                </td>
                                <td class="text-end col-lg-1 ">
                                    <c:out value="${vo.gender}" />
                                </td>
                                <td class="text-end col-lg-1 ">
                                    <c:out value="${vo.regDt}" />
                                </td>
                                <td class="text-end col-lg-1 ">
                                    <c:out value="${vo.userFilter}" />
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