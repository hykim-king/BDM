<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head> 
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>Balance Diet Management</title>
<style>
   .readonly-input {
    background-color: #e9ecef ;
   }

</style>
<script>
document.addEventListener("DOMContentLoaded",function() {
	commentsRetrieve();
    const moveToListBTN = document.querySelector("#moveToList");
    const doDeleteBTN   = document.querySelector("#doDelete");
    const regId = document.querySelector("#regId").value;
    const commentsDoSaveBTN = document.querySelector("#commentsDoSave");
    const postNo = $('#postNo').val();
    
    commentsDoSaveBTN.addEventListener("click",function(e){
    	console.log('commentsDoSaveBTN click');
    	
    	
    	const postNo = document.querySelector('#postNo').value;
    	if(eUtil.isEmpty(postNo) == true){
    		alert('게시글 순번을 확인 하세요.');
    		return;
    	}
    	console.log('postNo:'+postNo);
    	
    	
    	const contents = document.querySelector('#replyContents').value;
        if(eUtil.isEmpty(contents) == true){
            alert('댓글을 확인 하세요.');
            document.querySelector('#replyContents').focus();
            return;
        }
        console.log('replyContents:'+contents);
        
      	const regId    = '${sessionScope.user.id}';
        if(eUtil.isEmpty(regId) == true){
            alert('로그인 하세요.');
            return;
        }    	  
        console.log('regId: ' + regId);  
        
        
        $.ajax({
            type: "POST",
            url:"/bdm/qaComments/doSave.do",
            asyn:"true",
            dataType:"json",
            data:{
            	"contents": contents,
                "postNo": postNo,
                "id": regId,
                "modId": regId
            },
            success:function(data){//통신 성공
                console.log("success msgId:"+data.msgId);
                console.log("success msgContents:"+data.msgContents);
                
                if("1"==data.msgId){
                	alert(data.msgContents);
                	commentsRetrieve();//댓글 조회
                	//등록 댓글 초기화
                	document.querySelector('#replyContents').value = '';
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
    	  	
    });
    
    function commentsRetrieve(){
    	const postNo = document.querySelector("#postNo").value
    	console.log('postNo:'+postNo)
    	
    	if(eUtil.isEmpty(postNo) == true){
    		alert('게시글 번호를 확인 하세요.');
    		return;
    	}
    	
        $.ajax({
            type: "GET",
            url:"/bdm/qaComments/doRetrieve.do",
            asyn:"true",
            dataType:"json", //return type
            data:{
                "postNo": postNo  
            },
            success:function(data){//통신 성공
                console.log("success data:"+data);
                console.log("data.length:"+data.length);
                
                let commentsDiv = '';
                
                //기존 댓글 모두 삭제
                //#요소의 내용을 모두 지웁니다.
                document.getElementById("contentsDoSaveArea").innerHTML = "";
                
                
                if(0==data.length){
                	console.log("댓글이 없어요1");
                	return;
                }
                	
                
                for(let i=0;i<data.length;i++){
                	commentsDiv += '<div class="dynamicComments"> \n';
                	commentsDiv += '<div class="row justify-content-end"> \n';
                	commentsDiv += '<div class="col-auto"> \n';
                	commentsDiv += '<span>등록자: ' + data[i].id + '</span> \n';
                	commentsDiv += '<span>등록일: ' + data[i].regDt + '</span> \n';
                	commentsDiv += '\t\t\t <input type="button" value="댓글수정" class="btn btn-primary contentsDoUpdate"  >   \n';
                	commentsDiv += '\t\t\t <input type="button" value="댓글삭제" class="btn btn-primary contentsDoDelete"  >   \n';
                	commentsDiv += '</div> \n';
                	commentsDiv += '</div> \n';
                	
                	commentsDiv += '<div class="mb-3">  \n';
                	commentsDiv += '<input type="hidden" name="regNo" value="'+data[i].regNo +'"> \n';
                	
                	commentsDiv += '<textarea rows="3" class="form-control dyCommentsContents"   name="dyCommentsContents">'+data[i].contents+'</textarea> \n';
                	commentsDiv += '</div> \n';
                	
                	commentsDiv += '</div> \n';
                	
                }
                
                
                //조회 댓글 출력
                document.getElementById("contentsDoSaveArea").innerHTML = commentsDiv;
                
                
                //-댓글:삭제,수정-------------------------------------------------------------
                //댓글 수정
               // $(".contentsDoUpdate").on("click", function(e){
                //	console.log('contentsDoUpdate click');
               // }); 

                //javascript
                commentsDoUpdateBTNS = document.querySelectorAll(".contentsDoUpdate");
                commentsDoUpdateBTNS.forEach(function(e){
                	e.addEventListener("click",function(e){
                		console.log('commentsDoUpdate click');
                		
                		//reply,reply_seq
                		const regNo =this.closest('.dynamicComments').querySelector('input[name="regNo"]')
                		console.log('regNo:'+regNo.value);
                		if(eUtil.isEmpty(regNo.value)==true){
                			alert('댓글 순번을 확인하세요.');
                			return;
                		}
                		
                		const contents =this.closest('.dynamicComments').querySelector('textarea[name="dyCommentsContents"]')
                        if(eUtil.isEmpty(contents.value)==true){
                            alert('댓글을 확인하세요.');
                            contents.focus();
                            return;
                        }
                		
                		console.log('contents:'+contents.value);
                		
                		if(window.confirm('수정 하시겠습니까?')==false){
                			return ;
                		}
                		 var id = '${sessionScope.user.id}';
                		 var authorId = $(this).closest('.dynamicComments').find('span:contains("등록자:")').text().replace('등록자: ', '').trim();
                	        
                	        if(id != authorId){
                	        	alert('타인의 글은 수정 불가능합니다.');
                	        	return;
                	        }
                		
                        $.ajax({
                            type: "POST",
                            url:"/bdm/qaComments/doUpdate.do",
                            asyn:"true",
                            dataType:"json",
                            data:{
                                "regNo": regNo.value,
                                "contents":contents.value
                            },
                            success:function(data){//통신 성공
                                console.log("success data:"+data.msgId);
                                console.log("success data:"+data.msgContents);
                                
                                if("1" == data.msgId){
                                    alert(data.msgContents);
                                    commentsRetrieve();
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
                        
                		
                	});
                	
                });//-----replyDoUpdateBTNS-------------------------------------
                
                //댓글삭제
                $(".contentsDoDelete").on("click", function(e){
                	console.log('contentsDoDelete click');
                	
                	const regNo = $(this).closest('.dynamicComments').find('input[name="regNo"]').val();
                	console.log('regNo:'+regNo);
                	
                	if(window.confirm("삭제 하시겠습니까?")==false){
                		return;
                	}
                		var id = '${sessionScope.user.id}';
                		var authorId = $(this).closest('.dynamicComments').find('span:contains("등록자:")').text().replace('등록자: ', '').trim();
                    
                    if(id != authorId){
                    	alert('타인의 글은 삭제 불가능합니다.');
                    	return;
                    }
                	
                    $.ajax({
                        type: "GET",
                        url:"/bdm/qaComments/doDelete.do",
                        asyn:"true",
                        dataType:"json",
                        data:{
                            "regNo": regNo
                        },
                        success:function(data){//통신 성공
                            console.log("success data:"+data.msgId);
                            console.log("success data:"+data.msgContents);
                            
                            if("1" == data.msgId){
                            	alert(data.msgContents);
                            	commentsRetrieve();
                            } else {
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
                });
                
                
                //--------------------------------------------------------------
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });    	
    	
    }
    


  //삭제 이벤트 감지 및 처리
    doDeleteBTN.addEventListener("click",function(e){
        console.log('doDeleteBTN click');
        
        const postNo = document.querySelector("#postNo").value;
        console.log('postNo :'+postNo);
        
        if(eUtil.isEmpty(postNo) == true){
            alert('순번을 확인 하세요.');
            return;
        }

        if(window.confirm('삭제 하시겠습니까?')==false){
            return;
        }
 			var id = '${sessionScope.user.id}';
        
        if(id != regId){
        	alert('타인의 글은 삭제 불가능합니다.');
        	return;
        }


       	$.ajax({
    		type: "GET",
    		url:"/bdm/qa/doDelete.do",
    		asyn:"true",
    		dataType:"json",
    		data:{
    			"postNo": postNo
    		},
    		success:function(data){//통신 성공
        		console.log("success data.msgId:"+data.msgId);
        		console.log("success data.msgContents:"+data.msgContents);
                   alert(data.msgContents);
                   window.location.href = "${CP}/qa/doRetrieve.do";
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	}
    	});

   

    });
    
    
    //목록 이벤트 감지 및 처리
    moveToListBTN.addEventListener("click",function(e){
        console.log('moveToListBTN click');
        if(confirm('목록 화면으로 이동 하시겠습니까?')==false){
            return;
        }           
        window.location.href = "${CP}/qa/doRetrieve.do";
    
        
    });
});//--DOMContentLoaded
</script>
</head>
<body>
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">상세조회</h1>
        </div>
    </div>
    <!--// 제목 ----------------------------------------------------------------->
    <!-- 버튼 -->
    <div class="row justify-content-end">
        <div class="col-auto">
            <input type="button" value="목록" class="btn btn-primary" id="moveToList">
            <input type="button" value="삭제" class="btn btn-primary" id="doDelete" >
        </div>
    </div>
        <div class="mb-3 row" style="display: none"> <!--  아래쪽으로  여백 -->
            <label for="seq" class="col-sm-2 col-form-label">순번</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="postNo" name="postNo" maxlength="100"
                 value="${vo.postNo}" readonly
                >
            </div>
        </div>
        <div class="mb-3 row">
            <label for="regId" class="col-sm-2 col-form-label">등록자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regId" name="regId"  readonly="readonly"
                 value="${vo.id}"
                 >
            </div>        
        </div>
        <div class="mb-3 row">
            <label for="regId" class="col-sm-2 col-form-label">등록일</label>
            <div class="col-sm-10">
                <input type="text" class="form-control readonly-input" id="regDt" name="regDt" 
                value="${vo.regDt}"  readonly="readonly" >
            </div>        
        </div>
        <div class="mb-3"> <!--  아래쪽으로  여백 -->
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control readonly-input" id="title" name="title" maxlength="100" 
             value="${vo.title}"
            placeholder="제목을 입력 하세요">
        </div>      
        <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea rows="7" class="form-control readonly-input"  id="contents" name="contents">${vo.contents }</textarea>
        </div>
    <!--// form --------------------------------------------------------------->
    
    	<div id="contentsDoSaveArea">
        <!-- 버튼 -->
		<div class="dynamicComments">
			
			<div class="row justify-content-end">
				<div class="col-auto">
					<input type="button" value="댓글수정" class="btn btn-primary commentsDoUpdate"  >
					<input type="button" value="댓글삭제" class="btn btn-primary contentsDoDelete"  >
				</div>
			</div>
			<div class="mb-3">
				<input type="hidden" name="regNo" value="">
				<textarea rows="3" class="form-control dyCommentsContents"   name="dyCommentsContents"></textarea>
			</div>
		</div>        
	</div>
	<div id="contentsDoSaveArea">
    
	    <!-- 버튼 -->
		<div class="row justify-content-end">
			<div class="col-auto">
				<input type="button" value="댓글등록" class="btn btn-primary" id="commentsDoSave" >
			</div>
		</div>
	    <!--// 버튼 ----------------------------------------------------------------->
		<div class="mb-3">
			<textarea rows="3" class="form-control"  id="replyContents" name="replyContents"></textarea>
		</div>        
	</div>
    
      
</div>

</body>
</html>