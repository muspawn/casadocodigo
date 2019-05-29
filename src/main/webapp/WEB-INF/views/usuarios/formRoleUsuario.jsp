<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/imagens" var="imgPath"/>

<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<!-- <link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap-theme.min.css" /> -->

<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
<body>

	<nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}" >Casa do Código</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="nav-item"><a href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a></li>
	        <li class="nav-item"><a href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	      	 <li class="nav-item">
	      	 	<a href="<c:url value="/logout" />">Sair</a></span>
	      	 </li>
	      	 <li class="nav-item">
	      	 	<a href="#">
	      	 		<security:authentication property="principal" var="usuario" />
	      	 		${usuario.username }
	      	 	</a>
	      	 </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
	</nav>

	<div class="container">
		
		<form:form action="${s:mvcUrl('updateRolesdoUsuario').build() } " method="post"   modelAttribute="usuarios">
		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
		<br> 
		<h1>Cadastro de permissão para: ${usuarios.nome}  </h1>
		<p> ${sucesso} </p>
		<p> ${falha} </p>
	
		
		<div class="container">
		

		
		<div class="form-group">
		
		
<%-- 		<form:hidden path="${usuarios.email }" value="${usuarios.email }"/> --%>
<%-- 		<form:hidden path="usuarios" value="${usuarios.nome }"/>
		<form:hidden path="$usuarios" value="${usuarios.senha }"/>
		<form:hidden path="usuarios" value="${usuarios.senharepetir }"/> --%>

					<!-- Inicio Form  -->
					<div class="form-group">
						<label>Nome</label>
						<form:input path="nome" cssClass="form-control" />
						<form:errors path="nome" />
					</div>
					<div class="form-group">
						<label>Email</label>
						<form:input path="email" cssClass="form-control" />
						<form:errors path="email" />
					</div>
					<div class="form-group">
						<label>Senha</label>
						<form:password path="senha" cssClass="form-control" />
						<form:errors path="senha" />
					</div>
					<div class="form-group">
						<label>Senha Repetida</label>
						<form:password path="senharepetir" cssClass="form-control" />
						<form:errors path="senharepetir" />
					</div>
					<!-- Final form -->



					<c:forEach items="${roles}" var="roles" varStatus="status" >
					<c:set var="contains" value="false"/>
					
						<%-- <p> ${roles.nome} -- ${roles.nome }</p> --%>
		 				<c:forEach items="${usuarios.roles }" var="usuarios.roles"  varStatus="status" >
						<%-- <p> ${usuarios.roles} </p> --%>
	<%-- 					<p> Roles nome - ${roles } </p>
						<p> Usuario roles - ${usuarios.roles[status.index] } </p> --%>
						
						<c:if test="${roles == usuarios.roles[status.index] }">
							<c:set var="contains" value="true"/>
						</c:if>	
					</c:forEach>
					<c:choose>
						<c:when test="${contains}">
							<form:checkbox path="roles" value="${roles.nome }" checked="true"/> ${roles }
							<%-- <form:hidden path="${roles }"/> --%>
							
							<%-- <p><input type="checkbox" name="roles" value="${roles[status.index].id }" checked="true"> ${roles.nome} </p> --%>
							<c:set var="contains" value="false"/>
						</c:when>
						<c:when test="${not contains }">
							<form:checkbox path="roles" value="${roles.nome }" /> ${roles }
						<%-- 	<form:hidden path="${roles }"/> --%>
							<%-- <p><input type="checkbox" name="roles" value="${roles[status.index] }" > ${roles.nome} </p> --%>
						
						</c:when>
					</c:choose>  
					
					
				</c:forEach> 		
		</div>
		<br>
		<button type="submit" class="btn btn-primary">Salvar</button>
		
	
		</form:form>





		</div>
	
	<!-- Inicio modal -->
		<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      ...
		    </div>
		  </div>
		</div>
	<!-- Final modal -->
	
	
</body>
</html>