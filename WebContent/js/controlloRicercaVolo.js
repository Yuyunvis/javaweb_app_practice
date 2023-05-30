var today = new Date();
var tomorrow = new Date(today);
tomorrow.setDate(tomorrow.getDate() + 1);
var dd = String(tomorrow.getDate()).padStart(2, '0');
var mm = String(tomorrow.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = tomorrow.getFullYear();
Tomorrow =  yyyy+"-"+mm+"-"+dd;

document.getElementById("vai").style.pointerEvents = "none";
document.getElementById("vai").value = "Inserisci tutti i parametri";


function emptyHotel() {
	$("#myform2")[0].reset();
	document.getElementById("vai").style.pointerEvents = "none";
	document.getElementById("vai").value = "Inserisci tutti i parametri";
	document.getElementById("dateEnd").style.color = '#ccb4b4';
	document.getElementById("dateStart").style.color = '#ccb4b4';

}

jQuery(document).ready(
		function($) {
			document.getElementById("dateStart").setAttribute("min", Tomorrow);
			document.getElementById("dateEnd").style.pointerEvents = "none";
			document.getElementById("dateStart").onchange = function(){
				document.getElementById("dateEnd").value = "";
				document.getElementById("dateEnd").style.pointerEvents = "auto";
				document.getElementById("dateEnd").setAttribute("min", document.getElementById("dateStart").value);
			};

			$("form").bind("keypress", function(e) {
		        if (e.keyCode == 13) {
		            return false;
		        }
		    });
			var partenza=false;
			var destinazione=false;
			var data=false;
			$('input#from').bind("change paste keyup", function() {
				$("#vai").on("keydown keypress keyup", false);
				var citta1 = $('#from').val();
				if (citta1!="") {
					partenza=true;
					if(destinazione && data){
						document.getElementById("vai").style.pointerEvents = "auto";
						document.getElementById("vai").value = "Cerca Volo";
					}
					else{
						document.getElementById("vai").style.pointerEvents = "none";
						document.getElementById("vai").value = "Inserisci tutti i parametri";
					}
				}
				else{
					partenza=false;
					document.getElementById("vai").style.pointerEvents = "none";
					document.getElementById("vai").value = "Inserisci tutti i parametri";
				}
			});
			$('input#to').bind("change paste keyup", function() {
				$("#vai").on("keydown keypress keyup", false);
				var citta2 = document.getElementById("to").value;
				if (citta2!="") {
					destinazione=true;
					if(data && partenza){
						document.getElementById("vai").style.pointerEvents = "auto";
						document.getElementById("vai").value = "Cerca Volo";
					}
					else{
						document.getElementById("vai").style.pointerEvents = "none";
						document.getElementById("vai").value = "Inserisci tutti i parametri";
					}
				}
				else{
					destinazione=false;
					document.getElementById("vai").style.pointerEvents = "none";
					document.getElementById("vai").value = "Inserisci tutti i parametri";
				}
			});
			$('input#dateStart').bind("change paste keyup", function() {
				$("#vai").on("keydown keypress keyup", false);
				let date = document.getElementById("dateStart").value;
				if (date!="") {
					document.getElementById("dateStart").style.color = '#F78536';
					data=true;
					if(partenza && destinazione){
						document.getElementById("vai").style.pointerEvents = "auto";
						document.getElementById("vai").value = "Cerca Volo";
					}
					else{
						document.getElementById("vai").style.pointerEvents = "none";
						document.getElementById("vai").value = "Inserisci tutti i parametri";
					}
				}
				else{
					document.getElementById("dateEnd").style.color = '#ccb4b4';
					document.getElementById("dateStart").style.color = '#ccb4b4';
					data=false;
					document.getElementById("vai").style.pointerEvents = "none";
					document.getElementById("vai").value = "Inserisci tutti i parametri";
				}
			});
			$('input#dateEnd').bind("change paste keyup", function() {
				let date = document.getElementById("dateEnd").value;
				if (date!="") {
					document.getElementById("dateEnd").style.color = '#F78536';
				}
				else{
					document.getElementById("dateEnd").style.color = '#ccb4b4';
				}
			});
});


