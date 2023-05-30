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
import persistence.dao.UtenteDAO;
import utility.EmailManager;
import utility.PasswordManager;

/**
 * Servlet implementation class ModificaEmail
 */
@WebServlet("/ModificaEmail")
public class ModificaEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaEmail() {
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
		EmailManager emailManager = new EmailManager();
		String username = (String) request.getSession().getAttribute("username");
		String nome = (String) request.getSession().getAttribute("nome");
		String email = (String) request.getSession().getAttribute("email");
		RequestDispatcher rd;
		String emailDaCambiare = request.getParameter("emaildacambiare");
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		Utente utente = uDao.findByPrimaryKey(username);
		
		if (utente == null) {
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			if (emailDaCambiare!= null && !emailDaCambiare.equals("")) {
				emailManager.emailInfo(email,nome,emailDaCambiare);
				uDao.aggiornaEmail(username, emailDaCambiare);
				emailManager.emailChanged(emailDaCambiare,nome);
				request.getSession().setAttribute("email", emailDaCambiare);
			}
			response.sendRedirect(request.getContextPath()+"/profilo.jsp");
			
		}
	
	}
}
