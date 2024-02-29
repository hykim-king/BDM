<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head> 
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<%-- <jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
 --%><title>게시물 수정</title>
<style>
   .readonly-input {
    background-color: #e9ecef ;
   }

</style>
<script>
document.addEventListener("DOMContentLoaded",function(){ 
    
    //목록버튼
    const moveToListBTN = document.querySelector("#moveToList");
    //삭제버튼
    const doCancleBTN   = document.querySelector("#doCancle");
    //수정버튼
    const doUpdateBTN   = document.querySelector("#doUpdate");
    
    const postNo = document.querySelector("#postNo");

  //수정 이벤트 감지 및 처리
    doUpdateBTN.addEventListener("click", function(e){
  
    	const modId = document.querySelector("#modId").value;
        //const div = document.querySelector("#div").value;
        const postNo = document.querySelector("#postNo").value;
     
        if(eUtil.isEmpty(postNo) == true){
            alert('순번을 확인 하세요.');
            return;
        }
     
        const title = document.querySelector("#title").value;
        if(eUtil.isEmpty(title) == true){
            alert('제목을 입력 하세요.');
            title.focus();
            return;  
        }        
     
        const contents = document.querySelector("#contents").value;
        if(eUtil.isEmpty(contents) == true){
            alert('내용을 입력 하세요.');
            contents.focus();
            return;
       }               

        if(confirm('수정 하시겠습니까?')==false){
            return;
        }
     
        var id = '${sessionScope.user.id}';
        
        if(id != modId){
        	alert('타인의 글은 수정 불가능합니다.');
        	return;
        }
        
        
      	$.ajax({
    		type: "POST",
    		url:"/bdm/bulletin/doUpdate.do",
    		asyn:"true", 
    		dataType:"json",
    		data:{
                "postNo"  : postNo,
    			"title": title,
                "modId": modId,  
    			"contents": contents
    		},
    		success:function(data){//통신 성공
    			alert(data.msgContents);
    			window.location.href = "${CP}/bulletin/doRetrieve.do";
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});




    });
  

    //목록 이벤트 감지 및 처리
    moveToListBTN.addEventListener("click",function(e){
        console.log('moveToListBTN click');
        if(confirm('목록 화면으로 이동 하시겠습니까?')==false){
            return;
        }           
        window.location.href = "${CP}/bulletin/doRetrieve.do";
    })
    
    doCancleBTN.addEventListener("click", function(e){
    	console.log('doCancleBTN click');
    	if(confirm('작성하신 데이터는 사라지게 됩니다.') == false) return;
    	
    	let postNo = document.querySelector("#postNo").value;
    	window.location.href = "/bdm/bulletin/bulletinView.do?postNo=" + postNo;
    })

});//--DOMContentLoaded
</script>
</head>
<body>
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">게시물 수정</h1>
        </div>
    </div>    
    <!--// 제목 ----------------------------------------------------------------->
    <!-- 버튼 -->
    <div class="row justify-content-end">
        <div class="col-auto">
            <input type="button" value="목록" class="btn btn-primary" id="moveToList">
            <input type="button" value="완료" class="btn btn-primary" id="doUpdate" >
            <input type="button" value="취소" class="btn btn-primary" id="doCancle" >
        </div>
    </div>
    
    <div class="mb-3 row" style="display: none;">  
		<label for="postNo" class="col-sm-2 col-form-label" >순번</label> 
            <div class="col-sm-10"> 
                <input type="text" class="form-control readonly-input" id="postNo" name="postNo" maxlength="100" 
                 value="${vo.postNo }" 
                 readonly> 
            </div> 
        </div> 

    <!-- form -->
    <form action="#" name="regFrm" id="regFrm">
        <div class="mb-3 row" hidden> <!--  아래쪽으로  여백 -->
            <label for="readCnt" class="col-sm-2 col-form-label">조회수</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="readCnt" name="readCnt" maxlength="100"
                 value="${vo.readCnt }" 
                placeholder="조회수를 입력 하세요">
            </div>
        </div>

        <div class="mb-3 row" hidden>
            <label for="regId" class="col-sm-2 col-form-label">등록자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regId" name="regId"  readonly="readonly"
                 value=${vo.id }
                 >
            </div>        
        </div>
        <div class="mb-3 row">
            <label for="regDt" class="col-sm-2 col-form-label">등록일</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regDt" name="regDt" 
                value="${vo.regDt }"  readonly="readonly" >
            </div>        
        </div>        
        <div class="mb-3 row" hidden>
            <label for="regId" class="col-sm-2 col-form-label">수정자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="modId" name="modId" 
                value="${vo.modId }"  readonly="readonly"  >
            </div>        
        </div>
        <div class="mb-3"> <!--  아래쪽으로  여백 -->
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" name="title" maxlength="100" 
             value=${vo.title }
            placeholder="제목을 입력 하세요">
        </div>      
        <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea rows="7" class="form-control"  id="contents" name="contents">${vo.contents }</textarea>
        </div>
    </form> 
    <!--// form --------------------------------------------------------------->
    
    
      
</div>

</body>
</html>