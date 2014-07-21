<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<!-- jquery js  -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<!-- 부트스트랩 css,js  -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- jquery ui css,js -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>


<!-- 파일업로드 bootstrap CSS,JS -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>

<!--매니져 css  -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/manager.css'/>">



<script type="text/javascript" src="<c:url value='/js/manager/manager.js'/>"></script>
<title>관리자 페이지</title>


</head>
<body>
	<header> </header>

	<!-- Single button -->

	<nav>
		<ul>
			<!--웹툰 메뉴  -->
			<li><div class="btn-group">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">
						웹툰 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><form method="POST"
								action="<c:url value='/action/manager'/>">
								<button type="submit">
									<p>모든 웹툰 보기</p>
								</button>
								<input type="hidden" name="todo" value="getAllWebtoons" />
							</form></li>
						<li><form method="POST"
								action="<c:url value='/action/manager'/>">
								<button type="submit">
									<p>모든 장르 보기</p>
								</button>
								<input type="hidden" name="todo" value="getAllGenres" />
							</form></li>
						<li><form method="POST"
								action="<c:url value='/action/manager'/>">
								<button type="submit">
									<p>웹툰 연관성 점수 보기</p>
								</button>
								<input type="hidden" name="todo" value="getRelativeRate" />
							</form></li>
						<li><a href="#">웹툰 등록하기</a></li>
						<li class="divider"></li>
					</ul>
				</div></li>

			<!--챠트 메뉴  -->
			<li><div class="btn-group">
					<button type="button" class="btn btn-danger dropdown-toggle"
						data-toggle="dropdown">
						챠트보기 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">

						<li><form method="POST"
								action="<c:url value='/action/chart'/>">
								<button type="submit">
									<p>키워드 차트 보기</p>
								</button>
								<input type="hidden" name="todo" value="keywordChart" />
							</form></li>
						<li class="divider"></li>
						<li><form method="POST"
								action="<c:url value='/action/chart'/>">
								<button type="submit">
									<p>작가 차트 보기</p>
								</button>
								<input type="hidden" name="todo" value="authorChart" />
							</form></li>
						<li><form method="POST"
								action="<c:url value='/action/chart'/>">
								<button type="submit">
									<p>장르 차트 보기</p>
								</button>
								<input type="hidden" name="todo" value="genreChart" />
							</form></li>
						<li class="divider"></li>
						<li><form method="POST"
								action="<c:url value='/action/chart'/>">
								<button type="submit">
									<p>제공처 차트 보기</p>
								</button>
								<input type="hidden" name="todo" value="publisherChart" />
							</form></li>
						<li class="divider"></li>
						<li><form method="POST"
								action="<c:url value='/action/chart'/>">
								<button type="submit">
									<p>유저&웹툰 차트 보기</p>
								</button>
								<input type="hidden" name="todo" value="userWebtoonChart" />
							</form></li>
						<li class="divider"></li>
					</ul>
				</div></li>



			<li><div class="btn-group">
					<button type="button" class="btn btn-success dropdown-toggle"
						data-toggle="dropdown">
						키워드 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">

						<li><form method="POST"
								action="<c:url value='/action/manager'/>">
								<button type="submit">
									<p>모든 키워드 보기</p>
								</button>
								<input type="hidden" name="todo" value="getAllKeywords" />
							</form></li>
						<li class="divider"></li>
					</ul>
				</div></li>

			<!-- 작가 메뉴  -->
			<li><div class="btn-group">
					<button type="button" class="btn btn-info dropdown-toggle"
						data-toggle="dropdown">
						작가 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">

						<li><form method="POST"
								action="<c:url value='/action/manager'/>">
								<button type="submit">
									<p>모든 작가 보기</p>
								</button>
								<input type="hidden" name="todo" value="getAllAuthors" />
							</form></li>
						<li class="divider"></li>
					</ul>
				</div></li>
			<li>
				<!--유저 메뉴  -->
				<div class="btn-group">
					<button type="button" class="btn btn-warning dropdown-toggle"
						data-toggle="dropdown">
						유저 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">

						<li><form method="POST"
								action="<c:url value='/action/manager'/>">
								<button type="submit">
									<p>모든 유저 보기</p>
								</button>
								<input type="hidden" name="todo" value="getAllUsers" />
							</form></li>
						<li class="divider"></li>
					</ul>
				</div>
			</li>
		</ul>
	</nav>



	<section id="addWebtoon">
		<form role="form" method="post"
			action="<c:url value='/action/manager'/>">
			<div class="form-group">
				<label for="title">타이틀</label> <br /> <input type="text"
					class="form-control" placeholder="웹툰 제목을 입력하세요" id="webtoons_title">
			</div>
			<div class="form-group">

				<label for="title">줄거리</label><br />

				<textarea class="form-control" rows="3" id="webtoons_summary"></textarea>
			</div>
			<div class="form-group">
				<div class="ui-widget">
					<label for="author">작가</label> <input type="text"
						class=form-control placeholder="작가를 입력하세요 " id="authors_name">
				</div>
			</div>
			<div class="form-group">

				<label for="title">업데이트요일</label><br /> <label
					class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="월"> 월
				</label> <label class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="화"> 화
				</label> <label class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="수"> 수
				</label><label class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="목"> 목
				</label><label class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="금"> 금
				</label><label class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="토"> 토
				</label><label class="checkbox-inline"> <input type="checkbox"
					id="webtoons_update_days" value="일"> 일
				</label>
			</div>
			<div class="form-group">

				<label for="">현재 연재활동</label><br /> <label class="radio-inline">
					<input type="radio" name="webtoons_completed"
					id="webtoons_completed" value="중"> 연재 중
				</label> <label class="radio-inline"> <input type="radio"
					name="webtoons_completed" id="webtoons_completed" value="완결">
					완결
				</label> <label class="radio-inline"> <input type="radio"
					name="webtoons_completed" id="webtoons_completed" value="휴재">
					휴재
				</label>
			</div>
			<div class="form-group">
				<label for="">유/무료 여부</label><br /> <label class="radio-inline">
					<input type="radio" name="webtoons_viewfree" id="webtoons_viewfree"
					value="무료"> 무료
				</label> <label class="radio-inline"> <input type="radio"
					name="webtoons_viewfree" id="webtoons_viewfree" value="유료">
					유료
				</label><label class="radio-inline"> <input type="radio"
					name="webtoons_viewfree" id="webtoons_viewfree" value="부분유료">
					부분 유료
				</label>
			</div>

			<div class="form-group">

				<label for="">프로/아마</label> <br /> <label class="radio-inline">
					<input type="radio" name="inlineRadioOptions" id="inlineRadio1"
					value="option1"> 프로
				</label> <label class="radio-inline"> <input type="radio"
					name="inlineRadioOptions" id="inlineRadio2" value="option2">
					아마추어
				</label>

				<div class="form-group">
					<label for="">관람가</label><br /> <label class="radio-inline">
						<input type="radio" name="inlineRadioOptions" id="inlineRadio1"
						value="option1"> 전체
					</label> <label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio3" value="option3">
						성인
					</label>
				</div>
				<div class="form-group">
					<label for="">제공처</label><br />
				</div>
				<div class="form-group">
					<label for="">장르</label><br />
				</div>
				<div class="form-group">
					<label for="">대표 이미지</label><br />
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-preview thumbnail" data-trigger="fileinput"
							style="width: 500px; height: 300px;"></div>
						<div>
							<span class="btn btn-default btn-file"><span
								class="fileinput-new">Select image</span><span
								class="fileinput-exists">Change</span><input type="file"
								name="..."></span> <a href="#"
								class="btn btn-default fileinput-exists"
								data-dismiss="fileinput">Remove</a>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="">썸네일</label><br />
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-preview thumbnail" data-trigger="fileinput"
							style="width: 500px; height: 300px;"></div>
						<div>
							<span class="btn btn-default btn-file"><span
								class="fileinput-new">Select image</span><span
								class="fileinput-exists">Change</span><input type="file"
								name="thumnail"></span> <a href="#"
								class="btn btn-default fileinput-exists"
								data-dismiss="fileinput">Remove</a>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="">url</label> <input type="text" class="form-control"
						placeholder="Text input">
				</div>
				<button type="submit" class="btn btn-primary">저장하기</button>
				<input type="hidden" name="todo" value="addWebtoon" />
		</form>

	</section>

	<!-- Button trigger modal -->
	<button class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">Launch demo modal</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<form role="" action=""></form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>