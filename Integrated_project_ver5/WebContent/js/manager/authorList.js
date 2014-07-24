/**
 * 
 */

$(document).ready(function() {
	$("#authorList").stupidtable();
});

$(document).ready(function() {
	$('.authorBtn').click(function(e) {
		e.preventDefault();
	});
});



$(document)
		.ready(
				function() {
					$('.authorBtn')
							.click(
									function() {

										var authorID = $(this)
												.siblings("input").attr('value');
										console.log()
										console.log("authorID = " + authorID);

										$
												.ajax({
													type : "POST",
													url : "manager",
													datatype : "json",
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													data : {
														todo : "getWebtoonsByAuthorID",
														authorID : authorID
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
																			' <div class="checkbox"><label><input type="checkbox">'
																					+ webtoonTitle
																					+ '</label></div>');
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


function filter() {
	if ($('#txtFilter').val() == "")
		$("#authorList tr").css('display', '');
	else {
		$("#authorList tr").css('display', 'none');
		$("#authorList th").css('display', '');
		$("#authorList tr[name*='" + $('#txtFilter').val() + "']").css('display',
				'');
	}
	return false;
}

