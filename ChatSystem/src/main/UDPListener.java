package main;
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
				System.out.println("UDP packet received from " + inPacket.getAddress());
				
				//Deserialize packet
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
	            ObjectInputStream ois = new ObjectInputStream(bais);
	            UDPPacket packet = (UDPPacket) ois.readObject();
	            if (!packet.getSrcUser().getId().equals(nwk.getController().getUser().getId())) {	       
	            //if (!(packet.getSrcUser().getAddress().equals(this.nwk.getController().getUser().getAddress()))) {
	            	System.out.println("Handle received packet because adress different");
	            	System.out.println("address source : "+ packet.getSrcUser().getAddress());
	            	System.out.println("address local user : "+ this.nwk.getController().getUser().getAddress());
	            	this.handlePacket(packet);
	            }
	            else if (this.nwk.getLocalUserAddress()==null) {
	            	this.nwk.setLocalUserAddress(inPacket.getAddress());
	            	System.out.println("Update local user address : " + this.nwk.getLocalUserAddress());
	            }
	            
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
					/* Send a packet in unicast to prevent the pseudo is already used */
					this.nwk.sendUDPPacketUnicast(new UDPPacket(this.nwk.getController().getUser(),packet.getSrcUser(),"PseudoAlreadyUsed"),packet.getSrcUser().getAddress());
				}
				break;
			/* If the pseudo chosen by the local user is already used */
			case "PseudoAlreadyUsed":
				System.out.println("Someone has already this pseudo, choose another one !");
				this.nwk.setUnicityPseudo(false);
				break;
			/* If a new user is connected */
			case "UserConnected":
				System.out.println("A new user is connected : " + packet.getSrcUser().getPseudo());
				/* If the local user is the previous last user connected, 
				 * send the list of users to the new user connected
				 */
				if (this.nwk.lastUserConnected()) {
					this.nwk.sendListUsersUDPPacket(packet.getSrcUser());
				}
				/* Add the new user to the list of users */
				this.nwk.addUser(packet.getSrcUser());
				break;
			/* If a user has changed his/her pseudo */
			case "UserUpdated":
				System.out.println("A user has changed his/her pseudo");
				nwk.updateUser(packet.getSrcUser());
				break;
			/* If a user is disconnected */
			case "UserDisconnected":
				System.out.println("A user is disconnected");
				this.nwk.deleteUser(packet.getSrcUser());
				break;
			/* If the list of Users is received */
			case "ListUsers":
				System.out.println("The list of users received");
				this.nwk.setListUsers(((ListUsersUDPPacket) packet).getListUsers());
				break;
			default :
				System.out.println("The UDP packet motive is not recognized !");
		}
	}
	
	
}
