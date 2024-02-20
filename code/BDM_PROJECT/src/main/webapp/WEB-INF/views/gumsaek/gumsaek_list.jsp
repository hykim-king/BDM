<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
<script>
document.addEventListener("DOMContentLoaded",function() {
    console.log("DOMContentLoaded");

    const rows = document.querySelectorAll("#bulletinTable>tbody>tr");
    const plusBulletinBtn = document.querySelector("#plusBulletin");
    const plusNoticeBtn = document.querySelector("#plusNotice");
    const plusNewsBtn = document.querySelector("#plusNews");
    const searchWord = '${paramVO.searchWord}';
    
    plusBulletinBtn.addEventListener("click", function(e){
    	window.location.href = "${CP}/bulletin/doRetrieve.do?searchWord="+searchWord;
    });
    plusNoticeBtn.addEventListener("click", function(e){
        window.location.href = "${CP}/notice/doRetrieve.do?searchWord="+searchWord;
    });
    plusNewsBtn.addEventListener("click", function(e){
        window.location.href = "${CP}/news/doRetrieve.do?searchWord="+searchWord;
    });

    rows.forEach(function(row) {
        row.addEventListener('click', function(e) {         
            let cells = row.getElementsByTagName("td");
                const postNo = cells[5].innerText;
                console.log('postNo:'+ postNo);
            

            //javascript
            if (confirm('상세 조회 하시겠어요?') == false) return;
            window.location.href = "${CP}/bulletin/doSelectOne.do?postNo="+postNo;
        });
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
    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link active"
            aria-current="page" href="/bdm/index.jsp">Balance Diet Management</a>
        </li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown" href="#" role="button"
            aria-expanded="false">MEMBER</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/bdm/user/moveToReg.do">회원가입</a></li>
            </ul></li>
        <a class="nav-link" href="/bdm/beforeMain/moveToBulletin.do">자유게시판</a>
        </li>
        <li class="nav-item"><a class="nav-link"
            href="/bdm/beforeMain/moveToMain.do" tabindex="-1"
            aria-disabled="true">로그인</a></li>
    </ul>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">통합 검색</h1>
                <hr />
            </div>
        </div>
        '${paramVO.searchWord }' 로 검색한 결과입니다. <br/>
        <br/>
        
        <div style="display: inline-block;">
        <h3 style="display: inline;">자유 게시판</h3>
        </div>
        <input type="button" value = "더보기" id = "plusBulletin" style="display: inline;">
        <table
            class="table table-bordered border-primary table-hover table-striped"
            id="bulletinTable">
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
                    <c:when test="${ not empty bulletinList }">
					    <!-- 반복문 -->
					    <c:forEach var="vo" items="${bulletinList.subList(0, (bulletinList.size() < 5 ? bulletinList.size() : 5))}" varStatus="status">
					        <tr>
					            <td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
					            <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.title}" escapeXml="true" /></td>
					            <td class="text-center col-lg-2  col-sm-1"><c:out value="${vo.regDt}" escapeXml="true" /></td>
					            <td class="            col-lg-1 "><c:out value="${vo.id}" /></td>
					            <td class="text-end    col-lg-1 "><c:out value="${vo.readCnt}" /></td>
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
        <div style="display: inline-block;">
        <h3 style="display: inline;">공지사항</h3>
        </div>
        <input type="button" value = "더보기" id = "plusNotice" style="display: inline;">
        <table
            class="table table-bordered border-primary table-hover table-striped"
            id="noticeTable">
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
                    <c:when test="${ not empty noticeList }">
                        <!-- 반복문 -->
                        <c:forEach var="vo" items="${noticeList.subList(0, (noticeList.size() < 5 ? noticeList.size() : 5))}" varStatus="status">
                            <tr>
                                <td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
                                <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.title}" escapeXml="true" /></td>
                                <td class="text-center col-lg-2  col-sm-1"><c:out value="${vo.regDt}" escapeXml="true" /></td>
                                <td class="            col-lg-1 "><c:out value="${vo.id}" /></td>
                                <td class="text-end    col-lg-1 "><c:out value="${vo.readCnt}" /></td>
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
        <div style="display: inline-block;">
        <h3 style="display: inline;">뉴스</h3>
        </div>
        <input type="button" value = "더보기" id = "plusNews" style="display: inline;">
        <table
            class="table table-bordered border-primary table-hover table-striped"
            id="newsTable">
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
                    <c:when test="${ not empty newsList }">
                        <!-- 반복문 -->
                        <c:forEach var="vo" items="${newsList.subList(0, (newsList.size() < 5 ? newsList.size() : 5))}" varStatus="status">
                            <tr>
                                <td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
                                <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.title}" escapeXml="true" /></td>
                                <td class="text-center col-lg-2  col-sm-1"><c:out value="${vo.regDt}" escapeXml="true" /></td>
                                <td class="            col-lg-1 "><c:out value="${vo.id}" /></td>
                                <td class="text-end    col-lg-1 "><c:out value="${vo.readCnt}" /></td>
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

    </div>
</body>
</html>