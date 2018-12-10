
public class Controller {
	private Agent model;
	private Window view;
	
	public Controller(Agent model, Window view) {
		this.model = model;
		this.view = view;
	}
	
	public void changePseudo(String pseudo) {
		if (this.checkUnicityPseudo(pseudo)==true) {
			model.setPseudo(pseudo);
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

}
