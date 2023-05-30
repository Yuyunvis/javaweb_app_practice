package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Recensione;
import persistence.DAOFactory;
import persistence.dao.RecensioneDAO;

/**
 * Servlet implementation class ServletRecensioni
 */
@WebServlet("/ServletRecensioni")
public class ServletRecensioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecensioni() {
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
		String user = (String) request.getSession().getAttribute("username");
		String testo = request.getParameter("comment");
		String stelle = request.getParameter("rating");
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		RecensioneDAO r = factory.getRecensioneDAO();
		Recensione re = new Recensione();
		re.setUsername(user);
		re.setTesto(testo);
		re.setStelle(Integer.parseInt(stelle));
		r.aggiungiRecensione(re);
		RequestDispatcher rd = request.getRequestDispatcher("/profilo.jsp");
		rd.forward(request, response);
	}

}
