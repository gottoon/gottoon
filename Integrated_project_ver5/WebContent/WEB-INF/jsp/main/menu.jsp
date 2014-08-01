<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/menu.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/talkbox.css'/>">


<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
</head>
<script src="<c:url value='/js/main/borderMenu.js'/>"></script>
<script>
	//페북 관련
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

	//로그인 상태에 따른 콜백
	function statusChangeCallback(response) {
		console.log('statusChangeCallback 안녕하세요 스테터스 콜백 시전 ');
		if (response.status === 'connected') {

			if (
<%=session.getAttribute("CurrentUser")%>
	== null) {
				login();
				$("#logoutBtn").show();

			} else {
				welcomUser();
				$("#logoutBtn").show();
			}

		} else if (response.status === 'not_authorized') {
			document.getElementById('welcomUser').innerHTML = 'Please log '
					+ 'into this app.';
			$("#facebookBtn").show();
			$("#logoutBtn").hide();

		} else {
			$("#loginMent").html('<p>Facebook으로 로그인/회원가입 하시오.</p>');
			$("#facebookBtn").show();
			$("#logoutBtn").hide();

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

	/* 로그인 */
	function login() {
		console.log('로그인 실행 ');
		var photoUrl = "";
		/* make the API call */
		FB.api("/me/picture", function(response) {
			console.log('사진이야!!');
			if (response) {
				/* handle the result */
				photoUrl = response.data.url;
				$('#userImg').attr('src', response.data.url);
			}
		});

		FB.api('/me', function checkUser(response) {
			var form = $('#loginForm');

			var curruntUserName = response.name;
			var curruntUserEmail = response.email;
			var CurruntUser_facebookID = response.id;

			$.ajax({
				type : form.attr('method'),
				url : form.attr('action'),
				data : {
					"curruntUserName" : curruntUserName,
					"curruntUserEmail" : curruntUserEmail,
					"CurruntUser_facebookID" : CurruntUser_facebookID,
					"currentUserPhoto" : photoUrl
				},

				success : function(data) {
					var userGrade = data;

					if (userGrade == 1) {
						newbieCheck();
					} else {
						location.reload();
					}

				}

			});

		});

	}

	/* sdk 불러오기 */
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/kr_KO/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	$(document).ready(function() {
		$('#logoutBtn').click(function logout() {
			console.log('로그아웃 시작 ');
			var con = confirm("나갈꺼야...?");
			if (con) {
				FB.logout(function(response) {
					console.log(response);
					// user is now logged out
					$.ajax({

						type : "POST",
						url : "user",
						data : {
							todo : "logout"
						},
						success : function() {
							location.href = "main";
						}

					});

				});
			}

		});

	});

	//newbieCheck 

	//managerBtn
	$(document).ready(function() {
		var userGrade =
<%=session.getAttribute("userGrade")%>
	;
		console.log("asdfaeee " + userGrade);

		if (userGrade >= 1) {
			console.log('더 평가하기 없애');
			$('#genreBtn img').hide();

			if (userGrade >= 2) {

				$('#moreBtn img').hide();
				$('#recommendBtn img').hide();
				$('#mypageBtn img').hide();

				if (userGrade >= 10) {
					$('#managerBtn img').hide();
				}

			}

		}
		//버튼 클릭 
		$('#genreBtn').click(function(event) {
			if (userGrade >= 2) {
			} else {
				alert('레벨 2 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#moreBtn').click(function(event) {
			if (userGrade >= 1) {
			} else {
				alert('레벨 2 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#recommendBtn').click(function(event) {
			if (userGrade >= 1) {
			} else {
				alert('레벨 2 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
		$('#mypageBtn').click(function(event) {
			if (userGrade >= 1) {
			} else {
				alert('레벨 2 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});

		$('#managerBtn').click(function(event) {
			if (userGrade >= 10) {
				alert('하');

			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
	});
	//managerBtn

	function welcomUser() {

		FB.api('/me', function checkUser(response) {

			$("#welcomeUser").html("<h4> " + response.name + "</h4>");
			$(".talkbox").html(
					"<p>안녕하세요  <b>" + response.name
							+ "</b> 님!<br /> <b>갓툰</b>의 세계에 오신것을 환영합니다! </p>")
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

	}
</script>



<c:set value="${userGrade}" var="CurrentUserGrade" />

<fmt:parseNumber var="userGradeInt" integerOnly="true" type="number"
	value="${CurrentUserGrade}" />


<body>





	<header>
		<nav>
			<div id="navbar">
				<div id="topNav">
					<a href="#" class="menubtn"><img
						src="<c:url value='/img/menu/menu.png'/>">gottoon</a>
					<div id="userInfo">
						<ul>
							<li>
								<div id="welcomeUser"></div>

							</li>
							<li><div id="userPic">
									<img id="userImg" /></img>
								</div></li>

						</ul>
					</div>
				</div>





				<div id="hamburgermenu">
					<ul>

						<li id="home"><form id="homeForm"
								action="<c:url value='/action/main'/>" method="post">
								<button id="homeBtn" type="submit">
									<p>홈</p>
								</button>
							</form></li>

						<li>
							<form id="genreForm" action="<c:url value='/action/genre'/>"
								method="post">
								<button id="genreBtn" type="submit">
									<img src="<c:url value='/img/menu/lock.png'/>" />
									<p>장르 선택</p>
								</button>
								<input type="hidden" name="todo" value="showGenres">
							</form>
						</li>
						<li>
							<form id="moreForm" action="<c:url value='/action/userGenre'/>"
								method="post">
								<button id="moreBtn" type="submit">
									<img src="<c:url value='/img/menu/lock.png'/>" />
									<p>평가 하기</p>
								</button>
								<input type="hidden" name="todo" value="">
							</form>
						</li>
						<li><form id="recommendForm"
								action="<c:url value='/action/recommend'/>" method="post">
								<%-- 	<c:if test="${userGradeInt >= 2}"> --%>
								<button id="recommendBtn" type="submit">
									<img src="<c:url value='/img/menu/lock.png'/>" />
									<p>추천해줘</p>
								</button>
								<input type="hidden" name="todo" value="recommend">
								<%-- </c:if> --%>

							</form></li>

						<li><form id="mypageForm"
								action="<c:url value='/action/mypageReadWebtoon'/>"
								method="post">
								<button id="mypageBtn" type="submit">
									<img src="<c:url value='/img/menu/lock.png'/>" />
									<p>마이페이지</p>
								</button>
							</form></li>

						<li><form id="managerForm"
								action="<c:url value='/action/manager'/>" method="post">
								<button id="managerBtn" type="submit">
									<img src="<c:url value='/img/menu/lock.png'/>" />
									<p>매니져</p>
								</button>
								<input type="hidden" name="todo" value="manager">

							</form></li>
						<li id="logout"><form id="logoutForm"
								action="<c:url value='/action/user'/>" method="post">
								<button id="logoutBtn" type="button">
									<p>로그아웃</p>
								</button>
								<input type="hidden" name="todo" value="logout">
							</form></li>
					</ul>


				</div>
			</div>
		</nav>
		<div class="overlay"></div>
	</header>

	<!-- @end #pgcontainer -->

	<!--ajax wait  -->
	<div id="wait" style="display: none;">
		<img src="<c:url value='/img/menu/ajax-loader.gif'/>" width="64"
			height="64" /><br>Loading..
	</div>
	<!--ajax wait  -->




	<!--모달   -->
	<div id="goGenreModal">
		<form id="genreFormModal" action="<c:url value='/action/genre'/>"
			method="post">
			<div class=bubble>
				<h3>처음.... 이신가봐요 ~?</h3>
			</div>
			<button id="genreBtnModal" type="submit">장르 선택 하기</button>
			<input type="hidden" name="todo" value="showGenres">
		</form>


	</div>

</body>
</html>


