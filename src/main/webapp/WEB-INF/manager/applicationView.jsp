<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>지원 정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/applyView.css"/>
    
    <style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
	</style>
</head>

<body>
	<div id="headerWrap">
        	<div id="headerInner">
	        	<div id="logoWrap">
	            	<c:url value="/manager/listSitterApply" var="managerMainUrl"/>
	            	<img src="/images/adminRank.svg" />
	        		<div id="logo" onClick="location.href='${managerMainUrl}'">PIT A PET</div>
	        	</div>
         
	         	<div id="gnb">
	         		<c:url value='/member/logout' var="url"/>
	                <button id="headerLoginBtn" onclick="location.href='${url}'">로그아웃</button>
            	</div>
        	</div>
    	</div>

    <div id="pageWrap">
        <div id="pageTit">지원 정보</div>
        <div id="applyBox">
            <div id="applyImgWrap">
                <c:if test="${applicantDetail.applicant.profileImage eq null}">
					<img src="../images/applyNullImg.svg" id="profileImg" />
				</c:if>
				<c:if test="${applicantDetail.applicant.profileImage ne null}">
					<img src="${applicantDetail.applicant.profileImage}" id="profileImg" />
				</c:if>
                <div id="targetLocation">${applicantDetail.applicant.address}</div>
            </div>

            <div id="applyInfo">
                <div id="sitterName">${applicantDetail.applicant.id}</div>
                <div id="detailTit">${applicantDetail.introduction}</div>
                <div id="detailIntro">경력: ${applicantDetail.career} / 자격증: ${applicantDetail.certification}</div>
                <div id="serviceCaringWrap">
                    <div id="caringDateWrap">
                        <div id="lookUpDate">${applicantDetail.applyDate}</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="acceptBtnWrap">
            <button id="acceptBtn">승인하기</button>
        </div>
    </div>
</body>
</html>