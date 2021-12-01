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
    
    <style>
    
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

#applyBox {
    display: flex;
    justify-content: space-between;
    padding: 20px;
    width: 1154px;
    box-shadow: rgba(33, 35, 38, 0.1) 0px 10px 10px -10px;
    border-radius: 5px;
    background: white;
}

#applyImgWrap {
    position: relative;
}

#targetLocation {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0px;
    left: 0px;
    transform: (50%, 50%);
    width: 80px;
    height: 30px;
    border-radius: 5px 0px 5px 0px;
    background-color: #757575;
    color: white;
}

#applyInfo {
    display: flex;
    flex-direction: column;
    width: 576px;
    height: 348px;
}

#sitterName {
    margin-bottom: 34px;
    font-size: 24px;
}

#detailTit {
    margin-bottom: 20px;
    font-size: 18px;
}

#detailIntro {
    margin-bottom: 50px;
    font-size: 14px;
    color: #535353;
}

#lookUpDate {
    font-size: 12px;
    color: #C4C4C4;
}

#serviceCaringWrap {
    display: flex;
    flex-direction: column;
    margin-top: auto;
}

#petSitterServiceWrap {
    display: flex;
    margin-bottom: 20px;
}

#petSitterService {
    margin-left: 20px;
    font-size: 14px;
    color: #E3C1A2;
}

#serviceTit {
    font-size: 14px;
}

#caringTit {
    font-size: 14px;
    color: #535353;
}


#caringDateWrap {
    display: flex;
    justify-content: space-between;
}

#caringPetsWrap {
    display: flex;
    align-items: center;
    font-size: 14px;
}

#caringPet {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 20px;
    width: 70px;
    height: 24px;
    color: #E3C1A2;
    border: 1px solid #E3C1A2;
    border-radius: 50px;
}

	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
		
		#refuseBtn {
			margin-right: 40px;
		    width: 100px;
		    height: 40px;
		    background-color: #F28989;
		    border-radius: 5px;
		    color: white;
		    cursor: pointer;
		    border: none;
		}
		
		#acceptBtn {
    width: 100px;
    height: 40px;
    background-color: #89A0F2;
    border-radius: 5px;
    color: white;
    cursor: pointer;
    border: none;
}
		
		#acceptBtnWrap {
			display: flex;
			justify-content: flex-end;
			align-items: center;
			    margin-top: 60px;
			height: 40px;
		}
		
		#profileImg {
			width: 500px;
			height: 348px;
			border: 1px solid #eaeaea;
			border-radius: 5px;
			object-fit: cover;
		}
		
		#pageBg {
			background: #F8F9FA;
		}
	</style>
</head>

<body id="pageBg">
	<c:if test="${updateFailed}">
	    <script> alert('돌보미 등급 조정에 실패했습니다.'); </script>
	 </c:if>
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
        	<c:url value='/manager/refuse' var="refuse">
				<c:param name="applyId" value="${applicantDetail.id}" />
			</c:url>
        	<button id="refuseBtn" onclick="location.href='${refuse}'">거절하기</button>
        	<c:url value='/manager/approval' var="approval">
				<c:param name="applyId" value="${applicantDetail.id}" />
				<c:param name="memberId" value="${applicantDetail.applicant.id}" />
			</c:url>
            <button id="acceptBtn" onClick="location.href='${approval}'">승인하기</button>
        </div>
    </div>
</body>
</html>