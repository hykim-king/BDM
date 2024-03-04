<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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


<style>
	.card-title {
    color: black; /* 뉴스 제목 글씨색을 검은색으로 변경 */
}
    
</style>
<title>BDM</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	
	console.log( "main!" );
	
	$("#bulletinList").click(function(event) {
		window.location.href = "${CP}/bulletin/doRetrieve.do";
    });
	
	$("#noticeList").click(function(event) {
        window.location.href = "${CP}/notice/doRetrieve.do";
    });
	
	$("#qaList").click(function(event) {
        window.location.href = "${CP}/qa/doRetrieve.do";
    });
	
	$("#login").click(function(event) {
        event.preventDefault();
        window.location.href = "${CP}/beforeMain/moveToLogin.do";
    });
     
     $("#findId").on("click",function(e){
     	var width = 750;
         var height = 1200;
         var left = (window.innerWidth - width) / 2;
         var top = (window.innerHeight - height) / 2;
         myWindow = window.open('../beforeMain/moveToFindId.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
     }); // --#moveToMain
     
     $("#findPassword").on("click",function(e){
     	var width = 750;
         var height = 1200;
         var left = (window.innerWidth - width) / 2;
         var top = (window.innerHeight - height) / 2;
         myWindow = window.open('../beforeMain/moveToFindPassword.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
     }); // --#moveToMain
     
     $("#moveToReg").on("click",function(e){
     	location.href = "/bdm/user/moveToReg.do";
     }); // --#moveToMain     
 });//--document ready
