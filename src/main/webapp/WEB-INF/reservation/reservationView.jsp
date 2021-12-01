<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 상세 정보</title>
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/footer.css" />
<style>
#pageWrap {
    margin: 100px auto 0px auto;
    width: 1194px;
}

#pageTit {
    margin-bottom: 100px;
    font-size: 24px;
}
	
	#headerWrap {
	border-bottom: 1px solid rgba(150, 150, 150, 0.2);
}

table {
	width: 1194px;
	border: 1px solid #EAEAEA;
}

#sitterInfoWrap {
	margin-bottom: 80px;
}

#tableTit {
	background: #F3F3F3;
	height: 40px;
}

tr, td {
	border: 1px solid #EAEAEA;
	text-align: center;
	height: 40px;
}

#logo {
	z-index: 1;
	margin-left: 14px;
}

#pageSubTit {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
	height: 40px;
}

#soleImg {
	margin-right: 10px;
}
</style>
</head>
<body>
	<%@include file="../components/header.jsp"%>
	
	<div id="pageWrap">
	<div id="pageTit">돌봄 완료</div>
	<div id="pageSubTit"><img src="/images/sole.svg" id="soleImg" /> 반려동물 돌보미 정보</div>
	<table id="sitterInfoWrap">
		<tr id="tableTit">
			<th>돌보미 이름</th>
			<th>완료 상태</th>
			<th>돌봄 기간</th>
			<th>금액</th>
			<th>유의사항</th>
		</tr>
		<tr>
			<td>${reservationInfo.sitter.sitter.name}</td>
			<td>예약완료</td>
			<td>${fn:split(reservationInfo.startDate, ' ')[0]}~${fn:split(reservationInfo.endDate, ' ')[0]}</td>
			<td>${reservationInfo.totalPrice}</td>
			<td>${reservationInfo.notes}</td>
		</tr>
		</table>
		
		<div id="pageSubTit"><img src="/images/sole.svg" id="soleImg"/> 반려동물 정보</div>
		<table>
			<tr id="tableTit">
				<th>반려동물 이름</th>
				<th>받을 서비스</th>
				<th>완료 상태</th>
			</tr>
			<c:forEach var="careList" items="${reservationInfo.careList}">
			<tr>
				<td>${careList.carePet.name}</td>
				<td>${careList.serviceInfo.title}</td>
				<c:if test="${careList.check eq null}">
					<td>미완료</td>
				</c:if>
				<c:if test="${careList.check ne null}">
					<td>${careList.check}</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>