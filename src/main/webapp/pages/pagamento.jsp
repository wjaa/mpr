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
		        	<small>Pedido criado com sucesso: #${sessionScope.carrinho.pedido.id}. Realize o pagamento no pagseguro para finalizar a compra.</small></h4>
				</div>
		    </div>
		    
		    
		    	
	  </div>	
	  <div>
	         	<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
				<form  action="${requestScope.redirect}" method="post" target="_blank">
				<input type="image" src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/209x48-comprar-assina.gif" name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" />
				</form>
				<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
		   
       		</div>	
       
</div>       
<wjaa:botton/>
</body>
</html>
