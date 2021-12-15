<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>돌봄 일지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
	    window.onload = function(){
	    	var identity = "${sessionScope.identity}";
	    	if (identity == "S") {
		    	var start = new Date("${care.startDate}");
		    	var end = new Date("${care.endDate}");
		    	var needCount =  end.getDate() - start.getDate() + 1;
		    	var writeCount = "${writeCount}";
		    	if (writeCount == null || writeCount == '')
		    		writeCount = 0;
		    	else
		    		writeCount = parseInt(writeCount);   	
		
				if ((needCount - writeCount) > 0) {
					var newInputElement = document.createElement("input");
					$(newInputElement).attr("type", "button");
					$(newInputElement).attr("value", "일지추가");
					$(newInputElement).attr("onClick", "location.href='/care/recordCare'");
					$("#recordBtnDiv").append(newInputElement);
				}
	    	}
	    }
    </script>

    <style>

        #pageBg {
            background-color: #F5F6F7;
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

        #pageListWrap {
            width: 444px;
        }

        #petList {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0px 20px;
            width: 404px;
            height: 40px;
            background-color: white;
            border: 1px solid #F3F3F3;
        }

        #careViewWrap {
            width: 1194px;
            border-right: 2px solid white;
            border-left: 2px solid white;
            border-bottom: 2px solid white;
            border-radius: 5px;
            background-color: #FAFAFA;
        }

        #careViewHeader {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0px 20px;
            width: 1154px;
            height: 40px;
            border-radius: 5px 5px 0px 0px;
            background-color: white;
        }

        #careViewTit {
            font-size: 14px;
        }
        
        #careImg {
        	width: 604.15px;
        	height: 353.3px;
        	object-fit: cover;
        }

        #writeBtn {
            cursor: pointer;
        }

        #careViewBody {
            display: flex;
            justify-content: center;
            flex-direction: column;
            margin: 0 auto;
            padding: 0px 40px 0px 40px;
            width: 604px;
        }

        #writeDateWrap {
            display: flex;
            justify-content: center;
            width: 100%;
        }

        #writeDate {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 40px 0px;
            width: 240px;
            height: 24px;
            background-color: #F3F3F3;
            border-radius: 50px;
            color: #C0C0C0;
            font-size: 12px;
        }

        #careViewBox {
            width: 604px;
            border-radius: 5px;
            background-color: white;
        }

        #careViewBoxInner {
            padding: 20px;
            width: 564px;
        }

        #careViewBoxTit {
            display: flex;
            align-items: center;
            width: 100%;
            height: 40px;
            font-size: 14px;
        }

        #careViewBoxContent {
            display: flex;
            align-items: center;
            width: 100%;
            height: 40px;
            font-size: 14px;
            color: #535353;
        }

        #sitterNameWrap {
            display: flex;
            justify-content: flex-end;
            width: 100%;
        }

        #sitterName {
            font-size: 12px;
            color: #535353;
        }

        #writeTimeWrap {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
            width: 100%;
        }

        #writeTime {
            font-size: 12px;
        }

        #careViewBoxWrap {
            margin-bottom: 80px;
        }

        #careViewBoxWrap:last-child {
            margin-bottom: 20px;
        }

        #period {
            color: #535353;
            font-size: 12px;
        }

        #diaryServiceInfo {
            margin: 8px 0px;
            font-size: 12px;
            color: #89A0F2;
        }

        #careViewBoxTitWrap {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            height: 40px;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #ddd;}

        .dropdown:hover .dropdown-content {display: block;}
    </style>
</head>
<body id="pageBg">
    <%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTit">돌봄일지</div>
            <div id="careViewWrap">
                <div id="careViewHeader">
                    <div id="careViewTit">
                        <c:forEach var="pet" items="${care.carePetList}" varStatus="status">
                            ${pet.name}
                            <c:if test="${!status.last}">
                            , 
                            </c:if>
                        </c:forEach>
                        <span id="period">(보호자: ${care.companion.name}) [${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}]</span></div>
                    <img src="/images/writeBtn.svg" id="writeBtn"/>
                </div>
                <div id="careViewBody">
                    <c:if test="${care.careRecordList[0].writeDate == null}">
                        작성한 돌봄일지가 없습니다.
                    </c:if>
                    <c:if test="${care.careRecordList[0].writeDate != null}">
                        <c:forEach var="careRecord" items="${care.careRecordList}" varStatus="status">
                            <div id="writeDateWrap">
                                <c:set var="writeDate" value="${fn:split(careRecord.writeDate, ' ')[0]}"/>
                                <c:set var="writeTime" value="${fn:split(careRecord.writeDate, ' ')[1]}"/>
                                <div id="writeDate">${fn:split(writeDate, "-")[0]}년 ${fn:split(writeDate, "-")[1]}월 ${fn:split(writeDate, "-")[2]}일</div>
                            </div>

                            <div id="careViewBoxWrap">
                                <div id="careViewBox">
                                    <img src="/images/careNullImg.svg" id="careImg"/>
                                    <div id="careViewBoxInner">
                                        <div id="careViewBoxTitWrap">
                                        <div id="careViewBoxTit">${careRecord.title}</div>
                                            <div class="dropdown">
                                                <img src="/images/update.svg" />
                                                <div class="dropdown-content">
                                                <a href="#">수정하기</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="careViewBoxContent">${careRecord.content}</div>
                                        <div id="diaryServiceInfo">
                                            ★ 돌봄 체크 리스트 </br>
                                            <c:forEach var="careDetail" items="${careRecord.checkList}" varStatus="status">
                                                ${careDetail.carePet.name} - ${careDetail.serviceInfo.title} 
                                                <c:if test="${careDetail.check == 'Y'}">
                                                    ▣
                                                </c:if>
                                                <c:if test="${careDetail.check == 'N'}">
                                                    □
                                                </c:if>
                                                <br/> 
                                            </c:forEach>
                                        </div>
                                        <div id="sitterNameWrap">
                                            <div id="sitterName">by. ${care.sitter.sitter.name} 반려동물 돌보미</div>
                                        </div>
                                    </div>
                                </div>
                                <div id="writeTimeWrap">
                                    <div id="writeTime">${fn:split(writeTime, ':')[0]}시 ${fn:split(writeTime, ':')[0]}분</div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>