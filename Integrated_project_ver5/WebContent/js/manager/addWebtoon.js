$(document).ready(function() {
	
	$('#webtoons_first_update').datepicker();
	
	
	$('.information').popover('hide');
	 
	 
	 $(document).ajaxStart(function() {
			$("#wait").css("display", "block");
		});
		$(document).ajaxComplete(function() {
			$("#wait").css("display","none");
		});
		$("button").click(function() {
			$("#txt").load("demo_ajax_load.asp");
		});
	 
	
	
	//작가모달 버튼 
	$('#authorBtn').click(function(event){
		event.preventDefault();
		
		$
		.ajax({
			type : "POST",
			url : "manager",
			datatype : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				todo : "getAuthors",
			},
			success : function(data) {
				var json = $
						.parseJSON(data);
				console.log('제이썬 시작 ');
				$("#authors div")
						.remove();
				for (var i = 0; i < json.length; i++) {
					var authors_id_pk = i+1;
					var authors_name = json[i];
					

					$('#authors')
							.append(
									'<div class="col-md-3"><div class="inputDiv"><label><input id="authors_id'+i+'" type="checkbox" name="author"><span>'
									+ authors_name
									+ '</span></label></div></div>');
					$('#authors_id'+i)
					.attr(
							"value",
							authors_id_pk);
				}

				
			}

		});
		
		
	});
	//작가모달 버튼
	
	
//작가추가 버튼
	$('#modalSave').click(function(){
		
		
		var author_id_pk = "";
		var author_name = "";
		$("input[name='author']:checkbox:checked").each(function() {
			author_id_pk += $(this).val() + ",";
			author_name += $(this).siblings('span').text() + " ";
		});
		
		if($("input[name='authors_id']").attr('value').indexOf(author_id_pk) == -1 ){
			$("input[name='authors_id']").attr('value', $("input[name='authors_id']").val() + author_id_pk );
			$('#authorName').append('<p>'+author_name+'</p>');
			
		}
		
		
		
	});
//작가추가 버튼	
	
//작가 리셋
$('#authorReset').click(function(){
	$('input[name="authors_id"]').attr('value','');
	$('#authorName p').remove();
});



	//장르모달 버튼 
	$('#genreBtn').click(function(event){
		event.preventDefault();
		
		$
		.ajax({
			type : "POST",
			url : "manager",
			datatype : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				todo : "getAllGenresJson",
			},
			success : function(data) {
				var json = $
				.parseJSON(data);
				console.log('제이썬 시작 ');
				$("#genres div")
				.remove();
				for (var i = 0; i < json.length; i++) {
					var genres_id_pk = i+1;
					var genres_name = json[i];
					
					
					$('#genres')
					.append(
							'<div class="col-md-3"><div class="inputDiv"><label><input id="genres_id'+i+'" type="checkbox" name="genre"><span>'
							+ genres_name
							+ '</span></label></div></div>');
					$('#genres_id'+i)
					.attr(
							"value",
							genres_id_pk);
				}
				
				
			}
			
		});
		
		
	});
	//장르모달 버튼
	
	
//장르 세이브 버튼
	$('#genreModalSave').click(function(){
		
		
		var genre_id_pk = "";
		var genre_name = "";
		$("input[name='genre']:checkbox:checked").each(function() {
			genre_id_pk += $(this).val() + ",";
			genre_name += $(this).siblings('span').text() + " ";
		});
		
		if($("input[name='genres_id']").attr('value').indexOf(genre_id_pk) == -1 ){
			$("input[name='genres_id']").attr('value', $("input[name='genres_id']").val() + genre_id_pk );
			$('#genreName').append('<p>'+genre_name+'</p>');
			
		}
		
		
		
	});
//장르 세이브 버튼	
	
//장르 리셋
	$('#genreReset').click(function(){
		$('input[name="genres_id"]').attr('value','');
		$('#genreName p').remove();
	});
	

//키워드 버튼 
$('#keywordBtn').click(function(event){
	event.preventDefault();
	
	$
	.ajax({
		type : "POST",
		url : "manager",
		datatype : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			todo : "getAllKeywordsJson",
		},
		success : function(data) {
			
			
			var json = $
					.parseJSON(data);
			console.log('제이썬 시작 ');
			
			$("#keywords div")
					.remove();
			for (var i = 0; i < json.length; i++) {
				var keywords_id_pk = i+1;
				var keywords_name = json[i];
				

				$('#keywords')
						.append(
								'<div class="col-md-3"><div class="inputDiv"><label><input id="keyowrd_id'+i+'" type="checkbox" name="keyword"><span>'
								+ keywords_name
								+ '</span></label></div></div>');
				$('#keyowrd_id'+i)
				.attr(
						"value",
						keywords_id_pk);
			}

			
		}

	});
	
	
});
//키워드추가 버튼
$('#keywordModalSave').click(function(){
	
	
	var keyword_id_pk = "";
	var keywords_name = "";
	$("input[name='keyword']:checkbox:checked").each(function() {
		keyword_id_pk += $(this).val() + ",";
		keywords_name += $(this).siblings('span').text() + " ";
	});
	
	if($("input[name='keywords_id']").attr('value').indexOf(keyword_id_pk) == -1 ){
		$("input[name='keywords_id']").attr('value', $("input[name='keywords_id']").val() + keyword_id_pk );
		$('#keywordName').append('<p>'+keywords_name+'</p>');
		
	}
	
	
	
});
//키워드추가 버튼	

//키워드 리셋
$('#keywordReset').click(function(){
	$('input[name="keywords_id"]').attr('value','');
	$('#keywordName p').remove();
});	


