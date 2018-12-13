import java.lang.Runnable;
import java.net.Socket;
import java.io.*;

class ClientHandler implements Runnable {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	  
	public ClientHandler(Socket clientSocket) {
	    this.clientSocket = clientSocket;
	    
	    // Create input buffer and output buffer
		try {
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.out = new PrintWriter(clientSocket.getOutputStream(),true);
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
		out.println(data.msg);
	}
	
  public void run() {
	  // Wait for input from client and send response back to client
	  //sendData("Connexion OK with Server");
	  System.out.println(receiveData());
	  


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