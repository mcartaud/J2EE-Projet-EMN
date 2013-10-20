<%@ page language="java"
		 contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"
		 import="j2ee.association.bean.Article,
		 		 java.util.List,
				 java.util.Map,
				 java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Votre commande</title>
<%@include file="./config.jsp" %>
</head>

<body>
<%@include file="./menuBarre.jsp" %>
<div class="container">
	<h1>Votre commande</h1>
	<table>
		<thread>
		<tr>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Quantité</th>
		</tr>
		</thread>

		<tbody>
<!-- 			<tr> -->
<!-- 				<th>element</th> -->
<!-- 				<th>element</th> -->
<!-- 				<th>element</th> -->
<!-- 				<th>element</th> -->
<!-- 			</tr> -->
			<%
				List<Article> articles = (List<Article>)request.getAttribute("articles");
				Map<String, Integer> command = (Map<String,Integer>)request.getSession().getAttribute("command");
				
				if(command!=null) {
					for(Article article : articles) {
						if(command.containsKey(article.getArId())) {
							out.println("<tr>");
							out.println("<th>"+article.getArCode()+"</th>");
							out.println("<th>"+article.getArName()+"</th>");
							out.println("<th>"+article.getArPrice()+"</th>");
							out.println("<th>"+command.get(article.getArId())+"</th>");
							out.println("</tr>");
						}
					}
				}
			%>
		</tbody>
	</table>
	<%
	if( request.getSession().getAttribute("userName") != null ) {
		out.println( "<form action=\"commande\" method=\"post\">");
		out.println("<input type=\"submit\" value=\"Commander\" name=\"action\" />");
		out.println("<input type=\"submit\" value=\"Annuler\" name=\"action\" />");
		out.println("</form>");
	}
	%>
</div>
</body>
</html>