</script>
</head>
<body>
    <div class="wrap">
		<div class="row">
            <div class="col">
                <img src="${CP }/resources/images/main.jpg" class="img-fluid" alt="Main Image" style="width: 100%;">
            </div>
        </div>
		<div class="container login-container">
            <div class="row">
                <div class="col-md-7 d-flex flex-column" style="height: 100%;"> <!-- .col-md-7의 높이를 100%로 설정 -->
                    <div class="slider-wrap">
                        <div class="cont slick_01"></div>
                        <div class="cont slick_02"></div>
                        <div class="cont slick_03"></div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="card">
                        <div class="card-body d-flex flex-column justify-content-end">
                            <h3 class="text-center mb-4">로그인</h3>
                            <form style="width: 100%;">
                                <button type="submit" id = "login" class="btn btn-primary btn-block py-4" style="width: 100%;"><span>꼬르륵 </span>로그인</button>
                            </form>
                            <div class="text-center mt-3" style="width: 100%;">
                                <a href="" id = "findId">아이디 찾기</a> | <a href="#" id = "findPassword">비밀번호 찾기</a> | <a href="#" id = "moveToReg">회원 가입</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<div class="container main_news">
    <div class="row">
        <div class="col-md-9">
            <h3>실시간 뉴스</h3>
            <div class="row">
                <c:choose>
                    <c:when test="${ not empty newsList }">
                        <c:forEach var="vo" items="${newsList.subList(0, (newsList.size() < 4 ? newsList.size() : 4))}" varStatus="status">
                            <div class="col-md-6 news_tab">
                                <div class="card custom-card" >
                                    <div class="card-body">
                                        <a href="${CP}/news/doSelectOne.do?postNo=${vo.postNo}" class="card-link">
                                            <img src="<c:url value='/resources/upload/${vo.fileList[0].saveFileName}'/>" class="card-img-top img-fluid rounded-start" alt="이미지" style="max-height: 200px; max-width: 100%; height: auto;">
                                            <div class="card-body">
                                                <h5 class="card-title">${vo.title}</h5>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col text-center">
                            조회된 데이터가 없습니다.
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

                <div class="col-md-3 pop_search">    
                    <div class="row">
                        <h3>실시간 인기 검색어</h3>
                        <div class="card">
                            <div class="card-body">
                            	<c:choose>
						            <c:when test="${ not empty wordList }">  
						                 <!-- 반복문 -->
						                 <c:forEach var="vo" items="${wordList.subList(0, (wordList.size() < 5 ? wordList.size() : 5))}" varStatus="status">
						                     <table>
						                     <tr>
						                         <td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
						                         <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.searchWord}" escapeXml="true" /></td>
						                     </tr>
						                     </table>
						                 </c:forEach> 
		             				</c:when>
			             			<c:otherwise>
						                 <tr>
						                     <td colspan="99" class="text-center">인기검색어가 없습니다.</td>
						                 </tr>
			             			</c:otherwise>
		         				</c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="row week_pop">
                        <h3>주간 인기 검색어</h3>
                        <div class="card">
                            <div class="card-body">
                                <c:choose>
                                    <c:when test="${ not empty weeklyWordList }">  
                                         <!-- 반복문 -->
                                         <c:forEach var="vo" items="${weeklyWordList.subList(0, (weeklyWordList.size() < 5 ? weeklyWordList.size() : 5))}" varStatus="status">
                                             <table>
                                             <tr>
                                                 <td class="text-center col-lg-1  col-sm-1"><c:out value="${status.index+1}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.searchWord}" escapeXml="true" /></td>
                                             </tr>
                                             </table>
                                         </c:forEach> 
                                    </c:when>
                                    <c:otherwise>
                                         <tr>
                                             <td colspan="99" class="text-center">인기검색어가 없습니다.</td>
                                         </tr>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      
		<div class="container board_main">
            <div class="row">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="bulli-tab" type="button" role="tab" 
                        aria-controls="bulli" aria-selected="true">자유게시판</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="notice-tab" type="button" role="tab" 
                        aria-controls="notice" aria-selected="false">공지사항</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="QandA-tab" type="button" role="tab" 
                        aria-controls="QandA" aria-selected="false">Q&A</button>
                    </li>
                </ul>
            </div>
            <div class="row">    
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="bulli" role="tabpanel" aria-labelledby="bulli-tab">
                    <input type="button" value="더보기" class="btn btn-primary" id="bulletinList">
                        <table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
                            <thead>
                                <tr>
                                    <th class="text-left col-lg-7 col-sm-8">제목 </th>
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
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.title}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.regDt}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.id}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.readCnt}" escapeXml="true" /></td>
                                                 <td style=" display: none;"><c:out value="${vo.postNo}" /></td>
                                             </tr>
                                         </c:forEach> 
                                    </c:when>
                                    <c:otherwise>
                                         <tr>
                                             <td colspan="99" class="text-center">등록된 글이 없습니다.</td>
                                         </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                     </div>
                    <div class="tab-pane fade" id="notice" role="tabpanel" aria-labelledby="notice-tab">
                    <input type="button" value="더보기" class="btn btn-primary" id="noticeList">
                        <table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
                            <thead>
                                <tr>
                                    <th class="text-left col-lg-7 col-sm-8">제목 </th>
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
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.title}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.regDt}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.id}" escapeXml="true" /></td>
                                                 <td class="text-left   col-lg-7  col-sm-8"><c:out value="${vo.readCnt}" escapeXml="true" /></td>
                                                 <td style=" display: none;"><c:out value="${vo.postNo}" /></td>
                                             </tr>
                                         </c:forEach> 
                                    </c:when>
                                    <c:otherwise>
                                         <tr>
                                             <td colspan="99" class="text-center">등록된 글이 없습니다.</td>
                                         </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="QandA" role="tabpanel" aria-labelledby="QandA-tab">
                    <input type="button" value="더보기" class="btn btn-primary" id="qaList">
                        <table class="table table-bordered border-primary table-hover table-striped" id="bulletinTable">
                            <thead>
                                <tr>
                                    <th class="text-left col-lg-7 col-sm-8">제목 </th>
                                    <th class="text-center col-lg-2 col-sm-1">날짜</th>
                                    <th class="col-lg-1">작성자</th>
                                    <th scope="col" class="text-center   "style="display: none;">SEQ</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${ not empty qaList }">  
                                         <c:forEach var="vo" items="${qaList.subList(0, (qaList.size() < 5 ? qaList.size() : 5))}" varStatus="status">
                                             <tr>
                                                 <td class="text-left col-lg-7 col-sm-8">
                                                    <c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                                                        <a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                                                            <c:out value="${vo.title}" escapeXml="true" />
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                                                        <c:out value="비공개글입니다." />
                                                    </c:if>
                                                </td>
                                                <td class="text-center col-lg-2  col-sm-1">
                                                    <c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                                                        <a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                                                            <c:out value="${vo.regDt}" escapeXml="true" />
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                                                        <c:out value="" />
                                                    </c:if>
                                                </td>
                                                <td class="col-lg-1">
                                                    <c:if test="${vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1'}">
                                                        <a href="/bdm/qa/qaView.do?postNo=${vo.postNo}">
                                                            <c:out value="${vo.id}" />
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${not (vo.disclosure eq '0' or vo.id eq user.id or user.userFilter eq '1')}">
                                                        <c:out value="비공개"/>
                                                    </c:if>
                                                </td>
                                                <td style=" display: none;"><c:out value="${vo.postNo}" /></td>
                                            </tr>
                                        </c:forEach> 
                                    </c:when>
                                    <c:otherwise>
                                         <tr>
                                             <td colspan="99" class="text-center">등록된 글이 없습니다.</td>
                                         </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>                    
                    </div>
                </div>
            </div>
        </div>     
	</div>
