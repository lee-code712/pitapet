$(document).ready(function() { // html이 로드되면 실행됨
	// $("#memberBtn").click(searchMemberPageInfo);   
	$("#sitterBtn").click(searchSitterPageInfo); 
});

var memberInfoJson = JSON.parse('${memberInfoJson}');
var applicationStatus = '${applicationStatus}';
var careListJson = JSON.parse('${careListJson}');
var sitterInfoJson = JSON.parse('${sitterInfoJson}');

function searchSitterPageInfo() {
	var publicStatus = sitterInfoJson.publiStatus;
	alert(publicStatus);
	var ableDate = sitterInfoJson.ableDate;
	var calculatedPrice = sitterInfoJson.calculatedPrice;
	var tag = sitterInfoJson.tag;
	var notes = sitterInfoJson.notes;
}