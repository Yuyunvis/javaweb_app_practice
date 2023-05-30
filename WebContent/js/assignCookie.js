
$(document).ready(function () {

	if($("body").attr("username") == ""){
		//abilito i json nei cookie
	    $.cookie.json = true;

	    if ($.cookie("carrello") == null) {
	        // il cookie è assegnato
			let carr = {
				voli: [],
				camere: []
			};
			$.cookie("carrello",carr);
		}

		$.ajax({
			type: "POST",
			url: "InserisciPrenotazioniCarrelloCookie",
			datatype: "json",
			data: JSON.stringify($.cookie("carrello"))
		});
	}
});