<%@ page language="java"
	contentType="text/html;
	charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="j2ee.association.bean.Country,
			java.util.List"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Enregistrement</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="association-signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../docs-assets/js/html5shiv.js"></script>
      <script src="../../docs-assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="enregistrement" method="post">
        <h2 class="form-signin-heading">Enregistrement</h2>
        <input type="text" class="form-control" placeholder="Pseudo" name="userID" autofocus>
        <input type="password" class="form-control" placeholder="Password" name="userPassword">
        <input type="password" class="form-control" placeholder="Password (confirm)" name="userPasswordConfirm">
        <input type="text" class="form-control" placeholder="Nom" name="userName" autofocus>
        <input type="text" class="form-control" placeholder="Prénom" name="userFirstName" autofocus>
        <input type="text" class="form-control" placeholder="Adresse" name="userAddress" autofocus>
        <input type="text" class="form-control" placeholder="Code Postal" name="userPostcode" autofocus>
        <input type="text" class="form-control" placeholder="Ville" name="userTown" autofocus>
        <select name="userCountry">
        	<%
        	List<Country> countries = (List<Country>)request.getAttribute("countries");
    		for(int i=0; i<countries.size(); i++) {
    			Country country = countries.get(i);
        		out.println("<option value=\""+country.getCoId()+"\">"+country.getCoName()+"</option>");
    		}
        	%>
        </select>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
  </body>
</html>