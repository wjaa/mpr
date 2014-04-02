<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header/>
<body>
<wjaa:menu/>
<div class="container">
   	 <wjaa:logo/>
      <!-- Jumbotron -->
      <div class="jumbotron page-header" style="margin: 0px;">
      		<div>
	      		<h4><span class="label label-danger">${requestScope.error}</span></h4>
	      		<div>
		        	<h4><span class="label label-primary">4° passo.</span>&nbsp;&nbsp;Pagamento no pagseguro <br><br>
		        	<small>Seu pedido foi criado com sucesso: <span style="font-size: 20px;"><b>#${sessionScope.carrinho.pedido.id}</b>.</span><br> Clique no botão abaixo para realizar o pagamento no pagseguro e finalizar sua compra.</small>

		        	</h4>
				</div>
		    </div>
	  </div>	
	  <div class="panel panel-primary" style="margin-top: 10px;">
		  <!-- Default panel contents -->
		  <div class="panel-heading">Detalhes do pedido</div>
		  <!-- Table -->
		  <table class="table">
		  	<thead>
			 <tr>
		  	  <th>Número do Pedido</th>
		  	  <th>Descrição</th>
		  	  <th>Valor</th>
		  	</tr>
		  	</thead>
		  	<tbody>
		  		<tr>
		  			<td>#${sessionScope.carrinho.pedido.id}</td>
		  			<td>${sessionScope.carrinho.portaRetrato.nome} + Impressão de foto</td>
		  			<td>R$ ${sessionScope.carrinho.portaRetrato.precoStr}</td>
		  		</tr>
		  		<tr>
		  			<td colspan="2" align="right"><h4><span >Total: </span></h4></td>
		  			<td><h4><span class="label label-success">R$ ${sessionScope.carrinho.portaRetrato.precoStr} + Frete</span></h4></td>
		  		</tr>
		  	</tbody>
		  	
		  </table>
		</div>
    
	 <div style="margin-top: 30px; margin-bottom: 90px; text-align: center;">
        	<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
		<form  action="${requestScope.redirect}" method="post" target="_blank">
			<input class="btn btn-default btn-lg" type="image" src="assets/img/pagseguro.png" width="250px;" name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" value="Clique aqui para pagar com:" style="border: 3px solid transparent; border-color: #333333"></input>
		</form>
		<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
   
     		</div>    
	 		
       
</div>
<wjaa:rodape/>       
<wjaa:botton/>
</body>
</html>
