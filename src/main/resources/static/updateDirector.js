$(document).ready(function() {
	
	$(document).on('submit', 'form', function (e) {
		event.preventDefault();
		fillDetailsToUpdateForm($(this));
	})
	
	$("#customizedForm").submit(function(){
		event.preventDefault();
		ajaxPut();
	});
	
	function fillDetailsToUpdateForm(object){
		var dirId = $(object).find("input[name='directorId']").val();
		var name = $(object).find("input[name='name']").val();
		var gender = $(object).find("input[name='gender']").val();
		var age = $(object).find("input[name='age']").val();
		var count = $(object).find("input[name='count']").val();
	
		
		$("#updateFormDirId").val(dirId);
		$("#updateFormName").val(name);
		$("#updateFormGender").val(gender);
		$("#updateFormAge").val(age);
		$("#updateFormAwardCount").val(count);
	
	}
	
	
    function ajaxPut(){
    	// PREPARE FORM DATA
    	var formData = {
    			id: $("#updateFormDirId").val(),
    			name : $("#updateFormName").val(),
				gender : $("#updateFormGender").val(),
    			age : $("#updateFormAge").val(),
				count : $("#updateFormAwardCount").val(),
    	
    	}
    	
    	var name = $("#updateFormName").val();
    	
    	console.log("formData before PUT: " + formData);
    	
    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url : "/updateDirectorsAgeAndAwardCount/" + name,
			data : JSON.stringify(formData),
			dataType : 'json',
			
			// SUCCESS response
			success : function(director) {
				// Create successful message
				$("#putResultDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
											"Put Successfully! <br>" +
											"--> {id: " + director.id +
												"name: " + director.name +
												"gender: " + director.gender +
												"Age: " + director.age +
												"count: " + director.count +
												"}</p>");
				
				// Again fill data to Update-Form
				$("#updateFormDirId").val(director.id);
				$("#updateFormName").val(director.name);
				$("#updateFormGender").val(director.gender);
				$("#updateFormAge").val(director.age);
				$("#updateFormAwardCount").val(director.count);
			
				
				// Update name of the updated customer on Customer List
				$('#empform_' + director.id).find("input[name='name']").val(director.name);
				$('#empform_' + director.id).find("input[name='gender']").val(director.gender);
				$('#empform_' + director.id).find("input[name='age']").val(director.age);
				$('#empform_' + director.id).find("input[name='count']").val(director.count);
				
				alert("updated");
		
			},
			
			// ERROR response 
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
})