<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<%
    if (request.getSession().getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<head>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Travelife</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link
            href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
            rel='stylesheet' type='text/css'>

    <link rel="stylesheet"
          href="https://cdn.materialdesignicons.com/2.4.85/css/materialdesignicons.min.css">
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
    <script src="js/modernizr-2.6.2.min.js"></script>
    <script src="js/recensione.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            position: relative;
            background-color: #fefefe;
            margin: auto;
            padding: 0;
            border: 1px solid #888;
            width: 80%;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            -webkit-animation-name: animatetop;
            -webkit-animation-duration: 0.4s;
            animation-name: animatetop;
            animation-duration: 0.4s
        }

        /* Add Animation */
        @-webkit-keyframes animatetop {
            from {
                top: -300px;
                opacity: 0
            }
            to {
                top: 0;
                opacity: 1
            }
        }

        @keyframes animatetop {
            from {
                top: -300px;
                opacity: 0
            }
            to {
                top: 0;
                opacity: 1
            }
        }

        /* The Close Button */
        .close {
            color: #F78536;
            float: right;
            font-size: 36px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

		.tab-content .btn {
			letter-spacing: 1px;
			text-transform: uppercase;
			font-weight: bold;
			font-size: 12px;
		}

		h3 {
			color: #5a5a5a !important;
		}

        .modal-header {
            padding: 2px 16px;
            background-color: #5cb85c;
            color: white;
        }

        .modal-body {
            padding: 2px 16px;
        }

        .modal-footer {
            padding: 2px 16px;
            background-color: #5cb85c;
            color: white;
        }
    </style>
</head>
<body onload="cercaTuttePerUtente()">
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
                            <li>
                                <c:if test="${username != null}">
                                    <h1 style="font-size: 15px; margin-top: 17px">
									<i><b>Ciao,
											${username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></i>
								</h1>
                                </c:if>
                            </li>
                            <li><a href="index.jsp">Home</a></li>
                            <li><a href="recensioni.jsp">Dicono di noi</a></li>
                            <li class="active">
                                <c:if test="${username != null}">
                                    <a href="profilo.jsp">Profilo</a>
                                </c:if>
                                <c:if test="${username == null}">
                                    <a href="iscrizione.jsp">Iscriviti</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${username == null}">
                                    <a href="login.jsp?cerca=false">Accedi</a>
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
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div class="fh5co-hero">

            <div class="fh5co-cover" data-stellar-background-ratio="0.5"
                 style="background-image: url(images/personaAg1.jpg);">
                <div class="desc">
                    <div class="container">
                        <div class="row">


                            <div class="col-sm-12 col-md-12">
                                <div class="tabulation animate-box" id="ciso">

                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li role="presentation" class="active">
                                            <a href="#info" aria-controls="hotels" role="tab" data-toggle="tab">Informazioni</a>
                                        </li>
                                        <li>
                                            <a href="#prenotazioni" aria-controls="hotels" role="tab" data-toggle="tab">Prenotazioni</a>
                                        </li>
                                        <li>
                                            <a href="#recensioni" aria-controls="hotels" role="tab" data-toggle="tab">Recensioni</a>
                                        </li>
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content">

                                        <div role="tabpanel" class="tab-pane active" id="info">


                                            <div class="col-xs-6">
                                                <div class="text-center">
                                                    <c:if test="${immagineProfilo != null}">
                                                        <img src="${immagineProfilo}"
                                                             class="avatar img-circle img-thumbnail propic"
                                                             id="avatar_img" alt="avatar">
                                                    </c:if>
                                                    <c:if test="${immagineProfilo == null}">
                                                        <img src="images/avatar.png"
                                                             class="avatar img-circle img-thumbnail propic"
                                                             id="avatar_img" alt="avatar">
                                                    </c:if>

                                                    <p></p>

                                                    <!--  <button id="submit"  type="button" class="btn btn-primary btn-block" onclick="JavaScriptFetch()">Cerca</button>  -->
                                                </div>
												<div class="text-center">
													<button id="myBtn" type="button" class="btn btn-primary btn-sm">
														Modifica foto
													</button>
												</div>
                                                <div id="myModal" class="modal">

                                                    <!-- Modal content -->
                                                    <div class="modal-content">
                                                        <div class="tab-content">
                                                            <span class="close">&times;</span>

                                                            <h3><font color="#F78536">Scegli l' immagine del profilo da
                                                                Flickr</font></h3>

                                                            <input id="search" class="form-control" type="text"
                                                                   placeholder="Cerca con Flickr"/>
                                                            <p></p>

                                                            <p></p>


                                                            <div class="col-sm-10 col-md-12 text-center">
                                                                <div id="outputDiv" class="text-center"></div>
                                                                <br></br>
                                                            </div>
                                                            <button id="submit" type="button"
                                                                    class="btn btn-primary btn-block"
                                                                    onclick="JavaScriptFetch()">Cerca
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-xs-3">
                                                <h3>Username</h3>
												<h4><b><i>${username}</i></b></h4>
                                            </div>
                                            <div class="col-xs-3">
                                                <h3>Cognome</h3>
												<h4><b><i>${cognome}</i></b></h4>
                                            </div>
                                            <div class="col-xs-3">
                                                <h3>Data di nascita</h3>
												<h4><b><i>${data}</i></b></h4>
                                            </div>
                                            <div class="col-xs-3">
                                                <h3>Nome</h3>
												<h4><b><i>${nome}</i></b></h4>
                                            </div>

                                            <form name="Mod" action="ModificaEmail" method="post">
                                                <div class="col-xs-6"><p></p></div>
                                                <div class="col-xs-3">
                                                    <h3 style="margin-bottom: 22px">Email</h3>
													<input id="emaildacambiare" name="emaildacambiare"
														   style="font-size:15px;" class="form-control" type="text"
														   placeholder='${email}'/><label
                                                                style="font-size:10px; color:red;"
                                                                id='infoEmail'></label>
                                                </div>
                                                <div class="col-xs-3" style="height: 91px">
                                                        <button id="submit1" type="submit" style="margin-top: 45px"
                                                                class="btn btn-primary btn-sm">Modifica e-mail
                                                        </button>
                                                </div>
                                                <div class="col-xs-12"><p></p></div>


                                            </form>
                                            <form name="Mod1" action="ModificaPassword" method="post">
                                                <div class="col-xs-4">
                                                    <h4>Nuova password</h4>
                                                    <!-- 	<p><h1 style="font-size:15px;"><b><i>${email}</i></b></h1>	 -->
                                                    <p>
                                                    <h1 style="font-size:15px;"><b>
                                                    <input name="password1" id="password1" style="font-size:15px; background: rgba(0, 0, 0, 0.05);
														    border: none;
														    box-shadow: none;
														    font-weight: bold;
														    font-size: 14px;
														    padding: 5px 10px !important;
														    -webkit-border-radius: 0;
														    -moz-border-radius: 0;
														    -ms-border-radius: 0;
														    border-radius: 0;
														    color: #F78536 !important;" class="form-control" type="password" placeholder="Inserisci la nuova password"/>
														    </b>
														    <label style="font-size:10px; color:red;" id='infoPassword'></label>
                                                    </h1>
                                                </div>
                                                <p></p>
                                                <div class="col-xs-4">
                                                    <h4>Ripeti nuova password</h4>
                                                    <p>
                                                    <h1 style="font-size:15px;"><b><input name="password2"
                                                                                          id="password2" style="font-size:15px; background: rgba(0, 0, 0, 0.05);
														    border: none;
														    box-shadow: none;
														    font-weight: bold;
														    font-size: 14px;
														    padding: 5px 10px !important;
														    -webkit-border-radius: 0;
														    -moz-border-radius: 0;
														    -ms-border-radius: 0;
														    border-radius: 0;
														    color: #F78536 !important;" class="form-control"
                                                                                          type="password"
                                                                                          placeholder="Reinserisci la nuova password"/></b>
                                                    </h1>
                                                </div>

                                                <div class="col-xs-1"><p></p></div>
                                                <div class="col-xs-3">
 													<button id="submit2" type="submit" style="margin-top: 45px"
															class="btn btn-primary btn-sm">Modifica password
													</button>
                                                </div>
                                            </form>
                                        </div>


                                        <div role="tabpanel" class="tab-pane" id="prenotazioni"
                                             style="overflow-x: hidden;">
                                            <div class="col-xs-6">
                                                <c:if test="${prenotazioniVolo.size()==0}">
                                                    <h4><b>Nessun volo prenotato.</b></h4>
                                                </c:if>
                                                <c:if test="${prenotazioniVolo.size()>0}">
                                                    <h4><b>Voli</b></h4>
                                                    <div style="height:450px; overflow-x: hidden; padding-top:20px">

                                                        <c:forEach items="${prenotazioniVolo}" var="volo">
                                                            <h4><i>${volo.partenza}-${volo.destinazione} ${volo.dataPartenza}</i><br>
                                                                Passeggero:
                                                                <b><i>${volo.nomePasseggero} ${volo.cognomePasseggero}</i></b><br>
                                                                Prezzo: <b><i>${volo.prezzo}&euro;</i></b><br>
                                                                Posto: <b><i>${volo.posto}</i></b></h4>
                                                        </c:forEach>

                                                    </div>
                                                </c:if>
                                            </div>
                                            <div class="col-xs-6">
                                                <c:if test="${prenotazioniHotel.size()==0}">
                                                    <h4><b>Nessuna camera prenotata.</b></h4>
                                                </c:if>
                                                <c:if test="${prenotazioniHotel.size()>0}">
                                                    <h4><b>Hotel</b></h4>
                                                    <div style="height:450px; overflow-x: hidden; padding-top:20px">
                                                        <c:forEach items="${prenotazioniHotel}" var="hotel">
                                                            <h4><i>Hotel ${hotel.nomeHotel}, ${hotel.citta}</i><br>
                                                            	Tipologia camera: <b><i>${hotel.tipologia}</i></b><br>
                                                                Da: <b><i>${hotel.checkInString}</i></b><br>
                                                                A: <b><i>${hotel.checkOutString}</i></b><br>
                                                                Prezzo: <b><i>${hotel.prezzo}&euro;</i></b><br></h4>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>


                                        <div role="tabpanel" class="tab-pane" id="recensioni">

                                            <div class="col-md-12">
                                                <div class="col-xs-4">
                                                    <div class="well well-sm" style="margin-top: 5%;">
                                                        <div class="text-center">
                                                            <a class="btn btn-primary btn-block"
                                                               id="open-review-box">Scrivi una recensione</a>
                                                        </div>
                                                        <div class="row" id="post-review-box" style="display: none;">
                                                            <div class="col-md-12">
                                                                <form id="recensioni" method="post"
                                                                      action="ServletRecensioni">
											<textarea class="form-control animated" cols="50"
                                                      id="new-review" name="comment" required
                                                      placeholder="Inserisci la tua recensione..." rows="5"></textarea>

                                                                    <div class="col-md-12">
                                                                        <div class="rating-css hover-effect">
                                                                            <input type="radio" value="1" id="rating1"
                                                                                   name="rating"
                                                                                   checked> <label for="rating1"
                                                                                                   class="mdi mdi-star"></label>
                                                                            <input type="radio" value="2" id="rating2"
                                                                                   name="rating">
                                                                            <label for="rating2"
                                                                                   class="mdi mdi-star"></label> <input
                                                                                type="radio" value="3" id="rating3"
                                                                                name="rating">
                                                                            <label for="rating3"
                                                                                   class="mdi mdi-star"></label> <input
                                                                                type="radio" value="4" id="rating4"
                                                                                name="rating">
                                                                            <label for="rating4"
                                                                                   class="mdi mdi-star"></label> <input
                                                                                type="radio" value="5" id="rating5"
                                                                                name="rating">
                                                                            <label for="rating5"
                                                                                   class="mdi mdi-star"></label>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-12">
                                                                        <button type="submit"
                                                                                class="btn btn-primary btn-block">Invia
                                                                        </button><!--btn btn-success btn-green rew-->
                                                                        <button class="btn btn-primary btn-block"
                                                                                style="background-color:#F73B3B"
                                                                                id="close-review-box">Chiudi
                                                                        </button>


                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-8">

                                                    <h4>Recensioni già inserite</h4>

                                                    <div class="col-md-12 box_log"
                                                         style="height:450px; overflow-x: hidden; padding-top:20px"
                                                         id="contenitore">
                                                        <c:forEach items="${rec}" var="value">
                                                            <ul class="list-group" id="recensione_log">
                                                                <li class="list-group-item text-muted"
                                                                    id="intestazione_review"><span
                                                                        id="nome_recensitore">${value.getUsername()}</span>
                                                                    <div id="stelline">
                                                                        <c:forEach begin="1" end="${value.getStelle()}"
                                                                                   varStatus="loop">
                                                                            <label class="mdi mdi-star piene"></label>
                                                                        </c:forEach>
                                                                        <c:forEach begin="${value.getStelle()}" end="4"
                                                                                   varStatus="loop">
                                                                            <label class="mdi mdi-star vuote"></label>
                                                                        </c:forEach>
                                                                    </div>
                                                                </li>
                                                                <li class="list-group-item" id="corpo_review"><span
                                                                        id="text_rec"
                                                                        class="pull-left">${value.getTesto()}</span>
                                                                </li>
                                                            </ul>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <!--  <p id="avviso"></p> -->
                                            </div>
                                        </div>

                                    </div>
                                </div>

                                <!-- END col-md-5 -->
                            </div>


                            <!-- row-->
                        </div>

                        <!-- container-->
                    </div>
                    <!-- desc-->
                </div>


                <!-- END fh5co-cover-->
            </div>

            <!-- END fh5co-hero-->

        </div>


    </div>
    <!-- END fh5co-page -->


</div>
<footer>
    <div id="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 text-center">
                    <p class="fh5co-social-icons">
                        <a><i class="icon-twitter2"></i></a> <a><i class="icon-facebook2"></i></a> <a><i
                            class="icon-instagram"></i></a> <a><i class="icon-dribbble2"></i></a> <a><i
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
<script src="js/ImmagineProf.js"></script>
<script src="js/validatePassword.js"></script>


<script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>

