<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Travelife</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet"
	href="https://cdn.materialdesignicons.com/2.4.85/css/materialdesignicons.min.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="css/cs-select.css">
<link rel="stylesheet" href="css/cs-skin-border.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/modernizr-2.6.2.min.js"></script>
<script src="js/recensione.js"></script>
</head>


<body onload="cercaTutte()">

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
						<li><c:if test="${username != null}">
								<h1 style="font-size: 15px; margin-top: 17px">
									<i><b>Ciao,
											${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i>
								</h1>
							</c:if></li>
						<li><c:if test="${username != null}">
							</c:if></li>
						<li><a href="index.jsp">Home</a></li>
						<li class="active"><a href="recensioni.jsp">Dicono di
								noi</a></li>
						<li><c:if test="${username != null}">
								<a href="profilo.jsp">Profilo</a>
							</c:if> <c:if test="${username == null}">
								<a href="iscrizione.jsp">Iscriviti</a>
							</c:if></li>
						<li><c:if test="${username == null}">
								<a href="login.jsp?cercaVolo=false&cercaHotel=false">Accedi</a>
							</c:if></li>
						<li><a href="carrello.jsp">Carrello</a></li>
						<li><c:if test="${username != null}">
								<a href="doLogin?logout=true">Logout</a>
							</c:if></li>

					</ul>
				</nav>
			</div>
		</div>
	</header>

	<div id="fh5co-hero" data-stellar-background-ratio="0.5"
		style="background-image: url(images/recensione.jpeg);">
		<div id="">
			<div class="container">

				<br> <br>
				<div class="fh5co-overlay">
					<br> <br> <br>
				</div>

				<div class="fh5co-cover" style="height: 450px;">

					<div class="col-md-8 col-md-offset-2 text-center heading-section ">

					</div>
					<div class="container">
						<div class="row">
							<div
								class="col-md-8 col-md-offset-2 text-center heading-section ">
								<div class="tabulation animate-box fadeInUp animated" id="ciso">
									<ul class="nav nav-tabs" role="tablist">
										<li role="presentation" class="active"><a
											aria-controls="hotels" role="tab" data-toggle="tab">Recensioni
												dei nostri clienti</a></li>
									</ul>

									<div class="col-sm-12 col-md-12">
										<br>
									</div>
									
									<div class="col-md-4 filtro_stelle" style="color: white;">
										<!--  <h4 style="color:white;">Clicca sulle scritte per visualizzare le recensioni:</h4>-->
										<div>
										<h5>Clicca sulle numero di stelle per visualizzare le recensioni</h5>
										</div>
										<div id="5stelle" class="stelle_nel_filtro">
											<c:forEach begin="1" end="5">
												<label class="mdi mdi-star piene"
													style="color: #FFC107 !important;"></label>
											</c:forEach>
											<input type="button" value="5" id="5s"
												onclick="cerca(this.id)"><label class="filtri"
												for="5s" style="text-decoration: none !important;">5 stelle</label>
										</div>
										<div id="4stelle" class="stelle_nel_filtro">
											<c:forEach begin="1" end="4">
												<label class="mdi mdi-star piene"
													style="color: #FFC107 !important;"></label>
											</c:forEach>
											<label class="mdi mdi-star vuote"></label> <input
												type="button" value="4" id="4s" onclick="cerca(this.id)"><label
												class="filtri" for="4s" style="text-decoration: none !important;">4 stelle</label>
										</div>
										<div id="3stelle" class="stelle_nel_filtro">
											<c:forEach begin="1" end="3">
												<label class="mdi mdi-star piene"
													style="color: #FFC107 !important;"></label>
											</c:forEach>
											<c:forEach begin="1" end="2">
												<label class="mdi mdi-star vuote"></label>
											</c:forEach>
											<input type="button" value="3" id="3s"
												onclick="cerca(this.id)">
											<label class="filtri"
												for="3s" style="text-decoration: none !important;">3 stelle</label>
										</div>
										<div id="2stelle" class="stelle_nel_filtro">
											<c:forEach begin="1" end="2">
												<label class="mdi mdi-star piene"
													style="color: #FFC107 !important;"></label>
											</c:forEach>
											<c:forEach begin="1" end="3">
												<label class="mdi mdi-star vuote"></label>
											</c:forEach>
											<input type="button" value="2" id="2s"
												onclick="cerca(this.id)"><label class="filtri"
												for="2s" style="text-decoration: none !important;">2 stelle</label>
										</div>
										<div id="1stelle" class="stelle_nel_filtro">
											<label class="mdi mdi-star piene"
												style="color: #FFC107 !important;"></label>
											<c:forEach begin="1" end="4">
												<label class="mdi mdi-star vuote"></label>
											</c:forEach>
											<input type="button" value="1" id="1s"
												onclick="cerca(this.id)"><label class="filtri"
												for="1s" style="text-decoration: none !important;">1 stella</label>
										</div>
										<br>
										<div id="mostra_tutti" class="stelle_nel_filtro">
											<button class="btn btn-primary btn-sm" value="6" id="6s" onclick="cercaTutte()">Mostra tutte</button>
										</div>
										<br>
										
									</div>
									<div class="col-md-8 box_log" id="contenitore"
										style="height: 450px; overflow-x: hidden; padding-top: 20px">
										<c:forEach items="${rec}" var="value">
											<ul class="list-group" id="recensione_log">
												<li class="list-group-item text-muted"
													id="intestazione_review"><span id="nome_recensitore">${value.getUsername()}</span>
													<div id="stelline">
														<c:forEach begin="1" end="${value.getStelle()}"
															varStatus="loop">
															<label class="mdi mdi-star piene"
																style="color: #FFC107 !important;"></label>
														</c:forEach>
														<c:forEach begin="${value.getStelle()}" end="4"
															varStatus="loop">
															<label class="mdi mdi-star vuote"></label>
														</c:forEach>
													</div></li>
												<li class="list-group-item text-left" id="corpo_review"><span
													id="text_rec" class="pull-left">${value.getTesto()}</span></li>
											</ul>
										</c:forEach>
									</div>
									<div class="col-md-3"></div>

								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END fh5co-hero -->
			</div>



		</div>
		<!-- END fh5co-page -->


	</div>
	<!-- END fh5co-wrapper -->

	<footer>
		<div id="footer">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 text-center">
						<p class="fh5co-social-icons">
							<a><i class="icon-twitter2"></i></a> <a><i
								class="icon-facebook2"></i></a> <a><i class="icon-instagram"></i></a>
							<a><i class="icon-dribbble2"></i></a> <a><i
								class="icon-youtube"></i></a>
						</p>
						<p>
							Seguici sulle nostre pagine social. <br>Per maggiori
							informazioni contattaci via email: <b>travelagencyingsw@gmail.com</b>
							<br>o via cellulare: <b>393 51623314</b>
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>
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

</body>
</html>
