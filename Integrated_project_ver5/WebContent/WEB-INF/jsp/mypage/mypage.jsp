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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<style>

</style>
<title>MYPAGE</title>
</head>
<body>
	<div id="pgcontainer">
		<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
		<%=session.getAttribute("CurrentUser")%>


<div role="main"><!-- 추가부분  -->
	<div id="contents"><!-- 추가부분  -->
		<h1>마이페이지</h1>


<section class="page"><!-- 추가부분  -->
			<ul>
				<li class="active">
					<form name="Mypagetap"
						action="<c:url value='/action/mypageReadWebtoon'/>" method="POST">
						<input type="hidden" name="todo" value="mypageReadWebtoon">
						<input class="btn btn-primary btn-lg" type="submit"
							value="내가 본 웹툰" />
					</form>
				</li>
			<li><form name="Mypagetap" action="<c:url value='/action/mypageRecommendWebtoon'/>" method="POST">
					<input type="hidden" name="todo" value="mypageRecommend"> <input
						class="btn btn-primary btn-lg" type="submit" value="신작 / 찜 툰" />
				</form></li>
		</ul>
	</div>
	</body>
	</html>
