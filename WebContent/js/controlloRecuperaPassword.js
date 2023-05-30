document.getElementById("invio").disabled = true;

jQuery(document).ready(
		function($) { 
			$("input").bind("change paste keyup",function() {
				var user = $('#username').val();
				if (user != ""){
					$('#username').addClass("valideInput");
					$('#username').removeClass("invalideInput");
					document.getElementById("invio").disabled = false;
				}
				else {
					$('#username').removeClass("valideInput");
					$('#username').addClass("invalideInput");
					document.getElementById("invio").disabled = true;
				}
			});
			
});