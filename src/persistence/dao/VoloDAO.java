package persistence.dao;

import java.util.Date;
import java.util.List;

import model.Volo;

public interface VoloDAO {
	public List<Volo> findFlight(String partenza, String destinazione, Date data, int posti);
	public Volo cercaVoloDaId(int id);
}
