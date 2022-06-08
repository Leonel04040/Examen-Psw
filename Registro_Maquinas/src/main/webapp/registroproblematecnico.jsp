<%-- 
    Document   : registroproblematecnico
    Created on : 18 abr. 2022, 20:36:22
    Author     : lenin
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%!
    static Connection con = null;

    public void jspInit() {
        // se deben de establecer los elementos para la conexion con bd
        String url = "jdbc:mysql://localhost:3306/maquinas";
        //controlador:motorBD:puerto/IP/nombreBD
        String username = "root";
        String password = "n0m3l0";

        try {
            //internat concetar a la bd
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println("No conecto");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void jspDestroy() {
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
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
        <script type="text/javascript" src="./js/registroproblematecnico.js"></script>
    </head>
    <body>
        <fieldset>
            <legend>Describir Problema Tecnico</legend>
            <form name="frm_registro_problema" action="RegistroProblemaTecnico" method="post" onsubmit="return validarDatosRP()">
                <div id="indicaciones4">
                    <h1>A continuación debera de seleccionar el id de la maquina y describir el problema</h1>
                </div>
                <br>
                <div id="elegirmaquina">
                    <label>
                        Seleccione la maquina
                        <select name="maquinaid" id="MaquinaID" required>
                            <option value=""></option>
                            <%
                                String boleta = (String) session.getAttribute("usuario");

                                String isql = "SELECT * FROM maquinas.maquina_registro as mr where mr.boleta=?";
                                PreparedStatement ps = con.prepareStatement(isql);
                                ps.setString(1, boleta);
                                ResultSet rs = ps.executeQuery();

                                String id = null;
                                String marca = null;
                                String modelo = null;
                                String laboratorio = null;
                                String fecharegistro = null;

                                while (rs.next()) {
                                id = rs.getString("id");
                                marca = rs.getString("marca");
                                modelo = rs.getString("modelo");
                                laboratorio = rs.getString("laboratorio");
                                fecharegistro = rs.getString("fecharegistro");
                            %>
                            
                            <option value="<%=id%>"><%=id%></option>
                            <%
                                }
                                rs.close();
                                ps.close();
                            %>
                            
                        </select>
                    </label>
                </div>
                <br>
                <div id="descripcionproblematecnico">
                    <label>
                        Describa el problema técnico:
                        <input type="text" id="problematec" name="Describirproblema" size="100" maxlength="250" required>
                    </label>
                </div>
                <br>
                <div id="botonreprob">
                    <input type="submit" value="Enviar Problema tecnico" id="Boton17">
                </div>
                <br>
            </form>
        </fieldset>
        <br>
        <div id="botonvolveralinicio4">
            <button onclick="window.location='./inicio.jsp'" id="Boton20">Volver al Menú</button>
        </div>
    </body>
</html>