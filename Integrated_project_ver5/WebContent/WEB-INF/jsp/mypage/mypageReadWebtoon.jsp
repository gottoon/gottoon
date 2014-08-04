<%-- MYPAGE READ WEBTOON --%>
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
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />

<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/myPageStar.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypage.css'/>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<c:url value='/css/mypageContents.css'/>" />

<title>MYPAGE - READ WEBTOON</title>
</head>
<body>
	<%-- MYPAGE - READ WEBTOON --%>


		  <ol class="ui progress">

    <li class="active">
      <span>장르선택</span><span>1</span>
    </li>

    <li class="active"> 
      <span>웹툰평가</span><span>2</span>
    </li>

    <li>
      <span>추천받기</span><span>3</span>
    </li>

    <li>
      <span>마이정보</span><span>4</span>
    </li> 

</ol>
		
		


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
	
	<div class="show-levelup">
		<div class="levelup">
			<div>등급이 올랐습니다!</div>
			<input type="submit" class="close"
					value="close">
		</div>
	</div>
	
	<section>
		<div class="state">
			<div id="state_image">
				<div class="gradeImg">
					<div id="level">
						<div>
							<p id="grade" ><strong>Lv.</strong></p> 
						</div>
					</div>
				</div>

				<div id="state_gauge">
					<div>
						<h2>
							지금까지 총 <strong id="readWebtoonCount"></strong> 편의 웹툰을 평가하셨습니다.
						</h2>

						<div id="gauge_meter">
							<meter id="gauge" low=35 high=70 max=100 value=0
								style="display: none;"> </meter>
						</div>

						<div id="progress">
							<div class="progress-bar">
								<canvas id="inactiveProgress" class="progress-inactive"
									height="275px" width="275px"></canvas>
								<canvas id="activeProgress" class="progress-active"
									height="275px" width="275px"></canvas>
								<p></p>
							</div>
							<div id="progressControllerContainer">
								<input type="range" id="progressController" min="0" max="100"
									value=0 />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="contains">
		<div class="gallery">
			<table class="table" border='0' cellpadding='0' align="center">
				<tr>
					<td><input type="hidden" id="readWebtoonScrollCount" value="0">
						<div id="layout"></div></td>
				</tr>
			</table>
		</div>
	</section>

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/less.js/1.7.3/less.min.js"></script>
	<script src="<c:url value='/js/mypageReadWebtoon.js'/>"></script>
	<script src="<c:url value='/js/MyPageAndStarPoint.js'/>"></script>
	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
</body>
</html>
