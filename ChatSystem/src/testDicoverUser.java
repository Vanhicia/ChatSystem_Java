import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class testDicoverUser {
	public static void main (String arg[]) { 
		Controller contr = new Controller();
		/*User user1 = new User(UUID.randomUUID(), "Coco", InetAddress.getLocalHost(), 1);
		contr.setUser(user1);*/
		contr.connect("Lili");
	}
}