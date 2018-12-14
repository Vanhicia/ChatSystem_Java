import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class Controller {
	private User user;
	private Window view;
	private Network cc;
	
	public Controller() {
		this.user = null;
		this.view = new Window();
	}
	
	public User getUser() {
		return user;
	}
	
	public void connect(String pseudo, int portUDP, int portTCP) {
		try {
			this.user = new User(UUID.randomUUID(), pseudo, InetAddress.getLocalHost(), System.currentTimeMillis());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.cc = new Network(portUDP, portTCP);
		/* Send the pseudo in broadcast */
	}
	
	public void changePseudo(String pseudo) {
		if (cc.checkUnicityPseudo(pseudo)==true) {
			user.setPseudo(pseudo);
		} 
		else {
			// affichage d'un message d'erreur
		}		
	}
		
	/*
	public String receiveData () {
		String input = null;
		try {
			input = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	


	public Message contructMessage() {
		new Message("Connexion OK with Server",)
	}
	*/
	
	public void refreshWindows() {
		
	}

}
