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
		        	<h1>PEDIDO FINALIZADO COM SUCESSO. PARABEŃS EM BREVE CHEGAR O SEU PORTA RETRATO. ANOTE O NUMERO DO PEDIDO</h1>
				</div>
		    </div>
	  </div>	
	 
       
</div>       
<wjaa:botton ondeEstou="pagamento"/>
</body>
</html>
