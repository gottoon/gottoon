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
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script src="js/transition.js"></script> -->
<%-- <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/respond.js'/>"></script> --%>
<%-- <link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" /> --%>
<link rel="stylesheet"
	href="<c:url value='/css/mypageReadWebtoon.css'/>" />

<%-- <script src="<c:url value='/js/mypageReadWebtoon.js'/>"></script> --%>

<!--[if lt IE 9]>
<script type="text/javascript" src="/js/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">
	$(document).ready(function() {
		var grade = ${grade}
		
			$.ajax({
				type : "POST",
				url : "/Integrated_project_ver5/action/mypageReadWebtoon",
				data : {
					todo : "readWebtoonCount"
			},
			success : function(data) {
				$("#meter2").hide();
				$("#meter1").hide();
				
				if ([ data ] >= 1 && [ data ] < 61) {	
					$("#meter1").show();
				if ([ data ] == 20 ) {
					//해당 할때 한번반 떠야함
					/*  $(".show-modal1").show(function() {
						$(".modal1").fadeIn("fast");
						$(".modal1").fadeOut("slow");
					}); */
					 $("#meter1").hide();
					 $("#meter2").show(); 
				}
				$("#meter1").val([ data ]);
				}
			
				else if ([ data ] >= 60 && [ data ] < 151) {	
					 $("#meter2").show();
				if ([ data ] == 150) {
					/* $('.show-modal1').show(function() {
						$('.modal1').fadeIn('fast');
						$('.modal1').fadeOut('slow');
					});	 */
				}
				$("#meter2").val([ data ]-60);
				
				}
			}
			});
			
			if (grade == 1) {
				$("#grade").append(" <b>고수!!!</b>");
				$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level5.png"/>" width="200" height="200" border="0">');
			}

			if (grade == 2) {
				$("#grade").append(" <b>중수!!</b>");
				$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level3.png"/>" width="150" height="150" border="0">');
			}

			if (grade == 3) {
				$("#grade").append(" <b>하수!</b>");
				$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level1.png"/>" width="100" height="100" border="0">');
			};
			

});
	
</script>
<style>
meter {
-webkit-appearance: meter;
box-sizing: border-box;
display: inline-block;
height: 1.5em;
width: 15em;
vertical-align: -0.2em;
}

.modal1 {
  background-color: #dbd9d9;
  display: none;
  position: fixed;
  top: 50%;
  left: 51em;
  width: 100px;
  height: auto;
  margin-left: -200px;
  margin-top: -150px;
  padding: 50px;
  border-radius: 5px;
  z-index: 10;
}


</style>


<title>MYPAGE READ WEBTOON</title>
</head>

<body>
	<%-- MYPAGE READ WEBTOON --%>
	
	<section>
	<div class="show-modal1">
			<div class="modal1">
				<div>등업!</div>
	</div>
	
	
	<div><p>지금까지 총 ${fn:length(readToon)} 편의 웹툰을 보셨습니다.</p>
	
	<meter id="meter1" low=20 high=40 max=60 value=0>
		<input name="show" />
	</meter>
	
	<meter id="meter2" low=30 high=60 max=90 value=0>
		<input name="show" />
	</meter>
	</div>
		<h2>웹툰을 보는 당신은 웹툰</h2>
		<article>
			<nav>
				<div>
					<p id="grade"></p>
					<table border='0' cellpadding='0' align="center">
						<tr>
							<td class="gradeImg"></td>
						</tr>
					</table>
				</div>
			</nav>
		</article>
	</section>

	<section class="container">
		<table border='0' cellpadding='0' align="center">
			<hr>
			<tr>
				<td><c:forEach var="WebtoonVO" items="${readToon}">
						<div class="col-xs-6 col-md-4">
							<div class="thumbnail">
								<div class="caption">
									<article>
										<!-- 이미지 경로 때문에 nullpointer 뜸 -->
										<%-- <a href="${WebtoonVO.webtoons_url}" target="_blank"><img
											src="<c:url value='${WebtoonVO.webtoons_thumbnail}'/>"
											width="100%" height="100%" border="0"></a> --%> <br> <a
											href="${WebtoonVO.webtoons_url}" target="_blank">${WebtoonVO.webtoons_title}</a>
									</article>
								</div>
							</div>
						</div>
					</c:forEach></td>
			</tr>
		</table>
	</section>
</body>
</html>