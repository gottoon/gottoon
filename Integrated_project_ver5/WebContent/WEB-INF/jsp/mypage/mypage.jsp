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
<link rel="shortcut icon" href="../img/gradeImg/1406635516_97786.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<title>MYPAGE</title>
</head>
<body>
	<div id="pgcontainer">
		<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
		<%-- <%=session.getAttribute("CurrentUser")%> --%>

		<div role="main">
			<div id="contents">
				<div>
					<h1>마이페이지</h1>
				</div>
				<section class="page">
					<ul class="nav nav-pills nav-justified">
						<li><form name="Mypagetap"
								action="<c:url value='/action/mypageReadWebtoon'/>"
								method="POST">
								<input type="hidden" name="todo" value="mypageReadWebtoon">
								<input id="button1" class="btn btn-primary btn-lg" type="submit"
									value="내가 본 웹툰" />
							</form></li>
						<li><form name="Mypagetap"
								action="<c:url value='/action/mypageWishWebtoon'/>"
								method="POST">
								<input type="hidden" name="todo" value="mypageWishWebtoon">
								<input id="button2" class="btn btn-primary btn-lg" type="submit"
									value="찜 웹툰" />
							</form></li>
						<li><form name="Mypagetap"
								action="<c:url value='/action/mypageNewWebtoon'/>" method="POST">
								<input type="hidden" name="todo" value="mypageNewWebtoon">
								<input id="button3" class="btn btn-primary btn-lg" type="submit"
									value="신작 웹툰" />
							</form></li>
					</ul>
				</section>
			</div>
		</div>
	</div>
</body>
</html>
