<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String param_userName = (String)request.getSession().getAttribute("userName");
%>
 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index">Association</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="index">Accueil</a></li>
            <li><a href="catalogue">Articles</a></li>
            <li><a href="commande">Commande</a></li>
            <li><a href="enregistrement">Inscription</a></li>
          </ul>
           <form action="<% out.print(param_userName==null?"identification":"deconnexion"); %>" method="get" class="navbar-form pull-right">
              <button type="submit" class="btn"><% out.print(param_userName==null?"Connexion":param_userName+": Deconnexion"); %></button>
           </form>
        </div><!--/.nav-collapse -->
      </div>
    </div>