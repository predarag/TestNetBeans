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



public class RegistracijaServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            
           String ime = request.getParameter("ime");
           String prezime = request.getParameter("prezime");
           String korIme = request.getParameter("username");
           String lozinka = request.getParameter("password");
           String adresa = request.getParameter("adresa");
           String tel= request.getParameter("telefon");
           String email = request.getParameter("email");
           
           Statement st=null;
           Statement stmt2=null;
           Connection con=null;
           
           out.println("Servlettt\n\n");
           
        try {  
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException e){out.println(e.toString());}
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            st=con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM korisnici");
            petlja:
            while(rs.next()){
                String username = rs.getString("kor_ime");
                
                if(username.equals(korIme)){
                    
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                    out.println("<html><head>"); 
                    out.println("<script>alert('Korisnicko ime vec postoji u bazi!')</script>");
                    out.println("</head></html>");
                    
                }
                else
                {
                    
                    stmt2 = con.createStatement();
                    stmt2.executeUpdate("insert into korisnici(ime,prezime,kor_ime,lozinka,adresa,telefon,email,odobren,prihvacen) values('"+ime+"','"+prezime+"','"+korIme+"','"+lozinka+"','"+adresa+"','"+tel+"','"+email+"','"+0+"','"+0+"')");
                   
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                    out.println("<html><head>"); 
                    out.println("<script>alert('Uspesno ste se registrovali, vas nalog ce biti aktivan kad ga administrator odobri!')</script>");
                    out.println("</head></html>");
                    break petlja;
                }
            }
       
        }catch(SQLException e) {out.println(e.toString());}
        finally {
            if(st!=null) st.close();
            if(stmt2!=null) st.close();
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
            Logger.getLogger(RegistracijaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistracijaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
