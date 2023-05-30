package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PrenotazioneCamera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrenotazioneCamera(String nome, String cognome, Date checkInDate, Date checkOutDate, String utente, String tipologia, String nomeHotel, int codiceCamera, int codicePrenotazione, int prezzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		setCheckIn(this.checkInDate);
		setCheckOut(this.checkOutDate);
		this.utente = utente;
		this.tipologia = tipologia;
		this.nomeHotel = nomeHotel;
		this.codiceCamera = codiceCamera;
		this.codicePrenotazione = codicePrenotazione;
		this.prezzo = prezzo;
	}

	private String nome;
	private String cognome;
	private Date checkInDate;
	private Date checkOutDate;
	private Date nascita;
	private String nascitaString;
	private String checkInString;
	private String checkOutString;
	private String utente;
	private String tipologia;
	private String nomeHotel;
	private int codicePrenotazione;
	private int codiceCamera;
	private int prezzo;
	private boolean localCode = false;
	private String citta;
	
	public void setCheckIn(Date data) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
		this.checkInString=format.format(data);
	}
	
	public void setCheckOut(Date data) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
		this.checkOutString=format.format(data);
	}
	
	public PrenotazioneCamera() {}
	
	
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "CarrelloHotel [nome=" + nome + ", cognome=" + cognome + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", checkInString=" + checkInString + ", checkOutString="
				+ checkOutString + ", utente=" + utente + ", tipologia=" + tipologia + ", nomeHotel=" + nomeHotel
				+ ", codicePrenotazione=" + codicePrenotazione + ", prezzo=" + prezzo + "]";
	}

	public int getCodicePrenotazione() {
		return codicePrenotazione;
	}

	public void setCodicePrenotazione(int codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
		setCheckIn(this.checkInDate);
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
		setCheckOut(this.checkOutDate);
	}

	public String getCheckInString() {
		return checkInString;
	}

	public void setCheckInString(String checkInString) {
		this.checkInString = checkInString;
	}

	public String getCheckOutString() {
		return checkOutString;
	}

	public void setCheckOutString(String checkOutString) {
		this.checkOutString = checkOutString;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getNomeHotel() {
		return nomeHotel;
	}

	public void setNomeHotel(String nomeHotel) {
		this.nomeHotel = nomeHotel;
	}

	public int getCodiceCamera() {
		return codiceCamera;
	}

	public void setCodiceCamera(int codiceCamera) {
		this.codiceCamera = codiceCamera;
	}

	public Date getNascita() {
		return nascita;
	}

	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}

	public String getNascitaString() {
		return new SimpleDateFormat("dd/MM/yyyy").format(nascita);
	}

	public boolean isLocalCode() {
		return localCode;
	}

	public void setLocalCode(boolean localCode) {
		this.localCode = localCode;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
}
