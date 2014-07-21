<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인</title>
<meta name="description" content="recommend webtoon service" />
<meta name="keywords" content="webtoons,recommend,comic,cartoon" />

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/main.css'/>">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script src="<c:url value='/js/main/main.js'/>"></script>
</head>
<body>
	<script>
		//윈도우 시작시 페북 환경 체크 
		window.fbAsyncInit = function() {
			FB.init({
				appId : '618721798234752',
				cookie : true, // enable cookies to allow the server to access
				// the session
				xfbml : true, // parse social plugins on this page
				version : 'v2.0' // use version 2.0
			});

			// 페북 로그인 체크 시작 
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});

		};

		// 로그인 상태에 따른 콜백
		function statusChangeCallback(response) {
			console.log('statusChangeCallback 안녕하세요 스테터스 콜백 시전 ');
			if (response.status === 'connected') {
				login();
			} else if (response.status === 'not_authorized') {
				document.getElementById('welcomUser').innerHTML = 'Please log '
						+ 'into this app.';
			} else {
				$("#welcomeUser").html('<p>Please log into Facebook.</p>');
				FB
						.login(function(response) {
							if (response.authResponse) {
								console
										.log('Welcome!  Fetching your information.... ');

								location.reload();
							} else {
								console
										.log('User cancelled login or did not fully authorize.');
							}
						});
			}
		}

		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}

		/*로그인  */
		function login() {
			console.log('로그인 실행 ');
			FB.api('/me', function(response) {
				console.log('Successful login for: ' + response.name);
			});

			/* make the API call */
			FB.api("/me/picture", function(response) {
				console.log('사진이야!!');
				if (response) {
					/* handle the result */
					console.log(response);
					$('#userImg').attr('src', response.data.url);
				}
			});
			
			FB.api('/me', function checkUser(response) {
				var form = $('#loginForm');

				console.log("체크 유저 시작 " + response.email);
				var curruntUserName = response.name;
				var curruntUserEmail = response.email;
				var CurruntUser_facebookID = response.id;
				console.log(CurruntUser_facebookID);
				$.ajax({
					type : form.attr('method'),
					url : form.attr('action'),
					data : {
						"curruntUserName" : curruntUserName,
						"curruntUserEmail" : curruntUserEmail,
						"CurruntUser_facebookID" : CurruntUser_facebookID
					},

					success : function() {
						$("#welcomeUser").html(
								"<h2>안녕하세요 " + response.name + "님 !</h2>");
						$("#facebookBtn").hide();

					}

				});

			});

		}

		/*sdk 불러오기  */
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/kr_KO/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	
	
	<div id="pgcontainer">
		<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>

		<div id="content">
			<!--회전 효과    -->
			<section id="carousel">
				<div id="this-carousel-id" class="carousel slide">
					<!-- class of slide for animation -->
					<div class="carousel-inner">
						<div class="item active">
							<!-- class of active since it's the first item -->
							<img src="<c:url value='/img/carousel/badRelationship.png'/>"
								alt="" />
							<div class="carousel-caption">
								<p>서비스 설명이 들어갈 곳</p>
							</div>
						</div>
						<div class="item">
							<img src="<c:url value='/img/carousel/fashionKing.png'/>" alt="" />
							<div class="carousel-caption">
								<p>신작 소개 소개</p>
							</div>
						</div>
						<div class="item">
							<img src="<c:url value='/img/carousel/innerSound.png'/>" alt="" />
							<div class="carousel-caption">
								<p>추천인 랭킹순위</p>
							</div>
						</div>
						<div class="item">
							<img src="<c:url value='/img/carousel/namgihan.png'/>" alt="" />
							<div class="carousel-caption">
								<p>가장 많이 추천된 웹툰</p>
							</div>
						</div>
					</div>
					<a data-slide="prev" href="#this-carousel-id"
						class="left carousel-control">‹</a> <a data-slide="next"
						href="#this-carousel-id" class="right carousel-control">›</a>
				</div>
			</section>
			<!--회전 효과  bj  -->


			<!--페북 로그인 버튼, 로그인 후 환영 인사  -->
			<section id="loginSection">
				<article id=loginArticle>
					<ul>
						<li><div id="welcomeUser"></div></li>
						<li><div id="userPic">
								<img id="userImg" /></img>
							</div></li>

						<li>
							<form id="loginForm" method="post"
								action="<c:url value='/action/user'/>">
								<input id="loginInput" type="hidden" name="todo"
									value="checkUser" />
								<button id="facebookBtn" type="button"
									onclick="checkLoginState()"></button>
							</form>
						</li>
					</ul>
				</article>
			</section>
			<!--페북 로그인 버튼, 로그인 후 환영 인사  -->

		</div>
	</div>



</body>

</html>