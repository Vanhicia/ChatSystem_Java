import java.net.InetAddress;
import java.net.UnknownHostException;

public class mainTest {
	public static void main (String arg[]) { 
		//Controller contr = new Controller(11000,11001);
		/*User user1 = new User(1,InetAddress.getLocalHost(),System.currentTimeMillis());
		CommunicationController cc = new CommunicationController(12000);
		System.out.println("OK1\n");
		CommunicationController cc1 = new CommunicationController(13000);
		System.out.println("OK2\n");
		cc.beginNewSession(3,11000);*/
		System.out.println("Try to connect with Server");
		try {
			
			ClientTCP c1 = new ClientTCP(InetAddress.getLocalHost(),1234);
			ClientTCP c2 = new ClientTCP(InetAddress.getLocalHost(),1234);
			c1.sendData("Hello1");
			c2.sendData("Hello2 !!!!!!!!");
			System.out.println(c1.receiveData());
			System.out.println(c2.receiveData());
			c1.closeConnection();
			c2.closeConnection();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
