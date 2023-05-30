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
	<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="css/cs-select.css">
	<link rel="stylesheet" href="css/cs-skin-border.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/registrazione.css">
	<script src="js/jquery-3.4.1.js"></script>
	<script src="js/modernizr-2.6.2.min.js"></script>
	</head>
	<body>
		<div id="fh5co-wrapper">
		<div id="fh5co-page">

		<header id="fh5co-header-section" class="sticky-banner">
			<div class="container">
				<div class="nav-header">
					<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
					<h1 id="fh5co-logo"><a href="index.jsp"><i class="icon-airplane"></i>Travelife</a></h1>
					<!-- START #fh5co-menu-wrap -->
					<nav id="fh5co-menu-wrap" role="navigation">
						<ul class="sf-menu" id="fh5co-primary-menu">
						</ul>
					</nav>
				</div>
			</div>
		</header>

		<!-- end:header-top -->
	
		<div class="fh5co-hero">
			<div class="fh5co-overlay"></div>
			<div class="fh5co-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/sfondoAspettare.jpg);">
				<div class="desc">
					<div class="container">
						<div class="row">
							<div class="col-sm-5 col-md-5">
								<div class="tabulation animate-box" id ="ciso">

								  <!-- Nav tabs -->
								   <ul class="nav nav-tabs" role="tablist">
								      <li role="presentation" class="active">
								    	   <a href="#accesso" aria-controls="hotels" role="tab" data-toggle="tab">Accedi</a>
								      </li>
								      
								   </ul>

								   <!-- Tab panes -->
									<div class="tab-content">

									 <div role="tabpanel" class="tab-pane active" id="accesso">
									 	<form method="post" action="LoginServlet?carrello=${param.carrello}">
										 	<div class="row">
												<div class="col-xxs-12 col-xs-12 mt">
													<div class="input-field">
														<label for="username">Username:</label>
														<input id="username"type="text" class="form-control" name="username" placeholder=""/>
														<label style="font-size:10px; color:red;" id='infoUsername'></label>
													</div>
												</div>
												<div class="col-xxs-12 col-xs-12 mt">
													<div class="input-field">
														<label for="password">Password:</label>
														<input type="password" class="form-control" id="password" name="password"
															style="background: rgba(0, 0, 0, 0.05);
														    border: none;
														    box-shadow: none;
														    font-weight: bold;
														    font-size: 14px;
														    padding: 5px 10px !important;
														    -webkit-border-radius: 0;
														    -moz-border-radius: 0;
														    -ms-border-radius: 0;
														    border-radius: 0;
														    color: #F78536 !important;"/>
														    <h5 style="font-size:12px; color:red; text-align:center; margin-top:5px; margin-bottom:5px;" id='infoLogin'><b>${Message}</b></h5>
													</div>
												</div>
												<div>
												<h5 class="text-center"><a href="iscrizione.jsp" style="color:#f78536;">Registrati</a></h5>
												<h5 class="text-center"><a href="recuperoPassword.jsp" style="color:#f78536;">Ho dimenticato la password</a></h5>
								
												
												</div>
												
												<div class="col-xs-12">
													<input type="submit" id="accedi" class="btn btn-primary btn-block" value="Entra">
												</div>
												
												
											</div>
										</form>
										
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
	<script src="js/login.js"></script>
	</body>
</html>