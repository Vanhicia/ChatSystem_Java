import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class mainTest {
	public static void main (String arg[]) { 
		
		//Controller contr = new Controller(11000,11001);
		/*User user1 = new User(1,InetAddress.getLocalHost(),System.currentTimeMillis());
		CommunicationController cc = new CommunicationController(12000);
		System.out.println("OK1\n");
		CommunicationController cc1 = new CommunicationController(13000);
		System.out.println("OK2\n");
		cc.beginNewSession(3,11000);*/
		
		try {
			User srcUser = new User(UUID.randomUUID(), "Coco", InetAddress.getLocalHost(), 1);
			User srcUser_bis = new User(UUID.randomUUID(), "Lala", InetAddress.getLocalHost(), 1);
			User destUser = new User(UUID.randomUUID(), "Mimi", InetAddress.getLocalHost(), 1);
	
			System.out.println("Try to connect with Server");
	
				
			ClientTCP c1 = new ClientTCP(InetAddress.getLocalHost(),1234);
			ClientTCP c2 = new ClientTCP(InetAddress.getLocalHost(),1234);
			c1.sendData(new Message("Hello1", srcUser, destUser));
			c2.sendData(new Message("Hello2 !!!!!!!!", srcUser_bis, destUser));
			System.out.println(c1.receiveData());
			System.out.println(c2.receiveData());
			c1.closeConnection();
			c2.closeConnection();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
