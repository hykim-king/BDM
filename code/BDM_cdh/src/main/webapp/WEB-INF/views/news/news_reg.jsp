<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>게시판 등록</title>
<script src="${CP}/resources/js/jquery-3.7.1.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function(){
    console.log("DOMContentLoaded");

    const moveToListBTN = document.querySelector("#moveToList");    
    const doSaveBTN = document.querySelector("#doSave");
    const regForm = document.querySelector("#regFrm");
    
    doSaveBTN.addEventListener("click", function(e){
        console.log("doSaveBTN click");
        
        let title = document.querySelector("#title");
        if(eUtil.isEmpty(title.value)){
            alert("제목을 입력하세요.");
            regForm.title.focus();
            return;
        }
        
        let regId = document.querySelector("#regId");
        if(eUtil.isEmpty(regId.value)){
            alert("로그인이 필요합니다.");
            regId.focus();
            return;
        }
        
        let contents = document.querySelector("#contents");
        if(eUtil.isEmpty(contents.value)){
            alert("내용을 입력하세요.");
            contents.focus();
            return;
        }
        
        if(!confirm("등록하시겠습니까?")){
            return;
        }
        
        $.ajax({
            type: "POST",
            url: "/bdm/board/doSave.do",
            async: "true",
            dataType: "json",
            data: {
                "title": title.value,
                "contents": contents.value,
                "id": regId.value
            },
            success: function(data){
                console.log("data.msgId:" + data.msgId);
                console.log("data.msgContents:" + data.msgContents);
                
                if('1' == data.msgId){
                    alert(data.msgContents);
                    moveToListFun();
                } else {
                    alert(data.msgContents);
                }
            },
            error: function(data){
                console.log("error:" + data);
            },
            complete: function(data){
                console.log("complete:" + data);
            }
        });     
    });
    
    function moveToListFun(){
        window.location.href = "/bdm/beforeMain/moveToNews.do";
    }
    
    moveToListBTN.addEventListener("click", function(e){
        console.log("moveToListBTN click");
        moveToListFun();
    });
    
    
  
	
    
    
});
</script>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="text-center mb-4">뉴스 등록</h1>
        </div>
    </div>    
    
    <div class="row justify-content-center mb-3">
        <div class="col-lg-6">
            <form action="#" name="regFrm" id="regFrm">
                <input type="hidden" name="div" id="div" value="10">
                
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="제목을 입력하세요">
                </div>
                <div class="form-group">
                    <label for="regId">등록자</label>
                    <input type="text" class="form-control" id="regId" name="regId" value="${sessionScope.user.id}" readonly>        
                </div>
                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea class="form-control" rows="7" id="contents" name="contents"></textarea>
                </div>
                
                <div class="text-center">
                    <button type="button" class="btn btn-secondary mr-2" id="moveToList">목록</button>
                    <button type="button" class="btn btn-primary" id="doSave">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
