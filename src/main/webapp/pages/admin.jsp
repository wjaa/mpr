<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header/>
<style>
.tab-pane{
	margin-top: 25px; 
	
}
</style>
<body>

<div class="container">
   	 
	  <h2><span class="label label-danger">${requestScope.error}</span></h2>
	  		
	  <h2><span class="label label-info">${requestScope.msg}</span></h2>
		<br/>
		<ul class="nav nav-tabs" id="myTab">
 			 <li><a href="#cadPr" data-toggle="tab">Porta Retratos</a></li>
  			<li><a href="#cadConfig" data-toggle="tab">Configurações</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			
  			<div class="tab-pane active" id="cadPr">
  				<form class="form-horizontal" role="form" action="admin" method="POST">
  					<input type="hidden" name="flagSave" value="PR"/>
				  <div class="form-group">
				    <label for="inputPrCode" class="col-sm-2 control-label">prCode:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="prCode" name="prCode" placeholder="prCode" required autofocus/>
				    </div>
				    <label for="inputPreco" class="col-sm-2 control-label">Preço:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="preco" name="preco" placeholder="Preço" required autofocus>
				    </div>
				    
				  </div>
				  <div class="form-group">
				    <label for="inputNome" class="col-sm-2 control-label">Nome:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" required autofocus>
				    </div>
				    <label for="inputDescricao" class="col-sm-2 control-label">Descrição:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Descrição" required autofocus>
				    </div>
				  </div>
				  
				  <div class="form-group">
						<label for="inputTipo" class="col-sm-2 control-label">Tipo:</label>
				    	<div class="col-sm-3">
				  		<select class="form-control" required autofocus name="tipo" id="tipo">
				  			<option>...</option>
				  			<option>NORMAL</option>
				  			<option>INSTAGRAM</option>
				  			<option>FACEBOOK</option>
						</select>
						</div>
						
						<label for="inputQuantidade" class="col-sm-2 control-label">Quantidade:</label>
				    	<div class="col-sm-3">
				      		<input type="text" class="form-control" id="qtde" name="qtde" placeholder="Quantidade" required autofocus>
				    	</div>
						
					</div>
	
					  <div class="form-group">
				    	<div class="col-sm-offset-2 col-sm-2">
				      		<button type="submit" class="btn btn-lg btn-primary btn-block">Gravar</button>
				    	</div>
				  	 </div>
				  	 
				  	 <!-- Tabela de porta retratos cadastrados -->
				  	 <div>
						<table class="table table-hover">
					  		<thead>
					          <tr>
					            <th>prCode</th>
					            <th>Nome</th>
					            <th>Descrição</th>
					            <th>Preço</th>
					            <th>Tipo</th>
					            <th>Quantidade</th>
					          </tr>
					        </thead>
							<tbody>
								  <c:forEach var="pr" items="${requestScope.prs}">	
							          <tr id="tr${pr.prCode}" onclick="editar('${pr}');">
							            <td>${pr.prCode}</td>
							            <td>${pr.nome}</td>
							            <td>${pr.descricao}</td>
							            <td>${pr.preco}</td>
							            <td>${pr.tipo}</td>
							            <td>${pr.qtde}</td>
							          </tr>
							           
						          </c:forEach>
						     </tbody>
					     </table>
					</div>
				</form>
			</div>
			<div class="tab-pane" id="cadConfig">
				<form class="form-horizontal" role="form" action="admin" method="POST">
				<input type="hidden" name="flagSave" value="CONFIG"/>
				<div class="form-group">
				   <label for="inputPathImg" class="col-sm-2 control-label">Caminho das Imagens:</label>
					<div class="col-sm-3">
				    	<input type="text" name="pathImg" class="form-control" id="pathImg" placeholder="pathImg" value="${requestScope.prConfig.config.pathImgPr}"/>
					</div>
					<label for="inputPathUpload" class="col-sm-2 control-label">Caminho dos Uploads:</label>
					<div class="col-sm-3">
				    	<input type="text" name="pathUpload" class="form-control" id="pathUpload" placeholder="pathUpload" value="${requestScope.prConfig.config.pathUpload}">
					</div>
				</div>	  
				<div class="form-group">
				   <div class="col-sm-offset-2 col-sm-2">
				   		<button type="submit" class="btn btn-lg btn-primary btn-block">Gravar</button>
				   </div>
				</div>	 
				</form>				  	 
		  </div>
	

   	 
</div>
<script src="assets/js/jquery-1.10.2.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="assets/js/bootstrap.min.js"></script>
</body>

<script>
  $(function () {
    $('#myTab a:first').tab('show');
  })
  
  
  function editar(prJson){
	  var pr = $.parseJSON(decodeURIComponent(prJson));
	  $("#prCode").attr('value',pr.prCode);
	  $("#nome").attr('value',pr.nome);
	  $("#descricao").attr('value',pr.descricao);
	  $("#preco").attr('value',pr.preco);
	  $("#extensao").val(pr.extImg);
	  $("#tipo").val(pr.tipo);
	  $("#qtde").attr('value',pr.qtde);
	  
  }
</script>
</html>
