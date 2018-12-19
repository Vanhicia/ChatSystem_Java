package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class TCPListener implements Runnable, Observer{
	private static ClientHandler clientHandler;
	private static Thread thread;
	//private HashMap<User, ClientHandler> hmap;
	private HashMap<UUID, ClientHandler> hmap;
	private ClientHandler userdestupdate=null;
	public TCPListener(ServerSocket serverSocket, Network network) throws IOException {
		//this.hmap = new HashMap<User, ClientHandler>();
		this.hmap = new HashMap<UUID, ClientHandler>();
		System.out.println("Waiting for a client ..."); 
    	Socket clientsocket = serverSocket.accept();
        clientHandler = new ClientHandler(clientsocket, network);

        thread = new Thread(clientHandler);
        thread.start();
        System.out.println("Client accepted: " + clientsocket);
        System.out.println("Connexion with "+clientsocket.getInetAddress().toString());

        clientHandler.addObserver(this);
	}

	@Override
	public void run() {
		
		
	}

	public HashMap<UUID, ClientHandler> getHmap() {
		return hmap;
	}

	@Override
	public void update(Observable o, Object arg) {
		userdestupdate=(ClientHandler) o;

        System.out.println("tlist: "+userdestupdate);
        if (userdestupdate.getUserdest()!=null && userdestupdate!=null) {
        	this.hmap.put(userdestupdate.getUserdest().getId(), userdestupdate);
        }

	}
	
	
}
