/**
 * 
 */

$(document).ready(function() {
	$("#webtoonList").stupidtable();
});

$(document)
		.ready(
				function() {
					$('.keywordBtn')
							.click(
									function() {

										var webtoonID = $(this).parents("td")
												.siblings("td:first").text()
										console.log("webtoonID = " + webtoonID);

										$
												.ajax({
													type : "POST",
													url : "manager",
													datatype : "json",
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													data : {
														todo : "getKeywordByWebtoonID",
														webtoonID : webtoonID
													},
													success : function(data) {
														var json = $
																.parseJSON(data);
														console.log('제이썬 시작 ');
														console.log(json);

														$("#keywords div")
																.remove();

														for (var i = 0; i < json.length; i++) {
															var name = json[i].keywords_name;
															var keywords_id_fk = json[i].keywords_id_fk;

															$('#keywords')
																	.append(
																			'<div class="col-md-3"><div class="inputDiv"><label><input class="inputbox" type="checkbox"><span>'
																					+ name
																					+ '</span></label></div></div>');
															$(
																	'.inputbox')
																	.attr(
																			"value",
																			keywords_id_fk);
														}

														$('.keywordBtn').attr(
																'data-target',
																"#myModal");

													}

												});
									});
				});

function filter() {
	if ($('#txtFilter').val() == "")
		$("#webtoonList tr").css('display', '');
	else {
		$("#webtoonList tr").css('display', 'none');
		$("#webtoonList th").css('display', '');
		$("#webtoonList tr[name*='" + $('#txtFilter').val() + "']").css(
				'display', '');
	}
	return false;
}
