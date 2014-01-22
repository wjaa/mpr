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
 			 <li><a href="#tabPr" data-toggle="tab">Porta Retratos</a></li>
  			<li><a href="#tabConfig" data-toggle="tab">Configurações</a></li>
  			<li><a href="#tabPedidos" data-toggle="tab">Pedidos</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			
			<!-- ############ TAB DE PORTARETRATOS ###################### -->
			
  			<div class="tab-pane active" id="tabPr">
  				<form class="form-horizontal" role="form" action="savePr" method="POST" enctype="multipart/form-data">
  					<input type="hidden" id="idPr" name="id" />
				  <div class="form-group">
				    <label for="inputPrCode" class="col-sm-2 control-label">prCode:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="prCode" name="prCode" placeholder="prCode" required autofocus/>
				    </div>
				    <label for="inputPreco" class="col-sm-2 control-label">Preço:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="preco" name="precoStr" placeholder="Preço" required autofocus>
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
				  			<option value="N" >NORMAL</option>
				  			<option value="I">INSTAGRAM</option>
				  			<option value="F">FACEBOOK</option>
						</select>
						</div>
						
						<label for="inputQuantidade" class="col-sm-2 control-label">Quantidade:</label>
				    	<div class="col-sm-3">
				      		<input type="text" class="form-control" id="qtde" name="qtde" placeholder="Quantidade" required autofocus>
				    	</div>
						
					</div>
					<div class="form-group">
						<label for="inputPreview" class="col-sm-2 control-label">
				    	<img id="imgPreview" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIwAAACMCAYAAACuwEE+AAAFIklEQVR4Xu3X+SutbRTG8bUzFGXIUEiISJSSDKXEP49QykwZyvCDss0yO6279q7NOWUZOms9ffcv73uO23OuruuT+5HL5/NvwocGPtlADjCfbIpjqQHAAMHUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDmQFzfX0tGxsbov8dHh6WxsbGYhFvb2+yuLgoV1dXxa8dHR3JwcGBPD09pbMDAwNSXl7+z/J++/mm1f7j4UyAubm5kaWlJamrq5Ozs7MPYPb39+Xw8FAeHx/T16qqqmR2dlba29uloaFB1tbWpLOzU3p6ev46xW8//z/ub/6nMwHm/v5eXl5e5Pb2VlZWVkrAFMbu7e2Vzc3N9LW7uzvZ3t6WycnJhEex6fe3trbKzs6OjI6OSk1NjSwsLEgul5OhoSF5fX399vPHx8fNA3n7hkyAKZR6enpaAqZwFTU1NaWfJApDwZyfn6fraHp6Ol1Dq6ur6e+mpqZkeXlZFKB+z/HxsYyNjUl1dXX6J37i+d4AWPNkGoxeRTqyjn55efkBzMzMjJSVlZWA0Wtrfn4+XV/6XtPW1lbs9D2YrzzfOpC385kGMzc3l64fvVb0o9eK/r9ePScnJyVXkn5NYem1pleR/rmrq6vkveY9mK883xsAa55MgNH3j4eHB8nn87K1tSWDg4PpBVg/ei3pR99l9OrRnxqF9xN96a2vr5f19XXp7u6Wjo6O9NtURUWFtLS0pN+6RkZGpLa29keerwCjfzIBRn8z0neP9x99J6msrEx/fXFxUbyS9Ndo/Qmzt7cnz8/P0tzcLP39/bK7u5veWyYmJtLLsD5TofX19SVs332+Xn/RP5kAE32ESPkBE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBH+ACGI5qaapVofAAAAAElFTkSuQmCC" alt="..." class="img-thumbnail">
				    	</label>
				    	<div class="col-sm-3">
				      		<input type="file" class="form-control" id="preview" name="preview" placeholder="Preview" autofocus>
				    	</div>
				    	<label for="inputThumb" class="col-sm-2 control-label">
				    	<img id="imgThumb" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIwAAACMCAYAAACuwEE+AAAFIklEQVR4Xu3X+SutbRTG8bUzFGXIUEiISJSSDKXEP49QykwZyvCDss0yO6279q7NOWUZOms9ffcv73uO23OuruuT+5HL5/NvwocGPtlADjCfbIpjqQHAAMHUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDgDHVxWHAYMDUAGBMdXEYMBgwNQAYU10cBgwGTA0AxlQXhwGDAVMDmQFzfX0tGxsbov8dHh6WxsbGYhFvb2+yuLgoV1dXxa8dHR3JwcGBPD09pbMDAwNSXl7+z/J++/mm1f7j4UyAubm5kaWlJamrq5Ozs7MPYPb39+Xw8FAeHx/T16qqqmR2dlba29uloaFB1tbWpLOzU3p6ev46xW8//z/ub/6nMwHm/v5eXl5e5Pb2VlZWVkrAFMbu7e2Vzc3N9LW7uzvZ3t6WycnJhEex6fe3trbKzs6OjI6OSk1NjSwsLEgul5OhoSF5fX399vPHx8fNA3n7hkyAKZR6enpaAqZwFTU1NaWfJApDwZyfn6fraHp6Ol1Dq6ur6e+mpqZkeXlZFKB+z/HxsYyNjUl1dXX6J37i+d4AWPNkGoxeRTqyjn55efkBzMzMjJSVlZWA0Wtrfn4+XV/6XtPW1lbs9D2YrzzfOpC385kGMzc3l64fvVb0o9eK/r9ePScnJyVXkn5NYem1pleR/rmrq6vkveY9mK883xsAa55MgNH3j4eHB8nn87K1tSWDg4PpBVg/ei3pR99l9OrRnxqF9xN96a2vr5f19XXp7u6Wjo6O9NtURUWFtLS0pN+6RkZGpLa29keerwCjfzIBRn8z0neP9x99J6msrEx/fXFxUbyS9Ndo/Qmzt7cnz8/P0tzcLP39/bK7u5veWyYmJtLLsD5TofX19SVs332+Xn/RP5kAE32ESPkBE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBEAE2ktB1kB42CESBH+ACGI5qaapVofAAAAAElFTkSuQmCC" alt="..." class="img-thumbnail">
				    	</label>
				    	<div class="col-sm-3">
				    	  <input type="file" class="form-control" id="thumb" name="thumb" placeholder="Thumb" autofocus>
				    	</div>
				  </div>
	
					  <div class="form-group">
				    	<div class="col-sm-3">
				      		<button type="submit" class="btn btn-lg btn-primary btn-block">Gravar</button>
				    	</div>
				    	<div class="col-sm-3">
				      		<button id="btnDel" type="button" class="btn btn-lg btn-danger btn-block">Remover</button>
				    	</div>
				    	<div class="col-sm-3">
				      		<button id="btnNovo" type="button" class="btn btn-lg btn-success btn-block">Novo</button>
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
							            <td>${pr.precoStr}</td>
							            <td>${pr.tipoEnum}</td>
							            <td>${pr.qtde}</td>
							          </tr>
							           
						          </c:forEach>
						     </tbody>
					     </table>
					</div>
				</form>
			</div>
			
			<!-- ############ TAB DE CONFIGURACOES ###################### -->
			
			<div class="tab-pane" id="tabConfig">
				<form class="form-horizontal" role="form" action="saveConfig" method="POST">
				<input type="hidden" name="id" value="${requestScope.config.id}"/>
				<div class="form-group">
				   <label for="inputPathImg" class="col-sm-2 control-label">Caminho das Imagens:</label>
					<div class="col-sm-3">
				    	<input type="text" name="pathImgPr" class="form-control" id="pathImgPr" placeholder="pathImgPr" value="${requestScope.config.pathImgPr}"/>
					</div>
					<label for="inputPathUpload" class="col-sm-2 control-label">Caminho dos Uploads:</label>
					<div class="col-sm-3">
				    	<input type="text" name="pathUpload" class="form-control" id="pathUpload" placeholder="pathUpload" value="${requestScope.config.pathUpload}">
					</div>
				</div>	  
				<div class="form-group">
				   <div class="col-sm-offset-2 col-sm-2">
				   		<button type="submit" class="btn btn-lg btn-primary btn-block">Gravar</button>
				   </div>
				</div>	 
				</form>				  	 
		    </div>
		    
		    <!-- ##################### TAB DE PEDIDOS ############################## -->
		    
		    <div class="tab-pane active" id="tabPedidos">
		    	<form id="formPedidos" class="form-horizontal" role="form" action="listarPedidos" method="POST">
	  				<div class="form-group">
				    <label for="inputDataInicio" class="col-sm-2 control-label">Data Inicio:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="dataInicio" name="dataInicio" placeholder="Data Inicio"/>
				    </div>
				    <label for="inputPreco" class="col-sm-2 control-label">Data Fim:</label>
				    <div class="col-sm-3">
				      <input type="text" class="form-control" id="dataFim" name="dataFim" placeholder="Data Fim">
				    </div>
				    
				  </div>
				  <div class="form-group">
						<label for="inputTipo" class="col-sm-2 control-label">Tipo:</label>
				    	<div class="col-sm-3">
				  		<select class="form-control" name="status" id="status">
				  			<option value="">...</option>
				  			<option value="I" >INICIADO</option>
				  			<option value="E">EM_ANDAMENTO</option>
				  			<option value="P">PROCESSANDO</option>
				  			<option value="V">ENVIADO</option>
				  			<option value="C">CONCLUIDO</option>
						</select>
						</div>
						
					</div>
				
					  <div class="form-group">
				    	<div class="col-sm-3">
				      		<button id="btnBuscarPedidos" type="button" class="btn btn-lg btn-primary btn-block">Buscar</button>
				    	</div>
				    	
				  	 </div>
				 </form> 	 
  	 			<div id="tabListaPedidos">
  	 			</div>
			</div>
		    
		    <!-- ############## FIM DAS TABS ####################### -->
		    
		    
		    
		<div>
			 <div class="modal fade" id="modalConfirm">
		      <div class="modal-dialog" style='width:375px;'>
		        <div class="modal-content">
		          <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title">Remover Porta retrato.</h4>
		          </div>
		          <div class="modal-body">
		            Deseja realmente remover esse porta retrato?
		          </div>
		          <div class="modal-footer">
		            <button id="btnConfirmDel" type="button" class="btn btn-success">Sim</button>
		            <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>
		          </div>
		        </div><!-- /.modal-content -->
		      </div><!-- /.modal-dialog -->
		     </div> 
		</div>
   	 	
