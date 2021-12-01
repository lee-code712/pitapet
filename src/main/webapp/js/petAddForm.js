function addPet(id) {
	if (id == "cancelBtn") {
		location.href="/pet/petList";
	}
	
	if (id == "addBtn") {
		if (form.name.value == "") {
			alert("반려동물의 이름을 입력해 주세요.");
			form.name.focus();
			return false;
		}
	
		if (form.birth.value == "") {
			alert("반려동물의 생년월일을 입력해 주세요.");
			form.birth.focus();
			return false;
		}
	
		form.submit();
	}
}