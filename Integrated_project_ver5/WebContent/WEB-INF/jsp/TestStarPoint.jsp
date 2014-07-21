<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />
<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/TestStarPoint.js'/>"></script>
<script src="<c:url value='/js/bar.js'/>"></script>
</head>
<body>



	<div class="show-modal1">
		<div class="modal1">
			<div>저장 부아악!</div>
		</div>
	</div>



	<meter id="meter" value="0" max="2">
		<input name="show" />
	</meter>

	<br>
	<br>
	<br>



	<c:forEach var="userWebtoonVO" items="${loadingWebtoons}"
		varStatus="status">
		<table border="1" cellpadding="5" align="center" width="50%">

			<tr>
				<td>웹툰 타이틀 : ${userWebtoonVO.webtoon_title}</td>
			</tr>

			<tr>
				<td>
					<form id="myForm">

						이미지
						
						<p>별점 : ${userWebtoonVO.user_webtoon_rate }</p>
						
						
						<input type ="hidden" id = "rate" value = "${userWebtoonVO.user_webtoon_rate }">
						<input type ="hidden" id = "id" value = "${userWebtoonVO.webtoons_id_fk }">
						
						
						<div class="product-review-stars">

							<input type="radio"
								id="${status.count*status.count*status.count+1}" name="5"
								value="5^${userWebtoonVO.webtoons_id_fk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>
							<input type="radio"
								id="${status.count*status.count*status.count+2}" name="4"
								value="4^${userWebtoonVO.webtoons_id_fk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+2}"
								title="Pretty good">★</label> <input type="radio"
								id="${status.count*status.count*status.count+3}" name="3"
								value="3^${userWebtoonVO.webtoons_id_fk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+3}" title="Meh">★</label>
							<input type="radio"
								id="${status.count*status.count*status.count+4}" name="2"
								value="2^${userWebtoonVO.webtoons_id_fk}"
								onclick=onclickStart(this) class="visuallyhidden"> <label
								for="${status.count*status.count*status.count+4}"
								title="Kinda bad">★</label> <input type="radio"
								id="${status.count*status.count*status.count+5}" name="1"
								value="1^${userWebtoonVO.webtoons_id_fk}"
								onclick=onclickStart(this) class="visuallyhidden" /> <label
								for="${status.count*status.count*status.count+5}"
								title="Sucks big time">★</label>
						</div>
						<br /> 웹툰 아이디 : ${userWebtoonVO.webtoons_id_fk}<i></i>

					</form>
				</td>
			</tr>

			<tr>
				<td><input type="button" name="details" value="상세보기" /></td>
			</tr>

		</table>
	</c:forEach>




</body>
</html>