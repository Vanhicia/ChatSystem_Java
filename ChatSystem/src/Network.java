import java.io.IOException;
import java.net.*;
import java.util.List;

public class Network {
	private Controller contr;
	private User user;
	//private ServerTCP server;
	private int portUDP = 1233; // used for broadcast
	private int portTCP = 1234; // used for conversations
	private ThreadUDPListening UDPListener;
	private DatagramSocket UDPsocket;
	
	//private List<Session> sessions;
	
	public Network(Controller contr, User user) {
		this.contr = contr;
		this.user = user;
		
		// create a ThreadUDPListener
		try {
			this.UDPsocket = new DatagramSocket(portUDP);
			this.UDPListener = new ThreadUDPListening(this.contr, this.user, this.UDPsocket);
			Thread threadListening = new Thread(this.UDPListener);
			threadListening.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		this.sendUDPPacket(new UDPPacket(this.user,null,"UserConnected"));
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
		//Session session = new Session();
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
	
	public void sendUDPPacket(UDPPacket packet) {
		ThreadSendingBroadcast tsb = new ThreadSendingBroadcast(this.UDPsocket, packet);
		Thread threadSending = new Thread(tsb);
		threadSending.start();
	}
	
	public void closeUDPSocket() {
		this.UDPsocket.close();
	}
}
