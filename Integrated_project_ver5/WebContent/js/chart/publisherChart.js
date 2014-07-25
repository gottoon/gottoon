/**
 * 
 */
var publisherQueryObject = "";
	var publisherQueryObjectLen = "";

	$.ajax({
		type : 'POST',
		url : '/Integrated_project_ver5/chart/getWebtoonPublisherData.jsp',
		dataType : 'json',
		success : function(data) {
			publisherQueryObject = eval('(' + JSON.stringify(data) + ')');
			publisherQueryObjectLen = publisherQueryObject.empdetails.length;
		},
		error : function(xhr, type) {
			alert('server error occoured')
		}
	});

	google.load("visualization", "1", {
		packages : [ "controls" ]
	});

	google.setOnLoadCallback(drawPublisherDashboard);

	function drawPublisherDashboard() {
		var publisherData = new google.visualization.DataTable();
		publisherData.addColumn("string", "webtoons_publisher");
		publisherData.addColumn("number", "publisherCount");
		for (var i = 0; i < publisherQueryObjectLen; i++) {
			var webtoons_publisher = publisherQueryObject.empdetails[i].webtoons_publisher;
			var publisherCount = publisherQueryObject.empdetails[i].publisherCount;
			publisherData.addRows([ [ webtoons_publisher, parseInt(publisherCount) ] ]);
		}

		var publisherDashboard = new google.visualization.Dashboard(document
				.getElementById('publisherDashboard_div'));

		var publisherDonutRangeSlider = new google.visualization.ControlWrapper({
			'controlType' : 'NumberRangeFilter',
			'containerId' : 'publisherFilter_div',
			'options' : {
				'filterColumnLabel' : 'publisherCount'
			}
		});

		var publisherPieChart = new google.visualization.ChartWrapper({
			'chartType' : 'PieChart',
			'containerId' : 'publisherChart_div',
			'options' : {
				'width' : 800,
				'height' : 500,
				'pieSliceText' : 'number',
				'legend' : 'right'
			}
		});

		publisherDashboard.bind(publisherDonutRangeSlider, publisherPieChart);

		publisherDashboard.draw(publisherData);
	}