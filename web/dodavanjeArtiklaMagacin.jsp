
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sef magacina</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <link rel="stylesheet" href="css/kontakt.css" />
        <link rel="stylesheet" href="css/jquery-ui.css">
        <script src="js/provera.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/jquery-1.9.1.js"></script> 
        <script src="js/jquery-ui.js"></script>
        <script>
            $(function() {
            $( "#picker1" ).datepicker();
            });

            $(function() {
            $( "#picker2" ).datepicker();
            });
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
           <%
                    String poruka = (String) sesija.getAttribute("porukaArtikalMagacin");
                    if(poruka!=null){
                        out.print("<br>" + poruka);
                    }
                    sesija.setAttribute("porukaArtikalMagacin", "");
                %>
              
            </br>    
          
          <div id="form">
            
            <form name="forma" action="DodavanjeArtiklaMagacin" method="post" >
               
                <div class="red">
                <div class="label"> Vrsta artikla </div> <!-- kraj labele-->
                <div class="input">
                    <select name="vrsta" required>
                        <option value="1">Osnovna oprema</option>
                        <option value="2">Pomocna oprema</option>
                    </select>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Naziv artikla </div> <!-- kraj labele-->
                <div class="input">
                    <input name="naziv" type="text"  class="detail" id="fullname" required/>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Model artikla </div> <!-- kraj labele-->
                <div class="input">
                    <input name="model" type="text"  class="detail" id="fullname" required/>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Datum nabavke </div> <!-- kraj labele-->
                <div class="input">
                    <input name="datumNabavke" type="text" placeholder="yyyy-mm-dd" class="detail" id="picker1" required/>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Garantni rok </div> <!-- kraj labele-->
                <div class="input">
                    <input name="garantniRok" type="text" placeholder="yyyy-mm-dd" class="detail" id="picker2" required/>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Kompanija </div> <!-- kraj labele-->
                <div class="input">
                    <select name="idKomp" required>
                        <option value="" disabled selected></option>
                        <% 
                            try
                            {
                             Class.forName("com.mysql.jdbc.Driver");
                             Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                             
                             Statement st=con.createStatement();
                             
                             ResultSet rs=st.executeQuery("SELECT * FROM kompanija");
                             while(rs.next())
                             {
                                out.println("<option value='"+rs.getInt("idKomp")+"'>"+rs.getString("naziv")+"</option>");
                             }
                             
                            }catch(Exception e) {out.println(e.toString());}
                        
                        %>
                        
                    </select>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Vrednost u dinarim </div> <!-- kraj labele-->
                <div class="input">
                    <input name="vredDin" type="text"  class="detail" id="fullname" required/>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Vrednost u evrim </div> <!-- kraj labele-->
                <div class="input">
                    <input name="vredEur" type="text"  class="detail" id="fullname" required/>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Laboratorija </div> <!-- kraj labele-->
                <div class="input">
                    <select name="idLab" required>
                        <option value="" disabled selected></option>
                        <% 
                            try
                            {
                             Class.forName("com.mysql.jdbc.Driver");
                             Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                             
                             Statement st=con.createStatement();
                             
                             ResultSet rs=st.executeQuery("SELECT * FROM laboratorija");
                             while(rs.next())
                             {
                                out.println("<option value='" + rs.getString("idLab") + "'>"  + rs.getString("idLab") + " - " + rs.getString("naziv") + "</option>");
                             }
                             
                            }catch(Exception e) {out.println(e.toString());}
                            
                        
                        %>
                    </select>
                    
                <div class="red">
                <div class="label"> Status opreme </div> <!-- kraj labele-->
                <div class="input">
                    <select name="idStatusa" required>
                        <option value="" disabled selected></option>
                        <% 
                            try
                            {
                             Class.forName("com.mysql.jdbc.Driver");
                             Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
                             
                             Statement st=con.createStatement();
                             
                             ResultSet rs=st.executeQuery("SELECT * FROM sifrarnik");
                             while(rs.next())
                             {
                                out.println("<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>");
                             }
                             
                            }catch(Exception e) {out.println(e.toString());}
                        
                        %>
                        
                    </select>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                    
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="red">
                <div class="label"> Kolicina </div> <!-- kraj labele-->
                <div class="input">
                    <input name="kolicina" type="text"  class="detail" id="fullname" required>
                </div> <!-- kraj inputa -->
                </div> <!-- kraj rowa -->
                
                <div class="posalji">
                    <input type="submit" id="posalji" name="submit" value="Dodaj"/>
                    <input type="submit" id="posalji" name="reset" value="Odkazi"/>
                
                </div> 
                
                
            </form>
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
