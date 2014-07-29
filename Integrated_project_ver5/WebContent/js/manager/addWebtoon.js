/**
 * 
 */


$(document).ready(function() {
	
	$('#webtoons_first_update').datepicker();

	$('#saveBtn').click(function addWebtoon(event) {

		event.preventDefault();

		// var genre_id_fk =
		// Integer.parseInt(request.getParameter("genre_id_fk"));
		
		// var webtoons_publisher = request.getParameter("webtoons_publisher");
		// var webtoons_main_image;
		// var webtoons_thumbnail;
		
		var webtoons_first_update = $("#webtoons_first_update").val();
		
		var webtoons_title = $('#webtoons_title').val();
		var webtoons_summary = $('#webtoons_summary').val();
		var authors_name = $('#authors_name').val();
		var webtoons_url = $('#webtoons_url').val();
		
		console.log(webtoons_title);
		console.log(webtoons_summary);
		console.log(authors_name);
		console.log(webtoons_url);
		console.log(webtoons_first_update);
		
		var webtoons_update_days = "";
		$("input[name='webtoons_update_days']:checkbox:checked").each(function() {
			webtoons_update_days += $(this).val() + ",";
			console.log(webtoons_update_days);
		});
		var webtoons_completed = "";
		$('input[name="webtoons_completed"]:radio:checked').each(function() {
			webtoons_completed = $(this).val();
			console.log(webtoons_completed);
		});
		var webtoons_viewfree = "";
		$('input[name="webtoons_viewfree"]:radio:checked').each(function() {
			webtoons_viewfree = $(this).val();
			console.log(webtoons_viewfree);
		});
		
		var webtoons_professional = "";
		$('input[name="webtoons_professional"]:radio:checked').each(function() {
			 webtoons_professional = $(this).val();
			console.log(webtoons_professional);
		});
		
		var webtoons_pgrating = "";
		$('input[name="webtoons_pgrating"]:radio:checked').each(function() {
			 webtoons_pgrating = $(this).val();
			console.log(webtoons_pgrating);
		});
		
		

		 $.ajax({
			 type:"POST",
			 url: "manager",
			 data:{
				 todo: "addWebtoonInfo",
				 webtoons_title : webtoons_title,
				 webtoons_summary : webtoons_summary,
				 authors_name : authors_name,
				 webtoons_url : webtoons_url,
				 webtoons_update_days  :webtoons_update_days ,
				 webtoons_completed : webtoons_completed,
				 webtoons_viewfree : webtoons_viewfree,
				 webtoons_professional :webtoons_professional,
				 webtoons_pgrating : webtoons_pgrating
			 },
			 success: function(){
				 $(document).html('1');
			 }
		 });
	});

});