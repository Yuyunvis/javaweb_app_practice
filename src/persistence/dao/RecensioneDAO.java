package persistence.dao;
import model.Recensione;

import java.util.ArrayList;

import model.Recensione;
import model.Utente;

public interface RecensioneDAO {
	
	public void aggiungiRecensione(Recensione r);
	public int[] recensioniPerStelle();
	public ArrayList<Recensione> cercaPerStelle(int s);
	public String numeroRecensioniPerUtente(Utente u);
	public ArrayList<Recensione> getAll();
	public ArrayList<Recensione> recensioniPerUtente(String u);
	
}
