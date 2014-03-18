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
	       		<h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Procure uma foto<br><br>
	       		<small>Faça login na sua conta do facebook clicando no botão abaixo, ou caso já esteja logado e queria trocar de usuário, clique no botão 'Sair da conta'. </small></h4>
			 	<div class="row">
				 	<div class="col-lg-10">
					 	<div class="input-group">
				     		<img src="assets/img/btnfacebook.png" id="logarFace" class="btn"/>
							<button id="sairFace" class="btn btn-danger" >Sair da conta.</button>
					  	</div>
				  	 </div>
			  	 </div>
			  	 
	       	</div>
	 	  
 	 		<div class="row txtInfo">
 	 	
		 	 		<div class="col-sm-6 col-md-6" style="margin-top: 5px">
							<ul class="list-group">
								<li class="list-group-item active">
							   		Informações:
								</li>
								<li class="list-group-item">
				   					
				   					 As imagens com bordas vermelhas são imagens quadradas que muito provalmente são fotos do instagram.  
				   				</li>
				   				<li class="list-group-item">
				   					
				   					 Estaremos bloqueando essas imagens porque não ficaram boas em porta retrato retangular. Caso queira escolher fotos do instagram <a href="home?listPr=INSTAGRAM">clique aqui</a>  
				   				</li>
				   			</ul>
					</div>	
			
			</div>
		  	 
		</div>
	
	

     

	<div>
		    <div id="facebook" class="row" style="margin: 10px;">
		  
			 </div>	
			 <div style="text-align: center;">
			 	<button id="btnAnterior" type="button" class="btn btn-success">Anterior</button>
		     	<button id="btnProximo" type="button" class="btn btn-success" >Proximo</button>
			 </div>
		
			<form action="uploadUrl" method="POST">
	   			<input type="hidden" id="urlParam" name="url" value=""/>
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
		  	
		  	<!-- GERAR UMA TAG DESSE CARA -->
			<div class="modal fade" id="aguardeModal">
		      <div class="modal-dialog" style='width:375px;'>
		        <div class="modal-content">
		          <div class="modal-header" >
		            <h4 class="modal-title">Aguarde...<br><small>Gerando visualização</small></h4>
		            
		          </div>
		          <div class="modal-body" style="text-align: center;">
		            <img src="assets/img/aguarde.gif" width="178px" height="172px"/>
		          </div>
		        </div>
		      </div>
		  	</div>
		  	<!-- FIMMMMM -->
		  	
	</div>
</div>
<wjaa:rodape/>
</body>
<wjaa:botton/>
<script src="assets/js/faceretrato.js"></script>
<wjaa:prEscolhido_js/>

<script>
	$("#btnAnterior").attr('disabled', true);	
	$("#btnProximo").attr('disabled', true);
</script>
</html>