package main;
import java.io.Serializable;

public class Packet implements Serializable{
	private static final long serialVersionUID = 1L;
	private User srcUser;
	private User destUser;
	
	public Packet(User srcUser, User destUser) {
		this.srcUser = srcUser;
		this.destUser = destUser;
	}
	
	public User getSrcUser() {
		return this.srcUser;
	}
	
	public User getDestUser() {
		return this.destUser;
	}
	
	public void setSrcUser(User srcUser) {
		this.srcUser=srcUser;
	}
	
	public void setDestUser(User destUser) {
		this.destUser=destUser;
	}

}
