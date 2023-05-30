package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrenotazioneCamera;
import model.PrenotazioneVolo;
import model.Volo;
import persistence.DAOFactory;
import persistence.dao.PrenotazioneHotelDAO;
import persistence.dao.PrenotazioneVoloDAO;
import persistence.dao.UtenteDAO;
import persistence.dao.VoloDAO;

/**
 * Servlet implementation class ControlloPrenotazioni
 */
@WebServlet("/ControlloPrenotazioni")
public class ControlloPrenotazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloPrenotazioni() {
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
		// TODO Auto-generated method stub
		int totale = Integer.parseInt(request.getParameter("totale"));
		ArrayList<PrenotazioneVolo> voliCarrello = (ArrayList<PrenotazioneVolo>) request.getSession().getAttribute("carrelloVolo");
		ArrayList<PrenotazioneCamera> cameraCarrello = (ArrayList<PrenotazioneCamera>) request.getSession().getAttribute("carrelloCamere");
		ArrayList<PrenotazioneCamera> prenotazioniCamereDaEliminare = new ArrayList<>();
		ArrayList<PrenotazioneVolo> prenotazioniVoliDaEliminare = new ArrayList<>();
		ArrayList<PrenotazioneVolo> visualizzazioneErrore = new ArrayList<>();

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		PrenotazioneVoloDAO preVoDao = factory.getPrenotazioneVoloDAO();
		PrenotazioneHotelDAO preHoDao = factory.getPrenotazioneHotelDAO();
		VoloDAO voloDAO = factory.getVoloDAO();
		
		Date today = new Date(System.currentTimeMillis());
		for (PrenotazioneCamera cam: cameraCarrello) {
			if (cam.getCheckInDate().before(today) || !preHoDao.verificaDisponibilitaCamera(cam.getCodiceCamera(),cam.getCheckInDate(),cam.getCheckOutDate())) {
				prenotazioniCamereDaEliminare.add(cam);
			}
		}
		HashMap<Integer,ArrayList<PrenotazioneVolo>> idVoloPpostiPrenotazione = new HashMap<>();

		for (PrenotazioneVolo volo : voliCarrello) {
			try {
				volo.setDisponibilitaPosto(true);
				String voloDataPartenza = volo.getDataPartenza().substring(0,10);
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
				Date dataVolo = formatter.parse(voloDataPartenza);
				if (dataVolo.before(today)) {
					prenotazioniVoliDaEliminare.add(volo);
				} else {
					if (!idVoloPpostiPrenotazione.containsKey(volo.getIdVolo())) {
						idVoloPpostiPrenotazione.put(volo.getIdVolo(),new ArrayList<>());
					}
					idVoloPpostiPrenotazione.get(volo.getIdVolo()).add(volo);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		boolean verPosti = false;
		String nonDisponibili = "";
		int i=0;
		for (Integer k : idVoloPpostiPrenotazione.keySet()) {
			int postiDaPrenotare = idVoloPpostiPrenotazione.get(k).size();
			Volo v = voloDAO.cercaVoloDaId(k);
			int postiDisponibili = v.getNumeroPosti();
			if (postiDaPrenotare > postiDisponibili) {
				verPosti = true;
				nonDisponibili+=v.getPartenza() +"-"+v.getDestinazione()+" del "+v.getData()+", "+postiDisponibili+" posti disponibili.<br/>";
				idVoloPpostiPrenotazione.get(k).forEach( p -> {p.setDisponibilitaPosto(false);});
			}
		}

		response.setContentType("text/html;charset=UTF-8");

		if (prenotazioniCamereDaEliminare.size() > 0 || prenotazioniVoliDaEliminare.size() > 0 || verPosti ) {
			request.getSession().setAttribute("camereEliminate", prenotazioniCamereDaEliminare);
			request.getSession().setAttribute("voliEliminati", visualizzazioneErrore);
			for (PrenotazioneCamera prenotazioneCamera : prenotazioniCamereDaEliminare) {
				preHoDao.eliminaPrenotazione(prenotazioneCamera.getCodicePrenotazione());
				cameraCarrello.remove(prenotazioneCamera);
			}
			for (PrenotazioneVolo volo : prenotazioniVoliDaEliminare) {
				preVoDao.eliminaPrenotazione(volo.getCodicePrenotazione());
				voliCarrello.remove(volo);
			}

			ArrayList<Integer> idEliminati = new ArrayList<>();
			for (PrenotazioneVolo p : prenotazioniVoliDaEliminare) {
				if (!idEliminati.contains(p.getIdVolo())) {
					idEliminati.add(p.getIdVolo());
					visualizzazioneErrore.add(p);
				}
			}
			if (totale==0) {
				response.getWriter().write("carrello.jsp");
			}
			else {
				request.getSession().removeAttribute("carrelloVolo");
				request.getSession().setAttribute("carrelloVolo", voliCarrello);
				request.getSession().removeAttribute("carrelloCamere");
				request.getSession().setAttribute("carrelloCamere", cameraCarrello);
				if (verPosti) {
					response.getWriter().write("carrello.jsp?errore=true&errorePosti="+verPosti+"&nonDisponibili="+nonDisponibili);
				}
				else {
					response.getWriter().write("carrello.jsp?errore=true&errorePosti="+verPosti);
				}
			}
		} 
		else {
			if(totale != 0) {
				response.getWriter().write("pagamento.jsp?totale=" + totale);
			}
			else {
				response.getWriter().write("carrello.jsp");
			}
		}
	}

}
