/**
 * 
 */
$(document).ready(function() {
	$("#userList").stupidtable();
});
$(document)
		.ready(
				function() {
					$('.keywordBtn')
							.click(
									function() {

										var userID = $(this).parents("td")
												.siblings("td:nth-of-type(2)")
												.attr('id');
										console.log("userID = " + userID);

										$
												.ajax({
													type : "POST",
													url : "manager",
													datatype : "json",
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													data : {
														todo : "getKeywordByUserID",
														userID : userID
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
															var count = json[i].count * 13;

															$('#keywords')
																	.append(

																			'<div class="col-md-3"><div class="inputDiv"><label><input class="inputbox" type="checkbox"><span>'
																					+ name
																					+ '</span></label></div></div>');
															$('.inputbox')
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
		$("#userList tr").css('display', '');
	else {
		$("#userList tr").css('display', 'none');
		$("#userList th").css('display', '');
		$("#userList tr[name*='" + $('#txtFilter').val() + "']").css('display',
				'');
	}
	return false;
}

// input 값 초기화
function clearInput(input) {
	if (input.defaultValue == input.value) {
		input.value = "";
	}
}
