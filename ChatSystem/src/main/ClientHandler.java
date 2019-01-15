package main;

import java.lang.Runnable;
import java.net.Socket;
import java.util.Observable;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Message;
import main.gui.ChatWindow;

public class ClientHandler  extends Observable implements Runnable{
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private volatile boolean running = true;
	private Network network;
	private User userdest=null;
	private History history;
	private ChatWindow chat;
	
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
	public void sendData(Message message, boolean isSystemMessage){
		System.out.println("Paquet envoyé : "+message.msg);
		if(!isSystemMessage) {
			this.history.addEntry(message);
		}
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException ex) {
                chat.refreshWindow("SYSTEM", "Dest unreachable, you should close this window. \n "
                        + "Click on disconnect button");
            }
	}
		

        public void run() {

         while(running){
            // Wait for input from client and send response back to client
			try {
                Message data= receiveData();
	    		if (data!=null) { 
	    		    if (userdest==null && data.getSrcUser()!=null){
	    		    	setUserdest(data.getSrcUser());
                        this.history = new History(this.network.getController().getUser(), userdest, this.network.getController().getDatabase());
                        this.chat=new ChatWindow(this.network.getServer(), this.network.getController().getUser(), userdest);
                        chat.displayWindow();
                        chat.getWindowChatText().append(this.history.printHistory());
	    		    } else{
	    		    	if (data.getSrcUser()==null) {
	    		    		this.close();
	    		    		chat.closeWindow();
	    		    	} else {
	                        chat.refreshWindow(data.getSrcUser().getPseudo(), data.msg);
	                        this.history.addEntry(data);	    		    		
	    		    	}

                    }
		    		System.out.println("Paquet reçu : "+data.msg);
	    		}

			} catch (ClassNotFoundException e) {} 
			catch (IOException e) {}
         }
        }
        
	   public void close() {
	      try {
	          // Close all streams and sockets
	          out.close();
	          in.close();
	          clientSocket.close();
              //chat.closeWindow();
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


	public History getHistory() {
		return history;
	} 
	
	
}

