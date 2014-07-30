<%-- MYPAGE RECOMMEND WEBTOON --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, mypkg.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="mypage.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/myPageStar.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypage.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypageContents.css'/>" />

<title>MYPAGE RECOMMEND</title>
</head>
<body>
	<%-- MYPAGE RECOMMEND --%>
	
	<%-- <c:if test="${fn:length(wishList)==0}">
		<p>찜한 웹툰이 없습니다.</p>
	</c:if> --%>

	<table class="table" border='0' cellpadding='0' align="center">
		<tr>
			<td><input type="hidden" id="wishWebtoonScrollCount" value="0">
				<div id="layout"></div>
				</div>
				</div>
				</div></td>
		</tr>
	</table>
	</div>
	</section>

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/less.js/1.7.3/less.min.js"></script>
	<script src="<c:url value='/js/mypageWishWebtoon.js'/>"></script>
	<script src="<c:url value='/js/MyPageAndStarPoint.js'/>"></script>
	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
</body>
</html>