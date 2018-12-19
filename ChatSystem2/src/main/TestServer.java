package main;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

public class TestServer {
	public static void main (String arg[]) throws IOException { 

		ManagerServer server = new ManagerServer(1234);
		server.run();
	}
}
