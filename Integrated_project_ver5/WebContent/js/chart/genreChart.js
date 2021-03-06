/**
 * 
 */
var genreQueryObject = "";
	var genreQueryObjectLen = "";

	$.ajax({
		type : 'POST',
		url : '/Integrated_project_ver5/chart/getWebtoonGenreData.jsp',
		dataType : 'json',
		success : function(data) {
			genreQueryObject = eval('(' + JSON.stringify(data) + ')');
			genreQueryObjectLen = genreQueryObject.empdetails.length;
		},
		error : function(xhr, type) {
			alert('server error occoured')
		}
	});

	google.load("visualization", "1", {
		packages : [ "controls" ]
	});

	google.setOnLoadCallback(drawGenreDashboard);

	function drawGenreDashboard() {
		var genredata = new google.visualization.DataTable();
		genredata.addColumn("string", "genre");
		genredata.addColumn("number", "genreCount");
		for (var i = 0; i < genreQueryObjectLen; i++) {
			var genre = genreQueryObject.empdetails[i].genre;
			var genreCount = genreQueryObject.empdetails[i].genreCount;
			genredata.addRows([ [ genre, parseInt(genreCount) ] ]);
		}

		var genreDashboard = new google.visualization.Dashboard(document
				.getElementById('genreDashboard_div'));

		var genreDonutRangeSlider = new google.visualization.ControlWrapper({
			'controlType' : 'NumberRangeFilter',
			'containerId' : 'genreFilter_div',
			'options' : {
				'filterColumnLabel' : 'genreCount'
			}
		});

		var genrePieChart = new google.visualization.ChartWrapper({
			'chartType' : 'PieChart',
			'containerId' : 'genreChart_div',
			'options' : {
				'width' : 800,
				'height' : 500,
				'pieSliceText' : 'number',
				'legend' : 'right'
			}
		});

		genreDashboard.bind(genreDonutRangeSlider, genrePieChart);

		genreDashboard.draw(genredata);
	}