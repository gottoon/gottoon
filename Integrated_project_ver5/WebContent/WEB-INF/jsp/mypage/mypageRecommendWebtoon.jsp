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

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">


<title>MYPAGE RECOMMEND</title>
</head>
<body>
	<%-- MYPAGE RECOMMEND --%>
	<c:if test="${sessionRecommend!=null}">
		<section>
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
		</section>
	</c:if>

	<c:if test="${sessionRecommendAuthor!=null}">
		<form name="MypageRecommendAuthorTap"
			action="<c:url value='/action/mypageRecommendWebtoon'/>"
			method="POST">
			<c:if test="${sessionOnoff=='0'}">
				<p>작가 추천이 꺼져있습니다. 추천을 원하시면 설정에서 ON으로 바꿔주세요.</p>
			</c:if>

			<c:if test="${sessionOnoff=='1'}">
				<input type="hidden" name="todo" value="searchAuthor">
		작가 : <select name=author>
					<c:forEach var="AuthorVO" items="${author}">
						<option value="${AuthorVO.authors_id_pk}">${AuthorVO.authors_name}</option>
					</c:forEach>
					<input type="submit" value="검색">
				</select>

				<c:if test="${recommendAuthorWebtoon!=null}">
					<table border='1' cellpadding='6'>
						<tr>
							<th>TITLE</th>
							<th>DAYS</th>
							<th>SUMMARY</th>
							<th>PUBLISHER</th>
							<th>URL</th>
						</tr>
						<c:forEach var="WebtoonVO" items="${recommendAuthorWebtoon}">
							<tr>
								<td>${WebtoonVO.webtoons_title}
								<td>
								<td>${WebtoonVO.webtoons_days}</td>
								<td>${WebtoonVO.webtoons_summary}</td>
								<td>${WebtoonVO.webtoons_publisher}</td>
								<td><a href="${WebtoonVO.webtoons_url}" target="_blank">바로보기</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</c:if>
		</form>
	</c:if>


	<c:if test="${sessionRecommendNew!=null}">
		<form name="MypageRecommendNewTap"
			action="<c:url value='/action/mypageRecommendWebtoon'/>"
			method="POST">
			<c:if test="${sessionOnoff=='0'}">
				<p>신작 추천이 꺼져있습니다. 추천을 원하시면 설정에서 ON으로 바꿔주세요.</p>
			</c:if>

			<c:if test="${sessionOnoff=='1'}">

				<h3>네이버 신작</h3>

				<section class="container">
					<table border='0' cellpadding='0' align="center">
						<tr>
							<td><c:forEach var="WebtoonVO"
									items="${recommendNewWebtoon}">
									<div class="col-xs-10 col-md-10">
										<div class="thumbnail">
											<div class="caption">
												<article>
													<!-- 이미지 경로 때문에 nullpointer 뜸 -->
													<a href="${WebtoonVO.webtoons_url}" target="_blank"><img
														src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>"
														width="100%" height="100%" border="0"></a> <br> <br>
													href="${WebtoonVO.webtoons_url}"
													target="_blank">${WebtoonVO.webtoons_title}${WebtoonVO.webtoons_days}${WebtoonVO.webtoons_summary}</a>
												</article>
											</div>
										</div>
									</div>
								</c:forEach></td>
						</tr>
					</table>
				</section>

				<hr>
				<h3>다음 신작</h3>

				<section class="container">
					<table border='0' cellpadding='0' align="center">
						<tr>
							<td><c:forEach var="WebtoonVO"
									items="${recommendNewWebtoon}">
									<div class="col-xs-10 col-md-10">
										<div class="thumbnail">
											<div class="caption">
												<article>
													<!-- 이미지 경로 때문에 nullpointer 뜸 -->
													<a href="${WebtoonVO.webtoons_url}" target="_blank"><img
														src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>"
														width="100%" height="100%" border="0"></a> <br> <br>
													href="${WebtoonVO.webtoons_url}"
													target="_blank">${WebtoonVO.webtoons_title}${WebtoonVO.webtoons_days}${WebtoonVO.webtoons_summary}</a>
												</article>
											</div>
										</div>
									</div>
								</c:forEach></td>
						</tr>
					</table>
				</section>
			</c:if>
		</form>
	</c:if>



	<c:if test="${sessionWishList!=null}">
		<c:if test="${fn:length(wishList)==0}">
			<hr>
			<p>찜한 웹툰이 없습니다.</p>
		</c:if>

		<section class="container">
			<table border='0' cellpadding='0' align="center">
				<hr>
				<tr>
					<td><c:forEach var="WebtoonVO" items="${wishList}">
							<div class="col-xs-6 col-md-4">
								<div class="thumbnail">
									<div class="caption">
										<article>
											<!-- 이미지 경로 때문에 nullpointer 뜸 -->
											<a href="${WebtoonVO.webtoons_url}" target="_blank"><img
												src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>"
												width="100%" height="100%" border="0"></a> <br> <a
												href="${WebtoonVO.webtoons_url}" target="_blank">${WebtoonVO.webtoons_title}</a>
										</article>
									</div>
								</div>
							</div>
						</c:forEach></td>
				</tr>
			</table>
		</section>
	</c:if>
</body>
</html>