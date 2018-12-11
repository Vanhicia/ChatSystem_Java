//import java.sql.Time;

public class User {
	private int id;
	private int port;
	private String pseudo;
	private long horodate;
	
	public User(String pseudo, int port) throws ExceptionPort {
		if (port<10000 && port>11000) {
				throw new ExceptionPort("Incorrect port : the port must be between 10000 and 11000");
		}
		else {
			this.port = port;
		}
		//this.id = id;
		this.pseudo = pseudo;
		this.horodate = System.currentTimeMillis();
	}
	
	public int getId() {
		return id;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public long getHorodate() {
		return horodate;
	}
	
}
