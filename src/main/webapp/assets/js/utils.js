function get(url, params){
	$.ajax({
		dataType: "json",
		url: url,
		data: params,
		success: function(json){
			return jQuery.parseJSON(json);
		}
		error: function(){
			return "Error";
		}
	});
}