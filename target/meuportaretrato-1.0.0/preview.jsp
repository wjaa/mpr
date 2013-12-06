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

<div class="container">
   	 <wjaa:top/>
   	 <ol class="breadcrumb">
	
		<li ><a href="/upload"><span class="label label-success">1° Escolha o Porta retrato</span> </a></li>
		<li ><a href="#" onclick="openPageUpload()"><span class="label label-success">2° Envie Sua foto</span></a></li>
		<li><span class="label label-success">3° Veja como ficou</span></li>
	 </ol>


      	<!-- Jumbotron -->
    	<div class="jumbotron">
        <h1>Resultado final, o que achou?</h1>
        <p class="lead">Seu porta retrato ficará desse jeito.</p>
        </div>
        <div class="center">
			<div id="base" >
				<img alt="preview" src="assets/img/portaretrato.gif" width="300px" height="378px"/>
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