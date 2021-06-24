$(document).ready(function() {

	$(function() {
		$.ajax({
			type: "GET",
			url: "/getmovies",
			success: function(result) {
				$.each(result, function(index, mov) {

					var directors = '<tr>' +
						'<td>' + mov.movieId + '</td>' +
						'<td>' + mov.name + '</td>' +
						'<td>' + mov.boxOfficeCollection + '</td>' +
						'<td>' + mov.rating + '</td>' +

						'<td class="text-center">' +
						'<input type="hidden" value=' + mov.name + '>' +
						'<a>' +
						'<span class="glyphicon glyphicon-trash"></span>' +
						'</a>' +

						'</tr>';

					$('#getMovieTable tbody').append(directors);

				});

				$("#getMovieTable tbody tr:odd").addClass("info");
				$("#getMovieTable tbody tr:even").addClass("success");
			},
			error: function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});

	$(document).on("click", "a", function() {

		var name = $(this).parent().find('input').val();
		var workingObject = $(this);

		$.ajax({
			type: "DELETE",
			url: "/deleteMovieByName/" + name,
			success: function(resultMsg) {
				alert(name + " is deleted");
				$("#resultMsgDiv").html("<p style='background-color:black; color:white; padding:10px 10px 10px 10px'>" +
					"lead with Id=" + name + " is deleted successfully!" +
					"</p>");

				workingObject.closest("tr").remove();

				// re-css for table
				$("#getMovieTable tbody tr:odd").addClass("info");
				$("#getMovieTable tbody tr:even").addClass("success");
			},
			error: function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});

})

