<%-- MYPAGE READ WEBTOON --%>
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
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />

<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/myPageStar.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypage.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypageContents.css'/>" />

<title>MYPAGE READ WEBTOON</title>
</head>

<body>
	<%-- MYPAGE READ WEBTOON --%>

	<div class="show-modalStar">
		<div class="modalStar">
			<div>저장 부아악!</div>
		</div>
	</div>

	<div class="show-modalDeleteStar">
		<div class="modalDeleteStar">
			<div>삭제 끄아악!</div>
		</div>
	</div>

	<section>
		<div class="state">
			<div id="state_image">
				<div class="gradeImg">
					<div id="level">
						<div>
							<p id="grade" />
						</div>
					</div>
				</div>

				<div id="state_gauge">
					<div>
						<h2>지금까지 총 ${fn:length(readToon)} 편의 웹툰을 보셨습니다.</h2>

						<div id="gauge_meter">
							<meter id="gauge" low=35 high=70 max=100 value=0
								style="display: none;">
								<!-- <input name="showGauge" /> -->
							</meter>
						</div>

						<div id="progress">
							<div class="progress-bar">
								<canvas id="inactiveProgress" class="progress-inactive"
									height="275px" width="275px"></canvas>
								<canvas id="activeProgress" class="progress-active"
									height="275px" width="275px"></canvas>
								<p></p>
							</div>
							<div id="progressControllerContainer">
								<input type="range" id="progressController" min="0" max="100"
									value=0 />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="contains">
		<div class="gallery">
			<!-- <div class="table-responsive"> -->

			<table class="table" border='0' cellpadding='0' align="center">
				<tr>
					<td><c:forEach var="WebtoonVO" items="${readToon}"
							varStatus="status">
							<link
								href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
								rel='stylesheet' type='text/css'>
							<div class="gallery-item">
								<div class="vcard">
									<div id="image">

										<form method="post" action="webtoon">
											<input type="hidden" name="webtoon_id"
												value="${WebtoonVO.webtoons_id_pk}" /> <input type="hidden"
												name="todo" value="showWebtoonDetails" /> <input
												type="image"
												src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" alt
												class="photo" />
										</form>
									</div>
									<!-- <table id="fb-root" class="showToon" border="1"> -->
									<div id="title">
										<a id="title_link" href="${WebtoonVO.webtoons_url}"
											target="_blank"><strong>${WebtoonVO.webtoons_title}</strong></a>
									</div>

									<div id="desc">
										<input type="hidden" id="rate"
											value="${WebtoonVO.webtoon_rate }"> <input
											type="hidden" id="id" value="${WebtoonVO.webtoons_id_pk }">
										<form id="myForm">
											<div class="product-review-stars">
												<input type="checkbox"
													id="${status.count*status.count*status.count+1}"
													name="rating" value="5^${WebtoonVO.webtoons_id_pk}"
													onclick=onclickStart(this) class="visuallyhidden">
												<label for="${status.count*status.count*status.count+1}"
													title="최고에요">★</label> <input type="checkbox"
													id="${status.count*status.count*status.count+2}"
													name="rating" value="4^${WebtoonVO.webtoons_id_pk}"
													onclick=onclickStart(this) class="visuallyhidden">
												<label for="${status.count*status.count*status.count+2}"
													title="좋아요">★</label> <input type="checkbox"
													id="${status.count*status.count*status.count+3}"
													name="rating" value="3^${WebtoonVO.webtoons_id_pk}"
													onclick=onclickStart(this) class="visuallyhidden">
												<label for="${status.count*status.count*status.count+3}"
													title="그럭저럭">★</label> <input type="checkbox"
													id="${status.count*status.count*status.count+4}"
													name="rating" value="2^${WebtoonVO.webtoons_id_pk}"
													onclick=onclickStart(this) class="visuallyhidden">
												<label for="${status.count*status.count*status.count+4}"
													title="재미없음">★</label> <input type="checkbox"
													id="${status.count*status.count*status.count+5}"
													name="rating" value="1^${WebtoonVO.webtoons_id_pk}"
													onclick=onclickStart(this) class="visuallyhidden">
												<label for="${status.count*status.count*status.count+5}"
													title="시간이 아깝다">★</label>
											</div>
										</form>
										<!-- </table> -->
									</div>
								</div>
							</div>
							<!-- </div> -->
						</c:forEach></td>
				</tr>
			</table>
		</div>
	</section>

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/less.js/1.7.3/less.min.js"></script>
	<script src="<c:url value='/js/mypageReadWebtoon.js'/>"></script>
	<script src="<c:url value='/js/MyPageAndStarPoint.js'/>"></script>
	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
</body>
</html>
