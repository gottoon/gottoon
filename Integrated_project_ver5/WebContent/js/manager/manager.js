/**
 * 
 */



$(document).ready(function() {
	function getAuthors() {
		var todo = "getAuthors";
		$.ajax({
			url : "manager",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				todo : todo
			},
			success : function(data) {
				var authors = jQuery.parseJSON(data);
				$("#authors_name").autocomplete({
					source : authors
				});
			}
		});
	}
	var getAuthorsStart = getAuthors();

});
