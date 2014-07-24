<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<title>선택된 장르 웹툰</title>

<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />

<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
<script src="<c:url value='/js/bar.js'/>"></script>


</head>
<body>
<!-- <div id="pgcontainer">
 --> <%-- <c:import url="/WEB-INF/jsp/menu.jsp"></c:import> --%>




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


	<meter id="meter" value="0" max="20">
		<input name="show" />

	</meter>




	<br>
	<br>
	<br>
	<form id="button" class="recommand" method="post" action="recommend">
		<input type="hidden" name="todo" value="recommendWebtoons" /> <input
			class="show-modal open-modal" type="submit" value="추천해줭 " />
	</form>

<c:forEach var="userWebtoonVO" items="${showWebtoons}"
		varStatus="status" >
		<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
			rel='stylesheet' type='text/css'>

		<table class="webtoon_table" border="1" cellpadding="5" align="center" width="50%">

			<tr>
				<td>웹툰 타이틀 : ${userWebtoonVO.webtoons_title}</td>
			</tr>

			<tr>
				<td>
				
				<%-- <input type="hidden" id="rate"
					value="${userWebtoonVO.user_webtoon_rate }"> <input
					type="hidden" id="id" value="${userWebtoonVO.webtoons_id_fk }">
				 --%>	<form id="myForm">

						이미지

<%-- 						<p>별점 : ${userWebtoonVO.user_webtoon_rate }</p>
 --%>

						<div class="product-review-stars">
							<input type="checkbox"
								id="${status.count*status.count*status.count+1}" name="rating"
								value="5^${userWebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+2}" name="rating"
								value="4^${userWebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+2}"
								title="Pretty good">★</label> <input type="checkbox"
								id="${status.count*status.count*status.count+3}" name="rating"
								value="3^${userWebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+4}" name="rating"
								value="2^${userWebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>
							<input type="checkbox"
								id="${status.count*status.count*status.count+5}" name="rating"
								value="1^${userWebtoonVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+5}"
								title="Sucks big time">★</label>
						</div>
					</form> <br /> 웹툰 아이디 : ${userWebtoonVO.webtoons_id_pk}<i></i></td>
			</tr>

			<tr>
				<td><input type="button" name="details" value="상세보기" /></td>
			</tr>

		</table>

		<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
	</c:forEach>
	<%-- <c:forEach var="webtoonsVO" items="${showWebtoons}" varStatus="status">

		<table border="1" cellpadding="5" align="center" width="50%">

			<tr>
				<td>웹툰 타이틀 : ${webtoonsVO.webtoons_title}</td>
			</tr>

			<tr>
				<td>
					<form id="myForm">

						이미지
						<div class="product-review-stars">

							<input type="radio"
								id="${status.count*status.count*status.count+1}" name="rating"
								value="5^${webtoonsVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="radio"
								id="${status.count*status.count*status.count+2}" name="rating"
								value="4^${webtoonsVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+2}"
								title="Pretty good">★</label> <input type="radio"
								id="${status.count*status.count*status.count+3}" name="rating"
								value="3^${webtoonsVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="radio"
								id="${status.count*status.count*status.count+4}" name="rating"
								value="2^${webtoonsVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+4}"
								title="Kinda bad">★</label> <input type="radio"
								id="${status.count*status.count*status.count+5}" name="rating"
								value="1^${webtoonsVO.webtoons_id_pk}"
								onclick=onclickStart(this) class="visuallyhidden" /> <label
								for="${status.count*status.count*status.count+5}"
								title="Sucks big time">★</label>
						</div>
						<br /> 웹툰 아이디 : ${webtoonsVO.webtoons_id_pk}<i></i>

					</form>
				</td>
			</tr>

			<tr>
				<td><input type="button" name="details" value="상세보기" /></td>
			</tr>

		</table>
	</c:forEach> --%>


	<div class="container">
		<div class="modal">
			<div>더 평가하면 더 정확한 추천을 해드릴수 있어요</div>
			<form class="recommand" method="post" action="recommend">
				<input type="submit" name="select" value="추천해줭" /> <input
					id="showButton" type="button" class="close-modal" href="#"
					value="더평가할래요 " />
			</form>
		</div>
	</div>



<!-- </div>
 --></body>
</html>
