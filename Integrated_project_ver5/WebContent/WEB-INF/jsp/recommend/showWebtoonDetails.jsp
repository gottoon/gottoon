<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Show Recommended Webtoons</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" href="<c:url value='/css/recommend.css'/>" /> --%>
<link rel="stylesheet" href="<c:url value='/css/recommendStar.css'/>" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/recommend.js'/>"></script>
<script src="<c:url value='/js/webtoonDetailAndStarPoint.js'/>"></script>

<style>
.article {
	width : 90%;
	height : 500px;
	clear : both;
	margin : 5%;
	
}

#left-section {
	width : 50%;
	height : 100%;
	float : left;
}

#right-section {
	width : 50%;
	height : 100%;
	float : right;
}

/* img#webtoon_image {
	width : 100%;
	height : 100%;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
} */

.label_image {
	margin : 0;
	width : 100%;
	height : 100%;
	border-left: 2px solid #ccc;
	border-right: 3px solid #ccc;
	border-top: 2px solid #ccc;
	border-bottom: 10px solid #ccc;
}

.webtoonInfo {
	margin : 0;
	width : 100%;
	height : 100%;
	border-left: 3px solid #ccc;
	border-right: 10px solid #ccc;
	border-top: 2px solid #ccc;
	border-bottom: 10px solid #ccc;
}


</style>
</head>
<body>
	<div id="pgcontainer">
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
	</div>
	
	<div class="article">
		<div id="left-section">
			<table class="label_image" border="1">
				<tr>
					<td>
						<img id="webtoon_image" src="${webtoonDetail.webtoons_main_image}" />
					</td>
				</tr>
			</table>
		</div>
		<div id="right-section">
			<table class="webtoonInfo" border="1">
				<tr>
					<td>
						<p>test1</p>
			<p>test2</p>
			<p>test3</p>
			<p>test4</p>
			<p>test5</p>
			<p>test6</p>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	
	<%-- <div class="container">
		<div class="pageseparator"></div>
		<div class="two">
			<div id="add_label">
						<table>
							<tr>
								<td>
									<div class="image_label">
										<div class="image">
											<!-- width="500px" height="380px" -->
											<img id="webtoon_image" src="${webtoonDetail.webtoons_main_image}" width="500px" height="380px"/>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
			<br />
			<br />
			<p>test1</p>
			<p>test2</p>
			<p>test3</p>
			<p>test4</p>
			<p>test5</p>
			<p>test6</p>
			
		</div>
	</div> --%>
	
		<%-- <div id="container">
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
									<input type="hidden" id="rate" value="${webtoonDetail.webtoon_rate}">
						 			<input type="hidden" id="id" value="${webtoonDetail.webtoons_id_pk}">		
									<c:import url="/WEB-INF/jsp/star.jsp"></c:import>
									<div class="product-review-stars" align="center">
										<input type="checkbox" id="${status.count*status.count*status.count+1}" name="rating" 
											value="5^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
										<label for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
										<input type="checkbox"	id="${status.count*status.count*status.count+2}" name="rating"
											value="4^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
										<label for="${status.count*status.count*status.count+2}" title="Pretty good">★</label>
										<input type="checkbox"	id="${status.count*status.count*status.count+3}" name="rating"
											value="3^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
										<label for="${status.count*status.count*status.count+3}" title="Meh">★</label>
										<input type="checkbox"	id="${status.count*status.count*status.count+4}" name="rating"
											value="2^${webtoonDetail.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden" />
										<label for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
										<input type="checkbox"	id="${status.count*status.count*status.count+5}" name="rating"
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
								<td><a href="${webtoonDetail.webtoons_url}">
										<button>바로보기</button>
								</a></td>
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
			<br />
			<div class="fb-like" data-href="${webtoonDetail.webtoons_url}"
				data-layout="standard" data-action="like" data-show-faces="true"
				data-share="false">
			</div>
		</div> --%>
	

	<script>
	if ("${webtoonDetail.webtoons_completed}" === '완')
		$('.image').before('<div class="end_label"><img src="../img/labels/end.png" /></div>');
	
	if (calculateDateRange("${webtoonDetail.webtoons_first_update}"))
		$('.image_label').append('<img class="new_label" src="../img/labels/new.png" />');
	
	getHighRatedWebtoonsAuthor("${authorsName}", "${webtoonDetail.webtoons_id_pk}", function(result) {
		$('.image').before('<div class="author_label"><span class="author_span">'+ result + '<br />작가웹툰!!</span></div>');
	});
	</script>
</body>
</html>
