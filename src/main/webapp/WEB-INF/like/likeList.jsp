<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>좋아요</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Righteous&display=swap"
	rel="stylesheet">
	<link rel="stylesheet" href="/css/header.css" />
	<link rel="stylesheet" href="/css/footer.css" />

	<style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
		
		#petSitterImg {
			width: 354px;
			height: 242px;
			object-fit: cover;
			border-radius: 10px;
		}
		
		#pageBg {
			background: #F8F9FA;
		}
		
		#petSitterInfoBox {
			background: white;
			box-shadow: rgba(33, 35, 38, 0.1) 0px 10px 10px -10px;
		}
		
		#caringPet {
			display: flex;
			justify-content: center;
			align-items: center;
			margin-right: 20px;
			padding: 4px 8px;
			color: #E3C1A2;
			border: 1px solid #E3C1A2;
			border-radius: 50px;
		}
	
		#headerWrap {
		    border-bottom: 1px solid rgba(150, 150, 150, 0.2);
		}
		
		#likeListWrap {
		    margin: 100px auto 0px auto;
		    width: 1194px;
		}
		
		#pageTit {
		    margin-bottom: 100px;
		    font-size: 24px;
		}
		
		#petSitterInfoBox {
		    position: relative;
		    display: flex;
		    margin-bottom: 60px;
		    padding: 20px;
		    width: 1154px;
		    border-radius: 10px;
		}
		
		#petSitterLocation {
		    position: absolute;
		    top: 11.6%;
		    left: 4.8%;
		    transform: translate(-50%, -50%);
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    margin-right: 10px;
		    width: 70px;
		    height: 24px;
		    background-color: #757575;
		    color: white;
		    border-radius: 5px 0px 5px 0px;
		    font-size: 12px;
		}
		
		#petSitterInfoInner {
		    display: flex;
		    flex-direction: column;
		    margin-left: 20px;
		    width: 800px;
		    height: 242px;
		}
		
		#likeCountWrap {
		    position: absolute;
		    top: 86%;
		    left: 28.6%;
		    transform: translate(-50%, -50%);
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 14px;
		}
		
		#likeCount {
		    padding-left: 4px;
		    font-size: 12px;
		}
		
		#petSitterNameLikeWrap {
		    display: flex;
		    justify-content: space-between;
		    margin-bottom: 20px;
		    width: 780px;
		    height: 20px;
		}
		
		#petSitterName {
		    font-size: 18px;
		}
		
		#petSitterIntro {
		    font-size: 14px;
		}
		
		#petSitterService {
		    font-size: 14px;
		    color: #535353;
		}
		
		#caringDateWrap {
		    display: flex;
		    justify-content: space-between;
		    width: 780px;
		}
		
		#caringPetsWrap {
		    display: flex;
		    align-items: center;
		    font-size: 14px;
		}
		
		#serviceCaringWrap {
		    display: flex;
		    flex-direction: column;
		    margin-top: auto;
		}
		
		#petSitterServiceWrap {
		    display: flex;
		    margin-bottom: 20px;
		}
		
		#petSitterService {
		    margin-left: 20px;
		    font-size: 14px;
		    color: #E3C1A2;
		}
		
		#serviceTit {
		    font-size: 14px;
		}
		
		#caringTit {
		    font-size: 14px;
		    color: #535353;
		}
	</style>
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp"%>

	<div id="likeListWrap">
		<div id="pageTit">좋아요 목록</div>

		<c:forEach var="likeSitter" items="${likeSitterLists}">
			<div id="petSitterInfoBox">
				<img src="${likeSitter.sitter.profileImage}" id="petSitterImg" />
				<div id="petSitterLocation">${likeSitter.sitter.address}</div>
				<div id="likeCountWrap">
					<img src="/images/smallHeart.svg" is="likeCountImg" />
					<div id="likeCount">${likeSitter.like}</div>
				</div>
				<div id="petSitterInfoInner">
					<div id="petSitterNameLikeWrap">
						<div id="petSitterName">${likeSitter.sitter.id}</div>
						<img src="/images/likeOn.svg" is="likeOnImg" />
					</div>
					<div id="petSitterIntro">${likeSitter.notes}</div>
					<div id="serviceCaringWrap">
						<div id="caringDateWrap">
							<div id="caringPetsWrap">
								<c:set var="tags" value="${fn:split(likeSitter.tag,',')}" />
								<c:forEach var="tag" items="${tags}">
									<div id="caringPet">#${tag}</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>
</body>
</html>