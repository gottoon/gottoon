<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Show Recommended Webtoons</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/css/details.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/recommendStar.css'/>" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/recommend.js'/>"></script>
<script src="<c:url value='/js/webtoonDetailAndStarPoint.js'/>"></script>
</head>
<body>
		<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>

		<div class="show-modalStar">
			<div class="modalStar">
				<div>저장 부아악!</div>
			</div>
		</div>
		<div class="show-modalDeleteStar">
			<div class="modalDeleteStar">
				<div>삭제  끄아악!</div>
			</div>
		</div>
	
	<div class="article">
		<div id="left-section">
			<div id="image">
				<img id="webtoon_image" src="${webtoonDetail.webtoons_details_image}" />
				<c:if test="${webtoonDetail.webtoons_completed == '완'}">
					<div class="ribbon-wrapper">
						<div class="ribbon-end">완결</div>
					</div>
				</c:if>
				<jsp:useBean id="now" class="java.util.Date" />
				<c:if test="${webtoonDetail.webtoons_first_update gt now}">
					<div class="ribbon-wrapper">
						<div class="ribbon-new">NEW!</div>
					</div>
				</c:if>
			</div>
			<div id="option-button">
				<div id="webtoon-star">
					<input type="hidden" id="rate" value="${webtoonDetail.webtoon_rate}" />
					<input type="hidden" id="id" value="${webtoonDetail.webtoons_id_pk}" />
					<form id="myForm">
						<div class="product-review-stars" align="center">
							<input type="checkbox" id="${status.count*status.count*status.count+1}" name="rating" value="5^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
							<label for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="checkbox" id="${status.count*status.count*status.count+2}" name="rating" value="4^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
							<label for="${status.count*status.count*status.count+2}" title="Pretty good">★</label>
							<input type="checkbox" id="${status.count*status.count*status.count+3}" name="rating" value="3^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
							<label for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="checkbox" id="${status.count*status.count*status.count+4}" name="rating" value="2^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
							<label for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
							<input type="checkbox" id="${status.count*status.count*status.count+5}" name="rating" value="1^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
							<label for="${status.count*status.count*status.count+5}" title="Sucks big time">★</label>	
						</div>
					</form>
				</div>
				<div id="reserve-view">
					<div id="reserve">
							<form method="post" action="#">
								<input type="hidden" name="webtoon_id" value="${webtoonDetail.webtoons_id_pk}" />
								<input type="hidden" class="reserve_value" value="${webtoonDetail.reserveValue}" />
								<input type="hidden" class="reserve_title" value="${webtoonDetail.webtoons_title}" />
								<div class="heart"><input type="checkbox" id="${webtoonDetail.webtoons_title}"
									 class="visuallyhiddenHeart" name="reserve" onclick="seeReserve(this.form, this.id)" 
									 value="0${webtoonDetail.webtoons_title}" />
									<label for="${webtoonDetail.webtoons_title}" title="reserve_heart">❤</label>
								</div>
							</form>
						</div>
						<div id="view-now">
							<a href="${webtoonDetail.webtoons_url}">
								<button class="button view-now">
									<span class="right-now">바로보기</span>
								</button>
							</a>
						</div>
				</div>
			</div>
		</div>
		<div id="right-section">
			<div id="webtoon-summary">
				${webtoonDetail.webtoons_summary}
			</div>
			<div id="webtoonInfo">
				<div id="list-title">
					<span>장르</span><br />
					<span>작가</span><br />
					<span>유료/무료</span><br />
					<span>완결유무</span><br />
					<span>연재요일</span><br />
					<span>작가구분</span><br />
					<span>관람등급</span><br />
					<span>제공처</span><br />
					<span>연재시작일</span>
				</div>
				<div id="list-contents">
					<span>${webtoonDetail.genres_name}</span><br />
					<span>${authorsName}</span><br />
					<span>${webtoonDetail.webtoon_viewfree}</span><br />
					<span>${webtoonDetail.webtoons_completed}</span><br />
					<span>${webtoonDetail.webtoons_update_days}</span><br />
					<span>${webtoonDetail.webtoon_professional}</span><br />
					<span>${webtoonDetail.webtoons_pgrating}</span><br />
					<span>${webtoonDetail.webtoons_publisher}</span><br />
					<span>${webtoonDetail.webtoons_first_update}</span>
				</div>
			</div>
		</div>
	</div>
	<div id="goBack">
		<form action="recommend" method="post">
			<input type="hidden" name="todo" value="recommendWebtoons" />
			<button class="button goBack">
				<span class="goBack-span">뒤로가기</span>
			</button>
		</form>
	</div>
</body>
</html>
