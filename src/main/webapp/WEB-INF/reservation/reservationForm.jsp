<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>예약하기</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Righteous&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/footer.css" />
<link rel="stylesheet" href="/css/reservationForm.css" />
<script src="/js/reservationForm.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<style>
#petCheckBoxWrap {
	margin-right: 60px;
	padding: 20px;
	width: 200px;
	height: 260px;
	border-radius: 10px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
	transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
}

#tagCheckWrap {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-top: 20px;
}

#petInfoName {
	margin-top: 20px;
}

#checkbox {
	width: 20px;
	height: 20px;
}

#petPickWrap {
	display: flex;
	padding-bottom: 60px;
	overflow-x: scroll;
}

#petInfoBirth {
	margin-top: 4px;
	font-size: 14px;
	color: #C4C4C4;
}

#petInfoKind {
	font-size: 12px;
	color: #E3C1A2;
}

#petCheckImg {
	width: 200px;
	height: 150px;
	object-fit: cover;
}

#logo {
    z-index: 1;
   	margin-left: 14px;
}
</style>

<script>
	var ableService = JSON.parse('${ableService}');
</script>
</head>

<body>
	<%@include file="../components/header.jsp"%>

	<div id="reservationFormWrap">
		<div id="targetName">${petsitterInfo.sitter.id}반려동물 돌보미</div>
		<div id="subPageTit">반려동물 선택하기</div>
		<div id="petPickWrap">
			<c:forEach var="pet" items="${userPets}">
				<div id="petCheckBoxWrap">
					<c:if test="${pet.images eq null}">
						<img src="/images/petCheckImg.svg" id="petCheckImg" />
					</c:if>
					<c:if test="${pet.images ne null}">
						<img src="${pet.images[0]}" id="petCheckImg" />
					</c:if>
					<div id="petInfoName">${pet.name}</div>
					<div id="petInfoBirth">${pet.birth}</div>
					<div id="tagCheckWrap">
						<div id="petInfoKind">#${pet.kind.smallCategory}</div>
						<input type="checkbox" class="checkbox" />
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="subPageTit">원하는 서비스</div>
		<div id="petSitterService"></div>
		<div id="subPageTit">주의사항</div>
		<textarea placeholder="주의사항을 입력하세요." id="reviewText"></textarea>
		<button id="reservationBtn">예약하기</button>
	</div>

	<div id="footerWrap">
		<div id="footerText">Copyrights © 2021 by 윤김구이. All Rights
			Reserved.</div>
	</div>
</body>
</html>