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
        <div class="input-group mb-3">
            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">검색조건</button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">전체</a></li>
                <c:forEach var="vo" items="${noticeSearch}">
                    <li><a class="dropdown-item" href="#"><c:out value="${vo.detName}"></c:out></a></li>
                </c:forEach>
            </ul>
            <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어 입력" value="${paramVO.searchWord}">
            <select class="form-select" id="pageSize" name="pageSize">
                <c:forEach var="vo" items="${pageSize}">
                    <option value="<c:out value='${vo.detCode }' />" <c:if test="${vo.detCode == paramVO.pageSize }">selected</c:if>><c:out value='${vo.detName}' /></option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">검색</button>
        <input type="button" value="등록" class="btn btn-primary"  id="moveToReg">
    </div>
</form>
    <!-- //검색 -->

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>