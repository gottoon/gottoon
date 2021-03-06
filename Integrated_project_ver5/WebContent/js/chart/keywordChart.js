/**
 * 
 */

var queryObject = "";
	var queryObjectLen = "";

	$.ajax({
		type : 'POST',
		url : '/Integrated_project_ver5/chart/getKeywordData.jsp',
		dataType : 'json',
		success : function(data) {
			queryObject = eval('(' + JSON.stringify(data) + ')');
			queryObjectLen = queryObject.empdetails.length;
		},
		error : function(xhr, type) {
			alert('server error occoured')
		}
	});

	google.load("visualization", "1", {
		packages : [ "controls" ]
	});

	google.setOnLoadCallback(drawDashboard);

	function drawDashboard() {
		var data = new google.visualization.DataTable();
		data.addColumn("string", "keyword");
		data.addColumn("number", "keyword_count");
		for (var i = 0; i < queryObjectLen; i++) {
			var keyword = queryObject.empdetails[i].keyword;
			var keyword_count = queryObject.empdetails[i].keyword_count;
			data.addRows([ [ keyword, parseInt(keyword_count) ] ]);
		}

		var dashboard = new google.visualization.Dashboard(document
				.getElementById('dashboard_div'));

		var donutRangeSlider = new google.visualization.ControlWrapper({
			'controlType' : 'NumberRangeFilter',
			'containerId' : 'filter_div',
			'options' : {
				'filterColumnLabel' : 'keyword_count'
			}
		});

		var pieChart = new google.visualization.ChartWrapper({
			'chartType' : 'PieChart',
			'containerId' : 'chart_div',
			'options' : {
				'width' : 800,
				'height' : 500,
				'pieSliceText' : 'number',
				'legend' : 'right'
			}
		});

		dashboard.bind(donutRangeSlider, pieChart);

		dashboard.draw(data);
	}