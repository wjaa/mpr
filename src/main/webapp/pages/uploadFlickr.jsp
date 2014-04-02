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
       		<h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Procure uma foto no <span class="label" style="background-color: #FCFCFC; color:#609BE7">Flick<font color="#FF0787">r.</font></span><br><br>
       		<small>Pesquise fotos no flickr por usuário ou #hashtag.</small></h4>
		 	<div class="row">
			 	<div class="col-lg-10">
				 	<div class="input-group">
			     		<input type="text"  class="form-control" id="inBusca" placeholder="Usu&aacute;rio ou #hashtag">
			     		<span class="input-group-btn">
					        <button id="btnBuscar" type="button" class="btn btn-default">Buscar</button>
					    </span>
			     		
			  	 	</div>
			  	 </div>	
		  	 </div>
		  	 
       	</div>
 	  
 	 	<div class="row txtInfo">
 	 	
 	 		<div class="col-sm-6 col-md-6" style="margin-top: 5px">
					<ul class="list-group">
						<li class="list-group-item active">
					   		Dicas para Flickr:
						</li>
						<li class="list-group-item txt-item">
		   					 Procure as fotos sempre com a hastag e nome do usuário desejado.   
		   				</li>
		   				<li class="list-group-item txt-item">
		   					As fotos mais coloridas são as fotos que mais se adaptam ao papel na hora da impressão, tornando seu porta retrato muito mais bonito.   
		   				</li>
		   				<li class="list-group-item txt-item">
				   			Caso queira escolher fotos do instagram <a href="escolherImagem?listPr=INSTAGRAM">clique aqui</a>    
				   		</li>
		   			</ul>
			</div>	
			
		</div>
		  	 
	</div>
		
	<div>	

	    <div id="flickr" class="row" style="margin: 10px;">
	  
		</div>
		<form action="uploadUrl" method="POST">
	   		<input type="hidden" id="urlParam" name="url" value=""/>
	   		<input type="hidden" id="listPr" name="listPr" value="FLICKR"/>
	   		<input type="hidden" id="isAlterarFoto" name="isAlterarFoto" value="${isAlterarFoto}"/>
		</form>
		
		<div style="text-align: center;">
	    <button id="btnAnterior" type="button" class="btn btn-success">Anterior</button>
        	<button id="btnProximo" type="button" class="btn btn-success" >Proximo</button>
	    </div>
		<div class="modal fade" id="previewModal">
	      <div class="modal-dialog" style='width:375px;'>
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title">Essa foto que deseja imprimir?</h4>
	          </div>
	          <div class="modal-body">
	            <img id="previewImg" style="width: 300px;"/>
	          </div>
	          <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            <button id="btnSelecionar" type="button" class="btn btn-primary">Selecionar</button>
	          </div>
	        </div><!-- /.modal-content -->
	      </div><!-- /.modal-dialog -->
	  </div>
	  
	  <wjaa:aguarde/>

	</div>
	
<wjaa:rodape/>
</body>
<wjaa:botton/>
<script src="assets/js/flickrretrato.js"></script>
<wjaa:prEscolhido_js/>
</html>