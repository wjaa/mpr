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
   	 	
	   	 	<div style="float: left; width: 450px" >
	       		<h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Procure uma foto no <span class="label" style="background-color: #0000A0">Facebook.</span><br><br>
	       		<small>Faça login na sua conta do facebook clicando no botão abaixo, ou caso já esteja logado e queria trocar de usuário, clique no botão 'Sair da conta'.</small></h4>
			 	<div class="row">
				 	<div class="col-lg-10">
					 	<div class="input-group">
				     		
				     		<div class="input-group">
  								<span class="input-group-addon">Escolha um album:</span>
				     			<select id="selectAlbuns" class="form-control" onchange="faceretrato.listarPhotoByAlbum(this.value);">
							  		<option value="0">...</option>
								</select>
							</div>
							<div class="input-group" style="margin-top: 20px;">
				     			<img src="assets/img/btnfacebook.png" id="logarFace" class="btn"/>
								<button id="sairFace" class="btn btn-danger" >Sair da conta.</button>
							</div>
							
							
					  	</div>
				  	 </div>
			  	 </div>
			  	 
	       	</div>
	 	  
 	 		<div class="row txtInfo">
 	 	
		 	 		<div class="col-sm-6 col-md-6" style="margin-top: 5px">
							<ul class="list-group">
				   				<li class="list-group-item active txt-item">
							   		Dicas para Facebook:
								</li>
								<li class="list-group-item txt-item">
				   					Escolha a foto que mais lhe agrada para enquadrar e fazer um lindo porta retrato.   
				   				</li>
				   				<li class="list-group-item txt-item">
				   					As opções de fotos do aplicativo facebook são retangulares.   
				   				</li>
				   				<li class="list-group-item txt-item">
				   					Para que não haja nenhuma complicação na hora de produzirmos o porta retrato, estaremos bloqueando algumas imagens. As imagens com bordas vermelhas são imagens quadradas e não ficarão bem em porta retratos retangulares.   
				   				</li>
				   				<li class="list-group-item txt-item">
				   					Caso queira escolher fotos do instagram <a href="escolherImagem?listPr=INSTAGRAM">clique aqui</a>    
				   				</li>
				   			</ul>
					</div>	
			
			</div>
		  	 
		</div>
	
	

     

	<div>
		    <div id="facebook" class="row" style="margin: 10px;">
		  
			 </div>	
			 <div style="text-align: center;">
			 	<button id="btnAnterior" type="button" class="btn btn-success">Página anterior</button>
		     	<button id="btnProximo" type="button" class="btn btn-success" >Próximo página</button>
			 </div>
		
			<form action="uploadUrl" method="POST">
	   			<input type="hidden" id="urlParam" name="url" value=""/>
	   			<input type="hidden" id="listPr" name="listPr" value="FACEBOOK"/>
	   			<input type="hidden" id="isAlterarFoto" name="isAlterarFoto" value="${isAlterarFoto}"/>
			</form>
		
		   <div class="modal fade" id="previewModal">
		      <div class="modal-dialog" style='width:375px;'>
		        <div class="modal-content">
		          <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title">Essa foto que deseja imprimir?</h4>
		          </div>
		          <div class="modal-body">
		            <img id="previewImg" style='width:310px;'/>
		          </div>
		          <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		            <button id="btnSelecionar" type="button" class="btn btn-primary">Selecionar</button>
		          </div>
		        </div><!-- /.modal-content -->
		      </div><!-- /.modal-dialog -->
		  	</div>
		  	
		  	<div class="modal fade" id="notPreviewModal">
		      <div class="modal-dialog" style='width:375px;'>
		        <div class="modal-content">
		          <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title">Essa foto é quadrada</h4>
		          </div>
		          <div class="modal-body alert alert-danger">
		            Fotos quadradas provavelmente são fotos do instagram.<br>
		            Essa imagem não ficará boa em um porta retrato retangular, escolha outra imagem.<br>
		          </div>
		          <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		          </div>
		        </div><!-- /.modal-content -->
		      </div><!-- /.modal-dialog -->
		  	</div>
		  	
		  	<wjaa:aguarde/>
		  	
	</div>
</div>
<wjaa:rodape/>
</body>
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
<script src="assets/js/faceretrato.js"></script>
<wjaa:prEscolhido_js/>

<script>
	$("#btnAnterior").hide();	
	$("#btnProximo").hide();
</script>
</html>