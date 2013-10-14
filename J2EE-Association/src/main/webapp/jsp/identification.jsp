<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@include file="./config.jsp" %>
</head>

<body>
<%@include file="./menuBarre.jsp" %>
<div class="container">
	<form>
		<fieldset>
			<legend>Login</legend>
			<p>
				Identifiant <input type="text" name="id" />
			</p>
			<p>
				Password <input type="password" name="password" />
			</p>
			<p>
				<input type="submit" value="Envoyer" />
			</p>
		</fieldset>
	</form>
</div>
</body>
</html>