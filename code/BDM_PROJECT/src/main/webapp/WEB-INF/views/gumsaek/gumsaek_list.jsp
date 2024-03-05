<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${CP}/resources/css/main_style.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<title>Balance Diet Management</title>
<style>
.searchButton{
	margin-bottom:10px;
}
</style>
<script>
document.addEventListener("DOMContentLoaded",function() {
    console.log("DOMContentLoaded");

    const rowsBulletin = document.querySelectorAll("#bulletinTable>tbody>tr");
    const rowsNotice = document.querySelectorAll("#noticeTable>tbody>tr");
    const rowsNews = document.querySelectorAll("#newsTable>tbody>tr");
    const plusBulletinBtn = document.querySelector("#plusBulletin");
    const plusNoticeBtn = document.querySelector("#plusNotice");
    const plusNewsBtn = document.querySelector("#plusNews");
    const searchWord = '${paramVO.searchWord}';
    
    plusBulletinBtn.addEventListener("click", function(e){
    	window.location.href = "${CP}/bulletin/doRetrieve.do?searchDiv=10&searchWord="+searchWord;
    });
    plusNoticeBtn.addEventListener("click", function(e){
        window.location.href = "${CP}/notice/doRetrieve.do?searchDiv=10&searchWord="+searchWord;
    });
    plusNewsBtn.addEventListener("click", function(e){
        window.location.href = "${CP}/news/doRetrieve.do?searchDiv=10&searchWord="+searchWord;
    });

    rowsBulletin.forEach(function(row) {
        row.addEventListener('click', function(e) {         
            let cells = row.getElementsByTagName("td");
                const postNo = cells[5].innerText;
                console.log('postNo:'+ postNo);
            

            //javascript
            if (confirm('상세 조회 하시겠어요?') == false) return;
            window.location.href = "${CP}/bulletin/doSelectOne.do?postNo="+postNo;
        });
    });
    rowsNotice.forEach(function(row) {
        row.addEventListener('click', function(e) {         
            let cells = row.getElementsByTagName("td");
                const postNo = cells[5].innerText;
                console.log('postNo:'+ postNo);
            

            //javascript
            if (confirm('상세 조회 하시겠어요?') == false) return;
            window.location.href = "${CP}/notice/doSelectOne.do?postNo="+postNo;
        });
    });
    rowsNews.forEach(function(row) {
        row.addEventListener('click', function(e) {         
            let cells = row.getElementsByTagName("td");
                const postNo = cells[5].innerText;
                console.log('postNo:'+ postNo);
            

            //javascript
            if (confirm('상세 조회 하시겠어요?') == false) return;
            window.location.href = "${CP}/news/doSelectOne.do?postNo="+postNo;
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
        <input type="button" value = "더보기" id = "plusBulletin" style="display: inline;" class="btn btn-primary searchButton">
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
        <input type="button" value = "더보기" id = "plusNotice" style="display: inline;" class="btn btn-primary searchButton">
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
        <input type="button" value = "더보기" id = "plusNews" style="display: inline;" class="btn btn-primary searchButton">
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