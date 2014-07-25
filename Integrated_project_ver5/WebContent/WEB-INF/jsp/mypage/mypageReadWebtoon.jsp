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
<script src="<c:url value='/js/mypageReadWebtoon.js'/>"></script>
<script src="<c:url value='/js/MyPageAndStarPoint.js'/>"></script>




<%--<link rel="stylesheet" href="<c:url value='/css/mypageReadWebtoon.css'/>" />
<script src="<c:url value='/js/mypageReadWebtoon.js'/>"></script> --%>

<script type="text/javascript">
	var grade = $
	{
		grade
	}
	console.log("${grade}");

	if (grade == 1) {
		$("#grade").append(" <b>Level : 1 / 웹툰이라고 알어?</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level1.png"/>" width="200" height="200" border="0">');
	} else if (grade == 2) {
		$("#grade").append(" <b>Level : 2 / 웹툰은 보고 다니냐?</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level2.png"/>" width="200" height="200" border="0">');
	} else if (grade == 3) {
		$("#grade").append(" <b>Level : 3 / 웹툰 초보자</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level3.png"/>" width="200" height="200" border="0">');
	} else if (grade == 4) {
		$("#grade").append(" <b>Level : 4 / 웹툰 좀 보네?</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level4.png"/>" width="200" height="200" border="0">');
	} else if (grade == 5) {
		$("#grade").append(" <b>Level : 5 / 웹툰 중수</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level5.png"/>" width="200" height="200" border="0">');
	} else if (grade == 6) {
		$("#grade").append(" <b>Level : 6 / 장기 고색이시군요.</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level6.png"/>" width="200" height="200" border="0">');
	} else if (grade == 7) {
		$("#grade").append(" <b>Level : 7 / 웹툰 진짜 좋아하나봐?</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level7.png"/>" width="200" height="200" border="0">');
	} else if (grade == 8) {
		$("#grade").append(" <b>Level : 8 / 업계 종사자세요?</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level8.png"/>" width="200" height="200" border="0">');
	} else if (grade == 9) {
		$("#grade").append(" <b>Level : 9 / 경배하라! 웹툰 신이다!</b>");
		$(".gradeImg")
				.append(
						'<img src="<c:url value="/img/gradeImg/grade_level9.png"/>" width="200" height="200" border="0">');
	};
</script>

<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<style>
meter {
	-webkit-appearance: meter;
	box-sizing: border-box;
	display: inline-block;
	height: 1.5em;
	width: 15em;
	vertical-align: -0.2em;
}

.thumbnail {
	display: block;
	padding: 4px;
	margin-bottom: 30px;
	line-height: 2.42857143;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 10px;
	-webkit-transition: all .2s ease-in-out;
	-o-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
}

body {
	font-family: "Helvetica Neue", Helvetica,
		Arial, sans-serif;
	font-size: 12px;
	line-height: 1.42857143;
	color: #333;
	background-color: #fff;
}

h2,div {
	font-size: 20px;
	text-align: center;
}

a {
	width: 12em;
	border: 0px solid #000000;
	word-break: break-all;
}
</style>


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
		<div>
			<h2>지금까지 총 ${fn:length(readToon)} 편의 웹툰을 보셨습니다.</h2>

			<meter id="meter" low=35 high=70 max=100 value=0>
				<input name="show" />
			</meter>
		</div>

		<article>
			<nav>
				<div>
					<p id="grade"></p>
					<table border='0' cellpadding='0' align="center">
						<tr>
							<td class="gradeImg"></td>
						</tr>
					</table>
				</div>
			</nav>
		</article>
	</section>




	<section class="container">
		<table border='0' cellpadding='0' align="center">
			<tr>
				<td><c:forEach var="WebtoonVO" items="${readToon}"
						varStatus="status">
						<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
						<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
							<div class="thumbnail">
								<div class="caption">
									<article>

										<form method="post" action="webtoon">
											<input type="hidden" name="webtoon_id"
												value="${WebtoonVO.webtoons_id_pk}" /> <input type="hidden"
												name="todo" value="showWebtoonDetails" />
											<%-- <input type="image" width="100%" height="100%" src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" /> --%>
											<input type="image" width="100%" height="100%"
												src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>" />
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
													for="${status.count*status.count*status.count+1}"
													title="Rocks!">★</label> <input type="checkbox"
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
													for="${status.count*status.count*status.count+4}"
													title="Kinda bad">★</label> <input type="checkbox"
													id="${status.count*status.count*status.count+5}" name="rating"
													value="1^${WebtoonVO.webtoons_id_pk}"
													onclick=onclickStart(this) class="visuallyhidden"> <label
													for="${status.count*status.count*status.count+5}"
													title="Sucks big time">★</label>
											</div>
										</form>






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