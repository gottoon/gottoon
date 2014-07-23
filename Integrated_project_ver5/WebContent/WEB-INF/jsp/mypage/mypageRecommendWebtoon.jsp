<%-- MYPAGE RECOMMEND WEBTOON --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, mypkg.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="mypage.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/myPageStar.css'/>" />

<script src="<c:url value='/js/MyPageAndStarPoint.js'/>"></script>

<style>
h2, div {
	font-size: 20px;
	text-align: center;
}

</style>
<title>MYPAGE RECOMMEND</title>
</head>
<body>
	<%-- MYPAGE RECOMMEND --%>
	<%--<section>
			<ul>
				 <section>
					<form>
						<h3>
							친구 추천 :
							<button class="btn btn-success" type="submit">추천 받기</button>
						</h3>
					</form>
				</section>

				<hr>
				<section>
					<form name="MypageRecommendTap"
						action="<c:url value='/action/mypageRecommendWebtoon'/>"
						method="POST">
						<input type="hidden" name="todo" value="RecommendAuthor">
						<h3>
							작가 추천 :
							<button class="btn btn-success" type="submit">추천 받기</button>
						</h3>
					</form>
				</section>

				<hr>
				<section>
					<form name="MypageRecommendTap"
						action="<c:url value='/action/mypageRecommendWebtoon'/>"
						method="POST">
						<input type="hidden" name="todo" value="recommendNew">
						<h3>
							신작 웹툰 :
							<button class="btn btn-success" type="submit">신작 웹툰 보기</button>
						</h3>
					</form>
				</section>

				<hr>
				<section>
					<form name="MypageRecommendTap"
						action="<c:url value='/action/mypageRecommendWebtoon'/>"
						method="POST">
						<input type="hidden" name="todo" value="viewWishlist">
						<h3>
							찜한 웹툰 :
							<button class="btn btn-success" type="submit">찜한 웹툰 보기</button>
						</h3>
					</form>
				</section>
			</ul>
		</section>--%>

	<h2>신작 웹툰</h2>


	<c:if test="${fn:length(recommendNewWebtoon)==0}">
		<hr>
		<p>신작 웹툰이 없습니다.</p>
	</c:if>

	<c:if test="${fn:length(recommendNewWebtoon)!=0}">
		<section class="container">
			<table border='0' cellpadding='0' align="center">
				
				<tr>
					<td><c:forEach var="WebtoonVO" items="${recommendNewWebtoon}" varStatus="status">
							<c:if test="${WebtoonVO.webtoons_publisher=='네이버'}">
								<h3>네이버 신작</h3>
								<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
			rel='stylesheet' type='text/css'>
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
							<div class="thumbnail">
								<div class="caption">
									<article>

										<form method="post" action="webtoon">
										<input type="hidden" name="webtoon_id" value="${WebtoonVO.webtoons_id_pk}" />
										<input type="hidden" name="todo" value="showWebtoonDetails" />
										<input type="image" width="100%" height="100%" src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" />
										</form>

		<!-- <table id="fb-root" class="showToon" border="1"> -->


				<input type="hidden" id="rate" value="${WebtoonVO.webtoon_rate }">
					 <input type="hidden" id="id" value="${WebtoonVO.webtoons_id_pk }">
					<form id="myForm">
						<p>별점 : ${WebtoonVO.webtoon_rate }</p>
						<div class="product-review-stars">
							<input type="checkbox"
								id="${status.count*status.count*status.count+1}" name="rating"
								value="5^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+2}" name="rating"
								value="4^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+2}"
								title="Pretty good">★</label> <input type="checkbox"
								id="${status.count*status.count*status.count+3}" name="rating"
								value="3^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+4}" name="rating"
								value="2^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+5}" name="rating"
								value="1^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+5}"
								title="Sucks big time">★</label>
						</div>
					</form>
