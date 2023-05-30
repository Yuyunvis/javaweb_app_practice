//if ($("body").attr("username") == ""){
////	alert("eceow");
//	var ele = document.querySelectorAll('[id="vai"]');
//	for (i = 0; i < ele.length; i++){
//		ele[i].innerHTML="Effettua il login per acquistare";
//		var citta=$("body").attr("citta");
//		var checkin=$("body").attr("checkin");
//		var checkout=$("body").attr("checkout");
//		var topologia=$("body").attr("tiplogia");
//		window.location.href="login.jsp?cercaHotel=true&citta="+citta+"&dataInizioHotel="+checkin+"&dataFineHotel="+checkout+"&tipologia="+tipologia;
//	}
//}


var nom;
var prenotazioneSelezionata;

function selectHotel(nome){
	document.getElementById("fh5co-tours").style.display = "block";
	document.getElementById("fh5co-blog-section").style.display = "none";

	nom = nome;
	var appBanners = document.getElementsByClassName(nom);
	for (var i = 0; i < appBanners.length; i ++) {
	    appBanners[i].style.display = 'block';
	}
}

/*var elements = document.getElementsByClassName("button confirm");
for(var i=0; i<elements.length; i++) { 
	elements[i].style.pointerEvents = "none";
}
*/


function riepilogoPrenotazione(prenotazione) {
	prenotazioneSelezionata = prenotazione;
	document.getElementById("selezionaCamere").style.display = "none";
	document.getElementById("confermaCamera").style.display = "block";

	$('#riepilogo-nome').text(prenotazioneSelezionata.nome);
	$('#riepilogo-cognome').text(prenotazioneSelezionata.cognome);
	$('#riepilogo-data').text(prenotazioneSelezionata.nascita);
	$('#riepilogo-hotel').text(prenotazioneSelezionata.hotel);
	$('#riepilogo-giorni').text('dal ' + prenotazioneSelezionata.checkin + ' al ' + prenotazioneSelezionata.checkout);


}

function confermaPrenotazione() {
	document.getElementById("confCamera").disabled = true;
	if ($("body").attr("username") == "") {
		let carrelloPrima = $.cookie("carrello");
		carrelloPrima.camere.push(prenotazioneSelezionata);
		$.cookie("carrello",carrelloPrima);
		$.ajax({
			type: "POST",
			url: "InserisciPrenotazioniCarrelloCookie",
			datatype: "json",
			data: JSON.stringify($.cookie("carrello")),
			success: function (){
				window.location.href = "carrello.jsp";
			}
		});
	} else {
		$.ajax({
			type: "POST",
			url: "InserisciHotelCarrello",
			datatype: "json",
			data: JSON.stringify(prenotazioneSelezionata),
			success: function (){
				window.location.href = "carrello.jsp";
			}
		});
	}
}

function selezionaCamera(codiceCamera, prezzo, tipologia, checkin, checkout) {
	$.cookie.json = true;
	let nome = $('#nome_' + codiceCamera).val();
	let cognome = $('#cognome_' + codiceCamera).val();
	let nascita = $('#nascita_' + codiceCamera).val();
	
	var check = new RegExp(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
	var nomeValido = check.test(nome);
	var cognomeValido = check.test(cognome);
	
	let checkDate = new Date(nascita);
	let max = new Date('2001-12-31');

	if (max.getTime() - checkDate.getTime() < 0){
		checkDate=false
	}
	
	if (nomeValido!=false && cognomeValido!=false && checkDate!=false && nascita!=""){
		document.getElementById("conf_"+codiceCamera).disabled = true;
		var d1 = new Date(checkin);
		var d2 = new Date(checkout);
		let data_nascita = new Date(nascita);
		var timeDiff = d2.getTime() - d1.getTime();
		var DaysDiff = timeDiff / (1000 * 3600 * 24);
		var prenotazione = {
			codiceCamera : codiceCamera,
			hotel : nom,
			prezzo : prezzo*DaysDiff,
			tipologia : tipologia,
			checkin : d1.toLocaleDateString(),
			checkout : d2.toLocaleDateString(),
			nome : nome,
			cognome : cognome,
			nascita : data_nascita.toLocaleDateString()
		};

		riepilogoPrenotazione(prenotazione);
	}
	else{
		document.getElementById("conf_"+codiceCamera).setCustomValidity("Completa o correggi i campi per procedere");
		document.getElementById("conf_"+codiceCamera).reportValidity();
//		var elements = ["nome_" + codiceCamera, "cognome_" + codiceCamera, "nascita_" + codiceCamera]
//		if (nome==""){
//			elements.push("nome_" + codiceCamera);
////			document.getElementById("nome_" + codiceCamera).setCustomValidity("Campo mancante");
////			document.getElementById("nome_" + codiceCamera).reportValidity();
//		}
//		if (cognome==""){
//			elements.push("cognome_" + codiceCamera);
////			document.getElementById("cognome_" + codiceCamera).setCustomValidity("Campo mancante");
////			document.getElementById("cognome_" + codiceCamera).reportValidity();
//		}
//		if (nascita==""){
//			elements.push("nascita_" + codiceCamera);
////			document.getElementById("nascita_" + codiceCamera).setCustomValidity("Campo mancante");
////			document.getElementById("nascita_" + codiceCamera).reportValidity();
//		}
//		for (i = 0; i < elements.length; i++) {
//			document.getElementById(elements[i]).setCustomValidity("Campo mancante");
//			document.getElementById(elements[i]).reportValidity();
//		}
	}
}

