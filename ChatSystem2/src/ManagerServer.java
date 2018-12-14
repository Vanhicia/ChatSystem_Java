import java.io.IOException;
import java.net.ServerSocket;

public class ManagerServer {
	
	private static ClientHandler clientHandler;
	private static Thread thread;
	private static ServerSocket serverSocket;
	
	public ManagerServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		 
		while (true) {
			 clientHandler = new ClientHandler(serverSocket.accept());
			 thread = new Thread(clientHandler);
			 thread.start();
		}
	 }
	
	 protected void closeServer() throws IOException {
		 	System.out.println("Close Server");
	        serverSocket.close();
	}
	 
	 
}
