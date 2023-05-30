package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.PrenotazioneCamera;
import persistence.dao.PrenotazioneHotelDAO;

public class PrenotazioneHotelDAOJDBC implements PrenotazioneHotelDAO {
	private DataSource datasource;

	public PrenotazioneHotelDAOJDBC(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public void inserisciPrenotazioneNelCarrello(PrenotazioneCamera pv) {
		Connection connection = this.datasource.getConnection();
		try {
			String query = "insert into prenotazione_camera(checkin, checkout, prezzo, nome_cliente, cognome_cliente, id_camera, nascita_cliente) VALUES (?, ?, ?, ?, ?, ?, ?) returning id";
			String query2 = "insert into carrello(utente, prenotazione, tipologia_prenotazione) VALUES (?, ?, ?);";

			long checkinDate = pv.getCheckInDate().getTime();
			long checkoutDate = pv.getCheckOutDate().getTime();
			long nascitaDate = pv.getNascita().getTime();

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, new java.sql.Date(checkinDate));
			statement.setDate(2, new java.sql.Date(checkoutDate));
			statement.setInt(3, pv.getPrezzo());
			statement.setString(4, pv.getNome());
			statement.setString(5, pv.getCognome());
			statement.setInt(6, pv.getCodiceCamera());
			statement.setDate(7, new Date(nascitaDate));
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				pv.setCodicePrenotazione(resultSet.getInt("id"));
			}

			statement = connection.prepareStatement(query2);
			statement.setString(1, pv.getUtente());
			statement.setInt(2, pv.getCodicePrenotazione());
			statement.setString(3, "camera");
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public List<PrenotazioneCamera> getHotelCarrello(String username) {
		Connection connection = this.datasource.getConnection();
		List<PrenotazioneCamera> list = new ArrayList();
		try {
			PreparedStatement statement;
			String query = "select prenotazione_camera.*, camera.hotel, tipologia, citta " +
					"from carrello, prenotazione_camera, camera, hotel " +
					"where utente = ? and carrello.prenotazione = prenotazione_camera.id and id_camera = camera.id and camera.hotel = hotel.nome";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				long checkin = result.getDate("checkin").getTime();
				long checkout = result.getDate("checkout").getTime();
				long nascita = result.getDate("nascita_cliente").getTime();

				PrenotazioneCamera prenotazione = new PrenotazioneCamera();
				prenotazione.setCodicePrenotazione(result.getInt("id"));
				prenotazione.setLocalCode(false);
				prenotazione.setNome(result.getString("nome_cliente"));
				prenotazione.setCognome(result.getString("cognome_cliente"));
				prenotazione.setPrezzo(result.getInt("prezzo"));
				prenotazione.setCheckInDate(new java.util.Date(checkin));
				prenotazione.setCheckOutDate(new java.util.Date(checkout));
				prenotazione.setCodiceCamera(result.getInt("id_camera"));
				prenotazione.setNascita(new java.util.Date(nascita));
				prenotazione.setNomeHotel(result.getString("hotel"));
				prenotazione.setCitta(result.getString("citta"));
				prenotazione.setTipologia(result.getString("tipologia"));
				list.add(prenotazione);
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
		return list;
	}

	@Override
	public void eliminaPrenotazione(int id) {
		Connection connection = this.datasource.getConnection();
		try {
			String query = "delete from carrello where prenotazione = ? and tipologia_prenotazione = 'camera'";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();

			query = "delete from prenotazione_camera where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

	}

	@Override
	public List<PrenotazioneCamera> getPrenotazioniEffettuate(String username) {
		Connection connection = this.datasource.getConnection();
		List<PrenotazioneCamera> list = new ArrayList();
		try {
			PreparedStatement statement;
			String query = "select prenotazione_camera.*, camera.hotel, camera.tipologia, hotel.citta " +
					"from prenotazioni_effettuate, prenotazione_camera, camera, hotel " +
					"where utente = ? and prenotazioni_effettuate.prenotazione = prenotazione_camera.id and id_camera = camera.id and camera.hotel = hotel.nome";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				long checkin = result.getDate("checkin").getTime();
				long checkout = result.getDate("checkout").getTime();
				long nascita = result.getDate("nascita_cliente").getTime();

				PrenotazioneCamera prenotazione = new PrenotazioneCamera();
				prenotazione.setCodicePrenotazione(result.getInt("id"));
				prenotazione.setNome(result.getString("nome_cliente"));
				prenotazione.setCognome(result.getString("cognome_cliente"));
				prenotazione.setPrezzo(result.getInt("prezzo"));
				prenotazione.setCheckInDate(new java.util.Date(checkin));
				prenotazione.setCheckOutDate(new java.util.Date(checkout));
				prenotazione.setCodiceCamera(result.getInt("id_camera"));
				prenotazione.setNascita(new java.util.Date(nascita));
				prenotazione.setNomeHotel(result.getString("hotel"));
				prenotazione.setTipologia(result.getString("tipologia"));
				prenotazione.setCitta(result.getString("citta"));
				list.add(prenotazione);
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
		return list;
	}

	@Override
	public boolean verificaDisponibilitaCamera(int Id, java.util.Date checkin, java.util.Date checkout) {
		Connection connection = this.datasource.getConnection();
		try {
			PreparedStatement statement;
			String query = "SELECT id_camera " +
					"FROM prenotazione_camera, prenotazioni_effettuate " +
					"WHERE id_camera = ? AND prenotazione = prenotazione_camera.id AND " +
					"((? >= checkin AND ? <= checkout) OR " +
					"(? >= checkin AND ? <= checkout) OR " +
					"(? < checkin AND ? > checkout))";
			statement = connection.prepareStatement(query);
			statement.setInt(1,Id);
			statement.setDate(2, new Date(checkin.getTime()));
			statement.setDate(3, new Date(checkin.getTime()));
			statement.setDate(4, new Date(checkout.getTime()));
			statement.setDate(5, new Date(checkout.getTime()));
			statement.setDate(6, new Date(checkin.getTime()));
			statement.setDate(7, new Date(checkout.getTime()));


			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
