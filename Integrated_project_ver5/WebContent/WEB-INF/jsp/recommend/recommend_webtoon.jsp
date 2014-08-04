<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewprot" content="width=device-width">
<link rel="stylesheet" href="<c:url value='/css/recommend.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/recommendStar.css'/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/recommend.js'/>"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
<script src="<c:url value='/js/webtoonDetailAndStarPoint.js'/>"></script>


<title>Show Recommended Webtoons</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>


	<div class="nav">
		<ul class="viewfree-nav">
			<li>
				<form method="post" action="<c:url value='/action/recommend'/>">
					<input type="hidden" name="filterviewfree" value="null" /> <input
						class="viewfree-button" type="submit" value="전체 웹툰" />
				</form>
			</li>
			<li>
				<form method="post" action="<c:url value='/action/recommend'/>">
					<input type="hidden" name="filterviewfree" value="false" /> <input
						class="viewfree-button" type="submit" value="유료 웹툰" />
				</form>
			</li>
			<li>
				<form method="post" action="<c:url value='/action/recommend'/>">
					<input type="hidden" name="filterviewfree" value="true" /> <input
						class="viewfree-button" type="submit" value="무료 웹툰" />
				</form>
			</li>
		</ul>
	</div>

	<div class="section" id="webtoons">
		<c:forEach var="webtoonInfo" items="${recommendWebtoons}"
			varStatus="status">
			<div class="webtoon">
				<div class="label_div">
					<div class="main_image_div">
						<form method="post" action="webtoon" class="detail_form">
							<input type="hidden" name="webtoon_id"
								value="${webtoonInfo.webtoons_id_pk}" /> <input type="hidden"
								name="todo" value="showWebtoonDetails" />
							<button class="submit" type="submit">
								<img class="main_image"
									src="${webtoonInfo.webtoons_title_image}">
							</button>
						</form>

					</div>
					<c:if test="${webtoonInfo.webtoons_completed == '완'}">
						<div class="ribbon-wrapper">
							<div class="ribbon-end">완결</div>
						</div>
					</c:if>
					<jsp:useBean id="now" class="java.util.Date" />
					<c:if test="${webtoonInfo.webtoons_first_update gt now}">
						<div class="ribbon-wrapper">
							<div class="ribbon-new">NEW!</div>
						</div>
					</c:if>
				</div>
				<div class="toonInforDiv">
					<span class="image_title">${webtoonInfo.webtoons_title}</span>
					<div class="star">
						<form id="myForm">
							<input type="hidden" id="rate" name="rate"
								value="${webtoonDetail.webtoon_rate}" /> <input type="hidden"
								id="id" name="webtoons_id_pk"
								value="${webtoonInfo.webtoons_id_pk}" /> <input type="hidden"
								name="webtoons_title" value="${webtoonInfo.webtoons_title}" />
							<div class="product-review-stars">
								<input type="checkbox"
									id="${status.count*status.count*status.count+1}" name="rating"
									value="5^${webtoonInfo.webtoons_id_pk}"
									onclick=onclickStart(this) class="visuallyhidden"> <label
									for="${status.count*status.count*status.count+1}"
									title="Rocks!">★</label> <input type="checkbox"
									id="${status.count*status.count*status.count+2}" name="rating"
									value="4^${webtoonInfo.webtoons_id_pk}"
									onclick=onclickStart(this) class="visuallyhidden"> <label
									for="${status.count*status.count*status.count+2}"
									title="Pretty good">★</label> <input type="checkbox"
									id="${status.count*status.count*status.count+3}" name="rating"
									value="3^${webtoonInfo.webtoons_id_pk}"
									onclick=onclickStart(this) class="visuallyhidden"> <label
									for="${status.count*status.count*status.count+3}" title="Meh">★</label>
								<input type="checkbox"
									id="${status.count*status.count*status.count+4}" name="rating"
									value="2^${webtoonInfo.webtoons_id_pk}"
									onclick=onclickStart(this) class="visuallyhidden"> <label
									for="${status.count*status.count*status.count+4}"
									title="Kinda bad">★</label> <input type="checkbox"
									id="${status.count*status.count*status.count+5}" name="rating"
									value="1^${webtoonInfo.webtoons_id_pk}"
									onclick=onclickStart(this) class="visuallyhidden"> <label
									for="${status.count*status.count*status.count+5}"
									title="Sucks big time">★</label>
							</div>
						</form>
					</div>
					<div class="machingRateDiv">
						<ul>
							<li><span class="myWebtoon_title">
									${webtoonInfo.myWebtoon_title}</span> 의 뒤를 이을 웹툰!!</li>
							<li>추천인이 <span class="recommender_matching_percent">${webtoonInfo.recommender_matching_percent}%</span>
								보장합니다!
							</li>
							<li>내가 좋아하는 웹툰들과의 연관성 <span class="relative_matching_percent">${webtoonInfo.relative_matching_percent}%</span></li>
						</ul>
					</div>
					<div class="option-button">
						<ul>
							<li><div class="reserve">
									<form method="post" action="#">
										<input type="hidden" name="webtoon_id"
											value="${webtoonInfo.webtoons_id_pk}" />
										<div class="heart">
											<input type="checkbox" id="${webtoonInfo.webtoons_title}"
												class="visuallyhiddenHeart" name="reserve"
												onclick="seeReserve(this.form, this.id)" /> <label
												for="${webtoonInfo.webtoons_title}" title="reserve_heart">❤</label>
										</div>
									</form>
								</div></li>
							<li>
								<div class="view-now">
									<a href="${webtoonInfo.webtoons_url}">
										<button class="button">
											<span class="right-now">바로보기</span>
										</button>
									</a>
								</div>
							</li>
						</ul>
					</div>

				</div>
			</div>
		</c:forEach>
	</div>

	<script type="text/javascript">
		var webtoonCount = 0;
		var num = 0;
		var viewCount = 5;

		$(document).ready(
				function() {
					getHighRatedWebtoonsAuthor("${webtoonInfo.authors_name}",
							"${webtoonInfo.webtoons_id_pk}", function(result) {
								label_index = "${status.count}";
								$('.label_div').eq(label_index - 1).append(
										'<div class="author_label"><span class="author_span">'
												+ result
												+ '<br />작가웹툰!!</span></div>');
							});
				});
	</script>
</body>
</html>
