var accessToken = '41396575.1fb234f.a049882ede31420684b8f156e6f96431';
var userId = 41396575;
var clientId = '38c352213ce0437fb5141578ea84bbda';
var limit = 18; //Limite máximo de fotos
var setSize = "small";
var urlsPaginacao = new Array("","","","","","","","");

var instagram = function() {
	return {
		init: function(param) {
			$("#instagram").html("&nbsp;&nbsp;&nbsp;iniciando busca....");
			
			if (param.indexOf("#") > -1){
				instagram.loadImagesByHash(param.replace("#",""));
			}else{
				instagram.getUser(param);
			}
		},
		getUser: function(username) {
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
		loadImagesByHash: function(hash){
			var getImagesURL =  "https://api.instagram.com/v1/tags/" + hash + "/media/recent?access_token=" + 
			accessToken +'&count='  + limit;
			urlsPaginacao[0] = getImagesURL;
			instagram.getNextPage(getImagesURL,0);
		},
		loadImages: function(userID) {
			var getImagesURL = 'https://api.instagram.com/v1/users/'+ userID +'/media/recent/?access_token='+ 
			accessToken +'&count='  + limit;
			urlsPaginacao[0] = getImagesURL;
			instagram.getNextPage(getImagesURL,0);
		},
		preview: function(urlImg,urlHi) {
			$('#previewModal').modal('show') ;
			$('#previewImg').attr("src",decodeURIComponent(urlImg));
			var urlDownload = decodeURIComponent(urlHi);
			$("#urlParam").val(urlDownload);
			$('#btnSelecionar').click(function(){
				$("#aguardeModal").modal('show');
				document.forms[0].submit();
			});
			
		},
		getNextPage: function(getImagesURL, pagina){
			$.ajax({
				type: "GET",
				dataType: "jsonp",
				cache: false,
				url: getImagesURL,
				success: function(data) {
					$("#instagram").html("");
					$("#btnProximo").attr('disabled', false);
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
						var urlHi = encodeURIComponent(data.data[i].images.standard_resolution.url);

			            count++;
						$("#instagram").append("<div class='col-sm-3 col-md-2'><a id='linkPreview" + count + "' data-toggle='" + url + "'" +
								"' data-toggle2='" + urlHi + "' class='btn btn-primary btn-lg thumbnail carregando' href='#'>" + 
							" <img src='" + size +"'/></a></div>");
						var id = "#linkPreview" + count;
						$(id).click(function(){
							instagram.preview($(this).attr('data-toggle'),$(this).attr('data-toggle2') );
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

