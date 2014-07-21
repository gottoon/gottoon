$(document).ready(
		function() {

			
			$.ajax({
				type : "POST",
				url : "/Integrated_project_ver4_test2_soo2/action/userWebtoon",
				data : {
					todo : "getCount"
				},
				success : function(data) {
					$("meter").val([ data ]);
					console.log(data);
					
					
					var details = $('table .product-review-stars');
					for (var i = 0; i < details.length; i++) {

						// details[i].addEventListener("click", function() {
						details.each(function() {
							
							var detailsIndex = details.index(this);
							console.log("===================================");
							console.log("사용자가 평가했던 별점 "
									+ $('table #rate').eq(detailsIndex).val());
							console.log("사용자가 평가했던 웹툰 아이디 : "
									+ $('table #id').eq(detailsIndex).val());
							console.log("===================================");
							
							
							
						});
					}


				}
			} ,false);

		}, false);

function onclickStart(param) {
	var rateAndId = param.value;
	var strArray = rateAndId.split("^");
	console.log("별점 : " + strArray[0] + " , 아이디 : " + strArray[1]);

	$.ajax({
		type : "POST",
		url : "/Integrated_project_ver4_test2_soo2/action/userWebtoon",
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

