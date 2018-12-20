package main;

import java.lang.Runnable;
import java.net.Socket;
import java.util.Observable;
import java.io.*;
import main.Message;

public class ClientHandler  extends Observable implements Runnable{
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private volatile boolean running = true;
	private Network network;
	private User userdest=null;
	private History history;
	
	public ClientHandler(Socket clientSocket,Network network) {
	    this.clientSocket = clientSocket;

	    // Create input buffer and output buffer
		try {
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.in = new ObjectInputStream(clientSocket.getInputStream());
			this.network=network;
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	  	

	public Message receiveData () throws ClassNotFoundException, IOException {
		Message input = null;
        input = (Message) in.readObject();
		return input;
	}
	public void sendData(Message message) throws IOException {
		//System.out.println("Paquet envoyé : "+message.msg);
		this.history.addEntry(message);
		this.history.printHistory();
		out.writeObject(message);
		out.flush();
	}
		

        public void run() {

//TODO : maybe an observer/observable for read several packets (to optimize CPU resource use)
         while(running){
            // Wait for input from client and send response back to client
			try {
				Message data;
				
				data = receiveData();
		
	    		if (data!=null) { 
	    		    if (userdest==null && data.getSrcUser()!=null){
	    		    	setUserdest(data.getSrcUser());
	    			    this.history = new History(userdest);
	    		    }
	    		    
		    		this.history.addEntry(data);

		    		
	    		}

			} catch (ClassNotFoundException e) {} 
			catch (IOException e) {}
         }
         this.close();
        }
        
	   public void close() {
	      try {
	          // Close all streams and sockets
	          out.close();
	          in.close();
	          clientSocket.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	    }


	public User getUserdest() {
		return userdest;
	}


	public void setUserdest(User userdest) {
		this.userdest = userdest;
		setChanged();
		notifyObservers();
	} 
}

