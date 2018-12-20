package main;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
			try {				
				DatagramPacket inPacket = new DatagramPacket(data, data.length);
				socket.receive(inPacket);
				
				/* Deserialize packet */
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
	            ObjectInputStream ois = new ObjectInputStream(bais);
	            UDPPacket packet = (UDPPacket) ois.readObject();
	            
	            /* If it is not a packet from the local user */
	            if (!(packet.getSrcUser().getId().equals(nwk.getController().getUser().getId()))) {
	            	System.out.println("UDP packet received from " + inPacket.getAddress());
	            	
	            	System.out.println("address source (user) : "+ packet.getSrcUser().getAddress());
	            	/* Handle the packet */
	            	this.handlePacket(packet,inPacket.getAddress());
	            }
	            /* Else this packet is from the local user */
	            /* If the local user IP address is not known yet */
	            else if (this.nwk.getController().getUser().getAddress().equals(InetAddress.getLocalHost())) {
	            	/* The local user IP address is set */
	            	this.nwk.getController().getUser().setAddress(inPacket.getAddress());
	            	System.out.println("Update local user address : " + this.nwk.getController().getUser().getAddress());
	            }
	            
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Handle the UDP packet received */
	public void handlePacket(UDPPacket packet, InetAddress address) {
		String motive = packet.getMotive();
		switch(motive) {
			/* If the packet asked the uniqueness of a pseudo */
			case "NewPseudo":				
				/* If the local user uses this pseudo, he/she answered with a unicast UDP packet */
				if (packet.getSrcUser().getPseudo().equals(nwk.getController().getUser().getPseudo())) {
					System.out.println("Someone would like to use your pseudo !");
					/* Send a packet in unicast to warn the pseudo is already used */
					this.nwk.sendUDPPacketUnicast(new UDPPacket(this.nwk.getController().getUser(),packet.getSrcUser(),"PseudoAlreadyUsed"),address);
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
				this.nwk.getController().displayAllUsers();
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
				this.nwk.getController().displayAllUsers();
				break;
			/* If the list of Users is received */
			case "ListUsers":
				System.out.println("The list of users received");
				this.nwk.setListUsers(((ListUsersUDPPacket) packet).getListUsers());
				this.nwk.getController().displayAllUsers();
				break;
			default :
				System.out.println("The UDP packet motive is not recognized !");
		}
	}
	
	
}
