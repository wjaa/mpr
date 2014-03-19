var appSecret = '30c79d8a6cff5337959aced9bbd9b44d';
var appId = 192790747577104;
var limit = 18;

  window.fbAsyncInit = function() {
      FB.init({
        appId      : appId,
        status     : true, // check login status
        cookie     : true, // enable cookies to allow the server to access the session
        xfbml      : true  // parse XFBML
      });
      // Here we subscribe to the auth.authResponseChange JavaScript event. This event is fired
      // for any authentication related change, such as login, logout or session refresh. This means that
      // whenever someone who was previously logged out tries to log in again, the correct case below 
      // will be handled. 

      FB.Event.subscribe('auth.authResponseChange', function(response) {
        // Here we specify what we do with the response anytime this event occurs. 
        if (response.status === 'connected') {
          message('connected');
          $('#logarFace').hide();
          $('#sairFace').show();
          
          var o = FB.getAuthResponse();
          message(o.accessToken);
          
          faceretrato.listarFotos('/me/photos?'+o.accessToken + "&limit=" + limit,0);

        } else if (response.status === 'not_authorized') {
          message('Erro na autenticacao');
          faceretrato.login();
        } else {
          $('#logarFace').show();
          $('#sairFace').hide();
          $('#facebook').html("");
          message('Logout');
        }
      });
  };

  // Load the SDK asynchronously
  (function(d){
   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
   if (d.getElementById(id)) {return;}
   js = d.createElement('script'); js.id = id; js.async = true;
   js.src = "http://connect.facebook.net/en_US/all.js";
   ref.parentNode.insertBefore(js, ref);
  }(document));

  

var faceretrato = function() {
  return {
      init:function(){
    	  $('#sairFace').hide();
          $('#logarFace').click(function(){
              faceretrato.login();
          });
          $('#sairFace').click(function(){
             
              faceretrato.logout(function(response) { // user is now logged out });
              });
          });
      },
      login:function(){
          //ver o status do login
          faceretrato.accessToken();
          FB.login(function(response) {
        	  message(response);
          	}, 
              {scope: 'user_photos'}
          );
      },
      logout:function(){
          //ver o status do login
          FB.logout(function(response){});
      },
      listarFotos:function(url, page){
          
          FB.api(url, function(response) {
        	  
        	  btnAnterior
        	  $("#facebook").html("");
              var count = 0;
              for (var i = 0; i < response.data.length; i ++ ){
                  var urlEnc = encodeURIComponent(response.data[i].images[2].source);
                  var url = response.data[i].picture;
                  var urlHi = response.data[i].source;
                  
                  var altura = response.data[i].height;
                  var largura = response.data[i].width;
                  
                  var ehQuadrada =  ((altura / largura) == 1);   
                  
                  //paging.next
                  //paging.previous
                  count++;
                  appendHtmlLinkPopup(ehQuadrada, count, urlEnc, url, urlHi)
          		    
                  
                  //se for a primeira pagina entao pode ir pra frente e nao pra traz
                  if (page == 0){
                	  $("#btnProximo").attr('disabled', false); 
                	  $("#btnAnterior").attr('disabled', true);
                		  
                  }else{
                	  //se uma pagina diferente de zero entao pode ir pra traz
                	  $("#btnAnterior").attr('disabled', false);
                  }
                  
                  //se o resultado dessa pagina for menor que 12 é porque nao tem proxima pagina.
                  //TODO isso aqui pode dar problema se a ultima pagina tiver 12. Então é melhor chamar o nextpage pra ver 
                  //quantos elementos tem na proxima pagina
                  if (response.data.length < 12){
                	  $("#btnProximo").attr('disabled', true);
                  }else{
                	  $("#btnProximo").attr('disabled', false);
                  }
                  
                  $("#btnAnterior").unbind("click");
				  $("#btnAnterior").click(function(){
					  faceretrato.listarFotos(response.paging.previous, page - 1);
				  });	
				  
				  $("#btnProximo").unbind("click");
				  $("#btnProximo").click(function(){
					  faceretrato.listarFotos(response.paging.next, page + 1);
				  });
				  
				  
              }
          });
      },
      preview:function(urlImg, urlHi){
          $('#previewModal').modal('show') ;
          $('#previewImg').attr("src",decodeURIComponent(urlImg));
          var urlDownload = decodeURIComponent(urlHi);
			$("#urlParam").val(urlDownload);
			$('#btnSelecionar').click(function(){
				aguardeShow("Listando porta retratos para sua foto...");
				document.forms[0].submit();
			});
      },
      notPreview:function(urlImg){
          $('#notPreviewModal').modal('show') ;
      },
      accessToken:function(){
        $.ajax({
            url: 'https://graph.facebook.com/oauth/access_token?client_id=' + appId + 
                  '&client_secret=' + appSecret + '&grant_type=client_credentials',
            success: function(data){
              return data;
            },
            error:function(data){
                message(data);
            }
            
        });
  
      }
  }

}();

function appendHtmlLinkPopup(ehQuadrada, count, urlEnc, url, urlHi){
	var html = "";
	var idLink = "linkPreview" + count;
	var clazz = "btn-primary thumbnail";
	
	if (ehQuadrada){
		idLink = "linkNotPreview" + count;
		clazz = "btn-danger thumbnail thumbnailError";
	}
	
	html = html + "<div class='col-sm-3 col-md-2'>";
  	html = html + "<a id='" + idLink + "' data-toggle='" + urlEnc + "'" +
  		  " class='" + clazz + "' href='#'>";
  	html = html + "<img src='" + url +"' style='height:130px;'/>";
  	html = html + "</a>";
  	html = html + "</div>";
    
    $("#facebook").append(html); 
    
    if (!ehQuadrada){
    	var id = "#linkPreview" + count;
    	$(id).click(function(){
    		faceretrato.preview($(this).attr('data-toggle'), urlHi);
    	});
    }else{
    	var id = "#linkNotPreview" + count;
    	$(id).click(function(){
    		faceretrato.notPreview();
    	});
    }
    
    
}


function message(msg){
	/*$('.alert').show();
	$('.alert').append("<br>" + msg);
	setTimeout(function(){
	     	$('.alert').hide();
	     }, 5000
  );*/
}


$(document).ready(function(){
  faceretrato.init();
});
