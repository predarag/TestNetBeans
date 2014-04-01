
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
          <div class="panel">
             <h3> <a>Dobrodosli vi ste Šef magacina</a></h3>
          </div>

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
