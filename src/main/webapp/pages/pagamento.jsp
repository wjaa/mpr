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
	      		<h2><span class="label label-danger">${requestScope.error}</span></h2>
	      		<div>
		        	<h4><span class="label label-primary">4° passo.</span>&nbsp;&nbsp;Pagamento no pagseguro <br><br>
		        	<small>Seu pedido foi criado com sucesso: <span style="font-size: 18px;"><b>#${sessionScope.carrinho.pedido.id}</b>.</span><br> Realize o pagamento no pagseguro para finalizar a compra.</small></h4>
				</div>
		    </div>
		    
			 <div>
	         	<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
				<form  action="${requestScope.redirect}" method="post" target="_blank">
				<input class="btn btn-default" type="image" src="assets/img/pagseguro.png" width="200px;" name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" value="Clique aqui para pagar com:"></input>
				</form>
				<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
		   
       		</div>    
		    	
	  </div>	
	 		
       
</div>
<wjaa:rodape/>       
<wjaa:botton/>
</body>
</html>
