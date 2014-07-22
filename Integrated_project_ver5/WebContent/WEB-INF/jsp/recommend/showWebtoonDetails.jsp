<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Show Recommended Webtoons</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/css/recommend.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/recommend.js'/>"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${webtoonDetail.webtoons_title}</h1>
		</div>
		<div id="section">
			<div id="common_webtoon_info">
				<div id="add_label">
					<table>
						<tr>
							<td>
								<div class="label">
									<div class="label_container">
										<div class="left_label"></div>
										<div class="right_label"></div>
									</div>
								</div>
								<img id="webtoon_image" src="${webtoonDetail.webtoons_main_image}" />
							</td>
						</tr>
					</table>
				</div>
				<div id="option">
					<table id="option_table" border="1" cellpadding="6">
						<tr>
							<td width="40%">
								별점주기
							</td>
							<td>
								<%-- <c:import url="/WEB-INF/jsp/star.jsp"></c:import> --%>
								<div class="product-review-stars" align="center">
									<input type="radio" id="${status.count*status.count*status.count+1}" name="rating" 
										value="5^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
									<label for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
									<input type="radio"	id="${status.count*status.count*status.count+2}" name="rating"
										value="4^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
									<label for="${status.count*status.count*status.count+2}" title="Pretty good">★</label>
									<input type="radio"	id="${status.count*status.count*status.count+3}" name="rating"
										value="3^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
									<label for="${status.count*status.count*status.count+3}" title="Meh">★</label>
									<input type="radio"	id="${status.count*status.count*status.count+4}" name="rating"
										value="2^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
									<label for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
									<input type="radio"	id="${status.count*status.count*status.count+5}" name="rating"
										value="1^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
									<label for="${status.count*status.count*status.count+5}" title="Sucks big time">★</label>	
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<form method="post" action="#">
									<input type="hidden" name="webtoon_id" value="${webtoonDetail.webtoons_id_pk}" />
									<input type="button" id="reserve" value="찜하기" onclick="seeReserve(this.form);" />
								</form>
							</td>
							<td>
								<a href="${webtoonDetail.webtoons_url}">
									<button>바로보기</button>
								</a>
							</td>
						</tr>
						<tr>
							<td>평균별점</td>
							<td>${webtoonDetail.webtoons_average_rate}</td>
						</tr>
					</table>
				</div>
			</div>
			<br />
			<div id="other_webtoon_info">
				<table border="1" cellpadding="6">
					<tr>
						<th>장르</th>
						<td>${webtoonDetail.genres_name}</td>
						<th>작가</th>
						<td>${authorsName}</td>
					</tr>
					<tr>
						<th>유/무료</th>
						<td>${webtoonDetail.webtoon_viewfree}</td>
						<th>완결유무</th>
						<td>${webtoonDetail.webtoons_completed}</td>
					</tr>
					<tr>
						<th>연재요일</th>
						<td colspan="3">${webtoonDetail.webtoons_update_days}</td>
					</tr>
					<tr>
						<th>작가구분</th>
						<td>${webtoonDetail.webtoon_professional}</td>
						<th>관람등급</th>
						<td>${webtoonDetail.webtoons_pgrating}</td>
					</tr>
					<tr>
						<th>제공처</th>
						<td>${webtoonDetail.webtoons_publisher}</td>
						<th>연재시작일</th>
						<td>${webtoonDetail.webtoons_first_update}</td>
					</tr>
					<tr>
						<th colspan="4">줄거리</th>
					</tr>
					<tr>
						<td colspan="4">${webtoonDetail.webtoons_summary}</td>
					</tr>
				</table>
			</div>
		</div>
		<br />
		<div id="footer">
			<button onclick="goBack();">뒤로가기</button>
		</div>
	</div>
	
	<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>	
	
	<script>
	if ("${webtoonDetail.webtoons_completed}" === '완')
		$('.left_label').after('<img class="end_label" src="../img/labels/end.png" />');
	
	if (calculateDateRange("${webtoonDetail.webtoons_first_update}"))
		$('.left_label').after('<img class="new_label" src="../img/labels/new.png" />');
	
	getHighRatedWebtoonsAuthor("${authorsName}", "${webtoonDetail.webtoons_id_pk}", function(result) {
		$('.right_label').append('<div class="author_label">'+ result + ' 작가웹툰!!</div>');
	});
	</script>
</body>
</html>