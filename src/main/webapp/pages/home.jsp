<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header/>
<body>

<div class="container">
   	 <wjaa:top/>
   	 
   	 <ol class="breadcrumb">
		<li><span class="label label-success">1° Escolha o Porta retrato</span></li>
		<li><span class="label label-default">2° Envie Sua foto</span></li>
		<li><span class="label label-default">3° Veja como ficou</span></li>
	 </ol>
   	 
   	 
      <!-- Jumbotron -->
    <div class="jumbotron">
        <h2>1° passo.  Escolha o seu porta retrato.</h2>
        <p class="lead">Escolha entre porta retratos comuns ou porta retratos especificos para instagram.</p>
	</div>
	
	<div class="pr-list">
		   
	</div>
</div>
</body>
<wjaa:botton ondeEstou="HOME" listPr="${listPr}"/>
</html>
