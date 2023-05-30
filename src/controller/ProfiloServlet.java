package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DAOFactory;
import persistence.UtenteCredenziali;
import persistence.dao.RecensioneDAO;
import persistence.RecensioneDAOJDBC;
import persistence.UtenteCredenziali;
import persistence.dao.UtenteDAO;

/**
 * Servlet implementation class ProfiloServlet
 */
@WebServlet("/ProfiloServlet")
public class ProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfiloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username =  (String) request.getSession().getAttribute("username");
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtenteDAO uDao = factory.getUtenteDAO();
		Utente utente = uDao.findByPrimaryKey(username);
		request.setAttribute("utente",username);
		request.getSession().setAttribute("immagineProfilo", utente.getImmagineProfilo());
		request.getRequestDispatcher("profilo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
