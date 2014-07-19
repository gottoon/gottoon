<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/list.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/keywordList.css'/>">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	
<script src="<c:url value='/js/manager/authorList.js'/>"></script>	
	
<title>authors</title>
</head>
<body>

	

<section id=authors>
		<c:forEach var="author" items="${allAuthors}">

			<form>
				<input type="hidden" name="keywords_id_pk"
					value="<c:out value='${author.authors_id_pk}'></c:out>">
				<button class="authorBtn" data-toggle="modal"
					data-target="#myModal2">
					<c:out value='${author.authors_name}'></c:out>
				</button>
			</form>

		</c:forEach>


	</section>




	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">웹툰</h4>
				</div>
				<br />
				<div class="modal-body">
					<section id=webtoons></section>

				</div>

			</div>
		</div>
	</div>
</body>
</html>