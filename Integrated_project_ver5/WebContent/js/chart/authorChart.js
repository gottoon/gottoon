/**
 * 
 */

var authorQueryObject = "";
	var authorQueryObjectLen = "";

	$.ajax({
		type : 'POST',
		url : '/Integrated_project_ver5/chart/getWebtoonAuthorData.jsp',
		dataType : 'json',
		success : function(data) {
			authorQueryObject = eval('(' + JSON.stringify(data) + ')');
			authorQueryObjectLen = authorQueryObject.empdetails.length;
		},
		error : function(xhr, type) {
			alert('server error occoured')
		}
	});

	google.load("visualization", "1", {
		packages : [ "controls" ]
	});

	google.setOnLoadCallback(drawauthorDashboard);

	function drawauthorDashboard() {
		var authorData = new google.visualization.DataTable();
		authorData.addColumn("string", "authors_name");
		authorData.addColumn("number", "authorsCount");
		for (var i = 0; i < authorQueryObjectLen; i++) {
			var authors_name = authorQueryObject.empdetails[i].authors_name;
			var authorsCount = authorQueryObject.empdetails[i].authorsCount;
			authorData.addRows([ [ authors_name, parseInt(authorsCount) ] ]);
		}

		var authorDashboard = new google.visualization.Dashboard(document
				.getElementById('authorDashboard_div'));

		var authorDonutRangeSlider = new google.visualization.ControlWrapper({
			'controlType' : 'NumberRangeFilter',
			'containerId' : 'authorFilter_div',
			'options' : {
				'filterColumnLabel' : 'authorsCount'
			}
		});

		var authorPieChart = new google.visualization.ChartWrapper({
			'chartType' : 'PieChart',
			'containerId' : 'authorChart_div',
			'options' : {
				'width' : 800,
				'height' : 500,
				'pieSliceText' : 'number',
				'legend' : 'right'
			}
		});

		authorDashboard.bind(authorDonutRangeSlider, authorPieChart);

		authorDashboard.draw(authorData);
	}