<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>회원 가입</title>
<script>
$(document).ready(function(){
	console.log("ready");
	
	$(".numOnly").on("keyup", function(e){
		console.log("numOnly: " + $(this).val());
		
		let replaceNum = $(this).val().replace(/[^0-9]/g, "");
		
		$(this).val(replaceNum);
	});
	
});

	function doSave() {
		console.log("doSave()");
		
		// 성별 값 확인
		var genderValue = $("input[name='flexRadioDefault']:checked").val();
		// 'on'인 경우 1로, 'off'인 경우 2로 설정
		genderValue = (genderValue === 'on') ? 1 : 2;
		// 여자 선택 시 값 2로 변경
		if ($("#flexRadioDefault2").is(":checked")) {
			genderValue = 2;
		}

		
		console.log("javascript userId: " + document.querySelector("#userId").value);
		console.log("javascript ppl_input:"+document.querySelector(".ppl_input").value);
		console.log("javascript password: " + document.querySelector("#password").value);
		console.log("javascript email: " + document.querySelector("#email").value);
		console.log("javascript name: " + document.querySelector("#name").value);
		console.log("javascript birth: " + document.querySelector("#birth").value);
		console.log("javascript gender: " + genderValue);
		console.log("javascript height: " + document.querySelector("#height").value);
		console.log("javascript weight: " + document.querySelector("#weight").value);

		$.ajax({
			type : "POST",
			url : "/bdm/user/doSave.do",
			asyn : "true",
			dataType : "json",
			data : {
				id : document.querySelector("#userId").value,
				pw : document.querySelector("#password").value,
				email : document.querySelector("#email").value,
				name : document.querySelector("#name").value,
				birth : document.querySelector("#birth").value,
				gender : document.querySelector("#gender").value,
				height : document.querySelector("#height").value,
				weight : document.querySelector("#weight").value
			},
			success : function(data) {
				console.log("success data: " + data);
				
			},
			error : function(data) {
				console.log("error: " + data);
			},
			complete : function(data) {
				console.log("complete: " + data);
			}
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" href="/bdm/index.jsp">Balance Diet Management</a>
		</li>
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			data-bs-toggle="dropdown" href="#" role="button"
			aria-expanded="false">MEMBER</a>
			<ul class="dropdown-menu">
				<li><a class="dropdown-item" href="/bdm/user/moveToReg.do">회원
						가입</a></li>
				<li><a class="dropdown-item" href="#">새로운 탭</a></li>
				<li><a class="dropdown-item" href="#">새로운 탭</a></li>
				<li><hr class="dropdown-divider"></li>
				<li><a class="dropdown-item" href="#">새로운 탭</a></li>
			</ul></li>
		<li class="nav-item"><a class="nav-link" href="#">새로운 네비게이션 탭</a>
		</li>
		<li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">로그인</a></li>
	</ul>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">회원 가입</h1>
				<hr />
			</div>
		</div>
		<div class="row justify-content-end">
			<div class="col-auto">
				<button type="button" class="btn btn-primary" onclick="doSave()">등록</button>

				<hr />
			</div>
		</div>
		<input type="hidden" name="idCheck" id="idCheck" value="0">
		<table class="table">
			<tr>
				<th scope="row"><label for="userId" class="form-label">아이디</label></th>
				<td><input type="text" class="form-control ppl_input"
					id="userId" name="userId" placeholder="아이디를 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="password" class="form-label">비밀번호</label></th>
				<td><input type="password" class="form-control" id="password"
					name="password" placeholder="비밀번호를 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="email" class="form-label">이메일</label></th>
				<td><input type="text" class="form-control" id="email"
					name="email"></td>
			</tr>
			<tr>
				<th scope="row"><label for="name" class="form-label">이름</label></th>
				<td><input type="text" class="form-control" id="name"
					name="name" placeholder="이름을 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="birth" class="form-label">생년월일</label></th>
				<td><input type="text" class="form-control" id="birth"
					name="birth"></td>
			</tr>
			<tr>
				<th scope="row"><label for="gender" class="form-label"
					id="gender" name="gender">성별</label></th>
				<td><input class="form-check-input" type="radio"
					name="flexRadioDefault" id="flexRadioDefault1"> <label
					class="form-check-label" for="flexRadioDefault1"> 남자 </label> <input
					class="form-check-input" type="radio" name="flexRadioDefault"
					id="flexRadioDefault2" checked> <label
					class="form-check-label" for="flexRadioDefault2"> 여자 </label></td>
			</tr>
			<tr>
				<th scope="row"><label for="height" class="form-label">키</label></th>
				<td><input type="text" class="form-control" id="height"
					name="height" placeholder="키를 입력하세요."></td>
			</tr>
			<tr>
				<th scope="row"><label for="weight" class="form-label">몸무게</label></th>
				<td><input type="text" class="form-control" id="weight"
					name="weight" placeholder="몸무게를 입력하세요."></td>
			</tr>
		</table>
	</div>
</body>
</html>