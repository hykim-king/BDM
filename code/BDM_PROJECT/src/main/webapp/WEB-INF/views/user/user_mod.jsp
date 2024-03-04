<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.test.bdm.user.domain.UserVO" %>
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
<script src="${CP }/resources/js/eUtil.js"></script>  
<script>
document.addEventListener("DOMContentLoaded", function () {
	const modForm = document.querySelector("#userModFrm");
	const doChangeBtn = document.querySelector("#doChange");
	const moveToMyPageBtn = document.querySelector("#moveToMyPage");
	const doDeleteBtn = document.querySelector("#doDelete");
	
	doDeleteBtn.addEventListener("click", function(e){
		var width = 750;
        var height = 1200;
        var left = (window.innerWidth - width) / 2;
        var top = (window.innerHeight - height) / 2;
        myWindow = window.open('../user/moveToDelete.do', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', scrollbars=no');
	});	
	
	doChangeBtn.addEventListener("click", function(e){
		console.log('doChangeBtn clicked');
		
		id = '${user.id}';
		
		$.ajax({
            type: "POST",
            url:"${CP }/user/doUpdate.do",
            asyn:"true",
            dataType:"html",
            data:{
                height: modForm.height.value,
                weight: modForm.weight.value,
                activity: modForm.activity.value,
                id: id
            },
            success:function(data){//통신 성공     
               console.log("data:" + data);
               let parsedJSON = JSON.parse(data);
               if("1" === parsedJSON.msgId){
                   alert(parsedJSON.msgContents);
                   window.location.href = "${CP }/nutrient/doRetrieveOneDay.do";
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
	
	moveToMyPageBtn.addEventListener("click", function(e){
		console.log('moveToMyPageBtn clicked');
		window.location.href = "${CP }/nutrient/doRetrieveOneDay.do";
	});
});
	
</script>
</head>
<body>
<%
    UserVO sessionData = (UserVO) session.getAttribute("user");
	// 사용자의 성별 값 가져오기
	int genderValue = sessionData.getGender();
	
	// 성별 값을 기반으로 표시할 문자열 설정
	String genderDisplayText;
	if (genderValue == 1) {
	    genderDisplayText = "남자";
	} else if (genderValue == 2) {
	    genderDisplayText = "여자";
	} else {
	    genderDisplayText = "알 수 없음";
	}
	
	request.setAttribute("genderDisplayText", genderDisplayText);
%>
    <div class = "container">
       <div class ="row">
           <div class = "col-lg-12">
               <h1 class = "page-header">개인정보 변경</h1>
           </div>
       </div> 
         <div>
           <form action="#" name="userModFrm" id = "userModFrm">
               <div class="mb-3 row"> <!--  아래쪽으로  여백 -->
		            <label for="seq" class="col-sm-2 col-form-label">아이디</label>
		            <input type="text" class="form-control readonly-input" id="seq" name="seq" maxlength="100" value="${user.id }" readonly>
		       </div>
               <div class="p-div">
                   <label for="email" class="form-label">이메일</label>
                   <input type="text"  class="form-control" name="email" id="email" value="${user.email }" readonly>
               </div>
               <div class = "mb-3">
                   <label for="name" class="form-label">이름</label>
                   <input type="text"  class="form-control"  name="name" id="name" value="${user.name }" readonly>
               </div>
               <div class="p-div">
                   <label for="login" class="form-label">생년월일</label>
                   <input type="text"  class="form-control" name="birth" id="birth" value="${user.birth }" readonly>
               </div>
               <div class="mb-3">
				    <label for="gender" class="form-label">성별</label>
				    <input type="text" class="form-control" name="gender" id="gender" value="${genderDisplayText }" readonly>
			   </div>
               <div class="p-div">
                   <label for="email" class="form-label">키</label>
                   <input type="text"  class="form-control" name="height" id="height" placeholder="키를 입력하세요" size="20"  maxlength="320" value="${user.height }" >
               </div>
               <div class="p-div">
                   <label for="regDt" class="form-label">몸무게</label>
                   <input type="text" class="form-control"  name="weight" id="weight" placeholder="몸무게를 입력하세요" size="20"  maxlength="7" value="${user.weight }" >
               </div>
               <div class="p-div">
                   <label for="activity" class="form-label">활동지수</label>
                   <input type="text" class="form-control"  name="activity" id="activity" placeholder="활동지수를 입력하세요" size="20"  maxlength="7" value="${user.activity }" >
               </div>
           </form>
         </div>

       <div class = "row justify-content-end">
           <div class = "col-auto">
               <input type="button" class="btn btn-primary" value="돌아가기" id="moveToMyPage" >
               <input type="button" class="btn btn-primary" value="저장하기" id="doChange">
               <input type="button" class="btn btn-primary" value="탈퇴하기" id="doDelete">
           </div>
       </div>
         <!--// Button영역 ------------------------------------------------------>
         
     </div>
</body>
</html>