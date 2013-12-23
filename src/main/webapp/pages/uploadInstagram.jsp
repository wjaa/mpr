<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>  
<html>
<wjaa:header/>
<header>
<script type="text/javascript" src="assets/js/instaretrato.js"></script>
</header>
<body>

<div class="container">
   	 <wjaa:top/>

    <div class="jumbotron"> 
  <form role="form">
  
	 <div class="form-group">
    	<label for="exampleInputEmail1">Digite o nome de seu usu&aacute;rio aqui:</label>
    	<input type="email" class="form-control" id="inBusca" placeholder="Usu&aacute;rio do instagram">
  	 </div>

     <button id="btnAnterior" type="button" class="btn btn-default">Anterior</button>

     <button id="btnProximo" type="button" class="btn btn-default" >Proximo</button>

     <button type="button" class="btn btn-default" onclick="buscar();">Buscar</button>

    <div id="instagram" class="row">
  
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
            <button type="button" class="btn btn-primary">Selecionar</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
  </div>

  </form>
</div>
</div>
</body>
<script>
   function buscar(){
   		instagram.init($("#inBusca").val());
   }
</script>
</html>