
$(document).ready(function() {
	
	$(window).scroll(function() {//스크롤이 하단에 위치할때 뿌려주는 놈 
		var documentHeight = $(document).height();
		var scrollHeight = $(window).scrollTop() + $(window).height();
		if (scrollHeight == documentHeight) {
			toDoCheck();
		}
	});
});
function toDoCheck(){
	var count = $("#count").val();
//	console.log("toDoCheck의 count number : "+ count.value);
	scrollEvent(count);
}

function scrollEvent(count){
	console.log("scrollEvent의 Request와 count : "+ count);
	$.ajax({
		url : "userGenre",
		dataType : "json",
		type : "POST",
		data : {
			todo : "infinite",
			count : count
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


function showScroll(request , count){
	
	if(request.length !== 0){
		for(var i =0; i< request.length; i++){
			count++;
			$("#count").val(count);
			console.log(count);
			
			$('#layout').append('<div class="flip-container" ontouchstart="this.classList.toggle("hover");">'
					+'<div class="flipper"><div class="front" id="front'+count+'">'
					+'<span class="name"><section class="webtoonTable"><div class="poster">'
					+'<img src="'+request[i].webtoons_title_image+'" /></div>'
					+'<div id="toonLabel"><div id="add"><p><span>' + request[i].webtoons_title+ ' </span></p>'
					+'</div><div id = "comments" ><form id="myForm">'
					+'<div class = "product-review-stars" >'
					+'<input type="checkbox" id="'+count*count*count+1+'" name="rating" value="5^'+request[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count*count*count+1+'" title="Rocks!">★</label>'
					+'<input type="checkbox" id="'+count*count*count+2+'" name="rating" value="4^'+request[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count*count*count+2+'" title="Pretty good">★</label>'
					+'<input type="checkbox" id="'+count*count*count+3+'" name="rating" value="3^'+request[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count*count*count+3+'" title="Meh">★</label>'
					+'<input type="checkbox" id="'+count*count*count+4+'" name="rating" value="2^'+request[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count*count*count+4+'" title="Kinda bad">★</label>'
					+'<input type="checkbox" id="'+count*count*count+5+'" name="rating" value="1^'+request[i].webtoons_id_pk+'" onclick=onclickStart(this) class="visuallyhidden"> <label for="'+count*count*count+5+'" title="Sucks big time">★</label>'
					+'</div></form>'
			+'</div></div></section></span></div></div>');
			
			$('#front'+count.value).after('<div class="back"><h4>'+request[i].webtoons_title+'</h4></div></div></div>');
			
		}
	}	
}

$(document).ready(function() {//페이지가 로드되면 meter값 넣기 
	$.ajax({
		type : "POST",
		url : "userWebtoon",
		data : {
			todo : "getCount"
		},
		success : function(data) {
			$("meter").val( data);

			

			(function()  {//
				  var leaseMeter, meterBar, meterBarWidth, meterValue, progressNumber;

				  /*Get value of value attribute*/
				  var valueGetter = function() {
				    leaseMeter = document.getElementsByClassName('leaseMeter');
				    for (var i=0; i<leaseMeter.length; i++) {
				      meterValue = leaseMeter[i].getAttribute('value');
				      return meterValue;
				    }
				  };

				  /*Convert value of value attribute to percentage*/
				  var getPercent = function() {
					  meterBarWidth = parseInt(valueGetter());
					  if(meterBarWidth < 11){
						  meterBarWidth = parseInt(valueGetter()) * 10;
						  meterBarWidth.toString;
						  console.log("선택한 웹툰이 10 이하 일때 게이지바 퍼센트 = "+meterBarWidth);
						    meterBarWidth = meterBarWidth + "%";
						    return meterBarWidth;
					 
					  }else{
						  if(parseInt(valueGetter().charAt(valueGetter().length-1)) == 0){
							  meterBarWidth = 100 + "%";
							  return meterBarWidth;
						  }else{
							  meterBarWidth = parseInt(valueGetter().charAt(valueGetter().length-1)) * 10;
							  meterBarWidth = meterBarWidth + "%";
							  return meterBarWidth;
							  
						  }
					  }
				  };	
				  var adjustWidth = function() {
				    meterBar = document.getElementsByClassName('meterBar');
				    for (var i=0; i<meterBar.length; i++) {
				      meterBar[i].style['width'] = getPercent();
				    }
				  };
				  var indicUpdate = function() {
				    progressNumber = document.getElementsByClassName('progressNumber');
				    for (var i=0; i<progressNumber.length; i++) {
				      progressNumber[i].innerHTML = valueGetter();
				    }
				  };

				  adjustWidth();
				  indicUpdate();
				})();
			
			console.log(data);

			if (data == 0) {//신규 가입자용 알림창 
				alert("신규 가입자 입니다. 최소 10개의 웹툰을 평가해주세요 ");
			} else if ( data  >= 10) {//10개 이상 평가했을때 "추천해줭"버튼 유지 
				$("#button").show();
				$(".comm").hide();
			}
		}
	});
			toDoCheck()	;
});

function onclickStart(param) {//별점 추가 삭제 수정 변경.

	var rateAndId = param.value;
	var strArray = rateAndId.split("^");

	var starRate = strArray[0];
	var webtoonId = strArray[1];

	if ($(".product-review-stars input:checkbox[value = " + "'" + param.value+ "'" + "]").is(":checked")) 
	{//선택한 별점의 유무판단. 
		console.log("다른 별점 선택->저장! !!!!");//

		$(".product-review-stars input:checkbox[value = " + "'" + param.value+ "'" + "]");//값을 넣고 ,
		$($(".product-review-stars input:checkbox[value = " + "'"+ param.value + "'" + "]")).siblings('input:checkbox').attr('checked', false);
//선택된 별점이외의 값을 초기화시킨다. 
	} else {
		//선택한 별점이 없거나 , 동일한 별점을 클릭하여 평가한 별점이 없을경우 , 삭제를 위해 starRate에 0점등록.
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
		success : function(data) {//별점의 CRUD에 대한 평가한 웹툰의 총 수량을 meter에 적용한다. 
			if (data != 0) {
				if(data == 10){//10개일때 "추천해줭 ", "더평가할래!"선택창 띄움.

					$('.show-modal').show(function() {
						$('.choose-modal').fadeIn('normal');
					});
					$('.close-modal').click(function() {
						$('.choose-modal').fadeOut('fast');
					});
				}
				
				$("meter").val( data );//웹툰의 변동사항을 meter에 적용시킨다.
				(function()  {
					  var leaseMeter, meterBar, meterBarWidth, meterValue, progressNumber;
					  var valueGetter = function() {
					    leaseMeter = document.getElementsByClassName('leaseMeter');
					    for (var i=0; i<leaseMeter.length; i++) {
					      meterValue = leaseMeter[i].getAttribute('value');
					      return meterValue;
					    }
					  };
					  var getPercent = function() {
					  meterBarWidth = parseInt(valueGetter());
					  if(meterBarWidth < 11){
						  meterBarWidth = parseInt(valueGetter()) * 10;
						  meterBarWidth.toString;
						  console.log("선택한 웹툰이 10 이하 일때 게이지바 퍼센트 = "+meterBarWidth);
						    meterBarWidth = meterBarWidth + "%";
						    return meterBarWidth;
					  }else{
						  if(parseInt(valueGetter().charAt(valueGetter().length-1)) == 0){
							  meterBarWidth = 100 + "%";
							  return meterBarWidth;
						  }else{
							  meterBarWidth = parseInt(valueGetter().charAt(valueGetter().length-1)) * 10;
							  meterBarWidth = meterBarWidth + "%";
							  return meterBarWidth;
						  }
					  }
				  };	
					  var adjustWidth = function() {
					    meterBar = document.getElementsByClassName('meterBar');
					    for (var i=0; i<meterBar.length; i++) {
					      meterBar[i].style['width'] = getPercent();
					    }
					  };
					  var indicUpdate = function() {
					    progressNumber = document.getElementsByClassName('progressNumber');
					    for (var i=0; i<progressNumber.length; i++) {
					      progressNumber[i].innerHTML = valueGetter();
					    }
					  };

					  adjustWidth();
					  indicUpdate();
					})();
				
				
				if (starRate == 0) {//평가 별점이 0점일때 삭제 모달창

					$('.show-modalDeleteStar').show(function() {
						$('.modalDeleteStar').fadeIn('fast');
						$('.modalDeleteStar').fadeOut('slow');
					});

				} else {//평가별점이 0점이 아닐때 등록 모달창 

					$('.show-modalStar').show(function() {
						$('.modalStar').fadeIn('fast');
						$('.modalStar').fadeOut('slow');
					});
				}
			}//데이터값이 0이 아닐
		}
	});
}

$(document).ready(function() {
	$("#button").hide();
	$("#showButton").click(function(event) {
		$("#button").show();
	});
	
});
