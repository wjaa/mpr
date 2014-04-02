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
 	<div>
 		<div class="row">
  			<div class="col-xs-12 col-md-12">
  				<div id="myCarousel" class="carousel slide">
    <!-- Carousel items -->
    <div class="carousel-inner">
        <div class="active item">
        	<span class="thumbnail">
      			<img data-src="holder.js/900x200" alt=""  style="width: 900px; height: 272px;" src="assets/img/home_topo.jpg" usemap="#on-off">
      			<map name="on-off">
      				<area shape="rect" coords="0,0,450,272" href="${pageContext.request.contextPath}/escolherImagem?listPr=INSTAGRAM" alt="Porta retratos para fotos de instagram">
      				<!--  <area shape="rect" coords="450,0,900,272" href="${pageContext.request.contextPath}/escolherImagem?listPr=NORMAL" alt="Porta retratos para fotos comuns">-->
      			</map>
			</span>
        </div>
        <div class="item">
        	<span class="thumbnail">
      			
      				<img alt=""  style="width: 900px; height: 272px;" src="assets/img/home_topo2.jpg" alt="Monte o seu porta retrato com fotos do seu instagram">
      			
			</span>
        </div>
    </div>
    <!-- Carousel nav -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev">‹</a>
    <a class="carousel-control right" href="#myCarousel" data-slide="next">›</a>
</div>	
    			
    			
  			</div>
  
		</div>
	</div>	
 
 	<div>
    <div class="row" style="margin-top: 10px;" >
      <div class="col-sm-4 col-md-4">
        <div class="thumbnail">
          <a href="${pageContext.request.contextPath}/escolherImagem?listPr=INSTAGRAM">
          <img data-src="holder.js/300x200" alt="Decore seu escritório com fotos do instagram" class="homeImg" src="assets/img/home_img1.jpg" >
          </a>
          <div class="caption">
            <h3>Decore seu escritório</h3>
            <p>Dê um toque especial para o seu ambiente de trabalho. Decore com um lindo porta retrato com suas fotos do instagram.
			</p>
            <p><a href="${pageContext.request.contextPath}/escolherImagem?listPr=INSTAGRAM" class="btn btn-primary" role="button" style="margin-top: 46px;">Monte agora</a></p>
          </div>
        </div>
      </div>
      <div class="col-sm-4 col-md-4">
        <div class="thumbnail">
          <a href="${pageContext.request.contextPath}/home?escolherImagem=INSTAGRAM">
          <img data-src="holder.js/300x200" alt="Presentei seu ente querido com fotos do instagram" class="homeImg" src="assets/img/home_img2.jpg">
          </a>
          <div class="caption">
            <h3>Presenteie um familiar ou amigo.</h3>
            <p>Presenteie seus parentes e amigos com lindos portas retratos, faça um upload ou escolha as fotos do instagram da pessoa que deseja presentear. </p>
            <p><a href="${pageContext.request.contextPath}/escolherImagem?listPr=INSTAGRAM" class="btn btn-primary" role="button" >Monte agora</a></p>
          </div>
        </div>
      </div>
      <div class="col-sm-4 col-md-4">
        <div class="thumbnail">
          <a href="${pageContext.request.contextPath}/escolherImagem?listPr=INSTAGRAM">
          <img data-src="holder.js/300x200" alt="Decore sua casa com fotos do instagram" class="homeImg" src="assets/img/home_img3.jpg">
          </a>
          <div class="caption">
            <h3>Decore sua casa.</h3>
            <p>Decore sua casa com o amor, carinho e alegria de um momento. Eternize seus momentos em lindos portas retratos e quadros e enfeite cada canto de sua casa.</p>
            <p ><a href="${pageContext.request.contextPath}/escolherImagem?listPr=INSTAGRAM" class="btn btn-primary" role="button" style="margin-top: 25px;">Monte agora</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>   	 

</body>
<wjaa:rodape/>
<wjaa:botton/>
<script type="text/javascript">
    $('.carousel').carousel();
</script>
</html>
