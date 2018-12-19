package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;

public class TCPListener implements Runnable{
	private static ClientHandler clientHandler;
	private static Thread thread;
	private HashMap<User, ClientHandler> hmap;

	public TCPListener(ServerSocket serverSocket, Network network) throws IOException {
        System.out.println("Waiting for a client ..."); 
    	Socket clientsocket = serverSocket.accept();
        clientHandler = new ClientHandler(clientsocket, network);

        thread = new Thread(clientHandler);
        thread.start();
        System.out.println("Client accepted: " + clientsocket);
        System.out.println("Connexion with "+clientsocket.getInetAddress().toString());

        while(clientHandler.getUserdest()==null) {}
       
        hmap.put(clientHandler.getUserdest(), clientHandler);
        System.out.println("t: "+clientHandler.getUserdest());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public HashMap<User, ClientHandler> getHmap() {
		return hmap;
	}
	
	
}
