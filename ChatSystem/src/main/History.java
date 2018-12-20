package main;

import java.util.ArrayList;
import java.util.List;
public class History {
	private	List<Message> history;
	private User userDistant;
	
	public History(User dest) {
		this.userDistant = dest;
		this.history = new ArrayList<>();
	}
	
	public void addEntry(Message msg) {
		this.history.add(msg);
	}
	
	public void deleteEntry() {
		this.history.remove(0);
	}

	public void printHistory() {
		System.out.println("history");
		for (Message tmp : this.history) {
			System.out.println(tmp.msg);
		}
	}
	
	public List<Message> getHistory() {
		return history;
	}

	public User getUserDistant() {
		return userDistant;
	}
	
}
