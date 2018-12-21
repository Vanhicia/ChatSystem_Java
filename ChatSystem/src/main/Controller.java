package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;

import main.gui.Contact;


public class Controller {
	private User user;
	private Contact contacts;
	private Network nwk = null;
    private static ManagerServer server=null;
    private Thread manager;
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
	
	/* Change the pseudo */
	/* Option = 0 : pseudo of a new user */
	/* Option =/= 0 : pseudo updated */
	public void changePseudo(int option, String pseudo) {
		/* If the wanted pseudo is unique */
		if (this.nwk.checkUnicityPseudo(pseudo)==true) {
			System.out.println("Your pseudo is unique");
			user.setPseudo(pseudo);
			/* If a new user choose his/her pseudo */
			if (option == 0) {
				System.out.println("You are connected");
				this.user.setTimeConnection();
				this.nwk.sendUDPPacketUserConnected();
				try {
					this.server = new ManagerServer(this.nwk.portTCP,this.nwk);
					this.contacts = new Contact(this.server,this.nwk.portTCP, this.user,this.nwk);
					contacts.displayWindow();
					manager = new Thread(server);
					manager.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			/* If the user updates his/her pseudo */
			else {
				System.out.println("Your pseudo is updated");
				this.nwk.sendUDPPacketUserUpdated();
			}
		} 
		else {
			/* Print an error message */
			System.out.println("Your pseudo is refused, it is not unique");
			//TODO : integrate in the graphical interface
		}		
	}
	
	public void displayAllUsers() {
		System.out.println("List of users :");
		ArrayList<User> listUsers = this.nwk.getListUsers();
		for (User user : listUsers) {
			System.out.println(user.getPseudo() + ": @IP = " + user.getAddress());
		}
		System.out.println("");
	}

	public void refreshWindows() {
		contacts.refreshContacts();
		
	}

}
