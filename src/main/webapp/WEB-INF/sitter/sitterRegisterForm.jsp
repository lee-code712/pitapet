<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>돌보미 정보 등록</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/sitterRegisterForm.css"/>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
      <form name="form" method="POST" action="<c:url value='/petSitter/registerSitter' />"> 
        <div id="pageTit">돌보미 정보 등록</div>
        <table>
            <tr>
                <td id="tableTit">인증</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">이름</td>
                <td><input type="text" value="${applicationInfo.applicant.name}" id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">성별</td>
                <td><input type="text" value="${applicationInfo.applicant.gender}" id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">전화번호</td>
                <td><input type="text" value="${applicationInfo.applicant.phone}"  id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">주소</td>
                <td><input type="text" value="${applicationInfo.applicant.address}"  id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">돌보미 경력</td>
                <td><input type="text" value="${applicationInfo.career}"  id="textDesign" /></td>
            </tr>
            
            <tr>
                <td id="tdTit">제공 서비스</td>
                <td>
                    <c:forEach var="service" items="${serviceList}">
		                  <input type="checkbox" id="textDesign" name="serviceCheck" value="${service.id}" />${service.title}
	                </c:forEach>
                </td>
            </tr>

            <tr>
                <td id="tdTit">자격증</td>
				<td><input type="text" value="${applicationInfo.certification}"  id="textDesign" /></td>
            </tr>
        </table>

        <table id="introduceWrap">
            <tr>
                <td id="tableTit">자기소개</td>
            </tr>

            <tr>
                <td>
                    <textarea value="${applicationInfo.introduction}" id="introduceText"></textarea>
                </td>
            </tr>
        </table>

        <div id="fileReviewBtnWrap">
            <div id="fileWrap">
                <img src="../images/fileImg.svg" id="fileImg"/>
                <input type="file" id="fileBtn"/>
            </div>
        </div>
   
        <table id="infoRegisterWrap">
            <tr>
                <td id="tableTit">정보 등록</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">정보 공개</td>
                <td>
                    <input type="radio" name="publicStatus" value="Y" checked /> 공개
                    <input type="radio" name="publicStatus" value="N" id="radioBox" /> 비공개
                </td>
            </tr>

            <tr>
                <td id="tdTit">돌봄 가능한 날짜</td>
                <td>
                    <input type="checkbox" id="textDesign" name="ableDate" value="0" />월
                    <input type="checkbox" id="textDesign" name="ableDate" value="1" />화
                    <input type="checkbox" id="textDesign" name="ableDate" value="2" />수
                    <input type="checkbox" id="textDesign" name="ableDate" value="3" />목
                    <input type="checkbox" id="textDesign" name="ableDate" value="4" />금
                    <input type="checkbox" id="textDesign" name="ableDate" value="5" />토
                    <input type="checkbox" id="textDesign" name="ableDate" value="6" />일
                </td>
            </tr>

            <tr>
                <td id="tdTit">이용 금액</td>
                <td>
                    <input type="text" placeholder="이용금액(1박케어,데이케어)을 입력하세요. 예) 32000,22000" id="textDesign" name="calculatedPrice"/>
                </td>
            </tr>

            <tr>
                <td id="tdTit">돌봄 가능 반려동물</td>
                <td>
                    <input type="radio" value="0" checked /> 대형견
                    <input type="radio" value="1" id="radioBox" /> 소형견
                    <input type="radio" value="2" id="radioBox" /> 고양이
                </td>
            </tr>

            <tr>
                <td id="tdTit">원하는 검색 태그</td>
                <td><input type="text" placeholder="원하는 검색 태그를 입력하세요." id="textDesign" name="tag"/></td>
            </tr>

            <tr>
                <td id="tdTit">기타 사항</td>
                <td><input type="text" placeholder="기타 사항을 입력하세요." id="textDesign" name="notes"/></td>
            </tr>
        </table>

        	<div id="btnWrap">
            	<button type="submit" id="sitterRegisterBtn">저장</button>
        	</div>
        </form>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>