<!-- 		</table>
 -->
		<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>





										<br> <a href="${WebtoonVO.webtoons_url}" target="_blank">${WebtoonVO.webtoons_title}</a>
									</article>
								</div>
							</div>
						</div>
							</c:if>
						</c:forEach></td>
				</tr>
			</table>
		</section>

		<section class="container">
			<table border='0' cellpadding='0' align="center">
				<tr>
					<td><c:forEach var="WebtoonVO" items="${recommendNewWebtoon}" varStatus="status">
							<c:if test="${WebtoonVO.webtoons_publisher=='다음'}">
								<hr>
								<h3>다음 신작</h3>
								<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
			rel='stylesheet' type='text/css'>
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
							<div class="thumbnail">
								<div class="caption">
									<article>

										<form method="post" action="webtoon">
										<input type="hidden" name="webtoon_id" value="${WebtoonVO.webtoons_id_pk}" />
										<input type="hidden" name="todo" value="showWebtoonDetails" />
										<input type="image" width="100%" height="100%" src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" />
										</form>

		<!-- <table id="fb-root" class="showToon" border="1"> -->


				<input type="hidden" id="rate" value="${WebtoonVO.webtoon_rate }">
					 <input type="hidden" id="id" value="${WebtoonVO.webtoons_id_pk }">
					<form id="myForm">
						<p>별점 : ${WebtoonVO.webtoon_rate }</p>
						<div class="product-review-stars">
							<input type="checkbox"
								id="${status.count*status.count*status.count+1}" name="rating"
								value="5^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+2}" name="rating"
								value="4^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+2}"
								title="Pretty good">★</label> <input type="checkbox"
								id="${status.count*status.count*status.count+3}" name="rating"
								value="3^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+4}" name="rating"
								value="2^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+5}" name="rating"
								value="1^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+5}"
								title="Sucks big time">★</label>
						</div>
					</form>
<!-- 		</table>
 -->
		<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>





										<br> <a href="${WebtoonVO.webtoons_url}" target="_blank">${WebtoonVO.webtoons_title}</a>
									</article>
								</div>
							</div>
						</div>
							</c:if>
						</c:forEach></td>
				</tr>
			</table>
		</section>
	</c:if>
	
	<hr>
	<h2>내가 찜한 웹툰</h2>
	<hr>
	
	<c:if test="${fn:length(wishList)==0}">
		<hr>
		<p>찜한 웹툰이 없습니다.</p>
	</c:if>

	<section class="container">
		<table border='0' cellpadding='0' align="center">
			<tr>
				<td><c:forEach var="WebtoonVO" items="${wishList}" varStatus="status">
						<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
			rel='stylesheet' type='text/css'>
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
							<div class="thumbnail">
								<div class="caption">
									<article>

										<form method="post" action="webtoon">
										<input type="hidden" name="webtoon_id" value="${WebtoonVO.webtoons_id_pk}" />
										<input type="hidden" name="todo" value="showWebtoonDetails" />
										<input type="image" width="100%" height="100%" src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" />
										</form>

		<!-- <table id="fb-root" class="showToon" border="1"> -->


				<input type="hidden" id="rate" value="${WebtoonVO.webtoon_rate }">
					 <input type="hidden" id="id" value="${WebtoonVO.webtoons_id_pk }">
					<form id="myForm">
						<p>별점 : ${WebtoonVO.webtoon_rate }</p>
						<div class="product-review-stars">
							<input type="checkbox"
								id="${status.count*status.count*status.count+1}" name="rating"
								value="5^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+2}" name="rating"
								value="4^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+2}"
								title="Pretty good">★</label> <input type="checkbox"
								id="${status.count*status.count*status.count+3}" name="rating"
								value="3^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+4}" name="rating"
								value="2^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+5}" name="rating"
								value="1^${WebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+5}"
								title="Sucks big time">★</label>
						</div>
					</form>
<!-- 		</table>
 -->
		<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>





										<br> <a href="${WebtoonVO.webtoons_url}" target="_blank">${WebtoonVO.webtoons_title}</a>
									</article>
								</div>
							</div>
						</div>
					</c:forEach></td>
			</tr>
		</table>
	</section>
</body>
</html>