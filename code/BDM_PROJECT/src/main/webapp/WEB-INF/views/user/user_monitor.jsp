<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
</style>
<title>유저 관리</title>
<script src="${CP }/resources/js/jquery-3.7.1.js"></script>
<script src="${CP }/resources/js/eUtil.js"></script>  
<script>
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