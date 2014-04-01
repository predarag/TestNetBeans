
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class AktivacijaKorisnika extends HttpServlet {

    //adsasdasdasd
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        String korIme=request.getParameter("korIme");
        String tip=request.getParameter("tip");
        
        
        HttpSession sesija=request.getSession(true);
        
        Connection con=null;
        Statement st=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            st = con.createStatement();
            
            int i= st.executeUpdate("UPDATE korisnici SET tip='"+tip+"' ,odobren=1,prihvacen=1 WHERE kor_ime='"+korIme+"'");
            
            if(i==1){
                 sesija.setAttribute("porukaKorisnikStatus", "Korisnik: " + korIme + " je odobren! ");
                 RequestDispatcher rd = request.getRequestDispatcher("aktivacijaKorisnika.jsp");
                 rd.forward(request, response);
            }
            else
            {
                 sesija.setAttribute("porukaKorisnikStatus", "Doslo je do greske");
                 RequestDispatcher rd = request.getRequestDispatcher("aktivacijaKorisnika.jsp");
                 rd.forward(request, response);
            }
            
            
            
            
        } catch (Exception e) {
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
            Logger.getLogger(AktivacijaKorisnika.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AktivacijaKorisnika.class.getName()).log(Level.SEVERE, null, ex);
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
