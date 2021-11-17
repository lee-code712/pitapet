<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    
    <script>
    	// alert('로그인이 필요합니다.\n');
    </script>
</head>

<body>
	<div id="headerWrap">
        <div id="headerInner">
            <div id="logoWrap">
                <div id="logo">PIT A PET</div>
                <img src="../images/proRank.svg" />
            </div>
            
            <div id="gnb">    
            	<div id="reviewLink">이용 후기</div>
                <c:if test="${isLogined}">
                	<c:url value='/reservation/listSitter' var="sitterListUrl">
                		<c:param name="currentPage" value="1"/>
                	</c:url>
                	<div id="sitterListLink" onclick="location.href='${sitterListUrl}'">돌보미 조회</div>
                	
                	<c:url value='/like/likeList' var="likeListUrl"/>
                	<div id="likeLink" onclick="location.href='${likeListUrl}'">좋아요</div>
                	
                	<c:url value='/member/memberMyPage' var="memberMyPageUrl"/>
                	<div id="myPageLink" onclick="location.href='${memberMyPageUrl}'">마이페이지</div>
                	
                	<c:url value='/member/logout' var="url"/>
                	<button id="headerLoginBtn" onclick="location.href='${url}'">로그아웃</button>
                </c:if>
                <c:if test="${!isLogined}">
                	<div id="sitterListLink">돌보미 조회</div>
                	<div id="likeLink">좋아요</div>
                	<div id="myPageLink">마이페이지</div>
                	
                    <c:url value='/member/login/form' var="url"/>
                	<button id="headerLoginBtn" onclick="location.href='${url}'">로그인</button>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>