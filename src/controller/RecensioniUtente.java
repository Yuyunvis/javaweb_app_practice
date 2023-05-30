package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Recensione;
import persistence.DAOFactory;
import persistence.dao.RecensioneDAO;

/**
 * Servlet implementation class RecensioniUtente
 */
@WebServlet("/RecensioniUtente")
public class RecensioniUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecensioniUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		RecensioneDAO r = factory.getRecensioneDAO();
		String nomeutente = (String) request.getSession().getAttribute("username");
		ArrayList<Recensione> recensioni = r.recensioniPerUtente(nomeutente);

		LinkedList<JSONObject> listaJsonRecensioni= new LinkedList<>();
		for (Recensione recensione: recensioni) {
			JSONObject o=new JSONObject(recensione);
			listaJsonRecensioni.add(o);
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(listaJsonRecensioni);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
