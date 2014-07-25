<%-- MYPAGE HEADER --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, mypkg.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<style>
[role="main"] {
    padding-top: 1rem;
    padding-bottom: 2rem;
}
.page {
    background-color: #fff;
    color: #333;
    padding: 2.25rem;
    position: relative;
}
.page .stepout {
    margin: 0 -2.25rem -6.5%;
}

.contents {
    margin: 0 auto;
    padding-left: 5%;
    padding-right: 5%;
    max-width: 75em;
}

.gallery {
    margin-top: 0%;
 }
 
 .gallery-item {
    display: inline-block;
    vertical-align: top;
    margin-bottom: 1.5rem;
}

.gallery-item .vcard {
    width: 95%;
    text-align: center;
    margin-bottom: 0px;
}

.gallery-item .vcard .photo {
width: 100%;
}

h1, h2 {
	text-align: center;
    margin-top: 0;
}
h1, h2, h3, h4 {
	text-align: center;
    line-height: 1;
}
ul, ol {    
	text-align: center;
    margin: 0 0 1em;
    padding: 0;
    list-style: none;
}
img {
	text-align: center;
    border: none;
    margin: 0;
    padding: 0;
    max-width: 100%;
}

@media all and (min-width: 1800px) {
body {
        font-size: 25px;
        font-size: 1.125rem;
    }
   
.gallery .gallery-item {
        width: 15%;
    }

}

@media all and (min-width: 1200px) and (max-width: 1799px) {
body {
        font-size: 20px;
        font-size: 1.125rem;
    }
   
.gallery .gallery-item {
        width: 18%;
    }
}
@media all and (min-width: 1024px) and (max-width: 1199px){
body {
        font-size: 15px;
        font-size: 1.125rem;
    }
   
.gallery .gallery-item {
        width: 18%;
    }

}


@media all and (min-width: 768px) and (max-width: 1023px){
body {
        font-size: 15px;
        font-size: 1.125rem;
    }
   
.gallery .gallery-item {
        width: 20%;
    }

}

@media all and (min-width: 600px) and (max-width: 767px){
body {
        font-size: 15px;
        font-size: 1.125rem;
    }
   
.gallery .gallery-item {
        width: 30%;
    }

}

@media all and (min-width: 527px) and (max-width: 599px){
body {
        font-size: 15px;
        font-size: 1.125rem;
    }
   
.gallery .gallery-item {
        width: 30%;
    }

}

@media all and (max-width: 526px){
body {
        font-size: 15px;
        font-size: 1.125rem;
    }
    
div#image {
	width: 50%;
	float : left;
}

div#desc {
	float : Left;
} 

}
/* .photo {
position: relative;
right: 93px;
}   

form#myForm {
position: relative;
bottom: 210px;
left: 120px;
}

a#mya {
position: relative;
bottom: 210px;
left: 120px;
}

.gallery-item .vcard {
width: 95%;
text-align: center;
margin-bottom: -160px;
}
   */
.gallery .gallery-item {
        width: 100%;
    }
 
}
</style>
<title>MYPAGE</title>
</head>
<body>
	<div id="pgcontainer">
		<c:import url="/WEB-INF/jsp/main/menu.jsp"></c:import>
		<%=session.getAttribute("CurrentUser")%>


<div role="main"><!-- 추가부분  -->
	<div id="contents"><!-- 추가부분  -->
		<h1>마이페이지</h1>


<section class="page"><!-- 추가부분  -->
			<ul class="nav nav-pills">
				<li class="active">
					<form name="Mypagetap"
						action="<c:url value='/action/mypageReadWebtoon'/>" method="POST">
						<input type="hidden" name="todo" value="mypageReadWebtoon">
						<input class="btn btn-success btn-lg" type="submit"
							value="내가 본 웹툰" />
					</form>
				</li>
			<li><form name="Mypagetap" action="<c:url value='/action/mypageRecommendWebtoon'/>" method="POST">
					<input type="hidden" name="todo" value="mypageRecommend"> <input
						class="btn btn-success btn-lg" type="submit" value="신작 / 찜 툰" />
				</form></li>
		</ul>
	</div>
	</body>
	</html>
