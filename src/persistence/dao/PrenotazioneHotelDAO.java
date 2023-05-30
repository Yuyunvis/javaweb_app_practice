package persistence.dao;

import java.util.Date;
import java.util.List;

import model.PrenotazioneCamera;

public interface PrenotazioneHotelDAO {

	void inserisciPrenotazioneNelCarrello(PrenotazioneCamera ch);

	List<PrenotazioneCamera> getHotelCarrello(String username);

	void eliminaPrenotazione(int id);

	List<PrenotazioneCamera> getPrenotazioniEffettuate(String username);

	boolean verificaDisponibilitaCamera(int Id, Date checkin, Date checkout);

}
