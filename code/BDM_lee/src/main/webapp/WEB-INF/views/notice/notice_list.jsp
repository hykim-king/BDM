<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>공지사항 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">${title}</h1>
        </div>
    </div>
    <!-- //제목 -->

    <!-- 검색 -->
    <form action="#" method="get" id="noticeFrm" name="noticeFrm">
        <input type="hidden" name="pageNo" id="pageNo"/>
        <input type="hidden" name="div"    id="div"   value="${pramVO.getDiv()}"/>
        <div class="row g-1 justify-content-end">
            <label for="searchDiv" class="col-auto col-form-label">검색조건</label>
            <div class="col-auto">
                <select class="form-select" id="searchDiv" name="searchDiv">
                    <option value="">전체</option>
                    <c:forEach var="vo" items="${noticeSearch}">
                        <option value="<c:out value='${vo.detCode}'/>"></option>
                    </c:forEach>
                </select>
            </div>
        </div>

    </form>
    <!-- //검색 -->

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>