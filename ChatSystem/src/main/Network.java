package main;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class Network {
	private Controller contr;
	private ArrayList<User> listUsers;
	private UDPListener UDPListener;
	private DatagramSocket UDPsocket;
	
	private boolean unicityPseudo;

    protected static int portUDP = 1233;
    protected static int portTCP = 1234;
		
	public Network(Controller contr) {
		this.contr = contr;
		this.listUsers = new ArrayList<User>();
		
		// create a thread UDPListener
		try {
			this.UDPsocket = new DatagramSocket(portUDP);
			this.UDPListener = new UDPListener(this, this.UDPsocket);
			Thread threadListener = new Thread(this.UDPListener);
			threadListener.start();
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
		this.unicityPseudo = true;
		// send a message with the pseudo, in broadcast
		User localUser = this.contr.getUser();
		User userTemp = new User(localUser.getId(),pseudo, localUser.getAddress(), localUser.getTimeConnection());
		this.sendUDPPacketBroadcast(new UDPPacket(userTemp,null,"NewPseudo"));
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.unicityPseudo;
	}
	
	/* Send the identity of the new user in broadcast */
	public void sendUDPPacketUserConnected(User user) {
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"UserConnected"));
	}
	/* Send the identity of the updated user in broadcast */
	public void sendUDPPacketUserUpdated(User user) {
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"UserUpdated"));
	}
	
	/* Send the list of users */
	public void sendListUsersUDPPacket(User userDest) {
		ArrayList<User> listUsersTemp = this.listUsers;
		listUsersTemp.add(this.contr.getUser()); // the local user is added to the list of users
		this.sendUDPPacketUnicast(new ListUsersUDPPacket(this.contr.getUser(),userDest, listUsers),userDest.getAddress());
	}
	
	/* Return true is the local user is the last user connected */
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
	
	public void addUser(User user) {
		this.listUsers.add(user);
	}
	
	public void deleteUser(User user) {
		this.listUsers.remove(user);
	}
	
	public void updateUser(User user) {
		//TODO
	}
	
	public ArrayList<User> getListUsers() {
		return this.listUsers;
	}
	
	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void closeUDPSocket() {
		this.UDPsocket.close();
	}
	
	public void setUnicityPseudo(boolean b) {
		this.unicityPseudo = b;
	}
	
	public Controller getController() {
		return this.contr;
	}
	
	/*public int getPortUDP() {
		return portUDP;
	}
	
	public int getPortTCP() {
		return portTCP;
	}*/
}
