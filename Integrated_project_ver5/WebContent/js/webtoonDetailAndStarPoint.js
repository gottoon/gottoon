/**
 * 
 */
$(document).ready(function() {
	var count = 0;

	var details = $('table .product-review-stars');
//	 for (var i = 0; i < details.length; i++) {
	console.log("웹툰 갯 수 " + details.length);
	details.each(function() {
		var detailsIndex = details.index(this);
		console.log("===================================");
		console.log("사용자가 평가했던 별점 " + $('table #rate').eq(detailsIndex).val());
		console.log("사용자가 평가했던 웹툰 아이디 : " + $('table #id').eq(detailsIndex).val());
//		var webtoonId = "^" + $('table #id').eq(detailsIndex).val();
//		var rate = $('table #rate').eq(detailsIndex).val();
//		var value = rate + webtoonId;
		$(".product-review-stars input:checkbox[value="+"'"+$('table #rate').eq(detailsIndex).val()+"^"+$('table #id').eq(detailsIndex).val()+"'"+"]").attr("checked", true);
//		var rateValue = rate + "^" + webtoonId;
		// console.log("웹툰의 아이디+별점 : "
		// +$('.product-review-stars
		// input:radio[name ='+rate+'
		// ]').eq(detailsIndex).val());
		// $("input:radio[name='NAME']:radio[value='VALUE']").attr("checked",true);예제
//		console.log("읽은 웹툰의 아이디 + 별점 " + value);
		console.log("===================================");
//		console.log("하핳하하하" + rate + webtoonId);
		// $(".product-review-stars input:checkbox[value = "+ "'" + rate +
		// webtoonId + "'" + "]").attr("checked", true);


		// console.log($(".product-review-stars
		// input:radio[name
		// ='rating']:radio[class
		// ='visuallyhidden']:radio[value="+"'"+rate+webtoonId+"'"+"]").eq(detailsIndex).val());
		// console.log($(".product-review-stars
		// input:radio[name
		// ='rating']:radio[value="+"'"+rate+"^"+webtoonId+"'"+"]").eq(detailsIndex).val());
		// var 변수명 = $("타겟지정코드").val();
		// $('.product-review-stars
		// input:radio[name ='+rate+'
		// ]').eq(detailsIndex).attr("checked" ,
		// true);
		// input:radio[value =
		// '+rate+'^'+webtoonId+']'
		// .attr("checked" , true));

	});

	count++;
	console.log("반복 횟 수 " + count);
//	 }

//	$.ajax({
//		type : "POST",
//		url : "userWebtoon",
//		data : {
//			todo : "getCount"
//		},
//		success : function(data) {
//			$("meter").val([ data ]);
//			console.log("ajax 결과값 " + data);
//
//		}
//	});

});

function onclickStart(param) {

	var rateAndId = param.value;
	var strArray = rateAndId.split("^");

	var starRate = strArray[0];
	var webtoonId = strArray[1];

	if ($(".product-review-stars input:checkbox[value = " + "'" + param.value + "'" + "]").is(":checked")) {
		console.log("다른 별점 선택->저장! !!!!");

		$(".product-review-stars input:checkbox[value = " + "'" + param.value + "'" + "]");

		$($(".product-review-stars input:checkbox[value = " + "'" + param.value + "'" + "]")).siblings('input:checkbox').attr('checked', false);

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

				if ([ data ] == 2) {

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