package persistence.dao;

import java.util.List;

import model.PrenotazioneVolo;
import model.Utente;
import persistence.UtenteCredenziali;


public interface UtenteDAO {
	public void registraUtente(Utente utente);
	public Utente loginUtente(Utente utente);
	public void aggiornaImmagine(String path, String u);
	public UtenteCredenziali findByPrimaryKeyCredential(String username);
	public Utente findByPrimaryKey(String username);
	public boolean isPresent(String username);
	public void creaCarrello(String username);
	public void svuotaCarrello(String username);
	public void svuotaCarrelloHotel(String username);
	public void aggiornaPassword(String username, String password);
	public void aggiornaEmail(String username, String email);
	public void inserisciPrenotazioniEffettuate(String username);
	public String getEmail(String username);
}
