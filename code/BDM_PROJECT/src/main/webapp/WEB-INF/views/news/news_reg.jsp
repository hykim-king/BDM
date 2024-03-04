<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/navbar.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>
<title>게시판 등록</title>
<script>
function previewImages(event) {
    var preview = document.getElementById('imagePreview');
    preview.innerHTML = '';
    
    if (event.target.files) {
        for (var i = 0; i < event.target.files.length; i++) {
            var reader = new FileReader();
            reader.onload = function(e) {
                var img = document.createElement('img');
                img.src = e.target.result;
                img.style.maxWidth = '200px'; // 이미지 최대 너비 설정
                img.style.maxHeight = '200px'; // 이미지 최대 높이 설정
                preview.appendChild(img);
            }
            reader.readAsDataURL(event.target.files[i]);
        }
    }
}
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
      if(eUtil.isEmpty(contents.value) == true){
            alert("내용을 하세요.")
            contents.focus();
            return;
        }        
      if (!hasImageSelected()) {
          alert("이미지를 선택하세요.");
          return;
      }
      if (!confirm("등록 하시겠습니까?")) {
          return;
      }
      // 이미지가 선택되었는지 확인하는 함수
      function hasImageSelected() {
          var fileInput = document.querySelector("#file");
          return fileInput.files.length > 0;
      }
      
        $.ajax({
            type: "POST",
            url:"/bdm/news/doSave.do",
            processData: false,
            contentType: false,
            asyn:"true",
            dataType:"json",
            data:formData,
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
       
      //FormData 새로운 객체 생성
      var formData = new FormData();
      // 넘길 데이터를 담아준다.
      var data = {  
          "id"    : $("#regId").val(),
          "title"     : $("#title").val(),
          "contents"  :  $("#contents").val()
      }
      // input class 값
      var fileInput = $('.file');
      // fileInput 개수를 구한다.
      for (var i = 0; i < fileInput.length; i++) {
         if (fileInput[i].files.length > 0) {
            for (var j = 0; j < fileInput[i].files.length; j++) {
               console.log(" fileInput[i].files[j] :::"+ fileInput[i].files[j]);
               // formData에 'file'이라는 키값으로 fileInput 값을 append 시킨다. 
               formData.append('file', $('.file')[i].files[j]);
            }
         }
      }
      // 'key'라는 이름으로 위에서 담은 data를 formData에 append한다. type은 json 
      formData.append('key', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
      for (const [key, value] of formData.entries()) {
          console.log(key, value);
         };
      //console.log(formData);
      // ajax 처리 부분 *
      $.ajax({
            url: '/bdm/news/doSave.do',
            type: "POST",
            data: formData,
            contentType: false,               // * 중요 *
            processData: false,               // * 중요 *
            enctype : 'multipart/form-data',  // * 중요 *
            success: function(data) {
            	if('1'==data.msgId){
                    alert(data.msgContents);//메시지 출력
                    moveToListFun();
                 }else{
                    alert(data.msgContents);
                 }
            }
      });
   });
   //event감지 및 처리
   moveToListBTN.addEventListener("click",function(e){
      console.log("moveToListBTN click");
      moveToListFun();
   });
});//--DOMContentLoaded
function moveToListFun(){
    window.location.href = "/bdm/news/doRetrieve.do";
 }
</script>
</head>
<body>
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
			    <label for="file1">이미지 업로드</label>
			    <input type="file" id="file" name="file" class="file" placeholder="파일을 선택하세요." accept="image/*" multiple onchange="previewImages(event)"/>
			    <div id="imagePreview"></div>
			</div>
           
        </form>
        <!--// form --------------------------------------------------------------->
    </div>
</body>
</html>