<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/list.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/genreList.css'/>">
<title>genres</title>
</head>
<body>
	<nav>
		<!-- Button trigger modal -->
		<button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">장르 추가 하기</button>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">장르 추가</h4>
					</div>
					<div class="modal-body">
						<form method="POST" action="<c:url value='/action/manager'/>">

							<div class="form-group has-success has-feedback">
								<label class="control-label" for="inputSuccess2">장르를 추가해
									주세요</label> <input type="text" class="form-control" name="genreInput">
								<span class="glyphicon glyphicon-ok form-control-feedback"></span>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" id="addKeywordBtn"
									data-dismiss="modal">Save</button>
								<input type="hidden" name="todo" value="addGenre" />
							</div>
						</form>

					</div>


				</div>
			</div>
		</div>
	</nav>
	<br />



	<c:forEach var="genre" items="${allGenres}">
		<div class="ck-button">
			<label> <input type="checkbox"
				value="<c:out value='${genre.genres_id_pk} '></c:out>"><span><c:out
						value='${genre.genres_name}'></c:out></span>
			</label>
		</div>
	</c:forEach>

	<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>

</body>
</html>