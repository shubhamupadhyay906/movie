$(document).ready(function(){

	$("#tbn1").click(function(){
		var name= $("#addMovieName").val();
		$.ajax({
			url: '/getDirectorDetailsByMovieName/'+name,
			type: 'GET',
			success: function(data){
			alert(data.name+data.boxOfficeCollection+data.rating);
			console.log(data.name+data.boxOfficeCollection+data.rating);
			//$("#putResultDiv").html('<h4>'+data.name+" "+data.boxOfficeCollection +'</h4>');
			var jsonStr = JSON.stringify(data);
				document.getElementById("putResultDiv").innerHTML = jsonStr;
					
			},
			error: function(data){
				alert("Not Found");
			}
			
		});
	});
	
	
});
