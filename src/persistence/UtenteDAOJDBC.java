package persistence;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PrenotazioneVolo;
import model.Utente;
import persistence.dao.UtenteDAO;

public class UtenteDAOJDBC implements UtenteDAO{

	private DataSource datasource;
	
	public UtenteDAOJDBC(DataSource dataSource) {
		datasource =  dataSource;
	}
	
	
	public void registraUtente(Utente utente) {
		Connection connection = this.datasource.getConnection();
		try {
			String query = "insert into public.utente(nome, cognome, data, username, password, email) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());;
			long secs = utente.getDataNascita().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, utente.getUsername());
			statement.setString(5, utente.getPassword());
			statement.setString(6, utente.getEmail());
			
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
	public Utente loginUtente(Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Utente findByPrimaryKey(String username) {
		Connection connection = this.datasource.getConnection();
		Utente utente = null;
		try {
			PreparedStatement statement;
			String query = "select * from utente where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setUsername(result.getString("username"));				
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setImmagineProfilo(result.getString("immagine"));
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
		return utente;
	}
	@Override
	public UtenteCredenziali findByPrimaryKeyCredential(String username) {
		Utente utente = findByPrimaryKey(username);
		UtenteCredenziali utenteCred = null;
		if (utente != null){
			utenteCred = new UtenteCredenziali(datasource);
			utenteCred.setUsername(utente.getUsername());
			utenteCred.setCognome(utente.getCognome());
			utenteCred.setNome(utente.getNome());
			utenteCred.setDataNascita(utente.getDataNascita());
			utenteCred.setPassword(utente.getPassword());
		}
		return utenteCred;
	}
	
	
	public void aggiornaImmagine(String path, String u) {
		Connection connection = this.datasource.getConnection();
		String query = "update public.utente set immagine = ? where username = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, path);
			ps.setString(2, u);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
	}


	@Override
	public boolean isPresent(String username) {
		boolean present = true;
		Connection connection = this.datasource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from public.utente where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				present = false;
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
		return present;
	}


	@Override
	public void creaCarrello(String username) {
		Connection connection = this.datasource.getConnection();
		String query = "insert into public.carrello(utente) values (?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
	}

	
	@Override
	public void svuotaCarrelloHotel(String username) {
		Connection connection = this.datasource.getConnection();
		String query = "delete from public.aggiuntacamera where aggiuntacamera.carrello = (select carrello.id from public.carrello where carrello.utente = ? )";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
		
		
	}
	
	@Override
	public void svuotaCarrello(String username) {
		Connection connection = this.datasource.getConnection();
		String query = "delete from public.carrello where carrello.utente = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
	}


	@Override
	public void aggiornaPassword(String username, String password) {
		Connection connection = this.datasource.getConnection();
		String query = "UPDATE public.utente\n" + 
				"	SET password=?" + 
				"	WHERE username=?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
	}


	@Override
	public void aggiornaEmail(String username, String email) {
		Connection connection = this.datasource.getConnection();
		String query = "UPDATE public.utente\n" + 
				"	SET email=?\n" + 
				"	WHERE username=?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
	}
	
	@Override
	public String getEmail(String username) {
		Connection connection = this.datasource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select email from utente where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return result.getString("email");
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
		return null;
	}

	@Override
	public void inserisciPrenotazioniEffettuate(String username) {
		Connection connection = this.datasource.getConnection();
		String query = "INSERT INTO public.prenotazioni_effettuate(\n" + 
				"	utente, prenotazione, tipologia_prenotazione)\n" + 
				"	SELECT carrello.utente, carrello.prenotazione, carrello.tipologia_prenotazione\n" +
				"	FROM public.carrello\n" +
				"	WHERE carrello.utente=?\n" +
				"	RETURNING id, prenotazioni_effettuate.tipologia_prenotazione";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("tipologia_prenotazione").equals("volo")) {
					int id = resultSet.getInt("id");
					int freeSeats = 0;
					int id_volo = 0;
					int id_pren = 0;

					String getSeatFree = "SELECT numero_posti, volo.id_volo, prenotazione_volo.id " +
							"FROM volo, prenotazione_volo, prenotazioni_effettuate " +
							"WHERE prenotazioni_effettuate.id = ? AND " +
							"	prenotazione = prenotazione_volo.id AND " +
							"	prenotazione_volo.id_volo = volo.id_volo";
					ps = connection.prepareStatement(getSeatFree);
					ps.setInt(1,id);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						freeSeats = rs.getInt("numero_posti");
						id_volo = rs.getInt("id_volo");
						id_pren = rs.getInt("id");
					}

					String assignSeat = "UPDATE public.prenotazione_volo SET posto = ? WHERE id = ?";
					ps = connection.prepareStatement(assignSeat);
					ps.setInt(1,freeSeats);
					ps.setInt(2,id_pren);
					ps.executeUpdate();

					String updateFreeSeat = "UPDATE public.volo SET numero_posti = ? WHERE id_volo = ?";
					ps = connection.prepareStatement(updateFreeSeat);
					ps.setInt(1,--freeSeats);
					ps.setInt(2,id_volo);
					ps.executeUpdate();

				}
			}

		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) {
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }	
			}
		}
		
	}	

}
