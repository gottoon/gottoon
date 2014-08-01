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
	href="<c:url value='/css/manager/list.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/webtoonList.css'/>">

<script type="text/javascript"
	src="<c:url value='/js/manager/webtoonList.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/manager/stupidtable.js'/>"></script>

</head>
<body>

	<div class="container">
		<section id="searchBar">
			<div class=container>
				<div class="col-md-2">

					<img src="<c:url value='/img/manager/toonSearch.png'/>" />
				</div>
				<div class="col-md-8">
					<input type='text' id='txtFilter' onkeyup='{filter();return false}'
						onkeypress='javascript:if(event.keyCode==13){ filter(); return false;}'
						value="제목" onfocus="clearInput(this)">

				</div>
			</div>

		</section>

		<br /> <br />

		<section id="webtoonTable">
			<table class="table table-hover" id="webtoonList">
				<thead>
					<tr>
						<th data-sort="int">ID</th>
						<th data-sort="string">제목</th>
						<th data-sort="string">줄거리</th>
						<th data-sort="string">연재요일</th>
						<th data-sort="string">완결</th>
						<th data-sort="string">유/무료</th>
						<th data-sort="string">프로/아마</th>
						<th data-sort="string">관람가</th>
						<th data-sort="string">제공처</th>
						<th data-sort="float">평균별점</th>
						<th>썸네일</th>
						<th>바로가기</th>
						<th data-sort="string">처음 연재일</th>
						<th data-sort="string">장르</th>
						<th>키워드</th>
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

	</div>




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
					<button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
</body>
</html>