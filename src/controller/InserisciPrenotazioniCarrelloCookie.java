package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CodPrenotazioneGenerator;
import model.PrenotazioneCamera;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.PrenotazioneVolo;

/**
 * Servlet implementation class InserisciVoloCarrelloCookie
 */
@WebServlet("/InserisciPrenotazioniCarrelloCookie")
public class InserisciPrenotazioniCarrelloCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciPrenotazioniCarrelloCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = reader.readLine();
		List<PrenotazioneVolo> carrelloVolo = null;
		List<PrenotazioneCamera> carrelloCamere = null;

		try {
			JSONObject jObject = new JSONObject(line);
			JSONArray listaVoli = (JSONArray) jObject.get("voli");
			JSONArray listaHotel = (JSONArray) jObject.get("camere");

			carrelloVolo = new ArrayList<>();
			carrelloCamere = new ArrayList<>();


			for (int i = 0; i < listaVoli.length(); i++) {
				JSONObject json = listaVoli.getJSONObject(i);
				PrenotazioneVolo pv= new PrenotazioneVolo();
				pv.setCodicePrenotazione(CodPrenotazioneGenerator.getInstance().generaCodice());
				pv.setLocalCode(true);
				pv.setIdVolo(json.getInt("id"));
				pv.setPartenza(json.getString("partenza"));
				pv.setDestinazione(json.getString("destinazione"));
				pv.setNomePasseggero(json.getString("nome"));
				pv.setCognomePasseggero(json.getString("cognome"));
				pv.setPrezzo(json.getInt("prezzo"));
				pv.setDataPartenza(json.getString("dataA"));
				pv.setDataNascita(json.getString("dataNascita"));
				carrelloVolo.add(pv);
			}

			for (int i = 0; i < listaHotel.length(); i++) {
				JSONObject json = listaHotel.getJSONObject(i);
				PrenotazioneCamera ch = new PrenotazioneCamera();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
				Date dataCheckIn = format.parse(json.getString("checkin"));
				Date dataCheckOut = format.parse(json.getString("checkout"));
				Date nascita = format.parse(json.getString("nascita"));

				ch.setCodicePrenotazione(CodPrenotazioneGenerator.getInstance().generaCodice());
				ch.setLocalCode(true);
				ch.setNomeHotel(json.getString("hotel"));
				ch.setCheckInDate(dataCheckIn);
				ch.setCheckOutDate(dataCheckOut);
				ch.setNome(json.getString("nome"));
				ch.setCognome(json.getString("cognome"));
				ch.setTipologia(json.getString("tipologia"));
				ch.setPrezzo(json.getInt("prezzo"));
				ch.setCodiceCamera(json.getInt("codiceCamera"));
				ch.setNascita(nascita);
				carrelloCamere.add(ch);
			}

		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}

		request.getSession().removeAttribute("carrelloVolo");
		request.getSession().removeAttribute("carrelloCamere");
		request.getSession().setAttribute("carrelloVolo", carrelloVolo);
		request.getSession().setAttribute("carrelloCamere", carrelloCamere);
	}

}
