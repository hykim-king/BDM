<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<title>게시판 등록</title>
<script>
document.addEventListener("DOMContentLoaded",function(){
	console.log("DOMContentLoaded");
	

	//const moveToListBTN = document.getElementById("moveToList");
	const moveToListBTN = document.querySelector("#moveToList");    
	const doSaveBTN     = window.document.querySelector("#doSave");
	const regForm       = document.querySelector("#regFrm");
	
	//doSave event감지 및 처리
	doSaveBTN.addEventListener("click", function(e){
		console.log("doSaveBTN click");
		
		let title = document.querySelector("#title");
		console.log("title:"+title.value);
		if(eUtil.isEmpty(title.value) == true){
			alert("제목을 입력 하세요.")
			regForm.title.focus();
			return;
		}
		
		
		let regId = document.querySelector("#regId");  
		console.log("regId:"+regId.value);
		if(eUtil.isEmpty(regId.value) == true){
            alert("로그인 하세요.")
            regId.focus();
            return;
        }		
		
		let contents = document.querySelector("#contents");
		console.log("contents:"+contents.value);
		if(eUtil.isEmpty(contents.value) == true){
            alert("내용을 하세요.")
            contents.focus();
            return;
        }   		
		
		
		if(window.confirm("등록 하시겠습니까?")==false){
			return;
		}
		
		 //form생성
		 let formData = new FormData();
		 let inputFile01 = $("input[name='file1']")
		 
		 let files = inputFile01[0].files;
		 
		 console.log("files:"+JSON.stringify(files));
		 
		 
		 for(let i=0;i<files.length;i++){
			 formData.append("uploadFile", files[i]);	 
			 
		 }
		
        $.ajax({
            type: "POST",
            url:"/bdm/news/doSave.do",
            processData: false,
			contentType: false,
			enctype: 'multipart/form-data',
            asyn:"true",
            dataType:"json",
            data:{
                "title": document.querySelector("#title").value,
                "contents": document.querySelector("#contents").value,
                "id": document.querySelector("#regId").value,
                formData,
            },
            success:function(data){//통신 성공
            	//data.msgId가 1이면 : 메시지 출력,목록으로 이동
            	//data.msgId가 1이 아니면 : 메시지 출력
                console.log("data.msgId:"+data.msgId);
                console.log("data.msgContents:"+data.msgContents);
                
                if('1'==data.msgId){
                	alert(data.msgContents);//메시지 출력
                	moveToListFun();
                }else{
                	alert(data.msgContents);
                }
                
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });		
		
	});
	
	//event감지 및 처리
	moveToListBTN.addEventListener("click",function(e){
		console.log("moveToListBTN click");
		window.location.href = "/bdm/news/doRetrieve.do";
		
	});
});//--DOMContentLoaded

</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
    </nav>
    <div class="container mt-4">
        <h1 class="mb-4">꼬르륵 뉴스 등록하기</h1>
    <div class="container">
        <!-- 제목 -->
        
        <!--// 제목 ----------------------------------------------------------------->

        <!-- 버튼 -->
        <div class="row justify-content-end mb-3">
            <div class="col-auto">
                <button type="button" class="btn btn-primary" id="moveToList">목록</button>
                <button type="button" class="btn btn-primary" id="doSave">등록</button>
            </div>
        </div>
        <!--// 버튼 ----------------------------------------------------------------->

        <!-- form -->
        <form action="#" name="regFrm" id="regFrm">
            <input type="hidden" name="div" id="div" value="10">

            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="제목을 입력하세요">
            </div>
            <div class="mb-3">
                <label for="regId" class="form-label">등록자</label>
                <input type="text" class="form-control" id="regId" name="regId" value="${sessionScope.user.id}" readonly>
            </div>
            <div class="mb-3">
                <label for="contents" class="form-label">내용</label>
                <textarea rows="7" class="form-control" id="contents" name="contents"></textarea>
            </div>

            <!-- 이미지 첨부 버튼 및 파일 업로드 폼 -->
           
            <div class="form-group">
              <label for="file1">파일1</label>
              <input type="file" name="file1" id="file1" placeholder="파일을 선택 하세요."  multiple/>
           </div>
        </form>
        <!--// form --------------------------------------------------------------->
    </div>
</body>
</html>
