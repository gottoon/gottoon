function checkButton() {
	$('div #moreButton').remove();
	if (webtoonCount <= viewCount) {
		$('.webtoon_table').eq(webtoonCount-1).after('<br /><div class="reload">'
				+ '<form method="post" action="recommend">'
				+ '<input type="hidden" name="todo" value="highRated_test" />' 
				+ '<input class="button" type="submit" id="reset" value="다시 추천해줘!" />'
				+ '</form></div>');
	} else {
		$('div .webtoon_table').eq(num).after('<div id="moreButton">'
			+ '<button id="more" class="button">더보기</button></div>');
		$('div #more').click(function(){
			viewCount += 10;
			for (; num < viewCount; num++) {
				$('.webtoon_table').eq(num).show(); 
			}
			checkButton();
		});
	}
}

/*function seeReserve(form) {
	$.ajax({
		url : "userWebtoon",
		data : {"todo" : "seeReserve", "webtoon_id" : form.webtoon_id.value},
		success : function(data) {
			alert(data);
		}
	});
}*/

function seeReserve(clicked_form, clicked_id) {
	console.log(clicked_form);
	console.log(clicked_id);
	
	var reserve = "add";
	
	if ($(".heart input:checkbox[id = '" + clicked_id + "']").is(":checked")) {
		$(".heart input:checkbox[id = '" + clicked_id + "']"); // 있어야 할까?
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

function calculateDateRange(first_update) {
	var format = "-";

	if (first_update.length != 10)
		return null;

	if (first_update.indexOf(format) < 0)
		return null;

	var update_token = first_update.split(format);
	update_token[1] = (Number(update_token[1]) - 1) + "";

	var now = new Date();

	var from_date = new Date(update_token[0], update_token[1], update_token[2]);
/*	var to_date = new Date(now.getFullYear(), now.getMonth(), now.getDate());*/
	var to_date = new Date(2010, 07, 01);

	var checkDate = (to_date.getTime() - from_date.getTime()) / 1000 / 60;
	
	if (checkDate < 0 || checkDate >= 41760) {
		return false;
	} else {
		return true;
	}
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

/*function overlay(webtoon_id) {
	$.ajax({
		type : "POST",
		url : "webtoon",
		data : {"todo" : "showWebtoonDetails", "webtoon_id" : webtoon_id},
		success : function(data){
			console.log(data);
		}
	});
}*/
