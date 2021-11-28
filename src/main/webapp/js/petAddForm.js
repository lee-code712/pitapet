function addPet(id) {
	if (id == "cancelBtn") {
		location.href="/pet/petList";
	}
	
	if (id == "addBtn") {
		if ( !$("input[name=name]").val()) {
			alert("반려동물의 이름을 입력해 주세요.");
			return false;
		}
	
		if ( !$("#birth").val()) {
			alert("반려동물의 생년월일을 입력해 주세요.");
			return false;
		}
	
		form.submit();
	}
}