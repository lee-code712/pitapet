<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <title>홈</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/mainPage.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="../js/mainPage.js"></script>
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
            <div id="calendarWrap">
                <div id="calendarDateWrap">
                    <div class="cal_top">
                        <a href="#" id="movePrevMonth"><span id="prevMonth" class="cal_tit"><img src="../images/prevImage.svg" /></span></a>
                        <div>
                            <span id="cal_top_year"></span>
                            <span id="cal_top_month"></span>
                        </div>
                        <a href="#" id="moveNextMonth"><span id="nextMonth" class="cal_tit"><img src="../images/nextImage.svg" /></span></a>
                    </div>
                    <div id="cal_top_date"></div>
                    <div id="calendarDay">
                        (<span id="cal_top_dayName"></span>요일)
                    </div>
                </div>
                <div id="cal_tab" class="cal"></div>
            </div>
        </div>

        <div id="reviewWrap">
            <div id="reviewTit">이용 후기 <span>review</span></div>
            <div id="reviewMoreBtnWrap">
                <div id="reviewMoreBtn">더 보기+</div>
            </div>

            <div id="reviewBoxWrap">
                <div id="reviewBox">
                    <img src="../images/reviewNullImg.svg" id="reviewImg"/>
                    <div id="reviewBoxInner">
                        <div id="reviewerDateWrap">
                            <div id="reviewerTargetWrap">
                                <div id="targetLocation">상월곡</div>
                                <div id="reviewer">###보호자님</div>
                            </div>
                            <div id="reviewDate">2021-11-01</div>
                        </div>
                        <div id="review">(리뷰 작성)</div>
                        <div id="targetScopeWrap">
                            <div id="reviewTarget">about ###반려동물 돌보미</div>
                            <div id="scopeWrap">
                                <img src="../images/star.svg"/>
                                <div id="scope">5.0</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="reviewBox">
                    <img src="../images/reviewNullImg.svg" id="reviewImg"/>
                    <div id="reviewBoxInner">
                        <div id="reviewerDateWrap">
                            <div id="reviewerTargetWrap">
                                <div id="targetLocation">상월곡</div>
                                <div id="reviewer">###보호자님</div>
                            </div>
                            <div id="reviewDate">2021-11-01</div>
                        </div>
                        <div id="review">(리뷰 작성)</div>
                        <div id="targetScopeWrap">
                            <div id="reviewTarget">about ###반려동물 돌보미</div>
                            <div id="scopeWrap">
                                <img src="../images/star.svg"/>
                                <div id="scope">5.0</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="reviewBox">
                    <img src="../images/reviewNullImg.svg" id="reviewImg"/>
                    <div id="reviewBoxInner">
                        <div id="reviewerDateWrap">
                            <div id="reviewerTargetWrap">
                                <div id="targetLocation">상월곡</div>
                                <div id="reviewer">###보호자님</div>
                            </div>
                            <div id="reviewDate">2021-11-01</div>
                        </div>
                        <div id="review">(리뷰 작성)</div>
                        <div id="targetScopeWrap">
                            <div id="reviewTarget">about ###반려동물 돌보미</div>
                            <div id="scopeWrap">
                                <img src="../images/star.svg"/>
                                <div id="scope">5.0</div>
                            </div>
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