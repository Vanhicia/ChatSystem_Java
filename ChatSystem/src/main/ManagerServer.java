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

public class ManagerServer  implements Runnable, Observer{
	//
	private static Thread threadlistener;
	private ServerSocket serverSocket;
	private volatile boolean running = true ;
	private Network network;
	//private History history;
	private TCPListener listener;
	private HashMap<User, ClientHandler> hmap;
	
	public ManagerServer(int port) throws IOException {
		System.out.println("Binding to port " + port + ", please wait  ...");
		serverSocket = new ServerSocket(port);
		System.out.println("Server started: " + serverSocket);
	}
	
	public ManagerServer(int port, Network network) throws IOException {
		System.out.println("Binding to port " + port + ", please wait  ...");
		serverSocket = new ServerSocket(port);
		System.out.println("Server started: " + serverSocket);
		this.network=network;
    	listener = new TCPListener(serverSocket,network);
    	threadlistener = new Thread(listener);
    	threadlistener.start();

	}
        
	public void run() {

            
                    
 
    }
	
	public void sendMessage(Message message) throws IOException {
		while(listener.getHmap()==null) {} // maybe try to insert an observable in tcplistener and here an observer
		this.hmap=listener.getHmap();
		this.hmap.get(message.getDestUser()).sendData(message);
	}
	
	public void closeServer() throws IOException {
		System.out.println("Close Server");
                this.running=false;
	        serverSocket.close();
	}

	/*public HashMap<InetAddress, ClientHandler> getHmap() {
		return hmap;
	}
*/
	@Override
	public void update(Observable o, Object arg) {
		//arg contains the message that we have just received with src and dest
		System.out.println(arg.toString());
		
		
	}

	
}
