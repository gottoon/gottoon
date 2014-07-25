$(document).ready(function() {
	var count = 0;

	var details = $('table .product-review-stars');
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

	$.ajax({
		type : "POST",
		url : "userWebtoon",
		data : {
			todo : "getCount"
		},
		success : function(data) {
			/*$("meter").val([ data ]);*/
			console.log("ajax 결과값 " + data);
		}
	});

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

// if ($(this).is(":checked")) {
// if($(this).is(":checked")){
// console.log("됬다!!");
// var group = "input:checkbox[name='" + $(this).attr("name") + "']";
// $(group).prop("checked", '');
// $($(".product-review-stars input:checkbox[value = "+ "'" +param.value+ "'" +
// "]").is(":checked")).prop("checked", true);
// } else {
// $(this).prop("checked", false);
// console.log("안됐다!!");
// }
//
// console.log("넘어온 값 "+ param.value);
// (".product-review-stars input:checkbox").attr("checked", false);
// $(this).attr("checked", true);

// if($(".product-review-stars input:checkbox[value = "+ "'" +param+ "'" +
// "]").is(":checked")){
// console.log("참이냐!");
// ($(".product-review-stars input:checkbox[value = "+ "'" +param+ "'" +
// "]")).siblings.attr('checked','');
//
// }

// if ($(this).is(":checked")) {
// var group = "input:checkbox[name='" + $(this).attr("name") + "']";
// $(group).prop("checked", false);
// $(this).prop("checked", true);
// } else {
// $(this).prop("checked", false);
// }

// $("table input[name='rating']").click(function(e) {
// $("input[name='rating']").each(function() {
// if (this == e.target) {
// $(this).attr("checked", "true");
// } else {
// $(this).attr("checked", "");
// }
// });
// }

// $("input[name=chk]").click(function (e) {
// $("input[name=chk]").each(function () {
// if (this == e.target) {
// $(this).attr("checked", "true");
// }else {
// $(this).attr("checked", "");
// }
// });
// });
// //체크박스 그룹은 class="checkbox" 클래스로 지정
//
// $(".checkbox").click(function(){
// //체크했다면 자신을 제외한 다른 체크를 해제
// if($(this).attr('checked'))
// $(".checkbox").not(this).attr('checked','');
// });

//
// $('input:radio[name = ]').each(function(){
//
// var row = $('div .product-review-stars');
// console.log("별점 input tag의 갯수 : "+row+"(항상 5개가 나와야한다.)");
// for (var i = 0 ; row.length ; i++){
//		
// var rowIndex = row.index(this);
//		
// if( $('table #rate').eq(detailsIndex).val() == $('div
// .product-review-stars').eq(rowIndex).val()){
//
// $("input:checkbox[name='NAME']").attr("checked", false); /* by NAME */
//
// console.log("사용자의 별점 : "+$('table #rate').eq(detailsIndex).val()+" , 5개의 별점
// input tag"+$(this).eq(rowIndex).val());
//			
// $('input:radio[name]').val($('table
// #rate').eq(detailsIndex).val()+"^"+$('table #id').eq(detailsIndex.val()));
//			
// }
// }
//
// });
// alert(detailsIndex);
// $('table #').eq(detailsIndex).val();
// 별점 뜯어서 붙이기
// $('table ')



// window.addEventListener("load", function() {
// var details = $('table .product-review-stars');
// for (var i = 0; i < details.length; i++) {
// details[i].addEventListener("click", function() {
// var detailsIndex = details.index(this);
//		
//		
// $('input:radio[name]').each(function(){
// var row = $('div .product-review-stars');
//			
// for (var i = 0 ; row.length ; i++){
// var rowIndex = row.index(this);
//				
// if( $('table #rate').eq(detailsIndex).val() == $(this).eq(rowIndex).val()){
// $('input:radio[name]').val($('table #rate').eq(detailsIndex).val());
//					
//					
// }
// }
//		
// });
//		
//		
//		
// // alert(detailsIndex);
// $('table #rate').eq(detailsIndex).val();//받아온 별점
//		
// //별점 적용
// // $('table #').eq(detailsIndex).val();
//		
// //별점 뜯어서 붙이기
//		
// // $('table ')
//		
//		
//		
// });
// }
// }, false);

