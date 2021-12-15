<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <style>
        @charset "UTF-8";

        #headerWrap {
            border-bottom: 1px solid rgba(150, 150, 150, 0.2);
        }

        #careDiaryPageWrap {
            margin: 100px auto 0px auto;
            width: 1194px;
        }

        #pageTit {
            margin-bottom: 100px;
            font-size: 24px;
        }

        #activeTit,
        #petChoiceTit {
            display: flex;
            align-items: center;
            padding-left: 20px;
            width: 1174px;
            height: 40px;
            background: #F3F3F3;
            border-radius: 5px 5px 0px 0px;
        }

        #activeContentInner,
        #petChoiceContentInner {
            display: flex;
            align-items: center;
            width: 100%;
            height: 40px;
        }

        #activeContentTit,
        #petChoiceContentTit {
            margin-right: 20px;
            padding-left: 20px;
            width: 180px;
            border-right: 2px solid #C4C4C4;
        }

        #radioText {
            margin-left: 20px;
            margin-right: 8px;
        }

        #activeWrap,
        #petChoiceWrap {
            margin-bottom: 80px;
            border: 1px solid #EAEAEA;
            border-radius: 5px;
        }

        #diaryTit {
            display: flex;
            align-items: center;
            padding-left: 20px;
            width: 1174px;
            height: 40px;
            border-bottom: 1px solid #EAEAEA;
        }

        #diaryTitText {
            width: 1154px;
            font-size: 14px;
            border: none;
        }

        #diaryTitText:focus {
            outline: none;
        }

        #diaryContentText {
            padding: 20px;
            width: 1152px;
            height: 400px;
            border: none;
        }

        #diaryContentText:focus {
            outline: none;
        }

        #diaryWrap {
            border: 1px solid #EAEAEA;
            border-radius: 5px;
        }

        #fileReviewBtnWrap {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 40px 0px 0px 0px;
            width: 1194px;
            height: 40px;
        }

        #detailReviewBtn {
            width: 100px;
            height: 40px;
            background: #7AAFFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #detailReviewBtn:focus {
            outline: none;
        }

        #logo {
            z-index: 1;
            margin-left: 14px;
        }
    </style>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="careDiaryPageWrap">
        <div id="pageTit">돌봄 일지</div>

        <div id="petChoiceWrap">
            <div id="petChoiceTit">반려동물 선택</div>
            <div id="petChoiceContentWrap">
                <div id="petChoiceContentInner">
                    <div id="petChoiceContentTit">반려동물</div>
                    <div id="petChoiceContent">
                        <input type="radio" value="1" id="radioText" checked>멍멍이
                        <input type="radio" value="2" id="radioText">멍이
                        <input type="radio" value="3" id="radioText">고양이
                    </div>
                </div>
            </div>
        </div>

        <div id="activeWrap">
            <div id="activeTit">활동</div>
            <div id="activeContentWrap">
                <div id="activeContentInner">
                    <div id="activeContentTit">산책</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>1번
                        <input type="radio" value="2" id="radioText">2번
                        <input type="radio" value="3" id="radioText">3번 이상
                    </div>
                </div>

                <div id="activeContentInner">
                    <div id="activeContentTit">밥</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>적게
                        <input type="radio" value="2" id="radioText">보통
                        <input type="radio" value="3" id="radioText">많이
                    </div>
                </div>

                <div id="activeContentInner">
                    <div id="activeContentTit">배식여부</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>유
                        <input type="radio" value="2" id="radioText">무
                    </div>
                </div>

                <div id="activeContentInner">
                    <div id="activeContentTit">목욕여부</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>유
                        <input type="radio" value="2" id="radioText">무
                    </div>
                </div>
            </div>
        </div>

        <div id="diaryWrap">
            <div id="diaryTit">
                <input type="text" placeholder="제목을 입력하세요." id="diaryTitText"/>
            </div>
            <div id="diaryContent">
                <textarea placeholder="내용을 입력하세요." id="diaryContentText" style="resize: none;"></textarea>
            </div>
        </div>

        <div id="fileReviewBtnWrap">
            <div id="fileWrap">
                <img src="/images/fileImg.svg" id="fileImg"/>
                <input type="file" id="fileBtn"/>
            </div>
            <button id="detailReviewBtn">리뷰 등록</button>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>