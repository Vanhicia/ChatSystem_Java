
public class Controller {
	private User user;
	private Window view;
	private CommunicationController cc;
	private int port; 
	
	public Controller(int port, Window view) {
		this.user = null;
		this.view = view;
		this.port = port;
	}
	
	public void createUser(int id, String pseudo) throws ExceptionPort {
		this.user = new User(id, pseudo);
		this.cc = new CommunicationController(port);
	}
	
	public void changePseudo(String pseudo) {
		if (this.checkUnicityPseudo(pseudo)==true) {
			user.setPseudo(pseudo);
		} 
		else {
			// affichage d'un message d'erreur
		}		
	}
	
	public boolean checkUnicityPseudo(String pseudo) {
		// voir comment accéder à la base de données
		return true;
	}
	
	public void refreshWindows() {
		
	}
	
	public void startSession(int id) {
		ClientTCP client = new ClientTCP(1024+user.getId()+id);
		
	}

}
