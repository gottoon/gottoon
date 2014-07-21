
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
			} else if ([ data ] >= 2) {
				$("#button").show();

				$('.show-modal1').show(function() {
					$('.modal1').fadeIn('fast');
					$('.modal1').fadeOut('slow');
				});
			}
		}
	});
});


/* 별점 CRUD */
function onclickStart(param) {
	var rateAndId = param.value;
	var strArray = rateAndId.split("^");
	console.log("별점 : "+strArray[0] + " , 아이디 : " + strArray[1]);

	$.ajax({
		type : "POST",
		url : "userWebtoon",
		data : {
			rate : strArray[0],
			Id : strArray[1],
			todo : "insertWebtoon"
		},
		success : function(data) {
			if (data != 0) {

				if ([ data ] == 2) {

					$('.show-modal').show(function() {
						$('.modal').fadeIn('normal');
					});

					$('.close-modal').click(function() {
						$('.modal').fadeOut('fast');

					});
				}

				$("meter").val([ data ]);

				$('.show-modal1').show(function() {
					$('.modal1').fadeIn('fast');
					$('.modal1').fadeOut('slow');
				});
			}
		}
	});
}
/* 버튼생성 */
$(document).ready(function() {
	$("#button").hide();
	$("#showButton").click(function(event) {
		$("#button").show();
	});
});

/* 게이지 */
