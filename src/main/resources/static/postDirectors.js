$(document).ready(
		function() {
			
			// SUBMIT FORM
			$("#directorForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				
				// PREPARE FORM DATA
				var formData = {
						name : $("#addFormName").val(),
						age : $("#addAge").val(),
						gender : $("#addGender").val(),
						count : $("#addCount").val()
					
				}
				
				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/addNewDirector",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							alert("New Director "+formData.name+" Added");
							$("#postResultDiv").html(
									" " + result.data.productName + " Post Successfully! <br>"
											+ "</p>");
							
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
			}
		})