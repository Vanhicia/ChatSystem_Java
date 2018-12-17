import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/* Listen and notify when a packet is received */

public class UDPListener implements Runnable {
	private Network nwk;
	private DatagramSocket socket;
	
	public UDPListener (Network nwk, DatagramSocket socket) {
		this.nwk = nwk;
		this.socket = socket;
	}
	
	public void run() {
		byte[] data = new byte[1024*4];
		while (this.socket.isClosed()==false) {
			System.out.println("Listening...");
			try {				
				DatagramPacket inPacket = new DatagramPacket(data, data.length);
				socket.receive(inPacket);
				System.out.println("UDP packet received received");
				
				//Deserialize packet
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
	            ObjectInputStream ois = new ObjectInputStream(bais);
	            UDPPacket packet = (UDPPacket) ois.readObject();
	            
	            this.handlePacket(packet);
	            
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
			/* If the packet asked the uniqueness of a pseudo */
			case "NewPseudo":
				/* If the local user uses this pseudo, he/she answered with a unicast UDP packet */
				if (packet.getSrcUser().getPseudo().equals(nwk.getController().getUser().getPseudo())) {
					System.out.println("Someone would like to use your pseudo !");
					/* Send a packet in unicast to prevent the pseudo is alreadu used */
					this.nwk.sendUDPPacketUnicast(new UDPPacket(this.nwk.getController().getUser(),packet.getSrcUser(),"PseudoAlreadyUsed"),packet.getSrcUser().getAddress());
				}
				break;
			/* If the pseudo which the user wants is already used */
			case "PseudoAlreadyUsed":
				System.out.println("Someone has already this pseudo, choose another one !");
				//TO DO
				break;
			/* If a new user is connected, he/she is added to the list of users */
			case "UserConnected":
				System.out.println("A new user is connected");
				this.nwk.addUser(packet.getSrcUser());
			/* If a user has changed his/her pseudo */
				break;
			case "UserUpdated":
				System.out.println("A user has changed his/her pseudo");
				//contr.updateUser(packet.getSrcUser());
			/* If a user is disconnected */
				break;
			case "UserDisconnected":
				System.out.println("A user is disconnected");
				this.nwk.deleteUser(packet.getSrcUser());
				break;
			default :
				System.out.println("The UDP packet motive is not recognized !");
		}
	}
	
	
}
