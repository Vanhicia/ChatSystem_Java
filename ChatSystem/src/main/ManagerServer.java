package main;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
public class ManagerServer  implements Runnable{
	//
	private static Thread threadlistener;
	private ServerSocket serverSocket;
	private Network network;
	//private History history;
	private TCPListener listener;
	//private HashMap<User, ClientHandler> hmap;
	private HashMap<UUID, ClientHandler> hmap;
	public ManagerServer(int port, Network network) throws IOException {
		System.out.println("Binding to port " + port + ", please wait  ...");
		serverSocket = new ServerSocket(port);
		System.out.println("Server started: " + serverSocket);
		this.network=network;
    	//

	}
        
	public void run() {
		try {
			listener = new TCPListener(serverSocket,network);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	threadlistener = new Thread(listener);
    	threadlistener.start();
    	try {
			threadlistener.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
 
    }
	
	public void sendMessage(Message message) throws IOException {
		while(listener.getHmap()==null) {} 
		this.hmap=listener.getHmap();
		System.out.println("manager send " +this.hmap.get(message.getDestUser().getId()));
		this.hmap.get(message.getDestUser().getId()).sendData(message);
	}
	
	public void closeServer() throws IOException {
		System.out.println("Close Server");
		listener.setRunning(false);
	}



	
}
