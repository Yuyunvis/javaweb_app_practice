/* script per validazione form registrazione con jQuery e Ajax */
/* script per validazione form registrazione con jQuery e Ajax */
var password = document.getElementById("password"), confirm_password = document
		.getElementById("conferma_password");

function validatePassword() {
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Passwords Don't Match");
	} else {
		confirm_password.setCustomValidity('');
	}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

function validaRegistrazione() {
	var nome = $("input[type=text][name=input_nome]").val();
    var cognome = $("input[type=text][name=input_cognome]").val();
	var data = new Date($("input[type=date][name=input_dataNascita]").val());
	day = data.getDate();
	month = data.getMonth() + 1;
	year = data.getFullYear();
	var date = year+"-"+month+"-"+day;
	var user = $("input[type=text][name=input_username]").val();
	var pws = $("input[type=password][name=input_password]").val();
	var pws2 = $ ("#conferma_password").val();
	var email = $("input[type=email][name=input_email]").val();
	var telefono = $("input[type=text][name=input_telefono]").val();
	
	var email_reg_exp = "/^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/";
;
	//Effettua il controllo sul campo NOME
	if ((nome == "") || (nome === undefined)) {
	alert("Il campo Nome è obbligatorio.");
	document.modulo.nome.focus();
	return false;
	}
	
	//Effettua il controllo sul campo COGNOME
	else if ((cognome == "") || (cognome === undefined)) {
	alert("Il campo Cognome è obbligatorio.");
	document.modulo.cognome.focus();
	return false;
	}
	
	//Effettua il controllo sul campo NICKNAME
	else if ((user == "") || (user === undefined)) {
	alert("Il campo Nickname è obbligatorio.");
	document.modulo.user.focus();
	return false;
	}
	
	//Effettua il controllo sul campo DATA DI NASCITA
	else if (year>2001){
		alert ("La data inserita non è valida")
		document.modulo.data.focus();
		return false;
	}
	
	//Effettua il controllo sul campo PASSWORD
	else if ((pws === "") || (pws === undefined)) {
	alert("Il campo Password è obbligatorio.");
	document.modulo.pws.focus();
	return false;
	}
	//Effettua il controllo sul campo CONFERMA PASSWORD
	else if ((pws2 === "") || (pws2 === undefined)) {
	alert("Il campo Conferma password è obbligatorio.");
	document.modulo.pws2.focus();
	return false;
	}
	
	//Verifica l'uguaglianza tra i campi PASSWORD e CONFERMA PASSWORD
	else if (pws !== pws2) {
	alert("La password confermata è diversa da quella scelta, controllare.");
	alert(psw+psw2);
	document.modulo.pws2.value = "";
	document.modulo.pws2.focus();
	return false;
	}

	//Effettua il controllo sul campo TELEFONO
	else if (/*(isNaN(telefono)) ||*/ (telefono == "") || (telefono === undefined)) {
	alert("Il campo Telefono è numerico ed obbligatorio.");
	document.modulo.telefono.value = "";
	document.modulo.telefono.focus();
	return false;
	}
	else if (/*!email_reg_exp.test(email) ||*/ (email == "") || (email === undefined)) {
	alert("Inserire un indirizzo email corretto.");
	document.modulo.email.select();
	return false;
	}

	//INVIA IL MODULO
	else {
	$("#modulo").attr('action', 'RegistrationServlet');
	$("#modulo").submit();
	//$(location).attr('href', 'localhost:8080/AgenziaViaggi/ConfirmedReg.jsp');

	}
}







/* script per la conferma della password */
/*function controlloPassword (){

 var pass1 = $("input[type=password][name=input_password]").val();
 var passconf = $("input[type=password][name=conferma_password]").val();
 
   if(pass1!=passconf)
    alert("Attenzione, le due password non coincidono"); 
}*/

