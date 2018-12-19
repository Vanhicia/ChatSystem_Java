package main;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

public class TestServer {
	public static void main (String arg[]) throws IOException { 
		UUID uidsrc = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		UUID uiddest = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		User destUser = new User(uiddest, "Lili", InetAddress.getLocalHost(), 1);
		User srcUser = new User(uidsrc, "Coco", InetAddress.getLocalHost(), 1);
		ManagerServer server = new ManagerServer(1234);
		server.run();
		server.sendMessage(new Message("Hello there", srcUser, destUser));
	}
}
