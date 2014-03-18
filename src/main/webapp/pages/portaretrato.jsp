<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<html>
<wjaa:header description="Escolha suas fotos ou de seus amigos do instagram e crie um porta retrato para presentear ou decorar sua casa."
title="MeuPortaRetrato.com - Monte um porta retrato para suas fotos do instagram."/>
<body>
<wjaa:menu/>
<div class="container">
   	 <wjaa:logo/>
     <div class="jumbotron page-header" style="margin: 0px;">
     	<div style="float: left; width: 450px" >
	        <h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Escolha o seu porta retrato.<br><br>
	        <small>
	        <c:if test="${listPr == 'NORMAL' }">
	        	Os porta retratos abaixo são para fotos no estilo paisagem ou retrato. Trabalhamos apenas  com os tamanhos 10x15, 13x18 e 15x21. Em breve termos novos modelos e outros tamanhos.
	        </c:if>
	        
	        <c:if test="${listPr == 'INSTAGRAM' }">
	        	Os porta retratos abaixo são exclusivos para fotos de instagram. Trabalhamos apenas com os tamanhos 15x15, 18x18 e 21x21. Em breve teremos novos modelos e outros tamanhos.
	        </c:if>
	        
			</small></h4>
		</div>
		
		<wjaa:fotoEscolhida/>
		
	 </div>
	
	<div class="pr-list">
		    <div class="row">
				<c:forEach var="pr" items="${requestScope.prs}">
					<form action="escolherPr" method="get">
						<div class="col-sm-6 col-md-3">
				  			<div class="thumbnail" style="margin: 10px;">
				  					<input type="hidden" name="listPr" value="${requestScope.listPr}">
				  					<input type="hidden" name="prCode" value="${pr.prCode}">
				    				
				    				<c:choose>
				    				   <c:when test="${pr.tipo == 'I'}">
				    				      <img src="/static/img/${pr.prCode}_T.png" onError="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" alt="${pr.nome}" width="130px" height="130px">
				    				   </c:when>
				    				   <c:otherwise>
				    				      <img src="/static/img/${pr.prCode}_T.png" onError="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" alt="${pr.nome}" width="90px" height="130px">
				    				   </c:otherwise>
				    				</c:choose>
				    				<div class="caption">
				      					<div style="height:70px;overflow: auto;"><h6>${pr.nome}</h6></div>
				     				 	<h3><span class="label label-success"><span style="font-size: small;">R$&nbsp;</span>${pr.precoStr} <span style="font-size: small;">à vista</span></span></h3>
				     				 	<c:if test="${mostraParcela}">
				     				 		<h6 style="margin-top: 18px"><span class="info"><span style="font-size: small;">Ou em ${numParcela}x R$</span>
				     				 			<span style="font-size: 20px"><f:formatNumber pattern="#,##0.00">${pr.preco / numParcela}</f:formatNumber></span> <br>sem juros.
				     				 		</span></h6>
				     				 	</c:if>
				     				 	<br>
				      				 	<p><input type="submit" class="btn btn-primary" value="Escolher"/></p>
				    				</div>
				  			</div>
				  		</div>
			  		</form>
		  		</c:forEach>
		</div>
	</div>
</div>
<wjaa:rodape/>
</body>
<wjaa:botton/>
<script>
$(".pr-list").load("listarPr?listPr=${listPr}" ,function( data ) {
	$(".pr-list").html( data );
});
</script>
</html>
