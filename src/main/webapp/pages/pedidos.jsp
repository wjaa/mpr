<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  
 <!-- Tabela de pedidos-->
<table class="table table-hover">
 		<thead>
         <tr>
           <th># Pedido</th>
           <th>PrCode</th>
           <th>Data Pedido</th>
           <th>Preço</th>
           <th>Status</th>
           <th>Transação</th>
           <th>Email</th>
           <th>Status Email</th>
           <th>Download</th>
         </tr>
       </thead>
	<tbody>
		  <c:forEach var="p" items="${requestScope.pedidos}">	
	          <tr>
	            <td>${p.id}</td>
	            <td>${p.portaRetrato.prCode}</td>
	            <td>${p.dataPedidoStr}</td>
	            <td>${p.portaRetrato.precoStr}</td>
	            <td>${p.statusEnum}</td>
	            <td>${p.codigoTransacao}</td>
	            <td>${p.emailCliente}</td>
	            <td>${p.emailEnviado}</td>
	            <td><a href="uploadFoto?getfile=${p.imageName}">${p.imageName}</a></td>
	            
	          </tr>
	           
          </c:forEach>
     </tbody>
    </table>
