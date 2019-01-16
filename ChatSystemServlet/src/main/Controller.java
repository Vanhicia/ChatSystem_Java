package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.database.Database;
import main.gui.Contact;
import main.gui.LoginWindow;


public class Controller {
	private Database db;
	private User user;
	private Contact contacts;
	private Network nwk = null;
    private LoginWindow login = null;

	public Controller() {
		this.db= new Database();
		System.out.println("Database opened");
		try {
			/* Get in the database the local user's id */
			UUID id = this.db.getLocalUserId();
			this.user = new User(id, "", InetAddress.getLocalHost(), 0);
			System.out.println("Local user exists and its id = " + id);
		} catch (SQLException e) {
			/* The application is launched for the first time */
			System.out.println("The application is launched for the first time");
			this.launchFirstTime();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.nwk = new Network(this);
	}
	
	public User getUser() {
		return user;
	}
	
	public Network getNetwork() {
		return this.nwk;
	}
	
	public Database getDatabase() {
		return this.db;
	}
	
	/* Return 1 if the connection has succeeded */
	/* Else return 0 */
	public int connect(String pseudo) {
		return changePseudo(0, pseudo);
	}
	
	public void disconnect() {
		this.nwk.disconnectLocalUser();
		this.getLogin().displayWindow();
	}
	
	public void closeApplication() {
		this.nwk.closeNetwork();
		this.db.closeDatabase();
	}
	
	/* Change the pseudo */
	/* Option = 0 : pseudo of a new user */
	/* Option =/= 0 : pseudo updated */
	/* Return 1 if the pseudo has been changed */
	/* Else return 0 */
	public int changePseudo(int option, String pseudo) {
		/* If the wanted pseudo is unique */
		if (this.nwk.checkUnicityPseudo(pseudo)==true) {
			System.out.println("Your pseudo is unique");
			user.setPseudo(pseudo);
			/* If a new user choose his/her pseudo */
			if (option == 0) {
				this.user.setTimeConnection();
				this.nwk.connectLocalUser();
                this.contacts = new Contact(this.nwk.getServer(),Network.portTCP, this.user,this);
                try {
                    this.contacts.displayWindow();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
			} 
			/* If the user updates his/her pseudo */
			else {
				System.out.println("Your pseudo is updated");
				this.nwk.sendUDPPacketBroadcast("UserUpdated");
			}
			return 1;
		} 
		else {
			/* Print an error message */
			System.out.println("Your pseudo is refused, it is not unique");
			return 0;
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
		if (this.contacts != null) {
			this.contacts.refreshContacts();
		}
	}

    public void setLogin(LoginWindow login) {
        this.login = login;
    }

    public LoginWindow getLogin() {
        return login;
    }
    
    public void launchFirstTime() {
    	UUID id = UUID.randomUUID();
		try {
			this.user = new User(id, "", InetAddress.getLocalHost(), 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.db.createTables();
		this.db.insertLocalUser(id);
		System.out.println("A local user is created in the database");
    }
    
}
