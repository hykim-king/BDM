<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
<%-- document.addEventListener("DOMContentLoaded", function(){
	const logoutBTN = document.querySelector("#logout");
	const loginBTN = document.querySelector("#login");
	
	// �����κ��� ���޵� �α��� ���� ����
    const isLoggedIn = <%= session.getAttribute("user") != null %>;
    // �ʱ�ȭ �� �α��� ���¿� ���� ��ư ǥ�� ����
    updateButtonVisibility();

    loginBTN.addEventListener("click", function(e){
        alert('�α��� �������� �̵��մϴ�.');
        window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
    });

    logoutBTN.addEventListener("click", function(e){
        alert('�α׾ƿ� �Ǿ����ϴ�.');
        window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
    });
	
	loginBTN.addEventListener("click", function(e){
   	   alert('�α��� �������� �̵��մϴ�.');
   	   return;
   	 window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
    });
	
	logoutBTN.addEventListener("click", function(e){
    	$.ajax({
            type: "GET",
            url:"/bdm/beforeMain/doLogout.do",
            asyn:"true",
            dataType:"html",
            data:{
            },
            success:function(data){//��� ����     
               alert('�α׾ƿ� �Ǿ����ϴ�.');
               window.location.href = "/bdm/beforeMain/moveToBeforeMain.do";
            },
            error:function(data){//���н� ó��
                console.log("error:"+data);
            },
            complete:function(data){//����/���п� ������� ����!
                console.log("complete:"+data);
            }
        });
    });
	
	function updateButtonVisibility() {
        if (isLoggedIn) {
            logoutBTN.style.display = "block";
            loginBTN.style.display = "none";
        } else {
            logoutBTN.style.display = "none";
            loginBTN.style.display = "block";
        }
    }
}); // -- DOM end --%>
</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/bdm/index.jsp">Balance Diet Management</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<%-- <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do">��������</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/user/moveToReg.do" tabindex="-1" aria-disabled="true">ȸ������</a></li>
				<li class="nav-item"><a class="nav-link" href="#" id="doFindAccount">ID/PW ã��</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToMyPage.do" id="moveToMyPage">����������</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/bulletin/doRetrieve.do" id="moveToBulletin">�����Խ���</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/notice/doRetrieve.do" id="moveToNotice">��������</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/news/doRetrieve.do" id="moveToNews">����</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="logout">${vo.id}</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="logout">�α׾ƿ�</a></li>
				<li class="nav-item"><a class="nav-link" href="/bdm/beforeMain/moveToBeforeMain.do" id="login">�α���</a></li>
			</ul>
		</div> --%>
	</div>
</nav>

</body>
</html>