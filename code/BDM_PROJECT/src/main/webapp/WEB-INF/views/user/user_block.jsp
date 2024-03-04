<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${CP }/resources/js/eUtil.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<style>
</style>
<title>개인정보 변경</title>
<script>
document.addEventListener("DOMContentLoaded", function () {
	const modForm = document.querySelector("#userModFrm");
	const doBlockBtn = document.querySelector("#doBlock");
	const cancleBtn = document.querySelector("#doCancle");
	
	doBlockBtn.addEventListener("click", function(e){
		console.log('doBlockBtn clicked');
	    var selectElement = document.querySelector('.form-select');
	    var selectedValue = selectElement.value;
	    var id = document.querySelector("#id").value;
		
		$.ajax({
            type: "POST",
            url:"${CP }/user/doBlock.do",
            asyn:"true",
            dataType:"html",
            data:{
            	id: id,
                block: selectedValue
            },
            success:function(data){//통신 성공     
               console.log("data:" + data);
               let parsedJSON = JSON.parse(data);
               if("1" === parsedJSON.msgId){
                   alert(parsedJSON.msgContents);
                   window.close();
               }else{
                   alert(parsedJSON.msgContents);
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
	
	cancleBtn.addEventListener("click", function(e){
		console.log('moveToMyPageBtn clicked');
		window.close();
	});
});
	
</script>
</head>
<body>
    <div class = "container">
       <div class ="row">
           <div class = "col-lg-12">
               <h1 class = "page-header">회원 제재하기</h1>
           </div>
       </div> 
         <div>
           <form action="#" name="userModFrm" id = "userModFrm">
               <div class="mb-3 row"> <!--  아래쪽으로  여백 -->
		            <label for="seq" class="col-sm-2 col-form-label">아이디</label>
		            <input type="text" class="form-control readonly-input" id="id" name="id" maxlength="100" value="${outVO.id }" readonly>
		       </div>
               <div class = "mb-3">
                   <label for="name" class="form-label">이름</label>
                   <input type="text"  class="form-control"  name="name" id="name" value="${outVO.name }" readonly>
               </div>
               <div class="p-div">
                   <label for="login" class="form-label">생년월일</label>
                   <input type="text"  class="form-control" name="birth" id="birth" value="${outVO.birth }" readonly>
               </div>
               <div class="p-div">
                   <label for="block" class="form-label">제재 기간</label>
                   <select class="form-select" aria-label="Default select example">
					  <option selected>제재 기간을 선택하세요.</option>
					  <option value="1">1일</option>
					  <option value="3">3일</option>
					  <option value="7">7일</option>
					  <option value="30">30일</option>
					  <option value="0">제재 취소</option>
					</select>
               </div>
           </form>
         </div>

       <div class = "row justify-content-end">
           <div class = "col-auto">
               <input type="button" class="btn btn-primary" value="취소하기" id="doCancle" >
               <input type="button" class="btn btn-primary" value="저장하기" id="doBlock">
           </div>
       </div>
         <!--// Button영역 ------------------------------------------------------>
         
     </div>
</body>
</html>