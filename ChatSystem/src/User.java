import java.net.InetAddress;
import java.util.UUID;

//import java.sql.Time;

public class User {
	private UUID id;
	private String pseudo;
	private InetAddress address; //adresseIP
	private long timeConnection;
	
	public User(UUID id, String pseudo, InetAddress address, long timeConnection) {
		this.id = id;
		this.pseudo = pseudo;
		this.address = address;
		this.timeConnection = timeConnection;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public InetAddress getAddress() {
		return address;
	}
	
	public long getTimeConnection() {
		return timeConnection;
	}
	
}
