package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	
	final private String dbURI; 
	final private String userName; 
	final private String password; 
	
	public DataSource(String dbUri, String userName, String password) {
		this.dbURI = dbUri; 
		this.userName = userName;
		this.password = password;
	}

	 public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}		
		try {
			connection = DriverManager.getConnection(dbURI, userName, password);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}

