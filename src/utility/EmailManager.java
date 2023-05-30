package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.PrenotazioneCamera;
import model.PrenotazioneVolo;
import model.Utente;


public class EmailManager {
	private static Properties props = new Properties();
	private static Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("TravelAgencyIngsw@gmail.com","agenzia23");
		}
	});
	
	static {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
	}
	
    public EmailManager() {
		super();
		
	}
    
    public static void emailPagamentoEffettuato(String to,List<PrenotazioneVolo>prenotazioniVolo,List<PrenotazioneCamera> prenotazioniCamera, String nome) {
    	
    	try {
			Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
    		message.setSubject("Pagamento effettuato con successo");
    		
    		StringBuilder stringToCompose = new StringBuilder();
    		String messageToCompose;
    		
    		stringToCompose.append("Ciao"+nome+",\n ecco il resoconto prenotazioni effettuate.\n\n");
    		for(PrenotazioneVolo volo: prenotazioniVolo) {
    			messageToCompose = "Volo: "+volo.getPartenza()+"-"+volo.getDestinazione()+"\n"+
    					"data partenza: "+volo.getDataPartenza() + "\n" +
    					"nome passeggero: "+volo.getNomePasseggero() + "\n" +
						"cognome passeggero: "+volo.getCognomePasseggero() +"\n" +
						"data nascita passeggero: "+volo.getDataNascita() +"\n" +
						"prezzo: "+volo.getPrezzo() + " \u20ac \n" +
						"posto assegnato: "+volo.getPosto() +"\n" +"\n";
    			stringToCompose.append(messageToCompose);
    		}
    		stringToCompose.append("\n");
    		for(PrenotazioneCamera camera: prenotazioniCamera) {
    			messageToCompose = "Hotel: "+camera.getNomeHotel()+"\n"+
						"città: "+camera.getCitta()+"\n"+
    					"tipologia camera: "+camera.getTipologia()+"\n"+
    					"cliente: "+camera.getNome()+" "+camera.getCognome()+"\n"+
    					"check-in: "+camera.getCheckInString()+"\n"+
    					"check-out: "+camera.getCheckOutString()+"\n"+
    					"prezzo: "+camera.getPrezzo()+" \u20ac \n"+"\n";
    			stringToCompose.append(messageToCompose);
    		}
    		stringToCompose.append("\n");
    		stringToCompose.append("Grazie per averci scelto! Il tuo storico nella sezione profilo è stato appena aggiornato.\n");
    		stringToCompose.append("Goditi il tuo viaggio!.\n\nTravelLife agency");
    		message.setText(stringToCompose.toString());
    		Transport.send(message);
    	} 
    	catch (MessagingException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    public void emailChanged(String to, String nome) {
    	try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
    		message.setSubject("Cambio Email");
    		StringBuilder stringToCompose = new StringBuilder();
    		
    		stringToCompose.append("Ciao "+nome+", \nti informiamo che il cambiamento dell'e-mail è avvenuto con successo. Da ora in poi verrai contattato su questa email.\n\n");
    		stringToCompose.append("TravelLife agency.");
    		message.setText(stringToCompose.toString());
    		Transport.send(message);
    	} catch (MessagingException e) {
    		        throw new RuntimeException(e);
    	}
    }
    
    public void recoverPassword(String to,String password, String username) {	
    	try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
    		message.setSubject("Recupero Password");
    		StringBuilder stringToCompose = new StringBuilder();
    		stringToCompose.append("Ciao "+username+",\nti informiamo che la password a te assegnata è la seguente: "+password+".\n");
    		stringToCompose.append("Utilizzala per accedere al nostro in sito. Sei libero di cambiarla quando vuoi nella sezione del profilo.\n");
    		stringToCompose.append("Buona navigazione!\n\nTravelLife agency");
    		message.setText(stringToCompose.toString());
    		Transport.send(message);
    		
    	} catch (MessagingException e) {
    		        throw new RuntimeException(e);
    	}
    }
    	 
    public void registerValidationEmail(Utente utente,String to) {
    	try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
    		message.setSubject("Conferma Registrazione");
    		StringBuilder stringToCompose = new StringBuilder();
    		stringToCompose.append("Ciao, "+utente.getCognome()+" "+utente.getNome()+", \nbenvenuto su TravelLife. ");
    		stringToCompose.append("Grazie per esserti iscritto sul nostro sito; ora puoi godere di tutti i benefici che ti offriamo.");
    		stringToCompose.append("Organizzare un viaggio non è mai stato così semplice! Bastano pochi click per trasformare i tuoi sogni in realtà.");
    		stringToCompose.append("Accedi nella sezione profilo per visualizzare tutti i tuoi dati, inserire un'immagine del profilo, rilasciare una recensione e visionare lo storico delle tue future prenotazioni.\n");
    		stringToCompose.append("Buona navigazione e non esitare a contattarci per ulteriori informazioni.\n\nTravelLife agency");
    		message.setText(stringToCompose.toString());
    		Transport.send(message);
    		
    	} catch (MessagingException e) {
    		        throw new RuntimeException(e);
    	}
    	
    }
    
    public void emailConfermaPagamento(Utente utente, String to) {
    	try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
    		message.setSubject("Conferma Pagamento");
    		message.setText("Benvenuto, "+utente.getCognome()+" "+utente.getNome()+" su TravelLife.\n");
    		Transport.send(message);
    	} catch (MessagingException e) {
    		        throw new RuntimeException(e);
    	}
    }
    
    public void passwordChangedEmail(String nomeUtente,String to,String passwordNuova) {
    	try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
    		message.setSubject("Modifica password");
    
    		StringBuilder stringToCompose = new StringBuilder();
  
    		stringToCompose.append("Ciao "+nomeUtente+", \nti informiamo che il cambiamento della password è avvenuto con successo.\n");
    		stringToCompose.append("La password attuale è la seguente: "+passwordNuova+".\n\n");
    		stringToCompose.append("TravelLife agency");
    		message.setText(stringToCompose.toString());
    		Transport.send(message);
    	} catch (MessagingException e) {
    		        throw new RuntimeException(e);
    	}
    	
    }
	
    public String getPassword() {
    	String stringForPassword = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(10); 
        for (int i = 0; i < 10; i++) { 
        	  int index = (int)(stringForPassword.length() * Math.random()); 
        	  sb.append(stringForPassword.charAt(index)); 
        }
        return sb.toString();
    }

	public void emailInfo(String email, String nome, String emailDaCambiare) {
		// TODO Auto-generated method stub
		try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("TravelAgencyIngsw@gmail.com"));
    		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
    		message.setSubject("Cambio Email");
    		StringBuilder stringToCompose = new StringBuilder();
    		
    		stringToCompose.append("Ciao "+nome+", \nabbiamo accolto la tua richiesta per il cambio dell'email. Ti informiamo che la nuova email da te scelta è: "+emailDaCambiare+".\nPresto verrai contattato sul nuovo indirizzo.\n\n");
    		stringToCompose.append("TravelLife agency.");
    		message.setText(stringToCompose.toString());
    		Transport.send(message);
    	} catch (MessagingException e) {
    		        throw new RuntimeException(e);
    	}
	}
}