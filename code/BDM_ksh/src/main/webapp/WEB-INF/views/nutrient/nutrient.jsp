<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value = "${pageContext.request.contextPath}" scope = "page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
</style>
<title>음식 검색</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
   integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" 
   integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="${CP }/resources/js/jquery-3.7.1.js"></script>
<script src="${CP }/resources/js/eUtil.js"></script>  
<script>

document.addEventListener("DOMContentLoaded", function(){
	const doRetrieveBtn = document.querySelector("#doRetrieve");
	const saveFoodsBtn = document.querySelector("#saveFoods");
	const selectedDeleteBtn = document.querySelector("#selectedDelete");
	const deleteAllBtn = document.querySelector("#deleteAll");
	const closeBtn = document.querySelector("#close");
	
	closeBtn.addEventListener("click", function(e){
		if(confirm('입력된 정보들이 모두 사라집니다. 마이페이지로 되돌아 가시겠습니까?')==false) return;
		
		window.location.href = "/bdm/food/doCancle.do";
	});
	
	deleteAllBtn.addEventListener("click", function(e){
		var selectedFoods = [];
        <c:if test="${not empty selectedFoodList}">
            for (var i = 0; i <${selectedFoodList.size()}; i++) {
                var checkbox = document.getElementById("foodCheckbox" + i);
                selectedFoods.push(checkbox.value-1);
            }
            // console.log("Selected Foods: " + selectedFoods.join(", "));
            console.log("Selected Foods: " + selectedFoods);
            window.location.href = "${CP }/food/doSelectedDelete.do?index="+selectedFoods;
        </c:if>     
	});
	
	selectedDeleteBtn.addEventListener("click", function(e){
		var selectedFoods = [];
		<c:if test="${not empty selectedFoodList}">
	        for (var i = 0; i <${selectedFoodList.size()}; i++) {
	            var checkbox = document.getElementById("foodCheckbox" + i);
	            
	            if (checkbox && checkbox.checked) {
	                selectedFoods.push(checkbox.value-1);
	                // selectedFoods.push('${selectedFoodList.get(checkbox.value)}');
	            }
	        }
	        // console.log("Selected Foods: " + selectedFoods.join(", "));
	        console.log("Selected Foods: " + selectedFoods);
	        window.location.href = "${CP }/food/doSelectedDelete.do?index="+selectedFoods;
	    </c:if>	    
	});
	
    //목록버튼 이벤트 감지
    doRetrieveBtn.addEventListener("click",function(e){
        console.log("doRetrieve click");
        doRetrieve(1);
    });
    
    function doRetrieve(pageNo){
        console.log("doRetrieve pageNO:"+pageNo);
        
        let foodForm = document.foodFrm;
        // foodForm.div.value = document.querySelector("#div").value;
        foodForm.pageNo.value = pageNo;
        foodForm.action = "/bdm/nutrient/doRetrieve.do";
        console.log("doRetrieve pageNO:"+foodForm.pageNo.value);
        foodForm.submit();
    }
    
    //javascript:다건 이벤트 처리
    const rows = document.querySelectorAll("#foodTable>tbody>tr");
    //각행에 이벤트 처리
    rows.forEach(function(row) {
        row.addEventListener('dblclick',function(e){
	        //클릭된 행의 모든 셀(td)을 가져 오기
			let cells = row.getElementsByTagName("td");
			const name   = cells[2].innerText;
			const code = cells[0].innerText;
			console.log('name:'+name);
			
			if(confirm(name + '을/를 선택하시겠습니까?')==false) return;
			
			window.location.href = "${CP }/food/doSelectFood.do?code="+code + "&name=" + name;
			
			$.ajax({
	            type: "GET",
	            url:"${CP }/food/doShowSelectedFoods.do",
	            asyn:"true",
	            dataType:"json",
	            data:{
	            },
	            success:function(data){   
	               console.log('선택 완료');
	               window.location.href = "${CP}/food/doSelectFood.do";
	            },
	            error:function(data){
	                console.log("error:"+data);
	            },
	            complete:function(data){
	                console.log("complete:"+data);
	            }
	        });
        });
    });
    
    saveFoodsBtn.addEventListener("click", function(e){
    	console.log('saveFoodsBtn clicked');
    	
    	<c:if test="${empty selectedFoodList}">
            alert('저장할 음식을 선택해주세요.');
            return;
        </c:if>
    	
    	$.ajax({
            type: "POST",
            url:"${CP }/food/doSaveFood.do",
            asyn:"true",
            dataType:"html",
            data:{
            },
            success:function(data){   
            	console.log("success data:"+data);
                let parsedJSON = JSON.parse(data);
                if("1" === parsedJSON.msgId){
                    alert(parsedJSON.msgContents);
                }else{
                    alert(parsedJSON.msgContents);
                }
                window.location.href = "${CP }/beforeMain/moveToMyPage.do";
            },
            error:function(data){
                console.log("error:"+data);
            },
            complete:function(data){
                console.log("complete:"+data);
            }
        });
    });
});
function pageDoRetrieve(url,pageNo){
    console.log("url:"+url);
    console.log("pageNo:"+pageNo);
    
    let foodForm = document.foodFrm;
    foodForm.pageNo.value = pageNo;
    foodForm.action = url;
    foodForm.submit();
}
</script>
</head>
<body>
    ${user } <br/>
    selectedFoodList: ${selectedFoodList } <br/>
    selectedCodeList: ${selectedCodeList }
    <div class = "container">
        <div class = "row">
            <div class="col-lg-12 d-flex justify-content-between">
		        <h1 class="page-header">음식 검색</h1>
		        <button type="button" id = "close" class="btn-close ml-auto" aria-label="Close"></button>
		    </div>
        </div>
        
        <form action ="#" method = "get" id = "foodFrm" name = "foodFrm">
            <input type = "hidden" name = "pageNo" id = "pageNo"/>
            <div class = "col-auto">
                <input type = "text" id = "searchWord" name = "searchWord" maxlength = "100" placeholder = "검색할 음식을 입력하세요." value = "${paramVO.searchWord }">
                <input type = "button" value = "검색" id = "doRetrieve">
            </div>
        </form>
        
        <table class = "table table-bordered border-primary table-hover table-striped" id = "foodTable">
            <thead>
                <tr>
                    <th scope = "col" class = "text-center" style="display: none;">코드</th>
                    <th scope = "col" class = "text-center">NO</th>
                    <th scope = "col" class = "text-center">음식명</th>
                    <th scope = "col" class = "text-center">기준량</th>
                    <th scope = "col" class = "text-center">칼로리</th>
                    <th scope = "col" class = "text-center">탄수화물(g)</th>
                    <th scope = "col" class = "text-center">단백질(g)</th>
                    <th scope = "col" class = "text-center">지방(g)</th>
                    <th scope = "col" class = "text-center">당류(g)</th>
                    <th scope = "col" class = "text-center">1인분</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty list }">
                        <c:forEach var = "vo" items = "${list }" varStatus = "status">
                            <tr>
                                <td class = "text-center" style="display: none;"><c:out value="${vo.code}" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${status.index + 1}" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.name }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.norm }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.kcal }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.carbohydrate }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.protein }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.fat }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.sugars }" escapeXml = "true"/></td>
                                <td class = "text-center"><c:out value="${vo.weight }" escapeXml = "true"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan = "99" class = "text-center">조회된 음식이 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <!-- 페이징 : 함수로 페이징 처리 
	         총글수, 페이지 번호, 페이지 사이즈, bottomCount, url,자바스크립트 함수
	    -->           
	    <div class="d-flex justify-content-center">
	        <nav>
	           ${pageHtml }
	        </nav>    
	    </div>
        <form action ="#" method = "post" id = "eventFrm" name = "eventFrm">
	        <h3>선택한 음식</h3>
	        <input type = "button" value = "저장하기" id = "saveFoods" name = "saveFoods"/>
	        <input type = "button" value = "선택 삭제" id = "selectedDelete" name = "selectedDelete"/>
	        <input type = "button" value = "전체 삭제" id = "deleteAll" name = "deleteAll"/>
        </form>
        
        <table class = "table table-bordered border-primary table-hover table-striped" id = "selectedTable">
            <thead>
                <tr>
                    <th scope = "col" class = "text-center">선택한 음식</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty selectedFoodList }">
                        <c:forEach var = "vo" items = "${selectedFoodList }" varStatus = "status">
                            <%-- <tr>
                                <td class = "text-center"><c:out value="${selectedFoodList.get(status.index) }" escapeXml = "true"/></td>
                            </tr> --%>
                            <ul class="list-group">
							  <li class="list-group-item">
							    <input id = "foodCheckbox${status.index }" class="form-check-input me-1" type="checkbox" value="${status.index + 1}" aria-label="...">
							    ${selectedFoodList.get(status.index)}
							  </li>
							</ul>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan = "99" class = "text-center">선택한 음식이 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>