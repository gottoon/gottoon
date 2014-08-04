<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>선택된 장르 웹툰</title>

<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
<script src="<c:url value='/js/bar.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>

<!-- <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
	rel='stylesheet' type='text/css'>
 -->
</head>
<body>
	<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>

	<!-- <ol class="ui progress">
		<li class="active"><span>장르선택</span><span>1</span></li>
		<li class="active"><span>웹툰평가</span><span>2</span></li>
		<li><span>추천받기</span><span>3</span></li>
		<li><span>마이정보</span><span>4</span></li>
	</ol> -->

	<section class="big">
		<div class="meterContainer">
			<div aria-hidden="true" class="meterBackground">
				<div aria-hidden="true" class="meterBar">
					<label class="progressValue"> <span class="progressNumber"></span>
						<meter max="10" value="0" class="leaseMeter"></meter>
					</label>
					<!--progressValue-->
				</div>
				<!--meterBar-->
			</div>
			<!--meterBackground-->
		</div>
	</section>

	<input type="hidden" id="count" value="0">

	<form id="button" class="recommand" method="post" action="recommend">
		<input type="hidden" name="todo" value="recommendWebtoons" /> <input
			class="show-modal open-modal" type="submit" value="추천해줭 " />
	</form>

	<div class="container">
		<div class="choose-modal">
			<div>더 평가하면 더 정확한 추천을 해드릴수 있어요</div>
			<form class="recommand" method="post" action="recommend">
				<input type="submit" name="select" value="추천해줭" />
				<input id="showButton" type="button" class="close-modal" href="#" value="더평가할래요 " />
			</form>
		</div>
	</div>

	<div id="layout">
	</div>
</body>
</html>
