
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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
import javax.xml.crypto.Data;

/**
 *
 * @author user
 */
public class Pretraga extends HttpServlet {

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int tipArtikla=Integer.parseInt(request.getParameter("tip"));
        String naziv=request.getParameter("naziv");
        String datumod=request.getParameter("datumod");
        String datumdo=request.getParameter("datumdo");
        
        
        HttpSession sesija=request.getSession(true);
        
        String korIme=(String)sesija.getAttribute("korIme");
        
        out.println(datumod+" "+datumdo+" "+korIme+" "+tipArtikla);
        
        Connection con=null;
        Statement st=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
            out.println("usao u try");
           
        // pretraga osnovnih artikala
            if(tipArtikla==1 && naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a,laboratorija AS l,pomocna as P, kompanija AS k, sifrarnik AS s "
                        + "WHERE p.idLab=l.idLab AND l.idLab=a.idLab AND a.idKomp=k.idKomp AND a.idStatusa=s.idStatusa AND "
                        + "a.vrsta=1 AND a.idStatusa<>7 AND p.kor_ime='"+korIme+"'";
                
                out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                String izlaz="<table>";
                       izlaz+="<tr><th>Naziv</th><th>Model</th><th>Gar rok</th><th>Kompanija</th><th>Datum nabavke</th><th>Vred din</th><th>Vred eur</th><th>Lab</th><th>Vrsta</th><th>Status</th></tr>" ; 
                while(rs.next()){
                            String naz = rs.getString("a.naziv");
                            String model = rs.getString("a.model");
                            Date garantniRok = rs.getDate("a.garantniRok");
                            String kompanija = rs.getString("k.naziv");
                            Date datumNabavke = rs.getDate("a.datumNabavke");
                            int vredDin = rs.getInt("a.vredDin");
                            int vredEur = rs.getInt("a.vredEur");
                            String lab = rs.getString("l.naziv");
                            int tip = rs.getInt("a.vrsta");
                            String status = rs.getString("s.naziv");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+kompanija+"</td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
              
            }
            
            
            //Pretraga za pomocne artikle
            if(tipArtikla==2 && naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a,laboratorija AS l,pomocna as P, kompanija AS k, sifrarnik AS s "
                        + "WHERE p.idLab=l.idLab AND l.idLab=a.idLab AND a.idKomp=k.idKomp AND a.idStatusa=s.idStatusa AND"
                        + " a.vrsta=2 AND a.idStatusa<>7<>7 AND p.kor_ime='"+korIme+"'";
                
                out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                String izlaz="<table>";
                       izlaz+="<tr><th>Naziv</th><th>Model</th><th>Gar rok</th><th>Kompanija</th><th>Datum nabavke</th><th>Vred din</th><th>Vred eur</th><th>Lab</th><th>Vrsta</th><th>Status</th></tr>" ; 
                while(rs.next()){
                            String naz = rs.getString("a.naziv");
                            String model = rs.getString("a.model");
                            Date garantniRok = rs.getDate("a.garantniRok");
                            String kompanija = rs.getString("k.naziv");
                            Date datumNabavke = rs.getDate("a.datumNabavke");
                            int vredDin = rs.getInt("a.vredDin");
                            int vredEur = rs.getInt("a.vredEur");
                            String lab = rs.getString("l.naziv");
                            int tip = rs.getInt("a.vrsta");
                            String status = rs.getString("s.naziv");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+kompanija+"</td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
              
            }
            
            //Pretraga po nazivu za osnovne artikle
            if(tipArtikla==1 && !naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a,laboratorija AS l,pomocna as P, kompanija AS k, sifrarnik AS s "
                        + "WHERE p.idLab=l.idLab AND l.idLab=a.idLab AND a.idKomp=k.idKomp AND a.idStatusa=s.idStatusa AND "
                        + "a.vrsta=1 AND a.idStatusa<>7<>7 AND a.naziv LIKE '" +naziv + "%' AND p.kor_ime='"+korIme+"'";
                
                out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                String izlaz="<table>";
                       izlaz+="<tr><th>Naziv</th><th>Model</th><th>Gar rok</th><th>Kompanija</th><th>Datum nabavke</th><th>Vred din</th><th>Vred eur</th><th>Lab</th><th>Vrsta</th><th>Status</th></tr>" ; 
                while(rs.next()){
                            String naz = rs.getString("a.naziv");
                            String model = rs.getString("a.model");
                            Date garantniRok = rs.getDate("a.garantniRok");
                            String kompanija = rs.getString("k.naziv");
                            Date datumNabavke = rs.getDate("a.datumNabavke");
                            int vredDin = rs.getInt("a.vredDin");
                            int vredEur = rs.getInt("a.vredEur");
                            String lab = rs.getString("l.naziv");
                            int tip = rs.getInt("a.vrsta");
                            String status = rs.getString("s.naziv");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+kompanija+"</td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
              
            }
            
            //Pretraga po nazivu za pomocne artikle
            if(tipArtikla==2 && !naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a,laboratorija AS l,pomocna as P, kompanija AS k, sifrarnik AS s "
                        + "WHERE p.idLab=l.idLab AND l.idLab=a.idLab AND a.idKomp=k.idKomp AND a.idStatusa=s.idStatusa AND "
                        + "a.vrsta=2 AND a.idStatusa<>7<>7 AND a.naziv LIKE '" +naziv + "%' AND p.kor_ime='"+korIme+"'";
                
                out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                String izlaz="<table>";
                       izlaz+="<tr><th>Naziv</th><th>Model</th><th>Gar rok</th><th>Kompanija</th><th>Datum nabavke</th><th>Vred din</th><th>Vred eur</th><th>Lab</th><th>Vrsta</th><th>Status</th></tr>" ; 
                while(rs.next()){
                            String naz = rs.getString("a.naziv");
                            String model = rs.getString("a.model");
                            Date garantniRok = rs.getDate("a.garantniRok");
                            String kompanija = rs.getString("k.naziv");
                            Date datumNabavke = rs.getDate("a.datumNabavke");
                            int vredDin = rs.getInt("a.vredDin");
                            int vredEur = rs.getInt("a.vredEur");
                            String lab = rs.getString("l.naziv");
                            int tip = rs.getInt("a.vrsta");
                            String status = rs.getString("s.naziv");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+kompanija+"</td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
              
            }
            
            //Pretraga po datumu za osnovne artikle
            
            if(tipArtikla==1 && naziv.equals("") && !datumod.equals("") && !datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a,laboratorija AS l,pomocna as P, kompanija AS k, sifrarnik AS s "
                        + "WHERE p.idLab=l.idLab AND l.idLab=a.idLab AND a.idKomp=k.idKomp AND a.idStatusa=s.idStatusa AND "
                        + "a.vrsta=1 AND a.idStatusa<>7 AND datumNabavke BETWEEN'" +datumod + "' AND '" +datumdo + "'  AND p.kor_ime='"+korIme+"'";
                
                out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                String izlaz="<table>";
                       izlaz+="<tr><th>Naziv</th><th>Model</th><th>Gar rok</th><th>Kompanija</th><th>Datum nabavke</th><th>Vred din</th><th>Vred eur</th><th>Lab</th><th>Vrsta</th><th>Status</th></tr>" ; 
                while(rs.next()){
                            String naz = rs.getString("a.naziv");
                            String model = rs.getString("a.model");
                            Date garantniRok = rs.getDate("a.garantniRok");
                            String kompanija = rs.getString("k.naziv");
                            Date datumNabavke = rs.getDate("a.datumNabavke");
                            int vredDin = rs.getInt("a.vredDin");
                            int vredEur = rs.getInt("a.vredEur");
                            String lab = rs.getString("l.naziv");
                            int tip = rs.getInt("a.vrsta");
                            String status = rs.getString("s.naziv");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+kompanija+"</td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
              
            }
            
            
            //Pretraga po datumu za pomocne artikle
            if(tipArtikla==2 && naziv.equals("") && !datumod.equals("") && !datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a,laboratorija AS l,pomocna as P, kompanija AS k, sifrarnik AS s "
                        + "WHERE p.idLab=l.idLab AND l.idLab=a.idLab AND a.idKomp=k.idKomp AND a.idStatusa=s.idStatusa AND "
                        + "a.vrsta=2 AND a.idStatusa<>7 AND datumNabavke BETWEEN'" +datumod + "' AND '" +datumdo + "'  AND p.kor_ime='"+korIme+"'";
                
                out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                String izlaz="<table>";
                       izlaz+="<tr><th>Naziv</th><th>Model</th><th>Gar rok</th><th>Kompanija</th><th>Datum nabavke</th><th>Vred din</th><th>Vred eur</th><th>Lab</th><th>Vrsta</th><th>Status</th></tr>" ; 
                while(rs.next()){
                            String naz = rs.getString("a.naziv");
                            String model = rs.getString("a.model");
                            Date garantniRok = rs.getDate("a.garantniRok");
                            String kompanija = rs.getString("k.naziv");
                            Date datumNabavke = rs.getDate("a.datumNabavke");
                            int vredDin = rs.getInt("a.vredDin");
                            int vredEur = rs.getInt("a.vredEur");
                            String lab = rs.getString("l.naziv");
                            int tip = rs.getInt("a.vrsta");
                            String status = rs.getString("s.naziv");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+kompanija+"</td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
              
            }
            
            else
            {
                sesija.setAttribute("porukaPretraga", "Morate izabrati neku opciju za pretrazivanje!");
                RequestDispatcher rd = request.getRequestDispatcher("pretragaArtikla.jsp");
                rd.forward(request, response);
            }
            
            
        } catch (Exception e) {out.println(e.toString());}
        finally{
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
            Logger.getLogger(Pretraga.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Pretraga.class.getName()).log(Level.SEVERE, null, ex);
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
