package main;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* Listen on the UDP socket and manage the received UDP packets */

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
				System.out.println("Close application");
			}
		}
	}
	
	/* Handle the UDP packet received */
	public void handlePacket(UDPPacket packet, InetAddress address) {
		String motive = packet.getMotive();
		switch(motive) {
			/* If a user list request is received */
			case "RequestListUsers":
				/* If the local user is the last user connected, 
				 * send the list of users
				 */
				System.out.println("Send the listUsers to : " + address);
				if (this.nwk.lastUserConnected()) {
					this.nwk.sendListUsersUDPPacket(packet.getSrcUser(), address);
				}
			break;
			/* If a new user is connected */
			case "UserConnected":
				System.out.println("A new user is connected : " + packet.getSrcUser().getPseudo() + " " + address);
				/* Add the new user to the user list */
				User newUser = packet.getSrcUser();
				newUser.setAddress(address);
				this.nwk.addUser(newUser);
				this.nwk.getController().displayAllUsers();
				this.nwk.getController().refreshWindows();
				break;
			/* If a user has changed his/her pseudo */
			case "UserUpdated":
				/* Update his/her pseudo */
				System.out.println("A user has changed his/her pseudo");
				nwk.updateUser(packet.getSrcUser());
				System.out.println("The list of users is updated");
				this.nwk.getController().displayAllUsers();
				this.nwk.getController().refreshWindows();
				break;
			/* If a user is disconnected */
			case "UserDisconnected":
				/* Delete this user from the connected user list */
				System.out.println("A user is disconnected");
				this.nwk.deleteUser(packet.getSrcUser());
				this.nwk.getController().displayAllUsers();
				this.nwk.getController().refreshWindows();
				break;
			/* If the user list is received */
			case "ReplyListUsers":
				System.out.println("The list of users is received");
				/* Get the user list */
				this.nwk.setListUsers(((ListUsersUDPPacket) packet).getListUsers());
				/* Add the user, who sent the packet, to the user list */
				User srcUser = packet.getSrcUser();
				srcUser.setAddress(address);
				this.nwk.addUser(srcUser);
				this.nwk.getController().displayAllUsers();
				break;
			default :
				System.out.println("The UDP packet motive is not recognized !");
		}
	}
	
	public void stop() {
		this.socket.close();
	}
	
	
}
