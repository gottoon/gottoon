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
    <!-- 모바일 디바이스를 위한 설정 -->
  <!--  <meta name="viewport" content="width=device-width, initial-scale=1">  -->
<!-- jquery mobile 사용하겠다 -->
<!-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script> -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Genre</title>
</head>

<body>
<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
	<header>

	<h1 align="center">장르 선택을해주세요.</h1>
	<p align="center"><%=session.getAttribute("CurrentUser")%></p>
	
</header>
 <!-- <div data-role="content">  -->
           
	<!-- DB에있는 장르 -->
		<%-- <c:forEach var="GenreVO" items="${allGenres}"> 
		<p align="center">${GenreVO.genres_id_pk}</p>
		</c:forEach>  --%>
	
 	<!--사용자가 입력한 장르  -->
	<%-- 	<c:forEach var="UesrGenreMapsVO" items="${allusergenremaps}">
		<p align="center">${UesrGenreMapsVO.genres_id_fk}</p>
		</c:forEach> --%>
		
	<!-- <script>
	
		/* <c:forEach var="GenreVO" items="${allGenres}"> 
		<p align="center">${GenreVO.genres_id_pk}</p>
		$('#cb${GenreVO.genres_id_pk}').attr("checked", true);
		</c:forEach>  */
		
//	$(document).ready(function(){
	/* $('input:checbox[id="cb1"]').val();
	$('input:checbox[id="cb1"]').prop( 'checked', this.checked ); */
//	$('#cb1').attr("checked", true);
	//$('#cb2').attr("checked", false);
	//$('#cb3').attr("checked", true);
	/* $(":checbox[id='cb1']").attr("checked", true);  */
	//});
	</script> -->
	
	<form method='get' onsubmit="return Validate()"
		action="<c:url value='/action/userGenre'/>">
		<input type="hidden" name='todo' value='addUserGenre'>
		<table border='1' cellpadding='6' align="center">
			<tr>
				<td>
				<input type="checkbox" id="cb1" class="cb1" name="genrechek" value="${allGenres[0].genres_id_pk}">
			<label for="cb1"></label>
				<p>${allGenres[0].genres_id_pk}.${allGenres[0].genres_name}</p></td>
				<td>
				<input type="checkbox" id="cb2" name="genrechek" value="${allGenres[1].genres_id_pk}">
				<label for="cb2"></label>
				<p>${allGenres[1].genres_id_pk}.${allGenres[1].genres_name}</p></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="cb3" name="genrechek" value="${allGenres[2].genres_id_pk}"> 
				<label for="cb3"></label>
					<p>${allGenres[2].genres_id_pk}.${allGenres[2].genres_name}</p></td>
				<td><input type="checkbox" id="cb4" name="genrechek" value="${allGenres[3].genres_id_pk}"> 
				<label for="cb4"></label>
					<p>${allGenres[3].genres_id_pk}.${allGenres[3].genres_name}</p></td>	
			</tr>
			<tr>
				<td><input type="checkbox" id="cb5" name="genrechek" value="${allGenres[4].genres_id_pk}"> 
				<label for="cb5"></label>
					<p>${allGenres[4].genres_id_pk}.${allGenres[4].genres_name}</p></td>
				<td><input type="checkbox" id="cb6" name="genrechek" value="${allGenres[5].genres_id_pk}"> 
				<label for="cb6"></label>
					<p>${allGenres[5].genres_id_pk}.${allGenres[5].genres_name}</p></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="cb7" name="genrechek" value="${allGenres[6].genres_id_pk}"> 
				<label for="cb7"></label>
					<p>${allGenres[6].genres_id_pk}.${allGenres[6].genres_name}</p></td>
				<td><input type="checkbox" id="cb8" name="genrechek" value="${allGenres[7].genres_id_pk}"> 
				<label for="cb8"></label>
					<p>${allGenres[7].genres_id_pk}.${allGenres[7].genres_name}</p></td>				</tr>
			<tr>
				<td><input type="checkbox" id="cb9" name="genrechek" value="${allGenres[8].genres_id_pk}"> 
				<label for="cb9"></label>
					<p>${allGenres[8].genres_id_pk}.${allGenres[8].genres_name}</p></td>
				<td><input type="checkbox" id="cb10" name="genrechek" value="${allGenres[9].genres_id_pk}"> 
				<label for="cb10"></label>
					<p>${allGenres[9].genres_id_pk}.${allGenres[9].genres_name}</p></td>				
					</tr>

			<td><input type="submit" name="장르선택확인" align="center" value="확인">
			<a href="#" data-role="button" data-icon="star">확인</a>
			</td>
		</table>

	</form>
<!-- 	</div> -->
	
	<script>
	/* $("input:checkbox[id='cb10']").is(":checked") == true : false; */
	
		<c:forEach var="UesrGenreMapsVO" items="${allusergenremaps}">
		$('#cb${UesrGenreMapsVO.genres_id_fk}').attr("checked", true);
		</c:forEach>
		</script>
</body>
</html>