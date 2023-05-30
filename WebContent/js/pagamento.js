//$(function() {
//  $('form.require-validation').bind('submit', function(e) {
//    var $form         = $(e.target).closest('form'),
//        inputSelector = ['input[type=email]', 'input[type=password]',
//                         'input[type=text]', 'input[type=file]',
//                         'textarea'].join(', '),
//        $inputs       = $form.find('.required').find(inputSelector),
//        $errorMessage = $form.find('div.error'),
//        valid         = true;
//
//    $errorMessage.addClass('hide');
//    $('.has-error').removeClass('has-error');
//    $inputs.each(function(i, el) {
//      var $input = $(el);
//      if ($input.val() === '') {
//        $input.parent().addClass('has-error');
//        $errorMessage.removeClass('hide');
//        e.preventDefault(); // cancel on first error
//      }
//    });
//  });
//});
//
//$(function() {
//  var $form = $("#payment-form");
//
//  $form.on('submit', function(e) {
//    if (!$form.data('cc-on-file')) {
//      e.preventDefault();
//      Stripe.setPublishableKey($form.data('stripe-publishable-key'));
//      Stripe.createToken({
//        number: $('.card-number').val(),
//        cvc: $('.card-cvc').val(),
//        exp_month: $('.card-expiry-month').val(),
//        exp_year: $('.card-expiry-year').val()
//      }, stripeResponseHandler);
//    }
//  });
//
//  function stripeResponseHandler(status, response) {
//    if (response.error) {
//      $('.error')
//        .removeClass('hide')
//        .find('.alert')
//     .text(response.error.message);
//    } else {
//      // token contains id, last4, and card type
//      var token = response['id'];
//      // insert the token into the form so it gets submitted to the server
//      $form.find('input[type=text]').empty();
//      $form.append("<input type='hidden' name='reservation[stripe_token]' value='" + token + "'/>");
//      $form.get(0).submit();
//    }
//  }
//})

var carta = new RegExp(/^\d{16}$/);

document.getElementById("paga").onclick = function(){
	$("#modulo").attr('action', 'Pagamento');
	$("#modulo").submit();
}


jQuery(document).ready(
				function($) {
					
					function getValueForm() {
						var array = [];
						$("input[type != submit]").each(function() {
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
						
						var nome = new RegExp(/^[a-zA-Z]+(\s+[a-zA-Z]+)*$/);
						var numAnno = new RegExp(/^\d{4}$/);
						var numMese = new RegExp(/^\d{2}$/);
						
						var namec = $('#name').val();
						var surnamec = $('#surname').val();
						var numeroCarta = $('#numeroCarta').val();
						var mese = $('#mese').val();
						var anno = $('#anno').val();
						var cvc = $('#cvc').val();
						
						
						var nomeValido = nome.test(namec);
						var cognomeValido = nome.test(surnamec);
						var annoValido = numAnno.test(anno);
						var cvcValido=true;
						var meseValido = numMese.test(mese);
						
						

						if (mese>13 || mese<1){
							meseValido = false;
						}
					
						if (cvc === ""){
							cvcValido = false;
						}else{
							cvcValido = true;
						}
						
						if (anno<2020){
							annoValido = false;
						}
						
						if (mese === "" || mese>12){
							meseValido = false;
							
						}else{
							
							meseValido = true;
						}
						
						if (!nomeValido) {
							$("#name").addClass("invalideInput");
							$("#name").removeClass("valideInput");
							
						} else {
							$("#name").addClass("valideInput");
							$("#name").removeClass("invalideInput");
							
						}
						if (!cvcValido) {
							$("#cvc").addClass("invalideInput");
							$("#cvc").removeClass("valideInput");
							
						} else {
							$("#cvc").addClass("valideInput");
							$("#cvc").removeClass("invalideInput");
							
						}
						if (!cognomeValido) {
							$("#surname").addClass("invalideInput");
							$("#surname").removeClass("valideInput");
							
							
						} else {
							$("#surname").addClass("valideInput");
							$("#surname").removeClass("invalideInput");
							
						}
						if (!meseValido) {
							$("#mese").addClass("invalideInput");
							$("#mese").removeClass("valideInput");
							
						}else{
							$("#mese").addClass("valideInput");
							$("#mese").removeClass("invalideInput");
						}
						if (!annoValido) {
							$("#anno").addClass("invalideInput");
							$("#anno").removeClass("valideInput");
							
						} else {
							$("#anno").addClass("valideInput");
							$("#anno").removeClass("invalideInput");
							
						}
						
						var tuttiCampiValidi = true;

						if(!nomeValido || !cognomeValido || !meseValido || !annoValido || !cvcValido){
							tuttiCampiValidi = false;
						}
						
					
						if(tuttiCampiValidi){
							document.getElementById("paga").disabled = false;
						}else{
							document.getElementById("paga").disabled = true;
						}
						
						
					}
					$("input").bind("change paste keyup",function() {
						var eVal = $('#numeroCarta').val();
						if (check($('#numeroCarta').val(), carta)) {
							$("#numeroCarta").addClass("valideInput");
							$("#numeroCarta").removeClass("invalideInput");
							$("#infoCarta").text("Numero carta valido valido.");
						}
					});
					var cartaValida = false;
					$("input").bind("change paste keyup", function() {
						var valide = true;
						if (!check($('#numeroCarta').val(), carta)) {
							$("#numeroCarta").addClass("invalideInput");
							$("#numeroCarta").removeClass("valideInput");
							$("#infoCarta").text("Inserisci un numero di carta valido (16 cifre).");
							valide = false;
						} else {
							valide = getValueForm();
						}
						if(user = true)
							checkProseguiButton(valide);
					});

				});

function check(val, regex) {
	if (val != null || val != "")
		return regex.test(val);
	return true;
}
