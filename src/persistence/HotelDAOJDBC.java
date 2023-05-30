package persistence;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.Camera;
import model.Hotel;
import model.utility.HotelCamere;
import persistence.dao.HotelDAO;

public class HotelDAOJDBC implements HotelDAO{
	
	private DataSource datasource;
	
	public HotelDAOJDBC(DataSource dataSource) {
			datasource= dataSource;
	}

	@Override
	public HashMap<Hotel, ArrayList<Camera>> cercaHotel(String citta, String checkin, String checkout, String tipologia, String username) {
		
		Connection connection = this.datasource.getConnection();
		HashMap<Hotel,ArrayList<Camera>> result = new HashMap<>();
		try {
			PreparedStatement statement;
			String query = "select *\n" +
					"from hotel, camera\n" +
					"where hotel.citta = ? and camera.hotel=hotel.nome and camera.tipologia = ? and(\n" +
					"            camera.id not in (\n" +
					"            select prenotazione_camera.id_camera\n" +
					"            from prenotazione_camera)\n" +
					"        or camera.id not in (\n" +
					"        select prenotazione_camera.id_camera\n" +
					"        from prenotazione_camera, prenotazioni_effettuate\n" +
					"        where prenotazione_camera.id=prenotazioni_effettuate.prenotazione and prenotazioni_effettuate.tipologia_prenotazione='camera')\n" +
					"        or camera.id not in (\n" +
					"        select prenotazione_camera.id_camera\n" +
					"        from prenotazione_camera, prenotazioni_effettuate\n" +
					"        where (( ? >= prenotazione_camera.checkin and ? <= prenotazione_camera.checkout) or\n" +
					"               ( ? >= prenotazione_camera.checkin and ? <= prenotazione_camera.checkout) or\n" +
					"               ( ? <= prenotazione_camera.checkin and ? >= prenotazione_camera.checkout)) and\n" +
					"                prenotazione_camera.id=prenotazioni_effettuate.prenotazione and\n" +
					"                prenotazioni_effettuate.tipologia_prenotazione = 'camera'))";
			statement = connection.prepareStatement(query);
			statement.setString(1, citta);
			statement.setString(2, tipologia);
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date1=null;
			Date date2=null;
			try {
				date1 = (Date)formatter.parse(checkin);
				date2 = (Date)formatter.parse(checkout);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement.setDate(3, (new java.sql.Date(date1.getTime())));
			statement.setDate(4, (new java.sql.Date(date1.getTime())));
			statement.setDate(5, (new java.sql.Date(date2.getTime())));
			statement.setDate(6, (new java.sql.Date(date2.getTime())));
			statement.setDate(7, (new java.sql.Date(date1.getTime())));
			statement.setDate(8, (new java.sql.Date(date2.getTime())));
			
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Hotel hotel = new Hotel();
				hotel.setNome(resultSet.getString(1));
				hotel.setCitta(resultSet.getString(2));
				hotel.setImmagine(resultSet.getString(3));
				hotel.setDescrizione(resultSet.getString(4));
				hotel.setStelle(resultSet.getInt(5));

				Camera camera = new Camera();
				camera.setId(resultSet.getInt(6));
				camera.setTipologia(resultSet.getString(7));
				camera.setPrezzo(resultSet.getInt(8));
				camera.setImmagine(resultSet.getString(9));
				camera.setDescrizione(resultSet.getString(10));
				camera.setHotel(resultSet.getString(11));

				if (!result.containsKey(hotel)) result.put(hotel,new ArrayList<>());
				result.get(hotel).add(camera);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return result;
	}
}
