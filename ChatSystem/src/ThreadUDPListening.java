import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/* Listen and notify when a packet is received */

public class ThreadUDPListening implements Runnable {
	private Controller contr;
	private Network nwk;
	private User user;
	private DatagramSocket socket;
	
	public ThreadUDPListening (Controller contr, Network nwk, User user, DatagramSocket socket) {
		this.contr=contr;
		this.nwk = nwk;
		this.user = user;
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
		System.out.println("The UDP packet is handled");
		String motive = packet.getMotive();
		switch(motive) {
			/* If the packet asked the uniqueness of a pseudo */
			case "NewPseudo":
				/* If the local user uses this pseudo, he/she answered with a unicast UDP packet */
				if (packet.getSrcUser().getPseudo().equals(user.getPseudo())) {
					System.out.println("Someone would like to use your pseudo !");
					/* Send a packet in unicast to prevent the pseudo is alreadu used */
					this.nwk.sendUDPPacketUnicast(new UDPPacket(this.user,packet.getSrcUser(),"PseudoAlreadyUsed"),packet.getSrcUser().getAddress());
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
				//contr.addUserConnected();
			/* If a user has changed his/her pseudo */
				break;
			case "UserUpdated":
				System.out.println("A user has changed his/her pseudo");
				//contr.updateUser(packet.getSrcUser());
			/* If a user is disconnected */
				break;
			case "UserDisconnected":
				System.out.println("A user is disconnected");
				//contr.deleteUser(packet.getSrcUser());
				break;
			default :
				System.out.println("The UDP packet motive is not recognized !");
		}
	}
	
	
}
