<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- 파일업로드 bootstrap CSS,JS -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/datepicker.css'/>">
<script src="<c:url value='/js/manager/bootstrap-datepicker.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/manager/addWebtoon.css'/>">
<script src="<c:url value='/js/manager/addWebtoon.js'/>"></script>

<title>웹툰 등록</title>
</head>
<body>


	<div class="container">

		<section id="addWebtoon">
			<form role="form">
				<div class="form-group">
					<div class="centerDiv">
						<h4>타이틀</h4>
						<button class="information" type="button" data-container="body"
							data-toggle="popover" data-placement="right"
							data-content="웹툰의 제목을 입력해주세요 ^^ ">
							<span class="glyphicon glyphicon-question-sign"></span>
						</button>
						<br /> <input type="text" class="form-control"
							placeholder="웹툰 제목을 입력하세요" id="webtoons_title">
					</div>
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<h4>줄거리</h4>
						<br />

						<textarea class="form-control" rows="3" id="webtoons_summary"
							placeholder="줄거리를 입력해 주세요"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<button id="authorBtn" data-toggle="modal"
							data-target="#authorsModal" type="button" class="btn btn-success">작가</button>
						<div id="authorName">
							<input type="hidden" name="authors_id" value="">
						</div>
						<button type="button" class="btn btn-danger" id="authorReset">reset</button>
					</div>
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<button id="keywordBtn" data-toggle="modal"
							data-target="#keywordModal" type="button" class="btn btn-info">키워드</button>
						<div id="keywordName">
							<input type="hidden" name="keywords_id" value="">
						</div>
						<button type="button" class="btn btn-danger" id="keywordReset">reset</button>
					</div>

				</div>
				<div class="form-group">
					<div class="centerDiv">
						<button id="publisherBtn" data-toggle="modal"
							data-target="#publisherModal" type="button"
							class="btn btn-primary">제공처</button>

						<div id="publisherName">
							<input type="hidden" name="publishers_id" value="">
						</div>
						<button type="button" class="btn btn-danger" id="publisherReset">reset</button>

					</div>
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<button id="genreBtn" data-toggle="modal"
							data-target="#genreModal" type="button" class="btn btn-warning">장르</button>

						<div id="genreName">
							<input type="hidden" name="genres_id" value="">
						</div>

						<button type="button" class="btn btn-danger" id="genreReset">reset</button>
					</div>

				</div>
				<div class="form-group">
					<div class="centerDiv">
						<h4>업데이트요일</h4>
						<br /> <label class="checkbox-inline"> <input
							type="checkbox" name="webtoons_update_days" value="월"> 월
						</label> <label class="checkbox-inline"> <input type="checkbox"
							name="webtoons_update_days" value="화"> 화
						</label> <label class="checkbox-inline"> <input type="checkbox"
							name="webtoons_update_days" value="수"> 수
						</label><label class="checkbox-inline"> <input type="checkbox"
							name="webtoons_update_days" value="목"> 목
						</label><label class="checkbox-inline"> <input type="checkbox"
							name="webtoons_update_days" value="금"> 금
						</label><label class="checkbox-inline"> <input type="checkbox"
							name="webtoons_update_days" value="토"> 토
						</label><label class="checkbox-inline"> <input type="checkbox"
							name="webtoons_update_days" value="일"> 일
						</label>
					</div>
				</div>

				<div class="form-group">
					<div class="centerDiv">
						<h4>연재 시작일</h4>
						<div class="input-append date" data-date-format="dd-mm-yyyy">
							<input class="span2" size="16" type="text"
								id="webtoons_first_update" placeholder="dd-mm-yy"> <span
								class="add-on"><i class="icon-th"></i></span>
						</div>
					</div>
				</div>


				<div class="form-group">
					<div class="centerDiv">
						<h4>현재 연재활동</h4>
						<br /> <label class="radio-inline"> <input type="radio"
							name="webtoons_completed" id="webtoons_completed" value="중">
							연재 중
						</label> <label class="radio-inline"> <input type="radio"
							name="webtoons_completed" value="완결"> 완결
						</label> <label class="radio-inline"> <input type="radio"
							name="webtoons_completed" value="휴재"> 휴재
						</label>
					</div>
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<h4>유/무료 여부</h4>
						<br /> <label class="radio-inline"> <input type="radio"
							name="webtoons_viewfree" value="무료"> 무료
						</label> <label class="radio-inline"> <input type="radio"
							name="webtoons_viewfree" value="유료"> 유료
						</label><label class="radio-inline"> <input type="radio"
							name="webtoons_viewfree" value="부분유료"> 부분 유료
						</label>
					</div>
				</div>

				<div class="form-group">
					<div class="centerDiv">
						<h4>프로/아마</h4>
						<button class="information" type="button" data-container="body"
							data-toggle="popover" data-placement="right"
							data-content="프로 = 돈을 받고 만화를 그린다             아마추어 = 내가 그린 만화는 공짜다  ">
							<span class="glyphicon glyphicon-question-sign"></span>
						</button>
						<br /> <label class="radio-inline"> <input type="radio"
							name="webtoons_professional" value="true"> 프로
						</label> <label class="radio-inline"> <input type="radio"
							name="webtoons_professional" value="false"> 아마추어
						</label>
					</div>
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<h4>관람가</h4>
						<br /> <label class="radio-inline"> <input type="radio"
							name="webtoons_pgrating" value="전체"> 전체
						</label> <label class="radio-inline"> <input type="radio"
							name="webtoons_pgrating" value="성인"> 성인
						</label>
					</div>
				</div>

				<div class="form-group">
					<div class="centerDiv">
						<h4>대표 이미지</h4>
						<br />
						<div class="fileinput fileinput-new" data-provides="fileinput">
							<div class="fileinput-preview thumbnail" data-trigger="fileinput"></div>
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
				</div>
				<div class="form-group">
					<div class="centerDiv">
						<h4>썸네일</h4>
						<br />
						<div class="fileinput fileinput-new" data-provides="fileinput">
							<div class="fileinput-preview thumbnail" data-trigger="fileinput"></div>
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
				</div>

				<div class="form-group">
					<div class="centerDiv">
						<h4>url</h4>
						<input type="text" class="form-control"
							placeholder="웹툰의 URL을 입력해 주세요" id="webtoons_url">
					</div>
				</div>
				<div class="centerDiv">
					<button type="submit" class="btn btn-primary btn-lg" id="saveBtn"
						data-target="#confirmModal" data-toggle="modal">확인</button>
				</div>
			</form>
		</section>


	</div>

	<!--작가 모달  -->
	<div class="modal fade" id="authorsModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">작가</h4>
					<input class="form-control" name="newAuthor"
						placeholder="작가를 추가해 주세요">
				</div>
				<br />
				<div class="modal-body">

					<section id=authors></section>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="modalSave">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--장르 모달  -->
	<div class="modal fade" id="genreModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">작가</h4>
					<input class="form-control" name="newGenre"
						placeholder="장르를 추가해 주세요">
				</div>
				<br />
				<div class="modal-body">

					<section id=genres></section>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="genreModalSave">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!--제공처 모달  -->
	<div class="modal fade" id="publisherModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">제공처</h4>
					<input class="form-control" name="newPublisher"
						placeholder="제공처를 추가해 주세요">
				</div>
				<br />
				<div class="modal-body">

					<section id=publisher></section>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="publisherModalSave">Save changes</button>
				</div>
			</div>
		</div>
	</div>









	<!--키워드 모달  -->
	<div class="modal fade" id="keywordModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">키워드</h4>
					<input class="form-control" name="newKeyword"
						placeholder="키워드를 추가해 주세요">
				</div>
				<br />
				<div class="modal-body">

					<section id=keywords></section>
					
				</div>
