
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <script src="js/modernizr.js"></script>
        
    </head>
    <body>
        <%
                        HttpSession sesija = request.getSession(true);
                        String korisnickoIme = (String) sesija.getAttribute("korIme");
        %>
        
    <div class="row">
    <div class="large-12 columns">
      <div class="nav-bar right">
       <ul class="button-group">
            <li><a href="noviKorisnik.jsp" class="button">Dodaj </br> korisnika</a></li>
           <li><a href="aktivacijaKorisnika.jsp" class="button">Aktivacija </br> &nbsp;</a></li>
           <li><a href="dodavanjeLaboratorije.jsp" class="button">Dodaj </br> laboratoriju</a></li>
           <li><a href="#" class="button">Azuriraj </br> laboratoriju</a></li>
           <li><a href="dodajSefa.jsp" class="button">Dodaj sefa </br> &nbsp; </a></li>
           <li><a href="dodavanjeKompanije.jsp" class="button">Kompanija </br> &nbsp; </br></a></li>
           <li><a href="korisnici.jsp" class="button">Korisnici </br> &nbsp;</a></li>
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
                    
                   
                    
                    if (request.getParameter("korIme") != null && request.getParameter("akcija") != null) {
                        String korIme = request.getParameter("korIme");
                        
                        String akcija = request.getParameter("akcija");
                         
                        if (akcija.equals("odobri")) {
                            st.executeUpdate("UPDATE korisnici SET odobren=1 WHERE kor_ime='" + korIme+"'");
                            String izlaz = "Korisnik: " + korIme + " je aktiviran!";
                            sesija.setAttribute("porukaKorisnik", izlaz);
                        }
                        
                        else if (akcija.equals("obrisi")) {
                            st.executeUpdate("UPDATE korisnici SET odobren=0 WHERE kor_ime='" + korIme+"'");
                            String izlaz = "Korisnik: "+ korIme + " je deaktiviran!";
                            sesija.setAttribute("porukaKorisnik", izlaz);
                        }
                    }
                    
                    
                    ResultSet rs=st.executeQuery("SELECT * FROM korisnici");
                    
                    String status="";
                    
                    
                    out.println("<table>");
                    out.println("<tr><th>Ime</th><th>Prezime</th><th>Korisnicko ime</th><th>Tip</th><th>Akcija</th></tr>");
                    while(rs.next()){
                        String ime=rs.getString("ime");
                        String prezime=rs.getString("prezime");
                        String kIme= rs.getString("kor_ime");
                        int odb= rs.getInt("odobren");
                        int prihvacen=rs.getInt("prihvacen");
                        int tip=rs.getInt("tip");
                  
                        String tipKorisnika="";
                        
                        if(tip==1)
                            tipKorisnika="Administrator";
                         else if(tip==2)
                            tipKorisnika="Sef laboratorije";
                         else if(tip==3)
                            tipKorisnika="Sef magacina";
                        
                       
                        
                        if(prihvacen==1)
                        {
                            out.println("<tr><td>"+ime+"</td><td>"+prezime+"</td><td>"+kIme+"</td>");
                            out.println("<td>"+tipKorisnika+"</td>");
                      
                    
                           if(odb==0) 
                                out.print("<td><a href=korisnici.jsp?akcija=odobri&korIme=" + kIme + ">Aktiviraj korisnika</a></td>");
                           else if(odb==1)
                                out.print("<td><a href=korisnici.jsp?akcija=obrisi&korIme=" + kIme + ">Deaktiviraj korisnika</a></td>");
                        
                        
                           
                       }
                        
                    }
                   }catch(Exception e) {out.println(e.toString());}
                    out.println("</table>");
                    
                    String poruka = (String) sesija.getAttribute("porukaKorisnik");
                    if (poruka != null) {
                        out.print(poruka);
                    }
                    sesija.setAttribute("porukaKorisnik", "");
              %>

      </article>

     

      

    </div>

    <!-- End Main Content -->
            
    
    <!-- Sidebar -->

    <aside class="large-3 columns">

      <h5>Ulogovani ste kao: <%=korisnickoIme%></h5>
      <ul class="side-nav">
        <li><a href="promenaLozinkeAdmin.jsp">Promena lozinke</a></li>
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
            <li><a href="noviKorisnik.jsp" >Dodaj korisnika</a></li>
            <li><a href="aktivacijaKorisnika.jsp" ">Aktivacija</a></li>
            <li><a href="dodavanjeLaboratorije.jsp" >Dodaj laboratoriju</a></li>
            <li><a href="#" >Azuriraj laboratoriju</a></li>
            <li><a href="dodajSefa.jsp" >Dodaj sefa</a></li>
            <li><a href="dodavanjeKompanije.jsp" >Kompanija </a></li>
            <li><a href="korisnici.jsp" >Korisnici</a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
        
    </body>
</html>
