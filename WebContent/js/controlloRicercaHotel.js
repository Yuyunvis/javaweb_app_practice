var today = new Date();
var tomorrow = new Date(today);
tomorrow.setDate(tomorrow.getDate() + 1);
var dd = String(tomorrow.getDate()).padStart(2, '0');
var mm = String(tomorrow.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = tomorrow.getFullYear();
Tomorrow =  yyyy+"-"+mm+"-"+dd;

document.getElementById("dataInizioHotel").setAttribute("min", Tomorrow);
document.getElementById("dataFineHotel").style.pointerEvents = "none";

document.getElementById("cercaHotel").style.pointerEvents = "none";
document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";


function emptyFlight() {
	$("#myform1")[0].reset();
	document.getElementById("cercaHotel").style.pointerEvents = "none";
	document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
	document.getElementById("dataInizioHotel").style.color = '#ccb4b4';
	document.getElementById("dataFineHotel").style.color = '#ccb4b4';
}

document.getElementById("dataInizioHotel").onchange = function(){
	var result = new Date(document.getElementById("dataInizioHotel").value);
	result.setDate(result.getDate() + 1);
	var dd = String(result.getDate()).padStart(2, '0');
	var mm = String(result.getMonth() + 1).padStart(2, '0'); 
	var yyyy = result.getFullYear();
	result =  yyyy+"-"+mm+"-"+dd;
	document.getElementById("dataFineHotel").value="";
	document.getElementById("dataFineHotel").style.pointerEvents = "auto";
	document.getElementById("dataFineHotel").setAttribute("min", result);
};


jQuery(document).ready(
		function($) {
			$("form").bind("keypress", function(e) {
		        if (e.keyCode == 13) {
		            return false;
		        }
		    });
			var validaC=false;
			var validaD1=false;
			var validaD2=false;
			$('input#citta').bind("change paste keyup", function() {
				var citta = $('#citta').val();
				if (citta!="") {
					validaC=true;
					if(validaD1 && validaD2){
						document.getElementById("cercaHotel").style.pointerEvents = "auto";
						document.getElementById("cercaHotel").value = "Cerca Hotel";
						
					}
					else{
						
						document.getElementById("cercaHotel").style.pointerEvents = "none";
						document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
					}
				}
				else{
					validaC=false;
					
					document.getElementById("cercaHotel").style.pointerEvents = "none";
					document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
				}
			});
			$('input#dataInizioHotel').bind("change paste keyup", function() {
				var data1 = document.getElementById("dataInizioHotel").value;
				if (data1!="") {
					validaD1=true;
					document.getElementById("dataInizioHotel").style.color = '#F78536';
					if(validaD2 && validaC){
						document.getElementById("cercaHotel").style.pointerEvents = "auto";
						document.getElementById("cercaHotel").value = "Cerca Hotel";
						
					}
					else{
						
						document.getElementById("cercaHotel").style.pointerEvents = "none";
						document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
					}
				}
				else{
					document.getElementById("dataInizioHotel").style.color = '#ccb4b4';
					document.getElementById("dataFineHotel").style.color = '#ccb4b4';

					validaD1=false;
					
					document.getElementById("cercaHotel").style.pointerEvents = "none";
					document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
				}
			});
			$('input#dataFineHotel').bind("change paste keyup", function() {
				var data2 = document.getElementById("dataFineHotel").value;
				if (data2!="") {
					document.getElementById("dataFineHotel").style.color = '#F78536';
					validaD2=true;
					if(validaC && validaD1){
						document.getElementById("cercaHotel").style.pointerEvents = "auto";
						document.getElementById("cercaHotel").value = "Cerca Hotel";
						
					}
					else{
						
						document.getElementById("cercaHotel").style.pointerEvents = "none";
						document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
					}
				}
				else{
					document.getElementById("dataFineHotel").style.color = '#ccb4b4';
					validaD2=false;
					
					document.getElementById("cercaHotel").style.pointerEvents = "none";
					document.getElementById("cercaHotel").value = "Inserisci tutti i parametri";
				}
			});
});