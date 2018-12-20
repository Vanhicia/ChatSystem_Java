package main;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.UUID;

//import java.sql.Time;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String pseudo;
	private InetAddress address; //IP address
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
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	public long getTimeConnection() {
		return timeConnection;
	}
	
}