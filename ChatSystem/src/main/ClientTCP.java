
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTCP implements Runnable {
	private Socket link;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isStopped = false;
	
	public ClientTCP (InetAddress address, int port) {
		try {
			this.link = new Socket(address,port);
			this.in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			this.out = new PrintWriter(link.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isStopped() {
		return isStopped;
	}
	
	public void stopSClient( ) {
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
	
	public void sendData(Message data) {
		out.println(data.getSrcUser().getPseudo()+": "+data.msg);
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
		String msg = receiveData();
		if (msg!=null) {
			System.out.println(msg);
		}

		closeConnection();
	}
}

