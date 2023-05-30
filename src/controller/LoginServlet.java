package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrenotazioneCamera;
import model.PrenotazioneVolo;
import model.Utente;
import persistence.dao.PrenotazioneHotelDAO;
import persistence.dao.PrenotazioneVoloDAO;
import persistence.dao.UtenteDAO;
import utility.PasswordManager;
import persistence.DAOFactory;
import persistence.UtenteCredenziali;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
@SuppressWarnings("unchecked")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = null;
		try {
			password = PasswordManager.getPasswordCrypto(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String carrello = request.getParameter("carrello");
		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		PrenotazioneVoloDAO prenotazioniVoloDao = factory.getPrenotazioneVoloDAO();
		PrenotazioneHotelDAO prenotazioneHotelDAO = factory.getPrenotazioneHotelDAO();
		
		UtenteCredenziali utenteCred = uDao.findByPrimaryKeyCredential(username);
		Utente utente = uDao.findByPrimaryKey(username);
		
		if (utenteCred == null) {
			String message = "Username o password non corretti.";
			request.setAttribute("Message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			if (utenteCred.getPassword().equals(password)) {
				Locale.setDefault(Locale.ITALIAN);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("nome", utente.getNome());
				request.getSession().setAttribute("cognome", utente.getCognome());
				request.getSession().setAttribute("email", utente.getEmail());
				request.getSession().setAttribute("data", utente.getDataStr());
				request.getSession().setAttribute("immagineProfilo", utente.getImmagineProfilo());
				request.setAttribute("utente", utenteCred);
				List<PrenotazioneVolo> carrelloVolo = (List<PrenotazioneVolo>) request.getSession().getAttribute("carrelloVolo");
				List<PrenotazioneCamera> carrelloCamere = (List<PrenotazioneCamera>) request.getSession().getAttribute("carrelloCamere");
				// svuoto cookie carrello 
				if (carrelloVolo != null) {
					for (Cookie c: request.getCookies()) {
						if (c.getName().equals("carrello")) {
							c.setValue(null);
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
					request.getSession().removeAttribute("carrelloVolo");
					request.getSession().removeAttribute("carrelloCamere");
					prenotazioniVoloDao.creaPrenotazioni(carrelloVolo,username);
					carrelloCamere.forEach(ch -> {
						ch.setUtente(username);
						prenotazioneHotelDAO.inserisciPrenotazioneNelCarrello(ch);
					});

				}
				carrelloVolo = prenotazioniVoloDao.getPrenotazioniVoliCarrello(username);
				request.getSession().setAttribute("carrelloVolo",carrelloVolo);

				carrelloCamere = prenotazioneHotelDAO.getHotelCarrello(username);
				request.getSession().setAttribute("carrelloCamere",carrelloCamere);
				
				List<PrenotazioneVolo> prenotazioniVolo = new ArrayList<>();
				prenotazioniVolo = prenotazioniVoloDao.getPrenotazioniEffettuate(username);
				request.getSession().setAttribute("prenotazioniVolo",prenotazioniVolo);
				List<PrenotazioneCamera> prenotazioniHotel = new ArrayList<>();
				prenotazioniHotel = prenotazioneHotelDAO.getPrenotazioniEffettuate(username);
				request.getSession().setAttribute("prenotazioniHotel",prenotazioniHotel);

				RequestDispatcher rd;

				if (carrello!=null && carrello.equals("true")) {
					rd = request.getRequestDispatcher("carrello.jsp");
				}
				else {
					rd = request.getRequestDispatcher("index.jsp");
				}
				rd.forward(request, response);
			}else {
				String message = "Username o password non corrette.";
				request.setAttribute("Message", message);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}	
	}

}