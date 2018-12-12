import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP implements Runnable {
	private int port;
	private ServerSocket servSocket;
	private Socket link;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isStopped = false;
	
	public ServerTCP (int port) {
		this.port = port;
		try {
			this.servSocket = new ServerSocket(port);
			this.link = servSocket.accept();
			this.in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			this.out = new PrintWriter(link.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isStopped() {
		return isStopped;
	}
	
	public void stopServer( ) {
		this.isStopped = true;
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
	
	public void sendData(String data) {
		out.println(data);
	}
	
	public void closeConnection() {
		try {
			link.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(!isStopped()) {
			
		}
		closeConnection();
	}
	
}
