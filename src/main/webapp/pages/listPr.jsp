<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	<div class="row">
			
			<c:forEach var="pr" items="${requestScope.prs}">
				<form action="escolherPr" method="get">
					<div class="col-sm-6 col-md-3">
			  			<div class="thumbnail" style="margin: 10px;">
			  					<input type="hidden" name="listPr" value="${requestScope.listPr}">
			  					<input type="hidden" name="prCode" value="${pr.prCode}">
			    				
			    				<c:choose>
			    				   <c:when test="${pr.tipo == 'I'}">
			    				      <img src="/static/img/${pr.prCode}_T.png" onError="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" alt="${pr.nome}" width="130px" height="130px">
			    				   </c:when>
			    				   <c:otherwise>
			    				      <img src="/static/img/${pr.prCode}_T.png" onError="this.onerror=null;this.src='assets/img/nao_disponivel.jpg';" alt="${pr.nome}" width="90px" height="130px">
			    				   </c:otherwise>
			    				</c:choose>
			    				<div class="caption">
			      					<div style="height:70px;overflow: auto;"><h6>${pr.nome}</h6></div>
			     				 	<h3><span class="label label-success"><span style="font-size: small;">R$&nbsp;</span>${pr.precoStr}</span></h3>
			     				 	<br>
			      				 	<p><input type="submit" class="btn btn-primary" value="Comprar"/></p>
			    				</div>
			  			</div>
			  		</div>
		  		</form>
	  		</c:forEach>
	  		
	</div>

