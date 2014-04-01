
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class OtpisOpreme extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        int evBroj=Integer.parseInt(request.getParameter("evBroj"));
        
        
        
        Connection con=null;
        Statement st=null;
        
        HttpSession sesija=request.getSession(true);
        
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            st = con.createStatement();
            
            int i = st.executeUpdate("UPDATE artikal SET idStatusa=7  WHERE evBroj='" + evBroj + "'");
            
             if(i==1){
                 
                 sesija.setAttribute("porukaOdpisOpreme", "Uspesno ste odpisali artikal, vise se nece pojavljivati ni u jednoj pretrazi!");
                 RequestDispatcher rd = request.getRequestDispatcher("otpisOpreme.jsp");
                 rd.forward(request, response);
             }
             else{
                 sesija.setAttribute("porukaOdpisOpreme", "Doslo je do greske, pokusajte ponovo!");
                 RequestDispatcher rd = request.getRequestDispatcher("otpisOpreme.jsp");
                 rd.forward(request, response);
             }
            
        } catch (Exception e) {out.println(e.toString());}
         finally{
            if(st!=null) st.close();
            if(con!=null) con.close();
            out.close();
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
            Logger.getLogger(OtpisOpreme.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OtpisOpreme.class.getName()).log(Level.SEVERE, null, ex);
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
