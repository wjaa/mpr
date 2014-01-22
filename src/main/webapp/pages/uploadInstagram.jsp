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
   	 	
   	 	<div style="float: left">
       		<h4><span class="label label-primary">2° passo.</span>&nbsp;&nbsp;Busque suas fotos no instagram.<br><br>
       		<small>Pesquise pelo seu usuário:</small></h4>
		 	<div class="row">
			 	<div class="col-lg-4">
				 	<div class="input-group">
			     		<input type="email" class="form-control" id="inBusca" placeholder="Usu&aacute;rio do instagram">
			     		<span>
	      			  		<button class="btn btn-default" type="button" onclick="buscar();" >Buscar</button>
	      				</span>
			     	
			  	 	</div>
			  	 </div>	
		  	 </div>
       	</div>
 	  
 	 	<wjaa:prEscolhido/>
		  	 
	</div>
		
	<div>	     
	    <div id="instagram" class="row"></div>	
		<button id="btnAnterior" type="button" class="btn btn-default">Anterior</button>
        <button id="btnProximo" type="button" class="btn btn-default" >Proximo</button>
	    <div class="modal fade" id="previewModal">
	      <div class="modal-dialog" style='width:375px;'>
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title">Essa foto que deseja imprimir?</h4>
	          </div>
	          <div class="modal-body">
	            <img id="previewImg"/>
	          </div>
	          <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            <button id="modal" type="button" class="btn btn-primary">Selecionar</button>
	          </div>
	        </div><!-- /.modal-content -->
	      </div><!-- /.modal-dialog -->
	     </div>
	</div>

</div>
</body>
<wjaa:botton ondeEstou="uploadInstagram"/>
<script>
   function buscar(){
   		instagram.init($("#inBusca").val());
   }
   
</script>
</html>