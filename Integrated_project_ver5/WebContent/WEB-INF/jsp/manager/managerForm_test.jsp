<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8>
<title>Insert title here</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<!-- 부트스트랩 css,js  -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/managerMenu_test.css'/>">
<script src="<c:url value='/js/manager/managerForm_test.js'/>"></script>

</head>
<body>

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



	<div class="pgcontainer">
		<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>


		<section id="menu">
			<article id="user">
				<ul id="userMenu">
					<li><button id="userBtn">
							<img src="<c:url value='/img/manager/userMenu.png'/>"
								class="menuImg" />
						</button></li>
					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showUserBtn">

								<p>유저 정보</p>
							</button>
							<input type="hidden" name="todo" value="getAllUsers" />
						</form></li>
				</ul>

			</article>
			<article id="webtoon">
				<ul id="webtoonMenu">
					<li><button id="webtoonBtn">
							<img src="<c:url value='/img/manager/webtoonMenu.png'/>"
								class="menuImg" />
						</button></li>
					<li id="asdf"><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showWebtoonBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>웹툰</p>
							</button>
							<input type="hidden" name="todo" value="getAllWebtoons" />
						</form></li>

					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showRelativeBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>웹툰 연관성 점수</p>
							</button>
							<input type="hidden" name="todo" value="getRelativeRate" />
						</form></li>
					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="addToonBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>웹툰 등록</p>
							</button>
							<input type="hidden" name="todo" value="addWebtoon" />
						</form></li>
				</ul>

			</article>
			<article id="chart">
				<ul id="chartMenu">
					<li><button id="chartBtn">
							<img src="<c:url value='/img/manager/chartMenu.png'/>"
								class="menuImg" />
						</button></li>
					<li><form method="POST"
							action="<c:url value='/action/chart'/>">
							<button type="submit" id="keywordChartBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>키워드 차트</p>
							</button>
							<input type="hidden" name="todo" value="keywordChart" />
						</form></li>
					<li><form method="POST"
							action="<c:url value='/action/chart'/>">
							<button type="submit" id="authorChartBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>작가 차트</p>
							</button>
							<input type="hidden" name="todo" value="authorChart" />
						</form></li>

					<li><form method="POST"
							action="<c:url value='/action/chart'/>">
							<button type="submit" id="publisherChartBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>제공처 차트</p>
							</button>
							<input type="hidden" name="todo" value="publisherChart" />
						</form></li>
					<li><form method="POST"
							action="<c:url value='/action/chart'/>">
							<button type="submit" id="userWebtoonChartBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>유저&웹툰 차트</p>
							</button>
							<input type="hidden" name="todo" value="userWebtoonChart" />
						</form></li>
					<li><form method="POST"
							action="<c:url value='/action/chart'/>">
							<button type="submit" id="genreChartBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>장르 차트</p>
							</button>
							<input type="hidden" name="todo" value="genreChart" />
						</form></li>
				</ul>

			</article>

			<article id="genre">
				<ul id="genreMenu">
					<li><button id="genresBtn">
							<img src="<c:url value='/img/manager/genreMenu.png'/>"
								class="menuImg" />
						</button></li>
					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showGenreBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>장르</p>
							</button>
							<input type="hidden" name="todo" value="getAllGenres" />
						</form></li>
				</ul>

			</article>
			<article id="keyword">
				<ul id="keywordMenu">
					<li><button id="keywordBtn">
							<img src="<c:url value='/img/manager/keywordMenu.png'/>"
								class="menuImg" />
						</button></li>
					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showKeywordBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>키워드</p>
							</button>
							<input type="hidden" name="todo" value="getAllKeywords" />
						</form></li>
					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showKeywordBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>맨소니테스트</p>
							</button>
							<input type="hidden" name="todo" value="mansony" />
						</form></li>
				</ul>

			</article>

			<article id="author">
				<ul id="authorMenu">
					<li><button id="authorBtn">
							<img src="<c:url value='/img/manager/authorMenu.png'/>"
								class="menuImg" />
						</button></li>
					<li><form method="POST"
							action="<c:url value='/action/manager'/>">
							<button type="submit" id="showAuthorBtn">
								<img src="<c:url value='/img/menu/lock.png'/>" class="lockImg" />
								<p>작가보기</p>
							</button>
							<input type="hidden" name="todo" value="getAllAuthors" />
						</form></li>
				</ul>

			</article>
		</section>




	</div>


</body>
</html>