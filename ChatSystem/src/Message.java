import java.io.File;
import java.time.LocalDateTime;

public class Message {
	public int idMessage;
	public String msg;
	public File file;
	public LocalDateTime horodate;
	
	public Message(String msg) {
		this.msg = msg;
		this.horodate = LocalDateTime.now();  
	}
	
	public Message(File file) {
		this.file = file;
		this.horodate = LocalDateTime.now();  
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
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

	public LocalDateTime getHorodate() {
		return horodate;
	}

	public void setHorodate(LocalDateTime horodate) {
		this.horodate = horodate;
	}
	
	
}
