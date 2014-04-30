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
	        <h4><span class="label label-primary">2° passo.</span>&nbsp;&nbsp;Escolha o seu porta retrato.<br><br>
	        <small>
	        <c:if test="${listPr == 'NORMAL' || listPr == 'FACEBOOK' || listPr == 'FLICKR' || listPr == 'GOOGLE'}">
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
				  			<div class="thumbnail">
				  					<input type="hidden" name="listPr" value="${requestScope.listPr}">
				  					<input type="hidden" name="prCode" value="${pr.prCode}">
				    				
				    				<c:choose>
				    				   <c:when test="${pr.tipo == 'I'}">
				    				     	<img class="onerror" onclick="showZoom('${pr.prCode}','${pr.nome}');" onerror="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" src="/static/img/${pr.prCode}_T.png" alt="${pr.nome}" title="clique na imagem para ampliar" width="130px" height="130px" style="cursor: pointer;">
				    				   </c:when>
				    				   <c:otherwise>
				    				     	<img class="onerror" onclick="showZoom('${pr.prCode}','${pr.nome}');" onerror="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" src="/static/img/${pr.prCode}_T.png" alt="${pr.nome}" title="clique na imagem para ampliar" width="90px" height="130px" style="cursor: pointer;">
				    				   </c:otherwise>
				    				</c:choose>
				    				<div class="caption">
				      					<div style="overflow: auto;"><h6>${pr.nome}</h6></div>
				      					<c:if test="${ligaDesconto}">
				     				 		<h3><span style="font-size: medium; color: #d9534f; font-weight: bold;">De R$ <s>${pr.precoStr}</s></span></h3>
				     				 		<h3><span class="label label-success"><span style="font-size: small;">Por</span> <f:formatNumber pattern="#,##0.00">${pr.preco - (pr.preco * desconto / 100) }</f:formatNumber> <span style="font-size: small;">à vista</span></span></h3>
				     				 	</c:if>
				     				 	<c:if test="${!ligaDesconto}">
				     				 		<h3><span class="label label-success"><span style="font-size: small;">R$&nbsp;</span><f:formatNumber pattern="#,##0.00">${pr.preco }</f:formatNumber> <span style="font-size: small;">à vista</span></span></h3>
				     				 	</c:if>
				     				 	<c:if test="${mostraParcela}">
				     				 		<h6 style="margin-top: 18px"><span class="info"><span style="font-size: small;">Ou em ${numParcela}x R$</span>
				     				 			<span style="font-size: 20px"><f:formatNumber pattern="#,##0.00">${pr.preco / numParcela}</f:formatNumber></span> <br>sem juros.
				     				 		</span></h6>
				     				 	</c:if>
				      				 	<p><input type="submit" class="btn btn-primary" value="Escolher"/></p>
				    				</div>
				  			</div>
				  		</div>
			  		</form>
		  		</c:forEach>
		</div>
	</div>
	
	
	<div class="modal fade" id="previewModal">
	      <div class="modal-dialog" style='width:475px;'>
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 id="modalTitulo" class="modal-title"></h4>
	          </div>
	          <div class="modal-body">
	          	<div class="flexslider" style="margin: 0px;">
				  <ul class="slides">
				    <li id="liPreview1" data-thumb="slide1-thumb.jpg">
				      <img class="onerror" onerror="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" id="previewImg1" height="400px;"/>
				    </li>
				    <li id="liPreview2" data-thumb="slide2-thumb.jpg">
				      <img class="onerror" onerror="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" id="previewImg2" height="400px;"/>
				    </li>
				    <li id="liPreview3" data-thumb="slide3-thumb.jpg">
				      <img class="onerror" onerror="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" id="previewImg3" height="400px;"/>
				    </li>
				  </ul>
				</div>
	            
	          </div>
	          <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	          </div>
	        </div><!-- /.modal-content -->
	      </div><!-- /.modal-dialog -->
	     </div>
	
</div>
<wjaa:rodape/>
</body>
<!-- FlexSlider -->
<script defer src="assets/js/jquery.flexslider-min.js"></script>

<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
</html>
<script>

function showZoom(prCode, desc){
	$('.flexslider').removeData("flexslider");
	$(".flex-control-thumbs").html("");
	
	$("#previewImg1").attr("src","/static/img/" + prCode + "_TZ.png");
	$("#liPreview1").attr("data-thumb","/static/img/" + prCode + "_TZ.png");
	
	$("#previewImg2").attr("src","/static/img/" + prCode + "_TZ2.png");
	$("#liPreview2").attr("data-thumb","/static/img/" + prCode + "_TZ2.png");
	
	$("#previewImg3").attr("src","/static/img/" + prCode + "_TZ3.png");
	$("#liPreview3").attr("data-thumb","/static/img/" + prCode + "_TZ3.png");
	
	$('.flexslider').flexslider({
	    controlNav: "thumbnails"
	  });
	
	$(".flex-control-thumbs li img").error(function(){
		this.onerror=null;
		this.src='assets/img/nao_disponivel.jpg';
	});
	
	$("#modalTitulo").html(desc);
	$("#previewModal").modal("show");
}

$(function(){
	$(".onerror").error(function(){
		this.onerror=null;
		this.src='assets/img/nao_disponivel.jpg';
	})
});

</script>