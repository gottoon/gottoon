<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인</title>
<meta name="description" content="recommend webtoon service" />
<meta name="keywords" content="webtoons,recommend,comic,cartoon" />


<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/main.css'/>">
<script src="<c:url value='/js/main/main.js'/>"></script>
</head>
<body>
	<div class="container-fluid">
		<div id="pgcontainer">
			<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
			<div class="row">
					<section id="carousel">
					<div id="content">
						<!--회전 효과    -->
						<div id="this-carousel-id" class="carousel slide">
							<!-- class of slide for animation -->
							<div class="carousel-inner">
								<div class="item active">
									<div class="carouselImg">
										<img src="<c:url value='/img/carousel/badRelationship.png'/>"
											alt="" />
									</div>

									<div class="carousel-caption">
										<p>서비스 설명이 들어갈 곳</p>
									</div>
								</div>
								<div class="item">
									<div class="carouselImg">
										<img src="<c:url value='/img/carousel/fashionKing.png'/>"
											alt="" />
									</div>

									<div class="carousel-caption">
										<p>신작 소개 소개</p>
									</div>
								</div>
								<div class="item">
									<div class="carouselImg">
										<img src="<c:url value='/img/carousel/innerSound.png'/>"
											alt="" />
									</div>

									<div class="carousel-caption">
										<p>추천인 랭킹순위</p>
									</div>
								</div>
								<div class="item">
									<div class="carouselImg">
										<img src="<c:url value='/img/carousel/namgihan.png'/>" alt="" />
									</div>

									<div class="carousel-caption">
										<p>가장 많이 추천된 웹툰</p>
									</div>
								</div>
							</div>
							<a data-slide="prev" href="#this-carousel-id"
								class="left carousel-control">‹</a> <a data-slide="next"
								href="#this-carousel-id" class="right carousel-control">›</a>
						</div>
					</div>
				</section>
					<!--회전 효과  bj  -->
			</div>
			<div class="row">
				<section id="loginSection">
					<article id=loginArticle>





						<!--페북 로그인 버튼, 로그인 후 환영 인사  -->

						<div class="col-md-7">
							<div id="welcomeUser"></div>
							
						</div>
						<div class="col-md-5">
						<div id="userPic">
								<img id="userImg" /></img>
							</div>
							<form id="loginForm" method="post"
								action="<c:url value='/action/user'/>">
								<input id="loginInput" type="hidden" name="todo"
									value="checkUser" />
								<button id="facebookBtn" type="button"
									onclick="checkLoginState()"></button>
							</form>
						</div>
					</article>

				</section>

			</div>

			<!--페북 로그인 버튼, 로그인 후 환영 인사  -->


		</div>
	</div>








</body>

</html>