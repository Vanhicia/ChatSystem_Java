import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class TestClient {
	public static void main (String arg[]) { 		
		try {
			User destUser = new User(UUID.randomUUID(), "Lili", InetAddress.getLocalHost(), 1);

			Controller contr = new Controller();
			contr.connect("Coco");
			
			ClientTCP c1 = new ClientTCP(InetAddress.getLocalHost(),1234);
			//c1.sendData("test1");
			c1.sendData(new Message("Hello1", contr.getUser(), destUser));
			c1.closeConnection();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
