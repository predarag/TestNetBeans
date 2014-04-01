
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
          
          <form name="forma" action="AktivacijaKorisnika">
          
             <%
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                    Statement st = con.createStatement();
              
                    ResultSet rs=st.executeQuery("SELECT * FROM korisnici");
                    
                    String status="";
                    
                    
                    out.println("<table >");
                    out.println("<tr><th>Ime</th><th>Prezime</th><th>Korisnicko ime</th><th>Tip</th><th>Status</th><th>Akcija</th></tr>");
                    while(rs.next()){
                        String ime=rs.getString("ime");
                        String prezime=rs.getString("prezime");
                        String kIme= rs.getString("kor_ime");
                        int odb= rs.getInt("odobren");
                        int prihvacena=rs.getInt("prihvacen");
                  
                        
                        if(odb==1) status="Odobren";
                        else       status="Nije odobren";
                        
                     
                        
                        if(odb==0 && prihvacena==0)
                        {
                            out.println("<tr><td>"+ime+"</td><td>"+prezime+"</td><td><input name='korIme' type='text' value='"+kIme+"' readonly></td>");
                            out.println("<td>");
                        
                        
                         out.println("<select name='tip'> "
                                 + "<option value='1'>Administrator</option>"
                                  +"<option value='2' selected>Sef labortatorije</option>"
                                 +"<option value='3'>Sef magacina</option>"
                            +"</select>");
                        
                           out.println("</td><td>"+status+"</td>");
                           
                           out.println("<td><input type='submit' value='aktiviraj'></td>");
                           
                        }
                        
                    }
                    out.println("</table>");
                    %>
                    
          </form>      
                    
                    
              <%
                    String poruka = (String) sesija.getAttribute("porukaKorisnikStatus");
                    if (poruka != null) {
                        out.print(poruka);
                    }
                    sesija.setAttribute("porukaKorisnikStatus", "");
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
              <li><a href="dodajSefa.jsp" >Dodaj sefa<br> %nbsp</a></li>
              <li><a href="dodavanjeKompanije.jsp" >Kompanija </a></li>
              <li><a href="korisnici.jsp" >Korisnici</a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
        
    </body>
</html>
