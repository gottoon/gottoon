<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Google Chart with jsp Mysql Json</title>
<meta charset="UTF-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script src="<c:url value='/js/chart/userWebtoonChart.js'/>"></script>
</head>
<body>


	<div id="userWebtoonDashboard_div">
		<div id="userWebtoonFilter_div"></div>
		<div id="userWebtoonChart_div"></div>
	</div>
</body>
</html>