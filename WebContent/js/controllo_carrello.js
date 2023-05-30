/*jQuery(document).ready(function($) {
	let params = new URLSearchParams(document.location.search.substring(1));
	let verifica = params.get("errorePosti"); 
	if(verifica=="true"){
		document.getElementById("paga").disabled = true;
		document.getElementById("paga").setCustomValidity("Provvedi all'eliminazione delle prenotazioni per poter procedere");
		document.getElementById("paga").reportValidity();
	}
});*/

function payButton(){
	document.addEventListener("DOMContentLoaded", function(event) {
	  document.getElementById("paga").disabled = true;
	  document.getElementById("paga").setCustomValidity("Provvedi all'eliminazione delle prenotazioni per poter procedere");
	  document.getElementById("paga").reportValidity();
	});
}

function redirect(logged,total,posti){
	if (logged) {
		$.ajax({
			type: "POST",
			url: "ControlloPrenotazioni",
			datatype: "text",
			data: {totale : total},
			success: function (data) {
				window.location.href = data;
			}
		});
	} else{
		window.setTimeout(function(){
			window.location.href = "login.jsp?carrello=true";
		}, 3000);
	}
}
