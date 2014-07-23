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
	href="<c:url value='/css/manager/manager.css'/>">



<script type="text/javascript"
	src="<c:url value='/js/manager/manager.js'/>"></script>
<title>관리자 페이지</title>


</head>
<body>
	<div id="pgcontainer">
		<!-- Single button -->

		<nav>
			<ul>
				<!--웹툰 메뉴  -->
				<li><div class="btn-group">
						<button type="button" class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							웹툰 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>모든 웹툰 보기</p>
									</button>
									<input type="hidden" name="todo" value="getAllWebtoons" />
								</form></li>
							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>모든 장르 보기</p>
									</button>
									<input type="hidden" name="todo" value="getAllGenres" />
								</form></li>
							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>웹툰 연관성 점수 보기</p>
									</button>
									<input type="hidden" name="todo" value="getRelativeRate" />
								</form></li>
							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>웹툰 등록하기</p>
									</button>
									<input type="hidden" name="todo" value="addWebtoon" />
								</form></li>
							<li class="divider"></li>
						</ul>
					</div></li>

				<!--챠트 메뉴  -->
				<li><div class="btn-group">
						<button type="button" class="btn btn-danger dropdown-toggle"
							data-toggle="dropdown">
							챠트보기 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">

							<li><form method="POST"
									action="<c:url value='/action/chart'/>">
									<button type="submit">
										<p>키워드 차트 보기</p>
									</button>
									<input type="hidden" name="todo" value="keywordChart" />
								</form></li>
							<li class="divider"></li>
							<li><form method="POST"
									action="<c:url value='/action/chart'/>">
									<button type="submit">
										<p>작가 차트 보기</p>
									</button>
									<input type="hidden" name="todo" value="authorChart" />
								</form></li>
							<li><form method="POST"
									action="<c:url value='/action/chart'/>">
									<button type="submit">
										<p>장르 차트 보기</p>
									</button>
									<input type="hidden" name="todo" value="genreChart" />
								</form></li>
							<li class="divider"></li>
							<li><form method="POST"
									action="<c:url value='/action/chart'/>">
									<button type="submit">
										<p>제공처 차트 보기</p>
									</button>
									<input type="hidden" name="todo" value="publisherChart" />
								</form></li>
							<li class="divider"></li>
							<li><form method="POST"
									action="<c:url value='/action/chart'/>">
									<button type="submit">
										<p>유저&웹툰 차트 보기</p>
									</button>
									<input type="hidden" name="todo" value="userWebtoonChart" />
								</form></li>
							<li class="divider"></li>
						</ul>
					</div></li>



				<li><div class="btn-group">
						<button type="button" class="btn btn-success dropdown-toggle"
							data-toggle="dropdown">
							키워드 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">

							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>모든 키워드 보기</p>
									</button>
									<input type="hidden" name="todo" value="getAllKeywords" />
								</form></li>
							<li class="divider"></li>
						</ul>
					</div></li>

				<!-- 작가 메뉴  -->
				<li><div class="btn-group">
						<button type="button" class="btn btn-info dropdown-toggle"
							data-toggle="dropdown">
							작가 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">

							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>모든 작가 보기</p>
									</button>
									<input type="hidden" name="todo" value="getAllAuthors" />
								</form></li>
							<li class="divider"></li>
						</ul>
					</div></li>
				<li>
					<!--유저 메뉴  -->
					<div class="btn-group">
						<button type="button" class="btn btn-warning dropdown-toggle"
							data-toggle="dropdown">
							유저 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">

							<li><form method="POST"
									action="<c:url value='/action/manager'/>">
									<button type="submit">
										<p>모든 유저 보기</p>
									</button>
									<input type="hidden" name="todo" value="getAllUsers" />
								</form></li>
							<li class="divider"></li>
						</ul>
					</div>
				</li>
			</ul>
		</nav>
		</div>
</body>
</html>