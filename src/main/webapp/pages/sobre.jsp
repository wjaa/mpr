<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header description="Entre em nosso site e monte seu porta retrato para instagram."
title="MeuPortaRetrato.com - Temos os melhores porta retratos para instagram."/>
<body>
<wjaa:menu/>
<div class="container">
   	 <wjaa:logo/>
		<div class="jumbotron">
			<h3><span class="label label-primary">Sobre</span></h3>
			    
		    <div class="panel panel-default" style="margin-top: 30px;">
 				<div class="panel-body">
		    <span style="color:#666666; font-weight: normal; font-size: 14px;">
		    
			    <p>&nbsp;&nbsp;&nbsp;&nbsp;Nós da equipe <b>Meuportaretrato.com</b> temos os melhores produtos para garantir a satisfação de nossos clientes. Somos uma loja online, que transforma suas fotos do Instagram ou de seu computador em lindos porta retratos ou quadros.</p> 
 <p>&nbsp;&nbsp;&nbsp;&nbsp;Trabalhamos com os melhores fornecedores, altamente especializados em porta retratos e quadros, garantindo a qualidade dos materiais confeccionados.</p>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;Nosso pensamento é voltado para a inovação e moda, com isso, estaremos sempre apresentando novos modelos e tamanhos compatíveis a decoração de sua casa ou escritório. </p>

<h3>Instruções para compra: </h3>

<p>&nbsp;&nbsp;&nbsp;&nbsp;<b>1º) </b>Procure fotos do seu usuário ou de seus amigos e escolha a melhor imagem para um lindo porta retrato, ou faça um upload de uma foto do seu computador. </p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;<b>2º) </b>Escolha o Porta retrato que melhor se encaixe com a sua foto. </p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;<b>3º)</b> Veja como ficou. Daremos ao nossos clientes a oportunidade de visualizar a foto escolhida dentro do porta retrato selecionado. (A visualização que aparecerá em nosso site, é apenas ilustrativa. Garantimos a qualidade de nossos produtos.)</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;<b>4º)</b> Finalize o pedido e efetue o pagamento com toda a segurança do pagseguro.</p>

<h3>Informação importante:</h3>
<p>&nbsp;&nbsp;&nbsp;&nbsp;Todas as fotos enviadas serão avaliadas pela nossa equipe, caso não estejam com a qualidade adequada para a confecção do porta retrato, entraremos em contato para a troca da imagem ou devolveremos o dinheiro. </p>

<h3>Deixe sua opinião:</h3>
<p>&nbsp;&nbsp;&nbsp;&nbsp; Precisamos da sua opinião para melhorarmos cada vez mais. Acesse nossa fanpage e deixe seu comentário. 
<p>&nbsp;&nbsp;&nbsp;&nbsp; <a href="http://facebook.com/meuportaretrato" target="_blank"><img src="assets/img/facebook-icon.gif" width="25px" height="25px"/>&nbsp;Facebook: facebook.com/meuportaretrato</a><br></p>

		 	</span>       
		    </div>
		    </div>
			    
			
		</div>
</div>
</body>
<wjaa:rodape/>
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
</html>	