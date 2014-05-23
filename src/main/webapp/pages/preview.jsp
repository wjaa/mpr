<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<html>
<wjaa:header/>
<head>
 <style>
	div#portaRetratoPaisagem {
		-transform:rotate(90deg);
		-ms-transform:rotate(90deg); /* IE 9 */
		-webkit-transform:rotate(90deg); /* Opera, Chrome, and Safari */
	    -moz-transform: rotate(90deg);
      	-o-transform: rotate(90deg);
    
	}
</style>
	
</head>
<body>
<wjaa:menu/>
<div class="container">
 	<wjaa:logo/>
   	<div class="jumbotron page-header" style="margin: 0px;">
       	<h4><span class="label label-primary">3° passo.</span>&nbsp;&nbsp;Veja como ficou.<br><br>
       	<small>A imagem abaixo é apenas uma previa de como ficará o seu porta retrato. A qualidade do produto final é superior a imagem ilustrativa.</small></h4>
 	 </div> 
       
					<div  style="float: left; width: 450px; margin-top: 10px">
							<div class="btn-group" style="margin-bottom: 10px;margin-left: 95px;">
					  			<button id="btnTrocarPr" type="button" class="btn btn-default">Trocar porta retrato</button>
  								<button id="btnTrocarFoto" type="button" class="btn btn-default">Trocar foto</button>
							</div>
						<c:choose>
					       <c:when test="${sessionScope.carrinho.portaRetrato.tipo == 'I'}">
					       		<div id="imagem" style='position:relative; top:18px; left:110px; margin-left: 40px; margin-top: 10px;'>
									<img alt="preview" src="${sessionScope.carrinho.imgUrl}" width="150px" height="150px"/>
								</div>
								<div id="portaRetratoPaisagem" style='position:absolute; top:165px; left:90px; margin-left: 40px; margin-top: 10px;'>
									<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="200px" height="200px"/>
								</div>	
					       		<c:set var="styleAltura" value="margin-top: 50px;"></c:set>
					       </c:when>
					       <c:otherwise>
					       		<c:set var="isPaisagem" value="false"/>
					       		<c:if test="${sessionScope.carrinho.paisagem}">
					       			<c:set var="isPaisagem" value="true"/>
					       		</c:if>
					            <c:choose>
						            <c:when test="${isPaisagem}">
										<div id="imagem" style='position:relative; top:35px; left:78px; margin-left: 40px; margin-top: 10px;'>
											<img alt="preview" src="${sessionScope.carrinho.imgUrl}" width="210px" height="135px"/>
										</div>
										<div id="portaRetratoPaisagem" style='position:absolute; top:135px; left:110px; margin-left: 30px; margin-top: 20px;'>
											<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="175px" height="260px"/>
										</div>
										<c:set var="styleAltura" value="margin-top: 90px;"></c:set>
									</c:when>
									<c:otherwise>
										<div id="imagem" style='position:relative; top:20px; left:90px; margin-left: 70px;'>
											<img alt="preview" src="${sessionScope.carrinho.imgUrl}" width="130px" height="182px"/>
										</div>
										<div id="portaRetrato" style='position:absolute; top:175px; left:80px; margin-left: 80px; margin-left: 70px;'>
											<img alt="preview" src="/static/img/${sessionScope.carrinho.portaRetrato.prCode}_P.png" width="160px" height="220px"/>
										</div>
										<c:set var="styleAltura" value="margin-top: 50px;"></c:set>
									</c:otherwise>
								</c:choose>
					       </c:otherwise>
					    </c:choose>
					    <div class="caption" style="${styleAltura}">
			      			<h6>Foto + ${sessionScope.carrinho.portaRetrato.nome}</h6>
			     			<h4 style="margin-top: 20px"><span class="label label-success"><span style="font-size: small;">R$&nbsp;</span><f:formatNumber pattern="#,##0.00">${sessionScope.carrinho.pedido.itemSelecionado.valor}</f:formatNumber> <span style="font-size: small;">à vista</span></span></h4>
	     				 	<c:if test="${mostraParcela}">
	     				 		<h6 style="margin-top: 10px"><span class="info"><span style="font-size: small;">Ou em ${numParcela}x R$</span>
	     				 			<span style="font-size: 16px"><f:formatNumber pattern="#,##0.00">${sessionScope.carrinho.portaRetrato.preco / numParcela}</f:formatNumber></span> <br>sem juros.
	     				 		</span></h6>
	     				 	</c:if>
	     				 	
			    		</div>
				    	<div class="caption" >
			    			<form action="pagar" method="post">	
							   	<button id="btnFinalizar" type="submit" class="btn btn-primary"><h3>Finalizar Pedido</h3></button>
						    </form>
			    		</div>
			    		
			    		
						
					</div>
			
					<div class="col-sm-6 col-md-6" style="margin-top: 10px">
							<ul class="list-group">
							<li class="list-group-item active">
							   Informações importantes:
							</li>
								<li class="list-group-item">
				   				  <span class="badge">1</span>
				   				<b>O pagseguro calculará o preço do frete com base no seu endereço de entrega.</b>

				   				</li>
								  <li class="list-group-item">
				   				<span class="badge">2</span>
				   				<b>Levando em consideração a entrega de nossos fornecedores,  tempo de confecção e tratamento de imagem e impressão,  a entrega pode demorar até 5 dias úteis.</b>  
				   				</li>
				   				
				   				
				   				  <li class="list-group-item">
				   				  <span class="badge">3</span>
				   				Trabalhamos com entrega via correio, com isso será cobrado a taxa de postagem, após o envio do produto geraremos um código de rastreio e enviaremos para seu email.
				   				</li>
				   				
				   				  <li class="list-group-item">
				   				  <span class="badge">4</span>
				   				Não armazenamos nenhuma informação de nossos clientes, seus dados são armazenados apenas no pagseguro onde temos total segurança e confiabilidade em seus serviços.
				   				
				   				</li>
				   				<li class="list-group-item">
				   				  <span class="badge">5</span>
				   				Após a confirmação do pagamento, você receberá um email com o código do pedido, onde  poderá visualizar o status de seu pedido em nosso site.

				   				</li>
				   				
				   				
				   			</ul>	
		    		
					</div>
		    		
		    		<div class="modal fade" id="chooseModal">
				      <div class="modal-dialog" style='width:390px;'>
				        <div class="modal-content">
				          <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				            <h4 class="modal-title">Trocar de foto!</h4>
				          </div>
				          <div class="modal-body">
				          <div class="jumbotron page-header" style="margin: 0px;">
					       	<h4><small>Escolha a rede social de sua preferencia ou então faça um upload de uma foto do seu computador.</small></h4>
 	 					  </div>
				              
				              <ul class="nav nav-pills" style="margin-top: 10px;">
				              	 
		      
							      <li class="btn-change-flickr">
						      		<a href="#" style="color:#1f448d; font-weight: bold; padding:7px 7px;"><div style="width:75px; font-size:10px; text-align: center"><img src="assets/img/flickr-icon.png" alt="Escolha uma foto do seu Flickr" title="Escolha uma foto do seu Flickr" height="35px" /><br>Fotos do Flickr</div></a>
		      					  </li>
		      						<li class="btn-change-facebook">
		      							<a href="#" style="color:#1f448d; font-weight: bold; padding:7px 7px;"><div style="width:95px; font-size:10px; text-align: center"><img src="assets/img/facebook-icon.png" alt="Escolha uma foto do seu Facebook" title="Escolha uma foto do seu Facebook" height="35px" /><br>Fotos do Facebook</div></a>
		      						</li>
		      						 <li class="btn-change-camera">
		      							<a href="#" style="color:#1f448d; font-weight: bold; padding:7px 7px;"><div style="width:110px; font-size:10px; text-align: center"><img src="assets/img/camera-icon.png" alt="Escolha uma foto do seu Computador" title="Escolha uma foto do seu Computador"  height="35px" /><br>Fotos do Computador</div></a>
		      						</li>
				              
				              </ul>
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
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
<script>

