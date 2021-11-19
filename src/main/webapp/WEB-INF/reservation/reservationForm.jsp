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
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/reservationForm.css"/>
    <script src="/js/reservationForm.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="reservationFormWrap">
                <div id="targetName">### 반려동물 돌보미</div>
                <div id="periodWrap">
                    <div id="periodInner">
                        <div id="selectPetTit">반려동물 선택하기</div>
                        <div id="petInfoBox">
                            <div id="petInfoBoxInner">
                                <img src="/images/petImg.svg" id="petImg" />
                                <div id="petInfo">
                                    <div id="petNBWrap">
                                        <div id="petName">이름</div>
                                        <div id="petBirth">생년월일</div>
                                    </div>
                                    <div id="petKind"># 대형견</div>
                                </div>
                            </div>
                            <img src="/images/checkBoxOn.svg" id="checkBoxImg" />
                        </div>

                        <div id="petInfoBox">
                            <div id="petInfoBoxInner">
                                <img src="/images/petImg.svg" id="petImg" />
                                <div id="petInfo">
                                    <div id="petNBWrap">
                                        <div id="petName">이름</div>
                                        <div id="petBirth">생년월일</div>
                                    </div>
                                    <div id="petKind"># 대형견</div>
                                </div>
                            </div>
                            <img src="/images/checkBox.svg" id="checkBoxImg" />
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