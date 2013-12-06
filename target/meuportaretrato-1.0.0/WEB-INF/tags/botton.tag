<%@ attribute name="ondeEstou" required="true" %>
<%@ attribute name="listPr" required="false"%>
<script src="assets/js/jquery-1.10.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/pr.js"></script>
<script>
	var ondeEstou = "${ondeEstou}";
	var listPr = "${listPr}";
	
	if(ondeEstou == "INDEX"){
		$(".btn-pr").click(function (e){
			if ( $(this).hasClass('btn-pr-cam')){
				openHome("NORMAL");
			}else if ( $(this).hasClass('btn-pr-insta')){
				openHome("INSTAGRAM");				
			}else if ( $(this).hasClass('btn-pr-face')){
				openHome("FACEBOOK");				
			}
			
		});	
		
	}else if (ondeEstou == "HOME"){
		$(".btn-pr").click(function (e){
			$(".dropdown-menu li").removeClass("active");
			if ( $(this).hasClass('btn-pr-cam')){
				$(".btn-pr-cam").addClass("active");
		  		listPortaRetrato('NORMAL');
			}else if ( $(this).hasClass('btn-pr-insta')){
				$(".btn-pr-insta").addClass("active");
		  		listPortaRetrato('INSTAGRAM');
			}else if ( $(this).hasClass('btn-pr-face')){
				$(".btn-pr-face").addClass("active");
				listPortaRetrato("FACEBOOK");
				
			}
			
		});	
		
		$(".pr-list").load("portaretrato?listPr=" + listPr,function( data ) {
	  		$(".pr-list").html( data );
	  		addLinkPortaRetrato(listPr);
		});
	}
	
	function listPortaRetrato(pr){
		$(".pr-list").load("portaretrato?listPr=" + pr,function( data ) {
	  		$(".pr-list").html( data );
	  		addLinkPortaRetrato(pr);
		});
	}
	function addLinkPortaRetrato(pr){
		$(".btn-primary").click(function (e){
			
			var prCode = $(this).attr('rel');
			
			$.post( "portaretrato",
					{ prCode: prCode, listPr: pr},
					function( data ) {
				  		$("html").html( data );
					},
					"html"
			);
		});
	}
	
	function openHome(pr){
		window.location.href = 'home?listPr=' + pr;
	}

	
	/* (".navbar-nav img").click(function (e){
		window.location.href = "/upload";
	}); */
</script> 