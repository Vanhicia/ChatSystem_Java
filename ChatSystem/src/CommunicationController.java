import java.io.IOException;
import java.net.*;
import java.util.List;

public class CommunicationController {
	private User user;
	private ServerTCP server;
	private int port;
	private List<Session> sessions;
	
	public CommunicationController(int port) {
		this.port = port;
		server = new ServerTCP(port);
	}
	
	public void beginNewSession(int id, int port) {
		ClientTCP client = new ClientTCP(port);
		Session session = ;
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
		this.port = i;
	}
}
