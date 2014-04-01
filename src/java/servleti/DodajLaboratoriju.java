

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
public class DodajLaboratoriju extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession(true);
        
        int idLab=Integer.parseInt(request.getParameter("idLab"));
        String naziv=request.getParameter("naziv");
        double kvadratura=Double.parseDouble(request.getParameter("kvadratura"));
        int brojMesta=Integer.parseInt(request.getParameter("brPred"));
        String korIme=request.getParameter("sefLab");
        
        Connection con=null;
        Statement stmt=null;
        PreparedStatement ps=null;
        
        try
        {
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
             stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM laboratorija WHERE idLab='"+idLab+"'");
             
             if(rs.next())
             {
                    sesija.setAttribute("porukaLab", "Laboratorija sa tim ID-em vec postoji u bazi!");
                    RequestDispatcher rd = request.getRequestDispatcher("dodavanjeLaboratorije.jsp");
                    rd.forward(request, response);
             }
       
                
                else
                {
                    String upit="INSERT INTO laboratorija(idLab,naziv,kvadratura,brojMesta)" 
                                +"VALUES(?,?,?,?)";
                   
                    ps=con.prepareStatement(upit);  
                 
                    
                    ps.setInt(1,idLab);  
                    ps.setString(2,naziv);  
                    ps.setDouble(3,kvadratura);  
                    ps.setInt(4,brojMesta); 
                    
                    PreparedStatement ps1=con.prepareStatement("INSERT INTO pomocna(kor_ime,idLab) VALUES(?,?)");
                    
                    ps1.setString(1, korIme);
                    ps1.setInt(2, idLab);
                    
                                       
                
                    int i=ps.executeUpdate();
                    int j=ps1.executeUpdate();
                    if(i==1 && j==1){
                       
                        sesija.setAttribute("porukaLab", "Uspesno ste dodali laboratoriju!");
                        RequestDispatcher rd = request.getRequestDispatcher("dodavanjeLaboratorije.jsp");
                        rd.forward(request, response);
                    }
                
                    else
                    { 
                             RequestDispatcher r = request.getRequestDispatcher("dodavanjeLaboratorije.jsp");
                             r.include(request, response);
                             out.println("<html><head>"); 
                             out.println("<script>alert('Došlo je do greške prilikom unosa laboratorije!')</script>");
                             out.println("</head></html>");
                             out.close();
                    }
                 }
             
        
            
        }catch(Exception e) {out.println(e.toString());}
        finally{
            if(ps!=null) ps.close();;
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
            Logger.getLogger(DodajLaboratoriju.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DodajLaboratoriju.class.getName()).log(Level.SEVERE, null, ex);
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
