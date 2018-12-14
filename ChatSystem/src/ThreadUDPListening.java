import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/* Listen and notify when a packet is received */

public class ThreadUDPListening implements Runnable {
	private Controller contr;
	private User localUser;
	private DatagramSocket socket;
	
	public ThreadUDPListening (Controller contr, User user, DatagramSocket socket) {
		this.contr=contr;
		this.localUser = user;
		this.socket = socket;
	}
	
	public void run() {
		byte[] data = new byte[1024*4];
		while (this.socket.isClosed()==false) {
			System.out.println("Listening...");
			try {				
				DatagramPacket inPacket = new DatagramPacket(data, data.length);
				socket.receive(inPacket);
				System.out.println("Datagram received");
				
				//Deserialize packet
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
	            ObjectInputStream ois = new ObjectInputStream(bais);
	            UDPPacket packet = (UDPPacket) ois.readObject();
	            
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Handle the UDP packet received */
	public void handlePacket(UDPPacket packet) {
		String motive = packet.getMotive();
		switch(motive) {
			/* If the packet asked the unicity of a pseudo */
			case "NewPseudo":
				/* If the local user uses this pseudo, he/she answered with a unicast UDP packet */
				if (packet.getSrcUser().getPseudo()==localUser.getPseudo()) {
					System.out.println("Someone would like to use your pseudo !");
					/* Send a packet in unicast */
					//TODO
				}
			/* If a new user is connected, he/she is added to the list of users */
			case "UserConnected":
				System.out.println("A new user is connected");
				//contr.addUserConnected();
			/* If a user has changed his/her pseudo */
			case "UserUpdated":
				System.out.println("A user has changed his/her pseudo");
				//contr.updateUser(packet.getSrcUser());
			/* If a user is disconnected */
			case "UserDisconnected":
				System.out.println("A user is disconnected");
				//contr.deleteUser(packet.getSrcUser());
		}
	}
	
	
}
