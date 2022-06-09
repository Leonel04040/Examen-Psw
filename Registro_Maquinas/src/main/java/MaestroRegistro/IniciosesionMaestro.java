/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MaestroRegistro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenin
 */
public class IniciosesionMaestro extends HttpServlet {

        
    private Connection con;
    public void init(ServletConfig scg) throws ServletException {
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String password = request.getParameter("Password");
        String email = request.getParameter("Email");
        
        
        iniciarSesionMaestro(request,response,email,password);
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IniciosesionMaestro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Debe de ingresar un usuario valido");
            out.println("<button onclick=\"window.location='./iniciarsesionmaestro.html'\">Regresar</button>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
        private void iniciarSesionMaestro (HttpServletRequest request,HttpServletResponse response,String email, String password) throws SQLException, IOException{
        String isql = "SELECT * FROM cuenta_maestro as ca where ca.email=  ? and  ca.password = ?;";
        PreparedStatement ps = con.prepareStatement(isql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        
        HttpSession session = request.getSession();
        String nombre = null;
        String primerApellido = null;
        String segundoApellido = null;
        if(rs.next()){
            nombre = rs.getString("nombres");
            primerApellido = rs.getString("primer_apellido");
            segundoApellido = rs.getString("segundo_apellido"); 
            
            session.setAttribute("email",email);
            session.setAttribute("nombre", nombre);
            session.setAttribute("primerApellido", primerApellido);
            session.setAttribute("segundoApellido", segundoApellido);
            response.sendRedirect("./iniciomaestro.jsp");
            
        }
        rs.close();
        ps.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IniciosesionMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IniciosesionMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
