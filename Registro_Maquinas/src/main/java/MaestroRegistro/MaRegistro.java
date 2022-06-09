package MaestroRegistro;

import javax.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenin
 */
public class MaRegistro extends HttpServlet {

    /**
     * Para poder establecer una conexion con bd es necesario contar con 3 tipos
     * de objeto muy especificos los cuales son: Connection es el encargado de
     * establecer la conexion con el servidor BD Statement sirve para poder
     * definir y manipular los diferentes objetos de las querrys como por
     * ejemplo: create, delete, insert, update, drop, etc ResultSet el cual
     * sirve para poder realizar las consultas a la BD
     */
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
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MaRegistro.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MaRegistro.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("Email");
        String nombre = request.getParameter("Nombre");
        String primerApellido = request.getParameter("Primerapellido");
        String segundoApellido = request.getParameter("Segundoapellido");        
        String password = request.getParameter("Password");
        int resultado = 0;
        PrintWriter out = response.getWriter();
        String mensajeError = "";
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumRegistro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<button onclick=\"window.location='./index.html'\">Inicio</button>");
            resultado = altaCuenta( email, nombre, primerApellido, segundoApellido, password);
            if (resultado == 1) {
                out.println("<h1>El registro fue satisfactorio</h1>");
            }    
        } catch (SQLException e) {
          resultado = 0;
          mensajeError = e.getMessage();
          if(e.getErrorCode() == 1062){
          out.println("<h1>El registro de la cuenta no fue satisfactorio debido a que ya existe un registro con el correo:"+email+"</h1>");
          }else{
              out.println("<h1>El registro no fue satisfactorio, codigo de error:"+e.getErrorCode()+": "+mensajeError+"</h1>");
          }
          
        }
        finally
        {
            
            out.println("<button onclick=\"window.location='./registromaestro.html'\">Regresar</button>");
            out.println("</body>");
            out.println("</html>");
        }


    }

    private int altaCuenta(
            String email,
            String nombre,
            String primerApellido,
            String segundoApellido,
            String password) throws SQLException {
        String isql = "insert into cuenta_maestro(email,nombres,primer_apellido,segundo_apellido,password)"
                + " values(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(isql);
        ps.setString(1, email);
        ps.setString(2, nombre);
        ps.setString(3, primerApellido);
        ps.setString(4, segundoApellido);
        ps.setString(5, password);
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;

    }

    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
    }
}
