$(document).ready(function() {
	
	var count = 0;
	var details = $('table .product-review-stars');
	console.log("웹툰 갯 수 " + details.length);
	
	
	details.each(function() {
		var detailsIndex = details.index(this);
		console.log("===================================");
		console.log("사용자가 평가했던 별점 " + $('table #rate').eq(detailsIndex).val());
		console.log("사용자가 평가했던 웹툰 아이디 : " + $('table #id').eq(detailsIndex).val());
        console.log("===================================");
//		var webtoonId = "^" + $('table #id').eq(detailsIndex).val();
//		var rate = $('table #rate').eq(detailsIndex).val();
//		var value = rate + webtoonId;
		
		
		
		$(".product-review-stars input:checkbox[value="+"'"+$('table #rate').eq(detailsIndex).val()+"^"+$('table #id').eq(detailsIndex).val()+"'"+"]").attr("checked", true);

	
		
		//		self.close();

//		var rateValue = rate + "^" + webtoonId;
		// console.log("웹툰의 아이디+별점 : "
		// +$('.product-review-stars
		// input:radio[name ='+rate+'
		// ]').eq(detailsIndex).val());
		// $("input:radio[name='NAME']:radio[value='VALUE']").attr("checked",true);예제
//		console.log("읽은 웹툰의 아이디 + 별점 " + value);
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
	

	
	
//	$.one(function(){
//
//		
//		$.ajax({
//		    url: "",
//		    context: document.body,
//		    success: function(s,x){
//		        $(this).html(s);
//		    }
//		});
//		self.close();
////	location.reload();
//	
//});
//	opener.location.reload(true);  
//	self.close(); 
	
	
	
	
		
		
		
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
			
			// 서클 프로그레스 바 //
			$("#progressController").hide();
			if([ data ] == 0) {
			} else if ([ data ] >= 1 && [ data ] <= 19) {	
				$("#progressController").val([ data ] * 5); //1당 5
			} else if ([ data ] >= 21 && [ data ] <= 39) {	
				$("#progressController").val(([ data ] -20) * 5 ); //1당 5
			} else if ([ data ] >= 41 && [ data ] <= 79) {	
				$("#progressController").val(([ data ] -40) * 2.5); //1당 2.5
			} else if ([ data ] >= 81 && [ data ] <= 119) {	
				$("#progressController").val(([ data ] -80) * 2.5); //1당 2.5
			} else if ([ data ] >= 121 && [ data ] <= 179) {	
				$("#progressController").val(([ data ] -120) * 1.66); //1당 1.66
			} else if ([ data ] >= 181 && [ data ] <= 239) {	
				$("#progressController").val(([ data ] -180) * 1.66); //1당 1.66
			} else if ([ data ] >= 241 && [ data ] <= 319) {	
				$("#progressController").val(([ data ] -240) * 1.25); //1당 1.25
			} else if ([ data ] >= 321) {	
				$("#progressController").val(100);
			} else {
			}	
			console.log("ajax 결과값 " + data);
			
			var $pc = $('#progressController');
			var $pCaption = $('.progress-bar p');
			var iProgress = document.getElementById('inactiveProgress');
			var aProgress = document.getElementById('activeProgress');
			var iProgressCTX = iProgress.getContext('2d');


			drawInactive(iProgressCTX);

			$pc.on('change', function(){
				var percentage = $(this).val() / 100;
				console.log(percentage + '%');
				drawProgress(aProgress, percentage, $pCaption);
			});

			function drawInactive(iProgressCTX){
				iProgressCTX.lineCap = 'square';

				//outer ring
				iProgressCTX.beginPath();
				iProgressCTX.lineWidth = 15;
				iProgressCTX.strokeStyle = '#e1e1e1';
				iProgressCTX.arc(137.5,137.5,129,0,2*Math.PI);
				iProgressCTX.stroke();

				//progress bar
				iProgressCTX.beginPath();
				iProgressCTX.lineWidth = 0;
				iProgressCTX.fillStyle = '#e6e6e6';
				iProgressCTX.arc(137.5,137.5,121,0,2*Math.PI);
				iProgressCTX.fill();

				//progressbar caption
				iProgressCTX.beginPath();
				iProgressCTX.lineWidth = 0;
				iProgressCTX.fillStyle = '#fff';
				iProgressCTX.arc(137.5,137.5,100,0,2*Math.PI);
				iProgressCTX.fill();

			}
			function drawProgress(bar, percentage, $pCaption){
				var barCTX = bar.getContext("2d");
				var quarterTurn = Math.PI / 2;
				var endingAngle = ((2*percentage) * Math.PI) - quarterTurn;
				var startingAngle = 0 - quarterTurn;

				bar.width = bar.width;
				barCTX.lineCap = 'square';

				barCTX.beginPath();
				barCTX.lineWidth = 20;
				barCTX.strokeStyle = '#76e1e5';
				barCTX.arc(137.5,137.5,111,startingAngle, endingAngle);
				barCTX.stroke();

				$pCaption.text( (parseInt(percentage * 100, 10)) + '%');
				
			}

				var percentage = $pc.val() / 100;
				drawProgress(aProgress, percentage, $pCaption);
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

