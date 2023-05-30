function eliminaVolo(codice,disponibile){
	$.ajax({
	    type: 'GET',
	    url: "InserisciVoloCarrello",
	    data: {codice: codice, disponibile: disponibile},
		dataType: "text",
		success: function (data) {
			if (data=="controllo"){
				$.ajax({
					type: "POST",
					url: "ControlloPrenotazioni",
					datatype: "text",
					data: {totale : 0},
					success: function (data) {
						window.location.href = data;
					}
				});
			}
			else{
				window.location.href = data;
			}
		}
   	});
}

function eliminaHotel(codice){
   	$.ajax({
	    type: 'GET',
	    url: "InserisciHotelCarrello",
	    data: {codice: codice},
		dataType: "text",
		success: function (data) {
			window.location.href = "carrello.jsp";
		}
   	});
}