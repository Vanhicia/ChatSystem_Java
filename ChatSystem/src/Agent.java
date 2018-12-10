import java.sql.Time;

public class Agent {
	private int id;
	private String pseudo;
	private long horodate;
	
	public Agent(String pseudo) {
		this.pseudo = pseudo;
		this.horodate = System.currentTimeMillis();
	}
	
	public int getId() {
		return id;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public long getHorodate() {
		return horodate;
	}
}
