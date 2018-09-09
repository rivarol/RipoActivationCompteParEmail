<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<style type="text/css">
	.error{
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
	<h2>Connexion compte utilisateur</h2>
	<form:form modelAttribute="utilisateur" action="connexionCompteUtilisateur" method="post">
		<table>
			<tr>
				<td colspan="2"><label>Adresse email</label></td>
			</tr>
			<tr>
				<td><form:input path="email" type="email"/></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><label>Mot de passe</label></td>
			</tr>
			<tr>
				<td><form:password path="motDePasse"/></td>
				<td><form:errors path="motDePasse" cssClass="error" /></td>
			</tr>
			<tr><td colspan="2"><input type="submit" value="Se connecter" /></td></tr>
		</table>
	</form:form>
	<br/><br/>
	Si vous n'avez pas de compte<br><a href="formNouveauCompte">Créer un compte </a>
</body>
</html>