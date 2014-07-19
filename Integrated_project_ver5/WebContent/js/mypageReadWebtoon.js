/**
 * 
 */

function myFunction() {
	var grade = ${grade}	
	
	if(grade==1) {
	$("#grade").append(" <b>고수!!</b>");
	$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level5.png"/>" width="200" height="200" border="0">');
	}

	if(grade==2) {
	$("#grade").append(" <b>중수!!</b>");
	$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level3.png"/>" width="150" height="150" border="0">');
	}

	if(grade==3) {
	$("#grade").append(" <b>하수!!</b>");
	$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level1.png"/>" width="100" height="100" border="0">');
	};
}				
$(document).ready(myFunction); 