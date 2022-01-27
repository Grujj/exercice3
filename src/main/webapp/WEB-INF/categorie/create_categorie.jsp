<%@page import="beans.Categorie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creation categorie</title>
</head>
<body>
	<h1>Ajouter une catégorie</h1>
	<a href="/exercice3/articles">Voir les articles</a>
	<br>
	<%@ include file="/WEB-INF/generic/message.jsp" %>
	<form action="" method="post">
		<label for="titre">Titre : </label>
		<br>
		<input type="text" id="titre" name="titre">
		<br>
		<br>
		<input type="submit" value="Ajouter la catégorie">
	</form>
	
	<br>
	<br>
	<br>
	<h2>Voici la liste de vos catégories : </h2>
	<ul>
	<%
		List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
		for (Categorie categorie : categories) {
			out.println("<li>");
			out.println(categorie.getTitre());
			out.println("</li>");
		}
	%>
	</ul>
</body>
</html>