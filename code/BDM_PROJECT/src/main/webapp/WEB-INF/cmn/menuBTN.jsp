<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="com.test.bdm.nutrient.domain.NutrientVO"%>

<c:set var="CP" value="${pageContext.request.contextPath}" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${CP}/resources/css/menuBTN.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body id="aside">
    <div id="app" class="wrap">
        <div class="aside_header">
            <div class="ah_wrap">
                <h1 class="ah_title">바로가기</h1>
            </div>
        </div>
        <div class="aside_wrap">
            <div class="section_shortcut">
                <div class="user_area">
                    <a href="#" class="link_user" rel="noreferrer">
                        <h2 class="title">
                            <span class="name">로그인</span>
                            <span class="text">하세요</span>
                        </h2>
                    </a>
                </div>
                <div class="bg_media_wrap">
                    <div class="ss_shortcut_wrap" style="user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
                        <div class="scroll-area">
                            <div class="ss_scroll_list native_scroll comp_snap_list" style="position: relative;">
                                <div class="ss_scroll_item comp_snap_item">
                                    <ol class="ss_shortcut_list">
                                        <li class="ss_shortcut_item">
                                            <a class="shortcut_a" href="" target="_blank" rel="noreferrer">
                                                <div class="sa_mw">
                                                    <img src="new_icon.png" class="sa_m" width="48" height="48" alt="">
                                                </div>
                                                <em class="sa_t">뉴스</em>
                                            </a>
                                        </li>
                                        <li class="ss_shortcut_item">
                                            <a class="shortcut_a" href="/bdm/notice/doRetrieve.do" target="_blank" rel="noreferrer">
                                                <div class="sa_mw">
                                                    <img src="notice_icon.png" class="sa_m" width="48" height="48" alt="">
                                                </div>
                                                <em class="sa_t">공지사항</em>
                                            </a>
                                        </li>
                                        <li class="ss_shortcut_item">
                                            <a class="shortcut_a" href="/bdm/bulletin/doRetrieve.do" target="_blank" rel="noreferrer">
                                                <div class="sa_mw">
                                                    <img src="board_icon.png" class="sa_m" width="48" height="48" alt="">
                                                </div>
                                                <em class="sa_t">게시판</em>
                                            </a>
                                        </li>
                                        <li class="ss_shortcut_item">
                                            <a class="shortcut_a" href="" target="_blank" rel="noreferrer">
                                                <div class="sa_mw">
                                                    <img src="mall_icon.png" class="sa_m" width="48" height="48" alt="">
                                                </div>
                                                <em class="sa_t">꼬르륵몰</em>
                                            </a>
                                        </li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>