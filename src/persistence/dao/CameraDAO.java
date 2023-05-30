package persistence.dao;

import model.Camera;
import model.Hotel;

public interface CameraDAO {
	public Camera trovaCamera(Hotel hotel);
}
