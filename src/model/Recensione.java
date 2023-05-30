package model;

public class Recensione {
	String username;
	String testo;
	int stelle;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public int getStelle() {
		return stelle;
	}
	public void setStelle(int stelle) {
		this.stelle = stelle;
	}
	@Override
	public String toString() {
		return "Recensione [username=" + username + ", testo=" + testo + ", stelle=" + stelle + "]";
	}
	
	
	
}
