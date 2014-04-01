
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 9]><html class="lt-ie10" lang="en" > <![endif]-->
<html class="no-js" lang="en" data-useragent="Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ProjekatIP</title>
    
    <meta name="author" content="Tasic Predrag" />
    
    
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/kontakt.css" />
    <script src="js/provera.js"></script>
    <script src="js/modernizr.js"></script>
  </head>
  <body>
    

<!-- Nav Bar -->

  <div class="row">
    <div class="large-12 columns">
      <div class="nav-bar right">
       
      </div>
      <h1><small>Prijava na sistem</small></h1>
      <hr />
    </div>
  </div>

  <!-- End Nav -->


  <!-- Main Page Content and Sidebar -->

  <div class="row">

    <!-- Main Blog Content -->
    <div class="large-9 columns" role="content">

        <!-- REGISTRACIJA -->
      <article>

        <h3><a >Registracija</a></h3>
        
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
           
            <div class="posalji">
                <input type="submit" id="posalji" name="submit" value="Registruj se"" onclick="provera()"/>
                <input type="reset" id="posalji" name="reset" value="Ponisti" />
            </div> 
            </form>
            
                   
      </article>

        <!-- REGISTRACIJA -->

      

    </div>

    <!-- End Main Content -->


    <!-- Sidebar -->

    <aside class="large-3 columns">

      <div class="panel">
        <h5>Uloguj se</h5>
        <ul class="button-group">
           <li class="button">
               
               <form name="login" action="LogovanjeServlet" method="post">
                   Ime:<input type="text" name="ime"/>
                   Šifra: <input type="password" name="sifra"/>
                   <input type="submit" value="Uloguj se"/>
              </form>  
               
           </li>
        </ul>
        <br>
        <ul class="side-nav">
        <li><a href="promenaLozinkeIndex.jsp">Promena lozinke</a></li>
      </ul>
      </div>

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
        
      </div>
    </div>
  </footer>
   <!-- <script src="jquery.js"></script>-->
    
  </body>
</html>
