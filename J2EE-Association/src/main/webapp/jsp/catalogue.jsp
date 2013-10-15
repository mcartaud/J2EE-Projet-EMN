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
	<table>
		<thread>
		<tr>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Stock</th>
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
				out.println("</tr>");
			}
		%>
		</tbody>
	</table>
</div>
</body>
</html>