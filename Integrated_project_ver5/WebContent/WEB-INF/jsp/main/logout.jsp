<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8>
<title>logout</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		session.removeAttribute("userGrade");

		response.sendRedirect("/main");
		// 페이지의 메인으로 이동
	%>
</body>
</html>