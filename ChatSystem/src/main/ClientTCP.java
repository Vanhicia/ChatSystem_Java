
package main;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTCP implements Runnable {
	private Socket link;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ClientTCP (InetAddress address, int port) {
		try {
			System.out.println("Establishing connection. Please wait ...");
 			this.link = new Socket(address,port);
 			System.out.println("Connected: " + link);
			this.out = new ObjectOutputStream(new BufferedOutputStream(link.getOutputStream()));
			System.out.println("Test");
			this.in = new ObjectInputStream(link.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Message receiveData () throws ClassNotFoundException {
		Message input = null;
		try {
          input = (Message) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public void sendData(Message message) throws IOException {
		System.out.println("Paquet envoyé : "+message.msg);
		out.writeObject(message);
		out.flush();
	}
	
	public void closeConnection() {
		try {
			System.out.println("Client close");
			link.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//while(true) {
		try {
			Message data;
			data = receiveData();
			
    		if (data!=null) {
    			 System.out.println("c :" +data.msg);
   

    		}
    		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	//}
}