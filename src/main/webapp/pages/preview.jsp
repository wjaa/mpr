<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header/>
<head>
	<style>
		div#base {
			background-image: url("${sessionScope.carrinho.imgUrl}");
			background-repeat: no-repeat;
			background-size: 300px 378px;
			width: 300px;
			height: 378px;
			vertical-align: middle; 
			text-align: center;
		}
	</style>
</head>
<body>
<wjaa:menu/>
<div class="container">
 	<wjaa:logo/>
   	<div class="jumbotron page-header" style="margin: 0px;">
       	<h4><span class="label label-primary">3° passo.</span>&nbsp;&nbsp;Seu porta retrato ficará assim.<br><br>
       	<small>Gostou? clique no link do pagseguro para comprar o porta retrato.</small></h4>
 	 </div> 
       
		<div id="base" style="float: left;">
			<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="300px" height="378px"/>
		</div>
		<div style="margin: 150px;">
			<form action="pagar" method="post">
		    	<div class="col-sm-3">
		    	     Texto aqui explicando quanto tempo o pedido demorará para chegar, explicando sobre o pagseguro e tudo mais.
		    	     bla bla bla bla bla bla
				   	<button id="btnFinalizar" type="submit" class="btn btn-success">Finalizar Pedido</button>
				</div>	
		    </form>
	    </div>
</div>

 
</body>
<wjaa:botton ondeEstou="PREVIEW"/>
<script>
$("#btnFinalizar").on('click', function () {
	window.location.href = 'pagar';	
});	

</script>
</html>