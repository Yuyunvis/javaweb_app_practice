var regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

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

jQuery(document)
		.ready(
				function($) {/*
								 * 'use strict'; /*
								 * ================================================================== [
								 * Daterangepicker ]
								 * 
								 * try { $('.js-datepicker').daterangepicker({
								 * "singleDatePicker": true, "showDropdowns":
								 * true, "autoUpdateInput": false, locale: {
								 * format: 'DD/MM/YYYY' }, });
								 * 
								 * var myCalendar = $('.js-datepicker'); var
								 * isClick = 0;
								 * 
								 * $(window).on('click',function(){ isClick = 0;
								 * });
								 * 
								 * $(myCalendar).on('apply.daterangepicker',function(ev,
								 * picker){ isClick = 0;
								 * $(this).val(picker.startDate.format('DD/MM/YYYY'));
								 * 
								 * });
								 * 
								 * $('.js-btn-calendar').on('click',function(e){
								 * e.stopPropagation();
								 * 
								 * if(isClick === 1) isClick = 0; else
								 * if(isClick === 0) isClick = 1;
								 * 
								 * if (isClick === 1) { myCalendar.focus(); }
								 * });
								 * 
								 * $(myCalendar).on('click',function(e){
								 * e.stopPropagation(); isClick = 1; });
								 * 
								 * $('.daterangepicker').on('click',function(e){
								 * e.stopPropagation(); }); } catch(er)
								 * {console.log(er);} [ Select 2 Config ]
								 * ===========================================================
								 * 
								 * 
								 * try { var selectSimple =
								 * $('.js-select-simple');
								 * 
								 * selectSimple.each(function () { var that =
								 * $(this); var selectBox = that.find('select');
								 * var selectDropdown =
								 * that.find('.select-dropdown');
								 * selectBox.select2({ dropdownParent:
								 * selectDropdown }); }); } catch (err) {
								 * console.log(err); }
								 */
					/*
					 * var array1 =
					 * document.getElementByClassName("input--style-5"); var
					 * status = true; array1.forEach(function(element) { if
					 * (!element.checkValidity()) { status = false; } });
					 * 
					 */
					// Set button disabled
					/*
					 * $("button[type=submit]").attr("disabled", "disabled"); //
					 * Append a change event listener to you inputs
					 * $('input').change(function(){ // Validate your form here,
					 * example: var validated = true; var regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
					 * var regexPassword =
					 * /(?=.\W)(?=.\d)(?=.[a-z])(?=.[A-Z]).{8,}/; var
					 * regexName = /([a-zA-Z](?!\d)){2,20}/; var regexCreditCard =
					 * /^\d{16}$/; var regexDate = /^\d{4}-\d{2}-\d{2}$/;
					 * if(!check($('input[name=email]').val(),regexEmail) ||
					 * !check($('input[name=psw]').val(),regexPassword) ||
					 * !check($('input[name=last_name]').val(),regexName) ||
					 * !check($('input[name=first_name]').val(),regexName) ||
					 * $('input[name=address]').val().length===0 ||
					 * !check($('input[name=creditCard').val(),regexCreditCard) )
					 * validated = false; // If form is validated enable form
					 * if(validated)
					 * $("button[type=submit]").removeAttr("disabled"); });
					 * $('textarea').change(function(){ var validated = true;
					 * 
					 * if(!$('textarea[name=description]').val().length===0)
					 * validated = false; // If form is validated enable form
					 * if(validated)
					 * $("button[type=submit]").removeAttr("disabled"); }); //
					 * Trigger change function once to check if the form is
					 * validated on page // load
					 * 
					 * 
					 * 
					 * $('input:first').trigger('change');
					 * 
					 * $('textarea:first').trigger('change');
					 */

					$("#loadCreditCardLabel").show();
					$("#loadCreditCardButton").removeClass("btn--red ");
					$("#loadCreditCardButton").addClass("btn--redDisabled ");
					$("button[type=submit]").attr("disabled", "disabled");

					$('#form').submit(
							function(e) {
								e.preventDefault();

								// check if the input is valid

								var utente = {
									email : $('#email').val(),
									name : $('#name').val(),
									surname : $('#surname').val(),
									password : $('#password').val(),
									address : $('#address').val() + ";"
											+ $('#houseNumber').val() + ";"
											+ $('#cap').val() + ";"
											+ $('#city').val() + ";"
											+ $('#country').val(),
									date : $('#date').val()
								};

								$.ajax({
									type : 'POST',
									url : 'registraUtente?salta=false',
									datatype : "json",
									contentType : "application/json",
									data : JSON.stringify(utente),

									success : function(data) {
										// alert("CHIAMATA AVVENUTA CON
										// SUCCESSO");
										$('#creditCardForm').load(
												'creditCardForm.html');
									},

								});

							});

					function getValueForm() {
						var array = [];
						$("input").each(function() {
							array.push($(this).val());
						});
						for (i = 0; i < array.length; i++) {
							if (array[i] === null || array[i] === "") {
								return false;
							}
						}
						return true;
					}

					function checkProseguiButton(valide) {

						var emailc = $('#email').val();
						var namec = $('#name').val();
						var surnamec = $('#surname').val();
						var passwordc = $('#password').val();
						var passwordcc = $('#confirm_password').val();
						var addressc = $('#address').val();
						var housenc = $('#houseNumber').val();
						var capc = $('#cap').val();
						var cityc = $('#city').val();
						var countryc = $('#country').val();
						var datec = $('#date').val();

						/*if (emailc === "" || namec === "" || surnamec === ""
								|| passwordc === "" || addressc === ""
								|| housenc === "" || capc === ""
								|| cityc === "" || countryc === "") {
							alert("campoMancante");
						}*/

						/*if (emailc === "") {
							$("#asteriscoEmail").show();
						}
						else {
							$("#asteriscoEmail").hide();
						}*/
						if (namec === "" || surnamec === "") {
							$("#asteriscoNome").show();
						}
						else {
							$("#asteriscoNome").hide();
						}
						if (passwordc === "") {
							$("#asteriscoPass").show();
						}
						else {
							$("#asteriscoPass").hide();
						}
						if (passwordcc === "") {
							$("#asteriscoPassC").show();
						}
						else {
							$("#asteriscoPassC").hide();
						}
						if (addressc === "" || housenc === "" || capc === "" || countryc === "" || cityc === "") {
							$("#asteriscoAddress").show();
						}
						else {
							$("#asteriscoAddress").hide();
						}
						if (datec === "") {
							$("#asteriscoData").show();
						}
						else {
							$("#asteriscoData").hide();
						}
						
						
						
						var tuttiCampiValidi = true;
						
						var nomeValido = namec.match(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
						
						if(!nomeValido && namec !== ""){
							$("#name").addClass("coloralo");
						}else {
							$("#name").removeClass("coloralo");
						}
						var cognomeValido = surnamec.match(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
						if(!cognomeValido && surnamec !== ""){
							$("#surname").addClass("coloralo");
						}else {
							$("#surname").removeClass("coloralo");
						}
						var indirizzoValido = addressc.match(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
						if(!indirizzoValido && addressc !== ""){
							$("#address").addClass("coloralo");
						}else {
							$("#address").removeClass("coloralo");
						}
						var numeroCivicoValido = housenc.match(/^([0-9])+$/);
						if(!numeroCivicoValido && housenc !== ""){
							$("#houseNumber").addClass("coloralo");
						}else {
							$("#houseNumber").removeClass("coloralo");
						}
						var capValido = capc.match(/^([0-9])+$/);
						if(!capValido && capc !== ""){
							$("#cap").addClass("coloralo");
						}else {
							$("#cap").removeClass("coloralo");
						}
						var cittaValido = cityc.match(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
						if(!cittaValido && cityc !== ""){
							$("#city").addClass("coloralo");
						}else {
							$("#city").removeClass("coloralo");
						}
						var nazioneValido = countryc.match(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
						if(!nazioneValido && countryc !== ""){
							$("#country").addClass("coloralo");
						}else {
							$("#country").removeClass("coloralo");
						}
						var passwordValida = false;

						if (check(passwordc,(/^(?=.\W)(?=.\d)(?=.[a-z])(?=.[A-Z]).{8,}/))) {
							passwordValida = true;
						}
						if (passwordc !== passwordcc) {
							$("#PassC").show();
							passwordValida = false;
						}else {
							$("#PassC").hide();
						}
						
						
						var puoiContinuare = false;
						if(nomeValido && cognomeValido && indirizzoValido && numeroCivicoValido && capValido && cittaValido && nazioneValido && passwordValida){
							puoiContinuare = true;
						}
						
						if (valide && email && puoiContinuare) {
							$("#loadCreditCardLabel").hide();
							$("#loadCreditCardButton").removeClass(
									"btn--redDisabled");
							$("#loadCreditCardButton").addClass("btn--red ");
							$("button[type=submit]").removeAttr("disabled");
						} else {
							$("#loadCreditCardLabel").show();
							$("#loadCreditCardButton").removeClass("btn--red ");
							$("#loadCreditCardButton").addClass(
									"btn--redDisabled ");
							$("button[type=submit]").attr("disabled",
									"disabled");
						}
					}

					var email = false;

					$("input").bind("change paste keyup", function() {

						var valide = true;
						if (!check($('#email').val(), regexEmail)) {
							$("#email").removeClass("invalideInput");
							$("#email").removeClass("valideInput");
							$("#infoEmail").text("Inserisci un email valida.");
							valide = false;
						} else {

							valide = getValueForm();
						}

						checkProseguiButton(valide);
					});

					$("input[type = email")
							.bind(
									"change paste keyup",
									function() {

										var eVal = $('#email').val();
										if (check($('#email').val(), regexEmail)) {
											$("#infoEmail")
													.text(
															"Controllo email in corso...");

											$
													.ajax({
														type : 'GET',
														url : 'registraUtente',
														data : {
															email : eVal
														},
														dataType : "text",
														success : function(data) {
															$("#infoEmail")
																	.text("");
															var p = JSON
																	.parse(data);
															if (p.email == "presente") {
																// alert("l'email
																// è PRESENTE
																// nel database
																// "+$("#inputEmail").css("background"));

																$("#email")
																		.addClass(
																				"invalideInput");
																$("#email")
																		.removeClass(
																				"valideInput");
																$("#infoEmail")
																		.text(
																				"Email già presente.");
																// $("#inputEmail").attr("background",
																// "red");
																$(
																		"button[type=submit]")
																		.attr(
																				"disabled",
																				"disabled");
																email = false;
															}
															if (p.email == "no") {
																// alert("NON E'
																// PRESENTE"+$("#inputEmail").css("background"));
																$("#email")
																		.addClass(
																				"valideInput");
																$("#email")
																		.removeClass(
																				"invalideInput");
																$("#infoEmail")
																		.text(
																				"Email valida.");
																$(
																		"button[type=submit]")
																		.removeAttr(
																				"disabled");
																email = true;
																var valide = getValueForm();
																checkProseguiButton(valide);

															}
														}
													});

										}
									});

				});

function check(val, regex) {
	if (val != null || val != "")
		return regex.test(val);
	return true;
}

function checkBorn(val, regex) {
	if (val != null || val != "") {
		//alert(val);
		if (regex.test(val)) {
			var d = new Date();
			var v = new Date(val);
			if (d.getFullYear() - v.getFullYear() >= 10)
				return true;
			return false;
		}
	}
	return true;
}