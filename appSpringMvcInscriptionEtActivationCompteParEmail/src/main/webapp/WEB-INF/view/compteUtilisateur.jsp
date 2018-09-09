<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compte utilisateur</title>
</head>
<body>
	<h3>Bienenue sur votre compte</h3>
	<table>
		<tr>
			<td>Nom</td>
			<td>${user.getNom() }
		</tr>
		<tr>
			<td>Prénom</td>
			<td>${user.getPrenom() }
		</tr>
	</table>
</body>
</html>