package main;

import java.io.*;
import java.lang.Runnable;
import java.net.Socket;
import java.util.Observable;
import main.Message;
import main.gui.ChatWindow;

public class ClientHandler  extends Observable implements Runnable {
	
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Network network;
	private User userdest=null;
	private History history;
	private ChatWindow chat;
	private volatile boolean running = true;
	
	public ClientHandler(Socket clientSocket,Network network) { 
		try {
			this.clientSocket = clientSocket;
			
			// Create input buffer and output buffer
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.in = new ObjectInputStream(clientSocket.getInputStream());
			
			this.network=network;
		} catch (IOException e) {
			System.out.println("Error : Cannot create input and output buffers");
		}
	} 
	  	
	// Return the message that we have received from input buffer
	public Message receiveData () throws ClassNotFoundException, IOException {
		return (Message) in.readObject();
	}
	
	// Send a message from System or user (Server)
	public void sendData(Message message, boolean isSystemMessage) throws IOException{
		System.out.println("Paquet envoyé : "+message.msg);
		
		//We do not add system messages in user's history
		if(!isSystemMessage) {
			this.history.addEntry(message);
		} 
		
		// We write message in output buffer to send this message
        out.writeObject(message);
        out.flush();

        //We send a closure message (src=null) for client and we close our window
      /*  if (message.getSrcUser()==null) {
        	this.closeClientHandler();
		}*/
	}
		
	public void run() {
		 while(running){
			 try {
				 Message data = receiveData();
				 if (data!=null) { // We receive a message
				    if (userdest==null && data.getSrcUser()!=null){
				    	// We know who is the remote user so we stock this information
				    	setUserdest(data.getSrcUser());
				    	//We create a history for this conversation
			            this.history = new History(this.network.getController().getUser(), userdest, this.network.getController().getDatabase());
			            // We create a chat window for this conversation
			            this.chat=new ChatWindow(this.network.getServer(), this.network.getController().getUser(), userdest);
			            chat.displayWindow();
			            // If these two users have previous conversations, we print them in chat window
			            chat.getWindowChatText().append(this.history.printHistory());
				    } else{
				    	
				    	if (data.getSrcUser()==null) {
				    		// We receive a closure message (src=null)
				    		// We close this connection and the chat window that it associated
				    		chat.closeWindow();
				    		this.closeClientHandler();
				    	} else {
				    		// We receive a message, we print it and add it in history
				    		System.out.println("Paquet reçu : "+data.msg);
				    		chat.refreshWindow(data.getSrcUser().getPseudo(), data.msg);
			                this.history.addEntry(data);	    		    		
				    	}
			        }
					
		    	}
		
			} catch (ClassNotFoundException e) {
				System.out.println("Error : Cannot receive message because Message class not found");
			} catch (NullPointerException e) {
				System.out.println("Error : Null PointerException");
			} catch (Exception e) {}
		}
	}
	    
	public void closeClientHandler() {
	      try {
	          // Close all streams and sockets
	          out.close();
	          in.close();
	          clientSocket.close();
	      } catch (IOException e) {
	          System.out.println("Error : Cannot close client handler");
	      }
	    }
	
	//Getters
	public User getUserdest() {
		return userdest;
	}

	public History getHistory() {
		return history;
	} 
	
	//Setters
	public void setUserdest(User userdest) {
		this.userdest = userdest;
		setChanged();
		notifyObservers();
	}

}

