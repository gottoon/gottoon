<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/genre.css'/>" />
<script src="<c:url value='/js/genre.js'/>"></script>

<script src="//code.jquery.com/jquery-1.11.0.js"></script>
<title>Show Genre</title>
</head>

<body>


	<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
	<div class="container">

		<section id="selectSection">
			<form id="genreForm" method='post' onsubmit="return Validate()"
				action="<c:url value='/action/userGenre'/>">
				<input type="hidden" name='todo' value='addUserGenre'>
				<!-- <div class ="CSSTableGenerator"> -->

				<div class="sub">
					<input type="submit" name="장르선택확인" id="nextButton" align="center"
						value="다음 단계로">
				</div>

				<table border='1' cellpadding='6' id="genretable" align="center">
					<tr>
						<td id="tabletd">
							<div class="genreimg">
								<input type="checkbox" id="cb1" name="genrechek" class="check"
									value="${allGenres[0].genres_id_pk}"> <label for="cb1"></label>
								<h1 class="genrefont">SF</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb2" name="genrechek" class="check"
									value="${allGenres[1].genres_id_pk}"> <label for="cb2"></label>
								<h1 class="genrefont">코믹</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb3" name="genrechek" class="check"
									value="${allGenres[2].genres_id_pk}"> <label for="cb3"></label>
								<h1 class="genrefont">공포</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb4" name="genrechek" class="check"
									value="${allGenres[3].genres_id_pk}"> <label for="cb4"></label>
								<h1 class="genrefont">드라마</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb5" name="genrechek" class="check"
									value="${allGenres[4].genres_id_pk}"> <label for="cb5"></label>
								<h1 class="genrefont">로맨스</h1>
							</div>

							<div class="genreimg">
								<input type="checkbox" id="cb6" name="genrechek" class="check"
									value="${allGenres[5].genres_id_pk}"> <label for="cb6"></label>
								<h1 class="genrefont">스릴러</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb7" name="genrechek" class="check"
									value="${allGenres[6].genres_id_pk}"> <label for="cb7"></label>
								<h1 class="genrefont">스포츠</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb8" name="genrechek" class="check"
									value="${allGenres[7].genres_id_pk}"> <label for="cb8"></label>
								<h1 class="genrefont">액션</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb9" name="genrechek" class="check"
									value="${allGenres[8].genres_id_pk}"> <label for="cb9"></label>
								<h1 class="genrefont">일상</h1>
							</div>
							<div class="genreimg">
								<input type="checkbox" id="cb10" name="genrechek" class="check"
									value="${allGenres[9].genres_id_pk}"> <label for="cb10"></label>
								<h1 class="genrefont">판타지</h1>
							</div>
						</td>
					</tr>

				</table>

			</form>
		</section>






	</div>
	<!-- 모달 -->
	<div class='modalTable'>
		<div class="modal1">
			<h3>신규 인가요?</h3>
			<h4>
				장르 선택을 해주세요. <br>(다중선택가능)
			</h4>
			<input type="submit" class="close" value="close">
		</div>
		<img src="<c:url value='/img/genreImg/kaka.png'/>" />

	</div>
	<script>
		
		/* 기존 유저 선택한 장르 보여주기 */
		<c:forEach var="UesrGenreMapsVO" items="${allusergenremaps}">
		$('#cb${UesrGenreMapsVO.genres_id_fk}').attr("checked", true);
		</c:forEach>

		/* 신규유져   */
		if ('${allusergenremaps[0].genres_id_fk}' == 0) {
			/* 모달 */
			$(document).ready(function() {

				$('.modalTable').show(function() {
					$('.modal').fadeIn('slow');

				});

				$('.close').click(function() {
					$('.modalTable').fadeOut('slow');

				});

			});
			/*  alert("신규 User 인가요??  장르를 선택해주세요 다중선택이 가능합니다");  */
			/* 신규 버튼 안보이기 */
			$(document).ready(function() {
				$(".but").hide();
				$(".check").click(function(event) {
					$(".but").show();
				});

			});
		}
	</script>

	<!-- 모달 -->
	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
	<script src="<c:url value='/js/genremodal.js'/>"></script>

</body>
</html>
