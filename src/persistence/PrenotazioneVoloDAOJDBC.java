package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Volo;
import persistence.dao.PrenotazioneVoloDAO;
import model.PrenotazioneVolo;
import model.Utente;

public class PrenotazioneVoloDAOJDBC implements PrenotazioneVoloDAO {

	private DataSource datasource;
	public PrenotazioneVoloDAOJDBC(DataSource datasource) {
		this.datasource = datasource;
	}
	
	@Override
	public void inserisciPrenotazioneNelCarrello(int id, String username) {
		Connection connection = this.datasource.getConnection();
		try {
			String query = "INSERT INTO public.carrello(\n" + 
					"	utente, prenotazione, tipologia_prenotazione)\n" + 
					"	VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setInt(2, id);
			statement.setString(3, "volo");
			statement.executeUpdate();
			
		}catch (SQLException e) {
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
	public List<PrenotazioneVolo> getPrenotazioniVoliCarrello(String username) {

		Connection connection = this.datasource.getConnection();
		List<PrenotazioneVolo> list = new ArrayList();
		try {
			PreparedStatement statement;
			String query = "SELECT prenotazione_volo.*, volo.partenza, volo.destinazione, volo.data\n" +
					"FROM prenotazione_volo, carrello, volo\n" +
					"WHERE utente = ? and prenotazione_volo.id = prenotazione and\n" +
					"	prenotazione_volo.id_volo = volo.id_volo";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				PrenotazioneVolo prenotazione = new PrenotazioneVolo();
				prenotazione.setCodicePrenotazione(result.getInt("id"));
				prenotazione.setIdVolo(result.getInt("id_volo"));
				prenotazione.setPartenza(result.getString("partenza"));
				prenotazione.setPrezzo(result.getInt("prezzo"));
				prenotazione.setDestinazione(result.getString("destinazione"));
				prenotazione.setNomePasseggero(result.getString("nome_passeggero"));
				prenotazione.setCognomePasseggero(result.getString("cognome_passeggero"));
				prenotazione.setDataNascita(result.getString("data_nascita"));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date partenza = new Date(result.getTimestamp("data").getTime());
				prenotazione.setDataPartenza(sdf.format(partenza).replace(" ", ", ore: "));
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
			String query = "delete from carrello where prenotazione = ? and tipologia_prenotazione = 'volo'";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1,id);
			statement.executeUpdate();

			query = "delete from prenotazione_volo where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			
		}catch (SQLException e) {
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
	public void creaPrenotazioni(List<PrenotazioneVolo> carrelloVolo, String username) {
		// TODO Auto-generated method stub
		Connection connection = this.datasource.getConnection();
		try {
			for (PrenotazioneVolo pv: carrelloVolo) {
				String query = "INSERT INTO public.prenotazione_volo(nome_passeggero, cognome_passeggero, id_volo, data_nascita, prezzo)VALUES (?, ?, ?, ?, ?) RETURNING id;";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, pv.getNomePasseggero());
				statement.setString(2, pv.getCognomePasseggero());
				statement.setInt(3, pv.getIdVolo());
				statement.setString(4, pv.getDataNascita());
				statement.setInt(5, pv.getPrezzo());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					inserisciPrenotazioneNelCarrello(result.getInt("id"), username);
				}
			}
			
		}catch (SQLException e) {
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
	public List<PrenotazioneVolo> getPrenotazioniEffettuate(String username) {
		Connection connection = this.datasource.getConnection();
		List<PrenotazioneVolo> list = new ArrayList();
		try {
			PreparedStatement statement;
			String query = "SELECT prenotazione_volo.id, prenotazione_volo.nome_passeggero, prenotazione_volo.cognome_passeggero, prenotazione_volo.data_nascita, prenotazione_volo.prezzo, posto, \n" +
					"	volo.partenza, volo.destinazione, volo.id_volo,\n" + 
					"	volo.data" + 
					"	FROM prenotazione_volo, prenotazioni_effettuate, volo\n" + 
					"		WHERE prenotazioni_effettuate.utente=? and prenotazione_volo.id=prenotazioni_effettuate.prenotazione and \n" + 
					"			prenotazioni_effettuate.tipologia_prenotazione='volo' and prenotazione_volo.id_volo=volo.id_volo";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				PrenotazioneVolo prenotazione = new PrenotazioneVolo();
				prenotazione.setCodicePrenotazione(result.getInt("id"));
				prenotazione.setIdVolo(result.getInt("id_volo"));
				prenotazione.setPosto(result.getInt("posto"));
				prenotazione.setPartenza(result.getString("partenza"));
				prenotazione.setPrezzo(result.getInt("prezzo"));
				prenotazione.setDestinazione(result.getString("destinazione"));
				prenotazione.setNomePasseggero(result.getString("nome_passeggero"));
				prenotazione.setCognomePasseggero(result.getString("cognome_passeggero"));
				prenotazione.setDataNascita(result.getString("data_nascita"));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date partenza = new Date(result.getTimestamp("data").getTime());
				prenotazione.setDataPartenza(sdf.format(partenza).replace(" ", ", ore: "));

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
	
}
