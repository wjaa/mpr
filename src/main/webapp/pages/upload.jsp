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
      <!-- Jumbotron -->
      <div class="jumbotron page-header" style="margin: 0px;">
      	
      		<div style="float: left">
	        	<h4><span class="label label-primary">1° passo.</span>&nbsp;&nbsp;Envie sua foto do seu <span class="label" style="background-color: #5999E7; colo">Computador.</span> <br><br>
	        	<small>Sua foto será tratada da melhor forma para uma boa impressão.</small></h4>
	        	
	        	<span class="btn btn-success fileinput-button">
		        <i class="glyphicon glyphicon-plus"></i>
		        <span>Adicionar Foto...</span>
		        <!-- The file input field used as target for the file upload widget -->
		        <input id="fileupload" type="file" name="files" multiple>
			    </span>
			    <br>
			    <!-- The container for the uploaded files -->
			    <div id="files" class="files"></div>
			    
			    <div id="progress" class="progress" style="display:none">
			       <div class="progress-bar progress-bar-success"></div>
			    </div>
		    	
	        </div>
	        
	        <div class="row txtInfo">
 	 	
		 	 		<div class="col-sm-6 col-md-6" style="margin-top: 5px">
							<ul class="list-group">
								<li class="list-group-item active">
							   		Dicas para upload:
								</li>
								<li class="list-group-item txt-item">
				   					 Envie as fotos armazenadas em seu computador. Faça um upload e veja como ficará em nossos porta retratos.  
				   				</li>
				   				<li class="list-group-item txt-item">
				   					 Escolha fotos com uma qualidade boa, de preferência para imagens com resolução acima de 5mp.   
				   				</li>
				   				<li class="list-group-item txt-item">
				   					Caso queira escolher fotos do instagram <a href="escolherImagem?listPr=INSTAGRAM">clique aqui</a>    
				   				</li>
				   				
				   			</ul>
					</div>	
			
			</div>
	        
	        
	 </div>

	<wjaa:aguarde/>
	
</div>
<wjaa:rodape/>
</body>
<wjaa:botton/> 
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
<wjaa:upload_js/>
<wjaa:prEscolhido_js/>
</html>
