package main;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

public class TestServer {
	public static void main (String arg[]) throws IOException { 
		Controller contr = new Controller();
		contr.connect("S");
		
		UUID uidsrc = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		UUID uiddest = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		
		User destUser = new User(uiddest, "S", InetAddress.getLocalHost(), 1);
		User srcUser = new User(uidsrc, "C", InetAddress.getLocalHost(), 1);
		
		ManagerServer server = new ManagerServer(1234,contr.getNetwork());
		server.run();
		server.sendMessage(new Message("Hello there", destUser,srcUser));
		server.sendMessage(new Message("LOL", destUser,srcUser));
	}
}
