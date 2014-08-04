/**
 * 
 */
function toDoCheck(){
	var count = document.getElementById("wishWebtoonScrollCount");
	console.log("toDoCheck의 count number : "+ count.value);
	scrollEvent(count);
}

function scrollEvent(count){
	console.log("scrollEvent의 Request와 count : "+ count.value);
	$.ajax({
		/*url : "/Integrated_project_ver5/action/mypageWishWebtoon",*/
		url : "mypage",
		type : "POST",
		dataType : "json",
		data : {
			todo : "wishWebtoon", 
			count : count.value	
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
					+ '</form></div><div id="title"><div><div id="wish">♥</div><strong id="title">' + data[i].webtoons_title +'</strong></div>'
					+ '<a id="title_link" href='+data[i].webtoons_url+' target="_blank"><button>바로보기</button></a></div>'
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
	$("#button2").css({"color":"#fff", "background-color":"#428bca", "border-color":"#357ebd"});
	
	toDoCheck();
	
	$(window).scroll(function() {//스크롤이 하단에 위치할때 뿌려주는 놈 
		var documentHeight = $(document).height();
		var scrollHeight = $(window).scrollTop() + $(window).height();
		if (scrollHeight == documentHeight) {
			
			toDoCheck();
		}
	});
});	
