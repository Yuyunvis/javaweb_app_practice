
var voloAndata;
function funzione(id,partenza,destinazione,dataA,prezzo,numeroAdulti,numeroBambini,classe){
	
	voloAndata="?Id="+id+"&Partenza="+partenza+"&Destinazione="+destinazione+"&DataA="+dataA+"&Prezzo="+prezzo+"&NumeroAdulti="+numeroAdulti+"&NumeroBambini="+numeroBambini+"&Classe="+classe;
}

function funzione1(id,partenza,destinazione,dataA,prezzo,numeroAdulti,numeroBambini,classe){
	var volo="?Id="+id+"&Partenza="+partenza+"&Destinazione="+destinazione+"&DataA="+dataA+"&Prezzo="+prezzo+"&NumeroAdulti="+numeroAdulti+"&NumeroBambini="+numeroBambini+"&Classe="+classe;
	window.location.href="compilaBiglietti.jsp"+volo;
}

function ole(id,partenza,destinazione,dataA,prezzo){
	window.location.href="compilaBiglietti.jsp"+voloAndata+"&Id1="+id+"&Partenza1="+partenza+"&Destinazione1="+destinazione+"&DataA1="+dataA+"&Prezzo1="+prezzo;
}


function prenotazione(id,nome,cognome,dataNascita, partenza, destinazione, dataA, username, prezzo){
	var prenotazione = {
			id : id,
			nome : nome,
			cognome : cognome,
			dataNascita : dataNascita,
			partenza : partenza,
			destinazione : destinazione,
			dataA : dataA,
			username : username,
			prezzo : prezzo,
		};
	return prenotazione;
}

function Prenotazione(id,nome,cognome,dataNascita, partenza, destinazione, dataA,username,prezzo){
	this.id = id;
	this.nome = nome,
	this.cognome = cognome,
	this.dataNascita = dataNascita,
	this.partenza = partenza,
	this.destinazione = destinazione,
	this.dataA = dataA,
	this.username = username,
	this.prezzo = prezzo;
}

myList = new Array();
myList1 = new Array();
myListJSON = new Array();

var numAdulti=0;
var numBambini=0;
document.getElementById("date").setAttribute("max", "2006-12-31");
var p=0, de=0, da=0;

document.getElementById("fine1").style.pointerEvents = "none";

document.getElementById("name").addEventListener("change", function() {
	if (document.getElementById("name").value != ""){
		p=1;
	}
	else{
		p=0;
		document.getElementById("fine1").style.pointerEvents = "none";
		document.getElementById("fine1").value = "Inserisci tutti i parametri";
	}
	if (p==1 && da==1 && de==1){
		document.getElementById("fine1").style.pointerEvents = "auto";
		document.getElementById("fine1").value = "Continua";
	}
});
document.getElementById("surname").addEventListener("change", function() {
	if (document.getElementById("surname").value != ""){
		de=1;
	}
	else{
		de=0;
		document.getElementById("fine1").style.pointerEvents = "none";
		document.getElementById("fine1").value = "Inserisci tutti i parametri";
	}
	if (p==1 && da==1 && de==1){
		document.getElementById("fine1").style.pointerEvents = "auto";
		document.getElementById("fine1").value = "Continua";
	}
});
document.getElementById("date").addEventListener("change", function() {
	if (document.getElementById("date").value != ""){
		document.getElementById("date").style.color = "#F78536";
		da=1;
	}
	else{
		document.getElementById("date").style.color = "#ccb4b4";
		da=0;
		document.getElementById("fine1").style.pointerEvents = "none";
		document.getElementById("fine1").value = "Inserisci tutti i parametri";
	}
	if (p==1 && da==1 && de==1){
		document.getElementById("fine1").style.pointerEvents = "auto";
		document.getElementById("fine1").value = "Continua";
	}
});

var partenza=$("body").attr("partenza");
var destinazione=$("body").attr("destinazione");
var dataA = new Date($("body").attr("dataA")).toLocaleString();
var dataB = new Date($("body").attr("dataB")).toLocaleString();
dataA = dataA.substr(0,(dataA.length - 3)).replace(',',', ore:');
dataB = dataB.substr(0,(dataB.length - 3)).replace(',',', ore:');

