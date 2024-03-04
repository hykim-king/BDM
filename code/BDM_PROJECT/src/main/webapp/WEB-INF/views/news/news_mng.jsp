<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<c:set var="CP" value="${pageContext.request.contextPath}" />     

<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>
<head> 
<title>게시판 수정</title>
<style>
.black-han,
.noto-sans {
    font-weight: 400;
    font-style: normal;
}

.black-han {
    font-family: "Black Han Sans", sans-serif;
}

.noto-sans {
    font-family: "Noto Sans KR", sans-serif;
    font-optical-sizing: auto;
}
		 .form-control[readonly] {
		    background-color: #FFFFFF; /* 하얀색 배경색으로 설정 */
		}
		
        .thumbnail {
            width: 850px; /* 원하는 가로 크기로 조정 */
            height: 500px; /* 원하는 세로 크기로 조정 */
        }
	    body {
		    background-color: #f7e9e8;
		    color: #514752;
		    font-family: sans-serif;
		}
		span{
			font-weight:bold;
		}
		.container {
		   
		}
		
		.container-box {
		    border: 2px solid rgba(81, 71, 82, 0.4);
		    background-color: #FDF8EE;
			padding-bottom: 30px;
			margin-bottom: 70px;
		}
		.news_contents{
			font-size: 18px;
			line-height:26px;
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
<div class="container container-box"style="max-width: 900px;">
    <!-- 제목 -->
    <div class="col-lg-12" style="margin-top: 20px;">
    <span class="less-bold">꼬르륵 뉴스</span>
	</div>
    <!--// 제목 ----------------------------------------------------------------->
    <!-- 버튼 -->
    <div class="row justify-content-end">
        <div class="col-auto">
            <input type="button" value="목록" class="btn btn-primary" id="moveToList">
            <input type="button" value="삭제" class="btn btn-primary" id="doDelete">
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
    
    <div class="mb-3" style="text-align: center;">
	    <h2><strong>${vo.title}</strong></h2>
	</div>
	<div class="mb-3 row d-none">
    <div class="col-sm-10 d-none">
        <input type="text" class="form-control readonly-input d-none" id="id" name="id" readonly="readonly" value="${vo.id}">
	    </div>         
	</div>
    
    
    <div class="mb-3 row">
        <h5>${vo.id} 기자</h5><h6 style="color:lightgrey;">입력 ${vo.regDt}</h6> <h6 style="color:lightgrey;">조회수 ${vo.readCnt}</h6>
    </div>
    
    <c:forEach var="file" items="${fileList}">
        <img src="<spring:url value='/resources/upload/${file.saveFileName}'/>" class="thumbnail" >
    </c:forEach>
    <div class="mb-3" style="margin-top: 40px;">
    <h5 style="white-space: pre-line;" class="news_contents">${vo.contents}</h5>
    </div>
</div>
</body>
</html>