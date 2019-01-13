<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage d'un user</title>
</head>
<body>
	<p>Pseudo : 

            <% 

            String pseudo = (String) request.getAttribute("pseudo");

            out.println( pseudo );

            %>
    </p>
    
    <p>Liste des users : 
	<br>
            <% 

            String list = (String) request.getAttribute("listUsers");

            out.println( list );

            %>
    </p>
</body>
</html>

        
