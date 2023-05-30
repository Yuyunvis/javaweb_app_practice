package persistence.dao;
import java.util.ArrayList;
import java.util.HashMap;

import model.Hotel;
import model.Camera;

public interface HotelDAO {
	public HashMap<Hotel, ArrayList<Camera>> cercaHotel(String citta, String a, String b, String tipologia, String useername);
}
