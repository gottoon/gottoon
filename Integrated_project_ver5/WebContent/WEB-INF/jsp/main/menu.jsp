<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/normalize.css'/>" />

<!-- 부트스트랩 css,js  -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>



<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/menu.css'/>" />


<title>menu</title>
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
			console.log(response);
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





	<!-- 메뉴네비 -->
	<c:set value="${userGrade}" var="CurrentUserGrade" />

	<fmt:parseNumber var="userGradeInt" integerOnly="true" type="number"
		value="${CurrentUserGrade}" />


	<section class="menu">
		<c:if test="${userGradeInt >= 1}">
			<nav id="bt-menu" class="bt-menu">
				<a href="#" class="bt-menu-trigger"><span>Menu</span></a>

				<ul>
					<li id="home"><form id="homeForm"
							action="<c:url value='/action/main'/>" method="post">
							<button id="homeBtn" type="submit"></button>
						</form></li>
					<li>
						<form id="gnereForm" action="<c:url value='/action/genre'/>"
							method="post">
							<button id="genreBtn" type="submit"></button>
							<input type="hidden" name="todo" value="showGenres">
						</form>
					</li>
					<c:if test="${userGradeInt >= 2}">
						<li><form id="recommendForm"
								action="<c:url value='/action/recommend'/>" method="post">
								<button id="recommendBtn" type="submit">추천해줘</button>
								<input type="hidden" name="todo" value="recommend">
							</form></li>
					</c:if>
					<c:if test="${userGradeInt >= 5}">
						<li><form id="DNAForm" action="<c:url value='/action/DNA'/>"
								method="post">
								<button id="DNABtn" type="submit">DNA</button>
							</form></li>
					</c:if>
				</ul>
				<ul>
					<li id="logout"><form id="logoutForm"
							action="<c:url value='/action/main'/>" method="post">
							<button id="logoutBtn" type="button">마이페이지</button>
						</form></li>

					<li><form id="mypageForm"
							action="<c:url value='/action/mypageReadWebtoon'/>" method="post">
							<button id="mypageBtn" type="submit">마이페이지</button>
						</form></li>

					<c:if test="${userGradeInt >= 10}">
						<li><form id="managerForm"
								action="<c:url value='/action/manager'/>" method="post">
								<button id="managerBtn" type="submit">매니져</button>
								<input type="hidden" name="todo" value="manager">
							</form></li>
					</c:if>


				</ul>

			</nav>
		</c:if>
	</section>

	<!--ajax wait  -->
	<div id="wait"
		style="display: none; width: 100px; height: 89px; border: 1px solid black; position: absolute; top: 50%; left: 50%; padding: 2px;">
		<img src="<c:url value='/img/menu/ajax-loader.gif'/>" width="64"
			height="64" /><br>Loading..
	</div>
	<!--ajax wait  -->

	<script src="<c:url value='/js/main/classie.js'/>"></script>
	<script src="<c:url value='/js/main/borderMenu.js'/>"></script>


</body>
</html>