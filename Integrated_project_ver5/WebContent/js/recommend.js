function seeReserve(clicked_form, clicked_id) {
	console.log(clicked_form);
	console.log(clicked_id);
	
	var reserve = "add";
	
	if ($(".heart input:checkbox[id = '" + clicked_id + "']").is(":checked")) {
//		$(".heart input:checkbox[id = '" + clicked_id + "']"); // 있어야 할까?
	} else {
		reserve = "cancel";
	}
	
	$.ajax({
		url : "userWebtoon",
		data : {
			todo : "seeReserve",
			webtoon_id : clicked_form.webtoon_id.value,
			status : reserve
		},
		success : function(data) {
			alert(data);
		}
	});
}

function goBack() {
	window.history.back();
}

function getHighRatedWebtoonsAuthor(recommendAuthor, webtoon_id, callback) {
	var result;
	$.ajax({
		url : "userWebtoon",
		data : {"todo" : "authorLabel", "authorsName" : recommendAuthor},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				result = data;
				
				if (typeof callback === "function") callback(result);
			}
		}
	});
}

$(document).ready(function() {
	var reserves = $('.heart');
	reserves.each(function() {
		var detailsIndex = reserves.index(this);
		$(".heart input:checkbox[value='"+$('.reserve_value').eq(detailsIndex).val()+$('.reserve_title').eq(detailsIndex).val()+"']").attr("checked", true);
	});
});

$(document).ready(function() {
	$('input[name="viewfree"]').click(function() {
		var checkValue;
	   	
		if (this.value === "all") {
			checkValue = "null";
	    } else if (this.value === "paid") {
	    	checkValue = "false";
	    } else {
	    	checkValue = "true";
	    }
		
		$.ajax({
			url : "recommend",
			data : {filterviewfree : checkValue},
			dataType : "json",
			success : function(data) {
				alert(data);
			}
		});
	});
});
