package main;
public class UDPPacket extends Packet{
	private static final long serialVersionUID = 1L;
	private String motive;
	
	public UDPPacket(User srcUser, User destUser, String motive) {
		super(srcUser, destUser);
		this.motive = motive;
	}
	
	public String getMotive() {
		return this.motive;
	}
}
