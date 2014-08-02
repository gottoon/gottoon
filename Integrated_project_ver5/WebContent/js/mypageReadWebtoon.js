/**
 * 
 */
function toDoCheck(){
	var count = document.getElementById("readWebtoonScrollCount");
	console.log("toDoCheck의 count number : "+ count.value);
	scrollEvent(count);
}

function scrollEvent(count){
	console.log("scrollEvent의 Request와 count : "+ count.value);
	$.ajax({
		/*url : "/Integrated_project_ver5/action/mypageReadWebtoon",*/
		url : "mypage",
		type : "POST",
		dataType : "json",
		data : {
			todo : "readWebtoon",
			count : count.value,
	},
	success : function(data) {
		
		showScroll(data ,count);
	},
	error : function(request , status , error){
		alert("code : "+request.status + "\n"+"message : " +
				request.responseText+"\n"+"error : "+error);
		
	}
});
};

function showScroll(data , count){
	 if(data.length !== 0){
		for(var i =0; i< data.length; i++){
			count.value++;
			console.log(count.value);
			$('#layout').append('<div class="gallery-item"><div class="vcard"><div id="image">'
					+ '<form method="post" action="webtoon"><input type="hidden" name="webtoon_id"'
					+ ' value='+data[i].webtoons_id_pk+' /> <input type="hidden"'
					+ ' name="todo" value="showWebtoonDetails" />'
					+ '<input type="image" src="'+data[i].webtoons_thumbnail+'" class='+'\"'+'photo\" />'
					+ '</form></div><div id="title">'
					+ '<a id="title_link" href='+data[i].webtoons_url+' target="_blank"><strong>'+data[i].webtoons_title+'</strong></a></div>'
					+ '<div id="desc"><form id="myForm"><div class = "product-review-stars" >'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+1+'" name="rating" value="5^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+1+'" title="Rocks!">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+2+'" name="rating" value="4^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+2+'" title="Pretty good">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+3+'" name="rating" value="3^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+3+'" title="Meh">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+4+'" name="rating" value="2^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+4+'" title="Kinda bad">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+5+'" name="rating" value="1^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+5+'" title="Sucks big time">★</label>'
					+'</div></form></div></div></div>');
			
				console.log("===================================");
				console.log("사용자가 평가한 별점 " + data[i].webtoon_rate);
				console.log("사용자가 평가한 웹툰의 아이디 : " +data[i].webtoons_id_pk);
		        console.log("===================================");
				$(".product-review-stars input:checkbox[value="+"'"+data[i].webtoon_rate+"^"+data[i].webtoons_id_pk+"'"+"]").attr("checked", true);
		}
	}	
}

$(document).ready(function() {
	$.ajax({	
				/*url : "/Integrated_project_ver5/action/mypageReadWebtoon",*/
				url : "mypage",
				type : "POST",
				dataType : "json",
				data : {
					todo : "readWebtoonCount"
			},
			success : function(data) {
				$("#gauge").hide();
				$("#progressController").hide();
				console.log("유저 가 본 웹튼 카운트 : "+parseInt(data[0])+" 경험치 : "+data[1]+" 유저 등급 : "+parseInt(data[2]) + "등급 업 여부 : " + parseInt(data[3]));
				
				toDoCheck();
				
				if(parseInt(data[2]) == 1) {
					$("#grade").append(" <b id='level'>1</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level1.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 2) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>2</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level2.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 3) {	
					$("#gauge").show();	
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>3</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level3.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 4) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>4</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level4.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 5) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>5</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level5.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 6) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]);  
					$("#grade").append(" <b id='level'>6</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level6.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 7) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>7</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level7.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 8) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>8</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level8.png" width="200" height="200" border="0">');
				
				} else if (parseInt(data[2]) == 9) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>9</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level9.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 10) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>10</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level10.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 11) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>11</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level11.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 12) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>12</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level12.png" width="200" height="200" border="0">');	
				
				} else if (parseInt(data[2]) == 13) {	
					$("#gauge").show();
					$("#gauge").val(data[1]); 
					$("#progressController").val(data[1]); 
					$("#grade").append(" <b id='level'>13</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level13.png" width="200" height="200" border="0">');
					
				} else if (parseInt(data[2]) == 14) {	
					$("#gauge").show();
					$("#gauge").val(100);
					$("#progressController").val(100);
					$("#grade").append(" <b id='level'>14</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level14.png" width="200" height="200" border="0">');
				}
				
				// 테스트는 0으로 
				if(parseInt(data[3])== 1) {
					$('.show-levelup').show(function() {
						$('.levelup').fadeIn('slow');
					});

					$('.close').click(function() {
						$('.show-levelup').fadeOut('slow');
					});
				}
				
				$("#readWebtoonCount").append(parseInt(data[0]));
				
				$("#button1").css({"color":"#fff", "background-color":"#428bca", "border-color":"#357ebd"});
			
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
					barCTX.strokeStyle = '#0AD9E0';
					barCTX.arc(137.5,137.5,111,startingAngle, endingAngle);
					barCTX.stroke();

					$pCaption.text( (parseInt(percentage * 100, 10)) + '%');
					
				}

					var percentage = $pc.val() / 100;
					drawProgress(aProgress, percentage, $pCaption);
				
					$(window).scroll(function() {//스크롤이 하단에 위치할때 뿌려주는 놈 
						var documentHeight = $(document).height();
						var scrollHeight = $(window).scrollTop() + $(window).height();
						if (scrollHeight == documentHeight) {
							
							toDoCheck();
						}
					});
				}
			});	
});	
