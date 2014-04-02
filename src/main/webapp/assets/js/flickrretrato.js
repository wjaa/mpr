var key = '74154473b7640f9bfaf09a33f3a3da69';
var secret = '98d90ec0fac60b84';
var perPage = 18;
var format = 'json';
var countImages = 0;

var flickrretrato = function() {
  return {
    init: function() {
    	$("#btnAnterior").attr('disabled', true);
    	$("#btnProximo").attr('disabled', true);
      $("#inBusca").keypress(function(e) {
    	  if(e.which == 13) {
    		  flickrretrato.initFind();
    	   }
      });	
    	
      $("#btnBuscar").click(function(){
          flickrretrato.initFind();
      });
      
    },
    initFind: function(){
    	var arg = $("#inBusca").val();
        //se nao tiver hashtag é um usuário
        if (arg.indexOf("#") == -1){
            flickrretrato.getUser(arg);
        }else{
        	flickrretrato.find(arg.replace("#",""),"",1);
        }

    },
    getUser: function(username){
      var idUser = "";
      var findUrl = 'http://api.flickr.com/services/rest/?&method=flickr.people.findByUsername&api_key=' + key +'&username=' + username +'&format=json';
      $.ajax({
        type: "GET",
        dataType: "jsonp",
        jsonp: 'jsoncallback',
        cache: false,
        url: findUrl,
        success: function(data) {
        	if (data.message){
                $("#flickr").html(data.message);
                return;
             }
        	
            var idUser = data.user.id;
            
            flickrretrato.find("",idUser,1);
        },
        error: function(data) {
           $("#flickr").html("Usuário não encontrado!");
        }
      });
      return idUser;
    },
    find: function(tags, idUser, page) {
      $("#flickr").html("buscando...");
      countImages = 0;

      var findUrl = "";
      if (idUser){
          findUrl = 'http://api.flickr.com/services/rest/?&method=flickr.people.getPublicPhotos&api_key=' + key +
        '&user_id=' + idUser + '&format=' + format + '&per_page=' + perPage + '&page=' + page;
      }else{
          findUrl = 'http://api.flickr.com/services/rest/?&method=flickr.photos.search&api_key=' + key +
        '&tags=' + tags + '&format=' + format + '&per_page=' + perPage + '&page=' + page;
      }
      
      $.ajax({
        type: "GET",
        dataType: "jsonp",
        jsonp: 'jsoncallback',
        cache: false,
        url: findUrl,
        success: function(data) {
          if (data.message){
             $("#flickr").html(data.message);
             return;
          }
          var maxPages = data.photos.pages;

          if (page == 0){
            $("#btnAnterior").attr('disabled', true);
          }else{
            $("#btnAnterior").attr('disabled', false);
            $("#btnAnterior").unbind("click");
            $("#btnAnterior").click(function(){
                flickrretrato.find(tags,idUser,page-1);
            }); 
          }
          
          $("#btnProximo").unbind("click");
          $("#btnProximo").click(function(){
              flickrretrato.find(tags,idUser,page+1);
          });   
          $("#flickr").html("");
          var count = 0;
          for(var i = 0; i < data.photos.photo.length; i+=1) {
            flickrretrato.loadImages(data.photos.photo[i]);
          }

                    
        },
        error: function(data) {
          $("#flickr").html("Nenhuma imagem encontrada!");
        }   
      });
    },
    loadImages: function(image){
      var getImageUrl = 'http://api.flickr.com/services/rest/?&method=flickr.photos.getSizes&api_key=74154473b7640f9bfaf09a33f3a3da69&format=json&photo_id=' + image.id;

      $.ajax({
        type: "GET",
        dataType: "jsonp",
        jsonp: 'jsoncallback',
        cache: false,
        url: getImageUrl,
        success: function(data) {
          var urlPreview = "";
          var urlThumb = "";
          var urlHi = "";

          for(var i = 0; i < data.sizes.size.length; i+=1) {
             if (data.sizes.size[i].label == 'Small'){
                urlThumb = data.sizes.size[i].source;
             }
             if (data.sizes.size[i].label == 'Medium 640' || data.sizes.size[i].label == 'Medium 800' || data.sizes.size[i].label == 'Medium'){
                urlPreview = data.sizes.size[i].source;
             }
             if ( (data.sizes.size[i].label == 'Original' && data.sizes.size[i].height < 2000 )  || data.sizes.size[i].label == 'Large'){
                urlHi = data.sizes.size[i].source;
             }
          } 
          
          if (urlHi == ""){
        	  urlHi = urlPreview;
          }
          var url = encodeURIComponent(urlPreview);
          $("#flickr").append("<div class='col-sm-3 col-md-2'><a id='linkPreview" + countImages + "' data-toggle='" + url + "'" +
           " class='btn btn-primary btn-lg thumbnail' href='#'>" + 
           " <img src='" + urlThumb +"' style='height:160px'/></a></div>");
           var id = "#linkPreview" + countImages;
           $(id).click(function(){
              flickrretrato.preview($(this).attr('data-toggle'), urlHi);
           });
           countImages++;
        },
        error: function(data) {
          $("#flickr").html("Nenhuma imagem encontrada!");
        }   
      });
       
    },
    preview: function(urlImg, urlHi) {
      
      $('#previewModal').modal('show') ;
      $('#previewImg').attr("src",decodeURIComponent(urlImg));
      var urlDownload = decodeURIComponent(urlHi);
		$("#urlParam").val(urlDownload);
		$('#btnSelecionar').click(function(){
			aguardeShow("Listando porta retratos para sua foto...");
			document.forms[0].submit();
		});
      
    },

  }
}();   

$(document).ready(function(){
  flickrretrato.init();
}); 
