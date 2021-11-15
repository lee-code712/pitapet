<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
    <title>예약하기</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/reservationForm.css"/>
    <script src="../js/reservationForm.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="reservationFormWrap">
                <div id="targetName">### 반려동물 돌보미</div>
                <div id="periodWrap">
                    <div id="ableTimeWrap">
                        <table class="scriptCalendar">
                            <thead>
                                <tr>
                                    <div id="ableTimeTit">예약 가능일</div>
                                </tr>
                                <tr>
                                    <td onClick="prevCalendar();" style="cursor:pointer;"><img src="../images/calLeft.svg" /></td>
                                    <td colspan="5" id="calYearMonth">
                                        <span id="calYear">YYYY</span>년
                                        <span id="calMonth">MM</span>월
                                    </td>
                                    <td onClick="nextCalendar();" style="cursor:pointer;"><img src="../images/calRight.svg" /></td>
                                </tr>
                                <tr>
                                    <td>일</td><td>월</td><td>화</td><td>수</td><td>목</td><td>금</td><td>토</td>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>

                    <div id="periodInner">
                        <div id="askPeriodTit">언제 맡기시나요?</div>
                        <div id="pickPeriodWrap">
                            <img src="../images/calendar.svg" id="calendarImg" />
                            <div id="pickPeriodInner">
                                <input type="text" name="fromDate" id="fromDate" placeholder="체크인 날짜" onfocus="this.blur()" />
                                <img src="../images/arrow.svg"/>
                                <input type="text" name="toDate" id="toDate" placeholder="체크아웃 날짜" onfocus="this.blur()" />
                            </div>
                        </div>
                        <div id="carePriceKind">*1박케어: 32,000원 / 데이케어: 22,000원</div>
                        <div id="carePrice">1박 32,000원</div>
                        <div id="selectPetTit">반려동물 선택하기</div>
                        <div id="petInfoBox">
                            <div id="petInfoBoxInner">
                                <img src="../images/petImg.svg" id="petImg" />
                                <div id="petInfo">
                                    <div id="petNBWrap">
                                        <div id="petName">이름</div>
                                        <div id="petBirth">생년월일</div>
                                    </div>
                                    <div id="petKind"># 대형견</div>
                                </div>
                            </div>
                            <img src="../images/checkBoxOn.svg" id="checkBoxImg" />
                        </div>

                        <div id="petInfoBox">
                            <div id="petInfoBoxInner">
                                <img src="../images/petImg.svg" id="petImg" />
                                <div id="petInfo">
                                    <div id="petNBWrap">
                                        <div id="petName">이름</div>
                                        <div id="petBirth">생년월일</div>
                                    </div>
                                    <div id="petKind"># 대형견</div>
                                </div>
                            </div>
                            <img src="../images/checkBox.svg" id="checkBoxImg" />
                        </div>
                    </div>
                </div>
                <div id="subPageTit">원하는 서비스</div>
                <textarea placeholder="원하는 서비스를 작성하세요." id="reviewText"></textarea>

                <div id="subPageTit">주의사항</div>
                <textarea placeholder="주의사항을 입력하세요." id="reviewText"></textarea>
                <button id="reservationBtn">예약하기</button>
            </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>