</div>
<wjaa:botton ondeEstou="admin"/>
</body>

<script>
  $(function () {
		$("#btnNovo").hide();
		$("#btnDel").hide();
    	$('#myTab a:first').tab('show');
    
    	$("#btnDel").click(function(){
    		$("#modalConfirm").modal("show");
    		 //deletePr
	    });
    	$("#btnNovo").click(function(){
  		  window.location.href = "admin";
  	  	});
    	
    	$("#btnConfirmDel").click(function(){
 			document.forms[0].action = "deletePr";
 			document.forms[0].submit();
    		
    	});
    	
    	$("#btnBuscarPedidos").click(function(){
    		var dados = $("#formPedidos").serialize();
    		$.ajax({
    			  type: "POST",
    			  url: "listarPedidos",
    			  data: dados,
    			  success: function(data){
    				  $("#tabListaPedidos").html(data);
    			  }
    			  
    			});
    	});

  })
  
 
  
  
  function editar(prJson){
	  var pr = $.parseJSON(decodeURIComponent(prJson));
	  
	  $("#idPr").attr('value',pr.id);
	  $("#prCode").attr('value',pr.prCode);
	  $("#nome").attr('value',pr.nome);
	  $("#descricao").attr('value',pr.descricao);
	  $("#preco").attr('value',pr.precoStr);
	  $("#extensao").val(pr.extImg);
	  $("#tipo").val(pr.tipo); 
	  $("#qtde").attr('value',pr.qtde);
	  $("#imgPreview").attr('src',"/static/img/" + pr.prCode + "_P.png?dummy=" + new Date().getTime());
	  $("#imgThumb").attr('src',"/static/img/" + pr.prCode + "_T.png?dummy=" + new Date().getTime());
	  
	  $("#btnNovo").show();
	  $("#btnDel").show();
	  	  
  }
  
  
  
</script>
</html>
