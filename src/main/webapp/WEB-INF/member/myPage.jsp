<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>마이페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/myPage.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="../js/myPage.js"></script>
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
        <div id="pageTitWrap">
            <div id="pageTit">마이페이지</div>
            <div id="changeBtnWrap">
                <ul class="tabs">
                    <li class="tab-link current" data-tab="tab-1">보호자</li>
                    <li class="tab-link" data-tab="tab-2">돌보미</li>
                  </ul>
            </div>
        </div>

        <div class="container">
            <div id="tab-1" class="tab-content current">
                <div id="myInfoWrap">
                    <div id="myInfoTitWrap">
                        <div id="myInfoTit">내 정보</div>
                        <img src="../images/infoEdit.svg" id="editBtn" />
                    </div>
                    <img src="../images/myPageNullImg.svg" />
                    <div id="myInfo">
                        <div id="name">(이름)</div>
                        <div id="phone">(휴대폰 번호)</div>
                        <div id="myPageBtnWrap">
                            <button id="petInfoBtn">반려동물 정보</button>
                            <button id="applySitterBtn">돌보미 지원</button>
                        </div>
                    </div>
                </div>

                <div id="reservationInfoWrap">
                    <table>
                        <tr>
                            <th>이름</th>
                            <th>예약일</th>
                            <th>완료상태</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>###반려동물 돌보미</td>
                            <td>2021-09-19 ~ 2021-09-20</td>
                            <td>돌봄완료</td>
                            <td>
                                <button id="careBtn">돌봄일지</button>
                                <button id="reviewBtn">리뷰작성</button>
                            </td>
                        </tr>
                        <tr>
                            <td>###반려동물 돌보미</td>
                            <td>2021-09-19 ~ 2021-09-20</td>
                            <td>진행중</td>
                            <td>
                                <button id="careBtn">돌봄일지</button>
                            </td>
                        </tr>
                        <tr>
                            <td>###반려동물 돌보미</td>
                            <td>2021-09-19 ~ 2021-09-20</td>
                            <td>예약완료</td>
                            <td>
                                <button id="cancelBtn">취소하기</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tab-2" class="tab-content">
                <div id="myInfoWrap">
                    <div id="myInfoTitWrap">
                        <div id="myInfoTit">내 정보</div>
                        <img src="../images/infoEdit.svg" id="editBtn" />
                    </div>
                    <img src="../images/myPageNullImg.svg" />
                    <div id="myInfo">
                        <div id="name">(이름)</div>
                        <div id="phone">(휴대폰 번호)</div>
                        <div id="myPageBtnWrap">
                            <button id="certBtn">돌보미 인증</button>
                            <button id="registerInfoBtn">돌보미 정보 등록</button>
                        </div>
                    </div>
                </div>

                <div id="reservationInfoWrap">
                    <table>
                        <tr>
                            <th>이름</th>
                            <th>예약일</th>
                            <th>완료상태</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>###반려동물 돌보미</td>
                            <td>2021-09-19 ~ 2021-09-20</td>
                            <td>돌봄완료</td>
                            <td>
                                <button id="careBtn">돌봄일지</button>
                            </td>
                        </tr>
                        <tr>
                            <td>###반려동물 돌보미</td>
                            <td>2021-09-19 ~ 2021-09-20</td>
                            <td>진행중</td>
                            <td>
                                <button id="careBtn">돌봄일지</button>
                            </td>
                        </tr>
                        <tr>
                            <td>###반려동물 돌보미</td>
                            <td>2021-09-19 ~ 2021-09-20</td>
                            <td>예약완료</td>
                            <td>
                                <button id="acceptBtn">승인하기</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
          </div>
    </div>
</body>
</html>