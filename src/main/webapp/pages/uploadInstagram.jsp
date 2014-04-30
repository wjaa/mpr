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
       		<h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Procure uma foto no <span class="label" style="background-color: #826D5D">Instagram.</span><br><br>
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
		  	 </div>
		  	 
       	</div>
 	  
 	 	<div class="row txtInfo">
 	 	
 	 		<div class="col-sm-6 col-md-6" style="margin-top: 5px;">
					<ul class="list-group">
						<li class="list-group-item active">
					   		Dicas para Instagram:
						</li>
						<li class="list-group-item txt-item">
		   					 Procure fotos de seus usuarios ou de seus amigos. Ex: <b>jermzlee, muradosmann, ifitwags.</b>  
		   				</li>
		   				<li class="list-group-item txt-item">
		   					 Procure escolher as fotos com mais brilho e intensidade, pois se adaptam melhor ao papel na hora da impressão.
		   				</li>
		   				<li class="list-group-item txt-item">
		   					 Temos a opção de procurar por hastag. Ex: <b>#dog, #cat, #thundercats.</b>
		   				</li>
		   				 
		   			</ul>
			</div>	
			
		</div>
		  	 
	</div>
		
	<div>	     
	    <div id="instagram" class="row" style="margin: 10px;">
	    
	    </div>	
	    <div style="text-align: center;">
	    <button id="btnAnterior" type="button" class="btn btn-success">Pagina anterior</button>
        	<button id="btnProximo" type="button" class="btn btn-success" >Próxima página</button>
	    </div>
	    
	    <div class="modal fade" id="previewModal">
	      <div class="modal-dialog" style='width:375px;'>
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title">Deseja enquadrar essa foto?</h4>
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
	   <input type="hidden" id="listPr" name="listPr" value="INSTAGRAM"/>
	   <input type="hidden" id="isAlterarFoto" name="isAlterarFoto" value="${isAlterarFoto}"/>
	</form>
	
	
	<wjaa:aguarde/>
	

</div>
<wjaa:rodape/>
</body>
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
<script src="assets/js/instaretrato.js"></script>
<wjaa:prEscolhido_js/>
<script>

	$("#btnAnterior").hide();	
	$("#btnProximo").hide();
	$("#inBusca").focus();
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