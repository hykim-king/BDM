<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
    <title>회원가입</title>
    
    <!-- 여기에 필요한 CSS 및 JavaScript 링크를 추가하세요 -->
    
	<script>
	
	
	    /* $( document ).ready(function() {
	        console.log( "ready!" );
	        
	        const idDuBtn = document.querySelector("#");
	        
	        idDuBtn.adde=("click", function(e){
	        	
	        });
	        
	        
	        
	    }); */
	    
	    function moveToList(){
	        console.log("--------------");
	        console.log("-moveToList()-");
	        console.log("--------------");
	        
	        window.location.href = "/bdm/user/doRetrieve.do";
	    }
	    function idDuplicateCheck(){
	        console.log("-idDuplicateCheck()-"); 
	        let id = document.querySelector("#id").value;
	        if(eUtil.isEmpty(id) == true){
	            alert('아이드를 입력 하시오');
	            //$("#userId").focus();//사용자 id에 포커스
	            document.querySelector("#id").focus();
	            return;
	        }
	        
	        $.ajax({
	            type: "GET",
	            url:"/bdm/user/idDuplicateCheck.do",
	            asyn:"true",
	            dataType:"json", /*return dataType: json으로 return */
	            data:{
	            	id: id
	            },
	            success:function(data){//통신 성공
	                console.log("success data:"+data);
	                //let parsedJSON = JSON.parse(data);
	                if("1" === data.msgId){
	                    alert(data.msgContents);
	                    document.querySelector("#idCheck").value = 0;
	                }else{
	                    alert(data.msgContents);
	                    document.querySelector("#idCheck").value = 1;
	                }
	                        
	            },
	            error:function(data){//실패시 처리
	                console.log("error:"+data);
	            },
	            complete:function(data){//성공/실패와 관계없이 수행!
	                console.log("complete:"+data);
	            }
	        });
	        
	        
	    }
	    
	    function doSave(){
	        console.log("--------------");
	        console.log("-doSave()-");
	        console.log("--------------");
	        
	        /* javaScript 방법 */
	        console.log("javascript id:"+document.querySelector("#id").value);
	        console.log("javascript pw:"+document.querySelector("#pw").value);
	        console.log("javascript email:"+document.querySelector("#email").value);
	        
	        console.log("javascript name:"+document.querySelector("#name").value);
	        console.log("javascript birth:"+document.querySelector("#birth").value);
	        console.log("javascript gender:"+document.querySelector("#gender").value);
	        console.log("javascript height:"+document.querySelector("#height").value);
	        console.log("javascript weight:"+document.querySelector("#weight").value);
	        
	        
	        /* jquery: $ */
	        /* document.queryS */
	        /* $("#userId").val() : :jquery id 선택자 */
	        /* $(".userId") : 클래스 선택 */
	        //console.log("jquery userId:"+$("#userId").val());
	        
	        
	        //if(isEmpty($("#userId").val()) == true){
	        if(eUtil.isEmpty(document.querySelector("#id").value) == true){
	            alert('아이디를 입력하시오');
	            //$("#userId").focus();/* 포커스 */
	            document.querySelector("#id").focus();/* 방법2 */
	            
	            return;
	        }
	        
	        
	        if(eUtil.isEmpty(document.querySelector("#pw").value) == true){
	            alert('비밀번호를 입력하시오');
	            //$("#userId").focus();/* 포커스 */
	            document.querySelector("#pw").focus();/* 방법2 */
	            
	            return;
	        }
	        if(eUtil.isEmpty(document.querySelector("#email").value) == true){
	            alert('이메일을 입력하시오');
	            //$("#userId").focus();/* 포커스 */
	            document.querySelector("#email").focus();/* 방법2 */
	            
	            return;
	        }
	        if(eUtil.isEmpty(document.querySelector("#name").value) == true){
                alert('이름을 입력하시오');
                //$("#userId").focus();/* 포커스 */
                document.querySelector("#name").focus();/* 방법2 */
                
                return;
            }
            if(eUtil.isEmpty(document.querySelector("#birth").value) == true){
                alert('생년월일을 입력하시오');
                //$("#userId").focus();/* 포커스 */
                document.querySelector("#birth").focus();/* 방법2 */
                
                return;
            }
            if(eUtil.isEmpty(document.querySelector("#gender").value) == true){
                alert('성별을 입력하시오');
                //$("#userId").focus();/* 포커스 */
                document.querySelector("#gender").focus();/* 방법2 */
                
                return;
            }
            if(eUtil.isEmpty(document.querySelector("#height").value) == true){
                alert('키를 입력하시오');
                //$("#userId").focus();/* 포커스 */
                document.querySelector("#height").focus();/* 방법2 */
                
                return;
            }
            if(eUtil.isEmpty(document.querySelector("#weight").value) == true){
                alert('체중을 입력하시오');
                //$("#userId").focus();/* 포커스 */
                document.querySelector("#weight").focus();/* 방법2 */
                
                return;
            }
	        
	        /* confirm */
	        if(confirm("등록 하시겠습니까?")==false)return;
	        /* console.log("levelIntValue:"+$("#levelIntValue").val()); */
	        
	        $.ajax({
	            type: "POST",
	            url:"/bdm/user/doSave.do",
	            asyn:"true",
	            dataType:"html",
	            data:{
	            	id:document.querySelector("#id").value,
	            	pw: document.querySelector("#pw").value,
	                email: document.querySelector("#email").value,
	                name:document.querySelector("#name").value,
	                birth: document.querySelector("#birth").value,
	                gender: document.querySelector("#gender").value,
	                height: document.querySelector("#height").value,
	                weight: document.querySelector("#weight").value
	            },
	            success:function(data){//통신 성공     
	                console.log("success data:"+data);
	               //data:{"msgId":"1","msgContents":"dd가 등록 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}
	               let parsedJSON = JSON.parse(data);
	               if("1" === parsedJSON.msgId){
	                   alert(parsedJSON.msgContents);
	                   moveToList();
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
	        
	    }
	    
	</script>
</head>
<body>
    <div class="container "><!-- d-flex align-items-center justify-content-center vh-100 -->
        <div class="row">
            <div class="col-md-12 text-center">
                <h1 class="page-header">회원등록</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 offset-md-3">
                <!-- 나머지 body 내용 -->
                <form action="#" method="userRegFrm">
                    <!-- 회원가입에 필요한 필드들을 추가하세요 -->
                    <%-- id중복체크 수행 여부 확인:0(미수행),1(수행) --%>
                    <input type="hidden" name="idCheck" id="idCheck" value="0">
                    <div class="mb-3">
                        <label for="id" class="form-label">아이디</label>
                        <input type="text" class="form-control ppl_input" name="id" id="id" placeholder="아이디를 입력하시오" size="20" maxlength="30">
                        <button class="btn btn-primary" id="idDuplicateCheck" onclick="window.idDuplicateCheck();">중복확인</button>
                    </div>
                    
                    

                    <div class="mb-3">
                        <label for="pw" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" name="pw" id="pw" placeholder="비밀번호를 입력하시오" size="20" maxlength="30">
                    </div>

                    <!-- 나머지 필드들... -->
                    <div class="mb-3">
		            <label for="email" class="form-label">이메일</label>
		            <input type="text"  class="form-control" name="email" id="email" placeholder="이메일을 입력하시오" size="20"  maxlength="320">
		            </div>
		            
		            <div class="mb-3"> <!--  아래쪽으로  여백 -->
		            <label for="name" class="form-label">이름</label>
		            <input type="text"  class="form-control"  name="name" id="name" placeholder="이름을 입력하시오" size="20"  maxlength="21">
		            </div>
		            
		            <div class="mb-3"> <!--  아래쪽으로  여백 -->
		            <label for="birth" class="form-label">생년월일</label>
		            <input type="number"  class="form-control"  name="birth" id="birth" placeholder="생년월일을 입력하시오" size="20"  maxlength="21">
		            </div>
		            
		            <div class="mb-3"> <!--  아래쪽으로  여백 -->
		            <label for="gender" class="form-label">성별</label>
		            <select class="form-select" aria-label="Default select example" name="gender" id="gender">
		            <option selected>성별을 선택하세요</option>
		            <option value="1">남</option>
		            <option value="2">여</option>
		            </select>
		            </div>
		            
		            <div class="mb-3"> <!--  아래쪽으로  여백 -->
		            <label for="height" class="form-label">키</label>
		            <input type="number"  class="form-control"  name="height" id="height" placeholder="키를 입력하시오" size="20"  maxlength="21">
		            </div>
		            
		            <div class="mb-3"> <!--  아래쪽으로  여백 -->
		            <label for="weight" class="form-label">체중</label>
		            <input type="number"  class="form-control"  name="weight" id="weight" placeholder="체중을 입력하시오" size="20"  maxlength="21">
		            </div>

                    <div class="mb-3 text-center">
                        <input type="button" value="등록" class="btn btn-primary" id="doSave" onclick="window.doSave();">
                        <input type="button" value="목록" class="btn btn-primary" id="moveToList" onclick="window.moveToList();">
                    </div>
                </form>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-md-12 text-center">
                <jsp:include page="/WEB-INF/cmn/footer.jsp"></jsp:include>
            </div>
        </div>
    </div>
</body>
</html>
