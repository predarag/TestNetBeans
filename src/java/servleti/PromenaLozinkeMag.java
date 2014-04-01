

package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 *
 * @author user
 */
public class PromenaLozinkeMag extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String korisnicko=request.getParameter("korIme");
        String loz=request.getParameter("lozinka");
        String novaLoz=request.getParameter("lozinka1");
        String novaLoz2=request.getParameter("lozinka2");
        
        HttpSession sesija=request.getSession(true);
        
        Connection con=null;
        Statement stmt=null;
        
        if(novaLoz.equals(novaLoz2)){
            
        try
        {
            
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM korisnici WHERE kor_ime='"+korisnicko+"' and lozinka='"+loz+"'");
            boolean log=false;
            
            while(rs.next()){
                String korIme=rs.getString("kor_ime");
                String password=rs.getString("lozinka");
                
                if(korIme.equals(korisnicko) && password.equals(loz))
                {
                    Statement stmt2=con.createStatement();
                    
                    stmt2.executeUpdate("UPDATE korisnici SET lozinka='"+novaLoz+"' WHERE kor_ime='"+korisnicko+"' and lozinka='"+loz+"'");
                    
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                    out.println("<html><head>"); 
                    out.println("<script>alert('Uspesno ste promenili lozinku, ulogujte se ponovo sa novom lozinkom !')</script>");
                    out.println("</head></html>");
                    
                    log = true;
                }
            }
            if(!log){
                 sesija.setAttribute("porukaPromenaLozinke", "Pogresno unetko korisnicko ili lozinka, pokusajte ponovo!");
                 RequestDispatcher rd = request.getRequestDispatcher("promenaLozinkeMaf.jsp");
                 rd.forward(request, response);
                
            }
            
            
        }catch(Exception e){out.println(e.toString());}
        finally{
            if(stmt!=null) stmt.close();
            if(con!=null) con.close();
            out.close();
        }
        
        } else{
            sesija.setAttribute("porukaPromenaLozinke", "Doslo je do greske, pokusajte ponovo!");
            RequestDispatcher rd = request.getRequestDispatcher("promenaLozinkeMag.jsp");
            rd.forward(request, response);
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
            Logger.getLogger(PromenaLozinkeMag.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PromenaLozinkeMag.class.getName()).log(Level.SEVERE, null, ex);
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
