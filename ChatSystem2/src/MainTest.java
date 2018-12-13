import java.io.IOException;

public class MainTest {
 public static void main(String[] args) throws IOException {
	 System.out.println("Launch Server");
	 ManagerServer server = new ManagerServer(1234);
	 server.closeServer();
 }
}
