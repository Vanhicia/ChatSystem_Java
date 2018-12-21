package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import main.gui.ChatWindow;


public class Controller implements Observer{
	private User user;
	private ChatWindow chat;
	private Network nwk = null;
    private static ManagerServer server=null;
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
				try {
					this.server = new ManagerServer(this.nwk.portTCP,this.nwk);
					this.chat = new ChatWindow(this.server,this.nwk.portTCP, this.user,this.nwk);
					chat.displayWindow();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
	


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void refreshWindows() {
		chat.refreshContacts();
		
	}

}
