<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>돌보미 지원</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
	<link rel="stylesheet" href="/css/sitterApplyForm.css"/>
</head>

<body>
	<%@include file="../components/header.jsp" %>
    
    <div id="pageWrap">
        <div id="pageTit">돌보미 지원</div>
        <div id="introTit">자기소개</div>
        <div id="introTextWrap">
            <textarea placeholder="내용을 입력하세요." id="introText"></textarea>
        </div>

        <div id="infoTit">돌보미 경력</div>
        <div id="infoContent"><input type="radio" checked id="blank" /> 유 <input type="radio" id="blank" /> 무</div>
        <div id="fileWrap"><img src="../images/fileImg.svg" id="fileImg" /> <input type="file"/></div> 
        <div id="fileWarning">* 돌보미 경력을 인증할 사진을 첨부하세요.</div>

        <div id="infoTit">자격증</div>
        <div id="infoContent"><input type="radio" checked id="blank" /> 유 <input type="radio" id="blank" /> 무</div>
        <div id="fileWrap"><img src="../images/fileImg.svg" id="fileImg" /> <input type="file"/></div> 
        <div id="fileWarning">* 자격증 사진을 첨부하세요.</div>

        <div id="infoTit">제공 서비스</div>
        <div id="infoContent"><input type="checkbox" checked id="blank" /> 배식하기 <input type="checkbox" id="blank" /> 산책하기 <input type="checkbox" id="blank" /> 목욕하기 <input type="checkbox" id="blank" /> 픽업가능 <input type="checkbox" id="blank" /> 실내놀이 <input type="checkbox" id="blank" /> 미용관리</div>

        <div id="infoTit">돌봄 가능 반려동물</div>
        <div id="infoContent"><input type="checkbox" checked id="blank" /> 대형견 <input type="checkbox" id="blank" /> 소형견 <input type="checkbox" id="blank" /> 고양이</div>

        <div id="btnWrap">
            <button id="applyBtn">완료</button>
        </div>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>