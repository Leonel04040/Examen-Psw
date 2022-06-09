<%-- 
    Document   : consultaralumnos
    Created on : 8/06/2022, 06:34:17 PM
    Author     : Alumno
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
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
  /*String usuario = (String)session.getAttribute("usuario");
  String nombre = (String)session.getAttribute("nombre");
  String primerApellido = (String)session.getAttribute("primerApellido");
  String segundoApellido = (String)session.getAttribute("segundoApellido");
  String correo= (String)session.getAttribute("correo");
  String semestre = (String)session.getAttribute("semestre");
  String turno = (String)session.getAttribute("turno");*/

    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <table border="1">
            <tr>
                <th>Boleta: </th>
                <th>Nombre: </th>
                <th>Primer Apellido: </th>
                <th>Segundo Apellido: </th>
                <th>Correo Electronico: </th>
                <th>Semestre: </th>
                <th>Turno:</th>
            </tr>
            <%
                String boleta = (String) session.getAttribute("usuario");

                String isql = "SELECT * FROM maquinas.cuenta_alumno";
                PreparedStatement ps = con.prepareStatement(isql);
                ps.setString(1, boleta);
                ResultSet rs = ps.executeQuery();

                String usuario = null;
                String nombre = null;
                String primerApellido = null;
                String segundoApellido = null;
                String correo = null;
                String semestre = null;
                String turno = null;

                while (rs.next()) {
                    usuario = rs.getString("usuario");
                    nombre = rs.getString("nombre");
                    primerApellido = rs.getString("primerApellido");
                    segundoApellido = rs.getString("segundoApellido");
                    correo = rs.getString("correo");
                    semestre = rs.getString("semestre");
                    turno = rs.getString("turno");

                %>
                <tr>
                    <td><%=usuario%></td>
                    <td><%=primerApellido%></td>
                    <td><%=segundoApellido%></td>
                    <td><%=correo%></td>
                    <td><%=semestre%></td>
                    <td><%=turno%></td>
                </tr>
            <%
                }
            rs.close();
            ps.close();
            %>
        </table>
        </div>
                <div id="botonvolverinicio2">
            <button onclick="window.location='./iniciomaestro.jsp'" id="Boton18">Volver al Menú</button>
        </div>
    </body>
</html>
