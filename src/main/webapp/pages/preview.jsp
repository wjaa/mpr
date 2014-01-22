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
       <div class="center">
		<div id="base" >
			<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="300px" height="378px"/>
		</div>
	    <br/>
	    <span class="btn btn-danger">
	        <i class="glyphicon glyphicon-plus"></i>
        <span>Voltar.</span>
	    </span>
		    
		    <!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
			<form target="pagseguro" action="https://pagseguro.uol.com.br/checkout/v2/cart.html?action=add" method="post">
			<!-- NÃO EDITE OS COMANDOS DAS LINHAS ABAIXO -->
			<input type="hidden" name="itemCode" value="EB2F9BD53232F24EE42F3FA8AF1F7CFB" />
			<input type="image" src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/209x48-comprar-assina.gif" name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" />
			</form>
			<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
		    <br>
		</div>
</div>

 
</body>
<wjaa:botton ondeEstou="PREVIEW"/>
<script>
$(".btn-danger").on('click', function () {
	window.location.href = '/upload';	
});	

</script>
</html>