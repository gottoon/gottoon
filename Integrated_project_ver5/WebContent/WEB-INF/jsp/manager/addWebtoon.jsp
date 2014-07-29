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
					<label for="title">타이틀</label> <br /> <input type="text"
						class="form-control" placeholder="웹툰 제목을 입력하세요"
						id="webtoons_title">
				</div>
				<div class="form-group">

					<label for="title">줄거리</label><br />

					<textarea class="form-control" rows="3" id="webtoons_summary"
						placeholder="줄거리를 입력해 주세요"></textarea>
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
						name="webtoons_update_days" value="월"> 월
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

				<div class="form-group">
					<label class="datepicker">연재 시작일</label>
					<div class="input-append date" data-date-format="dd-mm-yyyy">
						<input class="span2" size="16" type="text"
							id="webtoons_first_update" placeholder="dd-mm-yy"> <span class="add-on"><i
							class="icon-th"></i></span>
					</div>
				</div>


				<div class="form-group">

					<label for="">현재 연재활동</label><br /> <label class="radio-inline">
						<input type="radio" name="webtoons_completed"
						id="webtoons_completed" value="중"> 연재 중
					</label> <label class="radio-inline"> <input type="radio"
						name="webtoons_completed" value="완결"> 완결
					</label> <label class="radio-inline"> <input type="radio"
						name="webtoons_completed" value="휴재"> 휴재
					</label>
				</div>
				<div class="form-group">
					<label for="">유/무료 여부</label><br /> <label class="radio-inline">
						<input type="radio" name="webtoons_viewfree" value="무료">
						무료
					</label> <label class="radio-inline"> <input type="radio"
						name="webtoons_viewfree" value="유료"> 유료
					</label><label class="radio-inline"> <input type="radio"
						name="webtoons_viewfree" value="부분유료"> 부분 유료
					</label>
				</div>

				<div class="form-group">

					<label for="">프로/아마</label> <br /> <label class="radio-inline">
						<input type="radio" name="webtoons_professional" value="true">
						프로
					</label> <label class="radio-inline"> <input type="radio"
						name="webtoons_professional" value="false"> 아마추어
					</label>
				</div>
				<div class="form-group">
					<label for="">관람가</label><br /> <label class="radio-inline">
						<input type="radio" name="webtoons_pgrating" value="전체">
						전체
					</label> <label class="radio-inline"> <input type="radio"
						name="webtoons_pgrating" value="성인"> 성인
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
				<div class="form-group">
					<label for="">썸네일</label><br />
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
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label for="">url</label> <input type="text" class="form-control"
								placeholder="웹툰의 URL을 입력해 주세요" id="webtoons_url">
						</div>
						<button type="submit" class="btn btn-primary btn-lg" id="saveBtn">저장하기</button>
					</div>
				</div>
			</form>
		</section>


	</div>
</body>
</html>