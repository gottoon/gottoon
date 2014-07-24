<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/normalize.css'/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/menu.css'/>" />
<title>메e</title>
</head>
<body>
	<!-- 메뉴네비 -->
	<section class="container">
		<nav id="bt-menu" class="bt-menu">
			<a href="#" class="bt-menu-trigger"><span>Menu</span></a>
			<ul>
				<li><form id="gnereForm"
						action="<c:url value='/action/genre'/>" method="post">
						<button id="genreBtn" style="visibility: hidden" type="submit"></button>
						<input type="hidden" name="todo" value="showGenres">
					</form></li>
				<li><form id="recommendForm"
						action="<c:url value='/action/recommend'/>" method="post">
						<button id="recommendBtn" style="visibility: hidden" type="submit">
							추천해줘</button>
						<input type="hidden" name="todo" value="recommend">
					</form></li>
			</ul>
			<ul>
				<li><form id="mypageForm"
						action="<c:url value='/action/mypageReadWebtoon'/>" method="post">
						<button id="mypageBtn" style="visibility: hidden" type="submit">마이페이지</button>
					</form></li>
				<li><form id="managerForm"
						action="<c:url value='/action/manager'/>" method="post">
						<button id="managerBtn" style="visibility: hidden" type="submit">매니져</button>
						<input type="hidden" name="todo" value="manager">
					</form></li>
			</ul>
		</nav>
	</section>

	<script src="<c:url value='/js/classie.js'/>"></script>
	<script src="<c:url value='/js/main/borderMenu.js'/>"></script>
</body>
</html>