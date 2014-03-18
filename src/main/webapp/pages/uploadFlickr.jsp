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
					   		Informações:
						</li>
						<li class="list-group-item">
		   					
		   					 Procure fotos do seu usuário ou fotos de seus amigos.  
		   				</li>
		   				<li class="list-group-item">
		   					
		   					 Procure fotos por hashtag ex: <b>#dog, #cat, #thundercats</b>  
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
	            <button type="button" class="btn btn-primary">Selecionar</button>
	          </div>
	        </div><!-- /.modal-content -->
	      </div><!-- /.modal-dialog -->
	  </div>

	</div>
	
<wjaa:rodape/>
</body>
<wjaa:botton/>
<script src="assets/js/flickrretrato.js"></script>
<wjaa:prEscolhido_js/>
</html>