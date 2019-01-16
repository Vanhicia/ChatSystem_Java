package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.UUID;

public class ManagerServer  implements Runnable{
	
	private ServerSocket serverSocket;
	private Network network;
	private TCPListener listener;
	private static Thread threadlistener;
	private HashMap<UUID, ClientHandler> hmap;
	
	public ManagerServer(int port, Network network) throws IOException {
		System.out.println("Binding to port " + port + ", please wait  ...");
		serverSocket = new ServerSocket(port);
		System.out.println("Server started: " + serverSocket);
		this.network=network;
	}
        
	public void run() {
		try {
			// We run a TCP listener to catch client connections
			System.out.println("Run TCP listener");
			listener = new TCPListener(serverSocket,network);
			threadlistener = new Thread(listener);
			threadlistener.start();
			threadlistener.join();
		} catch (IOException e) {
			System.out.println("Error : Exception has been raised in ManagerServer class");
		} catch (InterruptedException e) {
			System.out.println("TCP listenner has been interrupt for unknown reason");
		}
    }
	
	// Send a message from System or user (Server)
	public void sendMessage(Message message, boolean isSystemMessage) throws IOException {
		while(listener.getHmap()==null) {} // Wait that we have a client connection and we have finished to stock remote user information
		this.hmap = listener.getHmap();
		System.out.println("Server send to user " + message.getDestUser().getPseudo());
		this.hmap.get(message.getDestUser().getId()).sendData(message, isSystemMessage); // We send message to the right client (it is useful when we have several clients)
	}
	
	public void closeManagerServer() {
		System.out.println("Close Server");	
		// Close TCP listener
		listener.closeListener();
		// Close network because local user is now disconnected
		this.network.setConnectionStatus(false); 
	}

	//Getter
	public HashMap<UUID, ClientHandler> getHmap() {
		return hmap;
	}
}
