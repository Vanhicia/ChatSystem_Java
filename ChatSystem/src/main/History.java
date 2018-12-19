package main;

import java.util.ArrayList;
import java.util.List;
public class History {
	private	List<Message> history;
	private User userDistant;
	
	public History(User dest) {
		this.userDistant = dest;
		this.history = new ArrayList<Message>();
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

	public User getUserDistant() {
		return userDistant;
	}
	
}
