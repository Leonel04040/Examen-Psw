<%-- 
    Document   : inicio
    Created on : 17 abr. 2022, 23:22:56
    Author     : lenin
--%>
<%
  String usuario = (String)session.getAttribute("usuario");
  String nombre = (String)session.getAttribute("nombre");
  String primerApellido = (String)session.getAttribute("primerApellido");
  String segundoApellido = (String)session.getAttribute("segundoApellido");
  String semestre = (String)session.getAttribute("semestre");
  String turno = (String)session.getAttribute("turno");
  
    if(usuario == null){
        response.sendRedirect("./iniciarsesion.html");
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
        <h5>Usuario:<%=usuario%> Nombre:<%=nombre%> Primer apellido:<%=primerApellido%> Segundo apellido:<%=segundoApellido%> Semestre:<%=semestre%> Turno:<%=turno%> </h5>
        <div id="titulo">
        <h2>Menú</h2>
        </div>
        <div id="botonini2">
            <button onclick="window.location='./perfilusuario.jsp'" id="Boton10">Perfil del usuario</button>
        </div>
        <br>
        <div id="botonini1">
            <button onclick="window.location='./registromaquina.html'" id="Boton9">Registrar Maquina</button>
        </div>
        <br>
        <div id="botonini5">
            <button onclick="window.location='./consultardatosdelamaquina.jsp'" id="Boton13">Consultar datos de la maquina</button>
        </div>
        <br>
        <div id="botonini3">
            <button onclick="window.location='./registroproblematecnico.jsp'" id="Boton11">Registro de problema técnico</button>
        </div>
        <br>
        <div id="botonini4">
            <button onclick="window.location='./consultarbitacora.jsp'" id="Boton12">Consultar Bitacora</button>
        </div>
        <br>
        <div id="botonini6">
            <button onclick="window.location='./Cerrarsesion'" id="Boton14">Cerrar Sesión</button>
        </div>
    </body>
</html>