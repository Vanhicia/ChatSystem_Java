package main;
import java.util.ArrayList;

public class ListUsersUDPPacket extends UDPPacket {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> listUsers;
	
	public ListUsersUDPPacket(User srcUser, User destUser, ArrayList<User> listUsers) {
		super(srcUser, destUser, "ListUsers");
		this.listUsers= listUsers;
	}
	
	public ArrayList<User> getListUsers() {
		return this.listUsers;
	}
}
