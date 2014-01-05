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
     <div class="jumbotron page-header" style="margin: 0px;">
        <h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Escolha o seu porta retrato.<br><br>
        <small>Escolha entre porta retratos comuns ou porta retratos especificos para instagram.</small></h4>
	 </div>
	
	<div class="pr-list">
		   
	</div>
</div>
</body>
<wjaa:botton ondeEstou="HOME" listPr="${listPr}"/>
</html>
