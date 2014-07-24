/**
 * 
 */

$(document).ready(function() {
		
	alert("시작");
	$.ajax({
				type : "POST",
				url : "/Integrated_project_ver5/action/mypageReadWebtoon",
				data : {
					todo : "readWebtoonCount" 
			},
			success : function(data) {
				console.log(data);
				$("#meter").hide();
				
				//최소 20개 2등급
				if ([ data ] >= 1 && [ data ] < 20) {	
					$("#meter").show();
					$("#meter").val([ data ] * 5 ); //1당 5
				} else if ([ data ] >= 21 && [ data ] < 40) {	
					$("#meter").show();
					$("#meter").val(([ data ] -20) * 5 ); //1당 5
				} else if ([ data ] >= 41 && [ data ] < 80) {	
					$("#meter").show();
					$("#meter").val(([ data ] -40) * 2.5); //1당 2.5
				} else if ([ data ] >= 81 && [ data ] < 120) {	
					$("#meter").show();
					$("#meter").val(([ data ] -80) * 2.5); //1당 2.5
				} else if ([ data ] >= 121 && [ data ] < 180) {	
					$("#meter").show();
					$("#meter").val(([ data ] -120) * 1.66); //1당 1.66
				} else if ([ data ] >= 181 && [ data ] < 240) {	
					$("#meter").show();
					$("#meter").val(([ data ] -180) * 1.66); //1당 1.66
				} else if ([ data ] >= 241 && [ data ] < 320) {	
					$("#meter").show();
					$("#meter").val(([ data ] -240) * 1.25); //1당 1.25
				} else if ([ data ] >= 321) {	
					$("#meter").show();
					$("#meter").val(100);
				} else {
					$("#meter").show();
					$("#meter").val(0);
				}
				
				var grade = ${grade}
				console.log("${grade}");
				
				if (grade == 1) {
					$("#grade").append(" <b>Level : 1 / 웹툰이라고 알어?</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level1.png"/>" width="200" height="200" border="0">');
				} else if (grade == 2) {
					$("#grade").append(" <b>Level : 2 / 웹툰은 보고 다니냐?</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level2.png"/>" width="200" height="200" border="0">');
				} else if (grade == 3) {
					$("#grade").append(" <b>Level : 3 / 웹툰 초보자</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level3.png"/>" width="200" height="200" border="0">');
				} else if (grade == 4) {
					$("#grade").append(" <b>Level : 4 / 웹툰 좀 보네?</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level4.png"/>" width="200" height="200" border="0">');
				} else if (grade == 5) {
					$("#grade").append(" <b>Level : 5 / 웹툰 중수</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level5.png"/>" width="200" height="200" border="0">');
				} else if (grade == 6) {
					$("#grade").append(" <b>Level : 6 / 장기 고색이시군요.</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level6.png"/>" width="200" height="200" border="0">');
				} else if (grade == 7) {
					$("#grade").append(" <b>Level : 7 / 웹툰 진짜 좋아하나봐?</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level7.png"/>" width="200" height="200" border="0">');
				} else if (grade == 8) {
					$("#grade").append(" <b>Level : 8 / 업계 종사자세요?</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level8.png"/>" width="200" height="200" border="0">');
				} else if (grade == 9) {
					$("#grade").append(" <b>Level : 9 / 경배하라! 웹툰 신이다!</b>");
					$(".gradeImg").append('<img src="<c:url value="/img/gradeImg/grade_level9.png"/>" width="200" height="200" border="0">');
				};
			}
			});	
});