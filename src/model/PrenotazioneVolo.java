package model;

import java.io.Serializable;

public class PrenotazioneVolo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idVolo;
	private String nomePasseggero;
	private String cognomePasseggero;
	private String partenza;
	private String destinazione;
	private int posto;
	private int codicePrenotazione;
	private String dataNascita;
	private String dataPartenza;
	private int prezzo;
	private boolean localCode = false;
	private boolean disponibilitaPosto = true;
	
	public PrenotazioneVolo(int idVolo, String nomePasseggero, String cognomePasseggero, String partenza, String destinazione, int posto, String dataNascita, String dataPartenza, int prezzo) {
		this.idVolo = idVolo;
		this.nomePasseggero = nomePasseggero;
		this.cognomePasseggero = cognomePasseggero;
		this.partenza = partenza;
		this.destinazione = destinazione;
		this.posto = posto;
		this.dataNascita = dataNascita;
		this.dataPartenza = dataPartenza;
		this.prezzo = prezzo;
	}
	
	public PrenotazioneVolo() {}
	
	public String getNomePasseggero() {
		return nomePasseggero;
	}
	public void setNomePasseggero(String nomePasseggero) {
		this.nomePasseggero = nomePasseggero;
	}
	public String getCognomePasseggero() {
		return cognomePasseggero;
	}
	public void setCognomePasseggero(String cognomePasseggero) {
		this.cognomePasseggero = cognomePasseggero;
	}
	
	public int getPosto() {
		return posto;
	}
	public void setPosto(int posto) {
		this.posto = posto;
	}
	
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		
		this.dataNascita = dataNascita;
	}

	public String getPartenza() {
		return partenza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public String getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(String dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	
	@Override
	public String toString() {
		return "CarrelloVolo [nomePasseggero=" + nomePasseggero + ", cognomePasseggero=" + cognomePasseggero
				+ ", partenza=" + partenza + ", destinazione=" + destinazione + ", posto=" + posto
				+ ", codicePrenotazione=" + codicePrenotazione + ", dataNascita=" + dataNascita + ", dataPartenza="
				+ dataPartenza + ", prezzo=" + prezzo + "]";
	}

	public int getCodicePrenotazione() {
		return codicePrenotazione;
	}

	public void setCodicePrenotazione(int codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public boolean isLocalCode() {
		return localCode;
	}

	public void setLocalCode(boolean localCode) {
		this.localCode = localCode;
	}

	public boolean isDisponibilitaPosto() {
		return disponibilitaPosto;
	}

	public void setDisponibilitaPosto(boolean disponibilitaPosto) {
		this.disponibilitaPosto = disponibilitaPosto;
	}
}
