<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Travelife</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FREEHTML5.CO"/>
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive"/>
    <meta name="author" content="FREEHTML5.CO"/>
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
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/modernizr-2.6.2.min.js"></script>
</head>
<body username="${username}" numeroBambini="${param.NumeroBambini}" partenza="${param.Partenza}"
      destinazione="${param.Destinazione}" dataB="${param.DataB}" dataA="${param.DataA}"
      numeroAdulti="${param.NumeroAdulti}" prezzo="${param.Prezzo}" prezzo1="${param.Prezzo1}" classe="${param.Classe}">
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
                                    <h1 style="font-size:15px; margin-top:17px"><i><b>Ciao, ${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i>
                                    </h1>
                                </c:if>
                            </li>
                            <li><a href="carrello.jsp">Carrello</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div class="fh5co-hero">
            <div class="fh5co-overlay"></div>
            <div class="fh5co-cover" data-stellar-background-ratio="0.5"
                 style="background-image: url(images/aeroporto.jpg);">
                <div class="desc">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-5 col-md-5">
                                <div class="tabulation animate-box" id="COMPILA">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li role="presentation" class="active">
                                            <a href="#compila" id="stato" aria-controls="flights" role="tab"
                                               data-toggle="tab">Compila i biglietti</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane active" id="compila">
                                            <div class="row">
                                                <h1 style="font-size:20px">DATI
                                                    PASSEGGERO -
                                                    <span id="adultoBambini">ADULTO</span>
                                                    <span id="numeroBiglietto">1</span></h1>
                                                <form method="post">
                                                    <div class="col-xxs-12 col-xs-6 mt">
                                                        <div class="input-field">
                                                            <label for="nome">Nome:</label>
                                                            <input type="text" class="form-control" id="name"
                                                                   name="input_nome" placeholder="Mario"
                                                                   title="Must be letters" required/>
                                                        </div>
                                                    </div>
                                                    <div class="col-xxs-12 col-xs-6 mt">
                                                        <div class="input-field">
                                                            <label for="cognome">Cognome:</label>
                                                            <input type="text" class="form-control" id="surname"
                                                                   name="input_cognome" placeholder="Rossi"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-xxs-12 col-xs-6 mt alternate">
                                                        <div class="input-field">
                                                            <label for="date-start">Data di nascita:</label>
                                                            <input type="date" class="form-control"
                                                                   name="input_dataNascita" id="date"
                                                                   placeholder="mm/dd/yyyy"/>
                                                        </div>
                                                    </div>
                                                </form>
                                                <c:if test="${param.Id1 == null}">
                                                    <div class="col-xs-12">
                                                        <c:if test="${(param.NumeroAdulti==1 && param.NumeroBambini==0)}">
                                                            <input type="submit" value="Procedi al pagamento"
                                                                   class="btn btn-primary btn-block" id="fine1"
                                                                   onclick="prendiPrenotazioneA(${param.Id},'${username}');">
                                                        </c:if>
                                                        <c:if test="${(param.NumeroAdulti>1 || param.NumeroBambini>0)}">
                                                            <input type="submit" value="Compila gli altri biglietti"
                                                                   class="btn btn-primary btn-block" id="fine1"
                                                                   onclick="prendiPrenotazioneA(${param.Id},'${username}');">
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                                <c:if test="${param.Id1 != null}">
                                                    <div class="col-xs-12">
                                                        <c:if test="${(param.NumeroAdulti==1 && param.NumeroBambini==0)}">
                                                            <input type="submit" value="Procedi al pagamento"
                                                                   class="btn btn-primary btn-block" id="fine1"
                                                                   onclick="prendiPrenotazioneAR(${param.Id},${param.Id1},'${username}');">
                                                        </c:if>
                                                        <c:if test="${(param.NumeroAdulti>1 || param.NumeroBambini>0)}">
                                                            <input type="submit" value="Compila gli altri biglietti"
                                                                   class="btn btn-primary btn-block" id="fine1"
                                                                   onclick="prendiPrenotazioneAR(${param.Id},${param.Id1},'${username}');">
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="tabulation animate-box" style="visibility: hidden" id="CONFERMA">
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li role="presentation" class="active">
                                            <a href="#conferma" id="stato" aria-controls="flights" role="tab"
                                               data-toggle="tab">Conferma i dati</a>
                                        </li>
                                    </ul>
                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane active" id="conferma">
                                            <div class="row" style="height:450px; overflow-x: hidden;">
                                                <h1 style="font-size:20px">
                                                    <i>Volo:<b>  ${param.Partenza}-${param.Destinazione}</b> </i>
                                                    <c:if test="${param.Id1 != null}">
                                                        <i><b>& ${param.Destinazione}-${param.Partenza}</b></i>
                                                    </c:if>
                                                </h1>
                                                <c:forEach begin="1" end="${param.NumeroAdulti}" varStatus="loop">
                                                    <h1 style="font-size:17px; margin-bottom:10px;"><b>PASSEGGERO ADULTO ${loop.index}</b>
                                                    </h1>
                                                    <h3 style="color:#5a5a5a; font-size:17px; font-style: italic;"
                                                        id="codice${loop.index}"></h3>
                                                </c:forEach>
                                                <c:forEach begin="1" end="${param.NumeroBambini}" varStatus="loop">
                                                    <h1 style="font-size:17px; margin-bottom:10px;"><b>PASSEGGERO BAMBINO ${loop.index}</b>
                                                    </h1>
                                                    <h3 style="color:#5a5a5a; font-size:17px; font-style: italic;"
                                                        id="codice1${loop.index}"></h3>
                                                </c:forEach>
                                                <c:if test="${param.Id1 == null}">
                                                    <h1 style="font-size:20px" id="totale"><b></b></h1>
                                                </c:if>
                                                <c:if test="${param.Id1 != null}">
                                                    <h1 style="font-size:20px" id="totale1"><b></b></h1>
                                                </c:if>
                                                <input type="submit" id="acquista" value="INSERISCI NEL CARRELLO"
                                                       class="btn btn-primary btn-block">
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
<script src="js/prenota.js"></script>
</body>
</html>