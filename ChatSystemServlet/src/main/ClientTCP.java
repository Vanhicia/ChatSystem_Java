
package main;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.WindowConstants;
import main.gui.ChatWindow;
public class ClientTCP implements Runnable {
	private Socket link;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private History history;
	private User userdistant;
        private ChatWindow chat;
/*	public ClientTCP (InetAddress address, int port) {
		try {
			System.out.println("Establishing connection. Please wait ...");
 			this.link = new Socket(address,port);
 			System.out.println("Connected: " + link);
			this.out = new ObjectOutputStream(new BufferedOutputStream(link.getOutputStream()));
			this.in = new ObjectInputStream(link.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
	
	public ClientTCP (InetAddress address, int port, User userdistant) {
		try {
			System.out.println("Establishing connection. Please wait ...");
 			this.link = new Socket(address,port);
 			System.out.println("Connected: " + link);
			this.out = new ObjectOutputStream(new BufferedOutputStream(link.getOutputStream()));
			this.in = new ObjectInputStream(link.getInputStream());
			this.userdistant =userdistant;
			this.history = new History(userdistant);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Message receiveData () throws ClassNotFoundException {
		Message input = null;
		try {
          input = (Message) in.readObject();
		} catch (IOException e) {

		}
		return input;
	}
	
	public void sendData(Message message) throws IOException {
		System.out.println("Paquet envoyé : "+message.msg);
		out.writeObject(message);
		out.flush();
		this.history.addEntry(message);
	}
	
	public void closeConnection() {
		try {   
                    System.out.println("Client close");
                    in.close();
                    out.close();
                    link.close();
                    chat.closeWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
                            Message data;
                            data = receiveData();
	    		if (data!=null) {
	    			System.out.println("Paquet reçu :" +data.msg);
		    		this.history.addEntry(data);
                                chat.refreshWindow(data.getSrcUser().getPseudo(), data.msg);
	    		}
	    		
			} catch (ClassNotFoundException e) {}
		}
	}

    public void setChat(ChatWindow chat) {
        this.chat = chat;
    }

    public ChatWindow getChat(ChatWindow chat){
        return chat;
    }
    
    public History getHistory() {
        return history;
    }
        
        
}