<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
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
			 <tr bgcolor="#EEEEEE" >
		  	  <th>Número do Pedido</th>
		  	  <th >Descrição</th>
		  	  <th>Valor</th>
		  	</tr>
		  	</thead>
		  	<tbody>
		  		<tr>
		  			<td>#${sessionScope.carrinho.pedido.id}</td>
		  			<td>FOTO + ${sessionScope.carrinho.portaRetrato.nome}</td>
		  			<td>R$ <f:formatNumber pattern="#,##0.00">${sessionScope.carrinho.pedido.valor}</f:formatNumber></td>
		  		</tr>
		  		<tr >
		  			<td colspan="2" align="right">
			  			<form action="adicionarCupom" method="post">
				   		<label for="inputNome" class="col-sm-2 control-label">Cupom:</label>
				   		<c:if test="${sessionScope.carrinho.cupom != null}">
				   			<div class="col-sm-2">
								${sessionScope.carrinho.cupom.codigo}
							 </div>
				   		</c:if>
				   		<c:if test="${sessionScope.carrinho.cupom == null}">
							<div class="col-sm-2">
							 	<input type="text" class="form-control" id="codigo" name="codigo" placeholder="Cupom" required autofocus>
						 	</div>
							 <div class="col-sm-2">
							 	  <button id="btnAdicionarCupom" type="submit" class="btn btn-primary" ><h4>Adicionar Cupom</h4></button>
							 </div>
						 	 
						 	<div class="col-sm-2">
						 		<font color="red">${msg}</font>
						 	</div>
						 </c:if>
						</form>
						Desconto: 
					</td>
					<td>
						<font color="red">R$ <f:formatNumber pattern="#,##0.00">${sessionScope.carrinho.desconto}</f:formatNumber></font>
					</td>	
		  		</tr>
		  		<tr>
		  			<td colspan="2" align="right">
		  				<h4><span >Total: </span></h4>
		  			</td>
		  			<td><h4><span class="label label-success">R$ <f:formatNumber pattern="#,##0.00">${sessionScope.carrinho.valor}</f:formatNumber> </span><span style="font-weight:bold; color: #f49e09">&nbsp; + Frete</span></h4> <h6>O pagseguro calculará o seu frete</h6></td>
		  		</tr>
		  	</tbody>
		  	
		  </table>
		   		
		</div>
		<div style="margin-top: 30px;text-align: center;">
    		<b>Clique no botão do pagseguro para efetuar o pagamento com segurança.</b><br>
    	</div>	
	 <div style="margin-top: 20px; margin-bottom: 90px; text-align: center;">
	 	
        	<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
		<form  action="${requestScope.redirect}" method="post" target="_blank" onsubmit="finalizar();">
			<input class="btn btn-default btn-lg" type="image" src="assets/img/pagseguro.png" width="250px;" name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" value="Clique aqui para pagar com:" style="border: 3px solid transparent; border-color: #333333"></input>
		</form>
		<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
   
     		</div>    
	 		
       
</div>
<wjaa:rodape/>       
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
</body>
<script>
   function finalizar(){
	   
	   setTimeout(function(){
		   window.location.href = 'fecharCompra';
	   },2000);
	   
   }

</script>
</html>
