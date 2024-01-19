<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>회원 가입</title>
<script>
$("#idDuplicateCheck").on("click", function(e){
	
});
function idDuplicateCheck(){
    console.log("idDuplicateCheck click");
	alert('아이디를 입력하세요.'); 
}

function doSave(){
	console.log("doSave()");
	console.log("userId: " + document.querySelector("#userId").value);
	console.log("password: " + document.querySelector("#password").value);
	console.log("eMail: " + document.querySelector("#eMail").value);
	console.log("name: " + document.querySelector("#name").value);
	console.log("birthday: " + document.querySelector("#birthday").value);
	console.log("height: " + document.querySelector("#height").value);
	console.log("weight: " + document.querySelector("#weight").value);
}

function moveToList(){
	console.log("moveToList()");
}

</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="nav-item">
			<a class="nav-link active" aria-current="page" href="/bdm/index.jsp">Balance Diet Management</a>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">MEMBER</a>
			<ul class="dropdown-menu">
				<li><a class="dropdown-item" href="/bdm/user/moveToReg.do">회원 가입</a></li>
				<li><a class="dropdown-item" href="#">새로운 탭</a></li>
      			<li><a class="dropdown-item" href="#">새로운 탭</a></li>
      			<li><hr class="dropdown-divider"></li>
      			<li><a class="dropdown-item" href="#">새로운 탭</a></li>
			</ul>
		</li>
<li class="nav-item">
<a class="nav-link" href="#">Link</a>
</li>
<li class="nav-item">
<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
</li>
</ul>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">회원 가입</h1>
				<hr/>
			</div>
		</div>
		<div class="row justify-content-end">
			<div class="col-auto">
				<input type="button" class="btn btn-primary" value="등록" id="doSave"      onclick="window.doSave();">
				<input type="button" class="btn btn-primary" value="목록" id="moveToList"  onclick="window.moveToList();">
				<hr/>
			</div>
		</div>
		<table class="table">
			<tr>
				<th scope="row"><label for="userId" class="form-label">아이디</label></th>
				<td><input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요."></td>
				<td><input type="button" class="btn btn-primary" value="중복 확인" id="idDuplicateCheck"      onclick="window.idDuplicateCheck();"></td>
			</tr>
			<tr>
				<th scope="row"><label for="password" class="form-label">비밀번호</label></th>
				<td><input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="eMail" class="form-label">이메일</label></th>
				<td><input type="text" class="form-control" id="eMail" name="eMail"></td>
			</tr>
			<tr>
				<th scope="row"><label for="name" class="form-label">이름</label></th>
				<td><input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="birthday" class="form-label">생년월일</label></th>
				<td><input type="text" class="form-control" id="birthday" name="birthday"></td>
			</tr>
			<tr>
				<th scope="row"><label for="gender" class="form-label" id="gender" name="gender">성별</label></th>
				<td><input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
  					<label class="form-check-label" for="flexRadioDefault1">
    					남자
  					</label>
  					<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
  					<label class="form-check-label" for="flexRadioDefault2">
    					여자
  					</label>
  				</td>
			</tr>
			<tr>
				<th scope="row"><label for="height" class="form-label">키</label></th>
				<td><input type="text" class="form-control" id="height" name="height" placeholder="키를 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="weight" class="form-label">몸무게</label></th>
				<td><input type="text" class="form-control" id="weight" name="weight" placeholder="몸무게를 입력하세요."></td>
			</tr>
		</table>
	</div>
</body>
</html>