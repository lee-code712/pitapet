<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>관리자 페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/sitterApply.css"/>
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

    <div id="pageWrap">
        <div id="pageTit">지원사항 목록</div>

        <table>
            <tr>
                <th>아이디</th>
                <th>신청일</th>
                <th></th>
            </tr>
            <tr>
                <td>pitapet123</td>
                <td>2021-09-19 ~ 2021-09-20</td>
                <td><button id="applyDetailBtn">자세히 보기</button></td>
            </tr>
            <tr>
                <td>pitapet123</td>
                <td>2021-09-19 ~ 2021-09-20</td>
                <td><button id="applyDetailBtn">자세히 보기</button></td>
            </tr>
            <tr>
                <td>pitapet123</td>
                <td>2021-09-19 ~ 2021-09-20</td>
                <td><button id="applyDetailBtn">자세히 보기</button></td>
            </tr>
        </table>
    </div>
</body>
</html>