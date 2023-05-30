<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Travelife</title>
<link rel="shortcut icon" href="favicon.ico">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
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
						<!-- START #fh5co-menu-wrap -->
						<nav id="fh5co-menu-wrap" role="navigation">
							<ul class="sf-menu" id="fh5co-primary-menu">
								<li>
								<c:if test="${username != null}">
									<h1 style="font-size:15px; margin-top:17px"><i><b>Ciao, ${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i></h1>
								</c:if>
								</li>
								<li>
									<c:if test="${username != null}">
									</c:if>
								</li>
								<li class="active"><a href="index.jsp">Home</a></li>
								<li><a href="recensioni.jsp">Dicono di noi</a></li>
								<li>
								<c:if test="${username != null}">
									<a href="profilo.jsp">Profilo</a>
								</c:if>
								<c:if test="${username == null}">
									<a href="iscrizione.jsp">Iscriviti</a>
								</c:if>	 
								</li>
								<li>
									<c:if test="${username == null}">
										<a href="login.jsp?cercaVolo=false&cercaHotel=false">Accedi</a>
									</c:if>	 
								</li>
								<li>
									<a href="carrello.jsp">Carrello</a>
								</li>
								<li>
									<c:if test="${username != null}">
										<a href="doLogin?logout=true">Logout</a>
									</c:if>
								</li>
								<!-- <li><a href="profilo.jsp">Profilo</a></li> -->
							</ul>
						</nav>
					</div>
				</div>
			</header>

			<!-- end:header-top -->

			<div class="fh5co-hero">
				<div class="fh5co-overlay"></div>
				<div class="fh5co-cover" data-stellar-background-ratio="0.5"
					style="background-image: url(images/personaAg.jpg);">
					<div class="desc">
						<div class="container">
							<div class="row">
								<div class="col-sm-5 col-md-5">
									<div class="tabulation animate-box">

										<!-- Nav tabs -->
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a
												href="#flights" onclick="emptyHotel()" aria-controls="flights" role="tab"
												data-toggle="tab">Volo</a></li>
											<li role="presentation"><a href="#hotels"
												aria-controls="hotels" role="tab" onclick="emptyFlight()" data-toggle="tab">Hotel</a>
											</li>
										</ul>

										<!-- Tab panes -->
										<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="flights">
													<form action="CercaVolo?cercaVolo=true" method="post" id="myform1">
													<div class="row">
														<div class="col-xxs-12 col-xs-6 mt">
															<div class="input-field">
																<label for="from">Da:</label> <input type="text"
																	class="form-control" name="from" id="from"
																	placeholder="Los Angeles, USA" />
															</div>
														</div>
														<div class="col-xxs-12 col-xs-6 mt">
															<div class="input-field">
																<label for="to">A:</label> <input type="text"
																	class="form-control" name="to" id="to"
																	placeholder="Tokyo, Japan" />
															</div>
														</div>
														<div class="col-xxs-12 col-xs-6 mt alternate">
															<div class="input-field">
																<label for="dateStart">Andata:</label> <input
																	type="date" class="form-control" name="dateStart"
																	placeholder="mm/dd/yyyy" id="dateStart"/>
																	<!-- style="background: rgba(0, 0, 0, 0.05); border: none; box-shadow: none; font-weight: bold; font-size: 14px; padding: 5px 10px !important; -webkit-border-radius: 0; -moz-border-radius: 0; -ms-border-radius: 0; border-radius: 0; color: #F78536 !important;" -->
															</div>
														</div>
														<div class="col-xxs-12 col-xs-6 mt alternate">
															<div class="input-field">
																<label for="dateEnd">Ritorno:</label> <input
																	type="date" class="form-control" name="dateEnd"
																	placeholder="mm/dd/yyyy" id="dateEnd"/>
															</div>
														</div>
														<div class="col-sm-12 mt">
															<section>
																<label for="class">Classe:</label> <select name="classe" class="cs-select cs-skin-border">
																	<option value="economy">Economy</option>
																	<option value="first">First</option>
																</select>
															</section>
														</div>
														<div class="col-xxs-12 col-xs-6 mt">
															<section>
																<label for="class">Adulti:</label> <select name="numeroAdulti"
																	class="cs-select cs-skin-border">
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																</select>
															</section>
														</div>
														<div class="col-xxs-12 col-xs-6 mt">
															<section>
																<label for="class">Bambini:</label>
																<select id="class" name="numeroBambini" class="cs-select cs-skin-border">
																	<option value="0">0</option>
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																</select>
															</section>
														</div>
														<div class="col-xs-12">
															<input type="submit" class="btn btn-primary btn-block"
																value="Inserisci tutti i parametri" name="vai" id="vai">
														</div>
													</div>
													</form>
												</div>
											<div role="tabpanel" class="tab-pane" id="hotels">
												<form action="CercaHotel" method="post" id="myform2">
													<div class="row">
														<div class="col-xxs-12 col-xs-12 mt">
															<div class="input-field">
																<label for="from">Città:</label> <input type="text"
																	id="citta" class="form-control" name="citta"
																	placeholder="Tokyo, Japan" />
															</div>
														</div>
														<div class="col-xxs-12 col-xs-6 mt alternate">
															<div class="input-field">
																<label for="dataInizioHotel">Check-In:</label> <input
																	type="date" class="form-control" id="dataInizioHotel"
																	name="dataInizioHotel"
																	placeholder="mm/dd/yyyy"/>
															</div>
														</div>
														<div class="col-xxs-12 col-xs-6 mt alternate">
															<div class="input-field">
																<label for="dataFineHotel">Check-Out:</label> <input
																	type="date" class="form-control" id="dataFineHotel"
																	name="dataFineHotel"
																	placeholder="mm/dd/yyyy"/>
															</div>
														</div>
														<div class="col-xxs-12 col-xs-12 mt">
															<section>
																<label for="rooms">Camera:</label>
																<select name="camera" id="rooms"
																	class="cs-select cs-skin-border">
																	<option value="singola">Singola</option>
																	<option value="doppia">Doppia</option>
																	<option value="familiare">Familiare</option>
																</select>
															</section>
														</div>
														<div class="col-xs-12"> 
															<input type="submit" class="btn btn-primary btn-block"
																value="Inserisci tutti i parametri" id="cercaHotel">
														</div>
													</div>
												</form>
											</div>
										</div>

									</div>
								</div>
								<div class="desc2 animate-box">
									<div class="col-sm-7 col-sm-push-1 col-md-7 col-md-push-1">
										<h2>
											<i><b>&ldquo;People don&#39;t take trips, trips take
												people&rdquo;</b></i>
										</h2>
										<h1>
											<br><b>John Steinbeck</b>
										</h1>
										<!-- <p><a class="btn btn-primary btn-lg" href="#">Get Started</a></p> -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div id="fh5co-tours" class="fh5co-section-gray">
				<div class="container">
					<div class="row">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h3>Scegli il viaggio che fa per te</h3>
							<p>Valuta tutte le nostre proposte per vivere vacanze da sogno</p>
						</div>
					</div>
				</div>
			</div>




			<div id="fh5co-destination">
				<div class="tour-fluid">
					<div class="row">
						<div class="col-md-12">
							<ul id="fh5co-destination-list" class="animate-box">
								<li class="one-forth text-center"
									style="background-image: url(images/berlino.jpeg);"><a>
										<div class="case-studies-summary">
											<h2>Berlino</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/madrid.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Madrid</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/Italy.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Roma</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/atene.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Atene</h2>
										</div>
								</a></li>

								<li class="one-forth text-center"
									style="background-image: url(images/lisbona.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Lisbona</h2>
										</div>
								</a></li>
								<li class="one-half text-center" >
									<div class="title-bg">
										<div class="case-studies-summary">
											<h2>Detinazioni preferite</h2>
										</div>
									</div>
								</li>
								<li class="one-forth text-center"
									style="background-image: url(images/Parigi.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Parigi</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/stoccolma.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Stoccolma</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/londra.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Londra</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/milano.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Milano</h2>
										</div>
								</a></li>
								<li class="one-forth text-center"
									style="background-image: url(images/praga.jpg);"><a>
										<div class="case-studies-summary">
											<h2>Praga</h2>
										</div>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div id="fh5co-features">
				<div class="container">
					<div class="row">
						<div class="col-md-4 animate-box">

							<div class="feature-left">
								<span class="icon"> <i class="icon-hotairballoon"></i>
								</span>
								<div class="feature-copy">
									<h3>Viaggi di famiglia</h3>
									<p>Offriamo mete pensando ai più grandi e ai più piccoli.</p>									
								</div>
							</div>

						</div>

						<div class="col-md-4 animate-box">
							<div class="feature-left">
								<span class="icon"> <i class="icon-search"></i>
								</span>
								<div class="feature-copy">
									<h3>Programma i tuoi viaggi</h3>
									<p>Con l'aiuto della nostra agenzia, organizzare i tuoi viaggi sarà facile e veloce.</p>
									
								</div>
							</div>
						</div>
						<div class="col-md-4 animate-box">
							<div class="feature-left">
								<span class="icon"> <i class="icon-wallet"></i>
								</span>
								<div class="feature-copy">
									<h3>Luna di miele</h3>
									<p>Scegli noi per passare la luna di miele come hai sempre sognato.</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 animate-box">

							<div class="feature-left">
								<span class="icon"> <i class="icon-wine"></i>
								</span>
								<div class="feature-copy">
									<h3>Viaggi di lavoro</h3>
									<p>Spostarti per riunioni di lavoro non sarà più uno stress.</p>
								</div>
							</div>

						</div>

						<div class="col-md-4 animate-box">
							<div class="feature-left">
								<span class="icon"> <i class="icon-genius"></i>
								</span>
								<div class="feature-copy">
									<h3>Viaggi solitari</h3>
									<p>Hai bisogno di staccare da tutti e da tutto? Facciamo al caso tuo! Guarda le nostre offerte 
									e organizza il viaggio che aiuterà a scoprire te stesso.</p>
									
								</div>
							</div>

						</div>
						<div class="col-md-4 animate-box">
							<div class="feature-left">
								<span class="icon"> <i class="icon-chat"></i>
								</span>
								<div class="feature-copy">
									<h3>Esploratore</h3>
									<p>Scoprire e conoscere il mondo sarà straordinario grazie a noi.</p>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<!-- fh5co-blog-section -->
			<!-- <div id="fh5co-testimonial">
				<div class="container">
					<div class="row animate-box">
						<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
							<h2>Happy Clients</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="box-testimony animate-box">
								<blockquote>
									<span class="quote"><span><i
											class="icon-quotes-right"></i></span></span>
									<p>&ldquo;Far far away, behind the word mountains, far from
										the countries Vokalia and Consonantia, there live the blind
										texts. Separated they live in Bookmarksgrove right at the
										coast of the Semantics, a large language ocean.&rdquo;</p>
								</blockquote>
								<p class="author">
									John Doe, CEO <a href="http://freehtml5.co/" target="_blank">FREEHTML5.co</a>
									<span class="subtext">Creative Director</span>
								</p>
							</div>

						</div>
						<div class="col-md-4">
							<div class="box-testimony animate-box">
								<blockquote>
									<span class="quote"><span><i
											class="icon-quotes-right"></i></span></span>
									<p>&ldquo;Far far away, behind the word mountains, far from
										the countries Vokalia and Consonantia, there live the blind
										texts.&rdquo;</p>
								</blockquote>
								<p class="author">
									John Doe, CEO <a href="http://freehtml5.co/" target="_blank">FREEHTML5.co</a>
									<span class="subtext">Creative Director</span>
								</p>
							</div>


						</div>
						<div class="col-md-4">
							<div class="box-testimony animate-box">
								<blockquote>
									<span class="quote"><span><i
											class="icon-quotes-right"></i></span></span>
									<p>&ldquo;Far far away, behind the word mountains, far from
										the countries Vokalia and Consonantia, there live the blind
										texts. Separated they live in Bookmarksgrove right at the
										coast of the Semantics, a large language ocean.&rdquo;</p>
								</blockquote>
								<p class="author">
									John Doe, Founder <a href="#">FREEHTML5.co</a> <span
										class="subtext">Creative Director</span>
								</p>
							</div>

						</div>
					</div>
				</div>
			</div> -->
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
		<!-- END fh5co-page -->

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
	<script src="js/main.js"></script>
	<script src="js/controlloRicercaVolo.js"></script>
	<script src="js/controlloRicercaHotel.js"></script>
	<script src="js/assignCookie.js"></script>

</body>
</html>

