package main;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class testDiscoverUser {
	public static void main (String arg[]) { 
		Controller contr = new Controller();
		contr.connect("Lili");
	}
}