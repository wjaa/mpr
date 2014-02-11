<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>  
<html>
<style>
	.table-hover th{
	   color: #1F458E;
	}
	
</style>
<wjaa:header/>
<body>
<wjaa:menu/>

<div class="container">
	<wjaa:logo/>
   	<div class="jumbotron page-header" style="margin: 0px;">
   	 	
   	 	<div>
   	 		<form action="pedido" method="get">
       		<h4><span class="label label-primary">Pedido</span>&nbsp;&nbsp;Digite o código do pedido no campo abaixo:</h4>
     		<div class="input-group" style="margin-top: 30px;">
      			<input type="text" class="form-control" name="hash"  placeholder="Digite o código do pedido.">
      			<span class="input-group-btn">
        			<button class="btn btn-default" type="submit">Buscar</button>
      			</span>
    		</div>
    		</form>
	 	</div>
	 </div>
	 <br/>
	 <br/>
	 <c:if test="${msg != null}">
	 	<div class="alert alert-danger">${msg}</div>
	 </c:if>
	 <c:if test="${pedido != null }">
		 <!-- Tabela de pedidos-->
		<table class="table table-hover">
		 		<thead>
		         <tr>
		           <th># Pedido</th>
		           <th>Porta Retrato</th>
		           <th>Data Pedido</th>
		           <th>Status</th>
		           <th>Código de Rastreio</th>
		         </tr>
		       </thead>
			<tbody>
		          <tr>
		            <td>${pedido.id}</td>
		            <td>${pedido.portaRetrato.nome}</td>
		            <td>${pedido.dataPedidoStr}</td>
		            <td>${pedido.statusEnum}</td>
		            <td>-</td>
		          </tr>
		     </tbody>
		    </table>
    </c:if>
</div>
<wjaa:botton/>
</body>
<script>
	$("#inBusca").keypress(function(e) {
    	if(e.which == 13) {
        	buscar();
    	}
	});
	
	function buscar(){
		document.forms[0].submit();
	}
</script>
</html>	 