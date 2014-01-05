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

	<div class="alert alert-danger" style="display:none;"></div>

	<img src="assets/img/btnfacebook.png" id="logarFace" class="btn"/>
	<button id="sairFace" class="btn btn-primary" >Trocar de usuário?</button>

     <button id="btnAnterior" type="button" class="btn btn-default">Anterior</button>
     <button id="btnProximo" type="button" class="btn btn-default" >Proximo</button>

    <div id="facebook" class="row">
  
	 </div>	

   <div class="modal fade" id="previewModal">
      <div class="modal-dialog" style='width:375px;'>
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Essa foto que deseja imprimir?</h4>
          </div>
          <div class="modal-body">
            <img id="previewImg" style='width:310px;'/>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
            <button type="button" class="btn btn-primary">Selecionar</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
  </div>
</div>	
</body>
<script type="text/javascript" src="assets/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="assets/js/faceretrato.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</html>