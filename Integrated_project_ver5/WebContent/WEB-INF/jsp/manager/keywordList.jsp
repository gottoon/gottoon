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


<script src="<c:url value='/js/manager/keyword.js'/>"></script>

<title>keywords</title>
</head>
<body>

	<nav>
		<!-- Button trigger modal -->
		<button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">키워드 추가 하기</button>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">키워드 추가</h4>
					</div>
					<div class="modal-body">
						<form method="POST" action="<c:url value='/action/manager'/>">

							<div class="form-group has-success has-feedback">
								<label class="control-label" for="inputSuccess2">키워드를
									입력해 주세요</label> <input type="text" class="form-control"
									name="keywordInput"> <span
									class="glyphicon glyphicon-ok form-control-feedback"></span>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" id="addKeywordBtn"
									data-dismiss="modal">Save</button>
								<input type="hidden" name="todo" value="addKeyword" />
							</div>
						</form>

					</div>


				</div>
			</div>
		</div>
	</nav>
	<br />

	<section id=keywords>
		<ul>
			<c:forEach var="keyword" items="${allKeywords}">
				<li>
					<form>
						<input type="hidden" name="keywords_id_pk"
							value="<c:out value='${keyword.keywords_id_pk}'></c:out>">
						<button class="keywordBtn" data-toggle="modal"
							data-target="#myModal2">
							<c:out value='${keyword.keywords_name}'></c:out>
						</button>
					</form>
				</li>
			</c:forEach>
		</ul>

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
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="confirmModalSave"
						data-dismiss="modal">Save changes</button>

				</div>
			</div>
		</div>
	</div>
</body>
</html>