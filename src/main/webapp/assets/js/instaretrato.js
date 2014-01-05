var accessToken = '41396575.1fb234f.a049882ede31420684b8f156e6f96431';
var userId = 41396575;
var clientId = '38c352213ce0437fb5141578ea84bbda';
var limit = 12; //Limite máximo de fotos
var setSize = "small";
var username = '';
var urlsPaginacao = new Array("","","","","","","","");

var instagram = function() {
	return {
		init: function(uname) {
			$("#instagram").html("iniciando busca....");
			username = uname;
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
				},
				error: function(data) {
					$("#instagram").html("Usuário inválido");
				}		
			});
		},
		loadImages: function(userID) {
			
			var getImagesURL = 'https://api.instagram.com/v1/users/'+ userID +'/media/recent/?access_token='+ 
			accessToken +'&next_max_id='  + limit;
			urlsPaginacao[0] = getImagesURL;
			instagram.getNextPage(getImagesURL,0);
		},
		preview: function(urlImg) {
			$('#previewModal').modal('show') ;
			$('#previewImg').attr("src",decodeURIComponent(urlImg));
			
		},
		getNextPage: function(getImagesURL, pagina){
			$.ajax({
				type: "GET",
				dataType: "jsonp",
				cache: false,
				url: getImagesURL,
				success: function(data) {
					$("#instagram").html("");

					if (pagina == 0){
						$("#btnAnterior").attr('disabled', true);
					}else{
						$("#btnAnterior").attr('disabled', false);
						$("#btnAnterior").unbind("click");
						$("#btnAnterior").click(function(){
							instagram.getNextPage(urlsPaginacao[pagina], pagina-1);
						});	
					}

					
					var next_url = data.pagination.next_url;
					$("#btnProximo").unbind("click");
					$("#btnProximo").click(function(){
						urlsPaginacao[pagina+1] = getImagesURL;
						instagram.getNextPage(next_url, pagina+1);
					});	
					var count = 0;
					for(var i = 0; i < data.data.length; i+=1) {
						if(setSize == "small") {
							var size = data.data[i].images.thumbnail.url;
						} else if(setSize == "medium") {
							var size = data.data[i].images.low_resolution.url;
						} else {
							var size = data.data[i].images.standard_resolution.url;	
						}
						var url = encodeURIComponent(data.data[i].images.low_resolution.url);
			            count++;
						$("#instagram").append("<div class='col-sm-3 col-md-2'><a id='linkPreview" + count + "' data-toggle='" + url + "'" +
							" class='btn btn-primary btn-lg thumbnail carregando' href='#'>" + 
							" <img src='" + size +"'/></a></div>");
						var id = "#linkPreview" + count;
						$(id).click(function(){
							instagram.preview($(this).attr('data-toggle'));
						});

					}
				},
				error: function(data) {
					$("#instagram").html("Erro ao carregar imagens");
				}
			});
		}
	}
}();

