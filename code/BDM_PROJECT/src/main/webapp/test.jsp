<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-4">
    <div class="row row-cols-1 row-cols-md-1 g-0">
        <!-- 최대 3개의 뉴스만 보여주도록 설정 -->
        <c:forEach var="vo" items="${list}" varStatus="status" varEnd="2">
            <div class="col">
                <a href="${CP}/news/doSelectOne.do?postNo=${vo.postNo}" class="card-link">
                    <div class="card" style="width: 70%;">
                        <div class="row g-0">
                            <div class="col-md-4" style="padding: 20px;">
                                <img src="<c:url value='/resources/upload/${vo.fileList[0].saveFileName}'/>" class="img-fluid rounded-start card-img" alt="이미지" style="max-width: 200px; max-height: 100px;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h6 class="card-subtitle mb-2 text-muted">${vo.title}</h6>
                                    <p class="card-text">${vo.regDt}</p>
                                    <p class="card-text">${vo.contents}</p>
                                    <c:if test="${empty vo.fileList}">
                                        <!-- 이미지가 없는 경우에만 표시됩니다 -->
                                        <p class="card-text">이미지가 없습니다.</p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>