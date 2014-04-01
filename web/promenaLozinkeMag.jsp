
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promena lozinke</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <link rel="stylesheet" href="css/kontakt.css" />
        <script src="js/provera.js"></script>
        <script src="js/modernizr.js"></script>
        
        <script> 
            function provera(){
              uzorakLozinka = /\w{8,}/;
              
              var korisniIme=document.forma.korIme.value;
              var lozinka = document.forma.lozinka1.value;
              var potvrdaLoz=document.forma.lozinka2.value;
              
              
              if(korisniIme===""){
                  alert("Greska!!! Niste uneli korisničko ime!");
                  return 0;
            
              }
              
              if(lozinka===""){
                  alert("Greska!!! Niste uneli lozinku!");
                  return 0;
              }
              
              if (uzorakLozinka.test(lozinka)===false){
                    alert("Greska!!! Lozinka nema minimalno 8 karaktera!");       
                    return 0;               
              }
              
              if(lozinka!==potvrdaLoz){
                  alert("Greska!!! Niste dobro ponovili lozinku, pokusajte ponovo.");
                  return 0;
              }
              
              document.forma.action="PromenaLozinkeMag";
              
            }
        </script>
        
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
          <h3><a>Promena lozinke</a></h3>
            <div id="formWrap">
       
        <div id="form">
 
            <form name="forma"  method="post" >
  
        	
            <div class="red">
            <div class="label"> Korisničko ime </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="korIme" class="detail" id="fullname"/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Lozinka </div> <!-- kraj labele-->
            <div class="input">
                <input name="lozinka" type="password"  class="detail" id="fullname"/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Nova lozinka </div> <!-- kraj labele-->
            <div class="input">
                <input name="lozinka1" type="password"  class="detail" id="fullname"/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Ponovite lozinku </div> <!-- kraj labele-->
            <div class="input">
                <input name="lozinka2" type="password"  class="detail" id="fullname"/>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="posalji">
                <input type="submit" id="posalji" name="submit" value="Promeni"" onclick="provera()"/>
                <input type="reset" id="posalji" name="reset" value="Poništi" />
            </div> 
          </form>
            
            <br>
            <%  
              
              String poruka = (String) sesija.getAttribute("porukaPromenaLozinke");
              if (poruka != null) {
                   out.print(poruka);
               }
               sesija.setAttribute("porukaPromenaLozinke", "");
           %>  

      </article>

    

    </div>

    <!-- End Main Content -->


    <!-- Sidebar -->

    <aside class="large-3 columns">

      <h5>Ulogovani ste kao: <%=korisnickoIme%></h5>
      <ul class="side-nav">
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
