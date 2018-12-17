package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ManagerServer  implements Runnable, Observer{
	private HashMap<InetAddress, ClientHandler> hmap;
	private static ClientHandler clientHandler;
	private static Thread thread;
	private ServerSocket serverSocket;
	private volatile boolean running = true ;
	private Network network;
	
	public ManagerServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		 

	}
	
	public ManagerServer(int port, Network network) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.network=network;

	}
        
	public void run() {
            while (running) {
                try {
                	Socket clientsocket = serverSocket.accept();
                    clientHandler = new ClientHandler(clientsocket, network);
                    thread = new Thread(clientHandler);
                    thread.start();
                    hmap.put(clientsocket.getInetAddress(), clientHandler);
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

	public HashMap<InetAddress, ClientHandler> getHmap() {
		return hmap;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
		
	}

	
}
