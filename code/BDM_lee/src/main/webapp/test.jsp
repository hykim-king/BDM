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
        <!-- �ִ� 3���� ������ �����ֵ��� ���� -->
        <c:forEach var="vo" items="${list}" varStatus="status" varEnd="2">
            <div class="col">
                <a href="${CP}/news/doSelectOne.do?postNo=${vo.postNo}" class="card-link">
                    <div class="card" style="width: 70%;">
                        <div class="row g-0">
                            <div class="col-md-4" style="padding: 20px;">
                                <img src="<c:url value='/resources/upload/${vo.fileList[0].saveFileName}'/>" class="img-fluid rounded-start card-img" alt="�̹���" style="max-width: 200px; max-height: 100px;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h6 class="card-subtitle mb-2 text-muted">${vo.title}</h6>
                                    <p class="card-text">${vo.regDt}</p>
                                    <p class="card-text">${vo.contents}</p>
                                    <c:if test="${empty vo.fileList}">
                                        <!-- �̹����� ���� ��쿡�� ǥ�õ˴ϴ� -->
                                        <p class="card-text">�̹����� �����ϴ�.</p>
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