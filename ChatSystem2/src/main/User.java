package main;

import java.io.Serializable;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.UUID;


public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String pseudo;
	private final InetAddress address; //IP address
	private  final LocalDateTime timeConnection;
	
	public User(UUID id, String pseudo, InetAddress address, long timeConnection) {
		this.id = id;
		this.pseudo = pseudo;
		this.address = address;
		this.timeConnection = LocalDateTime.now();
	}
	
        public User(InetAddress IPAddress) {
		this.pseudo = "";
		this.address = IPAddress;
		this.timeConnection = LocalDateTime.now();
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
	
	public LocalDateTime getTimeConnection() {
		return timeConnection;
	}
	
}
