<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>반려동물 정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/petUpdateForm.css"/>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="/js/petAddForm.js"></script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTit">반려동물 정보</div>
        
        <c:if test="${addFailed}">
			<script> alert("반려동물 추가에 실패했습니다."); </script>
		</c:if>
        
        <form name="form" method="POST" action="<c:url value='/pet/addPet'/>" >
	        <table>
	            <tr>
	                <td id="tableTit">반려동물 정보</td>
	                <td id="tableTit"></td>
	            </tr>
	
	            <tr>
	                <td id="tdTit">종류</td>
	                <td>
	                    <select id="joinPetKind" name="petKind"> 
	                    	<c:forEach var="petKind" items="${petKindList}" varStatus="status">
		                        <option value="${petKind.id}">${petKind.largeCategory} - ${petKind.smallCategory}</option>
	                        </c:forEach>
	                    </select>	
	                </td>
	            </tr>
	
	            <tr>
	                <td id="tdTit">이름</td>
	                <td><input type="text" name="name" placeholder="이름을 입력하세요." id="textDesign" /></td>
	            </tr>
	
	            <tr>
	                <td id="tdTit">생년월일</td>
	                <td><input type="date" id="birth" name="birth" /></td>
	            </tr>
	            
	            <tr>
	                <td id="tdTit">성별</td>
	                <td>
						<select id="joinPetKind" name="gender"> 
							<option value="M">수컷</option>
							<option value="F">암컷</option>
	                    </select>	
					</td>
	            </tr>
	        </table>
	        
	        <div id="btnWrap">
	            <button id="cancelBtn" onclick="addPet(this.id)">취소</button>
	            <button id="addBtn" onclick="addPet(this.id)">추가</button> 
	        </div>
        </form>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>