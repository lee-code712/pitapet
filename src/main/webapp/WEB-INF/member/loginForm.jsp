<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/loginForm.css"/>
</head>

<body id="loginBg">
	<div id="loginWrap">
        <div id="loginTit">PIT A PET</div>
        <input type="text" placeholder="아이디" id="loginId" />
        <input type="text" placeholder="비밀번호" id="loginPwd" />
        <button id="loginBtn">로그인</button>
        <div id="joinLinkWrap">아직 회원이 아니신가요? <a href="#" id="joinLink">회원가입</a></div>
    </div>
</body>
</html>