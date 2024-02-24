<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport"  content="width=device-width, initial-scale=1">
<style>
    .card-body{
        color:#f7e9e8;
    }
    .search-container {
      max-width: 1920px;
      width:80%;
      margin: 0 auto;
      padding: 20px;
      text-align: center;
    }
    .search-input {
      width: 70%;
      padding: 10px;
      border: 1px solid #fdce64; 
      border-radius: 20px 0 0 20px;
      font-size: 16px;
      outline: none;
    }
    .search-button {
      width: 50px;
      padding: 10px;
      background-color: #fdce64;
      border: none;
      border-radius: 0 20px 20px 0;
      cursor: pointer;
      font-size: 16px;
    }
    .search-input:focus {
      border-color: #007bff;
    }
    .search-button img{
        width: 20px;
          height: 20px;
    }
    .link_main img{
        width:50px;
        height: auto;
    }
    
</style>
<title>Insert title here</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
	const gumsaekBtn = document.querySelector("#gumsaek");
	const searchWordTxt = document.querySelector("#searchWord");
	const moveToFindBtn = document.querySelector("#moveToFind");
     
	gumsaekBtn.addEventListener("click", function(e){
		let searchWordTxtV = document.querySelector("#searchWord").value;
        $.ajax({
            type: "POST",
            url:"/bdm/beforeMain/doSaveSearch.do",
            asyn:"true",
            dataType:"json",
            data:{
                words: searchWordTxtV
            },
            success:function(data){//통신 성공
            	// doRetrieve(1, searchWordTxtV);
                location.href = "/bdm/beforeMain/doGumsaek.do?pageNo=1&searchWord="+searchWordTxtV;
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });
	});
	
	searchWordTxt.addEventListener("keyup", function(e) {
        console.log("keyup:" + e.keyCode);
        let searchWordTxtV = document.querySelector("#searchWord").value;
        if (13 == e.keyCode) {
        	$.ajax({
                type: "POST",
                url:"/bdm/beforeMain/doSaveSearch.do",
                asyn:"true",
                dataType:"json",
                data:{
                    words: searchWordTxtV
                },
                success:function(data){//통신 성공
                    // doRetrieve(1, searchWordTxtV);
                	location.href = "/bdm/beforeMain/doGumsaek.do?pageNo=1&searchWord="+searchWordTxtV;
                },
                error:function(data){//실패시 처리
                    console.log("error:"+data);
                },
                complete:function(data){//성공/실패와 관계없이 수행!
                    console.log("complete:"+data);
                }
            });
        }
    });
	
	function doRetrieve(pageNo, searchWord) {
        console.log("doRetrieve pageNO:" + pageNo);
        console.log("doRetrieve searchWord:" + searchWord);

        let gumsaekForm = document.gumsaekFrm;
        gumsaekForm.pageNo.value = pageNo;
        gumsaekForm.action = "/bdm/beforeMain/doGumsaek.do";
        console.log("doRetrieve pageNO:" + gumsaekForm.pageNo.value);
        gumsaekForm.submit();
    }
}); // -- DOM end
</script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="${CP }/beforeMain/popSearchWord.do">Balance Diet Management</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<div class="search-container" id="search_area">
				<div class="search">
	                <form action="#" method="get" id="gumsaekFrm" name="gumsaekFrm">
	                    <input type="hidden" name="pageNo" id="pageNo" value = "1"/>
	                    <a href="#" class="link_main"> 
	                        <img src="${CP}/resources/images/logo-mini.png" alt="로고">
	                    </a>
	                    <input type="text" placeholder="검색어를 입력하세요" id = "searchWord" name="searchWord" class="search-input">
	                    <button type = "submit" class="search-button" id = "gumsaek"><img src="${CP}/resources/images/search_icon.png" alt=""></button>
	                </form>
	            </div>
			</div>
		</div>
	</div>
</nav>

</body>
</html>