<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="keywordModalSave">Save changes</button>
					</div>
			</div>
		</div>
	</div>



	<!--확인 모달  -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">확인창</h4>

				</div>
				<br />
				<div class="modal-body">

					<section id=confirmWebtoon>
						<article>
							<div id="confirmTitle"></div>
						</article>
						<article>
							<div id="confirmSummary"></div>
						</article>
						<article>
							<div id="confirmKeyword"></div>
						</article>
						<article>
							<div id="confirmGenre"></div>
						</article>
						<article>
							<div id="confirmAuthor"></div>
						</article>
						<article>
							<div id="confirmPublisher"></div>
						</article>
						<article>
							<div id="confirmUpdate_days"></div>
						</article>
						<article>
							<div id="confirmFirst_update_day"></div>
						</article>
						<article>
							<div id="confirmCompleted"></div>
						</article>
						<article>
							<div id="confirmViewfree"></div>
						</article>
						<article>
							<div id="confirmProfessional"></div>
						</article>
						<article>
							<div id="confirmPgrating"></div>
						</article>
						<article>
							<div id="confirmUrl"></div>
						</article>
					</section>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="confirmModalSave"
						data-dismiss="modal">Save changes</button>

				</div>
			</div>
		</div>
	</div>


	<!--ajax wait  -->
	<div id="wait" style="display: none;">
		<img src="<c:url value='/img/menu/ajax-loader.gif'/>" width="64"
			height="64" /><br>Loading..
	</div>
	<!--ajax wait  -->


</body>
</html>