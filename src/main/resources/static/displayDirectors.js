$(document).ready(function() {
	
	$(function () {
		$.ajax({
			type : "GET",
			url :  "/getDirectors",
			success: function(result){
				$.each(result, function(index, dir){
					
					var directors = '<tr>' +
										'<td>' + dir.name + '</td>' +
										'<td>' + dir.age + '</td>' +
										'<td>' + dir.gender + '</td>' +
										'<td>' + dir.count + '</td>' +
										/* '<td class="text-center">' +
								        	'<input type="hidden" value=' + dir.name + '>' +
								        	'<a>' +
						          				'<span class="glyphicon glyphicon-trash"></span>' +
						        			'</a>' +*/
										
									  '</tr>';
					
					$('#getDirectorTable tbody').append(directors);
					
		        });
				
				$( "#getDirectorTable tbody tr:odd" ).addClass("info");
				$( "#getDirectorTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});	
	});
})

