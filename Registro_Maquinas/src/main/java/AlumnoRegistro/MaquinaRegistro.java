package AlumnoRegistro;

import javax.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenin
 */
@WebServlet(name = "MaquinaRegistro", urlPatterns = {"/MaquinaRegistro"})
public class MaquinaRegistro extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        String id = request.getParameter("Id");
        String marca = request.getParameter("Marca");
        String modelo = request.getParameter("Modelo");
        String laboratorio = request.getParameter("Laboratorio");
        String boleta = (String) session.getAttribute("usuario");
        int resultado = 0;
        PrintWriter out = response.getWriter();
        String mensajeError="";
        
        
        try{
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MaquinaRegistro</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h2>La marca es: "+marca+"</h2>");
//            out.println("<h2>El Modelo es: "+modelo+" </h2>");
//            out.println("<h2>El Laboratorio es: "+laboratorio+"</h2>");
//            out.println("<h2>El que la registro fue: "+maquina+"</h2>");
//            out.println("<h2>La fecha fue en: "+fregistro+"</h2>");
            resultado = consultaRegistroMaquina(id,marca,modelo,laboratorio,boleta);
            if(resultado==1){
                out.println("<h1>El registro de la maquina fue satisfactorio</h1>");
            }
        }catch(SQLException e){
            resultado=0;
            mensajeError = e.getMessage();
            if(e.getErrorCode() == 1062){
                out.println("<h1>El registro de la maquina no fue satisfactoria ya que existe un registro con esta maquina.</h1>");
            }else{
                out.println("<h1>El registro no fue satisfactorio, codigo de error:"+e.getErrorCode()+": "+mensajeError+"</h1>");
            }
        }
        finally{
            out.println("<div id='Volveralmenu5'>");
            out.println("<button onclick=\"window.location='./inicio.jsp'\">Volver al Menú</buttton");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private int consultaRegistroMaquina(String id,
            String marca,
            String modelo,
            String laboratorio,
            String boleta) throws SQLException {
        
            String isql ="insert into maquina_registro(id,marca,modelo,laboratorio,boleta,fecharegistro)" + "values(?,?,?,?,?,now())";
            PreparedStatement ps = con.prepareStatement(isql);
            ps.setString(1, id);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setString(4, laboratorio);
            ps.setLong(5, Long.parseLong(boleta));
            
            int resultado = ps.executeUpdate();
            return resultado;
    
    }
    
     public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
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
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MaquinaRegistro.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(MaquinaRegistro.class.getName()).log(Level.SEVERE, null, ex);
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
