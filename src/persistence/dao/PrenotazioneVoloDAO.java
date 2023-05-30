package persistence.dao;

import java.util.List;

import model.PrenotazioneVolo; 

public interface PrenotazioneVoloDAO {
	
	public void inserisciPrenotazioneNelCarrello (int id, String username);
	public List<PrenotazioneVolo> getPrenotazioniVoliCarrello(String username);
	public void eliminaPrenotazione(int id);
	public void creaPrenotazioni(List<PrenotazioneVolo> carrelloVolo, String username);
	public List<PrenotazioneVolo> getPrenotazioniEffettuate(String username);
	
}
