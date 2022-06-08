<%-- 
    Document   : inicio
    Created on : 17 abr. 2022, 23:22:56
    Author     : lenin
--%>
<%
  String nombre = (String)session.getAttribute("nombre");
  String primerApellido = (String)session.getAttribute("primerApellido");
  String segundoApellido = (String)session.getAttribute("segundoApellido");
  
    if(email == null){
        response.sendRedirect("./iniciarsesionmaestro.html");
        return;
    }
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/Estilos.css">
    </head>
    <body>
        <h5>Nombre:<%=nombre%> Primer apellido:<%=primerApellido%> Segundo apellido:<%=segundoApellido%></h5>
        <div id="titulo">
        <h2>Menú</h2>
        <div id="botonini6">
            <button onclick="window.location='./Cerrarsesion'" id="Boton14">Cerrar Sesión</button>
        </div>
    </body>
</html>
