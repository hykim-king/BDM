<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>

<head> 
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<title>news</title>
<style>
   .readonly-input {
    background-color: #e9ecef ;
   }
   
    .thumbnail {
        width: 500px; /* 원하는 가로 크기로 조정 */
        height: 500px; /* 원하는 세로 크기로 조정 */
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
<!-- 	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	        <div class="container-fluid">
	            <a class="navbar-brand" href="/bdm/index.jsp">Balance Diet Management</a>
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
	    </nav> -->
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">${title}</h1>
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
   
        <div class="mb-3 row" style="display: none;">  
            <label for="postNo" class="col-sm-2 col-form-label" >순번</label> 
            <div class="col-sm-10"> 
                <input type="text" class="form-control readonly-input" id="postNo" name="postNo" maxlength="100" 
                 value="${vo.postNo}" 
                 readonly> 
            </div> 
        </div> 

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
                 value=${vo.id }
                 >
            </div>         
        </div>
        <div class="mb-3 row">
            <label for="regDt" class="col-sm-2 col-form-label">등록일</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regDt" name="regDt" 
                value="${vo.regDt}"  readonly="readonly" >
            </div>        
        </div>        
         
        <div class="mb-3"> <!--  아래쪽으로  여백 -->
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" name="title" maxlength="100" 
             value=${vo.title}
            placeholder="제목을 입력 하세요">
        </div>      
        <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea rows="7" class="form-control"  id="contents" name="contents">${vo.contents }</textarea>
        </div>
        <c:forEach var="file" items="${fileList}">
	    <img src="<spring:url value='/resources/upload/${file.saveFileName}'/>" class="thumbnail">
		</c:forEach>
    </form>    
    </div>

</body>
</html>