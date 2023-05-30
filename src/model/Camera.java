package model;

public class Camera {

	private int id;
	private String hotel;
	private String tipologia;
	private int prezzo;
	private String immagine;
	private String descrizione;
	
	public Camera() {
	}
	
	public Camera(int id, String hotel, String tipologia, int prezzo, String immagine, String descrizione) {
		super();
		this.id = id;
		this.hotel = hotel;
		this.tipologia = tipologia;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.descrizione = descrizione;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Override
	public String toString() {
		return "Camera [codice=" + id + ", hotel=" + hotel + ", tipologia=" + tipologia + ", prezzo=" + prezzo
				+ ", immagine=" + immagine + "]";
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + hotel.hashCode();
		result = prime * result + prezzo;
		result = prime * result + ((tipologia == null) ? 0 : tipologia.hashCode());
		return result;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