$("#btnTrocarPr").click(function(){
	<c:if test="${sessionScope.carrinho.portaRetrato.tipo == 'I'}">
		window.location.href = 'listarPr?listPr=INSTAGRAM';
	</c:if>	
	<c:if test="${sessionScope.carrinho.portaRetrato.tipo == 'N' || sessionScope.carrinho.portaRetrato.tipo == 'F' || sessionScope.carrinho.portaRetrato.tipo == 'L' || sessionScope.carrinho.portaRetrato.tipo == 'G'}">
		window.location.href = 'listarPr?listPr=NORMAL';
	</c:if>
	
});

$("#btnTrocarFoto").click(function(){
	<c:if test="${sessionScope.carrinho.portaRetrato.tipo == 'I'}">
		window.location.href = 'escolherImagem?listPr=INSTAGRAM&isAlterarFoto=true';
	</c:if>	
	
	<c:if test="${sessionScope.carrinho.portaRetrato.tipo == 'N' || sessionScope.carrinho.portaRetrato.tipo == 'F' || sessionScope.carrinho.portaRetrato.tipo == 'L' || sessionScope.carrinho.portaRetrato.tipo == 'G'}">
		$("#chooseModal").modal("show");
	</c:if>
	
});


$(".btn-change-flickr").click(function (e){
	escolherImagem("FLICKR");
});

$(".btn-change-camera").click(function (e){
	escolherImagem("NORMAL");
});

$(".btn-change-facebook").click(function (e){
	escolherImagem("FACEBOOK");
});
	

function escolherImagem(pr){
	window.location.href = '${pageContext.request.contextPath}/escolherImagem?listPr=' + pr + "&isAlterarFoto=true";
}

</script>
</html>