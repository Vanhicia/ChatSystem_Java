package main;
import java.io.IOException;
import java.net.*;
import java.util.List;

public class Network {
	private Controller contr;
	private List<User> listUsers = null;
	private UDPListener UDPListener;
	private DatagramSocket UDPsocket;

    protected static int portUDP = 1233;
    protected static int portTCP = 1234;
		
	public Network(Controller contr) {
		this.contr = contr;
		
		// create a thread UDPListener
		try {
			this.UDPsocket = new DatagramSocket(portUDP);
			this.UDPListener = new UDPListener(this, this.UDPsocket);
			Thread threadListener = new Thread(this.UDPListener);
			threadListener.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}

public class Login
		/* Send the pseudo chosen in broadcast */
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"NewPseudo"));
		/* Wait a answer */
		// TODO
		/* Send the identity of the new user in broadcast */
		this.sendUDPPacketBroadcast(new UDPPacket(this.contr.getUser(),null,"UserConnected"));
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
	
	public void startSession(User userDist) {
		//create a thread ClientTCP
		ClientTCP client = new ClientTCP(userDist.getAddress(), portTCP);
		new Thread(client).start();
	}
	
	public void closeSession(User userDist) {
		
	}
	
	/* Return true if the pseudo is not used by another user yet */
	public boolean checkUnicityPseudo(String pseudo) {
		// send a message with the pseudo in broadcast
		// if no response, the pseudo is unique
		//TODO
		return true;
	}
	
	/* Send the UDP packet to the address indicated */
	public void sendUDPPacketUnicast(UDPPacket packet, InetAddress address) {
		UDPSender sender = new UDPSender(this.UDPsocket, packet, address);
		Thread threadSender = new Thread(sender);
		threadSender.start();
	}
	
	/* Send the UDP packet in broadcast */
	public void sendUDPPacketBroadcast(UDPPacket packet) {
		try {
			UDPSender sender = new UDPSender(this.UDPsocket, packet, InetAddress.getByName("255.255.255.255"));
			Thread threadSender = new Thread(sender);
			threadSender.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addUser(User user) {
		this.listUsers.add(user);
	}
	
	public void deleteUser(User user) {
		this.listUsers.remove(user);
	}
	
	public List<User> getListUsers() {
		return this.listUsers;
	}
	
	public void closeUDPSocket() {
		this.UDPsocket.close();
	}
}
