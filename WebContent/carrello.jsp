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
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/custom.css" />
<link rel="stylesheet" type="text/css" href="css/loader.css" />

</head>
</head>
<body
	style="background-image: url(images/viaggio.jpg); overflow: hidden;"
	username="${username}">
	<header id="fh5co-header-section" class="sticky-banner">
		<div class="container">
			<div class="nav-header">
				<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
				<h1 id="fh5co-logo">
					<a href="index.jsp"><i class="icon-airplane"></i>Travelife</a>
				</h1>
				<nav id="fh5co-menu-wrap" role="navigation">
					<ul class="sf-menu" id="fh5co-primary-menu">
						<li><c:if test="${username != null}">
								<h1 style="font-size: 15px; margin-top: 17px">
									<i><b>Ciao,
											${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i>
								</h1>
							</c:if></li>
						<li><a href="index.jsp">Home</a></li>
						<li><a href="recensioni.jsp">Dicono di noi</a></li>
						<li><c:if test="${username != null}">
								<a href="profilo.jsp">Profilo</a>
							</c:if></li>
							<li><c:if test="${username == null}">
								<a href="iscrizione.jsp">Iscriviti</a>
							</c:if></li>
							<li><c:if test="${username == null}">
								<a href="login.jsp">Accedi</a>
							</c:if></li>
						<li class="active"><a href="carrello.jsp">Carrello</a></li>
						<li><c:if test="${username != null}">
								<a href="doLogin?logout=true">Logout</a>
							</c:if></li>
					</ul>
				</nav>
			</div>
		</div>
		<br>
	</header>



	<div class="fh5co-overlay">
		<br> <br> <br>
	</div>

	<div class="fh5co-cover">
		<div class="col-md-8 col-md-offset-2 text-center heading-section ">

		</div>


		<div class="col-md-6 col-md-offset-3">
			<div class="tabulation animate-box fadeInUp animated" id="ciso">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a
						aria-controls="hotels" role="tab" data-toggle="tab">Carrello</a></li>
				</ul>
				<br>
				<div style="height:60vh; overflow-x: hidden; padding-top:0px">
				<div class="col-sm-12 col-md-12" >
					<br>
					<c:set var="total" value="${0}" />
					<ul >
						<c:if test="${carrelloVolo.size()>0}">
							<c:forEach items="${carrelloVolo}" var="prenV">
								<c:if test="${prenV.disponibilitaPosto == true}">
									<li class="row" style="background-color: #f0f8fa; padding-left: 0px;">
									<span class="backspace" style="color: #4ea6bc;">
										<i><b>Volo ${prenV.partenza}-${prenV.destinazione} del ${prenV.dataPartenza}</b></i>
									</span>
										<br>
										<span class="itemName">Passeggero:
										${prenV.nomePasseggero} ${prenV.cognomePasseggero}
												${prenV.dataNascita}</span> <span class="popbtn"
																				  style="padding-top: 20px !important; padding-right: 0 !important; paddind-bottom: 0 !important; padding-left: 0px !important;"><a
											id="remove" onclick="eliminaVolo('${prenV.codicePrenotazione}','${prenV.disponibilitaPosto}')"
											class="glyphicon glyphicon-remove"></a></span> <span class="price">${prenV.prezzo}&euro;</span>
										<c:set var="total" value="${total + prenV.prezzo}" />
									</li>
								</c:if>
								<c:if test="${prenV.disponibilitaPosto == false}">
									<script type="text/javascript" src="js/controllo_carrello.js"></script>
									<script type="text/javascript">payButton();</script>
									<li class="row problema_posti" style="padding-left: 0px;">
									<span class="backspace" style="color: #4ea6bc;"> <i><b>
										Volo ${prenV.partenza}-${prenV.destinazione} del ${prenV.dataPartenza}</b></i>
									</span>
										<br>
										<span class="itemName">Passeggero:
										${prenV.nomePasseggero} ${prenV.cognomePasseggero}
												${prenV.dataNascita}</span> <span class="popbtn"
																				  style="padding-top: 20px !important; padding-right: 0 !important; paddind-bottom: 0 !important; padding-left: 0px !important;"><a
											id="remove" onclick="eliminaVolo('${prenV.codicePrenotazione}','${prenV.disponibilitaPosto}')"
											class="glyphicon glyphicon-remove"></a></span> <span class="price">${prenV.prezzo}&euro;</span>
										<c:set var="total" value="${total + prenV.prezzo}" />
									</li>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${carrelloCamere.size()>0}">
							<c:forEach items="${carrelloCamere}" var="prenH">
								<li class="row" style="padding-left: 0px; background-color: #f0f8fa;"><span
									class="backspace" style="color: #4ea6bc;"> <i><b>Hotel
										${prenH.nomeHotel} dal ${prenH.checkInString} al
										${prenH.checkOutString}</b></i></span><br>
								<span class="itemName">Camera ${prenH.tipologia} a nome
										di ${prenH.nome} ${prenH.cognome} </span> <span class="popbtn"
									style="padding-top: 20px !important; padding-right: 0 !important; paddind-bottom: 0 !important; padding-left: 0px !important;"><a
										id="remove" onclick="eliminaHotel('${prenH.codicePrenotazione}')"
										class="glyphicon glyphicon-remove"></a></span> <span class="price">${prenH.prezzo}&euro;</span>
									<c:set var="total" value="${total + prenH.prezzo}" /></li>
							</c:forEach>
						</c:if>

					</ul>
						<div class="col-sm-12 col-md-12 text-center" >
						<br>
						<c:if test="${carrelloCamere.size()>0 || carrelloVolo.size()>0}">
							<div>
									<c:if test="${username!=null}">
										<button id="paga" type="button" class="btn btn-primary"
											data-toggle="modal"
											onclick="redirect(true,${total},${param.posti})">
											PAGA ${total}&euro;</button>
									</c:if> <c:if test="${username==null}">
										<button type="button" onclick="redirect(false,0,${param.posti})"
											class="btn btn-primary" data-toggle="modal"
											data-target="#exampleModalCenter">PAGA
											${total}&euro;</button>
									</c:if>
							</div><
						</c:if>
						<c:if test="${carrelloCamere.size()==0 && carrelloVolo.size()==0}">
							<div><div style="color: #F78536; font-size: x-large;">Nessun
									elemento presente nel carrello.</div></div>
						</c:if>

						</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body text-center">
					<br>
					<div class="lds-ring">
						<div></div>
						<div></div>
						<div></div>
						<div></div>
					</div>
					<br>
					<h1 style="font-size: 23px;">Stai per essere reinderizzato
						alla pagina di login...</h1>
					<br> <br>
				</div>

			</div>
		</div>
	</div>

	<c:if test="${param.errore == true}">
		<!-- Button trigger modal -->
		<button id="errorModalButton" style="display: none" type="button" class="btn btn-primary" data-toggle="modal" data-target="#errorModal">
		</button>

		<!-- Modal -->
		<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title text-danger text-center" id="exampleModalLabel">Errore durante il pagamento</h4>
					</div>
					<div class="modal-body">
						<c:if test="${voliEliminati.size()>0 || camereEliminate.size()>0}">
							<h4 style="font-size:15px;"><b>Le seguenti prenotazioni verranno eliminate.</b></h4>
							<c:forEach items="${voliEliminati}" var="vol">
								<span></span>
								<h5>Volo: ${vol.partenza} - ${vol.destinazione} del ${vol.dataPartenza}</h5>
								<span></span>
							</c:forEach>
							<c:forEach items="${camereEliminate}" var="cam">
								<span></span>
								<h5>${cam.nomeHotel} dal ${cam.checkInString} al ${cam.checkOutString}.</h5>
								<span></span>
							</c:forEach>
						</c:if>
						<c:if test="${param.errorePosti==true}">
							<h4 style="font-size:15px;"><b>I seguenti voli hanno quasi esaurito il numero di posti disponibili.</b></h4>
							<h5>${param.nonDisponibili}</h5>
						</c:if>
					</div>
					<div class="modal-footer">
						<c:if test="${param.errorePosti==true}">
							<span style="font-size: 15px; float: left; margin-top: 6px;" class="modal-title text-danger">Provvedi all'eliminazione del corretto numero di prenotazioni.</span>
						</c:if>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Continua</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>


	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap2.min.js"></script>
	<script src="js/eliminaPrenotazione.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src="js/controllo_carrello.js"></script>
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
<script src="js/verifyError.js"></script>
</body>
</html>