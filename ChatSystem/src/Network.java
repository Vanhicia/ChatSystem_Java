import java.io.IOException;
import java.net.*;
import java.util.List;

public class Network {
	private User user;
	//private ServerTCP server;
	private int portUDP; // used for broadcast
	private int portTCP; // used for conversations
	//private List<Session> sessions;
	
	public Network(int portUDP, int portTCP) {
		this.portUDP = portUDP;
		this.portTCP = portTCP;
		// create a server UDP
		//TO DO
		
		/*server = new ServerTCP(port);
		new Thread(server).start(); */
	}
	
	public int getPortUDP() {
		return portUDP;
	}
	
	public int getPortTCP() {
		return portTCP;
	}
	
	public void startSession(User userDist) {
		//create a thread ClientTCP
		ClientTCP client = new ClientTCP(userDist.getAddress(), portTCP);
		new Thread(client).start();
		//Session session = new Session();
	}
	
	public void closeSession(Session s) {
		
	}
	
	//Check if a server is listening on a port number (that is between 1 and 1024)   
	public void portScanner() {
		int i=1;
		boolean connect = false;
		while(i<1024 && !connect) {
			try {
				Socket link = new Socket(InetAddress.getLocalHost(), i) ;
				System.out.println("Server is listening on port "+i+" of localhost");
				connect = true ;
			} catch (UnknownHostException e) {
				System.out.println("Server is not listening on port "+i+" of localhost");
			} catch (IOException e) {
				System.out.println("Server is not listening on port "+i+" of localhost");
			}
			i++;
		}
		this.portUDP = i;
	}
	
	/* Return true if the pseudo is not used by another user yet */
	public boolean checkUnicityPseudo(String pseudo) {
		// send a message with the pseudo in broadcast
		// if no response, the pseudo is unique
		//TODO
		return true;
	}
}
