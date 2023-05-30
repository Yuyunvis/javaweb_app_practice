package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utente {
	
	public Utente(String nome, String cognome, Date data, String username, String password, String email) {
		super();
		this.nome = nome;
		this.dataNascita=data;
		this.cognome = cognome;
		setData(this.dataNascita);
		this.username = username;
		this.password = password;
		this.email = email;
	}

	private String nome ;
	private String cognome ;
	private Date dataNascita;	
	private String username;
	private String password ;
	private String email ;
	private String immagineProfilo;
	private String dataStr;
	
	public Utente() {
		// TODO Auto-generated constructor stub
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getImmagineProfilo() {
		return immagineProfilo;
	}

	public void setImmagineProfilo(String immagineProfilo) {
		this.immagineProfilo = immagineProfilo;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", data=" + dataStr + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", immagineProfilo=" + immagineProfilo + "]";
	}
	
	public String getDataStr() {
		return dataStr;
	}
	
	public void setDataNascita(Date date) {
		this.dataNascita = date;
		setData(this.dataNascita);
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}

	public void setData(Date data) {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
		this.dataStr=format.format(data);
	}
}
