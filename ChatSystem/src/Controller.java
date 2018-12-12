import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class Controller {
	private User user;
	private Window view;
	private CommunicationController cc;
	
	public Controller(int portUDP, int portTCP) {
		this.user = null;
		this.view = new Window();
	}
	
	public User getUser() {
		return user;
	}
	
	public void connect(String pseudo, int portUDP, int portTCP) {
		try {
			this.user = new User(UUID.randomUUID(), pseudo, InetAddress.getLocalHost(), System.currentTimeMillis(), cc.getPortUDP(), cc.getPortTCP());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.cc = new CommunicationController(portUDP, portTCP);
		/* Send the pseudo in broadcast */
	}
	
	public void changePseudo(String pseudo) {
		if (cc.checkUnicityPseudo(pseudo)==true) {
			user.setPseudo(pseudo);
		} 
		else {
			// affichage d'un message d'erreur
		}		
	}
		
	public void refreshWindows() {
		
	}

}
