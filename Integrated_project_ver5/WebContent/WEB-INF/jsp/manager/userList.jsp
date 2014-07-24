<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8>
<title>usersList</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/list.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css//manager/webtoonList.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css//manager/userList.css'/>">
<script type="text/javascript" src="<c:url value='/js/manager/userList.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/manager/stupidtable.js'/>"></script>

</head>
<body>
	<section id="searchBar">
		이름검색: <input type='text' id='txtFilter'
			onkeyup='{filter();return false}'
			onkeypress='javascript:if(event.keyCode==13){ filter(); return false;}'>
		<br />

	</section>


	<section id="userTable">

		<table class="table table-hover" id="userList">
			<thead>
				<tr>
					<th data-sort="string">users_name</th>
					<th>users_email</th>
					<th data-sort="int">users_grade</th>
					<th data-sort="string">users_blacklist</th>

					<th>keywords</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${allUsers}">
					<tr name="${user.users_name}">

						<td id="<c:out value="${user.users_facebookID_pk}" />"><c:out
								value="${user.users_name}" /></td>
						<td><c:out value="${user.users_email}" /></td>
						<td><c:out value="${user.users_grade}" /></td>
						<td><c:out value="${user.users_blacklist}" /></td>

						<td><button class="keywordBtn" data-toggle="modal">keywords</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</section>




	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">keywords</h4>
				</div>
				<br />
				<div class="modal-body">
					<section id=keywords></section>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
</body>
</html>