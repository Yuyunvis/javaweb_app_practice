package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.PrenotazioneCamera;
import persistence.DAOFactory;
import persistence.dao.PrenotazioneHotelDAO;

/**
 * Servlet implementation class InserisciHotelCarrello
 */
@WebServlet("/InserisciHotelCarrello")
public class InserisciHotelCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciHotelCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codice"));
		List<PrenotazioneCamera> carrelloCamere = (List<PrenotazioneCamera>) request.getSession().getAttribute("carrelloCamere");
		PrenotazioneCamera daEliminare = new PrenotazioneCamera();
		for (int i = 0; i < carrelloCamere.size(); i++) {
			PrenotazioneCamera tmp = carrelloCamere.get(i);
			if (tmp.getCodicePrenotazione() == id) {
				daEliminare = tmp;
				break;
			}
		}

		if (daEliminare.isLocalCode()) {
			carrelloCamere.remove(daEliminare);
			JSONArray prenCam = new JSONArray();

			for (PrenotazioneCamera p: carrelloCamere) {
				JSONObject tmp = new JSONObject(p);

				try {
					tmp.put("hotel",tmp.getString("nomeHotel"));
					tmp.put("checkin",tmp.getString("checkInString"));
					tmp.put("checkout",tmp.getString("checkOutString"));
					tmp.put("nascita",tmp.getString("nascitaString"));

					tmp.remove("utente");
					tmp.remove("checkInDate");
					tmp.remove("checkOutDate");
					tmp.remove("nomeHotel");
					tmp.remove("checkInString");
					tmp.remove("checkOutString");
					tmp.remove("nascitaString");
					tmp.remove("localCode");
					tmp.remove("codicePrenotazione");

				} catch (Exception e) {
					e.printStackTrace();
				}

				prenCam.put(tmp);
			}

			for (Cookie k: request.getCookies()) {
				if (k.getName().equals("carrello")) {
					try {
						String ckstr = k.getValue();
						ckstr = URLDecoder.decode(ckstr, "UTF-8");
						JSONObject carrello = new JSONObject(ckstr);
						carrello.put("camere",prenCam);
						ckstr = URLEncoder.encode(carrello.toString(),"UTF-8");
						k.setValue(ckstr);
						response.addCookie(k);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
			PrenotazioneHotelDAO preHoDao = factory.getPrenotazioneHotelDAO();
			preHoDao.eliminaPrenotazione(id);
			for (PrenotazioneCamera pC: carrelloCamere) {
				if (id == pC.getCodiceCamera()) {
					daEliminare=pC;
					break;
				}
			}
			carrelloCamere.remove(daEliminare);
		}
		request.getSession().removeAttribute("carrelloCamere");
		request.getSession().setAttribute("carrelloCamere", carrelloCamere);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = reader.readLine();
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		PrenotazioneHotelDAO preHoDao = factory.getPrenotazioneHotelDAO();
		String username = (String) request.getSession().getAttribute("username");

		try {
			JSONObject json = new JSONObject(line);
			PrenotazioneCamera ch = new PrenotazioneCamera();

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
			Date dataCheckIn = format.parse(json.getString("checkin"));
			Date dataCheckOut = format.parse(json.getString("checkout"));
			Date nascita = format.parse(json.getString("nascita"));

			ch.setNomeHotel(json.getString("hotel"));
			ch.setCheckInDate(dataCheckIn);
			ch.setCheckOutDate(dataCheckOut);
			ch.setNome(json.getString("nome"));
			ch.setCognome(json.getString("cognome"));
			ch.setTipologia(json.getString("tipologia"));
			ch.setUtente(username);
			ch.setPrezzo(json.getInt("prezzo"));
			ch.setCodiceCamera(json.getInt("codiceCamera"));
			ch.setNascita(nascita);
			
			preHoDao.inserisciPrenotazioneNelCarrello(ch);
			
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}

		List<PrenotazioneCamera> carrelloCamere = preHoDao.getHotelCarrello(username);
		request.getSession().removeAttribute("carrelloCamere");
		request.getSession().setAttribute("carrelloCamere", carrelloCamere);

	}

}