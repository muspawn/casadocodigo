<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

<head>
	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
	<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
	

 
</head>

	<section id="index-section" class="container middle">
	
		<c:if test="${not empty message }">
			<h1 class="cdc-call">${message }</h1>
		</c:if>
		<c:if test="${empty message }">
			<!-- <h1 class="cdc-call">Lista de Pedidos Atuais</h1> -->
		</c:if>
		
		
	<div class="container">
		<h1>Lista de Produtos</h1>
		<br>
	
		<table class="table table-bordered">
			
			<tr>
				<th >ID</th>
				<th >Valor</th>
				<th >Data Pedido</th> 
				<th >Titulos </th>
			</tr>
			
		
			<c:forEach items="${response }" var="response" >
	          <tr>
	                    <td>${response.id}</td>
	                    <td>${response.valor} </td>
	                    <td><fmt:formatDate pattern="dd/MM/yyyy"
	                            value="${response.data.time}" /></td>
	                   	<td>
		                     <c:forEach  items="${response.produtos }"   var="produtos" varStatus="id" > 
				                  <c:if test= "${id.count > 1}" >, </c:if>  
				                   	  
				                   ${produtos.titulo}      
			                    
		                     </c:forEach > 
		                     
		                    
	                   	</td>
	                </tr>
			</c:forEach>			
			
		

		</table>
	</div>		


		


	</section>


	
</tags:pageTemplate>
