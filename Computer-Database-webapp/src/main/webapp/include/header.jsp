<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="css/bootstrap.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="css/main.css" />" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-default">
		<h1>
			<a href="ListComputer?page=1&search="> Application - Computer
				Database </a>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>

			<c:if test="${pageContext.request.userPrincipal.name != null}">
			
					Welcome : ${pageContext.request.userPrincipal.name} | <a
						href="javascript:formSubmit()"> Logout</a>
			
			</c:if>
		</h1>
	</header>
	&nbsp;&nbsp; Language : &nbsp;
	<a href=?lang=en>english</a>&nbsp;|&nbsp;
	<a href="?lang=fr">francais</a>