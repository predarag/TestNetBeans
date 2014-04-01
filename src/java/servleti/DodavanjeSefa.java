

package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


public class DodavanjeSefa extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int idLab=Integer.parseInt(request.getParameter("lab"));
        String korIme=request.getParameter("idKor");
        
        
        HttpSession sesija=request.getSession(true);
        
        Connection con=null;
        Statement stmt=null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            
           stmt = con.createStatement();
            
            ResultSet rs=  stmt.executeQuery("SELECT kor_ime,idLab FROM pomocna WHERE kor_ime='"+korIme+"' and idLab='"+idLab+"'");
            
            if(rs.next()){
                    sesija.setAttribute("porukaDodelaSefa", "Izabrani korisnik je vec sef u izabranoj laboratoriji!");
                    RequestDispatcher rd = request.getRequestDispatcher("dodajSefa.jsp");
                    rd.forward(request, response);
            }
            else
            {
                String upit="INSERT INTO pomocna(kor_ime,idLab)" 
                                +"VALUES(?,?)";
                
                PreparedStatement ps=con.prepareStatement(upit);  
             
                 ps.setString(1,korIme);  
                 ps.setInt(2,idLab);
                 
                 int i=ps.executeUpdate();
                 
                 if(i==1){
                     sesija.setAttribute("porukaDodelaSefa", "Uspesno ste dodali sefa u laboratoriju!");
                     RequestDispatcher rd = request.getRequestDispatcher("dodajSefa.jsp");
                     rd.forward(request, response);
                 }
            }
            
        } catch (Exception e) {out.println(e.toString());}
        finally{
            if(stmt!=null) stmt.close();
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
            Logger.getLogger(DodavanjeSefa.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DodavanjeSefa.class.getName()).log(Level.SEVERE, null, ex);
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
