<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<title>선택된 장르 웹툰</title>

<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
<script src="<c:url value='/js/bar.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'rel='stylesheet' type='text/css'>

</head>
<body>

	<div class="show-modalStar">
		<div class="modalStar">
			<div>저장 부아악!</div>
		</div>
	</div>

	<div class="show-modalDeleteStar">
		<div class="modalDeleteStar">
			<div>삭제 끄아악!</div>
		</div>
	</div>

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
	<!--meterContainer-->
	<br>
	<br>
	<h1>읽으신 웹툰만 선택해 주세요</h1>
	<br>



	<input type ="hidden" id = "count" value ="0">


	<form id="button" class="recommand" method="post" action="recommend">
		<input type="hidden" name="todo" value="recommendWebtoons" /> <input
			class="show-modal open-modal" type="submit" value="추천해줭 " />
	</form>

<div id= "layout"></div>




	<div class="container">
		<div class="modal">
			<div>더 평가하면 더 정확한 추천을 해드릴수 있어요</div>
			<form class="recommand" method="post" action="recommend">
				<input type="submit" name="select" value="추천해줭" /> <input
					id="showButton" type="button" class="close-modal" href="#"
					value="더평가할래요 " />
			</form>
		</div>
	</div>

</body>
</html>
