<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header/>
<body>

	<div class="container">
	   	 <wjaa:logo/>
	 <div class="carousel-size">  	 
		 <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
		  </ol>
		
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner">
		    <div class="item active">
		      <img src="assets/img/car1.jpg" alt="Primeiro passo" height="600px" >
		      <div class="carousel-caption">
		        Primeiro Passo...
		      </div>
		    </div>
		    <div class="item">
		      <img src="assets/img/car2.jpg" alt="Primeiro passo" height="600px" >
		      <div class="carousel-caption">
		        Segundo Passo...
		      </div>
		    </div>
		    <div class="item">
		      <img src="assets/img/car3.jpg" alt="Primeiro passo" height="600px" >
		      <div class="carousel-caption">
		        Terceiro Passo...
		      </div>
		    </div>
		    <div class="item">
		      <img src="assets/img/car4.jpg" alt="Primeiro passo" height="600px">
		      <div class="carousel-caption">
		        Quarto Passo...
		      </div>
		    </div>
		    
		  </div>
		
		  <!-- Controls -->
		  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left"></span>
		  </a>
		  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
		  </a>
		</div>
	</div>	
</div>   	 

</body>
<wjaa:botton ondeEstou="INDEX"/>
</html>
