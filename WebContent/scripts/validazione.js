
function validateEmail(email){
const mailformat = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
if(email.value.match(mailformat)) {
return true; 
} else {
       alert("email non valida");
return false; }
}

function validaCodiceFiscale(cf) {
    const cfformat = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/i;
    if(cf.value.match(cfformat)) {
return true; 
} else {
       alert("codice fiscale non valido");
return false; }
}

function validaCellulare(cellulare) {
    const cellformat = /^\+d{10}\$/;
    if(cellulare.value.match(cellformat)) {
return true; 
} else {
       alert("numero di telefono non valido");
return false; }
}




