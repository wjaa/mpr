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
       		<h4><span class="label label-primary">2° passo.</span>&nbsp;&nbsp;Selecione uma foto<br><br>
       		<small>Pesquise fotos no instagram por usuário ou #hashtag.</small></h4>
		 	<div class="row">
			 	<div class="col-lg-10">
				 	<div class="input-group">
			     		<input type="text"  class="form-control" id="inBusca" placeholder="Usu&aacute;rio ou #hashtag">
			     		<span class="input-group-btn">
					        <button id="btnBuscar" type="button" class="btn btn-default">Buscar</button>
					    </span>
			     		
			  	 	</div>
			  	 </div>	
			  	 <div class="col-lg-10">
			  		<div>
       					<h3>
       					<small>Veja alguns exemplos de usuário: <b>jermzlee, muradosmann, ifitwags</b><br><br>
       					       Veja alguns exemplos de hashtag: <b>#dog, #cat, #thundercats</b> 
       					       <br>
       					       <br>
       					       Não esqueça de adicionar <b>'#'</b> para hashtag.
       					</small></h3>
			  	 	</div>
			  	 	
			  	 </div>
		  	 </div>
		  	 
       	</div>
 	  
 	 	<wjaa:prEscolhido/>
		  	 
	</div>
		
	<div>	     
	    <div id="instagram" class="row" style="margin: 10px;">
	    
	    </div>	
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
	            <img id="previewImg"/>
	          </div>
	          <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            <button id="btnSelecionar" type="button" class="btn btn-primary">Selecionar</button>
	          </div>
	        </div><!-- /.modal-content -->
	      </div><!-- /.modal-dialog -->
	     </div>
	</div>
	<form action="uploadUrl" method="POST">
	   <input type="hidden" id="urlParam" name="url" value=""/>
	</form>
	
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
<wjaa:rodape/>
</body>
<wjaa:botton/>
<wjaa:prEscolhido_js/>
<script>

	$("#btnAnterior").attr('disabled', true);	
	$("#btnProximo").attr('disabled', true);

   function buscar(){
   		instagram.init($("#inBusca").val());
   }
   
   $("#inBusca").keypress(function(e) {
	    if(e.which == 13) {
	        buscar();
	    }
	});
   $("#btnBuscar").click(function(e){
	   buscar();
   });
   
</script>
</html>