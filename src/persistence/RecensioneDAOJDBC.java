package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Recensione;
import model.Utente;
import persistence.dao.RecensioneDAO;

public class RecensioneDAOJDBC implements RecensioneDAO {
	

	private DataSource datasource;
	public RecensioneDAOJDBC(DataSource dataSource) {
			datasource= dataSource;
	}
	
	public void aggiungiRecensione(Recensione r) {
		Connection connection = this.datasource.getConnection();
		String query = "insert into recensione(utente,testo,stelle) values (?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,r.getUsername());
			statement.setString(2, r.getTesto());
			statement.setInt(3, r.getStelle());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if (connection != null) 
	              try {
	            	  connection.close();
	              } catch (SQLException e) {
	            	  throw new PersistenceException(e.getMessage());
	           }			}
	}
	public int[] recensioniPerStelle() {
		int recensioni[] = new int [5];
		for(int i = 0; i < 5; i++) {
			recensioni[i] = 0;
		}
		ArrayList<Recensione> r = getAll();
		if(!(r.isEmpty())) {
		for(Recensione s : r) {
			if(s.getStelle() == 1) {
				recensioni[0]++;
			} else if(s.getStelle() == 2) {
				recensioni[1]++;
			} else if(s.getStelle() == 3) {
				recensioni[2]++;
			} else if(s.getStelle() == 4) {
				recensioni[3]++;
			} else if(s.getStelle() == 5) {
				recensioni[4]++;
			}
		}
		}
	return recensioni;
	}
	public ArrayList<Recensione> cercaPerStelle(int s) {
		ArrayList<Recensione> r = getAll();
		ArrayList<Recensione> recensioni = new ArrayList<Recensione>();
		for(Recensione x : r) {
			if(x.getStelle() == s) {
				recensioni.add(x);
			}
		}
		return recensioni;
	}
	public String numeroRecensioniPerUtente(Utente u) {
		Connection connection = this.datasource.getConnection();
		ResultSet rs = null;
		int cont = 0;
		try {
			String query = "select * from recensione where utente = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,u.getUsername());
			rs = statement.executeQuery();
			while(rs.next()) {
				cont++;
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
				if(rs!= null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	String c = Integer.toString(cont);
	return c;
	}
	
	public ArrayList<Recensione> getAll() {
		ArrayList<Recensione> recensioni = new ArrayList<Recensione>();
		Connection connection = this.datasource.getConnection();
		ResultSet rs = null;
		try {
			String query = "select * from recensione";
			PreparedStatement statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while(rs.next()) {
				Recensione temp = new Recensione();
				Utente u = new Utente();
				u.setUsername(rs.getString("utente"));
				temp.setUsername(u.getUsername());
				temp.setTesto(rs.getString("testo"));
				temp.setStelle(rs.getInt("stelle"));
				recensioni.add(temp);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return recensioni;
	}
	
	public ArrayList<Recensione> recensioniPerUtente(String username) { 
		ArrayList<Recensione> recensioni = new ArrayList<Recensione>();
		Connection connection = this.datasource.getConnection();
		ResultSet rs = null;
		try {
			String query = "select * from recensione where utente = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,username);
			rs = statement.executeQuery();
			while(rs.next()) {
				Recensione temp = new Recensione();
				
				temp.setUsername(username);
				temp.setTesto(rs.getString("testo"));
				temp.setStelle(rs.getInt("stelle"));
				recensioni.add(temp);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return recensioni;
	}
}

