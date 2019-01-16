package main;

import java.util.List;

import main.database.Database;

public class History {
	private	List<Message> history;
	private User userDistant;
	private Database db;
	
	public History(User local, User dest, Database db) {
		this.db = db;
		this.userDistant = dest;
		this.history = this.db.getHistory(local, dest);
		this.db = db;
	}
	
	public void addEntry(Message msg) {
		/* Add the message in the history object */
		this.history.add(msg);
		/* Add the message in the database */
		this.db.insertMessage(this.userDistant, msg);
	}
	
	public void deleteEntry() {
		this.history.remove(0);
	}

	public String printHistory() {
        String hist="";
		for (Message tmp : this.history) {
			hist+=(tmp.getSrcUser().getPseudo()+" : "+tmp.msg+"\n");
		}
        return hist;
	}
	
	public List<Message> getHistory() {
		return history;
	}

	public User getUserDistant() {
		return userDistant;
	}
	
}
