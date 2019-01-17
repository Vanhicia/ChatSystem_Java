
package main;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import main.gui.ChatWindow;

public class ClientTCP implements Runnable {
	private Socket link;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private History history;
    private ChatWindow chat;
    private boolean running;
	
	public ClientTCP (InetAddress address, int port, User userdistant, Network nwk) {
		try {
			System.out.println("Establishing connection. Please wait ...");
 			this.link = new Socket(address,port);
 			System.out.println("Connected: " + link);
 			
 			// Create input buffer and output buffer
			this.out = new ObjectOutputStream(new BufferedOutputStream(link.getOutputStream()));
			this.in = new ObjectInputStream(link.getInputStream());
			
			this.history = new History(nwk.getController().getUser(), userdistant, nwk.getController().getDatabase());
			this.running=true;
		} catch (IOException e) {
			System.out.println("Error : Cannot create input and output buffers");
		}
	}
	
	// Return the message that we have received from input buffer
	public Message receiveData () throws ClassNotFoundException, IOException  {
		return (Message) in.readObject();
	}
	
	// Send a message from System or user (Client)
	public void sendData(Message message, boolean isSystemMessage) throws IOException {
		System.out.println("Paquet envoyé : "+message.msg);
		
		//We do not add system messages in user's history
		if(!isSystemMessage) {
			this.history.addEntry(message);
		}
		
		// We write message in output buffer to send this message
		out.writeObject(message);
		out.flush();
	}
	
	public void closeConnection() {
		try {   
            System.out.println("Client close");
            // Close all streams and sockets
            in.close();
            out.close();
            link.close();
            running=false;
		} catch (IOException e) {
			System.out.println("Error : Cannot close connection");
		}
	}
	
	public void run() {
		while(running) {
			try {
                Message data = receiveData();
	    		if (data!=null) { // We receive a message
	    			if (data.getSrcUser()==null) {
	    				// We receive a closure message (src=null) 
	    				// We close this connection and the chat window that it associated
    		    		this.closeConnection();
    		    		chat.closeWindow();
    		    		this.running=false;
    		    	} else {
    		    		// We receive a message, we print it and add it in history
		    			System.out.println("Paquet reçu :" +data.msg);
		    			chat.refreshWindow(data.getSrcUser().getPseudo(), data.msg);
			    		this.history.addEntry(data);
    		    	}
	    		}
			} catch (ClassNotFoundException e) {				
				System.out.println("Error : Cannot receive message because Message class not found");
			} catch (NullPointerException e) {
				System.out.println("Error : Null PointerException");
			} catch (Exception e) {}
		}
	}
	
	//Getters
    public ChatWindow getChat(ChatWindow chat){
        return chat;
    }
    
    public History getHistory() {
        return history;
    }
    
    //Setters
    public void setChat(ChatWindow chat) {
        this.chat = chat;
    }

}