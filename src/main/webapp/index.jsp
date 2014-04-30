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
   	 <div style="margin-bottom: 10px;">
   	 <a href="#" onclick="openDlgEmail();" class="label label-success">Clique aqui</a> e adicione seu email para receber novidades, ofertas e cupom de desconto.<div style="float:right;" class="fb-like" data-href="https://www.facebook.com/meuportaretrato" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
   	 </div>
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
  
  
  
  
  <div class="modal fade" id="cadastroEmailModal">
	      <div class="modal-dialog" style='width:650px;'>
	      
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 id="modalTitulo" class="modal-title"><center>Promoção por tempo limitado.</center></h4>
	          </div>
	          <div class="modal-body">
	          
	          	<center><h4><span class="textBody">Todo o site com <font size="7em">20%</font> de desconto.</span></h4>
	             <h4>Cadastre seu email e ganhe um cupom com <font size="7em"> + 10%</font> de desconto.</h4></center>
	             	<form id="formEmail" onsubmit="return false;">
	             	<h4><span id="msgEmail" class="label label-danger" style="display: none;">Email inválido!</span></h4>
	             	<div class="input-group input-group-lg" style="">
  						
						<input type="text" class="form-control" placeholder="Entre com seu email aqui" name="email" id="email">
						<span class="input-group-btn" >
     						<button id="btnAdicionarEmail" class="btn btn-primary" type="button" >Adicionar</button>
   						</span>
   						
					</div>
      				</form>
					<div style="text-align: center; ">
	             		<h4><img src="assets/img/favicon.png" height="50px" style="margin:5px"/>Você receberá o cupom em seu email. <br></h4>
	             		<br>
	             		<h5>Promoção válida até o dia 05/05/2014.</h5>
	             		<h6>A equipe meuportaretrato.com agradece.</h6>
	             	</div>
	          </div>
	          <div class="modal-footer">
	            <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
	          </div>
	        </div><!-- /.modal-content -->
	        
	      </div><!-- /.modal-dialog -->
	     </div>
	
</div>
  
<wjaa:aguarde/>  
  

</body>
<wjaa:rodape/>
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
<script type="text/javascript">
    $('.carousel').carousel();
    
    setTimeout(function(){
    	openDlgEmail();
    	
    },2000);
    
    $("#email").keypress(function(e) {
	    if(e.which == 13) {
	        adicionarEmail();
	    }
	});
    
    function openDlgEmail(){
    	$("#btnAdicionarEmail").attr("enabled", true);
    	$("#email").attr("enabled",true);
    	
    	$("#btnAdicionarEmail").click(function(){
    		adicionarEmail();
    	});
    	$('#cadastroEmailModal').modal('show');
    	setTimeout(function(){
    		
    		$("#email").focus();
    	},500);
    }
    
    function adicionarEmail(){
    	var email = $("#email").val(); 
    	$("#btnAdicionarEmail").attr("enabled", false);
    	$("#email").attr("enabled",false);
    	if (isEmail(email)){
    		aguardeShow("Aguarde...");
    		submitForm("adicionarEmail", {email:email},"POST",function(){
    			aguardeHide();
        		$('#cadastroEmailModal').modal('hide');
        	});
    	}else{
    		$("#msgEmail").show();
    	}
    }
</script>
</html>
