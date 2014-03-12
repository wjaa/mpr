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
	        	<h4><span class="label label-primary">2° passo.</span>&nbsp;&nbsp;Envie sua foto. <br><br>
	        	<small>Sua foto será tratada da melhor forma para uma boa impressão.</small></h4>
	        	
	        	<span class="btn btn-success fileinput-button">
		        <i class="glyphicon glyphicon-plus"></i>
		        <span>Adicionar Foto...</span>
		        <!-- The file input field used as target for the file upload widget -->
		        <input id="fileupload" type="file" name="files[]" multiple>
			    </span>
			    <br>
			    <!-- The container for the uploaded files -->
			    <div id="files" class="files"></div>
			    
			    <div id="progress" class="progress" style="display:none">
			       <div class="progress-bar progress-bar-success"></div>
			    </div>
		    	
	        </div>
	        
	        <wjaa:prEscolhido/>
	 </div>

	<!-- GERAR UMA TAG DESSE CARA -->
	<div class="modal fade" id="aguardeModal">
      <div class="modal-dialog" style='width:375px;'>
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Aguarde...<br><small>Gerando visualização</small></h4>
            
          </div>
          <div class="modal-body">
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
<wjaa:upload_js/>
<wjaa:prEscolhido_js/>
</html>
