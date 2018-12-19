package main;

import java.lang.Runnable;
import java.net.Socket;
import java.io.*;
import main.Message;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private volatile boolean running = true;
	private Network network;
	private User userdest=null;
	
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
		System.out.println("Paquet envoyé : "+message.msg);
		out.writeObject(message);
		out.flush();
	}
		

        public void run() {


          //while(running){
             // Wait for input from client and send response back to client
			try {
				Message data;
				data = receiveData();
		
	    		if (data!=null) {
	    			 System.out.println("s :" +data.msg);
	    		    if (userdest==null){
		    			 this.userdest = data.getSrcUser();
		    		     System.out.println(userdest);
	    		    }

	    		}
	    		
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         // }	

  
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

  
  
}

