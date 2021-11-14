<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>돌봄 일지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/careDiary.css"/>
</head>
<body>
<div id="headerWrap">
        <div id="headerInner">
            <div id="logo">PIT A PET</div>
            <div id="gnb">
                <div id="sitterListLink">돌보미 조회</div>
                <div id="reviewLink">이용 후기</div>
                <div id="likeLink">좋아요</div>
                <div id="myPageLink">마이페이지</div>
                <button id="headerLoginBtn">로그인</button>
            </div>
        </div>
    </div>

    <div id="careDiaryPageWrap">
        <div id="pageTit">돌봄 일지</div>
        <div id="careDiaryInner">
            <div id="careDiaryPetName">###보호자님의 &&&반려동물</div>
            <div id="careDiaryContent">
            <div id="fullDateWrap">
                <div id="fullDate">2021년 9월 17일</div>
            </div>
                <div id="careDiaryCardWrap">
                    <div id="careDiaryCard">
                        <div id="careDiaryPetSitterName">
                            ###반려동물 돌보미
                        </div>
                        <img src="../images/diaryImg.svg" id="diaryImg" />
                        <div id="diaryContentWrap">
                            <div id="diaryTit">(제목)</div>
                            <div id="diaryContent">(내용)</div>
                            <div id="diaryServiceInfo">* 산책 3번 / 밥 많이 먹음 / 배식O / 목욕O</div>
                        </div>
                    </div>
                    <div id="diaryDateWarp">
                        <div id="diaryDate">오후 10:29</div>
                    </div>
                </div>

                <div id="careDiaryCardWrap">
                    <div id="careDiaryCard">
                        <div id="careDiaryPetSitterName">
                            ###반려동물 돌보미
                        </div>
                        <img src="../images/diaryImg.svg" id="diaryImg" />
                        <div id="diaryContentWrap">
                            <div id="diaryTit">(제목)</div>
                            <div id="diaryContent">(내용)</div>
                            <div id="diaryServiceInfo">* 산책 3번 / 밥 많이 먹음 / 배식O / 목욕O</div>
                        </div>
                    </div>
                    <div id="diaryDateWarp">
                        <div id="diaryDate">오후 10:29</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>