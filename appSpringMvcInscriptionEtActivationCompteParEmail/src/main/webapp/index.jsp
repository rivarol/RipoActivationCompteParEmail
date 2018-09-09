<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<c:url value="/showMessage" var="messageUrl" />
		<a href="${messageUrl}">Click to enter</a>
		<p>
			Connectez-vous à votre espace utilisateur<br/>
			<c:url value="login" var="loginUrl" />
			<a href="${loginUrl}">Connexion</a>
		</p>
	</body>
</html>
