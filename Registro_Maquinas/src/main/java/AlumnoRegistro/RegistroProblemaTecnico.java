package AlumnoRegistro;

import javax.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;

/**
 *
 * @author lenin
 */
public class RegistroProblemaTecnico extends HttpServlet {

    private Connection con;

    public void init(ServletConfig scg) throws javax.servlet.ServletException {
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String idMaquina = request.getParameter("maquinaid");
        String problematica = request.getParameter("Describirproblema");
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
            resultado = registroProblema(idMaquina,problematica);
            if (resultado == 1) {
                out.println("<h1>El registro fue satisfactorio</h1>");
            }    
        } catch (SQLException e) {
          resultado = 0;
          mensajeError = e.getMessage();
          
          out.println("<h1>El registro no fue satisfactorio, codigo de error:"+e.getErrorCode()+": "+mensajeError+"</h1>");
          
          
        }
        finally
        {
            
            out.println("<button onclick=\"window.location='./inicio.jsp'\">Regresar</button>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
    private int registroProblema(String idMaquina,
            String problematica)throws SQLException{ 
            String isql = "insert into registro_problema_tecnico(id_maquina,problematica,fecharegistro)"+"values(?,?,now())";
            PreparedStatement ps = con.prepareStatement(isql);
            ps.setString(1, idMaquina);
            ps.setString(2, problematica);
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
