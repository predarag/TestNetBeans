
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dodavanje kompanija</title>
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
          <h3><a>Nova kompanija</a></h3>
            <div id="formWrap">
       
        <div id="form">
 
            <form name="forma" action="DodavanjeKompanije" method="post" >
  
        	
            <div class="red">
            <div class="label"> Naziv kompanije </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="naziv" class="detail" id="fullname" required/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Adresa </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="adresa" class="detail" id="fullname" required/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
             
            <div class="red">
            <div class="label"> Telefon </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="telefon" class="detail" id="fullname" required/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Email </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="email" class="detail" id="fullname" required/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> PIB </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="pib" class="detail" id="fullname" required/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Ime kontakt osobe </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="ime" class="detail" id="fullname" required/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="posalji">
                <input type="submit" id="posalji" name="submit" value="Dodaj"/>
                <input type="reset" id="posalji" name="reset" value="Poništi" />
            </div> 
            
            <%
                    String poruka = (String) sesija.getAttribute("porukaLab");
                    if(poruka!=null){
                        out.print("<br>" + poruka);
                    }
                    sesija.setAttribute("porukaLab", "");
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
