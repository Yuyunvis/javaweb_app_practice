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
	<style>
		.tab-content .btn {
			letter-spacing: 1px;
			text-transform: uppercase;
			font-weight: bold;
			font-size: 12px;
		}
	</style>
<script src="js/modernizr-2.6.2.min.js"></script>
</head>
<body username="${username}">
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

								<li>
									<c:if test="${username == null}">
										<a href="login.jsp?cercaVolo=true&from=${cittaA}&to=${cittaB}&dateStart=${dataA}&dateEnd=${dataB}&dateEnd=${dataB}&numeroAdulti=${numeroAdulti}&numeroBambini=${numeroBambini}&classe=${classe}">Accedi</a>
									</c:if>	 
								</li>
								<li>
									<a href="carrello.jsp">Carrello</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header>
		<div class="fh5co-hero">
			<div class="fh5co-overlay"></div>
			<div class="fh5co-cover" style="background-image: url(images/volo.jpg);">
				<div class="desc">
					<div class="container">
						<div class="row">
							<div class="col-sm-5 col-md-5">
								<div class="tabulation animate-box">
								   <ul class="nav nav-tabs" role="tablist">
								      <li role="presentation" class="active">
								    	   <a href="#voloAndata" aria-controls="hotels" role="tab" data-toggle="tab">SELEZIONA VOLI</a>
								    	  
								      </li>
								      <li role="presentation">
								       <a href="#voloRitorno" aria-controls="hotels" role="tab" data-toggle="tab"></a>
								   		</li>
								   </ul>
									<div class="tab-content">
									<c:if test="${voliRitorno != null || voliAndata != null}">
									<h2 class="text-center" style="font-size:15px; color:#393e46;"><i>*Prezzo totale per tutti i passeggeri, tasse incluse</i></h2>
									</c:if>
									 <div role="tabpanel" class="tab-pane active" id="voloAndata">
									 	<div class="row">
											<div class="col-xxs-12 col-xs-12"  style="height:450px; overflow-x: hidden; padding-top:20px">
												<c:if test="${voliRitorno == null && voliAndata.size() > 0}">
													<c:forEach items="${voliAndata}" var="voloA">
														<h1 style="font-size:20px;">Volo: <b>${voloA.partenza}-${voloA.destinazione}</b><br>
															Partenza: <b>${voloA.dataPartenzaStr}</b><br>
															Arrivo: <b>${voloA.dataArrivoStr}</b><br>
															Compagnia aerea: <b>${voloA.compagniaArea}</b><br>
															<c:if test="${classe == 'economy'}">
																Prezzo: <b>${voloA.prezzo*numeroAdulti+(voloA.prezzo-((voloA.prezzo*20)/100))*numeroBambini}&euro;</b><br><br>
																<div class="col-xs-12 text-center">
																	<input type="submit" value="Procedi" class="btn btn-primary btn-sm" onclick="funzione1('${voloA.codice}','${voloA.partenza}','${voloA.destinazione}','${voloA.data}','${voloA.prezzo}','${numeroAdulti}','${numeroBambini}','${classe}');">
																</div>
															</c:if>
															<c:if test="${classe == 'first'}">
																Prezzo: <b>${((voloA.prezzo+(voloA.prezzo*20)/100)*numeroAdulti+(voloA.prezzo*numeroBambini))}&euro;</b><br><br>
																<div class="col-xs-12 text-center">
																	<input type="submit" value="Procedi" class="btn btn-primary btn-sm" onclick="funzione1('${voloA.codice}','${voloA.partenza}','${voloA.destinazione}','${voloA.data}','${voloA.prezzo}','${numeroAdulti}','${numeroBambini}','${classe}');">
																</div>
															</c:if>
														</h1>
														<br><hr>
													</c:forEach>
												</c:if>
												<c:if test="${voliRitorno == null && voliAndata.size() == 0}">
													<h1 style="font-size:17px; color:red;"><b>Nessun volo trovato con i parametri inseriti</b></h1>
													<div class="col-md-12 text-center animate-box">
														<a class="btn btn-primary btn-outline btn-lg" href="index.jsp"style="font-size:15px"><i class="icon-arrow-left22"></i>   RIPROVA</a>
													</div>
												</c:if>
												<c:if test="${voliRitorno != null && voliRitorno.size() == 0 && voliAndata.size() == 0}">
													<h1 style="font-size:17px; color:red;"><b>Nessun volo trovato con i parametri inseriti</b></h1>
														<div class="col-md-12 text-center animate-box">				
															<a class="btn btn-primary btn-outline btn-lg" href="index.jsp"style="font-size:15px"><i class="icon-arrow-left22"></i>   RIPROVA</a>
														</div>
												</c:if>
												<c:if test="${voliRitorno != null && voliRitorno.size() > 0 && voliAndata.size() == 0}">
													<h1 style="font-size:17px; color:red;"><b>Nessun volo di Andata trovato, disponibile solo il volo di ritorno.</b></h1>
													<div class="col-md-12 text-center animate-box">
														<a class="btn btn-primary btn-outline btn-lg" href="index.jsp"style="font-size:15px"><i class="icon-arrow-left22"></i>   RIPROVA</a>
													</div>
												</c:if>
												<c:if test="${voliRitorno != null && voliRitorno.size() == 0 && voliAndata.size() > 0}">
													<h1 style="font-size:17px; color:red;"><b>Nessun volo di ritorno trovato, disponibile solo il volo di andata.</b></h1>
													<div class="col-md-12 text-center animate-box">
														<a class="btn btn-primary btn-outline btn-lg" href="index.jsp"style="font-size:15px"><i class="icon-arrow-left22"></i>   RIPROVA</a>
													</div>
												</c:if>
												<c:if test="${voliRitorno != null && voliRitorno.size() > 0 && voliAndata.size() > 0}">
													<c:forEach items="${voliAndata}" var="voloA">
														<h1 style="font-size:20px;">Volo: <b>${voloA.partenza}-${voloA.destinazione}</b><br>
															Partenza: <b>${voloA.dataPartenzaStr}</b><br>
															Arrivo: <b>${voloA.dataArrivoStr}</b><br>
															Compagnia aerea: <b>${voloA.compagniaArea}</b><br>
															<c:if test="${classe == 'economy'}">
																Prezzo: <b>${voloA.prezzo*numeroAdulti+(voloA.prezzo-((voloA.prezzo*20)/100))*numeroBambini}&euro;</b><br><br>
																<div class="col-xs-12 text-center"><br>
																	<a href="#voloRitorno" onclick="funzione('${voloA.codice}','${voloA.partenza}','${voloA.destinazione}','${voloA.data}','${voloA.prezzo}','${numeroAdulti}','${numeroBambini}','${classe}');" aria-controls="hotels" role="tab" data-toggle="tab" style="font-size:15px;">
																		<input type="submit" value="Seleziona volo di andata" aria-controls="hotels" class="btn btn-primary btn-sm">
																	</a>
																</div>
															</c:if>
															<c:if test="${classe == 'first'}">
																Prezzo: <b>${((voloA.prezzo+(voloA.prezzo*20)/100)*numeroAdulti+(voloA.prezzo*numeroBambini))}&euro;</b><br><br>
																<div class="col-xs-12 text-center"><br>
																	<a href="#voloRitorno" onclick="funzione('${voloA.codice}','${voloA.partenza}','${voloA.destinazione}','${voloA.data}','${voloA.prezzo}','${numeroAdulti}','${numeroBambini}','${classe}');" aria-controls="hotels" role="tab" data-toggle="tab" style="font-size:15px;">
																		<input type="submit" value="Seleziona volo di andata" aria-controls="hotels" class="btn btn-primary btn-sm">
																	</a>
																</div>
															</c:if>
														</h1>
														<br><hr>
													</c:forEach>
												</c:if>
											</div>
										</div>
									 </div>
									 <div role="tabpanel" class="tab-pane" id="voloRitorno">
									 	<div class="row">
											<div class="col-xxs-12 col-xs-12 mt" style="height:450px; overflow-x: hidden; padding-top:20px">
												<c:forEach items="${voliRitorno}" var="voloD">
														<h1 style="font-size:20px;">Volo: <b>${voloD.partenza}-${voloD.destinazione}</b><br>
															Partenza:<b>${voloD.dataPartenzaStr}</b><br>
															Arrivo: <b>${voloD.dataArrivoStr}</b><br>
															Compagnia aerea: <b>${voloD.compagniaArea}</b><br>
															<c:if test="${classe == 'economy'}">
																Prezzo: <b>${voloD.prezzo*numeroAdulti+(voloD.prezzo-((voloD.prezzo*20)/100))*numeroBambini}&euro;</b><br><br>
																<div class="col-xs-12 text-center"><br>
																	<input type="submit" value="Seleziona volo ritorno" class="btn btn-primary btn-sm" id="clicca" onclick="ole('${voloD.codice}','${voloD.partenza}','${voloD.destinazione}','${voloD.data}','${voloD.prezzo}');ok('${numeroAdulti}','${numeroBambini}');">
																</div>
															</c:if>
															<c:if test="${classe == 'first'}">
																Prezzo: <b>${((voloD.prezzo+(voloD.prezzo*20)/100)*numeroAdulti+(voloD.prezzo*numeroBambini))}&euro;</b><br><br>
																<div class="col-xs-12 text-center"><br>
																	<input type="submit" value="Seleziona volo ritorno" class="btn btn-primary btn-sm" id="clicca" onclick="ole('${voloD.codice}','${voloD.partenza}','${voloD.destinazione}','${voloD.data}','${voloD.prezzo}');ok('${numeroAdulti}','${numeroBambini}');">
																</div>
															</c:if>
														</h1>
														<br><hr>
												</c:forEach>
											</div>
										</div>

									</div>

								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		</div>
		

		<footer>
				<div id="footer">
					<div class="container">
						<div class="row">
							<div class="col-md-6 col-md-offset-3 text-center">
								<p class="fh5co-social-icons">
									<a><i class="icon-twitter2"></i></a> <a><i
										class="icon-facebook2"></i></a> <a><i
										class="icon-instagram"></i></a> <a><i
										class="icon-dribbble2"></i></a> <a><i
										class="icon-youtube"></i></a>
								</p>
								<p>
									Seguici sulle nostre pagine social.
									<br>Per maggiori informazioni contattaci via email: <b>travelagencyingsw@gmail.com</b>
									<br>o via cellulare: <b>393 51623314</b>
								</p>
							</div>
						</div>
					</div>
				</div>
			</footer>
	

	</div>
	</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/jquery.cookie.js"></script>
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
	<script src="js/prenota.js"></script>
	<script src="js/ricaricaPage.js"></script>
	<script src="js/main.js"></script>
	<script src="js/controllaAccesso.js"></script>
</body>
</html>

