

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


public class DodavanjeArtiklaMagacin extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        			
        int vrsta=Integer.parseInt(request.getParameter("vrsta"));
        String naziv=request.getParameter("naziv");
        String model=request.getParameter("model");
        String datumNabavke=request.getParameter("datumNabavke");
        String garantniRok=request.getParameter("garantniRok");
        int    idKomp=Integer.parseInt(request.getParameter("idKomp"));
        int    vredDin=Integer.parseInt(request.getParameter("vredDin"));
        int    vredEur=Integer.parseInt(request.getParameter("vredEur"));
        int    idLab=Integer.parseInt(request.getParameter("idLab"));
        int    idStatusa=Integer.parseInt(request.getParameter("idStatusa"));		
        int    kolicina=Integer.parseInt(request.getParameter("kolicina"));  			

        HttpSession sesija=request.getSession(true);
        
        out.println("asdf");
        
        Connection con=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            ps=con.prepareStatement("insert into artikal(vrsta,naziv,model,datumNabavke,garantniRok,idKomp,vredDIn,vredEur,idLab,idStatusa,kolicina) values(?,?,?,?,?,?,?,?,?,?,?)"); 
            
            ps.setInt(1,vrsta);  
            ps.setString(2,naziv);  
            ps.setString(3,model);  
            ps.setString(4,datumNabavke);
            ps.setString(5,garantniRok);
            ps.setInt(6, idKomp);
            ps.setDouble(7,vredDin);
            ps.setDouble(8,vredEur);
            ps.setInt(9, idLab);
            ps.setInt(10, idStatusa);
            ps.setInt(11, kolicina);
            
            int i=ps.executeUpdate();  
            
            if(i==1) {
                 sesija.setAttribute("porukaArtikalMagacin", "Uspesno ste dodali artikal!");
                 RequestDispatcher rd = request.getRequestDispatcher("dodavanjeArtiklaMagacin.jsp");
                 rd.forward(request, response);
            }
            else{
                 sesija.setAttribute("porukaArtikalMagacin", "Doslo je do greske, pokusajte ponovo!");
                 RequestDispatcher rd = request.getRequestDispatcher("dodavanjeArtiklaMagacin.jsp");
                 rd.forward(request, response);
            }
            
        
        }catch(Exception e) {out.println(e.toString());}
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
            Logger.getLogger(DodavanjeArtiklaMagacin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DodavanjeArtiklaMagacin.class.getName()).log(Level.SEVERE, null, ex);
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
