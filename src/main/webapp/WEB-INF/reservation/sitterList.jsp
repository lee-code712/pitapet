<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
    <title>돌보미 조회</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/sitterList.css"/>
</head>

<body>
	<%@include file="../components/header.jsp" %>
	
    <div id="sitterListWrap">
        <div id="searchToolsWrap">
            <div id="pageTit">돌보미 정보 조회</div>
            <div id="searchTools">
                <select id="choiceBox">
                    <option value="bigDog">대형견</option>
                    <option value="smallDog">소형견</option>
                    <option value="cat">고양이</option>            
                </select>
                <div id="searchWrap">
                    <input type="text" placeholder="검색어를 입력하세요." id="searchText"/>
                    <img src="/images/search.svg" id="searchImg"/>
                </div>
                <div class="dropdown">
                    <img src="/images/hamburgerBar.svg" id="hamburgerBarImg" class="dropbtn"/>
                    <div class="dropdown-content">
                      <a href="#">인기순</a>
                      <a href="#">조회순</a>
                    </div>
                </div>
            </div>
        </div>
        <c:forEach var="petsitter" items="${petSitterList}">
        	<c:url value="/reservation/viewSitterDetail" var="viewUrl">
        		<c:param name="sitterId" value="${petsitter.sitter.id}"/>
        	</c:url>
			<div id="petSitterInfoBox" onClick="location.href='${viewUrl}'">
	            <c:if test="${petsitter.sitter.profileImage eq null}">
					<img src="/images/petSitterNullImg.svg" id="petSitterImg" />
				</c:if>
				<c:if test="${petsitter.sitter.profileImage ne null}">
					<img src="${petsitter.sitter.profileImage}" id="petSitterImg" />
				</c:if>
	            <div id="petSitterLocation">${petsitter.sitter.address}</div>
	            <div id="likeCountWrap">
	                <img src="/images/smallHeart.svg" is="likeCountImg" />
	                <div id="likeCount">${petsitter.like}</div>
	            </div>
	            <div id="petSitterInfoInner">
	                <div id="petSitterNameLikeWrap">
	                    <div id="petSitterName">${petsitter.sitter.id}</div>
	                    <c:set var="findCk" value="false"/>
	                    <c:forEach var="likesitter" items ="${likeSitters}" varStatus="status">
	                    	<c:if test="${findCk == false}">
	                    		<c:choose>
	                    			<c:when test="${likesitter.likeSitter.sitter.id == petsitter.sitter.id}">
	                    				<img src="/images/likeOn.svg" is="likeOnImg" />
		                    			<c:set var="findCk" value="true"/>
	                    			</c:when>
	                    			<c:otherwise>
	                    				<c:if test="${status.last}">
	                    					<img src="/images/like.svg" is="likeImg" />
	                    				</c:if>
	                    			</c:otherwise>
		                    	</c:choose>
	                    	</c:if>
	                    </c:forEach>
	                </div>
	                <div id="petSitterIntro">${petsitter.notes}</div>
	                <div id="serviceCaringWrap">
	                    <div id="caringDateWrap">
	                        <div id="caringPetsWrap">
	                            <c:set var="tags" value="${fn:split(petsitter.tag,',')}" />
	                            <c:forEach var="tag" items="${tags}">
	                            	<div id="caringPet">#${tag}</div>
	                            </c:forEach>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>        
        </c:forEach>
                
        <div id="paging">
        	<div id="pagingInner">
        		<c:set var="currentPage" value="${pageInfo.get(1)}"/>
        		<c:set var="totalPage" value="${pageInfo.get(0)}"/>
        		<c:if test="${currentPage > 1}">
        			<c:url value='/reservation/listSitter' var="preUrl">
        				<c:param name="currentPage" value="${currentPage - 1}"/>
        			</c:url>
	        		<a href="${preUrl}" id="pre">이전</a>
        		</c:if>
	        	<c:forEach var="i" begin="1" end="${totalPage}">
	        		<c:choose>
	        			<c:when test="${i == currentPage}">
	        				<div id="numOn">${i}</div>
	        			</c:when>
	        			<c:otherwise>
	        				<c:url value='/reservation/listSitter' var="url">
        						<c:param name="currentPage" value="${i}"/>
        					</c:url>
	        				<div id="num" onClick="location.href='${url}'">${i}</div>
	        			</c:otherwise>
	        		</c:choose>
	        	</c:forEach>
	        	<c:if test="${currentPage < totalPage}">
        			<c:url value='/reservation/listSitter' var="postUrl">
        				<c:param name="currentPage" value="${currentPage + 1}"/>
        			</c:url>
	        		<a href="${postUrl}" id="next">다음</a>
        		</c:if>
        	</div>
        </div>
        
        <div id="recommendTit">추천</div>
        
        <div id="reviewBoxWrap">
            <div id="reviewBox">
                <img src="/images/reviewNullImg.svg" id="reviewImg"/>
                <div id="reviewBoxInner">
                    <div id="reviewerDateWrap">
                        <div id="reviewerTargetWrap">
                            <div id="targetLocation">상월곡</div>
                            <div id="reviewer">###보호자님</div>
                        </div>
                        <div id="reviewDate">2021-11-01</div>
                    </div>
                    <div id="review">(리뷰 작성)</div>
                    <div id="targetScopeWrap">
                        <div id="reviewTarget">about ###반려동물 돌보미</div>
                        <div id="scopeWrap">
                            <img src="/images/star.svg"/>
                            <div id="scope">5.0</div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="reviewBox">
                <img src="/images/reviewNullImg.svg" id="reviewImg"/>
                <div id="reviewBoxInner">
                    <div id="reviewerDateWrap">
                        <div id="reviewerTargetWrap">
                            <div id="targetLocation">상월곡</div>
                            <div id="reviewer">###보호자님</div>
                        </div>
                        <div id="reviewDate">2021-11-01</div>
                    </div>
                    <div id="review">(리뷰 작성)</div>
                    <div id="targetScopeWrap">
                        <div id="reviewTarget">about ###반려동물 돌보미</div>
                        <div id="scopeWrap">
                            <img src="/images/star.svg"/>
                            <div id="scope">5.0</div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="reviewBox">
                <img src="/images/reviewNullImg.svg" id="reviewImg"/>
                <div id="reviewBoxInner">
                    <div id="reviewerDateWrap">
                        <div id="reviewerTargetWrap">
                            <div id="targetLocation">상월곡</div>
                            <div id="reviewer">###보호자님</div>
                        </div>
                        <div id="reviewDate">2021-11-01</div>
                    </div>
                    <div id="review">(리뷰 작성)</div>
                    <div id="targetScopeWrap">
                        <div id="reviewTarget">about ###반려동물 돌보미</div>
                        <div id="scopeWrap">
                            <img src="/images/star.svg"/>
                            <div id="scope">5.0</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
    <nav class="menu-box-1">
        <div class="button">
            <div id="btn">
                <img src="/images/leftSlide.svg" />
            </div>
        </div>
        <div id="category">
            <div>카테고리</div> 
        </div>
        <ul>
          <li>
            <a href="#">대분류</a>
            <ul>
              <li>
                <a href="#">소분류</a>
              </li>
              <li>
                <a href="#">소분류</a>
              </li>
              <li>
                <a href="#">소분류</a>
              </li>
            </ul>
          </li>
          <li>
            <a href="#">대분류</a>
            <ul>
              <li>
                <a href="#">소분류</a>
              </li>
              <li>
                <a href="#">소분류</a>
              </li>
              <li>
                <a href="#">소분류</a>
              </li>
            </ul>
          </li>
          <li>
            <a href="#">대분류</a>
            <ul>
              <li>
                <a href="#">소분류</a>
              </li>
              <li>
                <a href="#">소분류</a>
              </li>
              <li>
                <a href="#">소분류</a>
              </li>
            </ul>
          </li>
        </ul>
    </nav>
</body>
</html>
