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
	
	
}
