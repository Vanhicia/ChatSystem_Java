package main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;



public class TestClient {
	public static void main (String arg[]) throws IOException { 		
		try {
			UUID uidsrc = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
			UUID uiddest = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
			
			User destUser = new User(uiddest, "Lili", InetAddress.getLocalHost(), 1);
			User srcUser = new User(uidsrc, "Coco", InetAddress.getLocalHost(), 1);
	//		Controller contr = new Controller();
	//		contr.connect("Coco");

			ClientTCP c1 = new ClientTCP(InetAddress.getLocalHost(),1234);		
			c1.sendData(new Message("Hello1", srcUser, destUser));

			c1.closeConnection();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

// S <- C : C envoi à S et S recoit et affiche OK
// a faitre S -> C : S envoi au bon C et C recoit et affiche
