<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<head> 
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>${vo.title}</title>
<style>
    /* 기존 스타일 유지 */
    .readonly-input {
        background-color: transparent;
        border: none;
        resize: none;
        overflow: hidden;
    }
    
    .image-container {
        text-align: center;
    }
    
    #title {
        font-size: 1.5rem;
    }

    #contents {
        font-size: 1rem; /* 글자 크기를 조절 */
        overflow: hidden; /* 스크롤 제거 */
        height: 700px; /* 자동으로 늘어나도록 설정 */
    }
</style>
<script> 
document.addEventListener("DOMContentLoaded",function(){ 
    
    //목록버튼
    const moveToListBTN = document.querySelector("#moveToList");
    //삭제버튼
    const doDeleteBTN   = document.querySelector("#doDelete");
    

   

    //삭제 이벤트 감지 및 처리
    doDeleteBTN.addEventListener("click",function(e){
        console.log('doDeleteBTN click');
        
    	const regId = document.querySelector("#id").value;
        const postNo = document.querySelector("#postNo").value;
        console.log('postNo :'+postNo);
        
        if(eUtil.isEmpty(postNo) == true){
            alert('순번을 확인 하세요.');
            return;
        }

        if(window.confirm('삭제 하시겠습니까?')==false){
            return;
        }
 			var id = '${sessionScope.user.id}';
        
        if(id != regId){
        	alert('타인의 글은 삭제 불가능합니다.');
        	return;
        }


       	$.ajax({
    		type: "GET",
    		url:"/bdm/news/doDelete.do",
    		asyn:"true",
    		dataType:"json",
    		data:{
    			"postNo": postNo
    		},
    		success:function(data){//통신 성공
        		console.log("success data.msgId:"+data.msgId);
        		console.log("success data.msgContents:"+data.msgContents);
                if("1" == data.msgId){
                   alert(data.msgContents);
                   moveToList();     
                }else{
                    alert(data.msgContents);
                }
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	}
    	});

   

    });      

    //목록 이벤트 감지 및 처리
    moveToListBTN.addEventListener("click",function(e){
        console.log('moveToListBTN click');
        if(confirm('목록 화면으로 이동 하시겠습니까?')==false){
            return;
        }           
        moveToList();
    
        
    })
    
    function moveToList(){
    	let postNo = document.querySelector("#postNo").value;
    	window.location.href = "${CP}/news/doRetrieve.do?postNo="+postNo;
    }
    

});//--DOMContentLoaded
</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/bdm/index.jsp">Balance Diet Management News</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    
                    <li class="nav-item">
                        <a class="nav-link" href="/bdm/beforeMain/moveToNews.do">자유게시판</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/bdm/beforeMain/moveToMain.do" tabindex="-1" aria-disabled="true">로그인</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">${vo.title}</h1>
        </div>
    </div>    
    <!--// 제목 ----------------------------------------------------------------->
    
    <!-- 버튼 -->
    <div class="row justify-content-end">
        <div class="col-auto">
            <input type="button" value="목록" class="btn btn-primary" id="moveToList">
           
            <input type="button" value="삭제" class="btn btn-primary" id="doDelete" >
        </div>
    </div>
    <!--// 버튼 ----------------------------------------------------------------->
    <!-- 
    seq : sequence별도 조회
    div : 10(공지사항)고정
    read_cnt : 0 
    title,contents : 화면에서 전달
    reg_id,mod_id  : session에서 처리
     -->
   
       
       

        <div class="mb-3 row"> <!--  아래쪽으로  여백 -->
            <label for="readCnt" class="col-sm-2 col-form-label">조회수</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="readCnt" name="readCnt" maxlength="100"
                 value="${vo.readCnt}" >
            </div>
        </div>

         <div class="mb-3 row">
            <label for="regId" class="col-sm-2 col-form-label">등록자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="id" name="id"  readonly="readonly"
                 value=관리자
                 >
            </div>         
        
        
         
        <div class="mb-3"> <!--  아래쪽으로  여백 -->
            <label for="regDt" class="col-form-label">등록일</label>
            <input type="text" class="form-control" id="regDt" name="regDt" maxlength="100" 
             value=${vo.regDt}" readonly="readonly">
          
          </div>
        </div>      
        <c:forEach var="file" items="${fileList}">
		    <img src="<spring:url value='/resources/upload/${file.saveFileName}'/>" style="max-width: 1000px; max-height: 500px;">
		</c:forEach>
    	</form>    
        <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea rows="7" class="form-control readonly-input"  id="contents" name="contents" readonly oninput="autoResize(this)">${vo.contents }</textarea>
        </div>

    </div>
	
</body>
</html>