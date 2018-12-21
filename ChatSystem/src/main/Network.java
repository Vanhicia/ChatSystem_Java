package main;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class Network {
	private Controller contr;
	private ArrayList<User> listUsers;
	private UDPListener UDPListener;
	private DatagramSocket UDPsocket;
	private HashMap<String,User> hmap;

    protected static int portUDP = 1233;
    protected static int portTCP = 1234;
		
	public Network(Controller contr) {
		this.contr = contr;
		this.listUsers = new ArrayList<User>();
		this.hmap = new HashMap<String, User>();
		try {
			/* Create a socket to communicate with UDP */
			this.UDPsocket = new DatagramSocket(portUDP);
			/* Create a thread UDPListener */
			this.UDPListener = new UDPListener(this, this.UDPsocket);
			Thread threadListener = new Thread(this.UDPListener);
			threadListener.start();	
			this.sendUDPPacketRequestListUsers();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
		
	public void startSession(User userDist) {
		//create a thread ClientTCP
		//ClientTCP client = new ClientTCP(userDist.getAddress(), portTCP);
		//new Thread(client).start();
	}
	
	public void closeSession(User userDist) {
		
	}
	
	/* Send the UDP packet to the address indicated */
	public void sendUDPPacketUnicast(UDPPacket packet, InetAddress address) {
		UDPSender sender = new UDPSender(this.UDPsocket, packet, address, Network.portUDP);
		Thread threadSender = new Thread(sender);
		threadSender.start();
	}
	
	/* Send the UDP packet in broadcast */
	public void sendUDPPacketBroadcast(UDPPacket packet) {
		try {
			UDPSender sender = new UDPSender(this.UDPsocket, packet, InetAddress.getByName("255.255.255.255"), Network.portUDP);
			Thread threadSender = new Thread(sender);
			threadSender.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
		
	/* Return true if the pseudo is not used by another user yet */
	public boolean checkUnicityPseudo(String pseudo) {
		//this.unicityPseudo = true;
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
	
	/* Send a request to have the list of users, in broadcast */
	public void sendUDPPacketRequestListUsers() {
		System.out.println("Send a resquest of user list");
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"RequestListUsers"));
	}
	/* Send the identity of the new user in broadcast */
	public void sendUDPPacketUserConnected() {
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"UserConnected"));
	}
	/* Send the identity of the updated user in broadcast */
	public void sendUDPPacketUserUpdated() {
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"UserUpdated"));
	}
	
	/* Send the list of users */
	public void sendListUsersUDPPacket(User userDest, InetAddress address) {
		this.sendUDPPacketUnicast(new ListUsersUDPPacket(this.contr.getUser(),userDest,this.listUsers),userDest.getAddress());
	}
	
	/* Return true if the local user is the last user connected */
	public boolean lastUserConnected() {
		boolean last = true;
		User localUser = this.contr.getUser();
		Iterator<User> usersIter = this.listUsers.iterator();
		while (last && usersIter.hasNext()) {
			User nextUser = usersIter.next();
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
	
	public void closeNetwork() {
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"UserDisconnected"));
		this.UDPsocket.close();
	}
		
	public Controller getController() {
		return this.contr;
	}
	
    public HashMap<String,User> getHmap(){
        return this.hmap;
    }
    
    public User findUserWithPseudo(String dest){
        return this.hmap.get(dest);
    }
	/*public int getPortUDP() {
		return portUDP;
	}
	
	public int getPortTCP() {
		return portTCP;
	}*/
}
