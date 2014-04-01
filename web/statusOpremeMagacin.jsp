
<%@page import="java.sql.*"%>
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
         <form name="forma" action="StatusOpremeMagacin" method="post" >
                <div class="red">
                <div class="label"> Izaberite artikal </div> <!-- kraj labele-->
                <div class="input">
                
                 <select name="evBroj" required>
                      <option value="" disabled selected></option>
                      <%
                       
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT a.*,l.*,s.* FROM artikal AS a,laboratorija AS l,sifrarnik AS s WHERE a.idLab=l.idLab AND a.idStatusa=s.idStatusa AND a.idStatusa<>7");

                            while (rs.next()) {
                                   out.println("<option value=" + rs.getInt("a.evBroj") + ">" + "Naziv artikla: " + rs.getString("naziv")+ " ( " + rs.getString("s.naziv")+")  " + " - Laboratorija: " + rs.getInt("l.idLab")+"</option>");
                            }
                        }catch(Exception e){out.println(e.toString());}
                     %>
                     
                </select>
                </div> <!-- kraj inputa -->
             </div> <!-- kraj rowa -->
             
             <div class="red">
                <div class="label"> Izaberite status opreme </div> <!-- kraj labele-->
                <div class="input">
                
                 <select name="status" required>
                      <option value="" disabled selected></option>
                      <%
                        try
                        {
                            
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                            Statement stmt1 = con1.createStatement();
                            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM sifrarnik");
                            while (rs1.next()) {
                                   out.println("<option value='" + rs1.getInt("idStatusa") + "'>"  + rs1.getString("naziv") + "</option>");
                            }
                        }catch(Exception e){out.println(e.toString());}
                     %>
                     
                </select>
                </div> <!-- kraj inputa -->
             </div> <!-- kraj rowa -->
                     
                <div class="posalji">
                    <input type="submit" id="posalji" name="submit" value="Azuriraj"/>
                    <input type="submit" id="posalji" name="reset" value="Odkazi"/>
                </div> 
            
            <%
                    String poruka = (String) sesija.getAttribute("porukaPromenaStatus");
                    if(poruka!=null){
                        out.print("<br>" + poruka);
                    }
                    sesija.setAttribute("porukaPromenaStatus", "");
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
