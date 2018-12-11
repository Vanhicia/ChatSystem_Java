import java.util.ArrayList;
import java.util.List;
public class History {
	private	List<Message> history;
	private int userId; //userDistant
	
	public History(int id) {
		this.userId = id;
		this.history = new ArrayList<>();
	}
	
	public void addEntry(Message msg) {
		this.history.add(msg);
		if (this.history.size()<=10) {
			deleteEntry();
		}
	}
	
	public void deleteEntry() {
		this.history.remove(0);
	}

	public List<Message> getHistory() {
		return history;
	}

	public int getUserId() {
		return userId;
	}
	
}
