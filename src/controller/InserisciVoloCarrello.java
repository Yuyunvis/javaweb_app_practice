package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.PrenotazioneVolo;
import persistence.DAOFactory;
import persistence.dao.PrenotazioneVoloDAO;


/**
 * Servlet implementation class PrendiPrenotazione
 */
@WebServlet("/InserisciVoloCarrello")
public class InserisciVoloCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciVoloCarrello() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		int id = Integer.parseInt(request.getParameter("codice"));
		boolean disponibile = Boolean.parseBoolean(request.getParameter("disponibile"));
		List<PrenotazioneVolo> carrelloVolo = (List<PrenotazioneVolo>) request.getSession().getAttribute("carrelloVolo");
		PrenotazioneVolo daEliminare = new PrenotazioneVolo();
		response.setContentType("text/html;charset=UTF-8");
		for (PrenotazioneVolo p: carrelloVolo) {
			if (p.getCodicePrenotazione() == id) {
				daEliminare = p;
			}
		}
		if (daEliminare.isLocalCode()) {
			carrelloVolo.remove(daEliminare);
			JSONArray prenVol = new JSONArray();

			for (PrenotazioneVolo p : carrelloVolo) {
				JSONObject tmp = new JSONObject(p);

				try {
					tmp.put("id",tmp.getInt("idVolo"));
					tmp.put("nome",tmp.getString("nomePasseggero"));
					tmp.put("cognome",tmp.getString("cognomePasseggero"));
					tmp.put("dataA",tmp.getString("dataPartenza"));
					tmp.put("username","");

					tmp.remove("idVolo");
					tmp.remove("nomePasseggero");
					tmp.remove("cognomePasseggero");
					tmp.remove("dataPartenza");
					tmp.remove("localCode");
					tmp.remove("codicePrenotazione");

				} catch (Exception e) {
					e.printStackTrace();
				}
				prenVol.put(tmp);
			}

			for (Cookie k : request.getCookies()) {
				if (k.getName().equals("carrello")) {
					try {
						String ckstr = k.getValue();
						ckstr = URLDecoder.decode(ckstr,"UTF-8");
						JSONObject carrello = new JSONObject(ckstr);
						carrello.put("voli",prenVol);
						ckstr = URLEncoder.encode(carrello.toString(),"UTF-8");
						k.setValue(ckstr);
						response.addCookie(k);
						request.getSession().removeAttribute("carrelloVolo");
						request.getSession().setAttribute("carrelloVolo", carrelloVolo);
						response.getWriter().write("carrello.jsp");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} else {
			DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
			PrenotazioneVoloDAO preVoDao = factory.getPrenotazioneVoloDAO();
			preVoDao.eliminaPrenotazione(id);
			for (PrenotazioneVolo pV: carrelloVolo)
				if (id == pV.getCodicePrenotazione()) {
					daEliminare = pV;
					break;
				}
			carrelloVolo.remove(daEliminare);
			request.getSession().removeAttribute("carrelloVolo");
			request.getSession().setAttribute("carrelloVolo", carrelloVolo);
			if (!disponibile) {
				response.getWriter().write("controllo");
			}
			else {
				response.getWriter().write("carrello.jsp");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonReceived = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = reader.readLine();
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		PrenotazioneVoloDAO prenotazioniVoloDao = factory.getPrenotazioneVoloDAO();
		String username = (String) request.getSession().getAttribute("username");
		List<PrenotazioneVolo> prenotazioni = new ArrayList<PrenotazioneVolo>();
		JSONArray jsonA = null;
		while (line != null) {
			jsonReceived = jsonReceived + line + "\n";
			line = reader.readLine();
		}
		try {
			jsonA = new JSONArray(jsonReceived);
			for (int i=0; i<jsonA.length(); i++) {
				JSONObject json=jsonA.getJSONObject(i);
				PrenotazioneVolo pv= new PrenotazioneVolo();
				pv.setIdVolo(json.getInt("id"));
				pv.setPartenza(json.getString("partenza"));
				pv.setDestinazione(json.getString("destinazione"));
				pv.setNomePasseggero(json.getString("nome"));
				pv.setCognomePasseggero(json.getString("cognome"));
				pv.setPrezzo(json.getInt("prezzo"));
				pv.setDataPartenza(json.getString("dataA"));
				pv.setDataNascita(json.getString("dataNascita"));
				prenotazioni.add(pv);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().removeAttribute("carrelloVolo");
		prenotazioniVoloDao.creaPrenotazioni(prenotazioni, username);
		List<PrenotazioneVolo> carrelloVolo = prenotazioniVoloDao.getPrenotazioniVoliCarrello(username);
		request.getSession().setAttribute("carrelloVolo", carrelloVolo);
		response.getWriter().println(jsonA.toString());
	}
}
