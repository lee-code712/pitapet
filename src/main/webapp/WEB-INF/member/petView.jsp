<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    
    <style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
		
		@charset "UTF-8";

#headerWrap {
    border-bottom: 1px solid rgba(150, 150, 150, 0.2);
}

#pageWrap {
    margin: 100px auto 0px auto;
    width: 1194px;
}

#pageTit {
    margin-bottom: 100px;
    font-size: 24px;
}

#tableTit {
    padding-left: 20px;
    height: 40px;
    background-color: #F3F3F3;
    font-size: 18px;
}

table {
    border: 1px solid #EAEAEA;
    border-collapse: collapse;
    border-radius: 5px;
    border-style: hidden;
    box-shadow: 0 0 0 1px #EAEAEA;
}

td {
    padding-left: 20px;
    height: 40px;
    font-size: 14px;
}

#tdTit {
    width: 200px;
    border-right: 1px dotted #EAEAEA;
}


#addBtn {
    margin-left: 40px;
    width: 100px;
    height: 40px;
    background-color: #89A0F2;
    color: white;
    border-radius: 5px;
    border: none;
    cursor: pointer;
}

#petInfoAddBtnWrap {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding-right: 20px;
    width: 66%;
    background-color: #F3F3F3;
}

#petInfoAddBtn {
    width: 80px;
    height: 24px;
    background-color: white;
    border: 1px solid #b1b1b1;
    cursor: pointer;
}

#petInfoBox {
    display: flex;
    align-items: center;
    width: 1054px;
    height: 100px;
    background-color: white;
}

#petImg {
	width: 90px;
    height: 90px;
}

#deleteImg {
    margin-left: 60px;
    cursor: pointer;
}

#petInfoWrap {
    display: flex;
    flex-direction: column;
    margin-left: 40px;
}

#petBirth {
    margin-top: 2px;
    font-size: 12px;
    color: #535353;
}

#petKind {
    margin-top: 8px;
    font-size: 12px;
    color: #E3C1A2;
}

#petInfoBoxWrap {
    border-bottom: 1px dotted #EAEAEA;
}
	</style>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTit">반려동물 정보</div>
        
        <table>
            <tr>
                <td id="tableTit">반려동물 정보 추가하기</td>
                <td id="petInfoAddBtnWrap">
                	<c:url value='/pet/addPet' var="addPetUrl"/>
                	<button id="petInfoAddBtn" onclick="location.href='${addPetUrl}'">추가하기+</button>
                </td>
            </tr>
			
			<c:forEach var="pet" items="${petList}">
	            <tr id="petInfoBoxWrap">
	                <td id="petInfoBox">
		                <c:if test="${pet.images eq null}">
							<img src="/images/petImg.svg" id="petImg"/>
						</c:if>
						<c:if test="${pet.images ne null}">
							<img src="${pet.images[0]}" id="petImg"/>
						</c:if>
	                    <div id="petInfoWrap">
	                        <div id="petName">${pet.name}</div>
	                        <div id="petBirth">${pet.birth}살</div>
	                        <div id="petKind">#${pet.kind.smallCategory}</div>
	                    </div>
	                </td>
	                <td>	
	                	<c:url value='/pet/deletePet' var="deletePetUrl"/>
	                    <img src="/images/delete.svg" id="deleteImg" onclick="location.href='${deletePetUrl}'" />
	                </td>
	            </tr>
         	</c:forEach>
        </table>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>