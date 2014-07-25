<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<!-- jquery js  -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<!-- 부트스트랩 css,js  -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- jquery ui css,js -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>




<!--매니져 css  -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/managerMenu.css'/>">



<script type="text/javascript"
	src="<c:url value='/js/manager/manager.js'/>"></script>
<title>관리자 페이지</title>


</head>

<script>
	$(document).ready(function() {
		var userGrade =
<%=session.getAttribute("userGrade")%>
	console.log("asdfaeee " + userGrade);

		if (userGrade >= 10) {
			$('#showWebtoonBtn img').hide();
			$('#recommendBtn img').hide();
			$('#addToonBtn img').hide();
			$('#keywordChartBtn img').hide();
			$('#authorChartBtn img').hide();
			$('#showAuthorBtn img').hide();
			$('#showUserBtn img').hide();
			$('#mypageBtn img').hide();
			$('#mypageBtn img').hide();
			$('#mypageBtn img').hide();
			if (userGrade >= 11) {
				$('#publisherChartBtn img').hide();
				$('#userWebtoonChartBtn img').hide();
				$('#genreChartBtn img').hide();
				$('#showKeywordBtn img').hide();
				$('#showGenreBtn img').hide();

			}

		}
		//버튼 클릭 
		$('#showWebtoonBtn').click(function(event) {
			if (userGrade >= 10) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#showRelativeBtn').click(function(event) {
			if (userGrade >= 100) {
			} else {
				alert('공사중 !');
				event.preventDefault();
			}
		});
		$('#addToonBtn').click(function(event) {
			if (userGrade >= 10) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#keywordChartBtn').click(function(event) {
			if (userGrade >= 10) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});

		$('#authorChartBtn').click(function(event) {
			if (userGrade >= 10) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#publisherChartBtn').click(function(event) {
			if (userGrade >= 11) {
			} else {
				alert('레벨 11 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});

		$('#userWebtoonChartBtn').click(function(event) {
			if (userGrade >= 11) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#genreChartBtn').click(function(event) {
			if (userGrade >= 11) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#showAuthorBtn').click(function(event) {
			if (userGrade >= 9) {
			} else {
				alert('레벨 9 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#showUserBtn').click(function(event) {
			if (userGrade >= 10) {
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#showKeywordBtn').click(function(event) {
			if (userGrade >= 11) {
			} else {
				alert('레벨 11 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#showGenreBtn').click(function(event) {
			if (userGrade >= 11) {
			} else {
				alert('레벨 11 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
	});
</script>



<body>
	<div id="pgcontainer"></div>



	<section id="webtoonMenu">
		<div class="overlay"></div>
		<img src="<c:url value='/img/manager/webtoonMenu.png'/>"
			class="menuImg" />
		<article>
			<ul>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showWebtoonBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>웹툰</p>
						</button>
						<input type="hidden" name="todo" value="getAllWebtoons" />
					</form></li>

				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showRelativeBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>웹툰 연관성 점수</p>
						</button>
						<input type="hidden" name="todo" value="getRelativeRate" />
					</form></li>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="addToonBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>웹툰 등록</p>
						</button>
						<input type="hidden" name="todo" value="addWebtoon" />
					</form></li>
			</ul>
		</article>
	</section>

	<section id="chartMenu">
		<div class="overlay"></div>
		<img src="<c:url value='/img/manager/chartMenu.png'/>" class="menuImg" />
		<article>
			<ul>
				<!--챠트 메뉴  -->

				<li><form method="POST" action="<c:url value='/action/chart'/>">
						<button type="submit" id="keywordChartBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>키워드 차트</p>
						</button>
						<input type="hidden" name="todo" value="keywordChart" />
					</form></li>
				<li><form method="POST" action="<c:url value='/action/chart'/>">
						<button type="submit" id="authorChartBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>작가 차트</p>
						</button>
						<input type="hidden" name="todo" value="authorChart" />
					</form></li>

				<li><form method="POST" action="<c:url value='/action/chart'/>">
						<button type="submit" id="publisherChartBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>제공처 차트</p>
						</button>
						<input type="hidden" name="todo" value="publisherChart" />
					</form></li>
				<li><form method="POST" action="<c:url value='/action/chart'/>">
						<button type="submit" id="userWebtoonChartBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>유저&웹툰 차트</p>
						</button>
						<input type="hidden" name="todo" value="userWebtoonChart" />
					</form></li>
				<li><form method="POST" action="<c:url value='/action/chart'/>">
						<button type="submit" id="genreChartBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>장르 차트</p>
						</button>
						<input type="hidden" name="todo" value="genreChart" />
					</form></li>
			</ul>
		</article>
	</section>



	<section id="authorMenu">
		<div class="overlay"></div>

		<img src="<c:url value='/img/manager/authorMenu.png'/>"
			class="menuImg" />
		<article>
			<ul>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showAuthorBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>작가</p>
						</button>
						<input type="hidden" name="todo" value="getAllAuthors" />
					</form></li>

			</ul>
		</article>
	</section>





	<section id="userMenu">
		<div class="overlay"></div>
		<img src="<c:url value='/img/manager/userMenu.png'/>" class="menuImg" />
		<article>
			<ul>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showUserBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>유저</p>
						</button>
						<input type="hidden" name="todo" value="getAllUsers" />
					</form></li>
			</ul>
		</article>
	</section>




	<section id="keywordMenu">
		<div class="overlay"></div>
		<img src="<c:url value='/img/manager/keywordMenu.png'/>"
			class="menuImg" />
		<article>
			<ul>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showKeywordBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>키워드</p>
						</button>
						<input type="hidden" name="todo" value="getAllKeywords" />
					</form></li>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showKeywordBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>맨소니테스트</p>
						</button>
						<input type="hidden" name="todo" value="mansony" />
					</form></li>
			</ul>
		</article>
	</section>




	<section id="genreMenu">
		<div class="overlay"></div>
		<img src="<c:url value='/img/manager/genreMenu.png'/>" class="menuImg" />
		<article>
			<ul>
				<li><form method="POST"
						action="<c:url value='/action/manager'/>">
						<button type="submit" id="showGenreBtn">
							<img src="<c:url value='/img/menu/lock.png'/>" />
							<p>장르</p>
						</button>
						<input type="hidden" name="todo" value="getAllGenres" />
					</form></li>
			</ul>
		</article>
	</section>









</body>
</html>