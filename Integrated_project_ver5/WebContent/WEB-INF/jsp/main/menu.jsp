<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/menu.css'/>" />
<script src="<c:url value='/js/main/borderMenu.js'/>"></script>






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
					<li><form id="recommendForm"
							action="<c:url value='/action/recommend'/>" method="post">
							<c:if test="${userGradeInt >= 2}">
								<button id="recommendBtn" type="submit">
									<p>추천해줘</p>
								</button>
								<input type="hidden" name="todo" value="recommend">
							</c:if>
							
						</form></li>
					<li><form id="DNAForm" action="<c:url value='/action/DNA'/>"
							method="post">
							<c:if test="${userGradeInt >= 5}">
								<button id="DNABtn" type="submit">
									<p>DNA</p>
								</button>
							</c:if>
							
						</form></li>


					<li><form id="mypageForm"
							action="<c:url value='/action/mypageReadWebtoon'/>" method="post">
							<button id="mypageBtn" type="submit">
								<p>마이페이지</p>
							</button>
						</form></li>

					<li><form id="managerForm"
							action="<c:url value='/action/manager'/>" method="post">
							<c:if test="${userGradeInt >= 10}">
								<button id="managerBtn" type="submit">
									<p>매니져</p>
								</button>
								<input type="hidden" name="todo" value="manager">
							</c:if>
							
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
	style="display: none; width: 100px; height: 89px; border: 1px solid black; position: absolute; top: 50%; left: 50%; padding: 2px;">
	<img src="<c:url value='/img/menu/ajax-loader.gif'/>" width="64"
		height="64" /><br>Loading..
</div>
<!--ajax wait  -->



