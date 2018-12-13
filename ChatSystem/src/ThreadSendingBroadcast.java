import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ThreadSendingBroadcast implements Runnable {
	private DatagramSocket socket;
	private Packet packet;
	
	public ThreadSendingBroadcast(int port, Packet packet) {
		try {
			this.socket = new DatagramSocket(port);
			this.packet = packet;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
    public void run() {
    	try {
	        byte[] buffer = new byte[1024];
	        
	        // Convert the object Packet to bytes
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(packet);
	        oos.close();
	        byte[] data = baos.toByteArray();
	        DatagramPacket outPacket = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"),9000) ;
	               
	        socket.close();  
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }     
}
