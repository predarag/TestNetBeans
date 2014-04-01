

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


public class NoviKorisnik extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String korIme = request.getParameter("username");
        String lozinka = request.getParameter("password");
        String adresa= request.getParameter("adresa");
        String telefon=request.getParameter("telefon");
        String email = request.getParameter("email");
        int tip = Integer.parseInt(request.getParameter("tip"));

        HttpSession sesija = request.getSession(true);
        Connection con=null;
        Statement stmt=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM korisnici WHERE kor_ime='" + korIme + "'");
            boolean uslov = true;
            while (rs.next()) {
                String username = rs.getString("kor_ime");
                if (username.equals(korIme)) {
                    uslov = false;
                    sesija.setAttribute("porukaNoviKorisnik", "Korisnicko ime vec postoji u bazi, pokusajte ponovo!");
                    RequestDispatcher rd = request.getRequestDispatcher("noviKorisnik.jsp");
                    rd.forward(request, response);
                }
            }
            if (uslov) {
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate("INSERT INTO korisnici(ime,prezime,kor_ime,lozinka,adresa,telefon,email,odobren,tip,prihvacen) VALUES('" + ime + "','" + prezime + "','" + korIme + "','" + lozinka + "','" + adresa + "','" + telefon + "','" + email + "','" +1+ "','" +tip + "','"+1+"')");

                sesija.setAttribute("porukaNoviKorisnik", "Uspesno ste uneli korisnika.");
                RequestDispatcher rd = request.getRequestDispatcher("noviKorisnik.jsp");
                rd.forward(request, response);
            }

        }//catch(SQLException sqlgreska){}
        catch (Exception e) {
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
            Logger.getLogger(NoviKorisnik.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NoviKorisnik.class.getName()).log(Level.SEVERE, null, ex);
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
