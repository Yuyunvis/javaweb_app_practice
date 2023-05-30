package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.Utente;
import persistence.DAOFactory;
import persistence.UtenteCredenziali;
import persistence.dao.UtenteDAO;
import utility.EmailManager;
import utility.PasswordManager;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		String username = req.getParameter("username");
		try {
			JSONObject jsonObject = new JSONObject();

			if (uDao.isPresent(username)) {
				jsonObject.put("username", "presente");
				resp.getWriter().println(jsonObject);
			} else {
				jsonObject.put("username", "no");
				resp.getWriter().println(jsonObject);
			}
			
		} catch (Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		String nome = request.getParameter("name");
		String cognome = request.getParameter("surname");
		String username = request.getParameter("username");
		String dataStr=request.getParameter("date");
		EmailManager emailManager = new EmailManager();
		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		UtenteDAO uDao = factory.getUtenteDAO();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
		try {
			Date dataNascita = format.parse(dataStr);
			Utente utente = new Utente(nome, cognome, dataNascita, username,PasswordManager.getPasswordCrypto(password),email);
			uDao.registraUtente(utente);
			emailManager.registerValidationEmail(utente,utente.getEmail());
			response.sendRedirect( request.getContextPath()+"/login.jsp");
			
		}catch (Exception e) { System.out.println(e);}
		
	}

}
