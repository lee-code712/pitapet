$(document).ready(function() { // html이 로드되면 실행됨
	$("#memberBtn").click(searchMemberPageInfo);   
	$("#sitterBtn").click(searchSitterPageInfo); 
});

function searchMemberPageInfo() {
	location.href="/member/memberMyPage";
}

function searchSitterPageInfo() {
	var publicStatus = sitterInfoJson.publicStatus;
	var ableDate = sitterInfoJson.ableDate;
	var calculatedPrice = sitterInfoJson.calculatedPrice;
	var tag = sitterInfoJson.tag;
	var notes = sitterInfoJson.notes;
	
	$("#reservationInfoWrap").empty();
	
	$("#reservationInfoWrap").append("돌보미 정보");
	$("#reservationInfoWrap").append("<br/>");
	var newTableElement = document.createElement("table");
	$(newTableElement).attr("id", "infoTable");
	var newTrElement = document.createElement("tr");
	var newThElement = document.createElement("th");
	$(newThElement).html("정보 공개 상태");
	$(newTrElement).append(newThElement);
	var newThElement2 = document.createElement("th");
	$(newThElement2).html("가능 요일");
	$(newTrElement).append(newThElement2);
	var newThElement3 = document.createElement("th");
	$(newThElement3).html("데이케어, n박케어 요금");
	$(newTrElement).append(newThElement3);
	var newThElement4 = document.createElement("th");
	$(newThElement4).html("태그");
	$(newTrElement).append(newThElement4);
	var newThElement5 = document.createElement("th");
	$(newThElement5).html("소개글");
	$(newTrElement).append(newThElement5);
	$(newTableElement).append(newTrElement);
	
	var newTrElement2 = document.createElement("tr");
	var newTdElement = document.createElement("td");
	if (publicStatus == "Y") {
		$(newTdElement).html("공개");
	}
	else {
		$(newTdElement).html("비공개");
	}
	$(newTrElement2).append(newTdElement);
	var newTdElement2 = document.createElement("td");
	$(newTdElement2).html(ableDate.charCodeAt(0).toString(2));
	$(newTrElement2).append(newTdElement2);
	var newTdElement3 = document.createElement("td");
	$(newTdElement3).html(calculatedPrice);
	$(newTrElement2).append(newTdElement3);
	var newTdElement4 = document.createElement("td");
	$(newTdElement4).html(tag);
	$(newTrElement2).append(newTdElement4);
	var newTdElement5 = document.createElement("td");
	$(newTdElement5).html(notes);
	$(newTrElement2).append(newTdElement5);
	$(newTableElement).append(newTrElement2);
	$("#reservationInfoWrap").append(newTableElement);
	
	// 돌봄 가능한 펫 정보
	$("#reservationInfoWrap").append("<br/>");
	$("#reservationInfoWrap").append("돌봄 가능한 반려동물 정보");
	$("#reservationInfoWrap").append("<br/>");
	
	var newTableElement2 = document.createElement("table");
	$(newTableElement2).attr("id", "infoTable2");
	var newTrElement3 = document.createElement("tr");
	var newThElement6 = document.createElement("th");
	$(newThElement6).html("대분류");
	$(newTrElement3).append(newThElement6);
	var newThElement7 = document.createElement("th");
	$(newThElement7).html("소분류");
	$(newTrElement3).append(newThElement7);
	$(newTableElement2).append(newTrElement3);
	
	for (key in petKindJson) {
		var newTrElement4 = document.createElement("tr");
		var newTdElement6 = document.createElement("td");
		$(newTdElement6).html(petKindJson[key].largeCategory);
		$(newTrElement4).append(newTdElement6);
		var newTdElement7 = document.createElement("td");
		$(newTdElement7).html(petKindJson[key].smallCategory);
		$(newTrElement4).append(newTdElement7);
		$(newTableElement2).append(newTrElement4);
	}
	
	$("#reservationInfoWrap").append(newTableElement2);
	
	// 가능한 서비스 정보
	$("#reservationInfoWrap").append("<br/>");
	$("#reservationInfoWrap").append("가능한 서비스 정보");
	$("#reservationInfoWrap").append("<br/>");
	
	var newTableElement3 = document.createElement("table");
	$(newTableElement3).attr("id", "infoTable3");
	var newTrElement4 = document.createElement("tr");
	var newThElement7 = document.createElement("th");
	$(newThElement7).html("서비스");
	$(newTrElement4).append(newThElement7);
	var newThElement8 = document.createElement("th");
	$(newThElement8).html("내용");
	$(newTrElement4).append(newThElement8);
	$(newTableElement3).append(newTrElement4);
	
	for (key in serviceListJson) {
		var newTrElement5 = document.createElement("tr");
		var newTdElement7 = document.createElement("td");
		$(newTdElement7).html(serviceListJson[key].title);
		$(newTrElement5).append(newTdElement7);
		var newTdElement8 = document.createElement("td");
		$(newTdElement8).html(serviceListJson[key].content);
		$(newTrElement5).append(newTdElement8);
		$(newTableElement3).append(newTrElement5);
	}
	
	$("#reservationInfoWrap").append(newTableElement3);
}