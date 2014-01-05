var appSecret = '30c79d8a6cff5337959aced9bbd9b44d';
var appId = 192790747577104;

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
          
          faceretrato.listarFotos('/me/photos?'+o.accessToken);

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
      listarFotos:function(url){
          
          FB.api(url, function(response) {
        	  $("#facebook").html("");
              var count = 0;
              for (var i = 0; i < response.data.length; i ++ ){
                  var urlEnc = encodeURIComponent(response.data[i].images[0].source);
                  var url = response.data[i].picture;
                  
                  var altura = response.data[i].height;
                  var largura = response.data[i].width;
                  
                  var ehQuadrada =  ((altura / largura) == 1);   
                  
                  var html = "";
                  //paging.next
                  //paging.previous
                  count++;
                  html = html + "<div class='col-sm-3 col-md-2'>";
                  if (!ehQuadrada){
                	  html = html + "<a id='linkPreview" + count + "' data-toggle='" + urlEnc + "'" +
                		  " class='btn btn-primary btn-lg thumbnail' href='#'>"; 
                  }else{
                	  html = html + "<span class='btn btn-primary btn-lg thumbnail'>"; 
                  }
                  html = html + "<img src='" + url +"' style='height:130px;'/>";
                  if (!ehQuadrada){
                	  html = html + "</a>";
                  }else{
                	  html = html + "</span>";
                  }
                  html = html + "</div>";
          		  $("#facebook").append(html); 
                  var id = "#linkPreview" + count;
                  $(id).click(function(){
                      faceretrato.preview($(this).attr('data-toggle'));
                  });  
                  
                  
                  $("#btnAnterior").unbind("click");
				  $("#btnAnterior").click(function(){
					  faceretrato.listarFotos(response.paging.previous);
				  });	
				  
				  $("#btnProximo").unbind("click");
				  $("#btnProximo").click(function(){
					  faceretrato.listarFotos(response.paging.next);
				  });
              }
          });
      },
      preview:function(urlImg){
          $('#previewModal').modal('show') ;
          $('#previewImg').attr("src",decodeURIComponent(urlImg));
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

function message(msg){
	$('.alert').show();
	$('.alert').append("<br>" + msg);
	setTimeout(function(){
	     	$('.alert').hide();
	     }, 5000
  );
}


$(document).ready(function(){
  faceretrato.init();
});