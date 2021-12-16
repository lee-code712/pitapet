<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>관리자 페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>

    <style>
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

        #pageInner {
            display: flex;
            justify-content: space-between;
            width: 1194px;
        }

        #introPage {
            width: 684px;
        }

        #infooPage {
            width: 444px;
        }

        #locationWrap {
            display: flex;
            align-items: center;
            height: 40px;
            font-size: 12px;
            color: #C4C4C4;
        }

        #locationImg {
            margin-right: 4px;
        }

        #sitterName {
            font-size: 18px;
        }

        #subTit {
            margin: 40px 0px 20px 0px;
            font-size: 16px;
        }

        #service {
            font-size: 14px;
            color: #535353;
        }

        #ablePetWrap {
            display: flex;
            align-items: center;
            height: 40px;
        }

        #ablePet {
            margin-right: 14px;
            padding: 2px 8px;
            font-size: 14px;
            color: #E3C1A2;
            border: 1px solid #E3C1A2;
            border-radius: 50px;
        }

        #certName {
            font-size: 14px;
            color: #535353;
        }

        #btnWrap {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 444px;
            height: 40px;
        }

        #acceptBtn {
            width: 200px;
            height: 40px;
            color: white;
            background: #89A0F2;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        #refuseBtn {
            width: 200px;
            height: 40px;
            color: white;
            background: #F28989;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        #introTit {
            margin: 40px 0px 10px 0px;
            font-size: 18px;
        }

        #introText {
            color: #535353;
            font-size: 14px;
        }

        #certName {
            margin: 20px 0px 80px 0px;
        }

        #applyImg {
            width: 684px;
            height: 476.06px;
            object-fit: scale-down;
            border: 1px solid #EAEAEA;
            border-radius: 5px;
        }

        #certImg {
            width: 444px;
            height: 309.02px;
            object-fit: cover;
        }
        
        #headerWrap {
		    border-bottom: 1px solid rgba(150, 150, 150, 0.2);
		}
		
		#logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
    </style>
</head>

<body>
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
        <div id="pageInner">
            <div id="introPage">
                <c:if test="${applicantDetail.applicant.profileImage eq null}">
					<img src="/images/applyImage.svg" id="applyImg" />
				</c:if>
				<c:if test="${applicantDetail.applicant.profileImage ne null}">
					<img src="${applicantDetail.applicant.profileImage}" id="applyImg" />
				</c:if>
                <div id="introTit">${applicantDetail.introduction}</div>
                <div id="introText">등록 날짜: ${applicantDetail.applyDate}</div>
            </div>
            <div id="infoPage">
                <div id="sitterName">${applicantDetail.applicant.id}</div>
                <div id="locationWrap">
                    <img src="./images/location.svg" id="locationImg"/>
                    ${applicantDetail.applicant.address}
                </div>

                <div id="subTit">▪ 경력</div>
                <div id="service">${applicantDetail.career}</div>

                <div id="subTit">▪ 자격증</div>
                	<c:if test="${applicantDetail.images eq null}">
                    	<img src="./images/certImg.svg" id="certImg"/>
                    </c:if>
                    <c:if test="${applicantDetail.images ne null}">
                    	<img src="${applicantDetail.images[0]}" id="certImg"/>
                    </c:if>
                <div id="certName">${applicantDetail.certification}</div>

                <div id="btnWrap">
                    <c:url value='/manager/updateStatus' var="approvalUrl">
                        <c:param name="applyId" value="${applicantDetail.id}" />
                        <c:param name="memberId" value="${applicantDetail.applicant.id}" />
                        <c:param name='status' value='approval' />
                    </c:url>
                    <button id="acceptBtn" onClick="location.href='${approvalUrl}'">승인</button>
                    <c:url value='/manager/updateStatus' var="refuseUrl">
                        <c:param name="applyId" value="${applicantDetail.id}" />
                        <c:param name="memberId" value="${applicantDetail.applicant.id}" />
                        <c:param name='status' value='refuse' />
                    </c:url>
                    <button id="refuseBtn" onclick="location.href='${refuseUrl}'">거절</button>
                </div>
            </div>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>