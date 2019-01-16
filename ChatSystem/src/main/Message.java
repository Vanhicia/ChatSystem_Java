package main;
import java.io.File;
import java.sql.Timestamp;

public class Message extends Packet {
	private static final long serialVersionUID = 1L;
	public String msg = null;
	public File file = null;
	public Timestamp dateMessage;
	
	public Message(String msg, User srcUser, User destUser, Timestamp date) {
		super(srcUser, destUser);
		this.msg = msg;
		this.dateMessage = date;  
	}
	
	public Message(String msg, User srcUser, User destUser) {
		super(srcUser, destUser);
		this.msg = msg;
		this.dateMessage = new Timestamp(System.currentTimeMillis());  
	}
	
	public Message(File file, User srcUser, User destUser) {
		super(srcUser, destUser);
		this.file = file;
		this.dateMessage = new Timestamp(System.currentTimeMillis()); 
	}

	public Message(String msg, File file, User srcUser, User destUser) {
		super(srcUser, destUser);
		this.msg = msg;
		this.file = file;
		this.dateMessage = new Timestamp(System.currentTimeMillis());  
	}

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
