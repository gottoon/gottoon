<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/genre.css'/>" />
<script src="<c:url value='/js/genre.js'/>"></script>

<script src="//code.jquery.com/jquery-1.11.0.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Genre</title>

</head>

<body>
<center>
	
	 <c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import> 

	<h1 align="center">장르 선택을해주세요.</h1>
	<p align="center"><%=session.getAttribute("CurrentUser")%></p>
  

	<form method='get' onsubmit="return Validate()"
		action="<c:url value='/action/userGenre'/>">
		<input type="hidden" name='todo' value='addUserGenre'>
		<!-- <div class ="CSSTableGenerator"> -->
		
		<table border='1' cellpadding='6' class="genretable" align="center">
			<tr>
			<table class ="imgTable">
			<td id="g">
				<div class="c"><input type="checkbox" id="cb1" name="genrechek"
					value="${allGenres[0].genres_id_pk}"> <label for="cb1"></label>
					<%-- <p>${allGenres[0].genres_id_pk}.${allGenres[0].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb2" name="genrechek"
					value="${allGenres[1].genres_id_pk}"> <label for="cb2"></label>
					<%-- <p>${allGenres[1].genres_id_pk}.${allGenres[1].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb3" name="genrechek"
					value="${allGenres[2].genres_id_pk}"> <label for="cb3"></label>
					<%-- <p>${allGenres[2].genres_id_pk}.${allGenres[2].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb4" name="genrechek"
					value="${allGenres[3].genres_id_pk}"> <label for="cb4"></label>
					<%-- <p>${allGenres[3].genres_id_pk}.${allGenres[3].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb5" name="genrechek"
					value="${allGenres[4].genres_id_pk}"> <label for="cb5"></label>
					<%-- <p>${allGenres[4].genres_id_pk}.${allGenres[4].genres_name}</p> --%>
					</div>
			
				<div class="c"><input type="checkbox" id="cb6" name="genrechek"
					value="${allGenres[5].genres_id_pk}"> <label for="cb6"></label>
					<%-- <p>${allGenres[5].genres_id_pk}.${allGenres[5].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb7" name="genrechek"
					value="${allGenres[6].genres_id_pk}"> <label for="cb7"></label>
				<%-- 	<p>${allGenres[6].genres_id_pk}.${allGenres[6].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb8" name="genrechek"
					value="${allGenres[7].genres_id_pk}"> <label for="cb8"></label>
					<%-- <p>${allGenres[7].genres_id_pk}.${allGenres[7].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb9" name="genrechek"
					value="${allGenres[8].genres_id_pk}"> <label for="cb9"></label>
					<%-- <p>${allGenres[8].genres_id_pk}.${allGenres[8].genres_name}</p> --%>
					</div>
				<div class="c"><input type="checkbox" id="cb10" name="genrechek"
					value="${allGenres[9].genres_id_pk}"> <label for="cb10"></label>
					<%-- <p>${allGenres[9].genres_id_pk}.${allGenres[9].genres_name}</p> --%>
					</div>
					</td>
			</table>
			</tr>
	
		</table>
	
		<input type="submit" name="장르선택확인" class="button" align="center"
			value="확인">

	

			</form>
		
	<!-- 모달 -->
	<!-- <div class='modalTable'>
    <div class ='modal'>
	<p>신규 냐???</p>  
     <input type ="submit" class ="close" value="확인">     
     </div>
	</div>	 -->
			<script>
				/* $("input:checkbox[id='cb10']").is(":checked") == true : false; */
				/* 기존 유저 선택한 장르 보여주기 */
				<c:forEach var="UesrGenreMapsVO" items="${allusergenremaps}">
				$('#cb${UesrGenreMapsVO.genres_id_fk}').attr("checked", true);
				</c:forEach>
				 /* 신규유져   */
				if('${allusergenremaps[0].genres_id_fk}' == 0){
					alert("신규 User 인가요??  장르를 선택해주세요 다중선택이 가능합니다");
				}
			</script>
		</center>	
		<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
	 <script src="<c:url value='/js/genremodal.js'/>"></script>
		
</body>
</html>