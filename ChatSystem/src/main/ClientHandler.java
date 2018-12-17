package main;

import java.lang.Runnable;
import java.net.Socket;
import java.io.*;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private TCPListener listener;
	private volatile boolean running = true;
	private Network network;
	
	public ClientHandler(Socket clientSocket,Network network) {
	    this.clientSocket = clientSocket;
	    
	    // Create input buffer and output buffer
		try {
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.out = new PrintWriter(clientSocket.getOutputStream(),true);
			this.listener = new TCPListener(null);
			this.network=network;
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	  	
	public String receiveData () {
		String input = null;
		try {
                    input = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public void sendData(Message data) {
		out.println(data.getSrcUser().getPseudo()+": "+data.msg);
	}
	
        public void run() {


          while(running){
             // Wait for input from client and send response back to client
             //sendData("Connexion OK with Server");
      		String data = receiveData();
    		if (data!=null) {
    			Message msg = new Message(data,)
    			this.listener.setValue(msg);
    			
    		}

        }

  
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

  
  
}