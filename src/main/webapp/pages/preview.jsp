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
    <c:choose>
       <c:when test="${sessionScope.carrinho.portaRetrato.tipo == 'I'}">
            background-position:115px 40px;
       		background-size: 220px 220px;
			width: 300px;
       </c:when>
       <c:otherwise>
       background-position:110px 40px;
			background-size: 225px 310px;
			width: 300px;
			/*transform:rotate(7deg);*/
			/*-ms-transform:rotate(7deg); /* IE 9 */
			/*-webkit-transform:rotate(7deg); /* Opera, Chrome, and Safari */
       </c:otherwise>
    </c:choose>
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
       	<h4><span class="label label-primary">3° passo.</span>&nbsp;&nbsp;Veja como ficou.<br><br>
       	<small>A imagem abaixo é apenas uma previa de como ficará o seu porta retrato. A qualidade do produto final é superior a imagem ilustrativa.</small></h4>
 	 </div> 
       
					<div id="base" style="float: left; width: 450px; margin-top: 10px">
						<c:choose>
					       <c:when test="${sessionScope.carrinho.portaRetrato.tipo == 'I'}">
					       		<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="300px" height="300px"/>
					       </c:when>
					       <c:otherwise>
								<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="300px" height="378px"/>
					       </c:otherwise>
					    </c:choose>
					    <div class="caption">
			      			<h4>${sessionScope.carrinho.portaRetrato.nome}</h4>
			     			<h3><span class="label label-success"><span style="font-size: small;">R$&nbsp;</span>${sessionScope.carrinho.portaRetrato.precoStr}</span></h3>
			    		</div>
						
					</div>
			
					<div class="col-sm-6 col-md-6" style="margin-top: 5px">
							<ul class="list-group">
							<li class="list-group-item active">
							   Informações importantes:
							</li>
								  <li class="list-group-item">
				   				<span class="badge">1</span>
				   				Levando em consideração a entrega de nossos fornecedores,  tempo de confecção e tratamento de imagem e impressão,  a entrega pode demorar até 10 dias úteis.  
				   				</li>
				   				
				   				
				   				  <li class="list-group-item">
				   				  <span class="badge">2</span>
				   				Trabalhamos com entrega via correio, com isso será cobrado a taxa de postagem, após o envio do produto geraremos um código de rastreio e enviaremos para seu email.
				   				</li>
				   				
				   				  <li class="list-group-item">
				   				  <span class="badge">3</span>
				   				Não criamos vínculos com nossos cliente, seus dados são armazenados apenas no pagseguro onde temos total segurança e confiabilidade em seus serviços.
				   				
				   				</li>
				   				  <li class="list-group-item">
				   				  <span class="badge">4</span>
				   				Após a confirmação do pagamento, você receberá um email com o código do pedido, onde  poderá visualizar o status de seu pedido em nosso site.

				   				</li>
				   			</ul>	
		    		
					</div>
					<div class="col-sm-3">
		    			<form action="pagar" method="post">	
						   	<button id="btnFinalizar" type="submit" class="btn btn-primary"><h2>Finalizar Pedido</h2></button>
					    </form>
		    		</div>
</div>

 <wjaa:rodape/>
</body>
<wjaa:botton/>
<script>
$("#btnFinalizar").on('click', function () {
	window.location.href = 'pagar';	
});	

</script>
</html>