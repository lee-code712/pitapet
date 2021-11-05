<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="nav">
	<div id="logoWrap">
    	<div id="logoInner">
        	<img src="./images/logo.svg" id="logoImg" />
        	<div id="logoTit">PIT A PET</div>
    	</div>
    	<c:if test="${isLogined}">
			<img src="./images/rank.svg" id="rankImg" />
       </c:if>
   	</div>
    <div id="navInner">
        <div id="navListOn">
            <img src="./images/home.svg" id="navImg" />
            홈
        </div>
        <div id="navList">
            <img src="./images/search.svg" id="navImg" />
            돌보미 조회
        </div>
        <div id="navList">
            <img src="./images/review.svg" id="navImg" />
            이용 후기
        </div>
    </div>
</div>

<div id="componentWrap">
    <div id="componentInner">
        <div id="heartImgWrap" >
            <img src="./images/heart.svg" id="heartImg" />
        </div>
        <div id="personImgWrap">
            <img src="./images/person.svg" id="personImg" />
        </div>
        <div id="logoutImgWrap">
        	<c:if test="${isLogined}">
            	<img src="./images/logout.svg" id="logoutImg" />
            </c:if>
        </div>
    </div>
