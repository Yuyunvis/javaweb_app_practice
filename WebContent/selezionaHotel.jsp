<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Travelife</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="css/visualizzazioneCamere.css">
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
                                    <h1 style="font-size:15px; margin-top:17px"><i><b>Ciao, ${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i>
                                    </h1>
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

        <!-- end:header-top -->

        <div id="fh5co-blog-section" class="fh5co-section-gray">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
                        <h3>I migliori hotel scelti per te.</h3>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row row-bottom-padded-md">
                    <c:if test="${numHotel>0}">
                        <c:forEach items="${hotels}" var="hot">
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="fh5co-blog animate-box">
                                    <a><img class="img-responsive" src="${hot.immagine}" alt=""></a>
                                    <div class="blog-text">
                                        <div class="prod-title">
                                            <h3><a>${hot.nome}</a></h3>
                                            <span class="posted_by">${hot.citta}</span>
                                            <span class="comment">
												<c:forEach begin="1" end="${hot.stelle}">
                                                    <a style="color:#f78536;font-size:20px;">&#9733;</a>
                                                </c:forEach>
											</span>
                                            <p>${hot.descrizione}</p>
                                            <a href="javascript:selectHotel('${hot.nome}')">
                                                <button class="btn btn-primary btn-outline btn-lg">vedi le camere
                                                </button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${numHotel==0}">
                        <div class="col-md-12 text-center animate-box">
                            <h1 style="font-size:17px; color:red;"><b>Nessun hotel trovato con i parametri inseriti</b>
                            </h1>
                        </div>
                    </c:if>
                </div>

                <div class="col-md-12 text-center animate-box">
                    <p><a class="btn btn-primary btn-outline btn-lg" href="index.jsp"><i class="icon-arrow-left22"></i>
                        Cerca altri hotel</a></p>
                </div>

            </div>
        </div>
        <div id="fh5co-tours" style="display: none;" class="fh5co-section-gray">
            <div class="container" id="selezionaCamere">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
                        <h3>Prenota la tua camera</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="wrapper">
                        <c:forEach items="${camere}" var="cam">
                            <div class="card ${cam.hotel}" style="display: none;">
                                <input type="checkbox" id="${cam.id}" class="more" aria-hidden="true">
                                <div class="content ${cam.hotel}" style="display: none;">
                                    <div class="front" style="background-image: url('${cam.immagine}')">
                                        <div class="inner">
                                            <label for="${cam.id}" class="button" aria-hidden="true">
                                                Seleziona
                                            </label>
                                            <div class="price">${cam.prezzo}&euro; / notte</div>
                                        </div>
                                    </div>
                                    <div class="back">
                                        <div class="inner">
                                            <div class="description">
                                                <p>${cam.descrizione}</p>
                                            </div>
                                            <form method="post">
                                                <div class="input-field name">
                                                    <label for="nome_${cam.id}">Nome:</label>
                                                    <input type="text" class="form-control" id="nome_${cam.id}"
                                                           name="input_nome" placeholder="Mario" title="Must be letters"
                                                           required/>
                                                </div>
                                                <div class="input-field surname">
                                                    <label for="cognome_${cam.id}">Cognome:</label>
                                                    <input type="text" class="form-control" id="cognome_${cam.id}"
                                                           name="input_cognome" placeholder="Rossi"/>
                                                </div>
                                                <div class="input-field birth">
                                                    <label for="nascita_${cam.id}">Data di nascita:</label>
                                                    <input type="date" max='2001-12-31' class="form-control"
                                                           name="nascita" id="nascita_${cam.id}"
                                                           placeholder="mm/dd/yyyy"
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
                                                </div>
                                                <label for="${cam.id}" class="button return" aria-hidden="true">
                                                    <i class="icon-arrow-left22"></i>
                                                </label>
                                                <input type="submit" id="conf_${cam.id}" class="button confirm"
                                                       value="Conferma" aria-hidden="true"/>
                                                <script>
                                                    document.getElementById('conf_${cam.id}').addEventListener("click", function (ev) {
                                                        ev.preventDefault();
                                                        selezionaCamera('${cam.id}', '${cam.prezzo}', '${cam.tipologia}', '${checkin}', '${checkout}');
                                                    });
                                                </script>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div id="confermaCamera" class="container" style="display: none">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
                        <h3>Conferma la tua prenotazione</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="wrapper">
                        <div class="card">
                            <div class="content">
                                <div class="front">
                                        <div class="riepilogo" method="post">
                                            <div class="ri-hotel">
                                                <h4>Hotel:</h4>
                                                <p id="riepilogo-hotel"></p>
                                            </div>
                                            <div class="ri-nome">
                                                <h4>Nome:</h4>
                                                <p id="riepilogo-nome"></p>
                                            </div>
                                            <div class="ri-cognome">
                                                <h4>Cognome:</h4>
                                                <p id="riepilogo-cognome"></p>
                                            </div>
                                            <div class="ri-nascita">
                                                <h4>Data di nascita:</h4>
                                                <p id="riepilogo-data"></p>
                                            </div>
                                            <div class="ri-giorni">
                                                <h4>Giorni:</h4>
                                                <p id="riepilogo-giorni"></p>
                                            </div>
                                            <div class="ri-conf">
                                                <button id="confCamera" onclick="confermaPrenotazione()" class="btn btn-primary btn-outline btn-lg">Conferma
                                                    Prenotazione
                                                </button>
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
                                <br>Per maggiori informazioni contattaci via email: <b>travellife@gmail.com</b>
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
<script src="js/prendiCamere.js"></script>
</body>
</html>