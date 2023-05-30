/**
 * 
 */

/*funzioni per implementare l'api di FLICKR*/

function JavaScriptFetch() {
	var script = document.createElement('script');
	script.src = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=" + document.getElementById("search").value;;
	document.querySelector('head').appendChild(script);
	}
function jsonFlickrFeed(data) {
	var image = "";
	var s = 1;
	data.items.forEach(function (element) {
	image += "<img src=\"" + element.media.m + "\"  onclick='setImmagine(this.id)' id ="+ s +">";
	s += 1;
	});
	document.getElementById("outputDiv").innerHTML = image;
}
/*funzione in profilo.jsp per settare l'immagine di un utente a quella selezionata*/
function setImmagine(s) {
	var pa = document.getElementById(s).getAttribute('src');
	
	document.getElementById('avatar_img').src = pa;
	$.ajax({
		type:"POST",
		url:"aggiornaImmagine",
		data:{p:pa},
		success: function(data) {
			$(location).attr('href','ProfiloServlet');
		}
	})
}