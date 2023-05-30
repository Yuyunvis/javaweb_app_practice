var regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var regexUser = /^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
var regexName =/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/;
var regexPassword = /^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;

var password = document.getElementById("password"), confirm_password = document
		.getElementById("confirm_password");

function validatePassword() {
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Passwords Don't Match");
	} else {
		confirm_password.setCustomValidity('');
	}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;


var nomeValido=false;
var cognomeValido=false;
var dataValida=false;
var usernameValido=false;
var emailValida=false;
var passwordValida=false;
document.getElementById("registrati").disabled = true;

jQuery(document).ready(
		
		function($) {
			
			function getValueForm() {
				var array = [nomeValido,cognomeValido,dataValida,usernameValido,emailValida,passwordValida];
				for (i = 0; i < array.length; i++) {
					if (array[i] == false) {
						return false;
					}
				}
				return true;
			}
			
			$("#name").bind("change paste keyup", function() {
				var name = $('#name').val();
				if (!check(name, regexName)) {
					$("#name").addClass("invalideInput");
					$("#name").removeClass("valideInput");
					nomeValido=false;
					document.getElementById("registrati").disabled = true;
				}
				else{
					nomeValido=true;
					$("#name").addClass("valideInput");
					$("#name").removeClass("invalideInput");
					if (getValueForm())
						document.getElementById("registrati").disabled = false;
					else
						document.getElementById("registrati").disabled = true;
				}
			});
			$("#surname").bind("change paste keyup", function() {
				var surname = $('#surname').val();
				if (!check(surname, regexName)) {
					$("#surname").addClass("invalideInput");
					$("#surname").removeClass("valideInput");
					cognomeValido=false;
					document.getElementById("registrati").disabled = true;
				}
				else{
					cognomeValido=true;
					$("#surname").addClass("valideInput");
					$("#surname").removeClass("invalideInput");
					if (getValueForm())
						document.getElementById("registrati").disabled = false;
					else
						document.getElementById("registrati").disabled = true;
				}
			});
			$("#date").bind("change paste keyup", function() {
				var date = $('#date').val();
				if (date=="") {
					$("#date").addClass("invalideInput");
					$("#date").removeClass("valideInput");
					dataValida=false;
					document.getElementById("registrati").disabled = true;
					$("#date").css('color','#ccb4b4');
				}
				else{
					dataValida=true;
					$("#date").css('color','#F78536');
					$("#date").removeClass("invalideInput");
					$("#date").addClass("valideInput");
					if (getValueForm())
						document.getElementById("registrati").disabled = false;
					else
						document.getElementById("registrati").disabled = true;
				}
			});
			$("#password").bind("change paste keyup", function() {
				var password1 = $('#password').val();
				var password2 = $('#confirm_password').val();
				if (!check(password1, regexPassword)) {
					$("#password").addClass("invalideInput");
					$("#password").removeClass("valideInput");
					$("#confirm_password").addClass("invalideInput");
					$("#confirm_password").removeClass("valideInput");
					$("#infoPassword").text("Inserisci una password valida (almeno 6 caratteri).");
					passwordValida=false;
					document.getElementById("registrati").disabled = true;
				}
				else {
					$("#infoPassword").text("");
					$("#password").removeClass("invalideInput");
					$("#password").addClass("valideInput");
					if (password2==password1){
						$("#infoPassword").text("");
						passwordValida=true;
						$("#confirm_password").addClass("valideInput");
						$("#confirm_password").removeClass("invalideInput");
					}
					else{
						$("#infoPassword").text("Le password non coincidono.");
						passwordValida=false;
						$("#confirm_password").addClass("invalideInput");
						$("#confirm_password").removeClass("valideInput");
					}
					if (getValueForm())
						document.getElementById("registrati").disabled = false;
					else
						document.getElementById("registrati").disabled = true;
				}
			});
			$("#confirm_password").bind("change paste keyup", function() {
				var password1 = $('#password').val();
				var password2 = $('#confirm_password').val();
				if (password2=="" || password2!=password1) {
					$("#infoPassword").text("Le password non coincidono.");
					$("#confirm_password").addClass("invalideInput");
					$("#confirm_password").removeClass("valideInput");
					passwordValida=false;
					document.getElementById("registrati").disabled = true;
				}
				else if (password1!="" && password1==password2){
					passwordValida=true;
					$("#infoPassword").text("");
					$("#confirm_password").addClass("valideInput");
					$("#confirm_password").removeClass("invalideInput");
					if (getValueForm())
						document.getElementById("registrati").disabled = false;
					else
						document.getElementById("registrati").disabled = true;
				}
			});
			
			$("#email").bind("change paste keyup", function() {
				var email = $('#email').val();
				if (!check(email, regexEmail)) {
					$("#email").addClass("invalideInput");
					$("#email").removeClass("valideInput");
					$("#infoEmail").text("Inserisci un email valida.");
					document.getElementById("infoEmail").style.color = "red";
					emailValida=false;
					document.getElementById("registrati").disabled = true;
				}
				else{
					emailValida=true;
					$("#email").addClass("valideInput");
					$("#email").removeClass("invalideInput");
					$("#infoEmail").text("Email valida.");
					document.getElementById("infoEmail").style.color = "green";
					if (getValueForm())
						document.getElementById("registrati").disabled = false;
					else
						document.getElementById("registrati").disabled = true;
				}
			});
			$("#username").bind("change paste keyup",function() {
				var username = $('#username').val();
				if (check(username, regexUser)) {
					$("#infoUsername").text("Controllo username in corso...");
					document.getElementById("infoUsername").style.color = "red";
					$.ajax({
						type : 'GET',
						url : 'RegistrationServlet',
						data : {
						username : username
						},dataType : "text",
						success : function(data) {
							$("#infoUsername").text("");
							var p = JSON.parse(data);
							if (p.username == "presente") {
								$("#username").addClass("invalideInput");
								$("#username").removeClass("valideInput");
								$("#infoUsername").text("Username gi\340 presente.");
								document.getElementById("infoUsername").style.color = "red";
								usernameValido=false;
								document.getElementById("registrati").disabled = true;
							}
							else if (p.username == "no") {
									$("#username").addClass("valideInput");
									$("#username").removeClass("invalideInput");
									$("#infoUsername").text("Username valido.");
									document.getElementById("infoUsername").style.color = "green";
									usernameValido=true;
									if (getValueForm())
										document.getElementById("registrati").disabled = false;
									else
										document.getElementById("registrati").disabled = true;
								}
							}
						});

				}
				else{
					usernameValido=false;
					document.getElementById("registrati").disabled = true;
					$("#username").addClass("invalideInput");
					$("#username").removeClass("valideInput");
					$("#infoUsername").text("Inserisci un username valido (almeno 8 caratteri).");
					document.getElementById("infoUsername").style.color = "red";
				}
			});
});

function check(val, regex) {
	if (val != null || val != "")
		return regex.test(val);
	return true;
}

