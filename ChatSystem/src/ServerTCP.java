import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
	public int num;
	public ServerSocket servSocket;
	public Socket link;
	public BufferedReader in;
	public PrintWriter out;
	
	public ServerTCP (int num) {
		this.num = num;
		try {
			this.servSocket = new ServerSocket(num);
			this.link = servSocket.accept();
			this.in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			this.out = new PrintWriter(link.getOutputStream(),true);
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
	
}
