<script src="assets/js/jquery-1.10.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/pr.js"></script>
<script src="assets/js/jquery-ui.js"></script>
<script>
	
	$(".btn-pr").click(function (e){
		if ( $(this).hasClass('btn-pr-cam')){
			openHome("NORMAL");
		}else if ( $(this).hasClass('btn-pr-insta')){
			openHome("INSTAGRAM");				
		}else if ( $(this).hasClass('btn-pr-face')){
			openHome("FACEBOOK");				
		}else if ( $(this).hasClass('btn-pr-flic')){
			openHome("FLICKR");				
		}else if ( $(this).hasClass('btn-pr-goog')){
			openHome("GOOGLE");				
		}
		
	});	
		
	
	function openHome(pr){
		window.location.href = '${pageContext.request.contextPath}/escolherImagem?listPr=' + pr;
	}
	
</script> 