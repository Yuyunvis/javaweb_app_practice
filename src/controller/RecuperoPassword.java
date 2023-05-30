package controller;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DAOFactory;
import persistence.UtenteCredenziali;
import persistence.dao.UtenteDAO;
import utility.EmailManager;
import utility.PasswordManager;

/**
 * Servlet implementation class RecuperoPassword
 */
@WebServlet("/RecuperoPassword")
public class RecuperoPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final EmailManager emailManager = new EmailManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperoPassword() {
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
		// TODO Aggiungere requestDispatcher
        String username = request.getParameter("username");
		String password = "";
		EmailManager emailManager = new EmailManager();
		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		
		if(uDao.findByPrimaryKey(username)!=null) {
			password = emailManager.getPassword();
			try {
				uDao.aggiornaPassword(username, PasswordManager.getPasswordCrypto(password));
				emailManager.recoverPassword(uDao.getEmail(username), password, username);
				response.sendRedirect( request.getContextPath()+"/emailInviata.jsp");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			response.sendRedirect( request.getContextPath()+"/emailInviata.jsp");
		}
		
		
		
		
	}

}