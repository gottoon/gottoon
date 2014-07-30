/**
 * 
 */

function toDoCheck(){
	var count = document.getElementById("readWebtoonCount");
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
			/* var data_url= data[i].webtoons_thumbnail;
			var url = "src="+"\" &lt;c:url value='"+data_url+"' / &gt;";
			console.log(url);*/
			$('#layout').append('<div class="gallery-item"><div class="vcard"><div id="image">'

							+ '<form method="post" action="webtoon"><input type="hidden" name="webtoon_id"'
								+ ' value='+data[i].webtoons_id_pk+' /> <input type="hidden"'
								+ ' name="todo" value="showWebtoonDetails" />'
								+ '<input type="image" src=\"<c:url value=\''+data[i].webtoons_thumbnail+'\' /> class='+'\"'+'photo\" />'
//											     src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" class="photo" />
								//								+ ' type="image" src="<c:url value='+'\''+data[i].webtoons_thumbnail+'\''+'/>" class="photo" />'
								+ ' </form></div><div id="title">'
							+ '<a id="title_link" href='+data[i].webtoons_url+''
							+ ' target="_blank"><strong>'+data[i].webtoons_title+'</strong></a></div>'
							+ '<div id="desc"><form id="myForm">'
					+'<div class = "product-review-stars" >'
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
				console.log(data);
				
				
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
					$("#grade").append(" <b id='level'>4</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level4.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 81 && [ data ] <= 119) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -80) * 2.5); //1당 2.5
					$("#grade").append(" <b id='level'>5</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level5.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 121 && [ data ] <= 179) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -120) * 1.66); //1당 1.66
					$("#grade").append(" <b id='level'>6</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level6.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 181 && [ data ] <= 239) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -180) * 1.66); //1당 1.66
					$("#grade").append(" <b id='level'>7</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level7.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 241 && [ data ] <= 319) {	
					$("#gauge").show();
					$("#gauge").val(([ data ] -240) * 1.25); //1당 1.25
					$("#grade").append(" <b id='level'>8</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level8.png" width="200" height="200" border="0">');
				} else if ([ data ] >= 321) {	
					$("#gauge").show();
					$("#gauge").val(100);
					$("#grade").append(" <b id='level'>9</b>");
					$(".gradeImg").append('<img src="../img/gradeImg/grade_level9.png" width="200" height="200" border="0">');
				} else {
					$("#gauge").show();
					$("#gauge").val([ data ] * 0);
				}
				
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
	


