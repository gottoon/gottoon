<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/showToon.css'/>" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value='/js/showToon.js'/>"></script>
<title>Insert title here</title>
</head>
<body>
<form id="myForm">
<input type="hidden" name="webtoons_id_pk" value="${webtoonInfo.webtoons_id_pk}" />
<input type="hidden"	name="webtoons_title" value="${webtoonInfo.webtoons_title}" />
<div class="product-review-stars">
<input type="radio" id="${status.count*status.count*status.count+1}" name="rating" 
value="5^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">
<label for="${status.count*status.count*status.count+1}" title="Rocks!">â</label>
<input type="radio" id="${status.count*status.count*status.count+2}" name="rating" 
value="4^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">
<label for="${status.count*status.count*status.count+2}" title="Pretty good">â</label>
<input type="radio" id="${status.count*status.count*status.count+3}" name="rating" 
value="3^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">
<label for="${status.count*status.count*status.count+3}" title="Meh">â</label>
<input type="radio" id="${status.count*status.count*status.count+4}" name="rating" 
value="2^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">
<label for="${status.count*status.count*status.count+4}" title="Kinda bad">â</label>
<input type="radio" id="${status.count*status.count*status.count+5}" name="rating" 
value="1^${webtoonInfo.webtoons_id_pk}" onclick=onclickStart(this) class="visuallyhidden">
<label for="${status.count*status.count*status.count+5}" title="Sucks big time">â</label>
</div>
</form>
</body>
</html>