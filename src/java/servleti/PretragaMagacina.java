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


public class PretragaMagacina extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
     
        HttpSession sesija=request.getSession(true);
        
        String korIme=(String)sesija.getAttribute("korIme");
 
        Connection con=null;
        Statement st=null;
        
        try {
            
            int tipArtikla=Integer.parseInt(request.getParameter("tip"));
            String id     =request.getParameter("idLab");
            String naziv  =request.getParameter("naziv");
            String datumod=request.getParameter("datumod");
            String datumdo=request.getParameter("datumdo");
            
            out.println(datumod+" "+datumdo+"korisnik "+korIme+" tip "+tipArtikla+" id "+id);
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
        
            //Pretraga za osnovne artikle
            if(tipArtikla==1 && id==null &&  naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT  a.*, l.*, k.*, s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=1 AND a.idStatusa<>7";
                        
                        
                
               // out.println("prosao upit");
                st=con.createStatement();
                ResultSet rs=st.executeQuery(upit);
                out.println("Result: ");
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp='"+idKomp+"'>"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            }//Pretraga za osnovne artikle
            
            
          //Pretraga za pomocne artikle  
          else if(tipArtikla==2 && id==null && naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                out.println("prosao upit");
                String upit= "SELECT  a.*, l.*, k.*, s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=2 AND a.idStatusa<>7";
//                        
                 
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            }//Pretraga za pomocnu opremu
            
        
          
          //Pretraga za osnovnu opremu i naziv
             else if(tipArtikla==1 && id==null && !naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                
                
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=1 AND a.idStatusa<>7 AND a.naziv LIKE '"+naziv+"%'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            }//Pretraga za osnovne artikle i naziv
             
            //Pretraga za pomocne artikle i naziv
            else if(tipArtikla==2 && id==null && !naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                
               
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=2 AND a.idStatusa<>7 AND a.naziv LIKE '"+naziv+"%'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            }//Pretraga za pomocne artikle i naziv 
            
            
            //Pretraga za osnovne artikle i od jednog do drugog datuma
            else if(tipArtikla==1 && id==null && naziv.equals("") && !datumod.equals("") && !datumdo.equals("")){
            
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=1 AND a.idStatusa<>7 AND a.datumNabavke BETWEEN '"+datumod+"' AND '"+datumdo+"'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            }//Pretraga za osnovne artikle i od jednog do drugog datuma 
            
            
            //Pretraga za pomocne artikle i od jednog do drugog datuma
            else if(tipArtikla==2 && id==null && naziv.equals("") && !datumod.equals("") && !datumdo.equals("")){
            
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=2 AND a.idStatusa<>7 AND a.datumNabavke BETWEEN '"+datumod+"' AND '"+datumdo+"'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            }//Pretraga za pomocne artikle i od jednog do drugog datuma  
            
            
            
//            ------------------------------------------
//             Pretraga po tipu artikla i laboratoriji
//            ------------------------------------------
            
            //Pretraga osnovnih artikala po laboratoriji
           else if(tipArtikla==1 && id!=null && naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                
                int idLab=Integer.parseInt(id);
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=1 AND a.idStatusa<>7 AND a.idLab='"+idLab+"'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            } //Pretraga osnovnih artikala po laboratoriji  
            
            
             //Pretraga pomocnih artikala po laboratoriji
           else if(tipArtikla==2 && id!=null && naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                
                int idLab=Integer.parseInt(id);
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=2 AND a.idStatusa<>7 AND a.idLab='"+idLab+"'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            } //Pretraga pomocnih artikala po laboratoriji  
            
           
            //----------------------------------------------------------------
            
            
            //Pretraga osnovnih artikala po laboratoriji i nazivu
           else if(tipArtikla==1 && id!=null && !naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                
                int idLab=Integer.parseInt(id);
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=1 AND a.idStatusa<>7 AND a.idLab='"+idLab+"' AND a.naziv LIKE '"+naziv+"%'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            } //Pretraga osnovnih artikala po laboratoriji i nazivu  
            
            
             //Pretraga pomocnih artikala po laboratoriji i nazivu
           else if(tipArtikla==2 && id!=null && !naziv.equals("") && datumod.equals("") && datumdo.equals("")){
                
                int idLab=Integer.parseInt(id);
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=2 AND a.idStatusa<>7 AND a.idLab='"+idLab+"' AND a.naziv LIKE '"+naziv+"%'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            } //Pretraga pomocnih artikala po laboratoriji  i nazivu
           
            
            //-------------------------------------------------------------
            
            
             //Pretraga osnovnih artikala po laboratoriji i datumu
           else if(tipArtikla==1 && id!=null && naziv.equals("") && !datumod.equals("") && !datumdo.equals("")){
                
                int idLab=Integer.parseInt(id);
                 
                String upit= "SELECT a.*,l.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=1 AND a.idStatusa<>7 AND a.idLab='"+idLab+"' AND a.datumNabavke BETWEEN '"+datumod+"' AND '"+datumdo+"'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            } //Pretraga osnovnih artikala po laboratoriji i datumu  
            
            
             //Pretraga pomocnih artikala po laboratoriji i datumu
           else if(tipArtikla==2 && id!=null && naziv.equals("") && !datumod.equals("") && !datumdo.equals("")){
                
                int idLab=Integer.parseInt(id);
                 
                String upit= "SELECT a.*,l.*,p.*,k.*,s.* "
                        + "FROM artikal AS a INNER JOIN laboratorija AS l ON a.idLab = l.idLab "
                        + "INNER JOIN kompanija AS k ON a.idKomp = k.idKomp "
                        + "INNER JOIN sifrarnik AS s ON a.idStatusa = s.idStatusa "
                        + "WHERE a.vrsta=2 AND a.idStatusa<>7 AND a.idLab='"+idLab+"' AND a.datumNabavke BETWEEN '"+datumod+"' AND '"+datumdo+"'";

                
                
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
                            
                            int idKomp=rs.getInt("a.idKomp");
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            izlaz+="<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td><a href=prikazKompanije.jsp?idKomp="+idKomp+">"+kompanija+"</a></td><td>"+datumNabavke+"</td>";
                            izlaz+="<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+lab+"</td><td>"+tp+"</td><td>"+status+"</td>";
           
                            
                }
                izlaz+="</table>";
                
                sesija.setAttribute("porukaPretraga", izlaz);
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
              
            } //Pretraga pomocnih artikala po laboratoriji  i nazivu
            
        
             else
           {
                sesija.setAttribute("porukaPretraga", "Morate da izaberete neki od kriterijuma pretrage");
                RequestDispatcher rd = request.getRequestDispatcher("pretragaMagacin.jsp");
                rd.forward(request, response);
           }
           
        }catch(SQLException e) {e.toString();}
         catch(Exception e) {e.toString();}
        finally {
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
            Logger.getLogger(PretragaMagacina.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PretragaMagacina.class.getName()).log(Level.SEVERE, null, ex);
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
