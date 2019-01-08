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
	private ServerSocket serverSocket;
	private Network network;
	private static Thread thread;
	private HashMap<UUID, ClientHandler> hmap;
	private ClientHandler userdestupdate=null;
	private volatile boolean running;
	
	public TCPListener(ServerSocket serverSocket, Network network) throws IOException {
		this.running =true;
		this.serverSocket = serverSocket;
		this.network =network;
	}

	@Override
	public void run() {
		while(this.running) {
			try {
			this.hmap = new HashMap<UUID, ClientHandler>();
			System.out.println("Waiting for a client ..."); 
	    	Socket clientsocket;

			clientsocket = serverSocket.accept();

	        clientHandler = new ClientHandler(clientsocket, network);

	        thread = new Thread(clientHandler);
	        thread.start();
	        System.out.println("Client accepted: " + clientsocket);
	        System.out.println("Connexion with "+clientsocket.getInetAddress().toString());

	        clientHandler.addObserver(this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
                        clientHandler.close();
			this.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
	
}
