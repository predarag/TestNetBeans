
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sef magacina</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <link rel="stylesheet" href="css/kontakt.css" />
        <script src="js/provera.js"></script>
        <script src="js/modernizr.js"></script>
    </head>
    <%
                        HttpSession sesija = request.getSession(true);
                        String korisnickoIme = (String) sesija.getAttribute("korIme");
     %>
        
    <div class="row">
    <div class="large-12 columns">
      <div class="nav-bar right">
       <ul class="button-group">
           <li><a href="logovanjeMagacin.jsp" class="button">Pocetna</a></li>
           <li><a href="pretragaMagacin.jsp" class="button">Pretraga</a></li>
           <li><a href="dodavanjeArtiklaMagacin.jsp" class="button">Dodavanje artikla</a></li>
           <li><a href="prihvatanjeZahtevaMag.jsp" class="button">Pregled zahteva</a></li>
           <li><a href="otpisOpreme.jsp" class="button">Otpis opreme</a></li>
           <li><a href="statusOpremeMagacin.jsp" class="button">Status opreme</a></li>
         
        </ul>
      </div>
      <hr />
    </div>
  </div>

  <!-- End Nav -->


  <!-- Main Page Content and Sidebar -->

  <div class="row">

    <!-- Main Blog Content -->
    <div class="large-9 columns" role="content">

      <article>
          
          <%
                  try
                  {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                    Statement st = con.createStatement();
                    
                     if (request.getParameter("idKomp") != null){
                    
                         String id=request.getParameter("idKomp");
                       
                         ResultSet rs=st.executeQuery("SELECT * FROM kompanija WHERE idKomp="+id);
                         out.println("<h3>Podaci o kompaniji</h3></br>");
                         if(rs.next()){
                             out.println("<table>");
                             out.println("<tr><th>Naziv</th><th>Adresa</th><th>Telefon</th><th>Email</th><th>PIB</th><th>Kontakt osoba</th></tr>");
                             out.println("<td>"+rs.getString(2)+"</td>");
                             out.println("<td>"+rs.getString(3)+"</td>");
                             out.println("<td>"+rs.getString(4)+"</td>");
                             out.println("<td>"+rs.getString(5)+"</td>");
                             out.println("<td>"+rs.getInt(6)+"</td>");
                             out.println("<td>"+rs.getString(7)+"</td>");
                             out.println("</table>");
                         }
                         else{
                             out.println("<a>Doslo je do greske!</a>");
                         }
                         
                         out.println("</br></br></br>");
                         
                         out.println("<h3>Oprema nabavljena u poslednjih godinu dana</h3></br>");
                         
                         Date d=new Date();
                         
                         String trenutniDatum=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();
                         String pretGod=(d.getYear()+1900-1)+"-"+(d.getMonth()+1)+"-"+d.getDate();
                         
                         Statement st2 = con.createStatement();
                         
                         ResultSet rs2=st2.executeQuery("SELECT * FROM artikal WHERE idKomp="+request.getParameter("idKomp")+" AND datumNabavke BETWEEN '"+pretGod+"' AND '"+trenutniDatum+"'");
                         
                          out.println("<table>");
                       out.println("<tr><th>Naziv</th><th>Model</th><th>Datum nabavke</th><th>Gar rok</th><th>Vred din</th><th>Vred eur</th><th>Status</th><th>Kolicina</th></tr>"); 
                         while(rs2.next()){
                            int tip = rs2.getInt(2);
                            String naz = rs2.getString(3);
                            String model = rs2.getString(4);
                            Date garantniRok = rs2.getDate(5);
                            Date datumNabavke = rs2.getDate(6);
                            double vredDin = rs2.getDouble(8);
                            double vredEur = rs2.getDouble(9);
                            int kolicina=rs2.getInt(12);
                            
                            
                            String tp="";
                            if(tip==1) tp="Osnovna";
                            else if(tip==2) tp="Pomocna";
                            
                            out.println("<tr><td>"+naz+"</td><td>"+model+"</td><td>"+garantniRok+"</td><td>"+datumNabavke+"</td>");
                            out.println("<td>"+vredDin+"</td><td>"+vredEur+"</td><td>"+tp+"</td><td>"+kolicina+"</td></tr>");
                         }
                         
                         out.println("</table>");
                     }
                  }catch(Exception e) {out.println(e.toString());}
           %>

      </article>

    

    </div>

    <!-- End Main Content -->


    <!-- Sidebar -->

    <aside class="large-3 columns">

      <h5>Ulogovani ste kao: <%=korisnickoIme%></h5>
      <ul class="side-nav">
        <li><a href="promenaLozinkeMag.jsp">Promena lozinke</a></li>
        <li><a href="logout.jsp">Izloguj se</a></li>
      </ul>

    </aside>

    <!-- End Sidebar -->
  </div>

  <!-- End Main Content and Sidebar -->


  <!-- Footer -->

  <footer class="row">
    <div class="large-12 columns">
      <hr />
      <div class="row">
        <div class="large-6 columns">
          <p>&copy; Copyright Tasić Predrag.</p>
        </div>
        <div class="large-6 columns">
          <ul class="inline-list right">
             <li><a href="logovanjeMagacin.jsp" >Pocetna</a></li>
           <li><a href="pretragaMagacin.jsp" >Pretraga</a></li>
           <li><a href="dodavanjeArtiklaMagacin.jsp" >Dodavanje artikla</a></li>
           <li><a href="prihvatanjeZahtevaMag.jsp">Pregled zahteva</a></li>
           <li><a href="otpisOpreme.jsp">Otpis opreme</a></li>
           <li><a href="statusOpremeMagacin.jsp">Status opreme</a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
        
    </body>
</html>
