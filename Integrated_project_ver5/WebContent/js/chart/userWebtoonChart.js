/**
 * 
 */
var userWebtoonQueryObject = "";
var userWebtoonQueryObjectLen = "";

$.ajax({
	type : 'POST',
	url : '/Integrated_project_ver5/chart/getUserWebtoonData.jsp',
	dataType : 'json',
	success : function(data) {
		userWebtoonQueryObject = eval('(' + JSON.stringify(data) + ')');
		userWebtoonQueryObjectLen = userWebtoonQueryObject.empdetails.length;
	},
	error : function(xhr, type) {
		alert('server error occoured')
	}
});

google.load("visualization", "1", {
	packages : [ "controls" ]
});

google.setOnLoadCallback(drawuserWebtoonDashboard);

function drawuserWebtoonDashboard() {
	var userWebtoonData = new google.visualization.DataTable();
	userWebtoonData.addColumn("string", "webtoons_title");
	userWebtoonData.addColumn("number", "userWebtoonCount");
	for (var i = 0; i < userWebtoonQueryObjectLen; i++) {
		var webtoons_title = userWebtoonQueryObject.empdetails[i].webtoons_title;
		var userWebtoonCount = userWebtoonQueryObject.empdetails[i].userWebtoonCount;
		userWebtoonData
				.addRows([ [ webtoons_title, parseInt(userWebtoonCount) ] ]);
	}

	var userWebtoonDashboard = new google.visualization.Dashboard(document
			.getElementById('userWebtoonDashboard_div'));

	var userWebtoonDonutRangeSlider = new google.visualization.ControlWrapper({
		'controlType' : 'NumberRangeFilter',
		'containerId' : 'userWebtoonFilter_div',
		'options' : {
			'filterColumnLabel' : 'userWebtoonCount'
		}
	});

	var userWebtoonPieChart = new google.visualization.ChartWrapper({
		'chartType' : 'PieChart',
		'containerId' : 'userWebtoonChart_div',
		'options' : {
			'width' : 800,
			'height' : 500,
			'pieSliceText' : 'number',
			'legend' : 'right'
		}
	});

	userWebtoonDashboard.bind(userWebtoonDonutRangeSlider, userWebtoonPieChart);

	userWebtoonDashboard.draw(userWebtoonData);
}