<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>이용 후기</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/review.css"/>
    
    <style>
	    #reviewImg {
			width: 364px;
			height: 190px;
			object-fit: cover;
			border-radius: 10px 10px 0px 0px;
		}
		
		#targetLocation {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    margin-right: 10px;
		    width: 70px;
		    height: 24px;
		    background-color: #757575;
		    color: white;
		    border-radius: 10px 0px 10px 0px;
		    font-size: 12px;
		    position: absolute;
		    top:2.8%;
		    left:10%;
		    transform: translate(-50%, -50%);
		}
	</style>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="reviewPageWrap">
        <div id="pageTit">이용 후기</div>
        <div id="reviewBoxWrap">
           <c:forEach var="review" items="${reviews}"> 
            <div id="reviewBox">
                <c:if test="${review.images eq null}">
                    	<img src="/images/reviewNullImg.svg" id="reviewImg"/>
                </c:if>
                 <c:if test="${review.images ne null}">
                    	<img src="${review.images[0]}" id="reviewImg"/>
                </c:if>
                <div id="reviewBoxInner">
                    <div id="reviewerDateWrap">
                        <div id="reviewerTargetWrap">
                            <div id="targetLocation">${review.careInfo.sitter.sitter.address}</div>
                            <div id="reviewer">${review.careInfo.companion.id} 님</div>
                        </div>
                        <div id="reviewDate">${review.writeDate}</div>
                    </div>
                    <div id="review">${review.content}</div>
                    <div id="targetScopeWrap">
                        <div id="reviewTarget">by ${review.careInfo.sitter.sitter.id}</div>
                        <div id="scopeWrap">
                            <img src="/images/star.svg"/>
                            <div id="scope">${review.rate}</div>
                        </div>
                    </div>
                </div>
            </div>
		  </c:forEach>	
        </div>
    </div>
</body>
</html>