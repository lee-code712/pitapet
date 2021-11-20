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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
   	<meta http-equiv="X-UA-Compatible" content="ie=edge"> 
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 	
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
        #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
		
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
		
		#demo {
			z-index: 0;
		}
        	
       	.carousel-inner > .carousel-item > img{ 
           	width: 300px; height: 800px;
           	object-fit: cover;
        }
           
        #bannerText {
		    position: absolute;
		    top: 620px;
		    left: 45%;
		    transform: (-50%, 0%);
		}
		
		#bannerTit {
		    font-family: 'Righteous', cursive;
		    font-size: 48px;
		    color: black;
		}
		
		#bannerSubTit {
		    padding-left: 68px;
		    color: #FB7D71;
		    font-size: 24px;
		}
		
		#bannerInfo {
		    margin: 10px 0px 0px 4px;
		    color: #535353;
		}
	</style> 
</head>

<body>
		<%@include file="../components/header.jsp" %>
	
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
        </script> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> 
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 
        <script> 
	        $('.carousel').carousel({ 
	            interval: 500 //기본 5초 
	        }) 
        </script>
        
        <div id="demo" class="carousel slide" data-ride="carousel">
        	<div class="carousel-inner"> <!-- 슬라이드 쇼 -->
	            <div class="carousel-item active"> <!--가로--> 
		            <img class="d-block w-100" src="/images/bannerImg1.svg?auto=compress&cs=tinysrgb&h=650&w=940" alt="First slide"> 
				 	<div id="bannerText">
			        	<div id="bannerSubTit">핏어펫</div> 
			           	<div id="bannerTit">PIT A PET</div>
			           	<div id="bannerInfo">반려동물 돌보미 매칭 서비스</div> 
					</div>
	            </div> 
	            
	            <div class="carousel-item"> 
	           		<img class="d-block w-100" src="/images/bannerImg2.svg" alt="Second slide"> 
	           	</div>
	                          
	            <div class="carousel-item"> 
	            	<img class="d-block w-100" src="https://images.pexels.com/photos/2544554/pexels-photo-2544554.jpeg?auto=compress&cs=tinysrgb&h=650&w=940" alt="Third slide"> 
	            </div> <!-- / 슬라이드 쇼 끝 --> <!-- 왼쪽 오른쪽 화살표 버튼 --> 
	            
	            <a class="carousel-control-prev" href="#demo" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> 
	                                <!-- <span>Previous</span> -->
	            </a> 
	            <a class="carousel-control-next" href="#demo" data-slide="next"> 
	            <span class="carousel-control-next-icon" aria-hidden="true"></span> 
	                                 <!-- <span>Next</span> --> 
	            </a> <!-- / 화살표 버튼 끝 --> <!-- 인디케이터 --> 
	            <ul class="carousel-indicators"> 
	            	<li data-target="#demo" data-slide-to="0" class="active"></li>
	               	<!--0번부터시작--> <li data-target="#demo" data-slide-to="1"></li> 
	                <li data-target="#demo" data-slide-to="2"></li> 
	            </ul> <!-- 인디케이터 끝 --> 
       		</div>
       </div>
       
       <div id="mainPageWrap">                             
        <div id="scheduleWrap">
            <div id="scheduleTit">일정 <span>schedule</span></div>
        	<div id="calendar"></div>
        </div>

        <div id="reviewWrap">
            <div id="reviewTit">이용 후기 <span>review</span></div>
            <div id="reviewMoreBtnWrap">
            	<c:url value="/review/listReview" var="reviewUrl"/>
                <div id="reviewMoreBtn" onClick="location.href='${reviewUrl}'">더 보기+</div>
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