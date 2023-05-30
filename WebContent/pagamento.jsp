<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Travelife</title>
<link rel="shortcut icon" href="favicon.ico">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet">
<link rel="stylesheet" href="css/cs-select.css">
<link rel="stylesheet" href="css/cs-skin-border.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/modernizr-2.6.2.min.js"></script>
<link rel="stylesheet" href="css/pagamento.css">
<meta charset="ISO-8859-1">
<title>Pagamento</title>
</head>
<body>

<div id="fh5co-wrapper">
		<div id="fh5co-page">
			<header id="fh5co-header-section" class="sticky-banner">
				<div class="container">
					<div class="nav-header">
						<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
						<h1 id="fh5co-logo">
							<a href="index.jsp"><i class="icon-airplane"></i>Travelife</a>
						</h1>
						<nav id="fh5co-menu-wrap" role="navigation">
							<ul class="sf-menu" id="fh5co-primary-menu">
								<li>
								<c:if test="${username != null}">
									<h1 style="font-size:15px; margin-top:17px"><i><b>Ciao, ${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i></h1>
								</c:if>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header>


			<div id="fh5co-tours" class="fh5co-section-gray" id="pagamento">
				<div class="col-md-8 col-md-offset-2 text-center heading-section">
					<p>Inserisci i dati della tua carta di credito per  effettuare il pagamento.</p>
				</div>
			    <div class='row'>
			        <div class='col-md-4'></div>
			        <div class='col-md-4'>
			          <script src='https://js.stripe.com/v2/' type='text/javascript'></script>
			          <form method="post" name="modulo" id="modulo" accept-charset="UTF-8" action="/" class="require-validation" data-cc-on-file="false" data-stripe-publishable-key="pk_bQQaTxnaZlzv4FnnuZ28LFHccVSaj" ><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="â" /><input name="_method" type="hidden" value="PUT" /><input name="authenticity_token" type="hidden" value="qLZ9cScer7ZxqulsUWazw4x3cSEzv899SP/7ThPCOV8=" /></div>
			            <div class='form-row'>
			              <div class='col-xs-12 form-group required'>
			                <label class='control-label'>Nome Intestatario </label>
			                <input id="name" class='form-control' size='4' type='text'>
			              </div>
			            </div>
			            <div class='form-row'>
			              <div class='col-xs-12 form-group required'>
			                <label class='control-label'>Cognome Intestatario </label>
			                <input id="surname" class='form-control' size='4' type='text'>
			              </div>
			            </div>
			            <div class='form-row'>
			              <div class='col-xs-12 form-group card required'>
			                <label class='control-label'>Numero di Carta</label>
			                <input maxlength="16" id="numeroCarta" autocomplete='off' class='form-control card-number' size='16' type='text'>
			                <label style="font-size:10px; color:red;" id='infoCarta'></label>
			              </div>
			            </div>
			            <div class='form-row'>
			              <div class='col-xs-4 form-group cvc required'>
			                <label class='control-label'>CVC</label>
			                <input maxlength="3" id="cvc" autocomplete='off' class='form-control card-cvc' placeholder='ex. 311' size='4' type='text'>
			              </div>
			              <div class='col-xs-4 form-group expiration required'>
			                <label class='control-label'>Mese</label>
			                <input maxlength="2" min="1" max="12" id="mese" class='form-control card-expiry-month' placeholder='MM' size='2' type='text'>
			              </div>
			              <div class='col-xs-4 form-group expiration required'>
			                <label class='control-label'>Anno</label>
			                <input maxlength="4" id="anno" min="2019" class='form-control card-expiry-year' placeholder='YYYY' size='4' type='text'>
			              </div>
			            </div>
			            
			            <div class='form-row'>
			              <div class='col-md-12'>
			                <div class='form-control total btn btn-info' style="background-color:#F78536; pointer-events:none;">
			                  Totale:
			                  <span class='amount' >${param.totale}&euro;</span>
			                </div>
			                
			              </div>
			            </div>
			            <div class='form-row'>
			            <br><br><br>
			              <div class='col-md-12 form-group'>
			                <button id="paga" disabled="disabled" class='form-control btn btn-primary submit-button' type='submit' style="background-color:#87CEFA">
			                Paga</button>
			              </div>
			            </div>
			            
			          </form>
			        </div>
			        <div class='col-md-4'></div>
			    </div>
			
			 </div>
			 
 
 
 	</div>
 	</div>
 
 
 
 
 
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/sticky.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/selectFx.js"></script>
	<script src="js/main.js"></script>
	<script src="js/pagamento.js"></script>

</body>
</html>
 