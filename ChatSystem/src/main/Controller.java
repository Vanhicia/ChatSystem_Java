package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;


public class Controller {
	private User user;
	//private Window view;
	private Network nwk = null;
	
	public Controller() {
		this.user = null;
		//this.view = new Window();
	}
	
	public User getUser() {
		return user;
	}
	
	public Network getNetwork() {
		return this.nwk;
	}
	
	public void connect(String pseudo) {
		try {
			this.user = new User(UUID.randomUUID(), "", InetAddress.getLocalHost(), System.currentTimeMillis());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.nwk = new Network(this);
		changePseudo(0, pseudo);
	}
	
	/* Option = 0 : pseudo of a new user */
	/* Option =/= 0 : pseudo updated */
	public void changePseudo(int option, String pseudo) {
		if (nwk.checkUnicityPseudo(pseudo)==true) {
			System.out.println("Your pseudo is unique");
			user.setPseudo(pseudo);
			if (option == 0) {
				nwk.sendPacketUserConnected(this.user);
			} 
			else {
				nwk.sendPacketUserUpdated(this.user);
			}
		} 
		else {
			// affichage d'un message d'erreur
			System.out.println("Your pseudo is refused, it is not unique");
			//TO DO : ask a new pseudo to the user
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
