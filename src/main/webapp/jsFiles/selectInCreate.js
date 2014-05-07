// JavaScript Document

$(document).ready(function() {
	$('#bookFormContainer').show();
	$('#musicFormContainer').hide();
	$('#movieFormContainer').hide();

	$("#selectType").change(function() {
		var selectedValue = $(this).find("option:selected").text();
		
		switch(selectedValue) {
		case "Books":
			$('#bookFormContainer').show();
			$('#musicFormContainer').hide();
			$('#movieFormContainer').hide();
			break;
		case "Musics":
			$('#bookFormContainer').hide();
			$('#musicFormContainer').show();
			$('#movieFormContainer').hide();
			break;
		case "Movies":
			$('#bookFormContainer').hide();
			$('#musicFormContainer').hide();
			$('#movieFormContainer').show();
			break;
		}
	});
});