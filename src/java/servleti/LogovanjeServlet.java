
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
public class LogovanjeServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession(true);
        String korIme=request.getParameter("ime");
        String loz=request.getParameter("sifra");
        
        Connection con=null;
        Statement stmt=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");     
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");

           stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM korisnici WHERE kor_ime='"+korIme+"' and lozinka='"+loz+"'");
            boolean log  = false;
            
        while(rs.next())
        {
                int tip = rs.getInt(8);
                int odobren = rs.getInt(9);
                int prihvacen = rs.getInt(10);
                
                if(tip==1 && odobren==1){
                    sesija.setAttribute("korIme", korIme);
                    RequestDispatcher rd = request.getRequestDispatcher("aktivacijaKorisnika.jsp");
                    rd.forward(request, response);
                    log = true;
                }
                else if(tip==2  && odobren==1)
                {
                    sesija.setAttribute("korIme", korIme);
                    RequestDispatcher rd = request.getRequestDispatcher("logovanjeLab.jsp");
                    rd.forward(request, response);
                    log = true;
                }
                else if(tip==3  && odobren==1)
                {
                    sesija.setAttribute("korIme", korIme);
                    RequestDispatcher rd = request.getRequestDispatcher("logovanjeMagacin.jsp");
                    rd.forward(request, response);
                    log = true;
                }
                else if(odobren==0 && prihvacen==0)
                {
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                         out.println("<html><head>"); 
                         out.println("<script>alert('Vas nalog jos nije odobren, morate sacekati da vam administrator odobri nalog')</script>");
                         out.println("</head></html>");
                    log = true;
                }
                else if(odobren==0 && prihvacen==1){
                     RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                         out.println("<html><head>"); 
                         out.println("<script>alert('Vas nalog vise nije aktivan !')</script>");
                         out.println("</head></html>");
                    log = true;
                }
     
        }
        if(!log){
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                         out.println("<html><head>"); 
                         out.println("<script>alert('Pogresno uneko korisnicko ime ili sifra !')</script>");
                         out.println("</head></html>");
        }

        
        }catch(Exception e){
            out.println("Greska: " + e);
        }
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
            Logger.getLogger(LogovanjeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LogovanjeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
