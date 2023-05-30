package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Camera;
import model.Hotel;
import model.PrenotazioneCamera;
import model.Volo;
import model.utility.HotelCamere;
import persistence.DAOFactory;
import persistence.PersistenceException;
import persistence.dao.CameraDAO;
import persistence.dao.HotelDAO;
import persistence.dao.VoloDAO;

/**
 * Servlet implementation class TrovaCamera
 */
@WebServlet("/CercaHotel")
public class CercaHotel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaHotel() {
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
        try {
            String username = (String) request.getSession().getAttribute("username");
            String citta = request.getParameter("citta");
            String checkin = request.getParameter("dataInizioHotel");
            String checkout = request.getParameter("dataFineHotel");
            String tipologia = request.getParameter("camera");
            StringBuilder stringa1 = new StringBuilder(String.valueOf(citta.charAt(0)).toUpperCase());

            for (int i = 1; i < citta.length(); i++)
                stringa1.append(String.valueOf(citta.charAt(i)).toLowerCase());

            citta = stringa1.toString();

            DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);

            HotelDAO h = factory.getHotelDAO();
            HashMap<Hotel, ArrayList<Camera>> hotelCamere = h.cercaHotel(citta, checkin, checkout, tipologia, username);
            ArrayList<PrenotazioneCamera> carrCamere = (ArrayList<PrenotazioneCamera>) request.getSession().getAttribute("carrelloCamere");

            List<Hotel> hotels = new ArrayList<>(hotelCamere.keySet());
            List<Camera> camere = new ArrayList<>();

            Date chinC = new SimpleDateFormat("yyyy-MM-dd").parse(checkin);
            Date chouC = new SimpleDateFormat("yyyy-MM-dd").parse(checkout);

            for (Hotel k : hotelCamere.keySet()) {
                for (Camera c : hotelCamere.get(k)) {
                    boolean isPresent = false;
                    for (PrenotazioneCamera p : carrCamere) {
                        if (p.getCodiceCamera() == c.getId()) {
                            if ((chinC.after(p.getCheckInDate()) && chinC.before(p.getCheckOutDate())) ||
                                    (chouC.after(p.getCheckInDate()) && chouC.before(p.getCheckOutDate())) ||
                                    (chinC.equals(p.getCheckInDate()) || chouC.equals(p.getCheckOutDate())) ||
                                    (chinC.before(p.getCheckInDate()) && chouC.after(p.getCheckOutDate()))) {
                                isPresent = true;
                            }
                        }
                    }
                    if (!isPresent) camere.add(c);
                }
            }

            if (camere.isEmpty()) hotels.clear();

            request.setAttribute("hotels", hotels);
            request.setAttribute("camere", camere);
            request.setAttribute("numHotel", hotels.size());
            request.setAttribute("checkin", checkin);
            request.setAttribute("checkout", checkout);
            request.setAttribute("tipologia", tipologia);
            request.setAttribute("citta", citta);
            request.getSession().removeAttribute("camereEliminate");
    		request.getSession().removeAttribute("voliEliminati");
    		
            RequestDispatcher rd = request.getRequestDispatcher("/selezionaHotel.jsp");
            rd.forward(request, response);
            response.getWriter().write("True");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
