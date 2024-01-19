<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>회원등록</title>
<script src="/bdm/resources/js/jquery-3.7.1.js"></script>
<script src="/bdm/resources/js/eUtil.js"></script>
<script>
    //A $( document ).ready() block.
    //body에 모든 element가 로딩이 완료되면 동작
    $(document).ready(function() {
        console.log("ready!");
        //숫자만 입력
        //keyup: keyboard event로 키보드를 keyup 했을때 발생
        $(".numOnly").on("keyup", function(e) {
            console.log("numOnly:" + $(this).val());

            let replaceNum = $(this).val().replace(/[^0-9]/g, "");

            $(this).val(replaceNum);
        });

    });//document ready

    function idDuplicateCheck() {
        console.log("-idDuplicateCheck()-");
        let userId = document.querySelector("#userId").value;
        if (eUtil.isEmpty(userId) == true) {
            alert('아이디를 입력 하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#userId").focus();
            return;
        }

        $.ajax({
            type : "GET",
            url : "/bdm/user/idDuplicateCheck.do",
            asyn : "true",
            dataType : "json", /*return dataType: json으로 return */
            data : {
                userId : userId
            },
            success : function(data) {//통신 성공
                console.log("success data:" + data);
                //let parsedJSON = JSON.parse(data);
                if ("1" === data.msgId) {
                    alert(data.msgContents);
                    document.querySelector("#idCheck").value = 0;
                } else {
                    alert(data.msgContents);
                    document.querySelector("#idCheck").value = 1;
                }

            },
            error : function(data) {//실패시 처리
                console.log("error:" + data);
            },
            complete : function(data) {//성공/실패와 관계없이 수행!
                console.log("complete:" + data);
            }
        });

    }

    function doSave() {
        console.log("----------------------");
        console.log("-doSave()-");
        console.log("----------------------");

        //javascript

        console.log("javascript userId:"
                + document.querySelector("#userId").value);
        console.log("javascript name:" + document.querySelector("#name").value);
        console.log("javascript password:"
                + document.querySelector("#password").value);
        console.log("javascript email:"
                + document.querySelector("#email").value);
        console.log("javascript birth:"
                + document.querySelector("#birth").value);
        console.log("javascript gender:"
                + document.querySelector("#gender").value);
        console.log("javascript height:"
                + document.querySelector("#height").value);
        console.log("javascript weight:"
                + document.querySelector("#weight").value);
        
        
        

        //$("#userId").val() : jquery id선택자
        //$(".userId")

        // console.log("jquery name:"+$("#name").val());
        //console.log("jquery ppl_input:"+$(".ppl_input").val());  

        if (eUtil.isEmpty(document.querySelector("#userId").value) == true) {
            alert('아이디를 입력 하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#userId").focus();
            return;
        }

        if (eUtil.isEmpty(document.querySelector("#name").value) == true) {
            alert('이름을 입력 하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#name").focus();
            return;
        }

        if (eUtil.isEmpty(document.querySelector("#password").value) == true) {
            alert('비밀번호를 입력 하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#password").focus();
            return;
        }

        if (eUtil.isEmpty(document.querySelector("#email").value) == true) {
            alert('이메일을 입력 하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#email").focus();
            return;
        }

        if (eUtil.isEmpty(document.querySelector("#birth").value) == true) {
            alert('생년월일을 입력하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#birth").focus();
            return;
        }

        if (eUtil.isEmpty(document.querySelector("#gender").value) == true) {
            alert('성별을 입력하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#gender").focus();
            return;
        }
        if (document.querySelector("#height").value == '0') {
            alert('키를 입력해주세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#height").focus();
            return;
        }
        if (document.querySelector("#weight").value == '0') {
            alert('몸무게를 입력해주세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#weight").focus();
            return;
            
        }    
        
        if (document.querySelector("#idCheck").value == '0') {
            alert('아이디 중복체크를 수행 하세요.');
            //$("#userId").focus();//사용자 id에 포커스
            document.querySelector("#idCheck").focus();
            return;
        }

       
        //confirm
        if (confirm("등록 하시겠습니까?") == false)
            return;

        $.ajax({
            type : "POST",
            url : "/bdm/user/doSave.do",
            asyn : "true",
            dataType : "html",
            data : {
                userId : document.querySelector("#userId").value,
                name : document.querySelector("#name").value,
                password : document.querySelector("#password").value,
                email : document.querySelector("#email").value,
                birth : document.querySelector("#birth").value,
                gender : document.querySelector("#gender").value,
                height : document.querySelector("#height").value,
                weight : document.querySelector("#weight").value

            },
            success : function(data) {//통신 성공     
                console.log("success data:" + data);
                //data:{"msgId":"1","msgContents":"dd가 등록 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}
                let parsedJSON = JSON.parse(data);
                if ("1" === parsedJSON.msgId) {
                    alert(parsedJSON.msgContents);

                } else {
                    alert(parsedJSON.msgContents);
                }

            },
            error : function(data) {//실패시 처리
                console.log("error:" + data);
            },
            complete : function(data) {//성공/실패와 관계없이 수행!
                console.log("complete:" + data);
            }
        });

    }
</script>

</head>
<body>
    <div class="container">
        <!-- 제목 -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">회원등록</h1>
            </div>
        </div>
        <!--// 제목 ----------------------------------------------------------------->
        <!-- 버튼 -->
        <div class="row justify-content-end">
            <div class="col-auto">
                <input type="button" class="btn btn-primary" value="등록" id="doSave"
                    onclick="window.doSave();">
            </div>
        </div>
        <!--// 버튼 ----------------------------------------------------------------->

        <!-- 회원 등록영역 -->
        <div>
            <form action="#" name="userRegFrm">
                <%-- id중복체크 수행 여부 확인:0(미수행),1(수행) --%>
                <input type="hidden" name="idCheck" id="idCheck" value="0">
                <div class="mb-3">
                    <label for="userId" class="form-label">아이디</label> <input
                        type="text" class="form-control ppl_input" name="userId"
                        id="userId" placeholder="아이디를 입력 하세요." size="20" maxlength="30">
                    <input type="button" class="btn btn-primar" value="중복확인"
                        id="idDuplicateCheck" onclick="window.idDuplicateCheck();">
                </div>
                <div class="mb-3">
                    <!--  아래쪽으로  여백 -->
                    <label for="name" class="form-label">이름</label> <input type="text"
                        class="form-control" name="name" id="name"
                        placeholder="이름을 입력 하세요." size="20" maxlength="21">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label> <input
                        type="password" class="form-control" name="password" id="password"
                        placeholder="비밀번호를 입력 하세요." size="20" maxlength="30">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label> <input
                        type="text" class="form-control" name="email" id="email"
                        placeholder="이메일을 입력하세요." size="20" maxlength="320">
                </div>
                <div class="mb-3">
                    <label for="birth" class="form-label">생년월일</label> <input
                        type="text" class="form-control" name="birth" id="birth"
                        placeholder="생년월일6자리 입력하세요" size="20" maxlength="320">
                </div>
                <div class="mb-3">
                   <label for="gender" class="form-label">성별</label>
                   <select name="gender" id="gender" class="form-select">
                       <option value= 1>남자</option>
                       <option value= 2 >여자</option>                      
                   </select>
               </div>   
                <div class="mb-3">
                    <label for="height" class="form-label">키</label> <input type="text"
                        class="form-control" name="height" id="height"
                        placeholder="cm제외하고 입력해주세요." size="20" maxlength="320">
                </div>
                <div class="mb-3">
                    <label for="weight" class="form-label">몸무게</label> <input
                        type="text" class="form-control" name="weight" id="weight"
                        placeholder="kg제외하고 입력해주세요." size="20" maxlength="320">
                </div>
            </form>
        </div>
        <!--// 회원 등록영역 ------------------------------------------------------>
    </div>
</body>
</html>