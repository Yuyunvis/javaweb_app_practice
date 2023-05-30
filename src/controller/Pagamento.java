package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrenotazioneCamera;
import model.PrenotazioneVolo;
import persistence.DAOFactory;
import persistence.dao.PrenotazioneHotelDAO;
import persistence.dao.PrenotazioneVoloDAO;
import persistence.dao.UtenteDAO;
import utility.EmailManager;

/**
 * Servlet implementation class SvuotaCarrello
 */
@WebServlet("/Pagamento")
public class Pagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagamento() {
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
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		PrenotazioneVoloDAO preVoDao = factory.getPrenotazioneVoloDAO();
		PrenotazioneHotelDAO preHoDao = factory.getPrenotazioneHotelDAO();
		
		String username = (String) request.getSession().getAttribute("username");
		String nome = (String) request.getSession().getAttribute("nome");
		String email = (String) request.getSession().getAttribute("email");
		uDao.inserisciPrenotazioniEffettuate(username);
		uDao.svuotaCarrello(username);
		
		request.getSession().removeAttribute("prenotazioniVolo");
		request.getSession().removeAttribute("prenotazioniHotel");
		request.getSession().removeAttribute("camereEliminate");
		request.getSession().removeAttribute("voliEliminati");
		List<PrenotazioneVolo> prenotazioniVolo = new ArrayList<>();
		List<PrenotazioneCamera> prenotazioniCamera = new ArrayList<>();
		prenotazioniVolo=preVoDao.getPrenotazioniEffettuate(username);
		prenotazioniCamera=preHoDao.getPrenotazioniEffettuate(username);

		List<PrenotazioneVolo> carrelloDaPagareVolo = new ArrayList<>(); 
		List<PrenotazioneCamera> carrelloDaPagareCamere = new ArrayList<>();
		carrelloDaPagareVolo = (List<PrenotazioneVolo>) request.getSession().getAttribute("carrelloVolo");
		carrelloDaPagareCamere = (List<PrenotazioneCamera>) request.getSession().getAttribute("carrelloCamere");
		for (PrenotazioneVolo carrello: carrelloDaPagareVolo)
			for (PrenotazioneVolo pagate: prenotazioniVolo)
				if (carrello.getCodicePrenotazione() == pagate.getCodicePrenotazione()) {
					carrello.setPosto(pagate.getPosto());
					break;
				}
		EmailManager.emailPagamentoEffettuato(email,carrelloDaPagareVolo,carrelloDaPagareCamere,nome);

		request.getSession().removeAttribute("carrelloVolo");
		request.getSession().removeAttribute("carrelloCamere");
		List<PrenotazioneVolo> carrelloVolo = new ArrayList<>();
		List<PrenotazioneCamera> carrelloCamere = new ArrayList<>();
		
		request.getSession().setAttribute("carrelloVolo", carrelloVolo);
		request.getSession().setAttribute("carrelloCamere", carrelloCamere);
		request.getSession().setAttribute("prenotazioniVolo", prenotazioniVolo);
		request.getSession().setAttribute("prenotazioniHotel", prenotazioniCamera);
		
		RequestDispatcher rd = request.getRequestDispatcher("/PagamentoEffettuato.jsp");
		rd.forward(request, response);
	}

}
