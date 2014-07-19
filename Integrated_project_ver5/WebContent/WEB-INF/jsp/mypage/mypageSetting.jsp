<%-- MYPAGE SETTING --%>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
</style>
<title>MYPAGE SETTING</title>
</head>
<body>
	<%-- MYPAGE SETTING --%>
	<hr>
	<c:if test="${sessionSetting!=null}">
		<form name="MypageSettingTap"
			action="<c:url value='/action/mypageSetting'/>" method="POST">
			<input class="btn btn-success" type="button" value="알람 유무">
		</form>
		<br>
		<form name="MypageSettingTap"
			action="<c:url value='/action/mypageSetting'/>" method="POST">
			<input type="hidden" name="todo" value="onoffRecommendAuthor">
			<input class="btn btn-success" type="submit" value="작가 추천 유무">
		</form>

		<br>
		<form name="MypageSettingTap"
			action="<c:url value='/action/mypageSetting'/>" method="POST">
			<input type="hidden" name="todo" value="onoffRecommendNew"> <input
				class="btn btn-success" type="submit" value="신작 알람 유무">
		</form>

		<br>
		<form name="MypageSettingTap"
			action="<c:url value='/action/mypageSetting'/>" method="POST">
			<input class="btn btn-success" type="button" value="친구 추천 유무">
		</form>
	</c:if>

	<c:if test="${sessionOnoff!=null}">
		<c:if test="${recommendAuthorOnoff!=null}">
			<form name="OnoffSet" action="<c:url value='/action/mypageSetting'/>"
				method="POST">
				<input type="hidden" name="todo" value="setOnoffRecommendAuthor">
				<c:if test="${recommendAuthorOnoff=='0'}">
					<p>작가 추천 받기가 꺼져 있습니다.</p>
					<input type="submit" value="ON">
				</c:if>

				<c:if test="${recommendAuthorOnoff=='1'}">
					<p>작가 추천 받기가 켜져 있습니다.</p>
					<input type="submit" value="OFF">
				</c:if>
			</form>
		</c:if>
	</c:if>

	<c:if test="${sessionOnoff!=null}">
		<c:if test="${recommendNewOnoff!=null}">
			<form name="OnoffSet" action="<c:url value='/action/mypageSetting'/>"
				method="POST">
				<input type="hidden" name="todo" value="setOnoffRecommendNew">
				<c:if test="${recommendNewOnoff=='0'}">
					<p>신작 추천 받기가 꺼져 있습니다.</p>
					<input type="submit" value="ON">
				</c:if>

				<c:if test="${recommendNewOnoff=='1'}">
					<p>신작 추천 받기가 켜져 있습니다.</p>
					<input type="submit" value="OFF">
				</c:if>
			</form>
		</c:if>
	</c:if>
</body>
</html>