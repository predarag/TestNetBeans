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
                    
    document.forma.action="RegistracijaServlet";
}
