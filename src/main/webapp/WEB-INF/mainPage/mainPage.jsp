<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <title>홈</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/mainPage.css"/>
    <link rel="stylesheet" href="/css/main.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <!--  <script src="/js/mainPage.js"></script> -->
    <script src="/js/main.js"></script>
    <script>
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
        	var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView: 'dayGridMonth'
			});
        	calendar.render();
        	
        	var schedules = JSON.parse('${careSchedules}');
        	console.log(schedules);
        	for (key in schedules) {
            	calendar.addEvent({
            		title: schedules[key].sitter.sitter.id,
            		start: schedules[key].startDate,
            		end: schedules[key].endDate
            	})
        	}
		});
    </script>
 
    <style>
    	#reviewImg {
			width: 364px;
			height: 190px;
			object-fit: cover;
			border-radius: 10px 10px 0px 0px;
		}
		
		#targetLocation {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    margin-right: 10px;
		    width: 70px;
		    height: 24px;
		    background-color: #757575;
		    color: white;
		    border-radius: 10px 0px 10px 0px;
		    font-size: 12px;
		    position: absolute;
		    top:2.8%;
		    left:10%;
		    transform: translate(-50%, -50%);
		}
		
		#scheduleTit {
			margin: 80px 0px;
		}
		
		#scheduleWrap {
			margin: 60px 0px;
		}
    </style>
    
</head>

<body>
	<%@include file="../components/header.jsp" %>
	
    <div id="mainPageWrap">
        <div id="bannerWrap">
            <img src="../images/banner.svg" id="banner"/>
            <div id="bannerText">
                <div id="bannerSubTit">핏어펫</div>
                <div id="bannerTit">PIT A PET</div>
                <div id="bannerInfo">반려동물 돌보미 매칭 서비스</div>
            </div>
        </div>
        
        <div id="scheduleWrap">
            <div id="scheduleTit">일정 <span>schedule</span></div>
        	<div id="calendar"></div>
        </div>

        <div id="reviewWrap">
            <div id="reviewTit">이용 후기 <span>review</span></div>
            <div id="reviewMoreBtnWrap">
                <div id="reviewMoreBtn"><a href="<c:url value='/review/listReview'/>">더 보기+</a></div>
            </div>

            <div id="reviewBoxWrap">
            	<c:forEach var="review" items="${reviews}">
            		<div id="reviewBox">
                    	<c:if test="${review.images eq null}">
                    		<img src="../images/reviewNullImg.svg" id="reviewImg"/>
                    	</c:if>
                    	<c:if test="${review.images ne null}">
                    		<img src="${review.images[0]}" id="reviewImg"/>
                    	</c:if>
                    	<div id="reviewBoxInner">
                        	<div id="reviewerDateWrap">
                            	<div id="reviewerTargetWrap">
                                	<div id="targetLocation">${review.careInfo.sitter.sitter.address}</div>
                               	 	<div id="reviewer">${review.careInfo.companion.id} 님</div>
                            	</div>
                            	<div id="reviewDate">${review.writeDate}</div>
                        	</div>
                        	<div id="review">${review.content}</div>
                        	<div id="targetScopeWrap">
                            	<div id="reviewTarget">by ${review.careInfo.sitter.sitter.id}</div>
                            	<div id="scopeWrap">
                                	<img src="../images/star.svg"/>
                                	<div id="scope">${review.rate}</div>
                            	</div>
                        	</div>
                    	</div>
                	</div>
            	</c:forEach>
            </div>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>