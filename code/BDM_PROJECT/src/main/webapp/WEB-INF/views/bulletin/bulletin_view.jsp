<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head> 
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
<style>
   .readonly-input {
    background-color: #e9ecef ;
   }

</style>
<script>
document.addEventListener("DOMContentLoaded",function() { 
    
    //목록버튼
    const moveToListBTN = document.querySelector("#moveToList");
    
    
    const doSelectOneBTN = document.querySelector("#doSelectOne");
    
    //삭제버튼
    const doDeleteBTN   = document.querySelector("#doDelete");
    
    const regId = document.querySelector("#regId").value;
    
    const commentsDoSaveBTN = document.querySelector("#commentsDoSave");
    
    const contents = document.querySelector('#replyContents').value;
    if(eUtil.isEmpty(contents) == true){
        alert('댓글을 확인 하세요.');
        document.querySelector('#replyContents').focus();
        return;
    }
    console.log('replyContents:'+contents);
    
  	const regId    = '${sessionScope.user.id}';
    if(eUtil.isEmpty(regId) == true){
        alert('로그인 하세요.');
        return;
    }    	  
    console.log('regId:'+regId);  
    
    
    $.ajax({
        type: "POST",
        url:"/bdm/comments/doSave.do",
        asyn:"true",
        dataType:"json",
        data:{
        	"contents": contents,
            "postNo": postNo,
            "id": regId,
            "modId": regId
        },
        success:function(data){//통신 성공
            console.log("success msgId:"+data.msgId);
            console.log("success msgContents:"+data.msgContents);
            
            if("1"==data.msgId){
            	alert(data.msgContents);
            	commentsRetrieve();//댓글 조회
            	//등록 댓글 초기화
            	document.querySelector('#replyContents').value = '';
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
    
    
    commentsDoSaveBTN.addEventListener("click",function(e){
    	console.log('commentsDoSaveBTN click');


  //삭제 이벤트 감지 및 처리
    doDeleteBTN.addEventListener("click",function(e){
        console.log('doDeleteBTN click');
        
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
    		url:"/bdm/bulletin/doDelete.do",
    		asyn:"true",
    		dataType:"json",
    		data:{
    			"postNo": postNo
    		},
    		success:function(data){//통신 성공
        		console.log("success data.msgId:"+data.msgId);
        		console.log("success data.msgContents:"+data.msgContents);
                   alert(data.msgContents);
                   window.location.href = "${CP}/bulletin/doRetrieve.do";
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	}
    	});

   

    });

  //수정 이벤트 감지 및 처리
    doSelectOneBTN.addEventListener("click",function(e){
        console.log('doSelectOneBTN click');
		var id = '${sessionScope.user.id}';
        
        if( id != regId ){
        	alert('타인의 글은 수정이 불가능 합니다.');
        	return;
        } else if(confirm('수정페이지로 이동합니다') == false){
            return;
        } else {
        var postNo = document.querySelector("#postNo").value;
        doSelectOne(postNo);
        }
        
    });
    
    function doSelectOne(postNo){
    	window.location.href = "${CP}/bulletin/doSelectOne.do?postNo=" + postNo;
    }
    
    
    //목록 이벤트 감지 및 처리
    moveToListBTN.addEventListener("click",function(e){
        console.log('moveToListBTN click');
        if(confirm('목록 화면으로 이동 하시겠습니까?')==false){
            return;
        }           
        window.location.href = "${CP}/bulletin/doRetrieve.do";
    
        
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
                        <a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do">메인으로</a>
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
            <h1 class="page-header">상세조회</h1>
        </div>
    </div>
    <!--// 제목 ----------------------------------------------------------------->
    <!-- 버튼 -->
    <div class="row justify-content-end">
        <div class="col-auto">
            <input type="button" value="목록" class="btn btn-primary" id="moveToList">
            <input type="button" value="수정" class="btn btn-primary" id="doSelectOne" >
            <input type="button" value="삭제" class="btn btn-primary" id="doDelete" >
        </div>
    </div>
        <div class="mb-3 row"> <!--  아래쪽으로  여백 -->
            <label for="seq" class="col-sm-2 col-form-label">순번</label>
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
                 value="${vo.readCnt}" 
                placeholder="조회수를 입력 하세요">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="regId" class="col-sm-2 col-form-label">등록자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regId" name="regId"  readonly="readonly"
                 value="${vo.id}"
                 >
            </div>        
        </div>
        <div class="mb-3 row">
            <label for="regId" class="col-sm-2 col-form-label">등록일</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regDt" name="regDt" 
                value="${vo.regDt}"  readonly="readonly" >
            </div>        
        </div>        
        <div class="mb-3 row">
            <label for="regId" class="col-sm-2 col-form-label">수정자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="modId" name="modId" 
                value="${vo.modId}"  readonly="readonly"  >
            </div>        
        </div>
        <div class="mb-3"> <!--  아래쪽으로  여백 -->
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control readonly-input" id="title" name="title" maxlength="100" 
             value="${vo.title}"
            placeholder="제목을 입력 하세요">
        </div>      
        <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea rows="7" class="form-control readonly-input"  id="contents" name="contents">${vo.contents }</textarea>
        </div>
    <!--// form --------------------------------------------------------------->
        <!-- reply -->  
     <div id="contentsDoSaveArea">
        <!-- 버튼 -->
         <div class="dynamicComments">
	        <div class="row justify-content-end">
	            <div class="col-auto">
	                <input type="button" value="댓글수정" class="btn btn-primary commentsDoUpdate"  >
	                <input type="button" value="댓글삭제" class="btn btn-primary contentsDoDelete"  >
	            </div>
	        </div>
	        <div class="mb-3">
	            <input type="hidden" name="regNo" value="">
	            <textarea rows="3" class="form-control dyCommentsContents"   name="dyCommentsContents"></textarea>
	        </div>
        </div>        
    </div>
         <div id="contentsDoSaveArea">
    
	    <!-- 버튼 -->
	    <div class="row justify-content-end">
	        <div class="col-auto">
	            <input type="button" value="댓글등록" class="btn btn-primary" id="commentsDoSave" >
	        </div>
	    </div>
	    <!--// 버튼 ----------------------------------------------------------------->
	    <div class="mb-3">
	        <textarea rows="3" class="form-control"  id="replyContents" name="replyContents"></textarea>
	    </div>        
    </div>
    <!--// reply -------------------------------------------------------------->
    
    
      
</div>

</body>
</html>