function prendiPrenotazioneA(id,username){
	document.getElementById("fine1").style.pointerEvents = "none";
	p=0, de=0, da=0;
	var nome = $("input[type=text][name=input_nome]").val();
    var cognome = $("input[type=text][name=input_cognome]").val();
	var data = new Date($("input[type=date][name=input_dataNascita]").val());
	let formatted_date =  data.toLocaleDateString();
	console.log(formatted_date);
	var prezzo;
	bambini=$("body").attr("numeroBambini");
	adulti=$("body").attr("numeroAdulti");
	if (numAdulti==0 || numAdulti<adulti){
		if ($("body").attr("classe")=='economy'){
			prezzo=$("body").attr("prezzo");
		}
		else if ($("body").attr("classe")=='first'){
			p=$("body").attr("prezzo");
			prezzo=parseInt(p)+parseInt((parseInt(p)*20)/100);
		}
	}
	else if (numAdulti==adulti && numBambini<bambini){
		if ($("body").attr("classe")=='economy'){
			p=$("body").attr("prezzo");
			prezzo=parseInt(p)-parseInt((parseInt(p)*20)/100);
		}
		else if ($("body").attr("classe")=='first'){
			p=$("body").attr("prezzo");
			prezzo=parseInt(p);
		}
	}
	pass = new Prenotazione(id, nome, cognome, formatted_date, partenza, destinazione, dataA, username, prezzo);
	passJSON = prenotazione(id, nome, cognome, formatted_date, partenza, destinazione, dataA, username, prezzo);
	myList.push(pass);
	myListJSON.push(passJSON);
	
	if (numAdulti==0 || numAdulti<adulti){
		numAdulti+=1;
	}
	else if (numAdulti==numAdulti && numBambini<bambini){
		numBambini+=1;
	}
	
	if (numAdulti==adulti-1){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+1;
		document.getElementById("adultoBambini").innerHTML="ADULTO";
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").value="";
		document.getElementById("date").style.color = "#ccb4b4";
		if (bambini==0){
			document.getElementById("fine1").value="Procedi al pagamento";
		}
		else{
			document.getElementById("fine1").value="Compila gli altri biglietti";
		}
	}
	else if (numAdulti<adulti){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+1;
		document.getElementById("adultoBambini").innerHTML="ADULTO";
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").value="";
		document.getElementById("date").style.color = "#ccb4b4";
	}
	else if (adulti==numAdulti && numBambini==bambini-1){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+numBambini+1;
		document.getElementById("adultoBambini").innerHTML="BAMBINO";
		
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0');
		var yyyy = today.getFullYear();
		today =  yyyy+"-"+mm+"-"+dd;
		document.getElementById("date").setAttribute("max", today);
		document.getElementById("date").setAttribute("min", "2006-12-31");
		
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").style.color = "#ccb4b4";
		document.getElementById("date").value="";
		document.getElementById("fine1").value="Procedi al pagamento";
	}
	else if (numBambini<bambini){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+numBambini+1;
		document.getElementById("adultoBambini").innerHTML="BAMBINO";
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); 
		var yyyy = today.getFullYear();
		today =  yyyy+"-"+mm+"-"+dd;
		document.getElementById("date").setAttribute("max", today);
		document.getElementById("date").setAttribute("min", "2006-12-31");
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").style.color = "#ccb4b4";
		document.getElementById("date").value="";
	}
	else if (adulti==numAdulti && bambini==numBambini){
		document.getElementById("COMPILA").style.display = "none";
		document.getElementById("CONFERMA").style.visibility = "visible";
		for (var i=1; i<=adulti; i++){
			id="codice"+i;
			document.getElementById(id).innerHTML=myList[i-1].nome+"  "+myList[i-1].cognome+"  "+myList[i-1].dataNascita;
		}
		for (var i=1; i<=bambini; i++){
			id="codice1"+i;
			document.getElementById(id).innerHTML=myList[adulti-1+i].nome+" "+myList[adulti-1+i].cognome+" "+myList[adulti-1+i].dataNascita;
		}
		var totale=0;
		for (var i=0; i<myList.length; i++){
			totale+=parseInt(myList[i].prezzo);
		}
		document.getElementById("totale").innerHTML="TOTALE: "+totale+'\u20AC';
	}
}



