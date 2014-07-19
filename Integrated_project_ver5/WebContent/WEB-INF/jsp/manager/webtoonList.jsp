<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8>
<title>webtoonList</title>
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
	href="<c:url value='/css/manager/webtoonList.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/list.css'/>">

<script type="text/javascript" src="<c:url value='/js/manager/webtoonList.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/manager/stupidtable.js'/>"></script>

</head>
<body>

	<section id="searchBar">
		제목 검색: <input type='text' id='txtFilter'
			onkeyup='{filter();return false}'
			onkeypress='javascript:if(event.keyCode==13){ filter(); return false;}'>
		<br />

	</section>



	<section id="webtoonTable">
		<table class="table table-hover" id="webtoonList">
			<thead>
				<tr>
					<th data-sort="int">ID</th>
					<th data-sort="string">title</th>
					<th data-sort="string">summary</th>
					<th data-sort="string">update_days</th>
					<th data-sort="string">completed</th>
					<th data-sort="string">viewfree</th>
					<th data-sort="string">professional</th>
					<th data-sort="string">pgrating</th>
					<th data-sort="string">publisher</th>
					<th data-sort="float">average_rate</th>
					<th>thumbnail</th>
					<th>url</th>
					<th data-sort="string">first_update</th>
					<th data-sort="string">genres_name</th>
					<th>keywords</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="webtoon" items="${allWebtoons}">
					<tr name="${webtoon.webtoons_title}">
						<td><c:out value="${webtoon.webtoons_id_pk}" /></td>
						<td><c:out value="${webtoon.webtoons_title}" /></td>
						<td><c:out value="${webtoon.webtoons_summary}" /></td>
						<td><c:out value="${webtoon.webtoons_update_days}" /></td>
						<td><c:out value="${webtoon.webtoons_completed}" /></td>
						<td><c:out value="${webtoon.webtoons_viewfree}" /></td>
						<td><c:out value="${webtoon.webtoons_professional}" /></td>
						<td><c:out value="${webtoon.webtoons_pgrating}" /></td>
						<td><c:out value="${webtoon.webtoons_publisher}" /></td>
						<td><c:out value="${webtoon.webtoons_average_rate}" /></td>
						<td><c:out value="${webtoon.webtoons_thumbnail}" /></td>
						<td><a href="<c:url value='${webtoon.webtoons_url}' />">link</a></td>
						<td><c:out value="${webtoon.webtoons_first_update}" /></td>
						<td><c:out value="${webtoon.genres_name}" /></td>
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