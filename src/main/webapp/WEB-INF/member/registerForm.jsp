<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/registerForm.css"/>
</head>

<body id="joinBg">
	<div id="joinWrap">
        <div id="joinTit">PIT A PET</div>

        <div id="idPwdWrap">
            <input type="text" placeholder="아이디" id="joinId" />
            <input type="text" placeholder="비밀번호" id="joinPwd" />
            <input type="text" placeholder="비밀번호 재확인" id="joinCheckPwd" />
        </div>

        <div id="privacyWrap">
            <input type="text" placeholder="이름" id="joinName" />
            <div id="genderWrap">
                <div id="genderInner">
                    <button id="joinMan" class="gender">남성</button>
                    <button id="joinWoman" class="gender">여성</button>
                </div>
            </div>
            <input type="text" placeholder="생년월일" id="joinBirth" />
            <input type="text" placeholder="전화번호 (숫자만 입력)" id="joinPhoneNumber" />
            <input type="text" placeholder="주소" id="joinAddress" />
        </div>

        <button id="joinBtn">가입하기</button>
    </div>
</body>
<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>

<script id="rendered-js" >
    var gender = document.getElementsByClassName("gender");

    function handleClick(event) {
        console.log(event.target);

        console.log(event.target.classList);

        if (event.target.classList[1] === "clicked") {
            event.target.classList.remove("clicked");
        } else {
            for (var i = 0; i < gender.length; i++) {if (window.CP.shouldStopExecution(0)) break;
                gender[i].classList.remove("clicked");
            }window.CP.exitedLoop(0);
            event.target.classList.add("clicked");
        }
    }

    function init() {
        for (var i = 0; i < gender.length; i++) {if (window.CP.shouldStopExecution(1)) break;
            gender[i].addEventListener("click", handleClick);
        }window.CP.exitedLoop(1);
    }

    init();
</script>
</html>