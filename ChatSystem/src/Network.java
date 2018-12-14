import java.io.IOException;
import java.net.*;
import java.util.List;

public class Network {
	private Controller contr;
	private User user;
	private ThreadUDPListening UDPListener;
	private DatagramSocket UDPsocket;

    protected static int portUDP = 1233;
    protected static int portTCP = 1234;
		
	public Network(Controller contr, User user) {
		this.contr = contr;
		this.user = user;
		
		// create a ThreadUDPListener
		try {
			this.UDPsocket = new DatagramSocket(portUDP);
			this.UDPListener = new ThreadUDPListening(this.contr, this, this.user, this.UDPsocket);
			Thread threadListening = new Thread(this.UDPListener);
			threadListening.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		/* Send the pseudo chosen in broadcast */
		this.sendUDPPacketBroadcast(new UDPPacket(this.user,null,"NewPseudo"));
		/* Wait a answer */
		// TODO
		/* Send the identity of the new user in broadcast */
		this.sendUDPPacketBroadcast(new UDPPacket(this.user,null,"UserConnected"));
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	public void closeSession(Session s) {
		
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
		ThreadSendingUDPPacket tsb = new ThreadSendingUDPPacket(this.UDPsocket, packet, address);
		Thread threadSending = new Thread(tsb);
		threadSending.start();
	}
	
	/* Send the UDP packet in broadcast */
	public void sendUDPPacketBroadcast(UDPPacket packet) {
		try {
			ThreadSendingUDPPacket tsb = new ThreadSendingUDPPacket(this.UDPsocket, packet, InetAddress.getByName("255.255.255.255"));
			Thread threadSending = new Thread(tsb);
			threadSending.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeUDPSocket() {
		this.UDPsocket.close();
	}
}
