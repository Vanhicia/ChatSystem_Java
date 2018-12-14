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
	
	public ThreadSendingBroadcast(DatagramSocket socket, Packet packet) {
		this.socket = socket;
		this.packet = packet;
	}
	
    public void run() {
    	try {	        
	        // Serialize the packet to bytes
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(packet);
	        oos.close();
	        byte[] data = baos.toByteArray();
	        // Packet in broadcast
	        // port UDP = 1233
	        DatagramPacket outPacket = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"),1233) ;
	        socket.send(outPacket);
	        //socket.close();  
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }     
}
