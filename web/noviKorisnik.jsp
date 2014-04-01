
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novi korisnik</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <script src="js/modernizr.js"></script>
        
        <script>
            function provera(){
                 
                    uzorakKor = /^\w{6,}$/;
                    uzorakLozinka = /\w{8,}/;
                    uzorakMail = /^(\w+|([a-z]+\.[a-z]+))@\w+\.([a-z]{2,4}|[a-z]{2,4}\.[a-z]{2,4})$/;

                    var ime=document.forma.ime.value;
                    var prezime=document.forma.prezime.value;
                    var korIme=document.forma.username.value;
                    var lozinka = document.forma.password.value;
                    var potvrdaLoz=document.forma.passwordPonovo.value;
                    var mail=document.forma.email.value;


                    if(uzorakKor.test(korIme)===false){alert("Korisnicko ime mora imati barem 6 karaktera!");return 0;}

                    if (uzorakLozinka.test(lozinka)===false){
                         alert("Greska!!! Lozinka nema minimalno 8 karaktera!");       
                         return 0;               
                    }

                     if(lozinka!=potvrdaLoz){
                        alert("Greska! Niste dobro ponovili lozinku, pokusajte ponovo.");
                        return 0;
                     }

                     if(ime===""){
                        alert("Niste uneli ime!!!");
                        return 0;
                     }
                     if(prezime===""){
                        alert("Niste uneli prezime!!!");
                        return 0;
                     }

                    if(uzorakMail.test(mail)===false){
                       alert("Greska!!! Niste dobro uneli mejl! Mejl mora sadrzati @ i domen!");
                       return false;
                    }

                    document.forma.action="NoviKorisnik";
              }

        </script>
        
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
           <h3><a >Dodavanje korisnika</a></h3>
        
        <div id="formWrap">
       
        <div id="form">
 
            <form name="forma" method="post" >
  
        	
            <div class="red">
            <div class="label"> Ime </div> <!-- kraj labele-->
            <div class="input">
                <input type="text" name="ime" class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Prezime </div> <!-- kraj labele-->
            <div class="input">
                <input name="prezime" type="text"  class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label"> Korisničko ime </div> <!-- kraj labele-->
            <div class="input">
                <input  name="username" type="text" class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label">Lozinka</div> <!-- kraj labele-->
            <div class="input">
                <input name="password" type="password" class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label">Ponovite lozinku</div> <!-- kraj labele-->
            <div class="input">
                <input name="passwordPonovo" type="password" class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label">Adresa (ulica i broj, grad)</div> <!-- kraj labele-->
            <div class="input">
                <input name="adresa" type="text" class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label">Telefon</div> <!-- kraj labele-->
            <div class="input">
                <input name="telefon" type="text" class="detail" id="fullname" value">
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            <div class="red">
            <div class="label">Email</div> <!-- kraj labele-->
            <div class="input">
            <input name="email" type="text" class="detail" id="email" >
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
           
            
            <div class="red">
            <div class="label">Tip korisnika</div> <!-- kraj labele-->
            <div class="input">
                <select name="tip">
                    <option value="1">Administrator</option>
                    <option value="2">Šef laboratorije</option>
                    <option value="3">Šef magacina</option>
                </select>
            </div> <!-- kraj inputa -->
            </div> <!-- kraj rowa -->
            
            
            <div class="posalji">
                <input type="submit" id="posalji" name="submit" value="Dodaj korisnika"" onclick="provera()"/>
                <input type="reset" id="posalji" name="reset" value="Poništi" />
            </div> 
            </form>
            
            <%
                    String poruka = (String) sesija.getAttribute("porukaNoviKorisnik");
                    if(poruka!=null){
                        out.print("<br>" + poruka);
                    }
                    sesija.setAttribute("porukaNoviKorisnik", "");
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
            li><a href="noviKorisnik.jsp" >Dodaj korisnika</a></li>
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
