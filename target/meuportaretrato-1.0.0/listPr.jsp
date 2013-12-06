<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:if test="${requestScope.listPr == 'NORMAL'}">
	<div class="row">
				<div class="col-sm-6 col-md-3">
	  			<div class="thumbnail">
	    				<img src="assets/img/portaretrato.gif" alt="porta retrato" width="150" height="100">
	    				<div class="caption">
	      				<h3>Porta retrato 10x15</h3>
	     					 <p>R$20,00</p>
	      				 <p><a href="#" class="btn btn-primary" rel="10">Comprar</a></p>
	    				</div>
	  			</div>
	  		</div>
	  		<div class="col-sm-6 col-md-3">
	  			<div class="thumbnail">
	    				<img src="assets/img/portaretrato.gif" alt="porta retrato" width="150" height="100">
	    				<div class="caption">
	      				<h3>Porta retrato 13x18</h3>
	     					<p>R$20,00</p>
	      				<p><a href="#" class="btn btn-primary" rel="20">Comprar</a></p>
	    				</div>
	  			</div>
	  		</div>
	  		<div class="col-sm-6 col-md-3">
	  			<div class="thumbnail">
	    				<img src="assets/img/portaretrato.gif" alt="porta retrato" width="150" height="100">
	    				<div class="caption">
	      				<h3>Porta retrato 15x25</h3>
	     					 <p>R$20,00</p>
	      				 <p><a href="#" class="btn btn-primary" rel="30">Comprar</a></p>
	    				</div>
	  			</div>
			</div>
	</div>
</c:if>

<c:if test="${requestScope.listPr == 'INSTAGRAM'}">
	<div class="row">
				<div class="col-sm-6 col-md-3">
	  			<div class="thumbnail">
	    				<img src="assets/img/portaretratoinsta.png" alt="porta retrato" width="150" height="100">
	    				<div class="caption">
	      				<h3>Porta retrato 10x15</h3>
	     					 <p>R$20,00</p>
	      				 <p><a href="#" class="btn btn-primary" rel="10">Comprar</a></p>
	    				</div>
	  			</div>
	  		</div>
	  		<div class="col-sm-6 col-md-3">
	  			<div class="thumbnail">
	    				<img src="assets/img/portaretratoinsta.png" alt="porta retrato" width="150" height="100">
	    				<div class="caption">
	      				<h3>Porta retrato 13x18</h3>
	     					<p>R$20,00</p>
	      				<p><a href="#" class="btn btn-primary" rel="20">Comprar</a></p>
	    				</div>
	  			</div>
	  		</div>
	  		<div class="col-sm-6 col-md-3">
	  			<div class="thumbnail">
	    				<img src="assets/img/portaretratoinsta.png" alt="porta retrato" width="150" height="100">
	    				<div class="caption">
	      				<h3>Porta retrato 15x25</h3>
	     					 <p>R$20,00</p>
	      				 <p><a href="#" class="btn btn-primary" rel="30">Comprar</a></p>
	    				</div>
	  			</div>
			</div>
	</div>
</c:if>