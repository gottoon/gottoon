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


<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
</head>

<script type="text/javascript">
	$(document).ready(function() {
		$('#managerBtn').click(function(event) {
			console.log(<%=session.getAttribute("userGrade")%>);
			if (<%=session.getAttribute("userGrade")%> >= 10) {
				alert('하');
			} else {
				alert('레벨 10 이상만 들어갈수 있어요 !');
				event.preventDefault();
			}
		});
	});
	
	
</script>
<c:set value="${userGrade}" var="CurrentUserGrade" />

<fmt:parseNumber var="userGradeInt" integerOnly="true" type="number"
	value="${CurrentUserGrade}" />


<body>
	<script>
window.fbAsyncInit = function() {
	FB.init({
		appId : '618721798234752',
		cookie : true, // enable cookies to allow the server to access
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.0' // use version 2.0
		
		
	});

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
	
	
	session.re
</script>


	<header>
		<nav>
			<div id="navbar">
				<a href="#" class="menubtn">menu</a>
				<!-- use captain icon for toggle menu -->
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
									<p>장르 선택</p>
								</button>
								<input type="hidden" name="todo" value="showGenres">
							</form>
						</li>
						<li><form id="recommendForm"
								action="<c:url value='/action/recommend'/>" method="post">
								<%-- 	<c:if test="${userGradeInt >= 2}"> --%>
								<button id="recommendBtn" type="submit">
									<p>추천해줘</p>
								</button>
								<input type="hidden" name="todo" value="recommend">
								<%-- </c:if> --%>

							</form></li>
						<li><form id="DNAForm" action="<c:url value='/action/DNA'/>"
								method="post">
								<%-- <c:if test="${userGradeInt >= 5}"> --%>
								<button id="DNABtn" type="submit">
									<p>DNA</p>
								</button>
								<%-- 	</c:if> --%>

							</form></li>


						<li><form id="mypageForm"
								action="<c:url value='/action/mypageReadWebtoon'/>"
								method="post">
								<button id="mypageBtn" type="submit">
									<p>마이페이지</p>
								</button>
							</form></li>

						<li><form id="managerForm"
								action="<c:url value='/action/manager'/>" method="post">
								<%-- <c:if test="${userGradeInt >= 10}"> --%>
								<button id="managerBtn" type="submit">
									<p>매니져</p>
								</button>
								<input type="hidden" name="todo" value="manager">
								<%-- 	</c:if> --%>

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
	<div id="wait"
		style="display: none; width: 100px; height: 89px; position: absolute; top: 50%; left: 50%; padding: 2px;">
		<img src="<c:url value='/img/menu/ajax-loader.gif'/>" width="64"
			height="64" /><br>Loading..
	</div>
	<!--ajax wait  -->
	
	
	<script src="<c:url value='/js/main/borderMenu.js'/>"></script>
</body>
</html>

