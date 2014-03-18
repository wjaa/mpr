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
	
   <!-- Google Analitics -->
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-47958941-1', 'meuportaretrato.com');
  ga('send', 'pageview');
  <!-- End Google Analitics -->
	
</script> 