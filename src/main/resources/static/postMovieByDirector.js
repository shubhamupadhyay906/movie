$(document).ready(
	function() {

		// SUBMIT FORM
		$("#movieForm").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {


			// PREPARE FORM DATA
			var formData = {
				//name : $("#addFormName").val(),
				//boxOfficeCollection : $("#addBoxOffice").val(),
				//rating : $("#addRating").val(),
				//directorId : $("#addId").val()

				"name": $("#addName").val(),
				"boxOfficeCollection": $("#addBoxOffice").val(),
				"rating": $("#addRating").val(),
				"director": [
					{
						"directorId": $("#addId").val(),
					}
				]
			}
			// DO POST
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/addNewMoviesWithDirectors",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(result) {
					if (result.status == "success") {
						alert("New Movie " + formData.name + " Added");
						$("#postResultDiv").html(
							" " + result.data.productName + " Post Successfully! <br>"
							+ "</p>");

					} else {
						$("#postResultDiv").html("<strong>Error</strong>");
					}

				},
				error: function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			});
		}
	})