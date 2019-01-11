package main;
import java.io.File;
import java.sql.Timestamp;

public class Message extends Packet {
	private static final long serialVersionUID = 1L;
	//public int idMessage;
	public String msg =null;
	public File file=null;
	public Timestamp dateMessage;
	
	public Message(String msg, User srcUser, User destUser) {
		super(srcUser, destUser);
		this.msg = msg;
		this.dateMessage.setTime(System.currentTimeMillis());  
	}
	
	public Message(File file, User srcUser, User destUser) {
		super(srcUser, destUser);
		this.file = file;
		this.dateMessage.setTime(System.currentTimeMillis());  
	}

	public Message(String msg, File file, User srcUser, User destUser) {
		super(srcUser, destUser);
		this.msg = msg;
		this.file = file;
		this.dateMessage.setTime(System.currentTimeMillis());  
	}
	
/*	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
*/
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Timestamp getHorodate() {
		return dateMessage ;
	}

	public void setHorodate(Timestamp horodate) {
		this.dateMessage  = horodate;
	}
	
	
}
