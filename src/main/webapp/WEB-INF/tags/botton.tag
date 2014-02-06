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
	
		$(".btn-pr").click(function (e){
			if ( $(this).hasClass('btn-pr-cam')){
				openHome("NORMAL");
			}else if ( $(this).hasClass('btn-pr-insta')){
				openHome("INSTAGRAM");				
			}else if ( $(this).hasClass('btn-pr-face')){
				openHome("FACEBOOK");				
			}
			
		});	
		
	
	function openHome(pr){
		//window.location.href = '/${requestScope.contextPath}/?listPr=' + pr;
		window.location.href = '${pageContext.request.contextPath}/?listPr=' + pr;
	}
	
</script> 