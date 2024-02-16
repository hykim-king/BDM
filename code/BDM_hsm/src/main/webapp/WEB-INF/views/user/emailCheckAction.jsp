<!-- 사용자가 Email 인증 버튼을 눌렀을 때 처리해주는 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.bdm.user.domain.UserVO"%>
<%@ page import="com.test.bdm.user.confirm.SHA256"%>
<%@ page import="java.io.PrintWriter"%>


<%
    request.setCharacterEncoding("UTF-8");
    UserVO vo = new UserVO();
    String code = null;
    if(request.getParameter("code") != null){
        code = request.getParameter("code");
    }
    
    String userID = null;
    
    // hash 처리한 결과를 비교해 결과 값 반환
    String userEmail = vo.getEmail();
    boolean isRight = (new SHA256().getSHA256(userEmail).equals(code)) ? true : false;
    if(isRight == true){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('인증에 성공했습니다.');");
        script.println("location.href = 'index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }else{
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('잘못된 이메일 주소입니다.');");
        script.println("location.href = 'index.jsp'");
        script.println("</script>");
        script.close();
    }

%>