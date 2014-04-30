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
   
   			<c:if test="${msgInfo != null }">
	    		<div class="alert" style="margin-top: 30px;">${msgInfo}</div>
	    	</c:if>
	
	  </div>	
</div>       
<wjaa:botton/>
<c:if test="${requestScope.ligaGoogleAnalytics}">
	<wjaa:googleAnalytics/>
</c:if>
<wjaa:rodape/>
</body>
</html>
