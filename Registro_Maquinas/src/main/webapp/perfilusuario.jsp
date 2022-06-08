<%-- 
    Document   : perfilusuario
    Created on : 18 abr. 2022, 17:34:06
    Author     : lenin
--%>
<%
  String usuario = (String)session.getAttribute("usuario");
  String nombre = (String)session.getAttribute("nombre");
  String primerApellido = (String)session.getAttribute("primerApellido");
  String segundoApellido = (String)session.getAttribute("segundoApellido");
  String correo= (String)session.getAttribute("correo");
  String semestre = (String)session.getAttribute("semestre");
  String turno = (String)session.getAttribute("turno");
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
        <table border="1">
            <tr>
                <th>Boleta: <%=usuario%></th>
            </tr>
                <th>Nombre: <%=nombre%></th>
            <tr>
                <th>Primer Apellido: <%=primerApellido%></th>
            </tr>
            <tr>
                <th>Segundo Apellido: <%=segundoApellido%></th>
            </tr>
            <tr>
                <th>Correo Electronico: <%=correo%></th>
            </tr>
            <tr>
                <th>Semestre: <%=semestre%></th>
            </tr>
            <tr>
                <th>Turno: <%=turno%></th>
            </tr>
        </table>
            <br>
        <div id="botonvolverinicio2">
            <button onclick="window.location='./inicio.jsp'" id="Boton18">Volver al Menú</button>
        </div>
    </body>
</html>