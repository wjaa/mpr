


var accessToken = '41396575.1fb234f.a049882ede31420684b8f156e6f96431';
var userId = 41396575;
var clientId = '38c352213ce0437fb5141578ea84bbda';
var limit = 32; //Limite máximo de fotos
var setSize = "small";
var username = 'feehpinazo';


var instagram = function() {
	return {
		init: function() {
			instagram.getUser();
		},
		getUser: function() {
			var getUserURL = 'https://api.instagram.com/v1/users/search?q='+ username +'&access_token='+ 
			accessToken +'';
			$.ajax({
				type: "GET",
				dataType: "jsonp",
				cache: false,
				url: getUserURL,
				success: function(data) {
					var getUserID = data.data[0].id;
					instagram.loadImages(getUserID);
				}	
			});
		},
		loadImages: function(userID) {
			var getImagesURL = 'https://api.instagram.com/v1/users/'+ userID +'/media/recent/?access_token='+ 
			accessToken +'';
			$.ajax({
				type: "GET",
				dataType: "jsonp",
				cache: false,
				url: getImagesURL,
				success: function(data) {
					for(var i = 0; i < limit; i+=1) {
						if(setSize == "small") {
							var size = data.data[i].images.thumbnail.url;
						} else if(setSize == "medium") {
							var size = data.data[i].images.low_resolution.url;
						} else {
							var size = data.data[i].images.standard_resolution.url;	
						}

    					$("#instagram").append("<div class='col-sm-6 col-md-2'><a class='thumbnail' target='_blank' href='" + 
							data.data[i].link +"'><img src='" + size +"' /></a></div>");
					}
				}
			});
		}
	}
}();

$(document).ready(function() {
    instagram.init();
});