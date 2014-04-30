function aguardeShow(texto){
	$("#aguardeTxtSmall").html(texto);
	$("#aguardeModal").modal('show');
}

function aguardeHide(){
	$("#aguardeModal").modal('hide');
}


function submitForm(url,params, method, func){
	$.ajax({
		  type: method,
		  url: url,
		  data: params
		}).done(function( msg ) {
		    func.call();
		  });
}

function isEmail(email){
    var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
    var check=/@[\w\-]+\./;
    var checkend=/\.[a-zA-Z]{2,3}$/;
    if(((email.search(exclude) != -1)||(email.search(check)) == -1)||(email.search(checkend) == -1)){return false;}
    else {return true;}
}

