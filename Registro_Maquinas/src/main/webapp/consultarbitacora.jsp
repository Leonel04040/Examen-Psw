<%-- 
    Document   : consultarbitacora
    Created on : 18 abr. 2022, 22:02:54
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

    public void jspInit(){
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
    public void jspDestroy(){
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
    </head>
    <body>
         <div id="indicaciones5">
            <h1>Estos son los problemas tecnicos de las maquinas</h1>
        </div>
        <table border="1">
            <tr>
                <th>ID Maquina</th>
                <th>Marca</th>
                <th>Fecha</th>
            </tr>

            <%
                String boleta = (String) session.getAttribute("usuario");

                String isql = "SELECT rpt.* FROM maquinas.maquina_registro as mr,maquinas.registro_problema_tecnico as rpt where mr.boleta=? and mr.id = rpt.id_maquina order by rpt.fecharegistro desc";
                PreparedStatement ps = con.prepareStatement(isql);
                ps.setString(1, boleta);
                ResultSet rs = ps.executeQuery();

                String id_maquina = null;
                String problematica = null;
                String fecharegistro = null;

                while (rs.next()) {
                    id_maquina = rs.getString("id_maquina");
                    problematica = rs.getString("problematica");
                    fecharegistro = rs.getString("fecharegistro");
            %>
            <tr>
                <td><%=id_maquina%></td>
                <td><%=problematica%></td>
                <td><%=fecharegistro%></td>
            </tr>
            <%
                }
            rs.close();
            ps.close();
            %>
        </table>
        
        <br>
        <div id="volveralmenu1">
            <button onclick="window.location='./inicio.jsp'" id="Boton21">Volver al Menú</button>
        </div>
    </body>
</html>