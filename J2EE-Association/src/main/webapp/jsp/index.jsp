<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil adherent</title>
<%@include file="./config.jsp" %>
</head>

<body>
	<%@include file="./menuBarre.jsp" %>
	
	<div class="container">
		<h1>Bienvenue sur le site des adherents de l'association</h1>
		<p>
		<ul>
			<li><a href="./catalogue">Consulter les articles disponibles</a></li>
			<li><a href="./commande">Consulter votre commande</a></li>
		</ul>
		</p>
	</div>
</body>
</html>