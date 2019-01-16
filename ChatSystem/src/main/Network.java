package main;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Network {
	private Controller contr;
	private ArrayList<User> listUsers;
	private HashMap<String,User> hmap;
	private DatagramSocket UDPsocket;
	
	private UDPListener UDPListener;
    private ManagerServer server = null;
    
    private boolean connected = false; //true if the local user is connected
        
    protected static int portUDP = 1233;
    protected static int portTCP = 1234;
	
	public Network(Controller contr) {
		this.contr = contr;
		this.listUsers = new ArrayList<User>();
		this.hmap = new HashMap<String, User>();
		try {
			/* Create a socket to communicate in UDP */
			this.UDPsocket = new DatagramSocket(portUDP);
			/* Create a thread UDPListener */
			this.UDPListener = new UDPListener(this, this.UDPsocket);
			Thread threadListener = new Thread(this.UDPListener);
			threadListener.start();	
			/* Send a request of listUsers */
			this.sendUDPPacketBroadcast("RequestListUsers");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/* Send the UDP packet to the address indicated */
	public void sendUDPPacketUnicast(UDPPacket packet, InetAddress address) {
		UDPSender sender = new UDPSender(packet, address, Network.portUDP);
		Thread threadSender = new Thread(sender);
		threadSender.start();
	}
	
	/* Send the UDP packet in broadcast */
	public void sendUDPPacketBroadcast(String motive) {
		UDPPacket packet = new UDPPacket(this.contr.getUser(),null,motive);
		try {
			UDPSender sender = new UDPSender(packet, InetAddress.getByName("255.255.255.255"), Network.portUDP);
			Thread threadSender = new Thread(sender);
			threadSender.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
		
	/* Return true if the pseudo is not used by another user yet */
	public boolean checkUnicityPseudo(String pseudo) {
		boolean unicity = true;
		Iterator<User> usersIter = this.listUsers.iterator();
		while (unicity && usersIter.hasNext()) {
			User nextUser = usersIter.next();
			if (nextUser.getPseudo().equals(pseudo)) {
				unicity = false;
			}
		}
		return unicity;
	}
		
	/* Send the list of users */
	public void sendListUsersUDPPacket(User userDest, InetAddress address) {
		this.sendUDPPacketUnicast(new ListUsersUDPPacket(this.contr.getUser(),userDest,this.listUsers),address);
	}
	
	/* Return true if the local user is the last user connected */
	public boolean lastUserConnected() {
		boolean last = true;
		User localUser = this.contr.getUser();
		Iterator<User> usersIter = this.listUsers.iterator();
		/* If the local user is not connected */
		if (!this.connected) {
			last = false;
		}
		/* While we don't find a user connected after the local user */
		while (last && usersIter.hasNext()) {
			User nextUser = usersIter.next();
			/* If a remote user has a connection time more recent than the local user */
			if (nextUser.getTimeConnection()>localUser.getTimeConnection()) {
				last = false;
			}
		}
		return last;
	}
	
	/* Add a User in the listUsers */
	public void addUser(User user) {
		this.listUsers.add(user);
	}
	
	/* Remove a User from the listUsers */
	public void deleteUser(User user) {
		boolean delete = false;
		Iterator<User> usersIter = this.listUsers.iterator();
		while (!delete && usersIter.hasNext()) {
			User nextUser = usersIter.next();
			if (nextUser.getId().equals(user.getId())) {
				this.listUsers.remove(nextUser);
				delete=true;
			}
		}
		if (!delete) {
			System.out.println("Problem to delete the user : he/she can't be find in the list");
		}
	}
	
	/* Update a User, ie change his/her pseudo */
	public void updateUser(User user) {
		boolean update = false;
		Iterator<User> usersIter = this.listUsers.iterator();
		while (!update && usersIter.hasNext()) {
			User nextUser = usersIter.next();
			if (nextUser.getId().equals(user.getId())) {
				update = true;
				nextUser.setPseudo(user.getPseudo());
			}
		}
		if (!update) {
			System.out.println("Problem to update the user : he/she can't be find in the list");
		}
	}
	
	public ArrayList<User> getListUsers() {
		return this.listUsers;
	}
	
	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	/* Connect the local user */
	public void connectLocalUser() {
		System.out.println("You are connected");
		this.launchManagerServer();
        this.sendUDPPacketBroadcast("UserConnected");
	}
	
	/* Disconnect the local user */
	public void disconnectLocalUser() {
		System.out.println("You are disconnected");
		this.sendUDPPacketBroadcast("UserDisconnected");
		this.server.closeManagerServer();
	}
	
	/* Close the network */
	public void closeNetwork() {
		this.UDPListener.stop();
	}
		
	public Controller getController() {
		return this.contr;
	}
	
    public HashMap<String,User> getHmap(){
        return this.hmap;
    }
    
    public User findUserWithPseudo(String dest){
    	for (User tmp : listUsers) {
    		if (tmp.getPseudo().equals(dest)) {
    			return tmp;
    		}
    		
    	}
    	return null;
    }
    
    public void launchManagerServer() {
        try {
            this.server=new ManagerServer(Network.portTCP,this) ;
            Thread manager = new Thread(server);
            manager.start();
            this.connected = true; //the local user is now connected
        } catch (IOException e) {
        	e.printStackTrace();
		}
    }

    public ManagerServer getServer() {
        return server;
    }

    public void setServer(ManagerServer server) {
        this.server = server;
    }
    
    /* Return true if the local user is connected */
    public boolean getConnectionStatus() {
    	return this.connected;
    }
    
    /* Set the connection status of the local user */
    public void setConnectionStatus(boolean connected) {
    	this.connected = connected;
    }

}
