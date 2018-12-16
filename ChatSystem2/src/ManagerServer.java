package main;

import java.io.IOException;
import java.net.ServerSocket;

public class ManagerServer {
	
	private static ClientHandler clientHandler;
	private static Thread thread;
	private static ServerSocket serverSocket;
	private boolean running = true ;
	public ManagerServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		 

	}
        
	public void run() throws IOException {
            while (running) {
                clientHandler = new ClientHandler(serverSocket.accept());
                thread = new Thread(clientHandler);
                thread.start();
            }
        }
	public void closeServer() throws IOException {
		System.out.println("Close Server");
                this.running=false;
	        serverSocket.close();
	}
	 

}
