
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
           <li><a href="azuriranjeLab.jsp" class="button">Azuriraj </br> laboratoriju</a></li>
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
          
            <div id="form">
 
            <form name="forma" action="DodavanjeSefa" method="post" >
  
        	
            <div class="red">
            <div class="label"> Odaberite laboratoriju </div> <!-- kraj labele-->
            <div class="input">
                <select name="lab">
                    <option value="" disabled selected></option>
                    <%
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                            Statement stmt = con.createStatement();                            
                            ResultSet rs1 = stmt.executeQuery("select * from laboratorija");

                            while (rs1.next()) {
                                            
                                   out.println("<option value='" + rs1.getString("idLab") + "'>"  + rs1.getString("idLab") + " - " + rs1.getString("naziv") + "</option>");
                            }
                     %>
                    
                </select>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Adresa </div> <!-- kraj labele-->
            <div class="input">
                <select name="idKor">
                    <option value="" disabled selected></option>
                    <%
                           
                            Statement stmt2 = con.createStatement();
                            ResultSet rs2 = stmt.executeQuery("select * from korisnici");

                            while (rs2.next()) {
                                 if (rs2.getInt("tip") == 2)         
                                   out.println("<option value='" + rs2.getString("kor_ime") + "'>"  + rs2.getString("ime") + " " + rs2.getString("prezime") + "</option>");
                            }
                     %>
                    
                </select>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="posalji">
                <input type="submit" id="posalji" name="submit" value="Dodaj"/>
                
            </div> 
            
            <%
                    String poruka = (String) sesija.getAttribute("porukaDodelaSefa");
                    if(poruka!=null){
                        out.print("<br>" + poruka);
                    }
                    sesija.setAttribute("porukaDodelaSefa", "");
            %>
            
            </form>
          </div>

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
            <li><a href="azuriranjeLab.jsp" >Azuriraj laboratoriju</a></li>
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
