
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sef laboratorije</title>
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
           <li><a href="logovanjeLab.jsp" class="button">Pocetna</a></li>
           <li><a href="pretragaArtikla.jsp" class="button">Pretraga artikla</a></li>
           <li><a href="zahtevNabavka.jsp" class="button">Zahtev za nabavku</a></li>
           <li><a href="statusOpreme.jsp" class="button">Status opreme</a></li>
         
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
          <div class="panel">
              <h3> <a>Dobrodosli vi ste Šef laboratorije</a></h3></br></br>

              <h3>Vase laboratoriju su: </h3>
              
              <ul>
                  
                  <%
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                        Statement st=con.createStatement();
                        
                        ResultSet rs=st.executeQuery("SELECT idLab,naziv FROM laboratorija WHERE idLab IN( SELECT idLab FROM pomocna WHERE kor_ime='"+korisnickoIme+"')");
                        
                        while(rs.next()){
                            int idLab=rs.getInt(1);
                            String naziv=rs.getString(2);
                            
                            out.println("<li>"+idLab+" "+naziv+"</li>");
                        }
                  
                  %>
                  
              </ul>
              
          </div>

      </article>

    

    </div>

    <!-- End Main Content -->


    <!-- Sidebar -->

    <aside class="large-3 columns">

      <h5>Ulogovani ste kao: <%=korisnickoIme%></h5>
      <ul class="side-nav">
        <li><a href="promenaLozinkeLab.jsp">Promena lozinke</a></li>
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
              <li><a href="logovanjeLab.jsp" >Pocetna</a></li>
              <li><a href="pretragaArtikla.jsp" >Pretraga artikla</a></li>
              <li><a href="zahtevNabavka.jsp" >Zahtev za nabavku</a></li>
              <li><a href="statusOpreme.jsp" >Status opreme</a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
        
    </body>
</html>
