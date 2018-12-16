package main;

import java.io.Serializable;
import java.net.InetAddress;
import java.time.LocalDateTime;


public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private String pseudo;
	private final InetAddress address; //IP address
	private  final LocalDateTime timeConnection;

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
