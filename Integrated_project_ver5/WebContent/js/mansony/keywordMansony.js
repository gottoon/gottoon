/**
 * 
 */
// http://masonry.desandro.com/masonry.pkgd.js added as external resource
// create <div class="item"></div>
$(document).ready(function() {
	var numRand = Math.floor(Math.random() * 5);
	console.log("랜덤" + numRand);
	if (numRand == 1 || numRand == 0) {
		$(".masonry div").attr('class', 'item h2');
		$(".masonry div:nth-child(2n)").attr('class', 'item h2');
		$(".masonry div:nth-child(3n)").attr('class', 'item w2');
		$(".masonry div:nth-child(5n)").attr('class', 'item w3');
		$(".masonry div:nth-child(7n)").attr('class', 'item h3');
	} else if (numRand == 2) {
		$(".masonry div").attr('class', 'item h2');
		$(".masonry div:nth-child(2n)").attr('class', 'item w2');
		$(".masonry div:nth-child(3n)").attr('class', 'item h3');
		$(".masonry div:nth-child(8n)").attr('class', 'item w3');
		$(".masonry div:nth-child(5n)").attr('class', 'item h4');
	} else if (numRand == 3) {
		$(".masonry div").attr('class', 'item h2');
		$(".masonry div:nth-child(2n)").attr('class', 'item w3');
		$(".masonry div:nth-child(3n)").attr('class', 'item h3');
		$(".masonry div:nth-child(3n)").attr('class', 'item w3');
		$(".masonry div:nth-child(3n)").attr('class', 'item h4');
	} else if (numRand == 4) {
		$(".masonry div").attr('class', 'item h2');
		$(".masonry div:nth-child(2n)").attr('class', 'item h3');
		$(".masonry div:nth-child(3n)").attr('class', 'item w4');
		$(".masonry div:nth-child(9n)").attr('class', 'item w3');
		$(".masonry div:nth-child(10n)").attr('class', 'item h4');
	}

});

var container = document.querySelector('#masony');
var msnry = new Masonry(container, {
	// options
	columnWidth : 200,
	itemSelector : '.item'
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

										var keywordID = $(this).siblings(
												"input").attr('value');
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
															console
																	.log(webtoonTitle);
															var keywords_id_pk = json[i].keywords_id_pk;

															$('#webtoons')
																	.append(
																			' <div class="col-md-3"><div class="inputDiv"><label><input class="inputbox" type="checkbox"><span>'
																					+ webtoonTitle
																					+ '</span></label></div></div>');
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
