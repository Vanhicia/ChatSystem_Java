package main;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class testDiscoverUser {
	public static void main (String arg[]) throws InterruptedException { 
		Controller contr = new Controller();
		contr.connect("Titi");
		//TimeUnit.SECONDS.sleep(3);
		//contr.changePseudo(1, "Toto");
		//contr.disconnect();
		
	}
}