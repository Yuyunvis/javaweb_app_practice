package persistence;

import model.Camera;
import model.Hotel;
import persistence.dao.CameraDAO;

public class CameraDAOJDBC implements CameraDAO{
	
	private DataSource datasource;
	
	public CameraDAOJDBC(DataSource dataSource) {
			datasource= dataSource;
	}

	@Override
	public Camera trovaCamera(Hotel hotel) {
		// TODO Auto-generated method stub
		return null;
	}
}
