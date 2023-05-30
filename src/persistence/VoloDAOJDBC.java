package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Volo;
import persistence.dao.VoloDAO;



public class VoloDAOJDBC implements VoloDAO{
	
	private DataSource datasource;
	
	public VoloDAOJDBC(DataSource dataSource) {
			datasource= dataSource;
	}
	
	@Override
	public List<Volo> findFlight(String partenza, String destinazione, Date data, int posti) {
		Connection connection = this.datasource.getConnection();
		Volo volo = null;
		List<Volo> voli = new ArrayList<>();
		try {
			PreparedStatement statement;
			String query = "select * from volo where partenza = ? and destinazione = ? and data >= ? and data <= ? and numero_posti >= ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, partenza);
			statement.setString(2, destinazione);
			Calendar cal = Calendar.getInstance();
	        cal.setTime(data);
	        cal.add(Calendar.DATE, 1);
			java.sql.Timestamp sDate = new java.sql.Timestamp(data.getTime());
			java.sql.Timestamp sDate2 = new java.sql.Timestamp(cal.getTime().getTime());
			statement.setTimestamp(3, sDate);
			statement.setTimestamp(4, sDate2);
			statement.setInt(5, posti);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				volo = new Volo();
				volo.setCodice(result.getInt("id_volo"));				
				volo.setPartenza(result.getString("partenza"));
				volo.setDestinazione(result.getString("destinazione"));
				volo.setPrezzo(result.getInt("prezzo"));
				volo.setCompagniaArea(result.getString("compagnia_aerea"));
				volo.setNumeroPosti(result.getInt("numero_posti"));
				volo.setDurata(result.getInt("durata"));
				volo.setData(result.getTimestamp("data"));
				volo.setDataArrivo(new Date(volo.getData().getTime() + (volo.getDurata() * 60000)));

				voli.add(volo);
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
		return voli;
	}

    @Override
    public Volo cercaVoloDaId(int id) {
        Connection connection = this.datasource.getConnection();
		Volo volo = new Volo();

		try {
			PreparedStatement statement;
			String query = "select * from volo where id_volo = ?;";
			statement = connection.prepareStatement(query);
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				volo.setCodice(resultSet.getInt("id_volo"));
				volo.setPartenza(resultSet.getString("partenza"));
				volo.setDestinazione(resultSet.getString("destinazione"));
				volo.setPrezzo(resultSet.getInt("prezzo"));
				volo.setCompagniaArea(resultSet.getString("compagnia_aerea"));
				volo.setNumeroPosti(resultSet.getInt("numero_posti"));
				volo.setDurata(resultSet.getInt("durata"));
				volo.setData(resultSet.getTimestamp("data"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return volo;
    }
}