//제공처 모달 
$('#publisherBtn').click(function(event){
	event.preventDefault();
	
	$
	.ajax({
		type : "POST",
		url : "manager",
		datatype : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			todo : "getAllPublishersJson",
		},
		success : function(data) {
			
			
			var json = $
			.parseJSON(data);
			console.log('제이썬 시작 ');
			$("#publisher div")
			.remove();
			for (var i = 0; i < json.length; i++) {
				var publishers_id_pk = json[i].publishers_id_pk;
				var publishers_name = json[i].publishers_name;
				
				
				$('#publisher')
				.append(
						'<div class="col-md-3"><div class="inputDiv"><label><input id="publishers_id'+i+'" type="checkbox" name="publisher"><span>'
						+ publishers_name
						+ '</span></label></div></div>');
				$('#publishers_id'+i)
				.attr(
						"value",
						publishers_id_pk);
			}
			
			
		}
		
	});
	
	
});
//제공처추가 버튼
$('#publisherModalSave').click(function(){
	
	
	var publishers_id_pk = "";
	var publishers_name = "";
	$("input[name='publisher']:checkbox:checked").each(function() {
		publishers_id_pk += $(this).val() + ",";
		publishers_name += $(this).siblings('span').text() + " ";
		console.log(publishers_id_pk , publishers_name);
	});
	
	if($("input[name='publishers_id']").attr('value').indexOf(publishers_id_pk) == -1 ){
		$("input[name='publishers_id']").attr('value', $("input[name='publishers_id']").val() + publishers_id_pk );
		$('#publisherName').append('<p>'+publishers_name+'</p>');
		
	}
	
	
	
});
//제공처추가 버튼	

//제공처 리셋
$('#publisherReset').click(function(){
	$('input[name="publishers_id"]').attr('value','');
	$('#publisherName p').remove();
});	
	
//확인버튼 활성화





	
//웹툰 추가버튼
	$('#saveBtn').click(function addWebtoon(event) {
		$('#confirmWebtoon article div').empty();
		event.preventDefault();
		
		var webtoons_first_update = $("#webtoons_first_update").val();
		var webtoons_title = $('#webtoons_title').val();
		var webtoons_summary = $('#webtoons_summary').val();
		var authors_id = $('input[name="authors_id"]').attr('value');
		var authors_name = $('#authorName p').text();
		var keywords_id = $('input[name="keywords_id"]').attr('value');
		var keywords_name = $('#keywordName p ').text();
		var publishers_id = $('input[name="publishers_id"]').attr('value');
		var publishers_name = $('#publisherName p ').text();
		var genres_id = $('input[name="genres_id"]').attr('value');
		var genres_name = $('#genreName p ').text();
		var webtoons_url = $('#webtoons_url').val();
		
		console.log("제목 " +webtoons_title);
		console.log("줄거리"+ webtoons_summary);
		console.log("작가아디 "+ authors_id);
		console.log("키워드 아디 "+ keywords_id);
		console.log("제공처아디 "+publishers_id);
		console.log("장르 아디"+genres_id);
		console.log("유알엘 " + webtoons_url);
		console.log("연재 시작일"+ webtoons_first_update);
		
		var webtoons_update_days = "";
		$("input[name='webtoons_update_days']:checkbox:checked").each(function() {
			webtoons_update_days += $(this).val() + ",";
			console.log("연재일 =" + webtoons_update_days);
		});
		var webtoons_completed = $('input[name="webtoons_completed"]:radio:checked').val();
		var webtoons_viewfree = $('input[name="webtoons_viewfree"]:radio:checked').val();
		var webtoons_pgrating = $('input[name="webtoons_pgrating"]:radio:checked').val();
		var webtoons_professional = $('input[name="webtoons_professional"]:radio:checked').val();
		
		if(webtoons_professional == "true"){
			webtoons_professional = "프로";
		}else if(webtoons_professional == "false"){
			webtoons_professional = "아마추어";
		}
		
		
		$('#confirmTitle').append("<h2>"+webtoons_title+"</h2>");
		$('#confirmSummary').append("<p>"+webtoons_summary+"</p>");
		$('#confirmAuthor').append("<h4>"+authors_name+"</h4>");
		$('#Keyword').append("<p>"+keywords_name+"</p>");
		$('#confirmgenre').append("<h2>"+genres_name+"</h2>");
		$('#confirmUpdate_days').append("<h2>"+webtoons_update_days+"</h2>");
		$('#confirmCompleted').append("<h2>"+webtoons_completed+"</h2>");
		$('#confirmViewfree').append("<h2>"+webtoons_viewfree+"</h2>");
		$('#confirmProfessional').append("<h2>"+webtoons_professional+"</h2>");
		$('#confirmPgrating').append("<h2>"+webtoons_pgrating+"</h2>");
		$('#confirmFirst_update_day').append("<p>"+webtoons_first_update+"</p>");
		$('#confirmUrl').append("<p>"+webtoons_url+"</p>");
		
		
		
		
		$('#confirmModalSave').click(function(){
			 $.ajax({
				 type:"POST",
				 url: "email",
				 data:{
					 todo: "send",
					 webtoons_title : webtoons_title,
					 webtoons_summary : webtoons_summary,
					 authors_id : authors_id,
					 author_name: authors_name,
					 keywords_id : keywords_id,
					 keywords_name : keywords_name,
					 genres_id : genres_id,
					 genres_name : genres_name,
					 webtoons_url : webtoons_url,
					 webtoons_update_days  :webtoons_update_days ,
					 webtoons_completed : webtoons_completed,
					 webtoons_viewfree : webtoons_viewfree,
					 webtoons_professional :webtoons_professional,
					 webtoons_pgrating : webtoons_pgrating,
					 webtoons_first_update : webtoons_first_update
				 },
				 success: function(data){
					 
					 
				 }
			 });
		});
		});
});



//ajax wait

	
	
