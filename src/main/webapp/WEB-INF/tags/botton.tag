<%@ attribute name="ondeEstou" required="true" %>
<%@ attribute name="listPr" required="false"%>
<script src="assets/js/jquery-1.10.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/pr.js"></script>
<script src="assets/js/instaretrato.js"></script>
<script src="assets/js/jquery-ui.js"></script>
<script>
	var ondeEstou = "${ondeEstou}";
	var listPr = "${listPr}";
	
	if (ondeEstou == "HOME"){
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
		
		$(".pr-list").load("listarPr?listPr=" + listPr,function( data ) {
	  		$(".pr-list").html( data );
		});
	}else{
		$(".btn-pr").click(function (e){
			if ( $(this).hasClass('btn-pr-cam')){
				openHome("NORMAL");
			}else if ( $(this).hasClass('btn-pr-insta')){
				openHome("INSTAGRAM");				
			}else if ( $(this).hasClass('btn-pr-face')){
				openHome("FACEBOOK");				
			}
			
		});	
		
	}
	
	function listPortaRetrato(pr){
		$(".pr-list").load("listarPr?listPr=" + pr,function( data ) {
	  		$(".pr-list").html( data );
		});
	}
	
	function openHome(pr){
		//window.location.href = '/${requestScope.contextPath}/?listPr=' + pr;
		window.location.href = '${pageContext.request.contextPath}/?listPr=' + pr;
	}
	
</script> 