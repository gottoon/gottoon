<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<title>Show Recommended Webtoons</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewprot" content="width=device-width">
<link rel="stylesheet" href="<c:url value='/css/recommend.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/recommend.js'/>"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
<!-- <style>
#webtoon_table {
	background-color : #4963CE;
}
</style> -->
</head>
<body>
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

	
	<div class="top-header">
<div id="pgcontainer">
<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>


		<h1>Hello :)
		<span>${CurrentUser}'s<br />Recommend Webtoons!</span>
		</h1>
		<div class="nav">
			<div id="go_mypage">
				<form method="post" action="<c:url value='/action/mypageReadWebtoon'/>">
					<input type="submit" id="reset" value="마이페이지" />
				</form>
			</div>
			<div class="selectViewfree">
				<!--  07.20 희철 -->
				<div id="viewCharge">
					<form method = "post" action="<c:url value='/action/recommend'/>">
						<input type = "hidden" name = "filterviewfree" value ="false">
						<input type="submit" name="select" value="유료만보기" >
					</form>
				</div>
				<div id="viewFree">
					<form method = "post" action="<c:url value='/action/recommend'/>">
						<input type = "hidden" name = "filterviewfree" value ="true">
						<input type="submit" name="select" value="무료만보기" >
					</form>
				</div>
				<div id="viewNone">
					<form method = "post" action="<c:url value='/action/recommend'/>">
						<input type = "hidden" name = "filterviewfree" value ="null">
						<input type="submit" name="select" value="전체보기" >
					</form>
				</div>
			</div>
		</div>
	
	<div class="section">
		<div class="sec-header">


			<h3>추천 웹툰 List</h3>
		</div>
		<div class="article">
			<div align="center" id="webtoons">
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var webtoonCount = 0;
		var num = 0;
		var viewCount = 10;
		
 		$(document).ready(function(){
			<c:forEach var="webtoonInfo" items="${recommendWebtoons}" varStatus="status">
				$('#webtoons').append('<table class="webtoon_table" border="1" cellpadding="5">'
					+ '<tr><td colspan="2"></td></tr><tr><td></td><td></td></tr>'
					+ '<tr><td></td><td></td></tr></table><br id="webtoonBreak"/>');
				$('td').eq(-5).append('<div class="test" style="position:relative;">'
						+ '<div class="main_image">'
						+ '<img src="${webtoonInfo.webtoons_main_image}" width="300" height="382">'
						+ '<form method="post" action="webtoon">'
						+ '<input type="hidden" name="webtoon_id" value="${webtoonInfo.webtoons_id_pk}" />'
						+ '<input type="hidden" name="todo" value="showWebtoonDetails" />'
						+ '<button class="submit" type="submit" name="submit" value="showWebtoonDetails">'
						+ '<span class="black_overlay"></span>'
						+ '</button></form>'
						+ '<h2><span>${webtoonInfo.webtoons_title}</span></h2>'
						+ '</div>'
						+ '</div>');
				$('td').eq(-4).text("별점 평가하기");
				$('td').eq(-3).append('<form id="myForm">'
							+ '<input type="hidden" name="webtoons_id_pk" value="${webtoonInfo.webtoons_id_pk}" />'
							+ '<input type="hidden"	name="webtoons_title" value="${webtoonInfo.webtoons_title}" />'
							+ '<div class="product-review-stars">'
							+ '<input type="checkbox" id="${status.count*status.count*status.count+1}" name="rating" '
							+ 'value="5^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">'
							+ '<label for="${status.count*status.count*status.count+1}" title="Rocks!">★</label>'
							+ '<input type="checkbox" id="${status.count*status.count*status.count+2}" name="rating" '
							+ 'value="4^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">'
							+ '<label for="${status.count*status.count*status.count+2}" title="Pretty good">★</label>'
							+ '<input type="checkbox" id="${status.count*status.count*status.count+3}" name="rating" '
							+ 'value="3^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">'
							+ '<label for="${status.count*status.count*status.count+3}" title="Meh">★</label>'
							+ '<input type="checkbox" id="${status.count*status.count*status.count+4}" name="rating" '
							+ 'value="2^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">'
							+ '<label for="${status.count*status.count*status.count+4}" title="Kinda bad">★</label>'
							+ '<input type="checkbox" id="${status.count*status.count*status.count+5}" name="rating" '
							+ 'value="1^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">'
							+ '<label for="${status.count*status.count*status.count+5}" title="Sucks big time">★</label></div></form>');
				$('td').eq(-2).append('<form method="post" action="#">'
								+ '<input type="hidden" name="webtoon_id" value="${webtoonInfo.webtoons_id_pk}" />'
								+ '<input type="button" class="button" id="reserve" value="찜하기" onclick="seeReserve(this.form);" />'
								+ '</form>');
				$('td').eq(-1).append('<a href="${webtoonInfo.webtoons_url}">'
							+ '<button class="button">바로보기</button></a>');
				webtoonCount = "${status.count}";
				var completed = "${webtoonInfo.webtoons_completed}";
				if (completed === '완')
					$('.test').eq(webtoonCount - 1).append('<img class="end_label" src="../img/labels/end.png" />');
				
				if (calculateDateRange("${webtoonInfo.webtoons_first_update}"))
					$('.main_image').eq(webtoonCount - 1).append('<img class="new_label" src="../img/labels/new.png" />');

				getHighRatedWebtoonsAuthor("${webtoonInfo.authors_name}", "${webtoonInfo.webtoons_id_pk}", function(result) {
					label_index = "${status.count}";
					$('.main_image').eq(label_index - 1).append('<div class="author_label"><span class="author_span">'+ result + '<br />작가웹툰!!</span></div>');
	 			});
	 			
			</c:forEach>
			
			$('.webtoon_table').hide();
			
			for (; num < viewCount; num++) {
				$('.webtoon_table').eq(num).show();	 
			}
			
			checkButton();
		});
	</script>
	</div>
</div>	
</body>
</html>
