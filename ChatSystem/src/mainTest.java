
public class mainTest {
	public static void main (String arg[]) { 
		CommunicationController cc = new CommunicationController(12000);
		System.out.println("OK1\n");
		CommunicationController cc1 = new CommunicationController(13000);
		System.out.println("OK2\n");
		cc.beginNewSession(3,11000);
	}
}
