function openPageUpload(){
	$.post( "portaretrato",
			function( data ) {
		  		$("html").html( data );
			},
			"html"
	);
}
