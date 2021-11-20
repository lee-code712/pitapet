$(function() {
	$(".checkbox").click(function() {
		var checkbox = this;
		var petName = $(checkbox).parent().parent().children("#petInfoName").text();
		// alert(petName);

		if (document.getElementById(petName)) {
			var title = document.getElementById(petName);
			title.parentNode.removeChild(title);
		}
		else {
			// <반려동물명> 서비스 리스트 출력
			var newDivElement = document.createElement("div");
			$(newDivElement).text(petName + " 서비스 리스트: ");
			$(newDivElement).attr("id", petName);
			

			// 가능 서비스 리스트 출력
			var i = 1;
			for (key in ableService) {
				var newInputElement = document.createElement("input");
				$(newInputElement).attr("type", "checkbox");
				$(newInputElement).attr("id", "service" + i);
				i++;
				$(newDivElement).append(newInputElement);
				
				var newTextElement = ableService[key].title + " ";
				$(newDivElement).append(newTextElement);
			}
			
			$("#petSitterService").append(newDivElement);
		}
	});
});