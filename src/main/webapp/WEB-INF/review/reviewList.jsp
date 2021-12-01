<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
</head>

<body id="reviewBg">
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
               		<div id="reviewBoxContent">
                    	<div id="reviewerDateWrap">
                           	<div id="reviewer">${review.careInfo.companion.id} 님</div>
                        	<div id="reviewDate">${fn:split(review.writeDate, ' ')[0]}</div>
                    	</div>
                    	<div id="locationWrap">
                    		<img src="/images/location.svg" id="locationImg"/>
                    		${review.careInfo.sitter.sitter.address}
                    	</div>
                    	<div id="review">${review.content}</div>
                   	</div>
                   	<div id="targetScopeWrap">
                       	<div id="reviewTarget">about ${review.careInfo.sitter.sitter.id}</div>
                       	<div id="scopeWrap">
                           	<img src="../images/star.svg"/>
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