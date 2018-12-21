package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;


public class Controller {
	private User user;
	//private Window view;
	private Network nwk = null;
	
	public Controller() {
		try {
			this.user = new User(UUID.randomUUID(), "", InetAddress.getLocalHost(), 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.nwk = new Network(this);
		//this.view = new Window();
	}
	
	public User getUser() {
		return user;
	}
	
	public Network getNetwork() {
		return this.nwk;
	}
	
	public void connect(String pseudo) {
		changePseudo(0, pseudo);
	}
	
	public void disconnect() {
		this.nwk.closeNetwork();
	}
	
	/* Option = 0 : pseudo of a new user */
	/* Option =/= 0 : pseudo updated */
	public void changePseudo(int option, String pseudo) {
		if (this.nwk.checkUnicityPseudo(pseudo)==true) {
			System.out.println("Your pseudo is unique");
			user.setPseudo(pseudo);
			if (option == 0) {
				this.user.setTimeConnection();
				this.nwk.sendUDPPacketUserConnected(this.user);
			} 
			else {
				this.nwk.sendUDPPacketUserUpdated(this.user);
			}
		} 
		else {
			// affichage d'un message d'erreur
			System.out.println("Your pseudo is refused, it is not unique");
			//TO DO : ask a new pseudo to the user
		}		
	}
	
	public void displayAllUsers() {
		System.out.println("Users List :");
		ArrayList<User> listUsers = this.nwk.getListUsers();
		for (User user : listUsers) {
			System.out.println(user.getPseudo() + ": @IP = " + user.getAddress());
		}
		System.out.println("");
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
