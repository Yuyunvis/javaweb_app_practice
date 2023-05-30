var regexUser = /^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
var regexPassword = /^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;

document.getElementById("accedi").disabled = true;

jQuery(document).ready(
		function($) {
			var passwordValida = false;
			var user = false;
			$("#password").bind("change paste keyup",function() {
				$("#infoLogin").text("");
				var passwordc = $('#password').val();
				if (!check(passwordc, regexPassword)) {
					passwordValida = false;
					$("#password").addClass("invalideInput");
					$("#password").removeClass("valideInput");
					document.getElementById("accedi").disabled = true;
				}
				else{
					passwordValida = true;
					$("#password").addClass("valideInput");
					$("#password").removeClass("invalideInput");
					if (user == true){
						document.getElementById("accedi").disabled = false;
					}
				}
			});
			$("#username").bind("change paste keyup",function() {
				var uVal = $('#username').val();
				$("#infoLogin").text("");
				if (!check(uVal, regexUser)) {
					$("#username").addClass("invalideInput");
					$("#username").removeClass("valideInput");
					document.getElementById("accedi").disabled = true;
					user = false;
				}
				else{
					$("#username").addClass("valideInput");
					$("#username").removeClass("invalideInput");
					user = true;
					if (passwordValida){
						document.getElementById("accedi").disabled = false;
					}
				}
				/*document.getElementById("infoUsername").style.color = "red";
				$.ajax({
						type : 'GET',
						url : 'RegistrationServlet',
						data : {
						username : uVal
						},dataType : "text",
						success : function(data) {
							$("#infoUsername").text("");
							var p = JSON.parse(data);
							if (p.username == "presente") {
								$("#username").addClass("valideInput");
								$("#username").removeClass("invalideInput");
								$("#infoUsername").text("Username presente.");
								document.getElementById("infoUsername").style.color = "green";
								user = true;
								if (passwordValida){
									document.getElementById("accedi").disabled = false;
								}
							}
							if (p.username == "no") {
								user = false;
								$("#username").addClass("invalideInput");
								$("#username").removeClass("valideInput");
								$("#infoUsername").text("Nessun account presente con questo username.");
								document.getElementById("infoUsername").style.color = "red";
								document.getElementById("accedi").disabled = true;
							}
						}
					});*/
			});
		});

function check(val, regex) {
	if (val != null || val != "")
		return regex.test(val);
	return true;
}

