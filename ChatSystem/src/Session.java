import java.time.LocalDateTime;
import java.util.UUID;

public class Session {
	private UUID idSession;
	private LocalDateTime horodate;
	private User User1;
	private int idUser2;
	
	public Session() {
		this.idSession = UUID.randomUUID();
		this.horodate = LocalDateTime.now();  
	}
	
	public void sendMessage(Message msg) {
		// TODO
	}

	
	public void receiveMessage(Message msg) {
		//TODO
	}

	
	
	public UUID getIdSession() {
		return idSession;
	}

	public void setIdSession(UUID idSession) {
		this.idSession = idSession;
	}

	public LocalDateTime getHorodate() {
		return horodate;
	}

	public void setHorodate(LocalDateTime horodate) {
		this.horodate = horodate;
	}
	
	
}
