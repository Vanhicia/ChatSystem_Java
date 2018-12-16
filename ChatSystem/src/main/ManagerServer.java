package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerServer  implements Runnable{
	
	private static ClientHandler clientHandler;
	private static Thread thread;
	private static ServerSocket serverSocket;
	private volatile boolean running = true ;
	public ManagerServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		 

	}
        
	public void run() {
            while (running) {
                try {
                    clientHandler = new ClientHandler(serverSocket.accept());
                    thread = new Thread(clientHandler);
                    thread.start();
                } catch (IOException ex) {
                    Logger.getLogger(ManagerServer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
	public void closeServer() throws IOException {
		System.out.println("Close Server");
                this.running=false;
	        serverSocket.close();
	}
	 

}
