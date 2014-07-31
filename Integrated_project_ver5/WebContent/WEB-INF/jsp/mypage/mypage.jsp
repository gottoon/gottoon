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

<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/myPageStar.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypage.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypageContents.css'/>" />
	
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
								action="<c:url value='/action/mypage'/>"
								method="POST">
								<input type="hidden" name="todo" value="mypageReadWebtoon">
								<input id="button1" class="btn btn-primary btn-lg" type="submit"
									value="본 툰" />
							</form></li>
						<li><form name="Mypagetap"
								action="<c:url value='/action/mypage'/>"
								method="POST">
								<input type="hidden" name="todo" value="mypageWishWebtoon">
								<input id="button2" class="btn btn-primary btn-lg" type="submit"
									value="찜 툰" />
							</form></li>
						<li><form name="Mypagetap"
								action="<c:url value='/action/mypage'/>" method="POST">
								<input type="hidden" name="todo" value="mypageNewWebtoon">
								<input id="button3" class="btn btn-primary btn-lg" type="submit"
									value="신 툰" />
							</form></li>
					</ul>
				</section>
			</div>
		</div>
	</div>
</body>
</html>
