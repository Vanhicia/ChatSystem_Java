package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.UUID;

public class ManagerServer  implements Runnable{
	private static Thread threadlistener;
	private ServerSocket serverSocket;
	private Network network;
	private TCPListener listener;
	private HashMap<UUID, ClientHandler> hmap;
	
	public ManagerServer(int port, Network network) throws IOException {
		System.out.println("Binding to port " + port + ", please wait  ...");
		serverSocket = new ServerSocket(port);
		System.out.println("Server started: " + serverSocket);
		this.network=network;

	}
        
	public void run() {
		try {
			System.out.println("Run TCP listenner");
			listener = new TCPListener(serverSocket,network);
			threadlistener = new Thread(listener);
			threadlistener.start();
			threadlistener.join();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void sendMessage(Message message, boolean isSystemMessage) throws IOException {
		while(listener.getHmap()==null) {} 
		this.hmap=listener.getHmap();
		System.out.println("manager send " +this.hmap.get(message.getDestUser().getId()));
		System.out.println("manager send to user" +message.getDestUser().getPseudo());
		this.hmap.get(message.getDestUser().getId()).sendData(message, isSystemMessage);
	}
	
	public void closeManagerServer() {
		System.out.println("Close Server");		
		listener.closeListenner();
		this.network.setConnectionStatus(false); //the local user is now disconnected
	}

	public HashMap<UUID, ClientHandler> getHmap() {
		return hmap;
	}
}
