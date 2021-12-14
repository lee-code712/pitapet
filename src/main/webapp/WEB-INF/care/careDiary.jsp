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
    <link rel="stylesheet" href="/css/careDiary.css"/>
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
					$("#recordBtnDiv").append(newInputElement);
				}
	    	}
	    }
    </script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="careDiaryPageWrap">
        <div id="pageTit">돌봄 일지</div>
        <div id="careDiaryInner">
            <div id="careDiaryPetName">
            	<c:forEach var="pet" items="${care.carePetList}" varStatus="status">
            		${pet.name}
            		<c:if test="${!status.last}">
            		, 
            		</c:if>
            	</c:forEach>
            	 돌봄일지 [${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}]
            	 (보호자: ${care.companion.name})
            	<div id="recordBtnDiv"></div>
            </div>
            <div id="careDiaryContent">
            <c:if test="${care.careRecordList[0].writeDate == null}">
            	작성한 돌봄일지가 없습니다.
            </c:if>
            <c:if test="${care.careRecordList[0].writeDate != null}">
	            <c:forEach var="careRecord" items="${care.careRecordList}" varStatus="status">
	            	<div id="fullDateWrap">
	            		<c:set var="writeDate" value="${fn:split(careRecord.writeDate, ' ')[0]}"/>
	            		<c:set var="writeTime" value="${fn:split(careRecord.writeDate, ' ')[1]}"/>
	                	<div id="fullDate">
	                		${fn:split(writeDate, "-")[0]}년 ${fn:split(writeDate, "-")[1]}월 ${fn:split(writeDate, "-")[2]}일
	                	</div>
	            	</div>
	                <div id="careDiaryCardWrap">
	                    <div id="careDiaryCard">
	                        <div id="careDiaryPetSitterName">
	                        	작성자: ${care.sitter.sitter.name} 돌보미
	                        </div>
	                        <img src="/images/diaryImg.svg" id="diaryImg" />
	                        <div id="diaryContentWrap">
	                            <div id="diaryTit">${careRecord.title}</div>
	                            <div id="diaryContent">${careRecord.content}</div>
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
	                        </div>
	                    </div>
	                    <div id="diaryDateWarp">
	                        <div id="diaryDate">${fn:split(writeTime, ':')[0]}시 ${fn:split(writeTime, ':')[0]}분</div>
	                    </div>
	                </div>
	            </c:forEach>
            </c:if>
            </div>
        </div>
    </div>
</body>
</html>