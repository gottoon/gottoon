/**
 * 
 */
$(document).ready(function() {
	$('#addKeywordBtn').click(function() {
		$.ajax({
			type : 'POST',
			url : '/manager',

			data : {
				todo : "addKeyword",

			},
			success : function(data) {

				$(document).html(data);
			}
		});
	});
});



$(document).ready(function() {
	$('#keywords button').click(function(e) {
		e.preventDefault();
	});
});

$(document)
		.ready(
				function() {
					$('.keywordBtn')
							.click(
									function() {

										var keywordID = $(this)
												.siblings("input").attr('value');
										console.log("keywordID = " + keywordID);

										$
												.ajax({
													type : "POST",
													url : "manager",
													datatype : "json",
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													data : {
														todo : "getWebtoonsByKeywordID",
														keywordID : keywordID
													},
													success : function(data) {
														var json = $
																.parseJSON(data);
														console.log('제이썬 시작 ');
														console.log(json);

														$("#webtoons div")
																.remove();

														for (var i = 0; i < json.length; i++) {
															var webtoonTitle = json[i].webtoons_title;
															console.log(webtoonTitle);
															var keywords_id_pk = json[i].keywords_id_pk;

															$('#webtoons')
																	.append(
																			'<div class="ck-button"> <label> <input type="checkbox"><span>'
																					+ webtoonTitle
																					+ '</span> </label> </div>');
															$(
																	'.ck-button input')
																	.attr(
																			"value",
																			keywords_id_pk);
														}

														$('.keywordBtn').attr(
																'data-target',
																"#myModal2");

													}

												});
									});
				});
