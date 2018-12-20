package main;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSender implements Runnable {
	private DatagramSocket socket;
	private UDPPacket packet;
	private InetAddress address;
	private int port;
	
	public UDPSender(DatagramSocket socket, UDPPacket packet, InetAddress address, int port) {
		this.socket = socket;
		this.packet = packet;
		this.address = address;
		this.port = port;
	}
	
    public void run() {
    	try {	        
	        // Serialize the packet
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(packet);
	        oos.close();
	        byte[] data = baos.toByteArray();

	        //Send the serialized packet
	        DatagramPacket outPacket = new DatagramPacket(data, data.length, this.address, this.port) ;
	        socket.send(outPacket);
	        
	        System.out.println("Datagram send to " + this.address);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }     
}
