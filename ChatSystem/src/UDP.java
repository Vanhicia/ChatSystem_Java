import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP implements Runnable {
	private DatagramSocket socket;
	private boolean isStopped = false;
	
	public UDP(int port){
		this.socket = new DatagramSocket(port);
		this.isStopped = false;
		byte[] buffer = new byte[256];
	}
	
    public void run() {
        byte[] buffer = new byte[1024];
		while(!isStopped) {
			//DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
			
			/* Send packet with the identity of the user, in broadcast */
			packet = new Packet(User)
			DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, port);
			socket.receive(inPacket);
		}
		socket.close();
    }
    
    /* Send packet in broadcast */
    public void sendPacketBroadcast(Packet packet) {
    	
    }
	
	/* Ask the closure of the socket */
	public void closeUDPSocket() {
		isStopped = true;
	}

}
