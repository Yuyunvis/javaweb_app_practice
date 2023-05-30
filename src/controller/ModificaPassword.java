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
 * Servlet implementation class ModificaPassword
 */
@WebServlet("/ModificaPassword")
public class ModificaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaPassword() {
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
		String email = (String) request.getSession().getAttribute("email");
		String nome = (String) request.getSession().getAttribute("nome");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		RequestDispatcher rd;
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		Utente utente = uDao.findByPrimaryKey(username);
		
		if (utente == null) {
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			if ((password1 != null && !password1.equals("")) && (password2 != null && !password2.equals("")) && (password1.equals(password2))) {
				try {
					uDao.aggiornaPassword(username, PasswordManager.getPasswordCrypto(password1));
					emailManager.passwordChangedEmail(nome,email,password1);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
			response.sendRedirect(request.getContextPath()+"/profilo.jsp");
			
		}
		
	}

}
