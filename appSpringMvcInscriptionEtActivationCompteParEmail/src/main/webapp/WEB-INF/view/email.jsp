<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur email</title>
</head>
<body>
	<h2>Problème sur l'adresse email</h2>
	<p>email 
		Une erreur s'est produite au momemnt de l'ebregistrement de votre adresse email ; ${utilisateur.getEmail()}
	</p>
</body>
</html>