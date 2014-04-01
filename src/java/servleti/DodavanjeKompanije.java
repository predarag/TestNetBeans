

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

/**
 *
 * @author user
 */
public class DodavanjeKompanije extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            String naziv=request.getParameter("naziv");
            String adresa=request.getParameter("adresa");
            String telefon=request.getParameter("telefon");
            String email=request.getParameter("email");
            int pib=Integer.parseInt(request.getParameter("pib"));
            String ime=request.getParameter("ime");
            
            HttpSession sesija = request.getSession(true);
            
            Connection con=null;
            Statement stmt=null ;
            
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery("SELECT * from kompanija WHERE pib='"+pib+"'");
                
                if(rs.next()){
                    sesija.setAttribute("porukaLab", "Kompanija sa tim poreskim brojem vec postoji!");
                    RequestDispatcher rd = request.getRequestDispatcher("dodavanjeKompanije.jsp");
                    rd.forward(request, response);
                }
                else{
                    String upit="INSERT INTO kompanija(naziv,adresa,telefon,email,pib,imeKontakta)" 
                                +"VALUES(?,?,?,?,?,?)";
                  
                    PreparedStatement ps=con.prepareStatement(upit);  
                    
                    ps.setString(1, naziv);
                    ps.setString(2, adresa);
                    ps.setString(3, telefon);
                    ps.setString(4, email);
                    ps.setInt(5, pib);
                    ps.setString(6, ime);
                    
                    int i=ps.executeUpdate();
                    
                    if(i==1)
                    {
                        sesija.setAttribute("porukaLab", "Uspesno ste dodali kompaniju!");
                        RequestDispatcher rd = request.getRequestDispatcher("dodavanjeKompanije.jsp");
                        rd.forward(request, response);
                    }
                    else
                    {
                          RequestDispatcher rd = request.getRequestDispatcher("dodavanjeKompanije.jsp");
                          rd.include(request, response);
                          out.println("<html><head>"); 
                          out.println("<script>alert('Došlo je do greške prilikom unosa kompanije!')</script>");
                          out.println("</head></html>");
                          out.close();
                    }
                }
                    
                
            } catch (Exception e) {
                out.println(e.toString());
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
            Logger.getLogger(DodavanjeKompanije.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DodavanjeKompanije.class.getName()).log(Level.SEVERE, null, ex);
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
