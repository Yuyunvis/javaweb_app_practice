package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Volo;
import persistence.DAOFactory;
import persistence.PersistenceException;
import persistence.dao.VoloDAO;

/**
 * Servlet implementation class CercaVolo
 */
@WebServlet("/CercaVolo")
public class CercaVolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaVolo() {
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
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String partenza = request.getParameter("from");
			String destinazione = request.getParameter("to");
			String dataA = request.getParameter("dateStart")+" 00:00:01";
			String dataR = request.getParameter("dateEnd");
			int posti = Integer.parseInt(request.getParameter("numeroBambini")) + Integer.parseInt(request.getParameter("numeroAdulti"));
			String stringa1= String.valueOf(partenza.charAt(0)).toUpperCase();
			for(int i=1; i<partenza.length(); i++)
				stringa1+=String.valueOf(partenza.charAt(i)).toLowerCase();
			partenza=stringa1;
			String stringa2= String.valueOf(destinazione.charAt(0)).toUpperCase();
			for(int i=1; i<destinazione.length(); i++)
				stringa2+=String.valueOf(destinazione.charAt(i)).toLowerCase();
			partenza=stringa1;
			destinazione=stringa2;
			DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
			VoloDAO v = factory.getVoloDAO();
			Date date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dataA);
			if (dataR!="") {
				dataR+=" 00:00:01";
				Date date2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dataR);
				List<Volo> voliRitorno = v.findFlight(destinazione, partenza, date2, posti);
				request.setAttribute("voliRitorno",voliRitorno);
			}
			List<Volo> voliAndata = v.findFlight(partenza,destinazione, date1, posti);
			
			request.setAttribute("voliAndata",voliAndata);
			
			request.setAttribute("cittaA", request.getParameter("from"));
			request.setAttribute("cittaB",request.getParameter("to"));
			request.setAttribute("dataA", request.getParameter("dateStart"));
			request.setAttribute("dataB", request.getParameter("dateEnd"));
			
			request.setAttribute("numeroAdulti", request.getParameter("numeroAdulti"));
			request.setAttribute("numeroBambini", request.getParameter("numeroBambini"));
			request.setAttribute("classe", request.getParameter("classe"));
			request.getSession().removeAttribute("camereEliminate");
			request.getSession().removeAttribute("voliEliminati");
			RequestDispatcher rd = request.getRequestDispatcher("/prenotaVolo.jsp");
			rd.forward(request, response);
			response.getWriter().write("True");
			} catch(PersistenceException e) {
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

}
