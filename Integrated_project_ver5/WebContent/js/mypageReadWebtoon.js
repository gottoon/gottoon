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
		type : "POST",
		url : "/Integrated_project_ver5/action/mypageReadWebtoon",
		data : {
			count : count.value,
			todo : "readWebtoon"
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
					+ '<input type="image" src="..'+data[i].webtoons_thumbnail+'" class='+'\"'+'photo\" />'
					+ '</form></div><div id="title">'
					+ '<a id="title_link" href='+data[i].webtoons_url+' target="_blank"><strong>'+data[i].webtoons_title+'</strong></a></div>'
					+ '<div id="desc"><form id="myForm"><div class = "product-review-stars" >'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+1+'" name="rating" value="5^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+1+'" title="Rocks!">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+2+'" name="rating" value="4^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+2+'" title="Pretty good">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+3+'" name="rating" value="3^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+3+'" title="Meh">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+4+'" name="rating" value="2^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+4+'" title="Kinda bad">★</label>'
					+'<input type="checkbox" id="'+count.value*count.value*count.value+5+'" name="rating" value="1^'+data[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count.value*count.value*count.value+5+'" title="Sucks big time">★</label>'
					+'</div></form></div></div></div>');
		}
	}	
}

$(document).ready(function() {
	$.ajax({
				type : "POST",
				url : "/Integrated_project_ver5/action/mypageReadWebtoon",
				data : {
					todo : "readWebtoonCount"
			},
			success : function(data) {
				$("#gauge").hide();
				$("#progressController").hide();
				console.log(data);
				
				$("#button1").css("background-color", "red");
				// 계산 바꿔야함
				// 0~19는 1 
				// 20~39는 2
				//최소 20개 2등급
				if([ data ] == 0) {
					$("#grade").append(" <b id='level'>2</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level2.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 1 && [ data ] <= 19) {	
					$("#gauge").show();
					$("#gauge").val([ data ] * 5); //1당 5
					$("#progressController").val([ data ] * 5); 
					$("#grade").append(" <b id='level'>2</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level2.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 21 && [ data ] <= 39) {	
					$("#gauge").show();	
					$("#gauge").val(([ data ] -20) * 5 ); //1당 5
					$("#grade").append(" <b id='level'>3</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level3.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 41 && [ data ] <= 79) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -40) * 2.5); //1당 2.5
					$("#progressController").val(([ data ] -40) * 2.5);
					$("#grade").append(" <b id='level'>4</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level4.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 81 && [ data ] <= 119) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -80) * 2.5); //1당 2.5
					$("#progressController").val(([ data ] -80) * 2.5);
					$("#grade").append(" <b id='level'>5</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level5.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 121 && [ data ] <= 179) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -120) * 1.66); //1당 1.66
					$("#progressController").val(([ data ] -120) * 1.66); 
					$("#grade").append(" <b id='level'>6</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level6.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 181 && [ data ] <= 239) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -180) * 1.66); //1당 1.66
					$("#progressController").val(([ data ] -180) * 1.66);
					$("#grade").append(" <b id='level'>7</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level7.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 241 && [ data ] <= 319) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -240) * 1.25); //1당 1.25
					$("#progressController").val(([ data ] -240) * 1.25);
					$("#grade").append(" <b id='level'>8</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level8.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 321) {	
					$("#gauge").show();
					$("#gauge").val(100);
					$("#progressController").val(100);
					$("#grade").append(" <b id='level'>9</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level9.png" width="200" height="200" border="0">');
				} else {
					$("#gauge").show();
					$("#gauge").val([ data ] * 0);
				}
				
				/*$("#readWebtoonCount").append([ data ]);*/
				
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
