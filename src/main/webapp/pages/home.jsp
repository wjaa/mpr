<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header description="Escolha suas fotos ou de seus amigos do instagram e crie um porta retrato para presentear ou decorar sua casa."
title="MeuPortaRetrato.com - Monte um porta retrato para suas fotos do instagram."/>
<body>
<wjaa:menu/>
<div class="container">
   	 <wjaa:logo/>
     <div class="jumbotron page-header" style="margin: 0px;">
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
	
	<div class="pr-list">
		   
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
