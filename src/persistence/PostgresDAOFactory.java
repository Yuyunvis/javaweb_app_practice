package persistence;

import persistence.dao.CameraDAO;
import persistence.dao.PrenotazioneHotelDAO;
import persistence.dao.HotelDAO;
import persistence.dao.PrenotazioneVoloDAO;
import persistence.dao.RecensioneDAO;
import persistence.dao.UtenteDAO;
import persistence.dao.VoloDAO;

class PostgresDAOFactory extends DAOFactory {
	
	private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
//			dataSource=new DataSource("jdbc:postgresql://localhost:5434/AgenziaViaggi","postgres","12345");
			
			//dataSource=new DataSource("jdbc:postgresql://rogue.db.elephantsql.com:5432/tayollgx","tayollgx","UR_rsDKr1-2sRfHM1G1GE3qJl2c_Afao");
			dataSource=new DataSource("jdbc:postgresql://agenziaviaggi.cgodjpaume9h.eu-central-1.rds.amazonaws.com/postgres","postgres","00000000");
			
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	
	@Override
	public UtenteDAO getUtenteDAO() {
		return new UtenteDAOJDBC(dataSource);
	}
	
	public RecensioneDAO getRecensioneDAO () {
		return new RecensioneDAOJDBC(dataSource);
	}
	
	@Override
	public VoloDAO getVoloDAO() {
		return new VoloDAOJDBC(dataSource);
	}
	
	@Override
	public CameraDAO getCameraDAO() {
		return new CameraDAOJDBC(dataSource);
	}
	
	@Override
	public HotelDAO getHotelDAO() {
		return new HotelDAOJDBC(dataSource);
	}
	
	@Override
	public PrenotazioneVoloDAO getPrenotazioneVoloDAO() {
		return new PrenotazioneVoloDAOJDBC(dataSource);
	}

	@Override
	public PrenotazioneHotelDAO getPrenotazioneHotelDAO() {
		return new PrenotazioneHotelDAOJDBC(dataSource);
	}

}
