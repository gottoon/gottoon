$(document).ready(function() {
	$.ajax({
		type : "POST",
		url : "userWebtoon",
		data : {
			todo : "getCount"
		},
		success : function(data) {
			$("meter").val([ data ]);

			console.log(data);

			if (data == 0) {
				alert("신규 가입자 입니다. 최소 20개의 웹툰을 평가해주세요 ");
			} else if ([ data ] >= 20) {
				$("#button").show();

				$('.show-modal1').show(function() {
					$('.modal1').fadeIn('fast');
					$('.modal1').fadeOut('slow');
				});

			}
		}
	});
	if (data <= 1) {
		$("#button").show();
	}
});

/* 별점 CRUD */
function onclickStart(param) {

	var rateAndId = param.value;
	var strArray = rateAndId.split("^");

	var starRate = strArray[0];
	var webtoonId = strArray[1];

	if ($(
			".product-review-stars input:checkbox[value = " + "'" + param.value
					+ "'" + "]").is(":checked")) {
		console.log("다른 별점 선택->저장! !!!!");

		$(".product-review-stars input:checkbox[value = " + "'" + param.value
				+ "'" + "]");

		$(
				$(".product-review-stars input:checkbox[value = " + "'"
						+ param.value + "'" + "]")).siblings('input:checkbox')
				.attr('checked', false);

	} else {
		starRate = 0;
		console.log("같은 별점 선택 -> 삭제!");

	}

	console.log("별점 : " + starRate + " , 아이디 : " + webtoonId);

	$.ajax({
		type : "POST",
		url : "userWebtoon",
		data : {
			rate : starRate,
			Id : webtoonId,
			todo : "insertWebtoon"
		},
		success : function(data) {
			if (data != 0) {

				if ([ data ] <= 1) {

					$('.show-modal').show(function() {
						$('.modal').fadeIn('normal');
					});

					$('.close-modal').click(function() {
						$('.modal').fadeOut('fast');

					});
				}

				$("meter").val([ data ]);

				if (starRate == 0) {

					$('.show-modalDeleteStar').show(function() {
						$('.modalDeleteStar').fadeIn('fast');
						$('.modalDeleteStar').fadeOut('slow');
					});

				} else {

					$('.show-modalStar').show(function() {
						$('.modalStar').fadeIn('fast');
						$('.modalStar').fadeOut('slow');
					});

				}

			}
		}
	});
}

$(document).ready(function() {
	$("#button").hide();
	$("#showButton").click(function(event) {
		$("#button").show();
	});
	
});
