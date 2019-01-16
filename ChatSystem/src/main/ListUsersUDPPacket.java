package main;
import java.util.ArrayList;

/* UDP packet which contains the user list */
public class ListUsersUDPPacket extends UDPPacket {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> listUsers;
	
	public ListUsersUDPPacket(User srcUser, User destUser, ArrayList<User> listUsers) {
		super(srcUser, destUser, "ReplyListUsers");
		this.listUsers = listUsers;
	}
	
	public ArrayList<User> getListUsers() {
		return this.listUsers;
	}
}
