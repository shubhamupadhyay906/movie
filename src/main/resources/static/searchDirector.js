$(document).ready(function() {
	$("#btn").click(function() {
		//postFilm();
		var name = $("#addName").val();
		$.ajax({
			url: '/getDirectorByName/' + name,
			type: 'GET',
			success: function(data) {
				alert(data.name + data.age + data.gender);
				console.log(data.name + data.age + data.gender);
				var jsonStr = JSON.stringify(data);
				document.getElementById("putResultDiv").innerHTML = jsonStr;


			},
			error: function(data) {
				alert("Not Found");
			}

		});
	});


});
