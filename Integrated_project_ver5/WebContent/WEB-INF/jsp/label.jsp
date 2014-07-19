<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>

var completed = "${webtoonInfo.webtoons_completed}";
if (completed === '완')
	$('.left_label').eq(webtoonCount - 1).append('<img class="end_label" src="../img/labels/end.png" />');

if (calculateDateRange("${webtoonInfo.webtoons_first_update}"))
	$('.left_label').eq(webtoonCount - 1).append('<img class="new_label" src="../img/labels/new.png" />');

getHighRatedWebtoonsAuthor("${webtoonInfo.authors_name}", "${webtoonInfo.webtoons_id_pk}", function(result) {
	label_index = "${status.count}";
	$('.right_label').eq(label_index - 1).append('<div class="author_label">'+ result + ' 작가웹툰!!</div>');
	});
	
	
	
function calculateDateRange(first_update) {
	var format = "-";

	if (first_update.length != 10)
		return null;

	if (first_update.indexOf(format) < 0)
		return null;

	var update_token = first_update.split(format);
	update_token[1] = (Number(update_token[1]) - 1) + "";

	var now = new Date();

	var from_date = new Date(update_token[0], update_token[1], update_token[2]);
	var to_date = new Date(now.getFullYear(), now.getMonth(), now.getDate());

	var checkDate = (to_date.getTime() - from_date.getTime()) / 1000 / 60;
	
	if (checkDate < 0 || checkDate >= 41760) {
		return false;
	} else {
		return true;
	}
}

function getHighRatedWebtoonsAuthor(recommendAuthor, webtoon_id, callback) {
	var result;
	$.ajax({
		url : "userWebtoon",
		data : {"todo" : "authorLabel", "authorsName" : recommendAuthor},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				result = data;
				
				if (typeof callback === "function") callback(result);
			}
		}
	});
}	
</script>


</body>
</html>