var regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var regexPassword = /^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
var password = document.getElementById("password1"), confirm_password = document.getElementById("password2");

function validatePassword() {
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Le password non corrispondono");
	} else {
		confirm_password.setCustomValidity('');
	}
}

function check(val, regex) {
	if (val != null || val != "")
		return regex.test(val);
	return true;
}
document.getElementById("submit1").disabled = true;
document.getElementById("submit2").disabled = true;
password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

jQuery(document).ready(
		function($) {  
			$("#emaildacambiare").bind("change paste keyup",function() {
				var mail = $('#emaildacambiare').val();
				if (check(mail,regexEmail)) { 
					$('#emaildacambiare').addClass("valideInput");
					$('#emaildacambiare').removeClass("invalideInput");
					document.getElementById("submit1").disabled = false;
					$("#infoEmail").text("");
				}
				else if (!check(mail,regexEmail) && mail !== ""){
					$('#emaildacambiare').removeClass("valideInput");
					$('#emaildacambiare').addClass("invalideInput");
					document.getElementById("submit1").disabled = true;
					$("#infoEmail").text("Inserisci un email valida.");
				}
				else if (mail == ""){
					$('#emaildacambiare').removeClass("valideInput");
					$('#emaildacambiare').removeClass("invalideInput");
					document.getElementById("submit1").disabled = true;
					$("#infoEmail").text("");
				}
			});
			$("#password1").bind("change paste keyup", function() {
				var password1 = $('#password1').val();
				var password2 = $('#password2').val();
				if(password1=="" && password2==""){
					$("#password2").removeClass("invalideInput");
					$("#password1").removeClass("invalideInput");
					$("#password2").removeClass("valideInput");
					$("#password1").removeClass("valideInput");
					$("#infoPassword").text("");
					document.getElementById("submit2").disabled = true;
				}
				else if (!check(password1, regexPassword)) {
					$("#password1").addClass("invalideInput");
					$("#password1").removeClass("valideInput");
					$("#password2").addClass("invalideInput");
					$("#password2").removeClass("valideInput");
					$("#infoPassword").text("Inserisci una password valida (almeno 6 caratteri).");
					document.getElementById("submit2").disabled = true;
				}
				else if (check(password1, regexPassword)) {
					if (password2==password1){
						$("#infoPassword").text("");
						$("#password1").addClass("valideInput");
						$("#password1").removeClass("invalideInput");
						$("#password2").addClass("valideInput");
						$("#password2").removeClass("invalideInput");
						document.getElementById("submit2").disabled = false;
					}
					else{
						$("#infoPassword").text("Le password non coincidono.");
						$("#password2").addClass("invalideInput");
						$("#password2").removeClass("valideInput");
						$("#password1").addClass("invalideInput");
						$("#password1").removeClass("valideInput");
						document.getElementById("submit2").disabled = true;
					}	
				}
			});
			$("#password2").bind("change paste keyup", function() {
				var password1 = $('#password1').val();
				var password2 = $('#password2').val();
				if(password1=="" && password2==""){
					$("#password2").removeClass("invalideInput");
					$("#password1").removeClass("invalideInput");
					$("#password2").removeClass("valideInput");
					$("#password1").removeClass("valideInput");
					$("#infoPassword").text("");
					document.getElementById("submit2").disabled = true;
				}
				else if (password2!=password1) {
					if(!check(password2, regexPassword)){
						$("#password2").addClass("invalideInput");
						$("#password2").removeClass("valideInput");
						document.getElementById("submit2").disabled = true;
					}
					else if (check(password1, regexPassword) && check(password2, regexPassword)) {
						$("#infoPassword").text("Le password non coincidono.");
						$("#password2").addClass("invalideInput");
						$("#password2").removeClass("valideInput");
						document.getElementById("submit2").disabled = true;
					}
				}
				else if (password1==password2){
					if (check(password1, regexPassword) && check(password2, regexPassword)) {
						$("#infoPassword").text("");
						$("#password2").addClass("valideInput");
						$("#password2").removeClass("invalideInput");
						$("#password1").addClass("valideInput");
						$("#password1").removeClass("invalideInput");
						document.getElementById("submit2").disabled = false;
					}
				}
			});
			/*$("input[type = text]").bind("change paste keyup",function() {
				var mail = $('#emaildacambiare').val();
				if (!check(mail,regexEmail)) { 
					$('#emaildacambiare').removeClass("valideInput");
					$('#emaildacambiare').removeClass("invalideInput");
					document.getElementById("submit2").disabled = true;
					
				} else {
					$('#emaildacambiare').addClass("valideInput");
					$('#emaildacambiare').removeClass("invalideInput");
					document.getElementById("submit2").disabled = false;
				}
			});
			
			$("input[type = password]").bind("change paste keyup",function() {
				
				var passwordUguali = true;
				var psw1 = $('#password1').val();
				var psw2 = $('#password2').val();
				
				if (psw1 !== psw2) {
					$("#password1").addClass("invalideInput");
					$("#password2").addClass("invalideInput");
					$("#password1").removeClass("valideInput");
					$("#password2").removeClass("valideInput");
					document.getElementById("submit2").disabled = true;
				}
				else if (psw1 == "" && psw2 == "") { 
					$("#password1").removeClass("valideInput");
					$("#password2").removeClass("valideInput");
					$("#password1").removeClass("invalideInput");
					$("#password2").removeClass("invalideInput");
					document.getElementById("submit2").disabled = true;
				} 
				else if (psw1 !== "" && psw2 !== "" && psw1 === psw2) {
					$("#password1").addClass("valideInput");
					$("#password2").addClass("valideInput");
					$("#password1").removeClass("invalideInput");
					$("#password2").removeClass("invalideInput");
					document.getElementById("submit2").disabled = false;
				} 
			});*/
});