</body>
<script>
    // 버튼 클릭 이벤트 핸들러
    document.getElementById('navbar-toggler').addEventListener('click', function() {
        var layerBox = document.querySelector('.layer_box');
        // 팝업이 열려있는지 확인
        var isOpen = layerBox.getAttribute('aria-hidden') === 'false';
        // 팝업 상태를 토글
        isOpen ? closePopup() : openPopup();
    });

    // 팝업 열기 함수
    function openPopup() {
        var layerBox = document.querySelector('.layer_box');
        layerBox.style.display = 'block';
        layerBox.setAttribute('aria-hidden', 'false');
    }

    // 팝업 닫기 함수
    function closePopup() {
        var layerBox = document.querySelector('.layer_box');
        layerBox.style.display = 'none';
        layerBox.setAttribute('aria-hidden', 'true');
    }
</script>
<script>
    $(document).ready(function(){
            $('.slider-wrap').slick({
                dots: true,
                infinite: true,
                speed: 300,
                slidesToShow: 1,
                adaptiveHeight: true,
                arrows : false,
                dots : false,
                autoplay : true,
                responsive: [ // 반응형 웹 구현 옵션
                    {  
                    breakpoint: 960, //화면 사이즈 960px
                    settings: {
                        slidesToShow: 1
                    } 
                    },
                    { 
                    breakpoint: 768, //화면 사이즈 768px
                    settings: {    
                        slidesToShow: 1
                    } 
                    }
                ]
            });
        });
</script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const triggerTabList = document.querySelectorAll('#myTab button');
        const tabPaneList = document.querySelectorAll('.tab-pane');

        triggerTabList.forEach(triggerEl => {
            triggerEl.addEventListener('click', event => {
                event.preventDefault();
                const tabPaneId = event.target.getAttribute('aria-controls');
                const tabPane = document.getElementById(tabPaneId);
                const tabTrigger = new bootstrap.Tab(triggerEl);

                triggerTabList.forEach(el => {
                    el.classList.remove('active');
                });
                tabPaneList.forEach(el => {
                    el.classList.remove('show', 'active');
                });

                triggerEl.classList.add('active');
                tabPane.classList.add('show', 'active');
                tabTrigger.show();
            });
        });
    });
</script>
</html>