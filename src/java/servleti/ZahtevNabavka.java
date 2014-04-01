

package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class ZahtevNabavka extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int vrsta=Integer.parseInt(request.getParameter("vrsta"));
        String naziv=request.getParameter("naziv");
        int kolicina=Integer.parseInt(request.getParameter("kolicina"));
        int idLab=Integer.parseInt(request.getParameter("idLab"));
        int odobren=0;
        
        HttpSession sesija=request.getSession(true);
        Connection con=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            ps=con.prepareStatement("insert into zahtevNabavka(tip,naziv,kolicina,idLab,odobren) values(?,?,?,?,?)"); 
            
            ps.setInt(1,vrsta);  
            ps.setString(2,naziv);  
            ps.setInt(3,kolicina);  
            ps.setInt(4,idLab);
            ps.setInt(5,odobren);
            
          
            int i=ps.executeUpdate();  
            
            if(i==1) {
                 sesija.setAttribute("porukaZahtevNabavka", "Uspesno ste uputili zahtev za nabavku!");
                 RequestDispatcher rd = request.getRequestDispatcher("zahtevNabavka.jsp");
                 rd.forward(request, response);
            }
            else{
                 sesija.setAttribute("porukaZahtevNabavka", "Doslo je do greske, pokusajte ponovo!");
                 RequestDispatcher rd = request.getRequestDispatcher("zahtevNabavka.jsp");
                 rd.forward(request, response);
            }
            
        } catch (Exception e) {out.println(e.toString());}
        finally{
            if(con!=null) con.close();
            if(ps!=null) ps.close();
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
            Logger.getLogger(ZahtevNabavka.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ZahtevNabavka.class.getName()).log(Level.SEVERE, null, ex);
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
