package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Volo {
	
	private int codice;
	private String partenza;
	private String destinazione;
	private Date data;
	private int prezzo;
	private int numeroPosti;
	private int durata;
	private String compagniaArea;
	private Date dataArrivo;
	private String dataPartenzaStr;
	private String dataArrivoStr;

	public Volo (int codice, String partenza, String destinazione, Date data, int prezzo) {
		this.codice = codice;
		this.partenza = partenza;
		this.destinazione = destinazione;
		this.data=data;
		this.prezzo=prezzo;
	}
	
	public Volo() {
		// TODO Auto-generated constructor stub
	}
	
	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}
	public String getPartenza() {
		return partenza;
	}
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	public String getDestinazione() {
		return destinazione;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data=data;
		setDataPartenzaStr(new SimpleDateFormat("dd/MM/YYYY HH:mm").format(data));
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		return "Volo [codice=" + codice + ", città partenza=" + partenza + ", città destinazione=" + destinazione + ", prezzo=" + prezzo
				+ ", data=" + data + "]";
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getCompagniaArea() {
		return compagniaArea;
	}

	public void setCompagniaArea(String compagniaArea) {
		this.compagniaArea = compagniaArea;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public void setDataArrivo(Date datea) {
		this.dataArrivo = datea;
		setDataArrivoStr(new SimpleDateFormat("dd/MM/YYYY HH:mm").format(datea));
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public String getDataPartenzaStr() {
		return dataPartenzaStr;
	}

	public void setDataPartenzaStr(String dataPartenzaStr) {
		this.dataPartenzaStr = dataPartenzaStr;
	}

	public String getDataArrivoStr() {
		return dataArrivoStr;
	}

	public void setDataArrivoStr(String dataArrivoStr) {
		this.dataArrivoStr = dataArrivoStr;
	}
}
