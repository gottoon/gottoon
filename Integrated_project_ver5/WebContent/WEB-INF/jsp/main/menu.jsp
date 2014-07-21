<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="shortcut icon"
	href="http://static.tmimgcdn.com/img/favicon.ico">
<link rel="icon" href="http://static.tmimgcdn.com/img/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/menu.css'/>" />
<script src="<c:url value='/js/main/borderMenu.js'/>"></script>




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


	<c:set value="${userGrade}" var="CurrentUserGrade" />

	<fmt:parseNumber var="userGradeInt" integerOnly="true" type="number"
		value="${CurrentUserGrade}" />




	<header>
		<nav>
			<div id="navbar">
				<a href="#" class="menubtn">menu</a>
				<!-- use captain icon for toggle menu -->
				<div id="hamburgermenu">
					<ul>
						<c:if test="${userGradeInt >= 1}">
							<li id="home"><form id="homeForm"
									action="<c:url value='/action/main'/>" method="post">
									<button id="homeBtn" type="submit">
										<p>홈</p>
									</button>
								</form></li>

							<li>
								<form id="gnereForm" action="<c:url value='/action/genre'/>"
									method="post">
									<button id="genreBtn" type="submit">
										<p>장르 선택</p>
									</button>
									<input type="hidden" name="todo" value="showGenres">
								</form>
							</li>
							<c:if test="${userGradeInt >= 2}">
								<li><form id="recommendForm"
										action="<c:url value='/action/recommend'/>" method="post">
										<button id="recommendBtn" type="submit">
											<p>추천해줘</p>
										</button>
										<input type="hidden" name="todo" value="recommend">
									</form></li>
							</c:if>
							<c:if test="${userGradeInt >= 5}">
								<li><form id="DNAForm"
										action="<c:url value='/action/DNA'/>" method="post">
										<button id="DNABtn" type="submit">
											<p>DNA</p>
										</button>
									</form></li>
							</c:if>


							<li><form id="mypageForm"
									action="<c:url value='/action/mypageReadWebtoon'/>"
									method="post">
									<button id="mypageBtn" type="submit">
										<p>마이페이지</p>
									</button>
								</form></li>

							<c:if test="${userGradeInt >= 10}">
								<li><form id="managerForm"
										action="<c:url value='/action/manager'/>" method="post">
										<button id="managerBtn" type="submit">
											<p>매니져</p>
										</button>
										<input type="hidden" name="todo" value="manager">
									</form></li>
							</c:if>
							<li id="logout"><form id="logoutForm"
									action="<c:url value='/action/main'/>" method="post">
									<button id="logoutBtn" type="button">
										<p>로그아웃</p>
									</button>
								</form></li>
						</c:if>
					</ul>


				</div>
			</div>
		</nav>
		<div class="overlay"></div>
	</header>


	<!-- @end #pgcontainer -->

	<!--ajax wait  -->
	<div id="wait"
		style="display: none; width: 100px; height: 89px; border: 1px solid black; position: absolute; top: 50%; left: 50%; padding: 2px;">
		<img src="<c:url value='/img/menu/ajax-loader.gif'/>" width="64"
			height="64" /><br>Loading..
	</div>
	<!--ajax wait  -->
