<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="j2ee.association.bean.Article,
			java.util.List"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogue</title>
<%@include file="./config.jsp" %>
</head>

<body>
<%@include file="./menuBarre.jsp" %>
<div class="container">
	<h1>Catalogue des articles</h1>
	<form action="catalogue" method="post">
	<table class="displaytable">
		<thread>
		<tr class="displaytable">
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Stock</th>
			<%
				if(request.getAttribute("user") != null) {
					out.println("<th>Quantité</th>");
				}
			%>
		</tr>
		</thread>

		<tbody>
		<%
			List<Article> articles = (List<Article>)request.getAttribute("articles");
			for(int i=0; i<articles.size(); i++) {
				Article article = articles.get(i);
				out.println("<tr>");
				out.print("<th>");
					out.print(article.getArCode());
				out.println("</th>");
				out.print("<th>");
					out.print(article.getArName());
				out.println("</th>");
				out.print("<th>");
					out.print(article.getArPrice());
				out.println("</th>");
				out.print("<th>");
					out.print(article.getArStock());
				out.println("</th>");
				// If a user is connected (-> a bean has been given) he can select the nb of article he wants
				if(request.getSession().getAttribute("userName") != null) {
					out.print("<th><input type=\"number\" name=\""+article.getArId()+"\" /></th>");
					if(null != request.getAttribute((String)article.getArCode())) {
						out.println("Pas assez d\'articles en stock");
					}else out.println();
				}
				out.println("</tr>");
			}
		%>
		</tbody>
	</table>
	<input type="submit" value="Commander"/>
	</form>
</div>
</body>
</html>