function prendiPrenotazioneAR(id, id1, username){
	document.getElementById("fine1").style.pointerEvents = "none";
	p=0, de=0, da=0;
	var nome = $("input[type=text][name=input_nome]").val();
    var cognome = $("input[type=text][name=input_cognome]").val();
	var data = new Date($("input[type=date][name=input_dataNascita]").val());
	let formatted_date =  data.toLocaleDateString();
	var prezzo;
	var prezzo1;
	bambini=$("body").attr("numeroBambini");
	adulti=$("body").attr("numeroAdulti");
	if (numAdulti==0 || numAdulti<adulti){
		if ($("body").attr("classe")=='economy'){
			prezzo=$("body").attr("prezzo");
			prezzo1=$("body").attr("prezzo1");
		}
		else if ($("body").attr("classe")=='first'){
			p=$("body").attr("prezzo");
			p1=$("body").attr("prezzo1");
			prezzo=parseInt(p)+parseInt((parseInt(p)*20)/100);
			prezzo1=parseInt(p1)+parseInt((parseInt(p1)*20)/100);
		}
	}
	else if (numAdulti==adulti && numBambini<bambini){
		if ($("body").attr("classe")=='economy'){
			p=$("body").attr("prezzo");
			p1=$("body").attr("prezzo1");
			prezzo=parseInt(p)-parseInt((parseInt(p)*20)/100);
			prezzo1=parseInt(p1)-parseInt((parseInt(p1)*20)/100);
		}
		else if ($("body").attr("classe")=='first'){
			p=$("body").attr("prezzo");
			p1=$("body").attr("prezzo1");
			prezzo=parseInt(p);
			prezzo1=parseInt(p1);
		}
	}
	var pass = new Prenotazione(id, nome,cognome,formatted_date,partenza,destinazione, dataA, username, prezzo);
	var pass1 = new Prenotazione(id1, nome,cognome,formatted_date,destinazione,partenza, dataA, username, prezzo1);
	var passJSONA = prenotazione(id, nome,cognome,formatted_date,partenza,destinazione, dataA, username, prezzo);
	var passJSONR = prenotazione(id1, nome,cognome,formatted_date,destinazione,partenza, dataA, username, prezzo1);
	
	myList.push(pass);
	myList1.push(pass1);
	
	myListJSON.push(passJSONA);
	myListJSON.push(passJSONR);
	
	
	if (numAdulti==0 || numAdulti<adulti){
		numAdulti+=1;
	}
	else if (numAdulti==numAdulti && numBambini<bambini){
		numBambini+=1;
	}
	
	if (numAdulti==adulti-1){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+1;
		document.getElementById("adultoBambini").innerHTML="ADULTO";
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").style.color = "#ccb4b4";
		document.getElementById("date").value="";
		if (bambini==0){
			document.getElementById("fine1").value="Procedi al pagamento";
		}
		else{
			document.getElementById("fine1").value="Compila gli altri biglietti";
		}
	}
	else if (numAdulti<adulti){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+1;
		document.getElementById("adultoBambini").innerHTML="ADULTO";
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").value="";
		document.getElementById("date").style.color = "#ccb4b4";
	}
	else if (adulti==numAdulti && numBambini==bambini-1){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+numBambini+1;
		document.getElementById("adultoBambini").innerHTML="BAMBINO";
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0');
		var yyyy = today.getFullYear();
		today =  yyyy+"-"+mm+"-"+dd;
		document.getElementById("date").setAttribute("max", today);
		document.getElementById("date").setAttribute("min", "2006-12-31");
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").style.color = "#ccb4b4";
		document.getElementById("date").value="";
		document.getElementById("fine1").value="Procedi al pagamento";
	}
	else if (numBambini<bambini){
		document.getElementById("numeroBiglietto").innerHTML=numAdulti+numBambini+1;
		document.getElementById("adultoBambini").innerHTML="BAMBINO";
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); 
		var yyyy = today.getFullYear();
		today =  yyyy+"-"+mm+"-"+dd;
		document.getElementById("date").setAttribute("max", today);
		document.getElementById("date").setAttribute("min", "2006-12-31");
		document.getElementById("fine1").disabled = true;
		document.getElementById("fine1").disabled = false;
		document.getElementById("name").value="";
		document.getElementById("surname").value="";
		document.getElementById("date").style.color = "#ccb4b4";
		document.getElementById("date").value="";
	}
	else if (adulti==numAdulti && bambini==numBambini){
		document.getElementById("COMPILA").style.display = "none";
		document.getElementById("CONFERMA").style.visibility = "visible";
		for (var i=1; i<=adulti; i++){
			id="codice"+i;
			document.getElementById(id).innerHTML=myList[i-1].nome+"  "+myList[i-1].cognome+"  "+myList[i-1].dataNascita;
		}
		for (var i=1; i<=bambini; i++){
			id="codice1"+i;
			document.getElementById(id).innerHTML=myList[adulti-1+i].nome+" "+myList[adulti-1+i].cognome+" "+myList[adulti-1+i].dataNascita;
		}
		var totale=0;
		for (var i=0; i<myList.length; i++){
			totale+=parseInt(myList[i].prezzo);
		}
		for (var i=0; i<myList1.length; i++){
			totale+=parseInt(myList1[i].prezzo);
		}
		document.getElementById("totale1").innerHTML="TOTALE: "+totale+'\u20AC';
	}
}

document.getElementById("acquista").onclick = function() {
	document.getElementById("acquista").disabled = true;
    $.cookie.json = true;
	if($("body").attr("username")=="") {
		// user non loggato
		let carrelloPrima = $.cookie("carrello");
		if (carrelloPrima.voli.length > 0) {
			// c'era roba nel cookie
			myListJSON.forEach(function(e) {
				carrelloPrima.voli.push(e);
			});
			$.cookie("carrello",carrelloPrima);
		} else {
			// il cookie era vuoto
			carrelloPrima.voli = myListJSON;
			$.cookie("carrello",carrelloPrima);
		}
		$.ajax({
			type: "POST",
			url: "InserisciPrenotazioniCarrelloCookie",
			datatype: "json",
			data: JSON.stringify($.cookie("carrello")),
			success: function (){
				window.location.href="carrello.jsp";
			}
		});
	} else {
		// user loggato
		if (myListJSON.length>0){
			$.ajax({
				type: "POST",
				url: "InserisciVoloCarrello",
				datatype: "json",
				data: JSON.stringify(myListJSON),
				success: function (){
					window.location.href="carrello.jsp";
				}
			});
		}
		else{
			$.ajax({
				type: "POST",
				url: "InserisciVoloCarrello",
				datatype: "json",
				data: JSON.stringify(myList),
				success: function (){
					window.location.href="carrello.jsp";
				}
			});
		}
	}
	
};
