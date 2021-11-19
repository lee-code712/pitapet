<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <title>돌보미 상세정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/sitterDetailView.css"/>
    <script src="/js/sitterDetailView.js"></script>
    <link rel="stylesheet" href="/css/main.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <!--  <script src="/js/mainPage.js"></script> -->
    <script src="/js/main.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <script src="/js/reservationForm.js"></script>
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
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="detailViewWrap">
        <div id="pageTit">돌보미 상세정보</div>
        <div id="detailWrap">
            <div id="detailBox">
                <div id="detailPetSitterName">${sitterInfo.sitter.id}</div>
                <img src="/images/detailNulImg.svg" />
                <div id="targetLocation">${sitterInfo.sitter.address}</div>
                <div id="likeCountWrap">
                    <img src="/images/smallHeart.svg" id="smallHeart" />
                    <div id="likeCount">12</div>
                </div>
                <div id="detailInner">
                	<c:set var="tags" value="${fn:split(sitterInfo.tag,',')}" />
                    <div id="detailTit">
	                	<c:forEach var="tag" items="${tags}">
	                		#${tag} 
	                    </c:forEach>
	                </div>
                    <div id="detailIntro">${sitterInfo.notes}</div>
                    <div id="serviceCaringWrap">
                        <div id="petSitterServiceWrap">
                            <div id="serviceTit">제공 서비스</div> 
                            <div id="petSitterService">
                            	<c:forEach var="service" items="${sitterInfo.myApplyInfo.services}" varStatus="status">
                            		${service.title}
                            		<c:if test="${!status.last}">, </c:if>
                            	</c:forEach>
                            </div>
                        </div>
                        <div id="caringDateWrap">
                            <div id="caringPetsWrap">
                                <div id="caringTit">돌봄 가능 반려동물</div>
                                <c:forEach var="kind" items="${sitterInfo.myApplyInfo.kinds}">
                            		<div id="caringPet">${kind.smallCategory}</div>
                            	</c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div id="ableTimeWrap">
                  <div id="ableTimeTit">예약 가능일</div>
                  <div id="calendar"></div>
                </div>
                
                <div id="askPeriodTit">언제 맡기시나요?</div>
                <div id="pickPeriodWrap">
                    <img src="/images/calendar.svg" id="calendarImg" />
                    <div id="pickPeriodInner">
                        <input type="text" name="fromDate" id="fromDate" placeholder="체크인 날짜" onfocus="this.blur()" />
                        <img src="/images/arrow.svg"/>
                        <input type="text" name="toDate" id="toDate" placeholder="체크아웃 날짜" onfocus="this.blur()" />
                    </div>
                </div>
                <div id="carePriceKind">*1박케어: 32,000원 / 데이케어: 22,000원</div>
                <div id="carePrice">1박 32,000원</div>
                        
                <c:url value="/reservation/reserve" var="reserveUrl">
                	<c:param name="sitterId" value="${param.sitterId}"/>
                </c:url>
                <button id="reservationBtn" onClick="location.href='${reserveUrl}'">예약하기</button>
            </div>
        </div>

        <div id="detailReviewTit">리뷰작성</div>
        <div id="detailReviewWrap">
            <textarea placeholder="리뷰를 작성해주세요." id="reviewText"></textarea>
            <div id="fileReviewBtnWrap">
                <div id="fileWrap">
                    <img src="/images/fileImg.svg" id="fileImg"/>
                    <input type="file" id="fileBtn"/>
                </div>
                <button id="detailReviewBtn">리뷰 등록</button>
            </div>
        </div>

        <div id="reviewTitWrap">
            <div id="reviewTit">이용 후기</div>
            <div id="scopeWrap">
                <img src="/images/star.svg"/>
                <div id="scope">5.0</div>
            </div>
        </div>

        <div id="petSitterInfoBox">
            <img src="/images/petSitterNullImg.svg" id="petSitterImg" />
            <div id="petSitterLocation">상월곡</div>
            <div id="petSitterInfoInner">
                <div id="petSitterNameLikeWrap">
                    <div id="petSitterName">### 반려동물 보호자님</div>
                    <div id="lookUpDate">2021-11-01</div>
                </div>
                <div id="petSitterIntro">(후기)</div>
                <div id="serviceCaringWrap">
                    <div id="caringDateWrap">
                        <div id="caringPetsWrap">
                            <div id="caringTit">about ### 반려동물 돌보미</div>
                        </div>
                        <div id="scopeWrap">
                            <img src="/images/star.svg"/>
                            <div id="scope">5.0</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="petSitterInfoBox">
            <img src="/images/petSitterNullImg.svg" id="petSitterImg" />
            <div id="petSitterLocation">상월곡</div>
            <div id="petSitterInfoInner">
                <div id="petSitterNameLikeWrap">
                    <div id="petSitterName">### 반려동물 보호자님</div>
                    <div id="lookUpDate">2021-11-01</div>
                </div>
                <div id="petSitterIntro">(후기)</div>
                <div id="serviceCaringWrap">
                    <div id="caringDateWrap">
                        <div id="caringPetsWrap">
                            <div id="caringTit">about ### 반려동물 돌보미</div>
                        </div>
                        <div id="scopeWrap">
                            <img src="/images/star.svg"/>
                            <div id="scope">5.0</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>