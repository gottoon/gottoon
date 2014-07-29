<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/js/recommend.js'/>"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="reserve">
		<form method="post" action="#">
			<input type="hidden" name="webtoon_id"
				value="${webtoonInfo.webtoons_id_pk}" />
			<div class="heart">
				<input type="checkbox" id="${webtoonInfo.webtoons_title}"
					class="visuallyhiddenHeart" name="reserve"
					onclick=seeReserve(this.form) /> <label
					for="${webtoonInfo.webtoons_title}" title="reserve_heart">❤</label>
			</div>
		</form>
	</div>
	<div class="view-now">
		<a href="${webtoonInfo.webtoons_url}">
			<button class="button">
				<span class="right-now">바로보기</span>
			</button>
		</a>
	</div>
</body>
</html>