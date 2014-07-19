<%-- MYPAGE HEADER --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, mypkg.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<style>

</style>
<title>MYPAGE</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
	<%=session.getAttribute("CurrentUser")%>

	<header>
		<h1>마이페이지</h1>
	</header>
	
	<nav>
		<ul class="nav nav-pills">
			<li class="active">
				<form name="Mypagetap" action="<c:url value='/action/mypageReadWebtoon'/>" method="POST">
					<input type="hidden" name="todo" value="mypageReadWebtoon">
					<input class="btn btn-success btn-lg" type="submit" value="내가 본 웹툰" />
				</form>
			</li>

			<li><form name="Mypagetap" action="<c:url value='/action/mypageRecommendWebtoon'/>" method="POST">
					<input type="hidden" name="todo" value="mypageRecommend"> <input
						class="btn btn-success btn-lg" type="submit" value="웹툰 찾기" />
				</form></li>

			<li><form name="Mypagetap" action="<c:url value='/action/mypageSetting'/>" method="POST">
					<input type="hidden" name="todo" value="mypageSetting"> <input
						class="btn btn-success btn-lg" type="submit" value="설정" />
				</form></li>
		</ul>
	</nav>
	
	 
</body>
</html>