var today = new Date();
		var year = today.getFullYear();
	    $(function() {
	        $("#fromDate,#toDate").datepicker({
	                dateFormat: 'yy-mm-dd' //달력 날짜 형태
	                ,yearRange: year + ":" + (year + 1)
	                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	                ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
	                ,changeYear: true //option값 년 선택 가능
	                ,changeMonth: true //option값  월 선택 가능                       
	                ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
	                ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
	                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
	                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
	                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	                ,minDate: "+1D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	                ,maxDate: "+30D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	        		,beforeShowDay: disableAllTheseDays
	            });                    
	            
	            //초기값을 오늘 날짜로 설정해줘야 합니다.
	            $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
	        });
	    
	 	// 불가능한 날짜들 배열
	 	var schedules = JSON.parse('${schedules}');
	    var disabledDays = [];
	    for (key in schedules) {
	    	disabledDays.push(schedules[key].split(' ')[0]);
	    }
	 	console.log(disabledDays);

	    // 특정일 선택막기
	    function disableAllTheseDays(date) {
	        var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
	        m = cfSetAddZero(m);
	        d = cfSetAddZero(d);
	        for (var i = 0; i < disabledDays.length; i++) {
	            if($.inArray(y + '-' + (m + 1) + '-' + d,disabledDays) != -1) {
	                return [false];
	            }
	        }
	        return [true];
	    }
	    
	    function cfSetAddZero(target) {
	        if (target <= 9)
	        	return "0" + target;
	        return target;
	    }
	    
	    function getPrice() {
	    	var start = new Date($("#fromDate").val());
	    	var end = new Date($("#toDate").val());
	    	var dateDiff = Math.ceil((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
	    	console.log(dateDiff);
	    	var price = 0;
	    	if (dateDiff == 0) {
	    		price = '${fn:split(sitterInfo.calculatedPrice,',')[1]}';
	    		$('#carePrice').text('데이케어 ${fn:split(sitterInfo.calculatedPrice,',')[1]}원');
	    	}
	    	else {
	    		price = '${fn:split(sitterInfo.calculatedPrice,',')[0]}';
	    		price = price * dateDiff
	    		$('#carePrice').text(dateDiff + '박케어 ' + price +'원');
	    		console.log(price);
	    	}
	    	
	    	var newInputElement = document.createElement("input");
			$(newInputElement).attr("type", "hidden");
			$(newInputElement).attr("name", "totalPrice");
			$(newInputElement).attr("value", price);
			$("#carePrice").append(newInputElement);
	    }
	    
	    function reserve() {
	    	if (form.fromDate.value == "") {
	    		alert("체크인 날짜를 선택하세요.");
	    		form.fromDate.focus();
	    		return false;
	    	}
	    	if (form.toDate.value == "") {
	    		alert("체크아웃 날짜를 선택하세요.");
	    		form.toDate.focus();
	    		return false;
	    	}
	    	form.submit();
	    }